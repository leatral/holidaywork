package com.example.holidaywork.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.holidaywork.pojo.Route;
import com.example.holidaywork.pojo.User;
import com.example.holidaywork.service.FavoriteService;
import com.example.holidaywork.pojo.FavRoute;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("favorite")
@Api(tags="收藏控制器")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("list")
    @ApiOperation("获取用户收藏夹")
    public Page<Route> list(@ApiParam("页码") int pageNo,
                               HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return null;
        }else{
            Page<Route> page = favoriteService.findPageByUid(user.getUid(), pageNo);
            return page;
        }
    }

    @GetMapping("favRank")
    @ApiOperation("获取最受欢迎的业务")
    public Page<FavRoute> favRank(@ApiParam("页码") int pageNo){
        Page<FavRoute> page = favoriteService.findPageByCount(pageNo);
        return page;
    }
}
