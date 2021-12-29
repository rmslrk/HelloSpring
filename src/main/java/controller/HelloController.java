package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1(){
        System.out.println("안녕하세요");
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2(){
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String mybatisTest(@RequestParam(value ="name") String name){
        return userService.mybatisTest(name);
    }
}
