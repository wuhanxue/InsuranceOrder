package com.jdlink.service.Impl;

import com.jdlink.domain.Data;
import com.jdlink.mapper.DataMapper;
import com.jdlink.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataMapper dataMapper;
    @Override
    public void addData(Data data) {
        dataMapper.addData(data);
    }
}
