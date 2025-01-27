package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 *  员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工工作经历
     * @param exprList
     */

    public void insertBatch(List<EmpExpr> exprList);
}
