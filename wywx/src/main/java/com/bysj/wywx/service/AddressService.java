package com.bysj.wywx.service;

import com.bysj.wywx.bean.Address;

import java.util.List;

public interface AddressService {
    public List<Address> getAllAddress();
    public Address selectAddress(String id);
}
