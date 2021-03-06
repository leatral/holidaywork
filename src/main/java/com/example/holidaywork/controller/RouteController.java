package com.example.holidaywork.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.holidaywork.pojo.Route;
import com.example.holidaywork.pojo.User;
import com.example.holidaywork.service.FavoriteService;
import com.example.holidaywork.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/route")
@Api(tags="旅游业务控制器")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/list/")
    @ApiOperation("查询指定页旅游业务")
    public Page<Route> list(@ApiParam("业务编号") Integer cid,
                              @ApiParam("页码") int pageNo,
                              @ApiParam("查询") String rname){
        Page<Route> page = routeService.findByPage(cid, pageNo,rname);
        return page;
    }

    @GetMapping("/findOne")
    @ApiOperation("查询业务详细信息")
    public Route findOne(@ApiParam("业务ID") int rid){
        Route route = routeService.findOne(rid);
        return route;
    }

    @GetMapping("addFav")
    @ApiOperation("添加收藏")
    public boolean addFav(@ApiParam("业务ID") int rid,HttpSession session){
        //获取当前登录用户
        User user = (User) session.getAttribute("user");
        if(user == null){
            //未登录
            return false;
        }else{
            //已登录,则添加收藏
            favoriteService.addFav(rid, user.getUid());
            return true;
        }
    }

    @GetMapping("/judgeFav")
    @ApiOperation("用户是否收藏该业务")
    public boolean isFavorite(@ApiParam("业务ID") String rid, HttpSession session){
        User user = (User) session.getAttribute("user");
        int uid;
        if(user == null){
            //如果未登录
            uid = 0;
        }else{
            //如果已登录
            uid = user.getUid();
        }
        //查询是否收藏
        boolean judge = favoriteService.isFavorite(rid, uid);
        return judge;
    }
}

