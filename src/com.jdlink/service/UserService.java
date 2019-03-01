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

    boolean checkUserNameIsExist(String userName);

    /**
     * 检测账号密码是否超过90天未修改
     * @param user
     * @return
     */
    int checkUserPasswordModifyTimeIsLate(User user);

    List<User> searchUser(User user);

    int searchUserTotal(User user);


}
