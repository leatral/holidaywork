package com.example.holidaywork.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.holidaywork.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User> {
    //根据用户名查找用户
    User findByUsername(@Param("username") String username);

    //更新激活状态
    int updateStatusByCode(@Param("code") String code);
}
