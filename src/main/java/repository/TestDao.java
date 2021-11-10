package repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.*;

@Repository
public class TestDao {
    public static List<Test> tests;

    static {
        tests = new ArrayList<>();
        tests.add(new Test(1,"test1", 1,1));
    }

    // Select all test
    public List<Test> getAllTests(){
        return tests;
    }

    // Select one user by userId
    public Test getTestByTestName(String testName){
        return tests
                .stream()
                .filter(test -> test.getName().equals(testName))
                .findAny()
                .orElse(new Test(-1,"",-1,-1));
    }

    // Insert Test
    public Test insertTest(Test test){
        tests.add(test);

        return test;
    }

    // Modify Test
    public void updateTest(String testName, Test test){
        tests.stream()
                .filter(curTest -> curTest.getName().equals(testName))
                .findAny()
                .orElse(new Test(-1,"",-1,-1))
                .setName(test.getName());
    }

    // Delete Test
    public void deleteTest(String testId){
        tests.removeIf(test -> test.getName().equals(testId));
    }
}
