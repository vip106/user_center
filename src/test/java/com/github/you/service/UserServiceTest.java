package com.github.you.service;

import com.github.you.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("dogyou");
        user.setUserAccount("123");
        user.setAvatarUrl("xxxxxx");
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setPhone("123");
        user.setEmail("456");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        assertTrue(result);


    }

    @Test
    public void testUserRegister() {
        String userName = "fasd";
        String userAccount = "you123";
        String password = "";
        String checkPassword = "123456789";
        long result = userService.userRegister(userAccount, password, checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount = "yo u123";
        password = "123456789";
        long result1 = userService.userRegister(userAccount, password, checkPassword);
        Assertions.assertEquals(-1,result1);
        password = "12345678";
        long result2 = userService.userRegister(userAccount, password, checkPassword);
        Assertions.assertEquals(-1,result2);
        userAccount = "yupi";
        password = "123456789";
        long result3 = userService.userRegister(userAccount, password, checkPassword);
        Assertions.assertTrue(result3 > 0);
    }

}