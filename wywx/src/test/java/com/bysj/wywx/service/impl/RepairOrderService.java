package com.bysj.wywx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bysj.wywx.bean.RepairOrder;
import com.bysj.wywx.mapper.RepairOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RepairOrderService {
    @Resource
    RepairOrderMapper repairOrderMapper;

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
    @Test
    public void testSaveOrder() {
        Date date = new Date();
        String  nextId = new SimpleDateFormat("yyyyMMdd").format(date);
        int nextSerialNumber = getTodaySerialNumber(nextId) + 1;
        nextId += new DecimalFormat("0000").format(nextSerialNumber);

        String orderId = nextId;
        RepairOrder order =new RepairOrder(orderId,"hhh","lll","11212","bixiu" +
                "dodod","dddxiixix","sosoos","didid","did","dod",date,1);
        order.setOrderId(orderId);
        repairOrderMapper.insert(order);
        String next1 = new SimpleDateFormat("yyyyMMdd").format(date);
        nextSerialNumber = getTodaySerialNumber(next1) + 1;
        next1 += new DecimalFormat("0000").format(nextSerialNumber);
        System.out.println("orderId:"+next1);

    }
}
