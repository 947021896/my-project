package com.bysj.wywx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bysj.wywx.bean.Material;
import com.bysj.wywx.mapper.MaterialMapper;
import com.bysj.wywx.service.MaterialService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material>
        implements MaterialService {
    @Resource
    MaterialMapper materialMapper;

    @Override
    public List<Material> getAll() {
        return materialMapper.selectList(null);
    }

    @Override
    public int addNumber(String id, int number) {
        Material material = materialMapper.selectById(id);
        material.setNumber(material.getNumber()+ number);
        return materialMapper.updateById(material);
    }

    @Override
    public int reduceNumber(String id, int number) {
        Material material = materialMapper.selectById(id);
        material.setNumber(material.getNumber()- number);
        return materialMapper.updateById(material);
    }
}
