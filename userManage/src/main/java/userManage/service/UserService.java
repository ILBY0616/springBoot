package userManage.service;

import org.springframework.stereotype.Service;
import userManage.mapper.UserMapper;
import userManage.pojo.User;

import java.util.List;

@Service
public class UserService {
    final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    public Boolean deleteUser(int uid) {
        return userMapper.deleteUser(uid);
    }

    public Boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public User findUser(int uid) {
        return userMapper.findUser(uid);
    }

    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
