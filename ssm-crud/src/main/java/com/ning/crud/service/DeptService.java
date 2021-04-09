package com.ning.crud.service;

import com.ning.crud.bean.Dept;
import com.ning.crud.dao.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ning
 * @create 2021/4/9 0009 16:33
 */

@Service
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    //查出所有的部门信息
    public List<Dept> getDepts() {
        List<Dept> list = deptMapper.selectByExample(null);
        return  list;
    }
}
