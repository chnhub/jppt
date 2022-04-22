package com.wellcom.jppt.biz.stu.service;

import java.util.List;

import com.wellcom.jppt.biz.stu.entity.User;
import com.wellcom.jppt.biz.stu.dao.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userM;

    public List<User> queryUserInfo(){
        return this.userM.queryUserInfo();
    }
}

