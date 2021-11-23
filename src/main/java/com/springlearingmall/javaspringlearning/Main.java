package com.springlearingmall.javaspringlearning;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //AutoGenerator 是 MyBatis-Plus 的代码生成器，通过 AutoGenerator 可以快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码，极大的提升了开发效率。
        AutoGenerator autoGenerator = new AutoGenerator();

        /*数据库设置*/
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        //数据库类型
        dataSourceConfig.setDbType(DbType.MYSQL);
        //数据库驱动
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        //数据库用户名
        dataSourceConfig.setUsername("root");
        //数据库密码
        dataSourceConfig.setPassword("8161986");
        //数据库url
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/springlearning?useUnicode=true&characterEncoding=UTF-8");
        //装载进autoGenerator
        autoGenerator.setDataSource(dataSourceConfig);

        /*全局设置*/
        GlobalConfig globalConfig = new GlobalConfig();
        //完成后是否打开本地路径
        globalConfig.setOpen(true);
        //设置输出地址
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        //设置作者名
        globalConfig.setAuthor("LFR");
        //设置生成service的名字
        globalConfig.setServiceName("%sService");
        //装载进autoGenerator
        autoGenerator.setGlobalConfig(globalConfig);

        /*包信息设置*/
        PackageConfig packageConfig = new PackageConfig();
        //设置根路径 //controller那些文件的根路径
        packageConfig.setParent("com.springlearingmall.javaspringlearning");
        //entity的文件名
        packageConfig.setEntity("entity");
        //mapper的文件名
        packageConfig.setMapper("mapper");
        //controller的文件名
        packageConfig.setController("controller");
        //service的文件名
        packageConfig.setService("service");
        //ServiceImpl的文件名
        packageConfig.setServiceImpl("service.impl");
        //装载进autoGenerator
        autoGenerator.setPackageInfo(packageConfig);

        /*策略配置*/
        StrategyConfig strategyConfig = new StrategyConfig();
        //设置lombook
        strategyConfig.setEntityLombokModel(true);
        //下划线转驼峰 //数据库和类名的转换
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);

        List<TableFill> list = new ArrayList<>();
        TableFill createTime = new TableFill("create_time",FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time",FieldFill.INSERT_UPDATE);
        list.add(createTime);
        list.add(updateTime);

        //自动填充list的信息
        strategyConfig.setTableFillList(list);
        //装载进autoGenerator
        autoGenerator.setStrategy(strategyConfig);

        //执行
        autoGenerator.execute();
    }
}
