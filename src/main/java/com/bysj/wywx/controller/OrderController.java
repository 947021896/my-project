package com.bysj.wywx.controller;

import com.bysj.wywx.bean.Address;
import com.bysj.wywx.bean.Category;
import com.bysj.wywx.bean.PackageOrder;
import com.bysj.wywx.bean.RepairOrder;
import com.bysj.wywx.service.AddressService;
import com.bysj.wywx.service.CategoryService;
import com.bysj.wywx.service.RepairOrderService;
import com.bysj.wywx.service.WorkOrderService;
import com.bysj.wywx.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class OrderController {

    @Resource
    RepairOrderService repairOrderService;
    @Resource
    WorkOrderService workOrderService;
    @Resource
    CategoryService categoryService;
    @Resource
    AddressService addressService;


    @PostMapping("user/repair")
    public Object insertOrder(@RequestBody Map<String,String> map){
//        map.get("addressId");
        RepairOrder order = new RepairOrder();
        order.setUserId(map.get("userId"));
        order.setAddressContent(map.get("addressContent"));
        order.setContent(map.get("content"));
        order.setName(map.get("name"));
        order.setTel(map.get("tel"));
        order.setTitle(map.get("title"));
        order.setRepairCategoryId(map.get("repairCategoryId"));
        order.setAddressId(map.get("addressId"));
        System.out.println(map.get("content"));
        if(repairOrderService.saveOrder(order) == 0){
            log.info("维修单插入失败");
            return Result.ServerError().appendInfo("操作失败");
        }
        log.info("成功插入一个维修单");
        return Result.OK();
    }

    // 普通用户获取订单,state: 0-待审核 1-正在维修 2-已完成 3-所有的{userId}{state}
    @GetMapping("user/getOrder")
    public Object getAllOrder(@RequestParam String  userId, @RequestParam int state){
        List<PackageOrder> object = repairOrderService.getOrders(userId,state);
        return Result.OK().appendInfo(object);
    }
    // 普通用户获取某个订单{orderId}
    @GetMapping("user/getOneOrder")
    public Object getOneOrder(@RequestParam String  orderId){
        PackageOrder object = repairOrderService.getOneOrder(orderId);
        return Result.success(object);
    }

    // 获取校区和类别
    @GetMapping("/getAddAndCate")
    public Object getAddressAndCategory(){
        Map<String,Object> map = new HashMap<>();
        List<Address> list1 = addressService.getAllAddress();
        List<Category> list2 = categoryService.getAllCategory();
        map.put("list1",list1);
        map.put("list2",list2);
        List<String> list3 = new ArrayList<>();
        list3.add("请选择校区");
        for (Address address : list1) {
            list3.add(address.getName());
        }
        List<String> list4 = new ArrayList<>();
        list4.add("请选择");
        for (Category category : list2) {
            list4.add(category.getName());
        }
        map.put("list3",list3);
        map.put("list4",list4);
        return Result.success(map);
    }
}
