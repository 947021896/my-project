package com.bysj.wywx.service;

import com.bysj.wywx.bean.PackageOrder;
import com.bysj.wywx.bean.RepairOrder;

import java.util.List;

public interface RepairOrderService {

    public PackageOrder getOneOrder(String orderId);//查看某个订单
    public int saveOrder(RepairOrder order);    //保存（新建）
    public void updateOrder(RepairOrder order);  //修改
    public int deleteOrder(String orderId);     //删除
    public int OrderCount(int state);           //未处理、处理中、已完成的维修单数

    public List<PackageOrder> getOrders(String userId,int state); //根据用户id获取订单
    public List<PackageOrder> getAllOrder();

    public int sendOrder(String orderId); // 审核派单

}
