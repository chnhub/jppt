package com.wellcom.jppt.system.common.datasources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

import java.util.List;

import javax.servlet.http.Cookie;

@Data
@ToString
@Component
public class GetCookieDB {

    private Cookie[] cookies;
    @Autowired
    private GetDatainfo info2;

    String DBFromCookieStr = null;

    public String getDefaultDB(){
        if (cookies != null) {
            return this.getDefaultDB(this.cookies);
        }
        return null;
    }
    
    //从cookie获取数据库，并和配置文件对比是否存在
    public String getDefaultDB(Cookie[] cookies){
        //String DBFromCookieStr = null;
        DBFromCookieStr = this.getCookieDB(cookies);

        if (DBFromCookieStr == null || "".equals(DBFromCookieStr)) {
            List<DataBase> dblist = info2.getDatasource();
            for (DataBase db : dblist) {
                if (db.getDatasourceId() != null && db.getDatasourceId().indexOf("_default") != -1) {
                    DBFromCookieStr = db.getDatasourceId();
                }
            }

            /* 更高级别默认数据库配置，加入此功能失败
            if (info2.getDefaultDB() != null && !"".equals(DBFromCookieStr)) {
                DBFromCookieStr = info2.getDefaultDB();
            }
            */
        }
        return DBFromCookieStr;
    }
    //从cookie拿到默认数据库
    public String getCookieDB(Cookie[] cookies){
        //String DBFromCookieStr = null;
        if (cookies != null) {
            
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("datasourceId")) {
                    
                    DBFromCookieStr = cookie.getValue();
                    System.out.println("+++++++++++++++++++++++++ Cookie数据库：" + DBFromCookieStr);
                    //return DBFromCookieStr;
                }
            }
            //return null;
        }
       
        return DBFromCookieStr;
    }
    
}