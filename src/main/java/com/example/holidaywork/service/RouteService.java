package com.example.holidaywork.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.holidaywork.pojo.Route;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RouteService {
    //查询指定页面数据
    Page<Route> findByPage(Integer cid, int pageNo, String rname);

    //查询指定业务的详细信息
    Route findOne(int rid);
}
