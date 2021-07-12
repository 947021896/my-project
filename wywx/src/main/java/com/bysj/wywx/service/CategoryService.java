package com.bysj.wywx.service;

import com.bysj.wywx.bean.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategory();
    public Category selectById(String id);
}
