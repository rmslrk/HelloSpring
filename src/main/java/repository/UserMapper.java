package repository;

import domain.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    void signUp(UserDTO user);

    void setAge(Long uid, int age);

    void setSex(Long uid, int sex);

    void setPhone(Long uid, String phone_number);

    void setAddress(Long uid, String address);

    short getUserNumToEmail(String email);

    short getUserNumToNickName(String nickname);

    UserDTO getUserToEmail(String email);

    String getSaltToUid(Long uid);

    Long getUidToEmail(String email);

    String getPwtoEmail(String email);

    void setSalt(Long uid, String salt);
}
