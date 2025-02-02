package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 *  员工管理
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     *  分页查询
     * @return
     */
/*    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, String gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询: {}， {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);
        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);

        return Result.success(pageResult);
    }*/

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);

        return Result.success(pageResult);
    }

    /**
     * 新增员工
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工" + emp);
        empService.save(emp);

        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("根据id批量删除员工L: {}", ids);
        empService.delete(ids);

        return Result.success();
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询员工: {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 修改员工信息和工作经历
     */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工: {}", emp);
        empService.update(emp);

        return Result.success();
    }
}
