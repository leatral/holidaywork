package com.example.holidaywork.service;

import com.example.holidaywork.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService{
    //登录
    User login(User user);

    //注册
    boolean register(User user);

    //激活
    boolean active(String code);

    //查找用户
    User findByUsername(String username);
}
