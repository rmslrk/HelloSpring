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
import service.UserServiceImpl;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // 회원가입
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    @ApiOperation(value = "회원가입", notes = "회원가입 API {이메일, 비밀번호, 이름, 닉네임}")
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
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    @ApiOperation(value = "토큰 초기화", notes = "토큰 초기화 API")
    public ResponseEntity refresh() throws Exception {
        return new ResponseEntity(userService.refresh(), HttpStatus.OK);
    }

    // 토큰 확인
    @RequestMapping(value = "/checkKey", method = RequestMethod.POST)
    @ApiOperation(value = "토큰 확인", notes = "토큰 확인 API {토큰}")
    public ResponseEntity checkKey(@RequestBody String key) throws Exception {
        return new ResponseEntity(userService.checkKey(key), HttpStatus.OK);
    }

    /*
    // 회원가입
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "사용자 추가", notes = "사용자를 추가하기 위한 API")
    public String hello1(){
        System.out.println("안녕하세요");
        return "hello";
    }
    @ResponseBody
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2(){
        return "hello";
    }
    */
}
