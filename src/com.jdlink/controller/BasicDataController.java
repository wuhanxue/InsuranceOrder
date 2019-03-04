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
    @RequestMapping("basicData")
    public ModelAndView listDataList() {
        ModelAndView mav = new ModelAndView("/basicData");
        return mav;
    }

    /**
     * 根据条件获取基础数据集合
     * @param dataDictionary
     * @return
     */
    @RequestMapping("searchBasicData")
    @ResponseBody
    public String listBasicDataList(@RequestBody DataDictionary dataDictionary) {
        JSONObject res = new JSONObject();
        try {
            List<DataDictionary> dataList = dataDictionaryService.searchBasicData(dataDictionary);
            res.put("data", dataList);
            res.put("status", "success");
            res.put("message", "获取数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "获取数据失败！");
        }
        return res.toString();
    }

    /**
     * 根据条件获取基础数据总数
     * @param dataDictionary
     * @return
     */
    @RequestMapping("searchBasicDataTotal")
    @ResponseBody
    public int searchBasicDataTotal(@RequestBody DataDictionary dataDictionary) {
        return dataDictionaryService.searchBasicDataTotal(dataDictionary);
    }

    /**
     * 跳转基础数据编辑页面
     * @return
     */
    @RequestMapping("basicDataDetail")
    public ModelAndView basicDataDetail() {
        ModelAndView mav = new ModelAndView("/basicDataDetail");
        return mav;
    }

    /**
     * 根据条件获取基础数据明细数据集合
     * @param dataDictionaryItem
     * @return
     */
    @RequestMapping("searchBasicDataDetail")
    @ResponseBody
    public String listBasicDataDetailList(@RequestBody DataDictionaryItem dataDictionaryItem) {
        JSONObject res = new JSONObject();
        try {
            List<DataDictionaryItem> dataList = dataDictionaryService.searchBasicDataDetail(dataDictionaryItem);
            res.put("data", dataList);
            res.put("status", "success");
            res.put("message", "数据获取成功！");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "数据获取失败！");
        }
        return res.toString();
    }

    /**
     * 根据条件获取基础数据明细总数
     * @param dataDictionary
     * @return
     */
    @RequestMapping("searchBasicDataDetailTotal")
    @ResponseBody
    public int searchBasicDataDetailTotal(@RequestBody DataDictionaryItem dataDictionaryItem) {
        return dataDictionaryService.searchBasicDataDetailTotal(dataDictionaryItem);
    }

    /**
     * 修改基础数据信息
     * @param dataDictionary
     * @param session
     * @return
     */
    @RequestMapping("modifyDictionaryData")
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

    /*获取状态数据字典*/
    @RequestMapping("getStateData")
    @ResponseBody
    public String getDataDictionaryById(){
        JSONObject res=new JSONObject();

        try {
            List<DataDictionaryItem> dataItemList = dataDictionaryService.getDataDictionaryItemListByDataDictionaryId(4);
            res.put("data", dataItemList);
            res.put("status", "success");
            res.put("message", "状态数据字典获取成功");
        }
        catch (Exception e){
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "状态数据字典获取失败");
        }
        return res.toString();
    }

    /*获取申请部门数据字典*/
    @RequestMapping("getDepartmentData")
    @ResponseBody
    public String getDepartmentData(){
        JSONObject res=new JSONObject();

        try {
            List<DataDictionaryItem> dataItemList = dataDictionaryService.getDataDictionaryItemListByDataDictionaryId(2);
            res.put("data", dataItemList);
            res.put("status", "success");
            res.put("message", "状态数据字典获取成功");
        }
        catch (Exception e){
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "状态数据字典获取失败");
        }
        return res.toString();
    }

    /**
     * 根据明细ID获取基础数据明细信息
     * @param id
     * @return
     */
    @RequestMapping("getDataDictionaryItemById")
    @ResponseBody
    public String getDataDictionaryItemById(String id) {
        JSONObject res = new JSONObject();
        try {
            DataDictionaryItem dataDictionaryItem = dataDictionaryService.getDataDictionaryItemById(id);
            JSONObject data = JSONObject.fromBean(dataDictionaryItem);
            res.put("status", "success");
            res.put("message", "获取成功");
            res.put("data", data);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "获取失败");
        }
        return res.toString();
    }


    /**
     * 根据明细ID获取基础数据信息
     * @param id
     * @return
     */
    @RequestMapping("getBasicDataById")
    @ResponseBody
    public String getBasicDataById(int id) {
        JSONObject res = new JSONObject();
        try {
            DataDictionary dataDictionary = dataDictionaryService.getDataDictionaryById(id);
            JSONObject data = JSONObject.fromBean(dataDictionary);
            res.put("status", "success");
            res.put("message", "获取成功");
            res.put("data", data);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "获取失败");
        }
        return res.toString();
    }

}
