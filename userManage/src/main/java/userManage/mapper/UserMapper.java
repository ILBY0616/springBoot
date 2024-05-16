package userManage.mapper;

import org.apache.ibatis.annotations.Mapper;
import userManage.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {
    Boolean addUser(User user);

    Boolean deleteUser(int uid);

    Boolean updateUser(User user);

    User findUser(int uid);

    List<User> findAllUser();
}
