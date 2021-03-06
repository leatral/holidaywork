package com.example.holidaywork.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author leatral
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="RouteImg对象", description="")
public class RouteImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片ID")
      @TableId(value = "rgid", type = IdType.AUTO)
    private Integer rgid;

    @ApiModelProperty(value = "商品ID")
    private Integer rid;

    @ApiModelProperty(value = "详情商品大图")
    @TableField("bigPic")
    private String bigPic;

    @ApiModelProperty(value = "详情商品小图")
    @TableField("smallPic")
    private String smallPic;


}
