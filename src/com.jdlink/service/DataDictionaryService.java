package com.jdlink.service;

import com.jdlink.domain.dataItem.DataDictionary;
import com.jdlink.domain.dataItem.DataDictionaryItem;

import java.util.List;

public interface DataDictionaryService {

    /**
     * 根据外键获取数据字典数据列表
     * @param id
     * @return
     */
    List<DataDictionaryItem> getDataDictionaryItemListByDataDictionaryId(Integer id);

    List<DataDictionary> listDataDictionary();

    DataDictionary getDataDictionaryById(Integer id);

    /**
     * 修改数据信息（包括详细条目的修改，新增，删除）
     * @param dataDictionary
     */
    void modifyDictionaryData(DataDictionary dataDictionary);

    List<DataDictionaryItem> getDataDictionaryByParentId(String parentId);
}
