package com.wellcom.jppt.biz.sys.controller;

// import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.wellcom.jppt.system.common.datasources.DBChangeService;
import com.wellcom.jppt.system.common.datasources.DataSource;
import com.wellcom.jppt.system.common.datasources.GetDatainfo;
import com.wellcom.jppt.biz.stu.service.StudentinfoService;
//import com.wellcom.jppt.biz.stu.entity.StudentinfoEntity;
import com.wellcom.jppt.biz.stu.entity.Studentinfo;

@RestController
@RequestMapping("/biz/sys")
public class DataSourceController {

    @Autowired
    GetDatainfo dataInfo;

    @Autowired
    private DBChangeService dbChangeServiceImpl;

    @Autowired
    StudentinfoService studentinfoService;

    @RequestMapping("/getDBList")
    public String getDBList() throws JsonProcessingException {
        List<? extends DataSource> dataSourcesList = dataInfo.getDatasource();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(dataSourcesList);
    }

    //切换数据库
    @RequestMapping("/changeDB")
    public String changeDB(HttpServletRequest request) throws Exception {
        String retrunStr = "{\"result\":0, \"msg\":\"切换失败\"}";
        String dbIdStr = request.getParameter("datasourceId");
        if (!(dbIdStr == null||"".equals(dbIdStr))) {
            if(dbChangeServiceImpl.changeDb(dbIdStr)){

                retrunStr = "{\"result\":0, \"msg\":\"切换成功\"}";

                /*
                Studentinfo stu = new Studentinfo();
                stu.setStudentCode("33050092085758");

                List<Map<String, Object>> studentInfoList = null;
                StudentinfoEntity studao = null;
                ObjectMapper mapper = new ObjectMapper();
                PageHelper.startPage(1, 10);
                studentInfoList = studentinfoService.queryStudentInfo(stu);
                System.out.println(studentInfoList);
                */
                
            }else{
                retrunStr = "{\"result\":1, \"msg\":\"切换失败\"}";
                System.out.println("未找到数据源");
            }
    
        }
        
        return retrunStr;
    }
}