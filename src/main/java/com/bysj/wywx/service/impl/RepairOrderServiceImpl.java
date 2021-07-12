package com.bysj.wywx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.wywx.bean.PackageOrder;
import com.bysj.wywx.bean.RepairOrder;
import com.bysj.wywx.bean.WorkOrder;
import com.bysj.wywx.mapper.CategoryMapper;
import com.bysj.wywx.mapper.RepairOrderMapper;
import com.bysj.wywx.mapper.WorkOrderMapper;
import com.bysj.wywx.service.AddressService;
import com.bysj.wywx.service.CategoryService;
import com.bysj.wywx.service.RepairOrderService;
import com.bysj.wywx.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RepairOrderServiceImpl extends ServiceImpl<RepairOrderMapper, RepairOrder>
        implements RepairOrderService {

    @Resource
    private RepairOrderMapper repairOrderMapper;
    @Resource
    private WorkOrderMapper workOrderMapper;
    @Resource
    private WorkOrderService workOrderService;
    @Resource
    private AddressService addressService;
    @Resource
    CategoryService categoryService;
    //查看订单详情
    @Override
    public PackageOrder getOneOrder(String orderId) {
        RepairOrder repairOrder =  repairOrderMapper.selectById(orderId);
        if(repairOrder.getStateTag() == 0){
            PackageOrder packageOrder = new PackageOrder(repairOrder,null);
            packageOrder.setAddressName(addressService.selectAddress(packageOrder.getAddressId())
            .getName());
            packageOrder.setRepairCategoryName(categoryService.selectById(packageOrder.getRepairCategoryId())
            .getName());
            return packageOrder;
        }else{
            WorkOrder workOrder = workOrderService.selectByOrderId(orderId);
            PackageOrder packageOrder = new PackageOrder(repairOrder,workOrder);
            packageOrder.setAddressName(addressService.selectAddress(packageOrder.getAddressId())
                    .getName());
            packageOrder.setRepairCategoryName(categoryService.selectById(packageOrder.getRepairCategoryId())
                    .getName());
            return packageOrder;
        }

    }

    public int getTodaySerialNumber(String nextId) {
        QueryWrapper<RepairOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("order_id",nextId+"0000");
        queryWrapper.orderByDesc("order_id");
        List<RepairOrder> list = repairOrderMapper.selectList(queryWrapper);
        int cur = 0;
        if (list.size() != 0) {
            cur = Integer.parseInt(list.get(0).getOrderId().substring(8));
        }
        return cur;
    }
    @Override
    public int saveOrder(RepairOrder order) {
        Date date = new Date();
        String  nextId = new SimpleDateFormat("yyyyMMdd").format(date);
        int nextSerialNumber = getTodaySerialNumber(nextId) + 1;
        nextId += new DecimalFormat("0000").format(nextSerialNumber);
        order.setOrderId(nextId);
        order.setTime(date);
        order.setStateTag(0);
        return repairOrderMapper.insert(order);
    }

    //修改订单状态
    @Override
    public void updateOrder(RepairOrder order) {
        RepairOrder repairOrder =  repairOrderMapper.selectById(order.getOrderId());
        if(repairOrder.getStateTag() == 0){
            repairOrderMapper.updateById(order);
        }else{
            WorkOrder workOrder = workOrderService.selectByOrderId(order.getOrderId());
            workOrderMapper.updateById(workOrder);
        }
    }


    @Override
    public int deleteOrder(String orderId) {
        return 0;
    }

    @Override // state == 0:未处理 1：处理中 2：已完成
    public int OrderCount(int state) {
        if(state==0){
            return OrderCountByState(0);
        }else if(state == 1){
            int count1 = workOrderService.OrderCountByState(2);
            int count2 = workOrderService.OrderCountByState(3);
            return count1+count2;
        }else {
            int count1 =workOrderService. OrderCountByState(4);;
            int count2 =workOrderService.OrderCountByState(5);;
            return count1+count2;
        }
    }
    private int OrderCountByState(int state){
        QueryWrapper<RepairOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state_tag",state);
        return repairOrderMapper.selectCount(queryWrapper);
    }

    @Override
    public List<PackageOrder> getOrders(String userId,int state) {
        QueryWrapper<RepairOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.orderByDesc("time");
        List<RepairOrder> list =  repairOrderMapper.selectList(queryWrapper);//所有订单
        List<PackageOrder> list3 = new ArrayList<>();
        for (RepairOrder repairOrder : list){
            PackageOrder packageOrder = new PackageOrder(repairOrder,null);
            packageOrder.setAddressName(addressService.selectAddress(
                    repairOrder.getAddressId()).getName());
            packageOrder.setRepairCategoryName(categoryService.selectById(
                    repairOrder.getRepairCategoryId()).getName());
            list3.add(packageOrder);
        }

        List<PackageOrder> list0 = new ArrayList<>();
        List<PackageOrder> list1 = new ArrayList<>();
        List<PackageOrder> list2 = new ArrayList<>();
        if( state == 0){ //获取未审核的
            for (RepairOrder repairOrder : list) {
                PackageOrder packageOrder = new PackageOrder(repairOrder,null);
                packageOrder.setAddressName(addressService.selectAddress(
                        repairOrder.getAddressId()).getName());
                packageOrder.setRepairCategoryName(categoryService.selectById(
                        repairOrder.getRepairCategoryId()).getName());
                if (repairOrder.getStateTag() == 0) {
                    list0.add(packageOrder);
                }
            }
            return list0;
        }else {
            for (RepairOrder repairOrder : list) {
                if (repairOrder.getStateTag() == 1) {
                    WorkOrder workOrder = workOrderService.selectByOrderId(repairOrder.getOrderId());
                    PackageOrder packageOrder = new PackageOrder(repairOrder,workOrder);
                    packageOrder.setAddressName(addressService.selectAddress(
                            repairOrder.getAddressId()).getName());
                    packageOrder.setRepairCategoryName(categoryService.selectById(
                            repairOrder.getRepairCategoryId()).getName());
                    if(workOrder.getStateTag() ==2 || workOrder.getStateTag() == 3) {
                        list1.add(packageOrder);
                    }
                    else if(workOrder.getStateTag() ==4 || workOrder.getStateTag() == 5){
                        list2.add(packageOrder);
                    }
                }
            }
            if(state == 1) //正在维修中
                return list1;
            if(state == 2)  //已完成
                return list2;
        }
        return list3; //获取所有的订单 state=3
    }

    @Override
    public List<PackageOrder> getAllOrder() {
        List<PackageOrder> packageOrderList = new ArrayList<>();
        List<RepairOrder> repairOrderList = repairOrderMapper.selectList(
                new QueryWrapper<RepairOrder> ().orderByDesc("time")
        );
        for(RepairOrder repairOrder : repairOrderList){
            if(repairOrder.getStateTag()== 0) {
                PackageOrder packageOrder = new PackageOrder(repairOrder,null);
                packageOrder.setAddressName(addressService.selectAddress(
                        repairOrder.getAddressId()).getName());
                packageOrder.setRepairCategoryName(categoryService.selectById(
                        repairOrder.getRepairCategoryId()).getName());
                packageOrderList.add(packageOrder);
            }else if(repairOrder.getStateTag() == 1){
                WorkOrder  workOrder = workOrderService.selectByOrderId(repairOrder.getOrderId());
                PackageOrder packageOrder = new PackageOrder(repairOrder,workOrder);
                packageOrder.setAddressName(addressService.selectAddress(
                        repairOrder.getAddressId()).getName());
                packageOrder.setRepairCategoryName(categoryService.selectById(
                        repairOrder.getRepairCategoryId()).getName());
                packageOrderList.add(packageOrder);
            }
        }
        return packageOrderList;
    }

    @Override
    public int sendOrder(String orderId) {
        //更新repairOrder状态
        RepairOrder repairOrder = repairOrderMapper.selectById(orderId);
        repairOrder.setStateTag(1);
        int count = repairOrderMapper.updateById(repairOrder);
        //更新work状态
        if(workOrderService.selectByOrderId(orderId) == null){
            Date date = new Date();
            WorkOrder workOrder = new WorkOrder();
            workOrder.setRepairerId(orderId);
            workOrderService.insertOrder(workOrder);
        }
        return 0;
    }


}
