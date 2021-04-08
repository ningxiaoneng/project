package com.ning.crud.test;

import com.ning.crud.bean.Dept;
import com.ning.crud.bean.Emp;
import com.ning.crud.dao.DeptMapper;
import com.ning.crud.dao.EmpMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
* 1.导入spring单元测试模块@ContextConfiguration
* 2.@ContextConfiguration指定spring配置文件的位置
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSSM {
    @Autowired
    DeptMapper deptMapper;

    @Autowired
    EmpMapper empMapper;

    @Autowired
    SqlSession sqlSession;



     //mybatis逆向工程
    @Test
    public void dbHelper() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("F:\\special\\java\\project\\ssm-crud\\src\\main\\resources\\Mybatis_Generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }

    //测试dao的工作
    @Test
    public void  testMapper(){

        System.out.println(deptMapper);
/*      //添加部门
        deptMapper.insertSelective(new Dept(null,"宣传部"));
        //添加用户
        empMapper.insertSelective(new Emp(null,"ning","G","1697703",2));
*/

        //批量插入多个数据，使用批量操作的sqlsession
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        for (int i = 0; i < 500; i++) {
            String uid = UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Emp(null,uid,"B",uid+"@qq.com",1));
        }
        System.out.println("批量操作完成");

    }



}
