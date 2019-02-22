package com.jdlink.service.Impl;

import com.jdlink.domain.User;
import com.jdlink.mapper.UserMapper;
import com.jdlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUserByUserNameAndPassword(User user) {
        return userMapper.getUserByUserNameAndPassword(user);
    }

    @Override
    public List<User> listUser() { return userMapper.listUser(); }

    @Override
    public void add(User user) { userMapper.add(user); }

    @Override
    public void updateUserById(User user) {
        userMapper.updateUserById(user);
    }

    @Override
    public void deleteUserById(int id) { userMapper.deleteUserById(id); }

    @Override
    public User getUserById(int id) { return userMapper.getUserById(id); }
}
