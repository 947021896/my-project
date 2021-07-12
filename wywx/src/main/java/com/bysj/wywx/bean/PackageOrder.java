package com.bysj.wywx.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PackageOrder implements Serializable {

    private String workId;
    private String orderId;
    private String repairerId;
    private Integer stateTag;
    private String userId;
    private String name;
    private String tel;
    private String title;
    private String content;
    private String addressId;
    private String addressName;
    private String addressContent;
    private String repairCategoryId;
    private String repairCategoryName;
    private String pic;

    private Date time;
    private Date sendTime;
    private Date accTime;
    private Date overTime;
    private Integer evalDegree;
    private Date evalTime;
    private String text;
    public PackageOrder(RepairOrder order, WorkOrder workOrder){
        this.orderId = order.getOrderId();
        this.stateTag = order.getStateTag();
        this.name = order.getName();
        this.tel = order.getTel();
        this.title = order.getTitle();
        this.content = order.getContent();
        this.addressId = order.getAddressId();
        this.addressContent = order.getAddressContent();
        this.repairCategoryId = order.getRepairCategoryId();
        this.pic = order.getPic();
        this.time = order.getTime();
        if(workOrder!= null){
            this.sendTime = workOrder.getSendTime();
            this.accTime = workOrder.getAccTime();
            this.overTime = workOrder.getOverTime();
            this.evalDegree = workOrder.getEvalDegree();
            this.evalTime = workOrder.getEvalTime();
            this.text = workOrder.getEvalCont();
            if(order.getStateTag() == 1){
                this.stateTag = workOrder.getStateTag();
            }
        }
    }
}
