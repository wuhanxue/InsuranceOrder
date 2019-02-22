package com.jdlink.service;

import com.jdlink.domain.dataItem.DataDictionaryItem;

import java.util.List;

public interface DataDictionaryService {

    List<DataDictionaryItem> getDataDictionaryItemListByDataDictionaryId(Integer id);
}
