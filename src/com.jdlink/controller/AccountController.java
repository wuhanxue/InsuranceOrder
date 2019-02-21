package com.jdlink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AccountController {

    /**
     * 跳转
     */
//    @RequestMapping("CheckUserInfo")
//    @ResponseBody
    @RequestMapping(value="/account")
    public ModelAndView account(ModelAndView modelAndView) {
        modelAndView.setViewName("accountManage");
        return modelAndView;
    }
}
