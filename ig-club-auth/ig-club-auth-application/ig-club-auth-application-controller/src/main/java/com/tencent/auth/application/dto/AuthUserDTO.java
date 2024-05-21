package com.tencent.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息dto
 *
 * @author makejava
 * @since 2024-05-11 15:36:43
 */
@Data
public class AuthUserDTO implements Serializable {
    private Long id;

    private String userName;

    private String nickName;

    private String email;

    private String phone;

    private String password;

    private Integer sex;

    private String avatar;

    private Integer status;

    private String introduce;

    private String extJson;


}

