package com.wellcom.jppt.biz.stu.service;

import com.wellcom.jppt.biz.stu.dao.StudentinfoMapper;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

// import com.wellcom.jppt.biz.stu.entity.StudentinfoEntity;
import com.wellcom.jppt.biz.stu.entity.Studentinfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface StudentinfoService {

    //默认查询学员信息
    public List<Map<String, Object>> queryStudentInfo(Studentinfo studentinfo);
    //查询3301学员信息
    public List<Map<String, Object>> queryStudentInfo3301(Studentinfo studentinfo);

}


