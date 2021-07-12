package com.bysj.wywx.controller;

import com.bysj.wywx.bean.PackageOrder;
import com.bysj.wywx.bean.RepairOrder;
import com.bysj.wywx.bean.Repairer;
import com.bysj.wywx.bean.WorkOrder;
import com.bysj.wywx.service.RepairOrderService;
import com.bysj.wywx.service.RepairerService;
import com.bysj.wywx.service.WorkOrderService;
import com.bysj.wywx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class RepairerController {
    @Resource
    RepairerService repairerService;
    @Resource
    WorkOrderService workOrderService;
    @Resource
    RepairOrderService repairOrderService;

    @GetMapping("/repairer/login")
    public Object ToLogin(@RequestParam String name,@RequestParam String password){
        Repairer repairer1 = repairerService.findRepairer(name);
        if(repairer1 == null){
            return Result.ServerError().appendInfo("该用户不存在");
        }else {
            if(!repairer1.getPassword().equals(password)){
                return Result.ServerError().appendInfo("用户名或密码错误");
            }else {
                return Result.OK();
            }
        }
    }

    @GetMapping("/repairer/getOrders")
    public Object GetOrders(@RequestParam String  repairerId){
        List<WorkOrder> workOrderList = workOrderService.getAllCountByRepairerId(repairerId);
        return Result.OK().appendInfo(workOrderList);
    }

}
