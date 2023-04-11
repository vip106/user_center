package com.github.you.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.you.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
* @author Administrator
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-04-07 21:41:53
*/
public interface UserService extends IService<User> {

    long userRegister(String userAccount,String password , String checkPassword);

    User doLogin(String userAccount, String password, HttpServletRequest request);

    User getSafetyUser(User originUser);
}
