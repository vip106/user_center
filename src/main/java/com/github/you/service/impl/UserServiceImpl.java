package com.github.you.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.you.contant.UserConstant;
import com.github.you.mapper.UserMapper;
import com.github.you.model.domain.User;
import com.github.you.service.UserService;
import com.github.you.util.CheckString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
* @author Administrator
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-04-07 21:41:53
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Resource
    private UserMapper userMapper;



    @Override
    public long userRegister(String userAccount, String password, String checkPassword) {
        if (StringUtils.isAnyBlank(userAccount,password,checkPassword)) {
            return -1;
        }
//        账户长度 **不小于** 4 位
        if (userAccount.length() < 4) {
            return -1;
        }
        if (password.length() < 8) {
            return -1;
        }
        QueryWrapper<User> queryWrap = new QueryWrapper<>();
        queryWrap.eq("userAccount",userAccount);
        if (userMapper.selectCount(queryWrap) < 0) {
            return -1;
        }
        if (CheckString.isSpecialChar(userAccount)) {
            return -1;
        }
        if (!password.equals(checkPassword)) {
            return -1;
        }
        String encryPassword = DigestUtils.md5DigestAsHex((UserConstant.SALT + password).getBytes(StandardCharsets.UTF_8)); // 加密
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryPassword);
        boolean userId = this.save(user);
        if (!userId) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public User doLogin(String userAccount, String password , HttpServletRequest request) {
        if (userAccount == null || password == null) {
            return null;
        }
//        账户长度 **不小于** 4 位
        if (userAccount.length() < 4) {
            return null;
        }
        if (password.length() < 8) {
            return null;
        }

        if (CheckString.isSpecialChar(userAccount)) {
            return null;
        }
        String encryPassword = DigestUtils.md5DigestAsHex((UserConstant.SALT + password).getBytes(StandardCharsets.UTF_8)); // 加密
        QueryWrapper<User> queryWrap = new QueryWrapper<>();
        queryWrap.eq("userAccount",userAccount);
        queryWrap.eq("userPassword",encryPassword);
        User user = userMapper.selectOne(queryWrap);
        if (user == null) {
            log.info("message: userAccount is error or password is error!");
            return null;
        }
        User safetyUser = getSafetyUser(user);
        request.getSession().setAttribute(UserConstant.LOGIN_STATUS,user);
        return safetyUser;

    }
     /**
     * @description: 用户脱敏
     * @author Sniper_lw
     * @date: 2023/4/9 13:31
     */
    public User getSafetyUser(User originUser) {
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        return safetyUser;
    }
}




