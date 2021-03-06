package com.example.holidaywork.dao;

import com.example.holidaywork.pojo.Favorite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.holidaywork.pojo.Route;
import com.example.holidaywork.pojo.FavRoute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoriteMapper extends BaseMapper<Favorite> {

    //根据收藏数查找指定页的业务
    List<FavRoute> findPageByCount(@Param("begin") int begin,
                                   @Param("size") int size);

    //查询用户收藏夹
    List<Route> findPageByUid(@Param("uid") int uid,
                              @Param("begin") int begin,
                              @Param("size") int size);

    //查询用户收藏总数据
    int findTotalByUid(int uid);

    //查询有收藏的业务
    int findTotalCountByRid();
}
