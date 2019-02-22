package com.jdlink.service;

import com.jdlink.domain.User;

import java.util.List;

public interface UserService {

    List<User> getUserByUserNameAndPassword(User user);

    List<User> listUser();

    void add(User user);

    void updateUserById(User user);

    void deleteUserById(int id);

    User getUserById(int id);
}
