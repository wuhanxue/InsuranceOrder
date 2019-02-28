package com.jdlink.mapper;

import com.jdlink.domain.dataItem.DataDictionary;
import com.jdlink.domain.dataItem.DataDictionaryItem;

import java.util.List;

public interface DataDictionaryMapper {

    List<DataDictionaryItem> getDataDictionaryItemListByDataDictionaryId(Integer id);

    List<DataDictionary> listDataDictionary();

    DataDictionary getDataDictionaryById(Integer id);

    void updateDataDictionaryCodeAndNameById(DataDictionary dataDictionary);

    void deleteDataDictionaryItemById(DataDictionaryItem dataDictionaryItem);

    void addDataDictionaryItem(DataDictionaryItem dataDictionaryItem);

    void updateDataDictionaryItemCodeAndNameById(DataDictionaryItem dataDictionaryItem);

    String getMaxItemId();

    List<DataDictionaryItem> getDataDictionaryByParentId(String parentId);

    List<DataDictionary> searchBasicData(DataDictionary dataDictionary);

    int searchBasicDataTotal(DataDictionary dataDictionary);

    List<DataDictionaryItem> searchBasicDataDetail(DataDictionaryItem dataDictionaryItem);

    int searchBasicDataDetailTotal(DataDictionaryItem dataDictionaryItem);

    DataDictionaryItem getDataDictionaryItemById(String id);
}
