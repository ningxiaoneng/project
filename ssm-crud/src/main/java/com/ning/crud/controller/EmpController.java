package com.ning.crud.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ning.crud.bean.Dept;
import com.ning.crud.bean.Emp;
import com.ning.crud.bean.Msg;
import com.ning.crud.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    EmpService empService;

    //拿出所有的员工信息
    //这个方法用于原始的jsp跳转没有ajax的参与
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
        PageHelper.startPage(pn,5);
        List<Emp> emps = empService.getAll();
        PageInfo pageInfo = new PageInfo(emps,5);
        model.addAttribute("pageInfo",pageInfo);
        System.out.println(pageInfo.getList());
        return "list";
    }


    //ajax拿所有员工的信息
    //@ResponseBody可以直接将数据以json形式返回，但是需要导入Jackson的包
    @RequestMapping("/ajaxList")
    @ResponseBody
    public Msg getEmpsAjax(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        PageHelper.startPage(pn,5);
        List<Emp> emps = empService.getAll();
        PageInfo pageInfo = new PageInfo(emps,5);
        System.out.println(pageInfo.getList());
        return Msg.success().add("pageInfo",pageInfo);//这里创建 一个通用的MSg用来传递数据和信息
    }


    //保存新增员工
    @ResponseBody
    @RequestMapping(value = "/saveAddEmp",method = RequestMethod.POST)
    public Msg saveAddEmp(@Valid Emp emp, BindingResult result){
        if(result.hasErrors()){
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }else{
            empService.saveEmp(emp);
            return Msg.success();
        }
    }


    //检验用户名是否可用
    @ResponseBody
    @RequestMapping("/checkEmpName")
    public Msg checkEmpName(@RequestParam("empName") String empName){
        //先判断用户名是否是合法的表达式;
        String regName = "/(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})/";
        if(!empName.matches(regName)){
            return Msg.fail().add("va_msg", "用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }
        //数据库用户名重复校验
        boolean b =  empService.checkEmpName(empName);
        System.out.println(b);
        if (b){
            return Msg.success();
        }else{
            return Msg.fail();
        }

    }



}