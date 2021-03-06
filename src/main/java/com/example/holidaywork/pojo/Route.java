package com.example.holidaywork.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Route对象", description="")
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "线路ID")
      @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    @ApiModelProperty(value = "线路名称")
    private String rname;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "线路介绍")
    @TableField("routeIntroduce")
    private String routeIntroduce;

    @ApiModelProperty(value = "是否上架（1是0否）")
    private String rflag;

    @ApiModelProperty(value = "上架时间")
    private String rdate;

    @ApiModelProperty(value = "是否主题旅游（1是0否）")
    @TableField("isThemeTour")
    private String isthemetour;

    @ApiModelProperty(value = "收藏数量")
    private Integer count;

    @ApiModelProperty(value = "所属类别ID")
    private Integer cid;

    @ApiModelProperty(value = "缩略图")
    private String rimage;

    @ApiModelProperty(value = "所属商家ID")
    private Integer sid;

    @ApiModelProperty("所属商家")
    @TableField(exist = false)
    private Seller seller;

    @ApiModelProperty(value = "抓取数据的来源ID")
    @TableField("sourceId")
    private String sourceid;

    @ApiModelProperty("详情图片")
    @TableField(exist = false)
    private List<RouteImg> routeImgs;

}
