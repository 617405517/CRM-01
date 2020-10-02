package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.User;

public interface UserDao {

    User selectUser(String loginAct,String loginPwd);


}
