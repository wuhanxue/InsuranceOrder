package com.jdlink.domain;

import static com.jdlink.controller.Util.getURLContent;

public class Certest {

    public static  void  main(String args[]){
        System.out.println(getURLContent("https://edi.jd-link.cn/Api/GetToken?userName=admin&password=001"));
    }
}
