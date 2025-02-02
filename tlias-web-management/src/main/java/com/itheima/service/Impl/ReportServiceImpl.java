package com.itheima.service.Impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    /**
     * 统计员工职位信息
     * @return
     */
    @Override
    public JobOption getEmpJobData() {
        // 调用mapper接口，获取职位统计信息的map
        List<Map<String, Object>> list = empMapper.countEmpJobData(); //map: pos='教研主管'    val: num=1
        // 组装结果
        List<Object> jobList = list.stream().map(datamap -> datamap.get("pos")).toList();
        List<Object> dataList = list.stream().map(datamap -> datamap.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

    /**
     * 统计员工性别信息
     * @return
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
