package com.jdlink.controller;

import com.jdlink.domain.*;
import com.jdlink.domain.dataItem.Token;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import static com.jdlink.controller.Util.*;

@Controller
public class InsuranceOrderController {
    @Autowired
    InsuranceOrderService insuranceOrderService;
    @Autowired
    UserService userService;


    @RequestMapping("orderList")
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
    @RequestMapping("viewInsuranceOrder")
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


    @RequestMapping("getInsuranceOrderById")
    @ResponseBody
    public String getInsuranceOrderById(String id){
        JSONObject res=new JSONObject();

        try {
            InsuranceOrder insuranceOrder=insuranceOrderService.getInsuranceOrderById(id);
            res.put("data", insuranceOrder);
            res.put("status", "success");
            res.put("message", "更新成功");
        }
        catch (Exception e){
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "更新失败");
        }

        return res.toString();

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
                insuranceOrderService.insured(id);
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

    /*接单*/
   @RequestMapping("receiptById")
    @ResponseBody
    public String receiptById(String id){
       JSONObject res=new JSONObject();

    try {
       insuranceOrderService.receiptById(id);
       res.put("status", "success");
        res.put("message", "接单成功");
    }
    catch (Exception e){
       e.printStackTrace();
       res.put("status", "fail");
       res.put("message", "接单失败");
   }

      return res.toString();
   }

   /*作废*/
   @RequestMapping("cancelById")
   @ResponseBody
   public String cancelById(String id){
       JSONObject res=new JSONObject();

       try {
           insuranceOrderService.cancelById(id);
           res.put("status", "success");
           res.put("message", "订单作废成功");
       }
       catch (Exception e){
           e.printStackTrace();
           res.put("status", "fail");
           res.put("message", "订单作废失败");
       }

       return res.toString();
   }


    /*关闭*/
    @RequestMapping("shutDownById")
    @ResponseBody
    public String shutDownById(String id){
        JSONObject res=new JSONObject();

        try {
            insuranceOrderService.shutDownById(id);
            res.put("status", "success");
            res.put("message", "订单关闭成功");
        }
        catch (Exception e){
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "订单关闭失败");
        }

        return res.toString();
    }




    /*订单反馈接口*/
    @RequestMapping("PushOperationTracking")
    @ResponseBody
    public String PushOperationTracking(String userName,String code,String id){ //,登录人信息,订单号
        JSONObject res=new JSONObject();

        //获取token
        JSONObject jsonObject=getJSONObject("https://edi.jd-link.cn/Api/GetToken?userName=admin&password=001");
        Token token1=(Token)JSONObject.toBean(jsonObject, Token.class);

        String token=token1.getToken();//token

        Message message=new Message();//报文数据结构

        CDEC CDEC=new CDEC();//云平台的数据结构


        message.setTokenId(token);
        //发送数据的用户CODE*


        //唯一编码
        CDEC.setMESSAGE_ID(getGUID());

        CDEC.setSEND_DATE(getTimeSecondStr(new Date()));//发送时间

        //发送者编码
        CDEC.setSENDER(code);

        List<Map<String,List<TRACKING>>> DATA=new ArrayList<>();

        Map<String,List<TRACKING>> map=new HashMap<>();//存放数据的结构

        List<TRACKING> trackingList=new ArrayList<>();

        TRACKING tracking=new TRACKING();//具体的数据


        InsuranceOrder insuranceOrder=insuranceOrderService.getInsuranceOrderById(id);

        //如果保单号不存在,就反馈订单信息
         if(insuranceOrder.getInsuranceOrderItem()==null){
//设置作业内容
             tracking.setOPERATIONCONTENT(insuranceOrder.getOrderStateDataItem().getName());
             //设置作业类型
             tracking.setNODETYPE(insuranceOrder.getOrderStateDataItem().getId());
             //设置保单号
             tracking.setENTRUSTORDERNO("");
         }
        //如果保单号存在,则比较修改时间，已最晚的那个为准
         else {
             if(dateCompare(insuranceOrder.getModifyTime(),insuranceOrder.getInsuranceOrderItem().getModifyTime())){ //订单修改时间大于保单修改时间
                 tracking.setOPERATIONCONTENT(insuranceOrder.getOrderStateDataItem().getName());
                 //设置作业类型
                 tracking.setNODETYPE(insuranceOrder.getOrderStateDataItem().getId());
                 //设置保单号
                 tracking.setENTRUSTORDERNO(insuranceOrder.getInsuranceOrderItem().getId());
             }
             else {
                 tracking.setOPERATIONCONTENT(insuranceOrder.getInsuranceOrderItem().getOrderStateDataItem().getName());
                 //设置作业类型
                 tracking.setNODETYPE(insuranceOrder.getInsuranceOrderItem().getOrderStateDataItem().getId());
                 //设置保单号
                 tracking.setENTRUSTORDERNO(insuranceOrder.getInsuranceOrderItem().getId());
             }


         }

        //设置列ID
        tracking.setROWID(getGUID());
        //设置订单号
        tracking.setORDERNO(insuranceOrder.getId());

        //设置实际完成时间
        tracking.setATC(getTimeSecondStr(new Date()));

        //设置负责人MANAGER
        tracking.setMANAGER(userName);

        //设置操作人OPERATOR
        tracking.setOPERATOR(userName);
        trackingList.add(tracking);


        map.put("OPERATION_TRACKING",trackingList);
        DATA.add(map);
        CDEC.setDATA(DATA);
        message.setCDEC(CDEC);
        res.put("Token",token);
        res.put("CDEC",CDEC);
        return res.toString();
    }

    /*生成异常单*/
    @RequestMapping("getAbnormal")
    @ResponseBody
    public String getAbnormal(@RequestBody InsuranceOrderItem insuranceOrderItem){
        JSONObject res=new JSONObject();
        try {
            insuranceOrderService.getAbnormal(insuranceOrderItem);
            res.put("status", "success");
            res.put("message", "异常单生成成功");
        }
        catch (Exception e){
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "异常单生成失败");
        }

        return res.toString();
    }

    /**
     * 文件下载功能
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping( value = "downloadFile")
    @ResponseBody
    public String down(HttpServletRequest request,HttpServletResponse response,String fileName) throws Exception{
        JSONObject res=new JSONObject();

        try {
            //获取输入流
            String path = request.getSession().getServletContext().getRealPath("statics\\upload")+"\\"+fileName;
            InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            //转码，免得文件名中文乱码
            fileName = URLEncoder.encode(fileName,"UTF-8");
            //设置文件下载头
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            while((len = bis.read()) != -1){
                out.write(len);
                out.flush();
            }
            out.close();
            res.put("status", "success");
            res.put("message", "更新成功");
        }
        catch (Exception e){
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "更新失败");
        }

        return res.toString();


    }

    }
