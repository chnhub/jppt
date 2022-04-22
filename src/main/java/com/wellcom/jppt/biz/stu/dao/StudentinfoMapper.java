package com.wellcom.jppt.biz.stu.dao;

import java.util.List;
import java.util.Map;

// import com.wellcom.jppt.biz.stu.entity.StudentinfoEntity;
import com.wellcom.jppt.biz.stu.entity.Studentinfo;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//@Repository需要在Spring中配置扫描地址，然后生成Dao层的Bean才能被注入到Service层中。
//@Mapper不需要配置扫描地址，通过xml里面的namespace里面的接口地址，生成了Bean后注入到Service层中。
@Repository 
public interface StudentinfoMapper {
    // List<Map<String, Object>> queryStudentInfo(@Param("studentinfo")Studentinfo studentinfo);
    List<Map<String, Object>> queryStudentInfo(@Param("studentinfo")Studentinfo studentinfo);
    List<Map<String, Object>> queryStudentInfo3301(@Param("studentinfo")Studentinfo studentinfo);
}