package com.github.you.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.you.model.User;
import com.github.you.service.UserService;
import com.github.you.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-04-07 21:41:53
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




