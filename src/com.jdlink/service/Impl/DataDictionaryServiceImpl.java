package com.jdlink.service.Impl;

import com.jdlink.domain.dataItem.DataDictionary;
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
    public List<DataDictionaryItem> getDataDictionaryItemListByDataDictionaryId(Integer id) {
        return dataDictionaryMapper.getDataDictionaryItemListByDataDictionaryId(id);
    }

    @Override
    public List<DataDictionary> listDataDictionary() {
        return dataDictionaryMapper.listDataDictionary();
    }

    @Override
    public DataDictionary getDataDictionaryById(Integer id) {
        return dataDictionaryMapper.getDataDictionaryById(id);
    }

    @Override
    public void modifyDictionaryData(DataDictionary dataDictionary) {
        dataDictionaryMapper.updateDataDictionaryTypeAndNameById(dataDictionary);
        if (dataDictionary.getDataDictionaryItemList() != null && dataDictionary.getDataDictionaryItemList().size() > 0)
            for (int i = 0; i < dataDictionary.getDataDictionaryItemList().size(); i++) {
                DataDictionaryItem dataDictionaryItem = dataDictionary.getDataDictionaryItemList().get(i);
                if(dataDictionaryItem.getName().equals("_needDeleteDataDictionaryItem_")) {  // 需要删除的数据
                    dataDictionaryMapper.deleteDataDictionaryItemById(dataDictionaryItem);
                } else if(dataDictionaryItem.getId().equals("-1")){ // 需要新增的数据
                    dataDictionaryItem.setCreator(dataDictionary.getModifier());  // 设置创建者
                    dataDictionaryItem.setModifier(dataDictionary.getModifier());  // 设置修改者
                    String id = Integer.parseInt(dataDictionaryMapper.getMaxItemId()) + 1 + "";  // 获取当前最大的ID
                    dataDictionaryItem.setId(id);  // 设置ID
                    dataDictionaryMapper.addDataDictionaryItem(dataDictionaryItem);
                }else { // 需要修改的条目数据
                    dataDictionaryItem.setModifier(dataDictionary.getModifier());  // 设置修改者
                    dataDictionaryMapper.updateDataDictionaryItemTypeAndNameById(dataDictionaryItem);
                }
            }
    }
}
