package service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public String mybatisTest(String name){
        return userMapper.mybatisTest(name);
    }

    @Override
    public String
}
