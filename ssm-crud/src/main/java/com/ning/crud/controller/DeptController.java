package com.ning.crud.controller;

import com.ning.crud.bean.Dept;
import com.ning.crud.bean.Emp;
import com.ning.crud.bean.Msg;
import com.ning.crud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ning
 * @create 2021/4/9 0009 16:29
 */
@Controller
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查询所有的部门信息
    @ResponseBody
    @RequestMapping("/getDepts")
    public Msg getDept(){
        //查出的所有的信息
        List<Dept> list = deptService.getDepts();
        return Msg.success().add("depts",list);
    }


}