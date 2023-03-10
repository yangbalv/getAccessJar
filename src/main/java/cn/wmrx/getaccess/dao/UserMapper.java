package cn.wmrx.getaccess.dao;

import cn.wmrx.getaccess.model.User;

import java.util.List;

public interface UserMapper {
    int insertUser(User user);

    List<User> selectUser();
}
