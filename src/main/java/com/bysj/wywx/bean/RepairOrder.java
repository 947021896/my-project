package com.bysj.wywx.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("维修单")
@AllArgsConstructor
@NoArgsConstructor
@TableName("repair_order")
public class RepairOrder implements Serializable {

    @TableId("order_id")
    private String orderId;
    @TableField("user_id")
    private String userId;
    private String name;
    private String tel;
    private String title;
    private String content;
    @TableField("address_id")
    private String addressId;
    @TableField("address_content")
    private String addressContent;
    @TableField("repair_category_id")
    private String repairCategoryId;
    //TODO pic 如何存储
    private String pic;
    private Date time;
    @TableField("state_tag")
    private Integer stateTag;// 0：可修改（待审核，未处理） 1：审核通过（不可修改）（审核通过自动生成工单）
}
