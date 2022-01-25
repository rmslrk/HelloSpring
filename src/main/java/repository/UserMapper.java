package repository;

import domain.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    void signUp(UserDTO user);

    void setUserInfo(Long id, int age, int sex, String phone_number, String address);

    short getUserNumToEmail(String email);

    short getUserNumToNickName(String nickname);

    UserDTO getUserToEmail(String email);

    String getSaltToUid(Long id);

    Long getUidToEmail(String email);

    String getPwtoEmail(String email);

    void setSalt(Long id, String salt);

    UserDTO getMe(Long id);

    void delete(Long uid);
}
