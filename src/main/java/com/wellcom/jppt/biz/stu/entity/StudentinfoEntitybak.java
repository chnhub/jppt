package com.wellcom.jppt.biz.stu.entity;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentinfoEntitybak {

    private String studentcode;
    private String studentname;
    private String idnum;
    private List<Map<String, Object>> map;


}