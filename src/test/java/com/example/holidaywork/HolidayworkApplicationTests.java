package com.example.holidaywork;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.holidaywork.dao.FavoriteMapper;
import com.example.holidaywork.service.FavoriteService;
import com.example.holidaywork.pojo.FavRoute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HolidayworkApplicationTests {
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Test
    void contextLoads() {
//        Page<FavRoute> pageByCount = favoriteService.findPageByCount(1);
//        System.out.println(pageByCount);
        System.out.println(favoriteMapper.findTotalCountByRid());
    }

}
