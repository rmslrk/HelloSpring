package controller;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.TestService;
import repository.Test;

@Controller
@ComponentScan(basePackages={"service"})
public class TestController {
    @Autowired
    private TestService testService;

    @ResponseBody
    // select all
    @RequestMapping(value="/select",method=RequestMethod.GET)
    public List<Test> getAllUsers() {
        System.out.println("DB에 적용");
        return testService.getAllTests();}

    // select one
    @RequestMapping(value="/select2/{testname}",method=RequestMethod.GET)
    public Test getTestByTestName(@PathVariable String testname) {
        System.out.println("DB에 적용2");
        return testService.getTestByTestName(testname);
    }

    // insert
    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Test InsertTest(@RequestBody Test test) {
        System.out.println("DB에 적용3");
        return testService.insertTest(test);
    }

    // update
    @RequestMapping(value="/update/{testname}",method=RequestMethod.PUT)
    public void modifyTest(@PathVariable String name, @RequestBody Test test) {
        System.out.println("DB에 적용4");
        testService.modifyTest(name, test);
    }

    // delete
    @RequestMapping(value="/delete/{testname}",method=RequestMethod.DELETE)
    public void removeTest(@PathVariable String name) {
        System.out.println("DB에 적용5");
        testService.removeTest(name);
    }
}

