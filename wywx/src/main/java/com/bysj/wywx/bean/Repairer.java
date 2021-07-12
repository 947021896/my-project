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
@TableName("repairer")
public class Repairer implements Serializable {

    @TableId("repairer_id")
    private String repairerId;
    private String password;
    private String name;
    private String tel;
    private Integer sex;//0：男 1：女
    private String age;
    private String pic;
    @TableField("add_time")
    private Date addTime;

}
