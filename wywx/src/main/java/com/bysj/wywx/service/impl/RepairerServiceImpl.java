package com.bysj.wywx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.wywx.bean.Repairer;
import com.bysj.wywx.mapper.RepairerMapper;
import com.bysj.wywx.service.RepairerService;
import com.bysj.wywx.service.WorkOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RepairerServiceImpl extends ServiceImpl
        <RepairerMapper, Repairer> implements RepairerService {
    @Resource
    RepairerMapper repairerMapper;
    @Resource
    WorkOrderService workOrderService;

    @Override
    public Repairer findRepairer(String id) {
        return repairerMapper.selectById(id);
    }

    @Override
    public Repairer getMostCounter() {
        List<Repairer> Repairers = repairerMapper.selectList(null);
        int mostCount = 0;
        Repairer mostRepairer = new Repairer();
        for(Repairer repairer:Repairers){
            int count = workOrderService.getAllCountByRepairerId(repairer.getRepairerId()).size();
            if(count >= mostCount){
                mostCount = count;
                mostRepairer = repairer;
            }
        }
        return mostRepairer;
    }

    @Override
    public List<Repairer> getAll() {
        QueryWrapper<Repairer> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("repairer_id");
        return repairerMapper.selectList(queryWrapper);
    }
}
