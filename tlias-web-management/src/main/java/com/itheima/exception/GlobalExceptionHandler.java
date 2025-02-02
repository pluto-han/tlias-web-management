package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result HandleException(Exception e) {
       log.error("程序出错啦", e);

       return Result.error("出错啦，请联系管理员");
   }


   @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出错啦", e);
        String msg = e.getMessage();
        int i = msg.lastIndexOf("Duplicate entry");
        String errMsg = msg.substring(i);
        String[] arr = errMsg.split(" ");

        return Result.error(arr[2] + "已存在");
   }
}
