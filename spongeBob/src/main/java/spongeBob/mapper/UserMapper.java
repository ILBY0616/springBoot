package spongeBob.mapper;

import org.apache.ibatis.annotations.Mapper;
import spongeBob.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {
    Boolean addUser(User user);

    Boolean deleteUser(int uid);

    Boolean updateUser(User user);

    User findUserByUid(int uid);

    User findUserByEmail(String email);

    List<User> findAllUser();
}
