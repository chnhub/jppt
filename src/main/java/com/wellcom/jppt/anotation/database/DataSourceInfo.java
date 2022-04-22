package com.wellcom.jppt.anotation.database;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.*;

//注解 aop实现切换数据库
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceInfo {
    String value() default "dataSourceId";// 从cookie获取的数据库名
}