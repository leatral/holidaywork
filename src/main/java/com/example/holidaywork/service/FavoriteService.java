package com.example.holidaywork.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.holidaywork.pojo.Route;
import com.example.holidaywork.pojo.FavRoute;

public interface FavoriteService {
    //查询用户是否收藏指定业务
    boolean isFavorite(String rid,int uid);

    //添加收藏
    void addFav(int rid,int uid);

    //展示用户收藏
    Page<Route> findPageByUid(int uid, int pageNo);

    //查询最受欢迎的业务
    Page<FavRoute> findPageByCount(int pageNo);
}
