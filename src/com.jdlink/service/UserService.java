package com.jdlink.service;

import com.jdlink.domain.User;

import java.util.List;

public interface UserService {

    List<User> getUserByUserNameAndPassword(User user);
}
