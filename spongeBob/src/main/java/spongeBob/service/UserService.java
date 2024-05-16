package spongeBob.service;

import org.springframework.stereotype.Service;
import spongeBob.mapper.UserMapper;
import spongeBob.pojo.User;

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

    public User findUserByUid(int uid) {
        return userMapper.findUserByUid(uid);
    }

    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
