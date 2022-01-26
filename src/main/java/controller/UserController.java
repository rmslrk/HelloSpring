package controller;

import domain.UserDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.UserService;


@RequestMapping(value = "/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    @ApiOperation(value = "회원가입", notes = "회원가입을 진행합니다 {이메일, 비밀번호, 이름, 닉네임}")
    public ResponseEntity signUp(@RequestBody @Validated(UserDTO.class) UserDTO user) throws Exception {
        return new ResponseEntity(userService.signUp(user), HttpStatus.OK);
    }

    // 로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "로그인", notes = "로그인 API {이메일, 비밀번호}")
    public ResponseEntity login(@RequestBody @Validated(UserDTO.class) UserDTO user) throws Exception {
        return new ResponseEntity(userService.login(user), HttpStatus.OK);
    }

    // 토큰 초기화
    @RequestMapping(value = "/refresh", method = RequestMethod.PATCH)
    @ApiOperation(value = "토큰 초기화", notes = "토큰 초기화 API")
    public ResponseEntity refresh() throws Exception {
        return new ResponseEntity(userService.refresh(), HttpStatus.OK);
    }

    // 회원 정보 확인
    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    @ApiOperation(value = "회원 정보 확인", notes = "회원 정보 확인 API")
    public ResponseEntity mypage() throws Exception{
        return new ResponseEntity(userService.getLoginUser(), HttpStatus.OK);
    }

    // 회원 정보 등록
    @RequestMapping(value = "/userInfo", method = RequestMethod.PATCH)
    @ApiOperation(value = "회원 정보 등록", notes = "회원 정보 등록 API")
    public ResponseEntity setUserInfo(@RequestBody @Validated(UserDTO.class) UserDTO user) throws Exception{
        return new ResponseEntity(userService.setUserInfo(user), HttpStatus.OK);
    }

    // 회원 탈퇴
    @RequestMapping(value = "/withdraw", method = RequestMethod.DELETE)
    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴 API")
    public ResponseEntity withdraw() throws Exception{
        userService.deleteUser();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
