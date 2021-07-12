package com.bysj.wywx.controller;

import com.bysj.wywx.bean.*;
import com.bysj.wywx.service.*;
import com.bysj.wywx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class AdministerController {
    @Resource
    private AdministerService administerService;
    @Resource
    private RepairOrderService repairOrderService;
    @Resource
    private RepairerService repairerService;
    @Resource
    private WorkOrderService workOrderService;
    @Resource
    private MaterialService materialService;

    @GetMapping("/admin/login")//{name}/{password}
    public Object ToLogin(@RequestParam String name,@RequestParam String password){
        Administer administer1 = administerService.findAdminister(name);
        if(administer1 == null){
            return Result.ServerError().appendInfo("用户名错误");
        }else {
            if(!administer1.getPassword().equals(password)){
                return Result.ServerError().appendInfo("密码错误");
            }else{
                return Result.OK();
            }
        }
    }

    @GetMapping("/admin/orders")
    public Object getOrder(){
        List<PackageOrder> list = repairOrderService.getAllOrder();
        int toDoCount = repairOrderService.OrderCount(0);
        int dealCount = repairOrderService.OrderCount(1);
        int solvedCount = repairOrderService.OrderCount(2);
        Map<String,Object> map = new HashMap<>();
        map.put("orders",list);
        map.put("pending",toDoCount);
        map.put("processing",dealCount);
        map.put("solved",solvedCount);
        return Result.success(map);
    }

    @GetMapping("/admin/updateOrder")
    public Object updateOrder(@RequestParam RepairOrder repairOrder){
        repairOrderService.updateOrder(repairOrder);
        return Result.OK();
    }
    @GetMapping("/admin/sendOrder")
    public Object sendOrder(@RequestParam String orderId){
        int count = repairOrderService.sendOrder(orderId);
        if(count != 0)
            return Result.OK().appendInfo("派单成功");
        return Result.ServerError().appendInfo("操作失败");
    }


    @GetMapping("/admin/getOneOrder")
    public Object getOneOrder(@RequestParam String  orderId) {
        PackageOrder object = repairOrderService.getOneOrder(orderId);
        return Result.success(object);
    }

    @GetMapping("/admin/getRepairers")
    public Object getRepairers(){
        List<Repairer> repairers = repairerService.getAll();
        Map<String,Object> map = new HashMap<>();
        int count = workOrderService.getAllCount();
        map.put("repairers",repairers);
        map.put("manCounts",repairers.size());
        map.put("OrderCount",count);
        return Result.success(map);
    }

    @GetMapping("/admin/materials")
    public Object getMaterials(){
        List<Material> list  = materialService.getAll();
        return Result.OK().appendInfo(list);
    }

    @PostMapping("/admin/material/add")//{id}{number}
    public Object addMaterial(@RequestParam String id,@RequestParam int number){
        int count = materialService.addNumber(id,number);
        if(count>0){
            log.info("物料数量添加成功");
            return Result.OK().appendInfo("物料数量添加成功");
        }
        return Result.ServerError().appendInfo("操作失败");
    }

    @PostMapping("/admin/material/reduce")//{id}{number}
    public Object reduceMaterial(@RequestParam String id,@RequestParam int number){
        int count = materialService.reduceNumber(id,number);
        if(count>0){
            log.info("物料数量减少成功");
            return Result.OK().appendInfo("减少成功");
        }
        return Result.ServerError().appendInfo("操作失败");
    }
}
