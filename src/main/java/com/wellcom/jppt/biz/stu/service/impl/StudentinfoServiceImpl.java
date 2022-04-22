package com.wellcom.jppt.biz.stu.service.impl;

import com.wellcom.jppt.biz.stu.dao.StudentinfoMapper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

// import com.wellcom.jppt.biz.stu.entity.StudentinfoEntity;
import com.wellcom.jppt.biz.stu.entity.Studentinfo;
import com.wellcom.jppt.biz.stu.service.StudentinfoService;
import com.wellcom.jppt.system.common.base.CommonResult;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentinfoServiceImpl implements StudentinfoService{
    @Autowired
    StudentinfoMapper studentMapper;
    //默认查询学员信息
    public List<Map<String, Object>> queryStudentInfo(Studentinfo studentinfo){
        List<Map<String, Object>> list = null;
        try {
            
            list =  studentMapper.queryStudentInfo(studentinfo);
        } catch (Exception e) {
            //TODO: handle exception
            //System.out.println("！！！！！！！！！！异常："+e);
            throw e;
        }
        return list;
      
    }
    //查询3301学员信息
    public List<Map<String, Object>> queryStudentInfo3301(Studentinfo studentinfo){

        return studentMapper.queryStudentInfo3301(studentinfo);
    }
    
}