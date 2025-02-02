package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     *  分页查询方法+条件查询
     * @param empQueryParam
     * @return
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工信息
     * @param emp
     */
    void save(Emp emp);

    /**
     * 批量删除员工信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     *  根据id查询员工信息
     * @param id
     * @return
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工信息和工作经历
     * @param emp
     */
    void update(Emp emp);

    /**
     * 员工登录
     * @param emp
     * @return
     */
    LoginInfo login(Emp emp);
}
