package com.jdlink.mapper;

import com.jdlink.domain.dataItem.DataDictionary;
import com.jdlink.domain.dataItem.DataDictionaryItem;

import java.util.List;

public interface DataDictionaryMapper {

    List<DataDictionaryItem> getDataDictionaryItemListByDataDictionaryId(Integer id);

    List<DataDictionary> listDataDictionary();

    DataDictionary getDataDictionaryById(Integer id);

    void updateDataDictionaryTypeAndNameById(DataDictionary dataDictionary);

    void deleteDataDictionaryItemById(DataDictionaryItem dataDictionaryItem);

    void addDataDictionaryItem(DataDictionaryItem dataDictionaryItem);

    void updateDataDictionaryItemTypeAndNameById(DataDictionaryItem dataDictionaryItem);

    String getMaxItemId();
}
