package com.ning.crud.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ning.crud.bean.Emp;
import com.ning.crud.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    EmpService empService;

    @RequestMapping("/list")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){

        //这不是分页查询
        /*List<Emp> list = empService.getAll();*/

        /*
        为了分页方便，引入分页插件PageHelper
         pagehelper只需要在查询钱传入页码和每一页的长度
         startPage后面紧跟着分页查询
         再用pageinfo包装结果，只需要将pageinfo交给页面就行了
         里面已经封装了各种需要的分页信息
         */
        PageHelper.startPage(pn,10);
        List<Emp> emps = empService.getAll();
        PageInfo pageInfo = new PageInfo(emps);
        model.addAttribute("pageInfo",pageInfo);
        return "list";
    }

}
