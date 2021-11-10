package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.*;
import java.util.List;
import repository.Test;
import repository.TestDao;

@ComponentScan(basePackages={"repository"})
@Service
public class TestService {
    @Autowired
    TestDao testDao;

    public List<Test> getAllTests(){
        return testDao.getAllTests();
    }

    public Test getTestByTestName(String testId){
        return testDao.getTestByTestName(testId);
    }

    public Test insertTest(Test test){
        return testDao.insertTest(test);
    }

    public void modifyTest(String name, Test test){
        testDao.updateTest(name, test);
    }

    public void removeTest(String name){
        testDao.deleteTest(name);
    }
}
