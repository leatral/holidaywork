package com.example.holidaywork.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

//包含收藏数的业务类
@Data
public class FavRoute implements Serializable {
    @ApiModelProperty(value = "线路ID")
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    @ApiModelProperty(value = "线路名称")
    private String rname;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "缩略图")
    private String rimage;

    @ApiModelProperty("收藏次数")
    private Integer favCount;
}
