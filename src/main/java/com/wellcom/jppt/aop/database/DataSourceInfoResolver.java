package com.wellcom.jppt.aop.database;

import javax.servlet.http.HttpServletRequest;

import com.wellcom.jppt.anotation.database.DataSourceInfo;
import com.wellcom.jppt.system.common.datasources.DataSource;
import com.wellcom.jppt.system.common.datasources.GetCookieDB;
import com.wellcom.jppt.system.common.datasources.GetDatainfo;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
// @Component
// @Aspect

@Component
public class DataSourceInfoResolver implements HandlerMethodArgumentResolver {

    @Autowired
    GetCookieDB defaultDB;
    @Autowired
    GetDatainfo info2;
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // TODO Auto-generated method stub
        return  parameter.hasParameterAnnotation(DataSourceInfo.class);
        //return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // TODO Auto-generated method stub
        //获取通过注解写入的参数
        DataSourceInfo annotation = parameter.getParameterAnnotation(DataSourceInfo.class);
        String DBFromCookieStr = null;
        DataSource dataSourcesList = null ;


        System.out.println("———————————————————————注解传入的参数：" + annotation.value());
        //defaultDB = new GetCookieDB();
        DBFromCookieStr = defaultDB.getCookieDB(webRequest.getNativeRequest(HttpServletRequest.class).getCookies());		
		
        //通过配置文件获取默认数据库的地区编码    
        for (DataSource db: info2.getDatasource()){
            if (db.getDatasourceId().equals(DBFromCookieStr)) {
                dataSourcesList = db;
            }
        }
        DBFromCookieStr = dataSourcesList.getAreacode();
        System.out.println("———————————————————————注解返回的参数：" + DBFromCookieStr);
        return DBFromCookieStr;
    }
    
}