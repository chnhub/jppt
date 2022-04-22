package com.wellcom.jppt.system.common.datasources;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
@Component
@PropertySource(value = "classpath:datasources.yml", factory = YamFactory.class)
@ConfigurationProperties(prefix = "wellcom") //接收application.yml中的myProps下面的属性
public class GetDatainfo {
    //@Value("${spring.datasource.url}")
    //String datasourceId;
    //@Value("${test.datasource.url}")
    // @Value("${test.database.datasourceId}")
    // private String datasourceId;
    //@Value("${database}")
    // private List<Map<String, String>> database;

    //@Value("${configs}")
    private List<DataBase> datasource;
    //private List<Map<String, String>> datasource;
    private List<String> list2;
    private String defaultDB;
    private String defaultDBSuffix = "_default";
    // @Value("${datasource.userName}")
    // String userName;
    // @Value("${datasource.passWord}")
    // String passWord;
    // @Value("${datasource.code}")
    // String code;
    // @Value("${datasource.databasetype}")
    // String databasetype;
    /**
     * InnerGetDatainfo
     */

    //增加一个级别更高的默认库配置defaultDB，默认为datasourceId_default，简直脱裤子放屁，但是我开心
    /* 直接导致数据库changeDb切换时找不到该数据库，因为初始化对象的时候就直接改成_default了，再改起来就太麻烦了
    public List<DataBase> getDatasource(){
        // foreach遍历不能操作具体元素

        for (int i = 0; i < this.datasource.size(); i++){
            if (this.defaultDB != null && !"".equals(this.defaultDB) ) {
                DataBase db = (DataBase)this.datasource.get(i);
                //去掉文件带_default的
                if (db.getDatasourceId().indexOf(defaultDBSuffix) != -1) {
                    db.setDatasourceId(db.getDatasourceId().substring(db.getDatasourceId().indexOf(defaultDBSuffix)));
                    this.datasource.set(i, db);
                }

                //如果配置文件中找到该库直接返回
                if (defaultDB.equals(db.getDatasourceId())) {
                    db.setDatasourceId(db.getDatasourceId()+defaultDBSuffix);
                    this.datasource.set(i, db);
                }
                
                
            }
        }
        return this.datasource;
    }
    */

}


//可直接使用DataSource类，但因为抄来的代码不知道改动有什么影响还是自己再建个子类赋值
@Data
class DataBase extends DataSource{
    private String datasourceId;
    private String url;
    private String userName;
    private String passWord;
    private String code;
    private String areacode;
    private String databasetype;
    private String dataSourceName;
    public void setAreacode(String temp){
        this.code = temp;
        this.areacode = temp;
    }
}