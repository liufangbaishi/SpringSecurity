package com.cheng.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台接口表
 * </p>
 *
 * @author wei
 * @since 2021-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysApi对象", description="后台接口表")
public class SysApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "API名称")
    private String apiName;

    @ApiModelProperty(value = "API请求地址")
    private String apiUrl;

    @ApiModelProperty(value = "API请求方式：GET、POST、PUT、DELETE")
    private String apiMethod;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "排序")
    private Integer apiSort;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "0：不可用，1：可用")
    private Integer available;

    @ApiModelProperty(value = "描述")
    private String description;


}
