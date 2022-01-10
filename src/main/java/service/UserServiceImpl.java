package service;

import domain.UserDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import repository.UserMapper;
import response.BaseResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

     @Autowired
     private UserMapper userMapper;

     //@Autowired
     //private Jwt jwt;

     @Value("{token.user.name}")
     private String accessTokenName;

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


    // token의 id를 가져와 User를 반환하는 Method
    public UserDTO getLoginUser() throws Exception{
//
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String token = request.getHeader(accessTokenName);
//        if ( token == null){
//            return null;
//        }
//        else {
//            // user id로 User를 select 하는것은 자유롭게 해도 좋으나, salt값은 조회,수정 하면안된다. 만약 참고할 일이있으면 정수현에게 다렉을 보내도록하자.
//            if ( jwt.isValid(token,0) ==0 ) {
//                Map<String, Object> payloads = jwt.validateFormat(token, 0);
//                Long id = Long.valueOf(String.valueOf(payloads.get("id")));
//                return userMapper.getMe(id);
//            }
//            else{
//                throw new AccessTokenInvalidException(ErrorMessage.ACCESS_FORBIDDEN_AUTH_INVALID_EXCEPTION);
//            }
//        }
    return null;
     }


    @Override
    public void updateUser(UserDTO user) throws  Exception{

        //UserDTO dbUser = this.getLoginUser();

        //updateUser
        //userMapper.updateUser(dbUser.getUid() ,user.getAge(),user.getSex(), user.getPhone_number(),user.getAddress());

    }

    @Override
    public Map<String, Object> checkKey(String token) throws Exception {
    //    return jwt.verifyJWT(token);
        return null;
    }

    @Override
    public Map<String, String> login(UserDTO user) throws Exception {
//        UserDTO userDTO = userMapper.getUserToEmail(user.getEmail());
//        if (user.getEmail() == null)
//            throw new Exception("이메일이 잘못되었습니다.");
//
//        if (!BCrypt.checkpw(user.getPw(), userMapper.getPwtoEmail(user.getEmail()))) {
//            throw new Exception("비밀번호가 잘못되었습니다.");
//        } else {
//            Map<String, String> token = new HashMap<>();
//            // 토큰 발급
//            token.put("access_token", jwt.createToken(userDTO));
//            return token;
//        }
    return null;
    }

    @Override
    public Map<String, String> refresh() throws Exception {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String token = request.getHeader("Authorization");
//        Map<String, Object> data = jwt.verifyJWT(token);
//        UserMapper userMapper = null;
//        if (data == null)
//            throw new Exception("토큰이 잘못되었습니다.");
//
//        UserDTO user = new UserDTO();
//        user.setUid(Long.parseLong(data.get("uid").toString()));
//        user.setNickname(data.get("nickname").toString());
//
//        user.setSalt(userMapper.getSaltToUid(user.getUid()));
//
//        Map<String, String> refresh_token = new HashMap<>();
//        // 토큰 재발급
//        refresh_token.put("access_token", new Jwt().createToken(user));
//        return refresh_token;
 return null;
    }


}
