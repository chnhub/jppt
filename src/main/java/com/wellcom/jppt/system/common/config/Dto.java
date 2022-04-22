package com.wellcom.jppt.system.common.config;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
// 使用分页插件后不能直接返回list了，需要自定一个page对象
public class Dto<T> {
    private Integer page; //分页起始页
    private Integer size; //分页记录数
    private List<T> rows;//返回的记录集合
    private long total;
    
}