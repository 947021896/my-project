package com.bysj.wywx.service;

import com.bysj.wywx.bean.Material;

import java.util.List;

public interface MaterialService {

    public List<Material> getAll();
    public int addNumber(String id,int number);
    public int reduceNumber(String id, int number);
}
