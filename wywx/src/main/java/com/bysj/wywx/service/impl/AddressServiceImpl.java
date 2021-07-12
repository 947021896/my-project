package com.bysj.wywx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.wywx.bean.Address;
import com.bysj.wywx.mapper.AddressMapper;
import com.bysj.wywx.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
        implements AddressService {

    @Autowired
    AddressMapper addressMapper;
    @Override
    public List<Address> getAllAddress() {
        return addressMapper.selectByMap(null);
    }

    @Override
    public Address selectAddress(String id) {
        return addressMapper.selectById(id);
    }
}
