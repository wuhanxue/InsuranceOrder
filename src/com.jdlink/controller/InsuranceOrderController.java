package com.jdlink.controller;

import com.jdlink.domain.InsuranceOrder;
import com.jdlink.domain.Page;
import com.jdlink.service.InsuranceOrderService;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class InsuranceOrderController {
@Autowired
    InsuranceOrderService insuranceOrderService;


    @RequestMapping("/orderList")
    public String orderList(){

               return "orderList";
    }

    /*获取所有订单的信息*/
    @RequestMapping(value = "listInsuranceOrder",method = RequestMethod.POST)
    @ResponseBody
    public String listInsuranceOrder(@RequestBody Page page){
        JSONObject res=new JSONObject();
        try{

            List<InsuranceOrder> insuranceOrderList=insuranceOrderService.listInsuranceOrder(page);
            res.put("data",insuranceOrderList);
            res.put("status", "success");
            res.put("message", "订单信息查询成");


        }
        catch (Exception e){
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "查询失败");

        }
                return res.toString();
    }


    /*根据订单号获取明细*/
    @RequestMapping("/viewInsuranceOrder")
    public ModelAndView viewInsuranceOrder(ModelAndView modelAndView, @Param(value ="id")String id){

           try {
                modelAndView.clear();//清除之前的记录
                InsuranceOrder insuranceOrder=insuranceOrderService.getInsuranceOrderById(id);
               modelAndView.addObject("state","success");
               modelAndView.addObject("insuranceOrder",insuranceOrder);

           }
           catch (Exception e){
               modelAndView.addObject("ex", "订单获取失败!");
               modelAndView.addObject("state","error");
           }
        modelAndView.setViewName("orderDetail");
        return modelAndView;
    }


    @RequestMapping("getTotalInsuranceOrder")
    @ResponseBody
    public int getTotalInsuranceOrder(){
        return insuranceOrderService.getTotalInsuranceOrder();
    }


}
