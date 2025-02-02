package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

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

    /**
     * 新增员工信息
     * @param emp
     */
    @Transactional(rollbackFor = {Exception.class})// 事务管理. 普通的transactional只会回滚runtimeerror异常，要加rollbackfor
    @Override
    public void save(Emp emp) {
        try {
            //补全员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            //保存员工基本信息
            empMapper.insert(emp);

            //保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            // 记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }
    }

    // 要么同时成功要么同时失败
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        // 删除员工基本信息
        empMapper.deleteByIds(ids);
        // 删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        // 1 根据id修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        // 2 根据id修改员工工作经历
        // 2.1 先根据员工id删除原有工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        // 2.2 再添加该员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
                empExprMapper.insertBatch(exprList);
            });
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        // 调用mapper接口,根据用户名和密码查询员工
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        // 判断是否存在这个员工,存在则组装登录信息
        if(e != null) {
            log.info("登陆成功， 员工信息为:{}", e);

            //生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);

            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
        } else  {
            return null;
        }
    }
}
