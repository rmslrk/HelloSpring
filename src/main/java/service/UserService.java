package service;

import domain.UserDTO;
import response.BaseResponse;

import java.util.Map;

public interface UserService {
    BaseResponse signUp(UserDTO user) throws Exception;

    UserDTO getLoginUser() throws Exception;

    BaseResponse setUserInfo(UserDTO user) throws Exception;

    Map<String, String> login(UserDTO user) throws Exception;

    Map<String, String> refresh() throws Exception;

    BaseResponse deleteUser() throws Exception;
}
