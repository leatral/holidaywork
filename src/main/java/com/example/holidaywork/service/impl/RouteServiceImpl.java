package com.example.holidaywork.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.holidaywork.dao.FavoriteMapper;
import com.example.holidaywork.dao.RouteImgMapper;
import com.example.holidaywork.dao.SellerMapper;
import com.example.holidaywork.pojo.Favorite;
import com.example.holidaywork.pojo.Route;
import com.example.holidaywork.dao.RouteMapper;
import com.example.holidaywork.pojo.RouteImg;
import com.example.holidaywork.pojo.Seller;
import com.example.holidaywork.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteMapper routeMapper;
    @Autowired
    private SellerMapper sellerMapper;
    @Autowired
    private RouteImgMapper routeImgMapper;
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    //查询指定页数据
    public Page<Route> findByPage(Integer cid, int pageNo, String rname) {
        //暂且写死一页五条数据
        Page<Route> page = new Page<>(pageNo,5);

        //查询数据
        QueryWrapper<Route> wrapper = new QueryWrapper<>();
        if(cid != null){
            wrapper.eq("cid",cid);
        }
        if(!"null".equals(rname)){
            wrapper.like("rname",rname);
        }
        routeMapper.selectPage(page,wrapper);

        return page;
    }

    @Override
    //查询业务的详细信息
    public Route findOne(int rid) {
        //查询route的大部分信息
        Route route = routeMapper.selectById(rid);

        //查询route的详细图片、商家
        Seller seller = sellerMapper.selectById(route.getSid());
        List<RouteImg> routeImgs = routeImgMapper.selectList(new QueryWrapper<RouteImg>().eq("rid", route.getRid()));
        int count = favoriteMapper.selectCount(new QueryWrapper<Favorite>().eq("rid", rid));

        //将查询到的数据存入route
        route.setRouteImgs(routeImgs);
        route.setSeller(seller);
        route.setCount(count);

        return route;
    }
}
