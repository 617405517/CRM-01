package com.bjpowernode.crm.settings.web.controller;


import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserServiceImpl service;

    @RequestMapping(value = "/login/login.do")
    public void login(HttpServletRequest request, HttpServletResponse response) {

        String ip = request.getRemoteAddr();
        String loginAct = request.getParameter("loginAct");
        String loginPwd = MD5Util.getMD5(request.getParameter("loginPwd"));

        try {

            User myUser = service.selectUser(loginAct, loginPwd, ip);
            request.getSession().setAttribute("myUser",myUser);
            PrintJson.printJsonFlag(response,true);

        }catch (Exception e){
            e.printStackTrace();

            String msg = e.getMessage();
            Map<String,Object> mp = new HashMap<>();
            mp.put("success",false);
            mp.put("message",msg);
            PrintJson.printJsonObj(response,mp);


        }




    }



}
