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
@NoArgsConstructor
@AllArgsConstructor
@TableName("work_order")
public class WorkOrder implements Serializable {

    @TableId("work_id")
    private String workId;
    @TableField("order_id")
    private String orderId;
    @TableField("repairer_id")
    private String repairerId;
    @TableField("state_tag")
    private Integer stateTag;
    @TableField("send_time")
    private Date sendTime;
    @TableField("acc_time")
    private Date accTime;
    @TableField("over_time")
    private Date overTime;
    @TableField("eval_degree")
    private Integer evalDegree;
    @TableField("eval_time")
    private Date evalTime;
    @TableField("eval_cont")
    private String evalCont;

}
