package com.example.holidaywork.service;

import com.example.holidaywork.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService{
    //查找全部类别
    List<Category> findAll();
}
