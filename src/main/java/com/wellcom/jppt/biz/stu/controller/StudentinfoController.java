package com.wellcom.jppt.biz.stu.controller;

//import java.sql.Connection;
// import java.sql.DriverManager;

import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wellcom.jppt.biz.stu.service.impl.StudentinfoServiceImpl;
import com.wellcom.jppt.biz.stu.entity.Studentinfo;
import com.wellcom.jppt.biz.stu.service.StudentinfoService;
import com.wellcom.jppt.anotation.database.DataSourceInfo;
import com.wellcom.jppt.aop.database.TargetDataSource;
import com.wellcom.jppt.system.common.datasources.GetDatainfo;
import com.wellcom.jppt.system.common.base.CommonResult;
import com.wellcom.jppt.system.common.datasources.DBChangeService;
import com.wellcom.jppt.system.common.datasources.DataSource;
import com.wellcom.jppt.system.common.datasources.GetCookieDB;
import com.wellcom.jppt.system.common.base.CommonPageRelsult;
import com.wellcom.jppt.system.common.datasources.GetDatainfo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.netty.http.server.HttpServerResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/biz")
public class StudentinfoController {
	@Autowired
	private StudentinfoService studentService;
	@Autowired
	private StudentinfoService studentinfoService;;
	@Autowired
    private DBChangeService dbChangeServiceImpl;
	@Autowired
	GetCookieDB defaultDB;
	@Autowired
	GetDatainfo info2;
	
	@RequestMapping("/index")
	public CommonResult<?> test() {
		try {
			this.test1();
			
		} catch (Exception e) {
			//TODO: handle exception
			System.out.println(e);
		}
		//System.out.println(studentService.queryStudentInfo("33040092574234"));
		System.out.println("----------------------进入stucontroller方法");
		//return studentService.queryStudentInfo("33040092574234").toString();
		ObjectMapper mapper = new ObjectMapper();

		return CommonResult.success();
		//return mapper.writeValueAsString(CommonResult.success());
    }
    public void test1() throws ClassNotFoundException, SQLException {
		// String className="oracle.jdbc.driver.OracleDriver";//oracle的className（ojdbc6.jar）

		// String url="jdbc:oracle:thin:@192.168.191.160:1521:jxdb4g";//oracle的url（ojdbc6.jar）

		// //String className="com.microsoft.sqlserver.jdbc.SQLServerDriver";//sqlServer的className（sqljdbc.jar）

		// //String url="jdbc:sqlserver://localhost:1433;databasename=数据库名";//sqlServer的url（sqljdbc.jar）

		// Class.forName(className);//导入驱动类

		// Connection conn= DriverManager.getConnection(url, "dfo_3304", "wellcom");// 创建数据库连接对象，三个参数分别为:url,user,password
		// Statement st=conn.createStatement();
		String version = SpringVersion.getVersion();
		String version1 = SpringBootVersion.getVersion();
		System.out.println("spring:"+version +"\n springboot:" + version1);
	}
	@TargetDataSource
	@RequestMapping("/queryStudentinfo")
	//public CommonPageRelsult<?> queryStudentinfo(HttpServletRequest request) {
	public Object queryStudentinfo(HttpServletRequest request, @DataSourceInfo(value = "Fuck You!!!!!!") String dataSourceId) {
		//dbChangeServiceImpl.removeContextHolderValue();
		List<Map<String, Object>> studentInfoList = null;
		PageInfo<Map<String, Object>> dto = null;
		Studentinfo stu = new Studentinfo();
		String DBFromCookieStr = null;
		DataSource dataSourcesList = null ;

		int offset = 1;//第几页，默认1
        int limit = 10;//每页显示多少，默认10
		//notes:
        //equals比较时最好不要用未知值的变量点出equals，因为null时会报错，null是点不出来equals
        //== 基础类型比的是值  引用类型比的是地址 equals正常比的是内容
        String a = request.getParameter("offset");
        String b = request.getParameter("limit");
        if (!((a == null||"".equals(a)) || (b == null||"".equals(b)))) {
            offset = Integer.parseInt(request.getParameter("offset"));
            limit = Integer.parseInt(request.getParameter("limit"));
		}
		stu.setIdnum(request.getParameter("idnum"));

		try {
			PageHelper.startPage(offset, limit);
			//studentInfoList = studentinfoServiceImpl.queryStudentInfo(stu);
			switch (dataSourceId) {
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
		
		} catch (Exception e) {
			//TODO: handle exception
			System.out.println("+++++上级接收异常：+++++\n" + e);
			return  new ResponseEntity<>(CommonResult.result(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), "Fuck You!!!!!"),HttpStatus.INTERNAL_SERVER_ERROR);
		}

		dto = new PageInfo<Map<String, Object>>(studentInfoList);

		return CommonPageRelsult.toPage(dto);
	} 
}