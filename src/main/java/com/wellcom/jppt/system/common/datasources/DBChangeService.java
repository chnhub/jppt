package com.wellcom.jppt.system.common.datasources;

import com.wellcom.jppt.system.common.datasources.DataSource;

import java.util.List;
 
/**
 * @Author : JCccc
 * @CreateTime : 2019/10/22
 * @Description :
 **/

public interface DBChangeService {
 
    List<DataSource> get();
 
    boolean changeDb(String datasourceId) throws Exception;

    void removeContextHolderValue();
 
}