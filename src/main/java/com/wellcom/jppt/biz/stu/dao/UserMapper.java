package com.wellcom.jppt.biz.stu.dao;

import com.wellcom.jppt.biz.stu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
 
/**
 * @Author : JCccc
 * @CreateTime : 2019/10/23
 * @Description :
 **/
@Mapper
public interface UserMapper {
 
    List<User> queryUserInfo();
 
}