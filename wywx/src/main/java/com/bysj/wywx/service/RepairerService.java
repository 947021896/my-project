package com.bysj.wywx.service;

import com.bysj.wywx.bean.Repairer;

import java.util.List;

public interface RepairerService {
    public Repairer findRepairer(String id);
    public Repairer getMostCounter();
    public List<Repairer> getAll();
}
