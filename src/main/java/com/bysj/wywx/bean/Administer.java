package com.bysj.wywx.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("administer")
public class Administer implements Serializable {

    @TableId("ad_id")
    private String adId;
    private String password;
    private String name;
    private String tel;
    @TableField("create_date")
    private Date createDate;
}
