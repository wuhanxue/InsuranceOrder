package com.jdlink.controller;

import com.jdlink.domain.InsuranceOrder;
import com.jdlink.domain.InsuranceOrderItem;
import com.jdlink.domain.Page;
import com.jdlink.domain.User;
import com.jdlink.service.InsuranceOrderService;
import com.jdlink.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
public class InsuranceOrderController {
    @Autowired
    InsuranceOrderService insuranceOrderService;
    @Autowired
    UserService userService;


    @RequestMapping("/orderList")
    public ModelAndView orderList(HttpSession session) {
        ModelAndView mav = new ModelAndView("orderList");
        User user = (User) session.getAttribute("user");   // 获取用户信息
        if (user != null)
            if (userService.checkUserPasswordModifyTimeIsLate(user)) { // 检查登陆用户的账号密码是否90天未修改
                mav.addObject("modifyPasswordMark", "yes");  // 如果是则返回true
            }
        return mav;
    }

    /*获取所有订单的信息*/
    @RequestMapping(value = "listInsuranceOrder", method = RequestMethod.POST)
    @ResponseBody
    public String listInsuranceOrder(@RequestBody Page page) {
        JSONObject res = new JSONObject();
        try {

            List<InsuranceOrder> insuranceOrderList = insuranceOrderService.listInsuranceOrder(page);
            res.put("data", insuranceOrderList);
            res.put("status", "success");
            res.put("message", "订单信息查询成");


        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "查询失败");

        }
        return res.toString();
    }


    /*根据订单号获取明细*/
    @RequestMapping("/viewInsuranceOrder")
    public ModelAndView viewInsuranceOrder(ModelAndView modelAndView, @Param(value = "id") String id) {

        try {
            modelAndView.clear();//清除之前的记录
            InsuranceOrder insuranceOrder = insuranceOrderService.getInsuranceOrderById(id);
            modelAndView.addObject("state", "success");
            modelAndView.addObject("insuranceOrder", insuranceOrder);

        } catch (Exception e) {
            modelAndView.addObject("ex", "订单获取失败!");
            modelAndView.addObject("state", "error");
        }
        modelAndView.setViewName("orderDetail");
        return modelAndView;
    }


    /*获取订单总数*/
    @RequestMapping("getTotalInsuranceOrder")
    @ResponseBody
    public int getTotalInsuranceOrder() {
        return insuranceOrderService.getTotalInsuranceOrder();
    }


    /*订单查询*/
    @RequestMapping("searchInsuranceOrder")
    @ResponseBody
    public String searchInsuranceOrder(@RequestBody InsuranceOrder insuranceOrder) {
        JSONObject res = new JSONObject();

        try {
            List<InsuranceOrder> insuranceOrderList = insuranceOrderService.searchInsuranceOrder(insuranceOrder);
            res.put("status", "success");
            res.put("message", "订单高级查询成功");
            res.put("data", insuranceOrderList);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "订单高级查询失败");

        }

        return res.toString();
    }


    @RequestMapping("searchInsuranceOrderTotal")
    @ResponseBody
    public int searchInsuranceOrderTotal(@RequestBody InsuranceOrder insuranceOrder) {
        return insuranceOrderService.searchInsuranceOrderTotal(insuranceOrder);
    }


    /*根据订单信息查询信息*/
    @RequestMapping("getInsuranceOrderItemById")
    @ResponseBody
    public String getInsuranceOrderItemById(String id) {
        JSONObject res = new JSONObject();

        try {
            InsuranceOrderItem insuranceOrderItem = insuranceOrderService.getInsuranceOrderItemById(id);
            res.put("status", "success");
            res.put("message", "查看保单信息成功");
            res.put("data", insuranceOrderItem);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "查看保单信息失败");

        }

        return res.toString();
    }

    /*修改保单(明细)信息*/
    @RequestMapping("updateInsuranceOrderItem")
    @ResponseBody
    public String updateInsuranceOrderItemById(@RequestBody InsuranceOrderItem insuranceOrderItem) {
        JSONObject res = new JSONObject();

        try {
            insuranceOrderService.updateInsuranceOrderItem(insuranceOrderItem);
            res.put("status", "success");
            res.put("message", "保单修改成功");

        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "保单修改失败");
        }

        return res.toString();
    }


    /*删除保单*/
    @RequestMapping("deleteInsuranceOrderItemById")
    @ResponseBody
    public String deleteInsuranceOrderItemById(String id) {
        JSONObject res = new JSONObject();

        try {
            insuranceOrderService.deleteInsuranceOrderItemById(id);
            res.put("status", "success");
            res.put("message", "删除保单信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "删除保单信息失败");

        }


        return res.toString();
    }

    /*添加保单*/
    @RequestMapping("addInsuranceOrderItem")
    @ResponseBody
    public String addInsuranceOrderItem(@RequestBody InsuranceOrderItem insuranceOrderItem) {
        JSONObject res = new JSONObject();

        try {
            insuranceOrderService.addInsuranceOrderItem(insuranceOrderItem);
            res.put("status", "success");
            res.put("message", "添加保单成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "添加保单失败");
        }


        return res.toString();


    }

    /*上传电子保单*/
    @RequestMapping("uploadInsurancePolicy")
    @ResponseBody
    public String uploadInsurancePolicy(MultipartFile multipartFile, HttpServletRequest request,String id){
        JSONObject res=new JSONObject();

        try {
            if(multipartFile==null){
                res.put("status", "nullFile");
                res.put("message", "文件为空!");
            }
            else {
                String path = "InsuranceOrderFiles/InsurancePolicy";
                String fileName = multipartFile.getOriginalFilename();
                File dir = new File(path,fileName);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                multipartFile.transferTo(dir);
                InsuranceOrderItem insuranceOrderItem=new InsuranceOrderItem();
                insuranceOrderItem.setId(id);
                insuranceOrderItem.setFileUrl(path+"/"+fileName);
                //文件路径更新
                insuranceOrderService.setInsurancePolicyFileUrl(insuranceOrderItem);
                res.put("status", "success");
                res.put("message", "文件上传成功");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "文件上传失败");

        }


        return res.toString();


    }
}
