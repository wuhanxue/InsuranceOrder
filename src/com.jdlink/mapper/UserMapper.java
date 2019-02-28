package com.jdlink.mapper;

import com.jdlink.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> getUserByUserNameAndPassword(User user);

    List<User> listUser();

    void add(User user);

    void updateUserById(User user);

    void updatePasswordById(User user);

    void deleteUserById(int id);

    User getUserById(int id);

    User getUserByUserName(String userName);

    List<User> searchUser(User user);

    int searchUserTotal(User user);
}
