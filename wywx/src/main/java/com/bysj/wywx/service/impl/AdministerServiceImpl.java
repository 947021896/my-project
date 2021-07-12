package com.bysj.wywx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.wywx.bean.Administer;
import com.bysj.wywx.mapper.AdministerMapper;
import com.bysj.wywx.service.AdministerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdministerServiceImpl extends ServiceImpl<AdministerMapper, Administer>
        implements AdministerService {
    @Resource
    AdministerMapper administerMapper;

    @Override
    public Administer findAdminister(String adID) {
        return administerMapper.selectById(adID);
    }
}
