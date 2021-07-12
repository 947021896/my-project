package com.bysj.wywx.service;

import com.bysj.wywx.bean.User;

public interface UserService {

    public User findUserById(String id); // 根据ID查找用户
    public int insertUser(User user);
}
