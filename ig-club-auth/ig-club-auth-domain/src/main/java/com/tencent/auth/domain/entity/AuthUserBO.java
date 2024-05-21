package com.tencent.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息bo
 *
 * @author makejava
 * @since 2024-05-11 15:36:43
 */

@Data
public class AuthUserBO implements Serializable {
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

