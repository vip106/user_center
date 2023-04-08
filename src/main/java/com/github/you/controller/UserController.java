package com.github.you.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.you.contant.UserConstant;
import com.github.you.model.domain.User;
import com.github.you.model.request.UserLoginRequest;
import com.github.you.model.request.UserRegisterRequest;
import com.github.you.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/8 16:38
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount,userPassword,checkPassword);

    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest , HttpServletRequest request) {

        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)) {
            return null;
        }
        return userService.doLogin(userAccount,userPassword,request);

    }
    @GetMapping("/search")
    public List<User> search(String userName,HttpServletRequest request) {
        if (isAuthority(request)) {
            return new ArrayList<User>();
        }
        QueryWrapper<User> query = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            query.like("username",userName);
        }
        return userService.list(query);
    }
    @DeleteMapping("/delete")
    public boolean deleteUser(@RequestBody long id,HttpServletRequest request) {
        if (isAuthority(request)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }


    public boolean isAuthority(HttpServletRequest request) {
        Object userInfo = request.getSession().getAttribute(UserConstant.LOGIN_STATUS);
        User user = (User) userInfo;
        if (userInfo == null || user.getRole() != UserConstant.ROLE_ADMIN) {
            return false;
        }
        return true;
    }


}
