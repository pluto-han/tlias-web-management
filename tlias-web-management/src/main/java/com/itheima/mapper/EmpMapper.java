package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 *  员工
 */
@Mapper
public interface EmpMapper {
    //public List<Emp> list(String name, String gender, LocalDate begin, LocalDate end);
    public List<Emp> list(EmpQueryParam empQueryParam);
}
