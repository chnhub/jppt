package com.wellcom.jppt.biz.stu.controller;

import com.wellcom.jppt.system.common.datasources.DBContextHolder;
import com.wellcom.jppt.system.common.datasources.GetCookieDB;
import com.wellcom.jppt.system.common.datasources.GetDatainfo;
import com.wellcom.jppt.system.common.datasources.DataSource;
//import com.wellcom.jppt.biz.stu.entity.StudentinfoEntity;
// import com.wellcom.jppt.biz.stu.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wellcom.jppt.aop.database.TargetDataSource;
import com.wellcom.jppt.biz.stu.entity.Studentinfo;
// import com.wellcom.jppt.system.common.config.Dto;
import com.wellcom.jppt.system.common.datasources.DBChangeService;
import com.wellcom.jppt.biz.stu.service.impl.StudentinfoServiceImpl;
import com.wellcom.jppt.biz.stu.service.StudentinfoService;

import com.wellcom.jppt.biz.stu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
// import javax.rmi.CORBA.Stub;
import javax.servlet.http.HttpServletRequest;
 
/**
 * @Author : JCccc
 * @CreateTime : 2019/10/23
 * @Description :
 **/
 
@RestController
public class UserController {
 
 
    @Autowired
    private DBChangeService dbChangeServiceImpl;
    @Autowired
    UserService userService;
    @Autowired
    StudentinfoService studentinfoService;
    @Autowired
    GetDatainfo info2;
    @Autowired
    GetCookieDB defaultDB;
 
 
 
    /**
     * 查询所有
     * @return
     */
    // 通过请求path
    //@PathVariable("offset") Integer id,@PathVariable("limit") String name  
    @TargetDataSource
    @RequestMapping("/biz/test") ////业务层部分代码暂时写到Controller层
    public String test(HttpServletRequest request) throws Exception {
        System.out.println("==========================\n"+info2 + "==========================\n");
        /*
        //切换到数据库dbtest2
        String datasourceId="dbtest2";
        dbChangeServiceImpl.changeDb(datasourceId);
        List<User> userList= userService.queryUserInfo();
        System.out.println(userList.toString());
 
        //再切换到数据库dbtest3
        dbChangeServiceImpl.changeDb("dbtest3");
        List<User> userList3= userService.queryUserInfo();
        System.out.println(userList3.toString());
        
        //通过请求直接获取request中包含的所有的cookie
        Cookie[] cookies = request.getCookies();
        String DBFromCookieStr = null;
        // 遍历所有的cookie,然后根据cookie的key值来获取value值
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("datasourceId")) {
                    DBFromCookieStr = cookie.getValue();
                    System.out.println("+++++++++++++++++++++++++" + DBFromCookieStr);
                }
            }
        }else {
            DBFromCookieStr = info2.getDefaultDB();
        }
        


        if(DBFromCookieStr == null||"".equals(DBFromCookieStr)){
            DBFromCookieStr = info2.getDefaultDB();
        }
        */
        
        //studentinfoService.queryStudentInfo(request);

        String DBFromCookieStr = null;
        DBFromCookieStr = defaultDB.getDefaultDB(request.getCookies());

        int offset = 1;//第几页，默认1
        int limit = 10;//每页显示多少，默认10

        //equals比较时最好不要用未知值的变量点出equals，因为null时会报错，null是点不出来equals
        //== 基础类型比的是值  引用类型比的是地址 equals正常比的是内容
        String a = request.getParameter("offset");
        String b = request.getParameter("limit");
        if (!((a == null||"".equals(a)) || (b == null||"".equals(b)))) {
            offset = Integer.parseInt(request.getParameter("offset"));
            limit = Integer.parseInt(request.getParameter("limit"));
        }
            
        Studentinfo stu = new Studentinfo();
        //stu.setStudentCode("33050092085758");
        //stu.setIdnum(request.getParameter("idnum"));

        Studentinfo stu2 = new Studentinfo();
        stu2.setStudentCode("33040092574234");

        //String datasourceId="jxdb4g";
        
        List<Map<String, Object>> studentInfoList = null;
        ObjectMapper mapper = new ObjectMapper();
        
        //Dto<Map<String, Object>> dto = new Dto<Map<String, Object>>();
        PageInfo<Map<String, Object>> dto = null;
        //PageHelper.startPage(offset, limit);
        //studentInfoList = studentinfoService.queryStudentInfo(stu);
        
        if(DBFromCookieStr != null && !"".equals(DBFromCookieStr)){
            //List<User> userList= userService.queryUserInfo();  
            //List<? extends DataSource> dataSourcesList = info2.getDatasource();
            DataSource dataSourcesList = null ;
            
            for (DataSource db: info2.getDatasource()){
                if (db.getDatasourceId().equals(DBFromCookieStr)) {
                    dataSourcesList = db;
                }
            }
            PageHelper.startPage(offset, limit);
            //dataSourcesList.setAreacode(null);
            switch (dataSourcesList.getAreacode()) {
                case "3306": case "3304":
                    //PageHelper.startPage(offset, limit);
                    studentInfoList = studentinfoService.queryStudentInfo(stu);
                    break;
                case "3301":
                    //PageHelper.startPage(offset, limit);
                    studentInfoList = studentinfoService.queryStudentInfo3301(stu);
                    int ttes;
                    System.out.println(studentInfoList);
                    break;  
                default:
                    break;
            }
            
            dto = new PageInfo<Map<String, Object>>(studentInfoList);
            /*
            if ("3306".equals(dataSourcesList.getAreacode())) {
                PageHelper.startPage(offset, limit);
                studentInfoList = studentinfoService.queryStudentInfo(stu);
                dto = new PageInfo<Map<String, Object>>(studentInfoList);
            }
            if ("3301".equals(dataSourcesList.getAreacode())) {
                PageHelper.startPage(offset, limit);
                studentInfoList = studentinfoService.queryStudentInfo(stu);
                dto = new PageInfo<Map<String, Object>>(studentInfoList);
            }
            */

           
            /*
            System.out.println(studentInfoList.toString());*/
        }else{
            System.out.println("未找到数据源");
            return null;
        }
        
        // PageHelper.startPage(offset, limit);
        // studentInfoList = studentinfoService.queryStudentInfo(stu);
        // dto = new PageInfo<Map<String, Object>>(studentInfoList);
        
        //切回主数据源
        //DBContextHolder.clearDataSource();
        //return studentInfoList.toString();
        //String jsonstr = "{\"total\":1768,\"list\":[{},{}],\"pageNum\":1,\"pageSize\":10,\"pages\":177,\"prePage\":0,\"nextPage\":2,\"isFirstPage\":true,\"isLastPage\":false,\"hasPreviousPage\":false,\"hasNextPage\":true,\"navigatePages\":8,\"navigatepageNums\":[1,2,3,4,5,6,7,8],\"navigateFirstPage\":1,\"navigateLastPage\":8}";
        String jsonstr = "{\"total\":1768,\"list\":[{},{}],\"pageNum\":1,\"pageSize\":10,\"pages\":177}";

        return mapper.writeValueAsString(dto);
        //return jsonstr;
        //return "offset:" + offset + "\n-------------------\nlimit:" + limit;
    }
 
}