package com.jdlink.controller;

import com.jdlink.domain.InsuranceOrder;
import com.jdlink.domain.Page;
import com.jdlink.service.InsuranceOrderService;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class InsuranceOrderController {
@Autowired
    InsuranceOrderService insuranceOrderService;

   /*获取所有订单的信息*/
    @RequestMapping("orderList")
    public ModelAndView getAllInsuranceOrder(ModelAndView modelAndView,  Page page ){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("orderList");
        try{

                List<InsuranceOrder> insuranceOrderList=insuranceOrderService.listInsuranceOrder(page);
                mav.addObject("insuranceOrderList", insuranceOrderList);
            mav.addObject("total", insuranceOrderService.getTotalInsuranceOrder());
                mav.addObject("state","success");

        }
        catch (Exception e){
            mav.addObject("ex", "订单获取失败!");
            mav.addObject("state","error");

        }


//        System.out.println("访问路径走到这了！");

        return mav;
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
}
