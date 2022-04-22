package com.wellcom.jppt.system.common.datasources;

public class DBContextHolder {
    // 对当前线程的操作-线程安全的
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
 
    // 调用此方法，切换数据源
    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
        //log.info("已切换到数据源:{}",dataSource);
        System.out.printf("已切换到数据源:{%s}",dataSource);
    }
 
    // 获取数据源
    public static String getDataSource() {

        return contextHolder.get();
    }
 
    /***
     *  note:这特么是个巨坑，因为线程复用，用之前最好清掉 不然contextHolder.get()会拿到脏数据
     */
    // 删除数据源
    public static void clearDataSource() {
        contextHolder.remove();
        //log.info("已切换到主数据源");
        System.out.println("已切换到主数据源");
    }
 
}
