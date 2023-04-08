package com.github.you.model.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 昵称  
     */
    private String username;

    /**
     * 登录账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 电话 
     */
    private String phone;

    /**
     * 邮件
     */
    private String email;
    /**
     * 用户角色 0 普通用户  1  管理员
     */
    private Integer role;

    /**
     * 用户状态 
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    private static final long serialVersionUID = 1L;
}