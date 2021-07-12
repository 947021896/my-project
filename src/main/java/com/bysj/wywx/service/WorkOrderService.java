package com.bysj.wywx.service;

import com.bysj.wywx.bean.WorkOrder;

import java.util.List;

public interface WorkOrderService {
    public void insertOrder(WorkOrder order);// 生成工单
    public WorkOrder selectByOrderId(String orderId);
    public int update(WorkOrder order);// 修改工单信息
    public List<WorkOrder> getAllCountByRepairerId(String id);//根据维修员获取每个维修员所有处理的订单个数
    public int getSolvedByRepairer(String id); //根据维修员获取每个维修员已经处理的订单
    public int getAllCount();
    public int updateState(String workId,int state); //修改工单状态
    public int OrderCountByState(int state);
}
