package com.jdlink.service;

import com.jdlink.domain.dataItem.DataDictionaryItem;

import java.util.List;

public interface DataDictionaryService {

    /**
     * 根据外键获取数据字典数据列表
     * @param id
     * @return
     */
    List<DataDictionaryItem> getDataDictionaryItemListByDataDictionaryId(Integer id);
}
