package com.wellcom.jppt.system.common.base;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import lombok.Data;
import lombok.ToString;
/**
 * 分页数据实体
 * @author mldong
 *
 * @param <T>
 */
@Data
@ToString
public class CommonPageRelsult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8297224397945376979L;
	/**
	 * 每几页
	 */
	private int pageNum;
	/**
	 * 每页大小
	 */
    private int pageSize;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 数据集合
     */
    private List<T> rows;
    /**
     * 将mybatis分页插件实体转成通用分页数据实体
     * @param page
     * @return
     */
    public static <T> CommonPageRelsult<T> toPage(PageInfo<T> page) {
        CommonPageRelsult<T> result = new CommonPageRelsult<T>();
        result.setPages(page.getPages());
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        result.setTotal(page.getTotal());
        result.setRows(page.getList());
        return result;
    }
	// 省略 get set    
}
