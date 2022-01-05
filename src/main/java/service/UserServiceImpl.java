package service;

import domain.UserDTO;
import response.BaseResponse;

import java.util.Map;

public interface UserServiceImpl{
    BaseResponse signUp(UserDTO user) throws Exception;

    void updateUser(UserDTO user) throws Exception;

    Map<String, Object> checkKey(String key) throws Exception;

    Map<String, String> login(UserDTO user) throws Exception;

    Map<String, String> refresh() throws Exception;
}
