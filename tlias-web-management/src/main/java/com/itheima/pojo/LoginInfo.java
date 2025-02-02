package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装登录结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginInfo {
    Integer id;
    String username;
    String name;
    String token;
}
