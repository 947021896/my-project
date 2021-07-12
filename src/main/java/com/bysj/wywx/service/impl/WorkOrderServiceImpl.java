package com.bysj.wywx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.wywx.bean.WorkOrder;
import com.bysj.wywx.mapper.WorkOrderMapper;
import com.bysj.wywx.service.RepairerService;
import com.bysj.wywx.service.WorkOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder>
        implements WorkOrderService {
    @Resource
    WorkOrderMapper workOrderMapper;
    @Resource
    RepairerService repairerService;
    public int getTodaySerialNumber(String nextId) {
        QueryWrapper<WorkOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("work_id",nextId+"0000");
        queryWrapper.orderByDesc("work_id");
        List<WorkOrder> list = workOrderMapper.selectList(queryWrapper);
        int cur = 0;
        if (list.size() != 0) {
            cur = Integer.parseInt(list.get(0).getOrderId().substring(9));
        }
        return cur;
    }

    @Override
    public void insertOrder(WorkOrder order) {
        Date date= new Date();
        String prefix = "w";
        String  nextId = prefix + new SimpleDateFormat("yyyyMMdd").format(date);
        int nextSerialNumber = getTodaySerialNumber(nextId) + 1;
        nextId += new DecimalFormat("0000").format(nextSerialNumber);
        order.setSendTime(date);
        order.setStateTag(2);
        order.setWorkId(nextId);
        order.setRepairerId(repairerService.getMostCounter().getRepairerId());
        workOrderMapper.insert(order);
    }

    @Override
    public WorkOrder selectByOrderId(String orderId) {
        QueryWrapper<WorkOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderId);
        return workOrderMapper.selectOne(queryWrapper);
    }

    @Override
    public int update(WorkOrder order) {
        if(selectByOrderId(order.getOrderId()) == null){
            insertOrder(order);
        }
        return workOrderMapper.updateById(order);
    }

    @Override
    public List<WorkOrder> getAllCountByRepairerId(String id) {
        QueryWrapper<WorkOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repairer_id",id);
        return workOrderMapper.selectList(queryWrapper);
    }

    @Override
    public int getSolvedByRepairer(String id) {
        QueryWrapper<WorkOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("repairer_id",id);
        queryWrapper.eq("state_tag",4);
        return workOrderMapper.selectCount(queryWrapper);
    }

    @Override
    public int getAllCount() {
        return workOrderMapper.selectCount(null);
    }

    @Override
    public int updateState(String WorkId, int state) {
        WorkOrder workOrder = workOrderMapper.selectById(WorkId);
        workOrder.setStateTag(state);
        return workOrderMapper.updateById(workOrder);
    }

    @Override
    public int OrderCountByState(int state) {
        return workOrderMapper.selectCount(
                new QueryWrapper<WorkOrder>().eq("state_tag",state)
        );
    }

}
