package com.wellcom.jppt.system.common.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.wellcom.jppt.aop.database.DataSourceInfoResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;


import org.mybatis.spring.annotation.MapperScan;

@Configuration
public class WebMvcConfigSuppor extends WebMvcConfigurationSupport {
    @Autowired
    DataSourceInfoResolver dataSourceInfoResolver;

	//添加自定义得拦截器
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        //要使用注解的方式，这样 HandlerMethodArgumentResolver的子类注解才不会被无效
        //argumentResolvers.add(new DataSourceInfoResolver());
        argumentResolvers.add(dataSourceInfoResolver);
    }

	

}
