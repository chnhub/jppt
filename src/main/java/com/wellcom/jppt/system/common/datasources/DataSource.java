package com.wellcom.jppt.system.common.datasources;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DataSource {
    String datasourceId;
    String url;
    String userName;
    String passWord;
    String code;
    String areacode;
    String databasetype;
    String dataSourceName;
}