package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    /**
     * 本地磁盘存储代码
     * @param name
     * @param age
     * @param file
     * @return
     * @throws IOException
     */
/*    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("接收参数:{}, {}, {}", name, age, file);
        //接收原始文件名
        String originalFilename = file.getOriginalFilename();

        //新的文件名（UUID)
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")); //获取拓展名
        String newFileName = UUID.randomUUID().toString() + extension;

        //保存文件
        file.transferTo(new File(""));
        return Result.success();
    }*/

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传: {}", file.getOriginalFilename());
        // 将文件交给OSS存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传到OSS的url: {}", url);

        return Result.success(url);
    }
}
