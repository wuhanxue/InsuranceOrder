package com.jdlink.mapper;

import com.jdlink.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> getUserByUserNameAndPassword(User user);

    List<User> listUser();
}
