package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.User;

public interface UserService {


    User selectUser(String loginAct,String loginPwd);


}
