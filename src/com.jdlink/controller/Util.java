package com.jdlink.controller;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/*工具类*/
public class Util {
    /**
     * Created by matt on 2018/8/8.
     * DoubleClickTo 666
     * 时间转换工具
     */


    /**
     * 获取时间对象
     *
     * @param dateStr 时间格式字符串 yyyy/MM/dd
     * @return 时间对象
     */
    public Date getDateFromStr(String dateStr) {
        if (dateStr == null || dateStr.equals("null")) return null;
        dateStr = dateStr.trim();
        dateStr = dateStr.replace("\"", "");
        SimpleDateFormat dateFormat;
        Date date = null;
        try {
            if (dateStr.contains("/")) dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            else if (dateStr.contains("-")) dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            else if (dateStr.contains("年")) dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            else if (dateStr.contains(".")) dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            else throw new Exception("时间格式异常");
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取时间对象
     *
     * @param dataTimeStr 时间格式字符串 yyyy-MM-dd HH:mm:ss
     * @return 时间对象
     */
    public static Date getDateTimeFromStr(String dataTimeStr) {
        dataTimeStr = dataTimeStr.trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dataTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 通过时间对象获取时间字符串
     *
     * @param date 时间对象
     * @return 时间格式字符串 yyyy-MM-dd
     */
    public static String getDateStr(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time;
        try {
            time = simpleDateFormat.format(date);
        } catch (Exception e) {
            return "时间错误";
        }
        return time;
    }

    /**
     * 通过时间对象获取时间字符串
     *
     * @param date 时间对象
     * @return 时间格式字符串 yyyy-MM-dd HH:mm
     */
    public static String getTimeStr(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time;
        try {
            time = simpleDateFormat.format(date);
        } catch (Exception e) {
            return "时间错误";
        }
        return time;
    }

    /**
     * 通过时间对象获取时间字符串
     *
     * @param date 时间对象
     * @return 时间格式字符串 yyyy-MM-dd HH:mm:ss
     */
    public static String getTimeSecondStr(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time;
        try {
            time = simpleDateFormat.format(date);
        } catch (Exception e) {
            return "时间错误";
        }
        return time;
    }

    /**
     * 取得月份天数
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /*生成GUID 全局唯一标识符*/
    public static  String getGUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    //parm：请求的url链接  返回的是json字符串
    public static String getURLContent(String urlStr) {

        //请求的url
        URL url = null;

        //建立的http链接
        HttpURLConnection httpConn = null;

        //请求的输入流
        BufferedReader in = null;

        //输入流的缓冲
        StringBuffer sb = new StringBuffer();

        try{
            url = new URL(urlStr);

            in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8") );

            String str = null;

            //一行一行进行读入
            while((str = in.readLine()) != null) {
                sb.append( str );
            }
        } catch (Exception ex) {

        } finally{
            try{
                if(in!=null) {
                    in.close(); //关闭流
                }
            }catch(IOException ex) {

            }
        }
        String result =sb.toString();
        return result;
    }



    /*将请求的数据返回成JSONObject*/
    public static JSONObject getJSONObject(String urlStr){

        String result=getURLContent(urlStr);
        JSONObject jsonObject=JSONObject.fromObject(result);

        return jsonObject;

    }

    /*日期比较*/
    public static  Boolean dateCompare(Date date1,Date date2){
        return date1.after(date2);//如果前者比后者大返回true，否则为false
    }

}



