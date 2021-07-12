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
@ApiModel("普通用户实体")
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @TableId("user_id")
    private String userId;
    private String name;
    private String tel;
    @TableField("create_date")
    private Date createDate;
}
