package com.github.you.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/8 16:37
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}
