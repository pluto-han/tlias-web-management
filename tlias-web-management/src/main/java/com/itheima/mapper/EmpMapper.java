package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *  员工
 */
@Mapper
public interface EmpMapper {
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)\n" +
            "    values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * 根据id批量删除员工基本信息
     * @param ids
     */

    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息和工作经历信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 根据id更新员工基本信息
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 统计员工职位信息
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别信息
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 登录
     * @param emp
     * @return
     */
    @Select("select id, username, name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
