package com.wellcom.jppt.aop.database;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import com.wellcom.jppt.system.common.datasources.DBChangeService;
import com.wellcom.jppt.system.common.datasources.GetCookieDB;
import com.wellcom.jppt.system.common.datasources.DBContextHolder;

import org.aspectj.lang.annotation.Around;
//import org.springframework.stereotype.Component;



/**
* 数据库切换拦截器
* 
*/
@Component
@Aspect
public class DataSourceAspect {

    @Autowired
    private DBChangeService dbChangeServiceImpl;

    @Autowired
    GetCookieDB cookiesDB;

    String defaultDB = null;



    //TODO 切面execution留意可能会有更改
    @Pointcut("@annotation(com.wellcom.jppt.aop.database.TargetDataSource)")
    public void pointcut() {
    }
    //@Around(value = "pointcut()")
    public Object around() {
        System.out.println("-----------------------进入aop around-----------------------");
        return null;
    }
    @Before(value = "pointcut()")
    public void before(JoinPoint jp) throws Exception {
        Object[] args = jp.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        defaultDB = cookiesDB.getCookieDB(request.getCookies());
        // for (Object arg : args) {
        //     System.out.println("参数：" + arg);
        // } 
        dbChangeServiceImpl.changeDb(defaultDB);
        
        System.out.println("-----------------------进入aop before-----------------------");
        System.out.println("-| "+ defaultDB +" |-");
        //DBContextHolder.clearDataSource();
    }
    @After(value = "pointcut()")
    public void after(JoinPoint jp) throws Exception {
        // Object[] args = jp.getArgs();
        // HttpServletRequest request = (HttpServletRequest) args[0];
        // defaultDB = cookiesDB.getCookieDB(request.getCookies());
        dbChangeServiceImpl.removeContextHolderValue();
        System.out.println("-----------------------进入aop after-----------------------");
        System.out.println("-| "+ "用完清除contextHolder" +" |-");
        //DBContextHolder.clearDataSource();
    }


}
    
