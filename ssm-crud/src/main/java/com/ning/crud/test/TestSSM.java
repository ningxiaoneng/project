package com.ning.crud.test;

import com.ning.crud.bean.Dept;
import com.ning.crud.dao.DeptMapper;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
* 1.导入spring单元测试模块@ContextConfiguration
* 2.@ContextConfiguration指定spring配置文件的位置
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestSSM {
    @Autowired
    DeptMapper deptMapper;

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


    }

}
