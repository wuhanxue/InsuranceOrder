package com.jdlink.controller;

import com.jdlink.domain.User;
import com.jdlink.domain.dataItem.DataDictionary;
import com.jdlink.domain.dataItem.DataDictionaryItem;
import com.jdlink.service.DataDictionaryService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BasicDataController {
    @Autowired
    DataDictionaryService dataDictionaryService;

    /**
     * 获取基础数据页面数据
     *
     * @return
     */
    @RequestMapping("/basicData")
    public ModelAndView listDataList() {
        ModelAndView mav = new ModelAndView();
        try {
            List<DataDictionary> dataList = dataDictionaryService.listDataDictionary();  // 获取所有用户
            mav.addObject("status", "success");
            mav.addObject("message", "获取成功！");
            mav.addObject("dataList", dataList);
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("status", "fail");
            mav.addObject("message", "获取失败！");
        }
        mav.setViewName("/basicData");
        return mav;
    }

    /**
     * 根据ID获取字典明细数据列表
     *
     * @param user
     * @return
     */
    @RequestMapping("/getBasicDataById/{id}")
    public ModelAndView getBasicDataById(DataDictionary dataDictionary) {
        List<DataDictionaryItem> dataItemList = dataDictionaryService.getDataDictionaryItemListByDataDictionaryId(dataDictionary.getId());
        DataDictionary dataDictionary1 = dataDictionaryService.getDataDictionaryById(dataDictionary.getId());
        ModelAndView mav = new ModelAndView("/basicDataDetail");
        mav.addObject("dataItemList", dataItemList);
        mav.addObject("data", dataDictionary1);
        return mav;
    }

    /**
     * 修改基础数据信息
     * @param dataDictionary
     * @param session
     * @return
     */
    @RequestMapping("/modifyDictionaryData")
    @ResponseBody
    public String modifyDictionaryData(@RequestBody DataDictionary dataDictionary, HttpSession session) {
        JSONObject res = new JSONObject();
        try {
            User user = (User) session.getAttribute("user");   // 获取登陆用户信息
            if (user != null){
                dataDictionary.setModifier(user.getName()); // 设置创建者
            }else {
                dataDictionary.setModifier("未登录"); // 设置创建者
            }
            dataDictionaryService.modifyDictionaryData(dataDictionary);
            res.put("status", "success");
            res.put("message", "修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "修改失败！");
        }
        return res.toString();
    }

}
