package com.example.holidaywork.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Seller对象", description="")
public class Seller implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商家ID")
      @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    @ApiModelProperty(value = "商家名称")
    private String sname;

    @ApiModelProperty(value = "商家电话")
    private String consphone;

    @ApiModelProperty(value = "商家地址")
    private String address;


}
