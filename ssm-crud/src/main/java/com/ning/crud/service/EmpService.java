package com.ning.crud.service;

import com.ning.crud.bean.Emp;
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
        return empMapper.selectByExampleWithDept(null);
    }
}
