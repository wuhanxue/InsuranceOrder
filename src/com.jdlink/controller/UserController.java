package com.jdlink.controller;

import com.jdlink.domain.User;
import com.jdlink.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 验证登录账号和密码
     * @param user
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value="/CheckUserInfo",method=RequestMethod.POST)
    public ModelAndView CheckUserInfo(User user, HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            // 查询参数
            if (user.getUserName().equals("") || user.getPassword().equals("")) {
                mav.addObject("status", "fail");
                mav.addObject("message","账号或用户名为空！");
            }
            List<User> userList = userService.getUserByUserNameAndPassword(user);
            // 更新用户，通过数据查询后得到的用户为准
            if (userList.size() > 0) {
                user = userList.get(0);
                session.setAttribute("user", user); // 将登陆账户存储到session中
                // 放入jsp路径
                mav.setViewName("redirect:/orderList");
            } else {
                mav.addObject("status", "fail");
                mav.addObject("message","账号或密码错误！");
                mav.setViewName("signin");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("status", "fail");
            mav.addObject("message","服务器异常！");
            mav.setViewName("signin");
        }
        return mav;
    }

    /**
     * 获取当前登陆用户
     * @param session
     * @return
     */
    @RequestMapping(value="/getCurrentUserInfo",method=RequestMethod.GET)
    public ModelAndView getCurrentUserInfo(HttpSession session){
        ModelAndView mav = new ModelAndView();
        try{
            User user = (User) session.getAttribute("user");   // 获取用户信息
            mav.addObject("status", "success");
            mav.addObject("message","获取成功！");
            mav.addObject("user", user);
        }catch (Exception e) {
            e.printStackTrace();
            mav.addObject("status", "fail");
            mav.addObject("message","获取失败！");
        }
        return mav;
    }

    /**
     * 获取用户管理页面数据
     * @return
     */
    @RequestMapping("/accountManage")
    public ModelAndView listUser() {
        ModelAndView mav = new ModelAndView();
        try{
            List<User> userList = userService.listUser();  // 获取所有用户
            mav.addObject("status", "success");
            mav.addObject("message","获取成功！");
            mav.addObject("userList", userList);
        }catch (Exception e) {
            e.printStackTrace();
            mav.addObject("status", "fail");
            mav.addObject("message","获取失败！");
        }
        mav.setViewName("accountManage");
        return mav;
    }

    /**
     * 导航栏跳转到账号管理页面
     */
    @RequestMapping(value="/account")
    public ModelAndView account(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/accountManage");
        return modelAndView;
    }
}
