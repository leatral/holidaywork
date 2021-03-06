package com.example.holidaywork.controller;


import com.example.holidaywork.pojo.Category;
import com.example.holidaywork.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@Api(tags="类别控制器")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/findAll")
    @ApiOperation("查询全部类别")
    public List<Category> findAll(){
        return categoryService.findAll();
    }
}

