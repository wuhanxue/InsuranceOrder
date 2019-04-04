package com.jdlink.controller;

import com.jdlink.domain.*;
import com.jdlink.domain.dataItem.CurrencyDataItem;
import com.jdlink.domain.dataItem.DepartmentDataItem;
import com.jdlink.domain.dataItem.FreightDataItem;
import com.jdlink.domain.dataItem.Token;
import com.jdlink.service.DataService;
import com.jdlink.service.InsuranceOrderService;
import com.jdlink.service.UserService;
import com.sun.corba.se.spi.activation.Server;
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




import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.http.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.jdlink.controller.Util.*;
import static com.jdlink.controller.Util.getTimeSecondStr;
import static com.jdlink.domain.Connect.postMessage;

@Controller
public class InsuranceOrderController {
    @Autowired
    InsuranceOrderService insuranceOrderService;
    @Autowired
    UserService userService;
    @Autowired
    DataService dataService;


    @RequestMapping("orderList")
    public ModelAndView orderList(HttpSession session) {
        ModelAndView mav = new ModelAndView("orderList");
        User user = (User) session.getAttribute("user");   // 获取用户信息
        if (user != null) {  // 检测账号是否需要密码修改 2 强制修改，1 提示，0 不需要
            mav.addObject("modifyPasswordMark", userService.checkUserPasswordModifyTimeIsLate(user));  // 如果是则返回true
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
    public String getInsuranceOrderById(String id) {
        JSONObject res = new JSONObject();

        try {
            InsuranceOrder insuranceOrder = insuranceOrderService.getInsuranceOrderById(id);
            res.put("data", insuranceOrder);
            res.put("status", "success");
            res.put("message", "更新成功");
        } catch (Exception e) {
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
            List<InsuranceOrderItem> insuranceOrderItemList = insuranceOrderService.getInsuranceOrderItemById(id);
            res.put("status", "success");
            res.put("message", "查看保单信息成功");
            res.put("data", insuranceOrderItemList);
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
    public String uploadInsurancePolicy(MultipartFile multipartFile, HttpServletRequest request, String id) {
        JSONObject res = new JSONObject();

        try {
            if (multipartFile == null) {
                res.put("status", "nullFile");
                res.put("message", "文件为空!");
            } else {
                String path = "InsuranceOrderFiles/InsurancePolicy";
                String fileName = multipartFile.getOriginalFilename();
                File dir = new File(path, fileName);
                if (!dir.exists()) {
                    dir.createNewFile();
                }
                multipartFile.transferTo(dir);
                InsuranceOrderItem insuranceOrderItem = new InsuranceOrderItem();
                insuranceOrderItem.setId(id);
                insuranceOrderItem.setFileUrl(path + "/" + fileName);
                //文件路径更新
                insuranceOrderService.setInsurancePolicyFileUrl(insuranceOrderItem);
                insuranceOrderService.insured(id);
                res.put("status", "success");
                res.put("message", "文件上传成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "文件上传失败");

        }


        return res.toString();


    }

    /*接单*/
    @RequestMapping("receiptById")
    @ResponseBody
    public String receiptById(String id) {
        JSONObject res = new JSONObject();

        try {
            insuranceOrderService.receiptById(id);
            res.put("status", "success");
            res.put("message", "接单成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "接单失败");
        }

        return res.toString();
    }

    /*作废*/
    @RequestMapping("cancelById")
    @ResponseBody
    public String cancelById(String id) {
        JSONObject res = new JSONObject();

        try {
            insuranceOrderService.cancelById(id);
            res.put("status", "success");
            res.put("message", "订单作废成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "订单作废失败");
        }

        return res.toString();
    }


    /*关闭订单*/
    @RequestMapping("shutDownById")
    @ResponseBody
    public String shutDownById(String id) {
        JSONObject res = new JSONObject();

        try {
            insuranceOrderService.shutDownById(id);
            res.put("status", "success");
            res.put("message", "订单关闭成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "订单关闭失败");
        }

        return res.toString();
    }

    /*关闭保单*/
    @RequestMapping("shutInsuranceOrderItemDownById")
    @ResponseBody
    public String shutInsuranceOrderItemDownById(String id) {
        JSONObject res = new JSONObject();

        try {
            insuranceOrderService.shutInsuranceOrderItemDownById(id);
            res.put("status", "success");
            res.put("message", "保单关闭成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "保单关闭失败");
        }

        return res.toString();
    }

    /*PushDocStatus
     * 单据关闭结算接口
     * */
    @RequestMapping(value = "PushDocStatus", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String PushDocStatus(@Param(value = "id") String id) {
        JSONObject res = new JSONObject();

        try {
            //获取token
            JSONObject jsonObject = getJSONObject("https://edi.jd-link.cn/Api/GetToken?userName=admin&password=001");
            Token token1 = (Token) JSONObject.toBean(jsonObject, Token.class);
            String token = token1.getToken();//token
            BMC BMC = new BMC();//云平台的数据结构
            BMC.setMESSAGE_ID(getGUID());
            BMC.setSEND_DATE(getTimeSecondStr(new Date()));//发送时间

            List<Map<String, List<BMS>>> DATA = new ArrayList<>();
            Map<String, List<BMS>> map = new HashMap<>();
            List<BMS> list = new ArrayList<>();
            BMS bms = new BMS();
            //根据单号获取信息
//             Page page=null;
//             List<InsuranceOrder> insuranceOrderList=insuranceOrderService.listInsuranceOrder(page);
            InsuranceOrder insuranceOrder = insuranceOrderService.getInsuranceOrderById(id);
            List<InsuranceOrderItem> insuranceOrderItemList = insuranceOrder.getInsuranceOrderItemList();
            InsuranceOrderItem insuranceOrderItem=null;
            if(insuranceOrderItemList.size()>0){
                 insuranceOrderItem = getNewestDate(insuranceOrderItemList);
            }
           else {
                insuranceOrderItem=null;
            }

            //都是空的
            if (insuranceOrder == null && insuranceOrderItem == null) {
                bms.setDOC_NO("");
                bms.setDOC_STATUS("");
                bms.setDOC_DATE(getTimeSecondStr(new Date()));
            }

            //订单存在，保单不存在
            if (insuranceOrder != null && insuranceOrderItem == null) {

                bms.setDOC_NO(insuranceOrder.getId());
                bms.setDOC_STATUS("01");
                bms.setDOC_DATE(getTimeSecondStr(new Date()));
            }
            //保单存在，订单不存在
            if (insuranceOrder == null && insuranceOrderItem != null) {
                bms.setDOC_NO(insuranceOrderItem.getId());
                bms.setDOC_STATUS("02");
                bms.setDOC_DATE(getTimeSecondStr(new Date()));
            }
            //保单订单都存在
            if (insuranceOrder != null && insuranceOrderItem != null) {
                //外面关闭时间长
                if (insuranceOrder.getModifyTime().after(insuranceOrderItem.getModifyTime())) {
                    bms.setDOC_NO(insuranceOrder.getId());
                    bms.setDOC_STATUS("01");
                    bms.setDOC_DATE(getTimeSecondStr(new Date()));
                } else {
                    bms.setDOC_NO(insuranceOrderItem.getId());
                    bms.setDOC_STATUS("02");
                    bms.setDOC_DATE(getTimeSecondStr(new Date()));
                }
            }

            list.add(bms);
            map.put("DOC_STATUS", list);

            DATA.add(map);

            BMC.setDATA(DATA);
            res.put("TOKEN", token);
            res.put("BMC", BMC);
            String url = "https://edi.jd-link.cn/Api/PushDocStatus";
            postMessage(url, res.toString());
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "更新失败");
        }

        return res.toString();
    }

    /*订单反馈接口*/
    @RequestMapping(value = "PushOperationTracking", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String PushOperationTracking(HttpSession session, Page page, String id) throws IOException { //,登录人信息,订单号
        JSONObject res = new JSONObject();
        page = null;
        User user = (User) session.getAttribute("user");   // 获取用户信息
        //获取token
        JSONObject jsonObject = getJSONObject("https://edi.jd-link.cn/Api/GetToken?userName=admin&password=001");
        Token token1 = (Token) JSONObject.toBean(jsonObject, Token.class);

        String token = token1.getToken();//token

        Message message = new Message();//报文数据结构

        CDEC CDEC = new CDEC();//云平台的数据结构


        //发送者编码
        if (user != null) {
            CDEC.setSENDER(user.getCompanyDataItem().getCode());
        }


        message.setTokenId(token);
        CDEC.setMESSAGE_ID(getGUID());

        CDEC.setSEND_DATE(getTimeSecondStr(new Date()));//发送时间


        List<Map<String, List<TRACKING>>> DATA = new ArrayList<>();

        Map<String, List<TRACKING>> map = new HashMap<>();//存放数据的结构

        List<TRACKING> trackingList = new ArrayList<>();

        TRACKING tracking = new TRACKING();//具体的数据
        if (user != null) {
            //设置负责人MANAGER
            tracking.setManager(user.getName());

            //设置操作人OPERATOR
            tracking.setOperator(user.getName());
        }

        //找出修改时间最晚的订单,和保单
//            List<InsuranceOrder> insuranceOrderList=insuranceOrderService.listInsuranceOrder(page);

        InsuranceOrder insuranceOrder = insuranceOrderService.getInsuranceOrderById(id);

        List<InsuranceOrderItem> insuranceOrderItemList = insuranceOrder.getInsuranceOrderItemList();
        //存在保单
        if (insuranceOrderItemList.size() > 0) {
            InsuranceOrderItem insuranceOrderItem = getNewestDate(insuranceOrderItemList);
            //如果订单时间大于保单时间
            if (insuranceOrder.getModifyTime().after(insuranceOrderItem.getModifyTime())) {
                //设置订单号
                tracking.setOrderNo(insuranceOrder.getId());
                tracking.setOperationContent(insuranceOrder.getOrderStateDataItem().getName());
                //设置作业类型
                tracking.setNodeType(insuranceOrder.getOrderStateDataItem().getId());
                //设置保单号
                tracking.setEntrustOrderNo(insuranceOrderItem.getId());
                //设置实际完成时间
                tracking.setAtc(getTimeSecondStr(insuranceOrder.getModifyTime()));
            }
            //如果订单时间小于保单时间
            if (insuranceOrder.getModifyTime().before(insuranceOrderItem.getModifyTime())) {
                //设置订单号
                tracking.setOrderNo(insuranceOrder.getId());
                tracking.setOperationContent(insuranceOrderItem.getOrderStateDataItem().getName());
                //设置作业类型
                tracking.setNodeType(insuranceOrderItem.getOrderStateDataItem().getId());
                //设置保单号
                tracking.setEntrustOrderNo(insuranceOrderItem.getId());
                //设置实际完成时间
                tracking.setAtc(getTimeSecondStr(insuranceOrderItem.getModifyTime()));
            }
        }


        //如果保单不存在
        if (insuranceOrderItemList.size() == 0) {
            //设置订单号
            tracking.setOrderNo(insuranceOrder.getId());
            tracking.setOperationContent(insuranceOrder.getOrderStateDataItem().getName());
            //设置作业类型
            tracking.setNodeType(insuranceOrder.getOrderStateDataItem().getId());
            //设置保单号
            tracking.setEntrustOrderNo("");
            //设置实际完成时间
            tracking.setAtc(getTimeSecondStr(insuranceOrder.getModifyTime()));
        }


        //设置列ID
        tracking.setRowId(getGUID());


        trackingList.add(tracking);


        map.put("OPERATION_TRACKING", trackingList);
        DATA.add(map);
        CDEC.setDATA(DATA);
        message.setCDEC(CDEC);
        res.put("TOKEN", token);
        res.put("CDEC", CDEC);
        String url = "https://edi.jd-link.cn/Api/PushOperationTracking";
        postMessage(url, res.toString());
        return res.toString();
    }

    /*保单信息上传订单接口*/
    @RequestMapping(value = "PushInsuranceDetail", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String PushOperationTracking(String id, String state) {

        JSONObject res = new JSONObject();

        try {

            PushInsuranceDetail pushInsuranceDetail = new PushInsuranceDetail();
            pushInsuranceDetail.setMESSAGE_ID(getGUID());
            pushInsuranceDetail.setSEND_DATE(getTimeSecondStr(new Date()));
            List<Map<String, List<InsuranceDetail>>> DATA = new ArrayList<>();
            Map<String, List<InsuranceDetail>> map = new HashMap<>();
            List<InsuranceDetail> insuranceDetailList = new ArrayList<>();
            //根据单号查询保单信息
            InsuranceOrderItem insuranceOrderItem = insuranceOrderService.getInsuranceOrderItemByItemId(id);
            InsuranceDetail insuranceDetail = new InsuranceDetail();
            /*订单号*/
            insuranceDetail.setORDER_NO(insuranceOrderItem.getOrderId());
            /*保单号*/
            insuranceDetail.setPOLICY_NO(insuranceOrderItem.getId());
            /*保险公司*/
            insuranceDetail.setINSURANCE_COMPANY(insuranceOrderItem.getInsureCompanyName());
            /*保费*/
            insuranceDetail.setFEE(String.valueOf(insuranceOrderItem.getPremium()));
            /*投保日期*/
            insuranceDetail.setINSURANCE_DATE(getTimeSecondStr(insuranceOrderItem.getInsureDate()));

            if (state.equals("已投保")) {
                insuranceDetail.setDATASTATUS("1");
            }
            if (state.equals("异常")) {
                insuranceDetail.setDATASTATUS("2");
            }
            if (state.equals("删除")) {
                insuranceDetail.setDATASTATUS("0");
            }
            insuranceDetailList.add(insuranceDetail);
            map.put("INSURANCE_DT", insuranceDetailList);
            DATA.add(map);
            pushInsuranceDetail.setDATA(DATA);

            /*TOKEN*/
            //获取token
            JSONObject jsonObject = getJSONObject("https://edi.jd-link.cn/Api/GetToken?userName=admin&password=001");
            Token token1 = (Token) JSONObject.toBean(jsonObject, Token.class);

            String token = token1.getToken();//token
            res.put("TOKEN", token);
            res.put("OMS", pushInsuranceDetail);
            String url = "https://edi.jd-link.cn/Api/PushInsuranceDetail";
            postMessage(url, res.toString());

        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "更新失败"+e.toString());
        }

        return res.toString();

    }

    /*生成异常单*/
    @RequestMapping("getAbnormal")
    @ResponseBody
    public String getAbnormal(@RequestBody InsuranceOrderItem insuranceOrderItem) {
        JSONObject res = new JSONObject();
        try {
            insuranceOrderService.getAbnormal(insuranceOrderItem);
            res.put("status", "success");
            res.put("message", "异常单生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "异常单生成失败");
        }

        return res.toString();
    }

    /**
     * 文件下载功能
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("downloadFile")
    @ResponseBody
    public String down(HttpServletRequest request, HttpServletResponse response, @Param(value = "fileName") String fileName) throws Exception {
        JSONObject res = new JSONObject();

        try {

            fileName = new String(fileName.getBytes("iso8859-1"), "utf-8");
            String newFileName = java.net.URLEncoder.encode(getFileName(fileName), "UTF-8");
//            newFileName= new String(newFileName.getBytes("iso8859-1"), "utf-8");
            InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            response.addHeader("Content-Disposition", "attachment;filename=" + newFileName);
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            while ((len = bis.read()) != -1) {
                out.write(len);
                out.flush();
            }
            out.close();
            res.put("status", "success");
            res.put("message", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "更新失败");
        }


        return res.toString();
    }


    /*点击投保按钮,根据订单号查询货物价值信息*/
    @RequestMapping("getGoodsValueById")
    @ResponseBody
    public String getGoodsValueById(String id) {
        JSONObject res = new JSONObject();

        try {
            List<GoodsValue> goodsValueList = insuranceOrderService.getGoodsValueById(id);
            res.put("data", goodsValueList);
            res.put("status", "success");
            res.put("message", "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "更新失败");
        }

        return res.toString();

    }

    /*根据保单号查询保单信息*/
    @RequestMapping("getInsuranceOrderItemByItemId")
    @ResponseBody
    public String InsuranceOrderItem(String id) {
        JSONObject res = new JSONObject();

        try {
            InsuranceOrderItem insuranceOrderItem = insuranceOrderService.getInsuranceOrderItemByItemId(id);
            res.put("data", insuranceOrderItem);
            res.put("status", "success");
            res.put("message", "保单信息查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("status", "fail");
            res.put("message", "保单信息查询失败");
        }

        return res.toString();
    }

    /*找出保险单列表中修改时间最晚的那一个*/
    public static InsuranceOrderItem getNewestDate(List<InsuranceOrderItem> insuranceOrderItemList) {
        int i = 0;
        Date maxDate = insuranceOrderItemList.get(i).getModifyTime();//假设最晚的时间
        for (int j = 1; j < insuranceOrderItemList.size(); j++) {
            if (!maxDate.after(insuranceOrderItemList.get(j).getModifyTime())) {
                maxDate = insuranceOrderItemList.get(j).getModifyTime();
                i = j;
            }

        }
        System.out.println(i);

        return insuranceOrderItemList.get(i);
    }

    /*找出订单列表中修改时间最晚的那一个*/
    public static InsuranceOrder getNewestDateInsuranceOrder(List<InsuranceOrder> insuranceOrderList) {
        int i = 0;
        Date maxDate = insuranceOrderList.get(i).getModifyTime();//假设最晚的时间
        for (int j = 1; j < insuranceOrderList.size(); j++) {
            if (!maxDate.after(insuranceOrderList.get(j).getModifyTime())) {
                maxDate = insuranceOrderList.get(j).getModifyTime();
                i = j;
            }

        }
        System.out.println(i);

        return insuranceOrderList.get(i);
    }

    /*找出订单中状态是关闭的最晚的那个*/
    public InsuranceOrder getInsuranceOrderFromShutDown(List<InsuranceOrder> insuranceOrderList) {

        if (insuranceOrderList.size() > 0) {
            int index = insuranceOrderList.size() - 1;
            while (index >= 0 && (!insuranceOrderList.get(index).getOrderStateDataItem().getId().equals("5"))) {
                index--;
            }
            System.out.println("最晚订单关闭的位置:" + index);
            if (index >= 0) {
                return insuranceOrderList.get(index);
            } else {
                return null;
            }
        } else {
            return null;
        }


    }

    /*找出保单中状态是关闭的最晚的那个*/
    public InsuranceOrderItem getInsuranceOrderItemFromShutDown(List<InsuranceOrderItem> insuranceOrderItemList) {

        if (insuranceOrderItemList.size() > 0) {
            int index = insuranceOrderItemList.size() - 1;
            while (index >= 0 && (!insuranceOrderItemList.get(index).getOrderStateDataItem().getId().equals("5"))) {
                index--;
            }
            System.out.println("最晚订单关闭的位置:" + index);
            if (index >= 0) {
                return insuranceOrderItemList.get(index);
            } else {
                return null;
            }
        } else {
            return null;
        }


    }

    @RequestMapping(value = "ICSOrders", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String Test(ServletRequest request, ServletResponse response) throws IOException, ServletException, ParseException {
        JSONObject res = new JSONObject();
        StringBuffer str = new StringBuffer();
        try {
            BufferedReader reader=null;
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
//            BufferedInputStream in = new BufferedInputStream(request.getInputStream());
            int i;
            char c;
            while ((i=reader.read())!=-1) {
                c=(char)i;
                str.append(c);
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        String string = new String(str);
        if(string.length()>0) {

            System.out.println(string);
            /*保存报文到数据库*/
            Data data=new Data();
            data.setContent(string);
//            dataService.addData(data);

//            com.alibaba.fastjson.JSONObject jsStr = com.alibaba.fastjson.JSONObject.parseObject(string); //将字符串{“id”：1}
            com.alibaba.fastjson.JSONObject jsonObject = getJSONObject1(string);
            //1 取出TOKEN token一样才能进行读取;



            //取出ICS
            Object ICS= getJSONObject1(jsonObject.toString()).get("ICS");
            //取出其中的DATA
            Object DATA= getJSONObject1(ICS.toString()).get("DATA");
            //转成数组
            com.alibaba.fastjson.JSONArray jsonArray= com.alibaba.fastjson.JSONArray.parseArray(DATA.toString());
            //取出保险订单选项
            Object ORDER_ICS_SERVICEArray=getJSONObject1(jsonArray.get(0).toString()).get("ORDER_ICS_SERVICE");
            //转成数组
            com.alibaba.fastjson.JSONArray jsonArray1= com.alibaba.fastjson.JSONArray.parseArray(ORDER_ICS_SERVICEArray.toString());

            Object ORDER_ICS_SERVICE=getJSONObject1(jsonArray1.get(0).toString());
            com.alibaba.fastjson.JSONObject ORDER_ICS_SERVICEJson=(com.alibaba.fastjson.JSONObject) ORDER_ICS_SERVICE;
            //获取其中的一个属性
            //取出货物价值属性
            //在ORDER_INFO==》ORDER_CARGO_VALUE
            //1取出 在ORDER_INFO==>现在是数组
            Object ORDER_INFOArray=getJSONObject1(jsonArray.get(0).toString()).get("ORDER_INFO");
            //转成数组
            com.alibaba.fastjson.JSONArray jsonArray2= com.alibaba.fastjson.JSONArray.parseArray(ORDER_INFOArray.toString());

            Object ORDER_INFO=getJSONObject1(jsonArray2.get(0).toString());

            com.alibaba.fastjson.JSONObject ORDER_INFOJson=(com.alibaba.fastjson.JSONObject) ORDER_INFO;
//        System.out.println(ORDER_INFOJson);

            Object GoodValuesArray=getJSONObject1(jsonArray2.get(0).toString()).get("ORDER_CARGO_VALUE");
            //转成数组
            com.alibaba.fastjson.JSONArray jsonArray3= com.alibaba.fastjson.JSONArray.parseArray(GoodValuesArray.toString());
            Object GoodValues=getJSONObject1(jsonArray3.get(0).toString());

            com.alibaba.fastjson.JSONObject GoodValuesJson=(com.alibaba.fastjson.JSONObject) GoodValues;

            System.out.println(GoodValuesJson);




            InsuranceOrder insuranceOrder=new InsuranceOrder();
            insuranceOrder.setId(ORDER_ICS_SERVICEJson.get("ORDER_NO").toString());//订单号
            insuranceOrder.setProposer(ORDER_ICS_SERVICEJson.get("PROPOSER").toString());//申请人
            DepartmentDataItem departmentDataItem=new DepartmentDataItem();
            departmentDataItem.setId(ORDER_ICS_SERVICEJson.get("DEPARTMENT_ID").toString());
            insuranceOrder.setDepartmentDataItem(departmentDataItem);
            insuranceOrder.setInsuredPersonName(ORDER_ICS_SERVICEJson.get("INSURANT").toString());
            insuranceOrder.setGoodsName(ORDER_ICS_SERVICEJson.get("GOODS_NAME").toString());
            insuranceOrder.setGoodsType(Integer.parseInt(ORDER_ICS_SERVICEJson.get("CARGO_TYPE").toString()));
            insuranceOrder.setPackageNumber(Integer.parseInt(ORDER_ICS_SERVICEJson.get("PACKAGE_NUMBER").toString()));
            //包装重量

            insuranceOrder.setPackageWeight(Float.parseFloat(ORDER_ICS_SERVICEJson.get("PACKAGE_WEIGHT").toString()));
            insuranceOrder.setActualCarrier(Integer.parseInt(ORDER_ICS_SERVICEJson.get("ACTUAL_CARRIER").toString()));
            insuranceOrder.setBearfees((ORDER_ICS_SERVICEJson.get("BEAR_FEES").toString()));

            //国际运输类型
            FreightDataItem internationalFreightDataItem=new FreightDataItem();
            internationalFreightDataItem.setId(ORDER_ICS_SERVICEJson.get("INTERNATIONAL_TRANSPORT_TYPE").toString());
            insuranceOrder.setInternationalFreightDataItem(internationalFreightDataItem);
            //国内运输类型
            FreightDataItem domesticFreightDataItem=new FreightDataItem();
            domesticFreightDataItem.setId(ORDER_ICS_SERVICEJson.get("INLAND_TRANSPORT_TYPE").toString());
            insuranceOrder.setDomesticFreightDataItem(domesticFreightDataItem);
            insuranceOrder.setOriginalPlace(ORDER_ICS_SERVICEJson.get("FROM_CITY").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            insuranceOrder.setReceiveTime(sdf.parse(ORDER_ICS_SERVICEJson.get("PICK_DATETIME").toString()));
            insuranceOrder.setDestination(ORDER_ICS_SERVICEJson.get("TO_CITY").toString());
            insuranceOrder.setFeeCostType(Integer.parseInt(ORDER_ICS_SERVICEJson.get("FEE_COST_TYPE").toString()));
            //航名
            insuranceOrder.setFlightName(ORDER_ICS_SERVICEJson.get("SHIP_NAME").toString());
            //航次
            insuranceOrder.setFlightShift(ORDER_ICS_SERVICEJson.get("SAIL_NUM").toString());
            //航班号
            insuranceOrder.setFlightNumber(ORDER_ICS_SERVICEJson.get("FLIGHT_NUM").toString());
            //车次
            insuranceOrder.setTruckShift(ORDER_ICS_SERVICEJson.get("TRANS_NO").toString());
            //车牌号
            insuranceOrder.setLicensePlate(ORDER_ICS_SERVICEJson.get("CAR_NO").toString());
            //是否港澳
            if(Integer.parseInt(ORDER_ICS_SERVICEJson.get("Y_HT_GOODS").toString())==1){
                insuranceOrder.setyHTGoods(true);
            }
            if(Integer.parseInt(ORDER_ICS_SERVICEJson.get("Y_HT_GOODS").toString())==0){
                insuranceOrder.setyHTGoods(false);
            }
            insuranceOrder.setInsuranceOrderRequirement(Integer.parseInt(ORDER_ICS_SERVICEJson.get("POLICY_REQUIREMENTS").toString()));
            insuranceOrder.setFileInsurance(Integer.parseInt(ORDER_ICS_SERVICEJson.get("ADDITIONAL_INSURANCE_TYPE").toString()));
            insuranceOrder.setOtherInsurance(ORDER_ICS_SERVICEJson.get("ADDITIONAL_INSURANCE_NAME").toString());
            List<GoodsValue> goodsValueList=new ArrayList<>();
            System.out.println(insuranceOrder);


            JSONObject obj=JSONObject.fromString(string);
            JSONObject ICSObj=obj.getJSONObject("ICS");
            JSONArray DATAarray=ICSObj.getJSONArray("DATA");
            JSONObject dataObj=DATAarray.getJSONObject(0);
            JSONArray order_infoArray=dataObj.getJSONArray("ORDER_INFO");
            JSONObject ORDER_INFOObj=order_infoArray.getJSONObject(0);
            JSONArray ORDER_CARGO_VALUEArray=ORDER_INFOObj.getJSONArray("ORDER_CARGO_VALUE");
            for (int y=0;y<ORDER_CARGO_VALUEArray.length();y++){
                JSONObject ORDER_CARGO_VALUE=null;
                ORDER_CARGO_VALUE=ORDER_CARGO_VALUEArray.getJSONObject(y);
                GoodsValue goodsValue=new GoodsValue();
                CurrencyDataItem currencyDataItem=new CurrencyDataItem();
                currencyDataItem.setId(ORDER_CARGO_VALUE.getString("currency"));
                goodsValue.setCurrencyDataItem(currencyDataItem);
                goodsValue.setInsuranceOrderId(insuranceOrder.getId());
                goodsValue.setValue(Float.parseFloat(ORDER_CARGO_VALUE.getString("CARGO_VALUE")));
                goodsValueList.add(goodsValue);
                insuranceOrder.setGoodsValues(goodsValueList);
            }


//            for (int i=0;i<jsonArray3.size();i++){
//
//
//            }

            System.out.println(insuranceOrder);
            /*TOKEN*/
            //获取token
            JSONObject jsonObject2 = getJSONObject("https://edi.jd-link.cn/Api/GetToken?userName=admin&password=001");
            Token token1 = (Token) JSONObject.toBean(jsonObject2, Token.class);
            String token = token1.getToken();//token

            String token2 = jsonObject.getString("TOKEN");


            try {
                //如果单号不存在就添加
                if (insuranceOrderService.getInsuranceOrderById(insuranceOrder.getId()) == null) {
                    insuranceOrderService.addInsuranceOrder(insuranceOrder);
                } else {
                    //更新
                    //货物价值要删除 再添加
                    insuranceOrderService.deleteGoodValues(insuranceOrder.getId());
                    insuranceOrderService.updateInsuranceOrder(insuranceOrder);
                }

                JSONObject jsonDATA=new JSONObject();
                jsonDATA.put("TOKEN",token2);
                 JSONArray jsonArray4=new JSONArray();
                JSONObject jsonObject1 = new JSONObject();
                JSONObject lan1 = new JSONObject();
                lan1.put("MESSAGE_ID", insuranceOrder.getId());
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(currentTime);
                lan1.put("MESSAGE_TYPE", "json");
                lan1.put("DATE", dateString);
                lan1.put("REMARK", "其他备注信息");
                jsonObject1.put("RECEIVE_SYS", "OMS");
                jsonObject1.put("SEND_SYS", "ICS");
                jsonObject1.put("RESULTFLAG", "1");
                jsonObject1.put("FAILCODE", "");
                jsonObject1.put("FAILINFO", "");
                jsonObject1.put("RETDATA", lan1);
                jsonArray4.put(jsonObject1);
                jsonDATA.put("DATA",jsonArray4);
                return jsonDATA.toString();
            } catch (Exception e) {
                JSONObject jsonDATA=new JSONObject();
                jsonDATA.put("TOKEN",token2);
                JSONArray jsonArray4=new JSONArray();
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("RECEIVE_SYS", "OMS");
                jsonObject1.put("SEND_SYS", "ICS");
                jsonObject1.put("RESULTFLAG", "0");
                jsonObject1.put("FAILCODE", "");
                jsonObject1.put("FAILINFO", e.toString());
                JSONObject lan1 = new JSONObject();
                lan1.put("MESSAGE_ID", insuranceOrder.getId());
                Date currentTime = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(currentTime);
                lan1.put("MESSAGE_TYPE", "json");
                lan1.put("DATE", dateString);
                lan1.put("REMARK", "其他备注信息");
                jsonObject1.put("RETDATA", lan1);
                jsonArray4.put(jsonObject1);
                jsonDATA.put("DATA",jsonArray4);
                e.printStackTrace();
                return jsonDATA.toString();
            }

        }
        else
            res.put("fail","fail");
        return res.toString();
        }


        }
