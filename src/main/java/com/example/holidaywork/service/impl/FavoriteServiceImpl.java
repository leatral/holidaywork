package com.example.holidaywork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.holidaywork.dao.RouteMapper;
import com.example.holidaywork.pojo.Favorite;
import com.example.holidaywork.dao.FavoriteMapper;
import com.example.holidaywork.pojo.Route;
import com.example.holidaywork.service.FavoriteService;
import com.example.holidaywork.pojo.FavRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    //添加收藏
    public void addFav(int rid,int uid) {
        Favorite favorite = new Favorite();
        favorite.setDate(new Date());
        favorite.setRid(rid);
        favorite.setUid(uid);
        favoriteMapper.insert(favorite);
    }

    @Override
    //展示用户收藏
    public Page<Route> findPageByUid(int uid,int pageNo) {
        Page<Route> page = new Page<>(pageNo,10);

        //查询数据
        int begin = (pageNo-1)*5;
        List<Route> records = favoriteMapper.findPageByUid(uid, begin, 10);
        //封装数据
        page.setRecords(records);

        //查询总收藏数
        page.setTotal(favoriteMapper.findTotalByUid(uid));

        return page;
    }

    @Override
    //获取最受欢迎的业务
    public Page<FavRoute> findPageByCount(int pageNo) {
        Page<FavRoute> page = new Page<>(pageNo,10);

        //查询数据
        int begin = (pageNo-1)*5;
        List<FavRoute> records = favoriteMapper.findPageByCount(begin, 10);
        //封装数据
        page.setRecords(records);

        //查询总收藏数
        page.setTotal(favoriteMapper.findTotalCountByRid());

        return page;
    }

    @Override
    //查询用户是否收藏指定业务
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteMapper.selectOne(new QueryWrapper<Favorite>()
                .eq("rid", rid)
                .eq("uid", uid));
        return favorite != null;
    }
}
