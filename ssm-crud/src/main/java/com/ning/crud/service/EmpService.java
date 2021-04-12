package com.ning.crud.service;

import com.ning.crud.bean.Emp;
import com.ning.crud.bean.EmpExample;
import com.ning.crud.dao.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {

    @Autowired
    EmpMapper empMapper;

    //查询所有的员工
    public List<Emp> getAll() {
        EmpExample example = new EmpExample();
        example.setOrderByClause("emp_id");
        return empMapper.selectByExampleWithDept(example);
    }

    //保存新增员工
    public void saveEmp(Emp emp) {
        empMapper.insertSelective(emp);
    }


    //检验邮箱是否可用
    //查看当前数据库的当前邮箱的数量
    public Boolean checkEmpName(String empName){
        EmpExample example = new EmpExample();
        EmpExample.Criteria  criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = empMapper.countByExample(example);
        return count == 0;
    }

}
