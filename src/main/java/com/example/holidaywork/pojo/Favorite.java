package com.example.holidaywork.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Favorite对象", description="")
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
      @TableId(value = "rid", type = IdType.ASSIGN_ID)
    private Integer rid;

    @ApiModelProperty(value = "日期")
    private Date date;

    @ApiModelProperty(value = "用户ID")
    private Integer uid;


}
