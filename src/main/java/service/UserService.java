package service;

import domain.UserDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import repository.UserMapper;
import response.BaseResponse;
import util.Jwt;


import javax.servlet.http.HttpServletRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserServiceImpl {

     @Autowired
     private UserMapper userMapper;

     @Autowired
     private Jwt jwt;

     @Override
     public BaseResponse signUp(UserDTO user) throws Exception {
       // 이메일 중복체크
      if (userMapper.getUserNumToEmail(user.getEmail()) != 0)
          return new BaseResponse("이미 존재하는 이메일입니다.", HttpStatus.OK);

      // 닉네임 중복체크
      if (userMapper.getUserNumToNickName(user.getNickname()) != 0)
          return new BaseResponse("이미 존재하는 닉네임입니다.", HttpStatus.OK);

      // 비밀번호 암호화
      user.setPw(BCrypt.hashpw(user.getPw(), BCrypt.gensalt()));
      try {
        userMapper.signUp(user);  // 회원 가입
      } catch (Exception ex) {
           return new BaseResponse("알 수 없는 원인으로 회원가입에 실패하였습니다.", HttpStatus.OK);
      }

      // salt를 설정해주기위해 uid를 가져옴
      Long id = userMapper.getUidToEmail(user.getEmail());

      // salt 생성을 위한 날짜
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(new Date());
      String salt = id.toString() + calendar.getTime();

      salt = (BCrypt.hashpw(salt, BCrypt.gensalt()));
      userMapper.setSalt(id, salt);

      return new BaseResponse("회원가입에 성공했습니다.", HttpStatus.OK);
     }


    @Override
    public Map<String, Object> checkKey(String token) throws Exception {
        return jwt.verifyJWT(token);
    }

    @Override
    public Map<String, String> login(UserDTO user) throws Exception {
        UserDTO userDTO = userMapper.getUserToEmail(user.getEmail());
        if (user.getEmail() == null)
            throw new Exception("이메일이 잘못되었습니다.");

        if (!BCrypt.checkpw(user.getPw(), userMapper.getPwtoEmail(user.getEmail()))) {
            throw new Exception("비밀번호가 잘못되었습니다.");
        } else {
            Map<String, String> token = new HashMap<>();
            // 토큰 발급
            token.put("access_token", jwt.createToken(userDTO));
            return token;
        }
    }

    @Override
    public Map<String, String> refresh() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        Map<String, Object> data = jwt.verifyJWT(token);
        UserMapper userMapper = null;
        if (data == null)
            throw new Exception("토큰이 잘못되었습니다.");

        UserDTO user = new UserDTO();
        user.setUid(Long.parseLong(data.get("uid").toString()));
        user.setNickname(data.get("nickname").toString());

        user.setSalt(userMapper.getSaltToUid(user.getUid()));

        Map<String, String> refresh_token = new HashMap<>();
        // 토큰 재발급
        refresh_token.put("access_token", new Jwt().createToken(user));
        return refresh_token;
    }
}
