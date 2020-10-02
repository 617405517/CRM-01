package com.bjpowernode.crm.settings.web.controller;


import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Resource
    private UserServiceImpl service;

    @RequestMapping(value = "/login/login.do")
    public String login(HttpServletRequest request){
        String userName =  request.getParameter("loginAct");
        String password = request.getParameter("loginPwd");
        User user = service.selectUser(userName, password);
        System.out.println(user);
        if (user !=null){
            return "workbench/index.html";
        }
        return "login.html";
    }

}
