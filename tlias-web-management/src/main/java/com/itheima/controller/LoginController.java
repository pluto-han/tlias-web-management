package com.itheima.controller;

/**
 *  登录请求处理
 */

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController

public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result Login(@RequestBody Emp emp) {
        log.info("登录:{}", emp);
        LoginInfo info = empService.login(emp);

        if(info != null) {
            return Result.success(info);
        } else {
            return Result.error("用户名或密码错误");
        }


    }
}
