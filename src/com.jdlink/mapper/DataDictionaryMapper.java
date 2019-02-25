package com.jdlink.mapper;

import com.jdlink.domain.dataItem.DataDictionary;
import com.jdlink.domain.dataItem.DataDictionaryItem;

import java.util.List;

public interface DataDictionaryMapper {

    List<DataDictionaryItem> getDataDictionaryItemListByDataDictionaryId(Integer id);

    List<DataDictionary> listDataDictionary();
}
