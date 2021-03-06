package com.example.holidaywork.service.impl;

import com.example.holidaywork.pojo.Category;
import com.example.holidaywork.dao.CategoryMapper;
import com.example.holidaywork.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //查找全部类别
    public List<Category> findAll(){
        //1、从Redis中查询
        List list = redisTemplate.opsForList().range("category", 0, -1);
        //2、判断查询的集合是否为空
        if(list == null || list.size() == 0){
            //如果为空，则需从数据库中查询，并将数据存入Redis
            list = categoryMapper.selectList(null);
            redisTemplate.opsForList().rightPushAll("category",list);
        }
        //如果不为空，则直接返回
        return list;
    }
}
