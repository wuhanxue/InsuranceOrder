package com.jdlink.service.Impl;

import com.jdlink.domain.dataItem.DataDictionaryItem;
import com.jdlink.mapper.DataDictionaryMapper;
import com.jdlink.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    DataDictionaryMapper dataDictionaryMapper;

    @Override
    public List<DataDictionaryItem> getDataDictionaryItemListByDataDictionaryId(Integer id) { return dataDictionaryMapper.getDataDictionaryItemListByDataDictionaryId(id); }
}
