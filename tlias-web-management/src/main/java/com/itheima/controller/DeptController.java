package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    //

    // 查询全部部门名称
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();

        return Result.success(deptList);
    }

    // 根据id删除部门
    @DeleteMapping
    // 前端传递的请求参数名和定义的形参名字一样
    public Result delete(Integer id) {
        log.info("根据ID删除部门: {}", id);
        deptService.deleteById(id);

        return Result.success();
    }

    // 新增部门
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门: {}", dept);
        deptService.add(dept);

        return Result.success();
    }

    // 根据id查询部门（查询回显）
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询部门数据: {}", id);
        Dept dept = deptService.getById(id);

        return Result.success(dept);
    }

    // 修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门数据: {}", dept);
        deptService.update(dept);

        return Result.success();
    }
}
