package com.example.holidaywork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.holidaywork.pojo.User;
import com.example.holidaywork.dao.UserMapper;
import com.example.holidaywork.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.holidaywork.util.MailUtils;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private Producer kaptchaProduce;    //借用Kaptcha生成激活码

    @Override
    //用户登录
    public User login(User user) {
        //判断是否存在该用户
        User u = userMapper.findByUsername(user.getUsername());
        if(u.getPassword().equals(user.getPassword())){
            return u;
        }

        return null;
    }

    @Override
    //用户注册
    public boolean register(User user) {
        //如果用户名已存在，注册失败
        if(userMapper.findByUsername(user.getUsername()) != null)return false;

        user.setStatus("N");    //默认为未激活状态
        String code = kaptchaProduce.createText();;    //生成验证码
        user.setCode(code);
        //调用dao层进行注册
        userMapper.insert(user);

        //发送激活邮件
        String content = "<a href='http://localhost:8080/user/active/"+code+"'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;
    }

    @Override
    //激活指定验证码的账户
    public boolean active(String code) {
        int i = userMapper.updateStatusByCode(code);
        return i == 1;
    }

    @Override
    //根据用户名查找用户
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
