package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public User selectUser(String loginAct,String loginPwd,String ip) throws LoginException {

        Map<String,String> map = new HashMap<>();

        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User user = userDao.selectUser(map);

        if (user == null){
           throw new LoginException("账号密码错误");
        }

        // 判断账号失效时间
        String expireTime = user.getExpireTime();
        String nowTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(nowTime)<0){
            throw new LoginException("账号已失效");
        }

        // 判断账号锁
        if ("0".equals(user.getLockState())){
            throw new LoginException("账号已锁定，请联系管理员");
        }

        // 判断ip地址
        String allowTips = user.getAllowIps();


        if (allowTips !=null && allowTips != ""){
            if (!allowTips.contains(ip)){
                System.out.println(ip);
                System.out.println(allowTips);
                throw new LoginException("ip地址受限");
            }
        }



        return user;
    }
}
