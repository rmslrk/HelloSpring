package service;

import domain.UserDTO;
import exception.BaseException;
import exception.ErrorMessage;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class UserServiceImpl implements UserService {

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


    // 현재 로그인한 유저를 가져오는 메소드
    @Override
    public UserDTO getLoginUser() throws Exception{

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        if ( token == null ){
            return null;
        }
        else {
            // token의 uid를 가져와 user의 데이터를 반환
            Map<String, Object> payloads = jwt.isValid(token);
            Long id = Long.valueOf(String.valueOf(payloads.get("id")));
            return userMapper.getMe(id);
        }
     }

    // 유저의 추가 정보를 설정하는 메소드
    @Override
    public BaseResponse setUserInfo(UserDTO user) throws Exception{

         // dbUser에 현재 로그인한 유저를 가져옴
        UserDTO dbUser = this.getLoginUser();

        if (dbUser==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        // updateUser
        userMapper.setUserInfo(dbUser.getId() ,user.getAge(),user.getSex(), user.getPhone_number(),user.getAddress());
        
        return new BaseResponse("회원 정보 등록에 성공했습니다.", HttpStatus.OK);

    }

    // 로그인 메소드
    @Override
    public Map<String, String> login(UserDTO user) throws Exception {
         
        if (user.getEmail() == null)
            throw new Exception("이메일이 잘못되었습니다.");

        if (!BCrypt.checkpw(user.getPw(), userMapper.getPwtoEmail(user.getEmail()))) {
            throw new Exception("비밀번호가 잘못되었습니다.");
        } 
        else {
            Map<String, String> token = new HashMap<>();
            // 토큰 발급
            token.put("access_token", jwt.createToken(user.getId(), user.getNickname()));
            return token;
        }
    }

    // 토큰 재발급 메소드
    @Override
    public Map<String, String> refresh() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");
        // => "Authorization" 부분 이해 안감 : 찾아볼것

        // token에서 payloads를 받아옴.
        Map<String, Object> payloads = jwt.validateFormat(token);
        if (payloads == null)
            throw new Exception("토큰이 잘못되었습니다.");

        // payloads의 uid 값을 저장
        Long uid = Long.valueOf(String.valueOf(payloads.get("uid")));

        // user 정보 저장
        UserDTO user = new UserDTO();
        user.setId(uid);
        user.setNickname(payloads.get("nickname").toString());

        //UserMapper userMapper = null;
        user.setSalt(uid.toString());

        // 토큰 재발급
        Map<String, String> refresh_token = new HashMap<>();
        refresh_token.put("access_token", new Jwt().createToken(user.getId(), user.getNickname()));
        return refresh_token;
    }

    // 회원 탈퇴 메소드
    @Override
    public BaseResponse deleteUser() throws Exception {

         // 현재 로그인한 유저를 가져옴
         UserDTO user = this.getLoginUser();

         userMapper.delete(user.getId());

        return new BaseResponse("회원 탈퇴에 성공했습니다.", HttpStatus.OK);
    }
}
