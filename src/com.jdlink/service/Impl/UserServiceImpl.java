package com.jdlink.service.Impl;

import com.jdlink.domain.User;
import com.jdlink.mapper.UserMapper;
import com.jdlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        userMapper.updatePasswordById(user);
    }

    @Override
    public void deleteUserById(int id) { userMapper.deleteUserById(id); }

    @Override
    public User getUserById(int id) { return userMapper.getUserById(id); }

    @Override
    public boolean checkUserNameIsExist(String userName) {
        User user = userMapper.getUserByUserName(userName);
        if(user != null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkUserPasswordModifyTimeIsLate(User user) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date d1 = new Date();
            Date d2 = user.getPasswordModifyTime();   //账号密码最近修改时间
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是毫秒级别
            long days = diff / (1000 * 60 * 60 * 24);  // 相差天数
            // long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);  // 相差小时数
           // long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);  // 相差分钟数
            if(days >= 90) {
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
