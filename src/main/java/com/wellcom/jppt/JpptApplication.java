package com.wellcom.jppt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.wellcom.jppt.aop.database.DataSourceInfoResolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;


import org.mybatis.spring.annotation.MapperScan;

//@MapperScan(basePackages="com.ostrich.*.repository")这个注解是用户扫描mapper接口的也就是dao类，
//mybatis.mapper-locations，而这个是用于扫描mapper.xml的，二者缺少一个都会报错
@MapperScan({"com.wellcom.jppt.biz.*.dao","com.wellcom.jppt.system.common.datasources"})
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class JpptApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpptApplication.class, args);
		System.out.println("hello world");



		try {
			//test();
		} catch (Exception e) {
			//TODO: handle exception
			System.out.println(e);
		}
		
	}


	public static void jdbc() throws ClassNotFoundException, SQLException {
		String className="oracle.jdbc.driver.OracleDriver";//oracle的className（ojdbc6.jar）

		String url="jdbc:oracle:thin:@192.168.191.160:1521:jxdb4g";//oracle的url（ojdbc6.jar）

		//String className="com.microsoft.sqlserver.jdbc.SQLServerDriver";//sqlServer的className（sqljdbc.jar）

		//String url="jdbc:sqlserver://localhost:1433;databasename=数据库名";//sqlServer的url（sqljdbc.jar）

		Class.forName(className);//导入驱动类

		Connection conn= DriverManager.getConnection(url, "dfo_3304", "wellcom");// 创建数据库连接对象，三个参数分别为:url,user,password
		Statement st=conn.createStatement();
		ResultSet rs = st.executeQuery("select * from gen_studentinfo");
		ResultSetMetaData data = rs.getMetaData();
		int columnsNumber = data.getColumnCount();
		System.out.println("共查得数量为:"+columnsNumber);
		while (rs.next()) {
    		for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				String columnValue = rs.getString(i);
				System.out.print(columnValue + " " + data.getColumnName(i));
    		}
    		System.out.println("");
		}
		rs.next();
		st.close();
		conn.close();
		rs.close();
		//System.out.println(rs.getString(1));
		
	}

}
