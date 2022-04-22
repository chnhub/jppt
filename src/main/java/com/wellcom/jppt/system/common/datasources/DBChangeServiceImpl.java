package com.wellcom.jppt.system.common.datasources;

import com.wellcom.jppt.system.common.datasources.DBContextHolder;
import com.wellcom.jppt.system.common.datasources.DynamicDataSource;
import com.wellcom.jppt.system.common.datasources.DataSourceMapper;
import com.wellcom.jppt.system.common.datasources.DataSource;
import com.wellcom.jppt.system.common.datasources.DBChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
 
/**
 * @Author : JCccc
 * @CreateTime : 2019/10/22
 * @Description :
 **/
@Primary
@Service
public class DBChangeServiceImpl implements DBChangeService {
 
    @Autowired
    DataSourceMapper dataSourceMapper;
    @Autowired
    private DynamicDataSource dynamicDataSource;
    @Autowired
    GetDatainfo dataInfo;

    @Override
    public List<DataSource> get() {
        return dataSourceMapper.get();
    }
 
    @Override
    public boolean changeDb(String datasourceId) throws Exception {
 
        //默认切换到主数据源,进行整体资源的查找
        DBContextHolder.clearDataSource();
        //List<DataSource> dataSourcesList = dataSourceMapper.get();
        //加载查得的数据源，目前写死
        //目前两种解决办法，1. 配置文件 2. 数据库
        //List<DataSource> dataSourcesList = new ArrayList<DataSource>();
        //暂时这么写，不改变父类代码
        List<? extends DataSource> dataSourcesList = dataInfo.getDatasource();
 
        for (DataSource dataSource : dataSourcesList) {
            if (dataSource.getDatasourceId().equals(datasourceId)) {
                //System.out.println("需要使用的的数据源已经找到,datasourceId是：" + dataSource.getDatasourceId());
                System.out.println("需要使用的的数据源已经找到,datasourceId是：" + dataSource.getDatasourceId() + ",dataSourceName是：" + dataSource.getDataSourceName());
                //创建数据源连接&检查 若存在则不需重新创建
                dynamicDataSource.createDataSourceWithCheck(dataSource);
                //切换到该数据源
                DBContextHolder.setDataSource(dataSource.getDatasourceId());
                return true;
            }
        }
        return false;
 
    }

    @Override
    public void removeContextHolderValue() {
        DBContextHolder.clearDataSource();
    }
}