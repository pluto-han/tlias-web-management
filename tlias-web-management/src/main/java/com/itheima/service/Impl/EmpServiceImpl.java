package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /**
     * 原始分页查询方法
     * @param page
     * @param pageSize
     * @return
     */
    /*
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        // 调用mapper接口查询总记录数
        Long total = empMapper.count();
        // 调用mapper接口查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        // 封装PageResult
        return new PageResult<Emp>(total, rows);
    } */

    /**
     *  基于PageHelper进行分页查询
     * @param empQueryParam
     * @return
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //查询
        List<Emp> emplist = empMapper.list(empQueryParam);
        //解析查询结果并封装
        Page<Emp> p = (Page<Emp>) emplist;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

}
