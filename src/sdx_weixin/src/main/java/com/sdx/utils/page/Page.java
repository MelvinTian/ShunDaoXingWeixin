package com.sdx.utils.page;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
/**
 * 分页对象
 * 
 */

public class Page extends RowBounds {
	private int pageNo;
	private int pageSize;
	private String where; 
	private String orderBy;
	private boolean isAsc;
	private List<Map<String,Object>> results;
	private List<?> resultsList;
//	private Long totalPageCount;
	private Long totalCount;
	public Page(){}
	public Page(int pageNo, int pageSize, String where, String orderBy,
			boolean isAsc, List<Map<String, Object>> results,
			Long totalPageCount, Long totalCount) {
        super((pageNo - 1) * pageSize, pageSize);
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.where = where;
		this.orderBy = orderBy;
		this.isAsc = isAsc;
		this.results = results;
//		this.totalPageCount = totalPageCount;
		this.totalCount = totalCount;
		
	}
	public long getTotalPageCount() {
		return totalCount % pageSize == 0 ? totalCount / pageSize : totalCount
				/ pageSize + 1;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public boolean isAsc() {
		return isAsc;
	}
	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}
	public List<Map<String, Object>> getResults() {
		return results;
	}
	public void setResults(List<Map<String, Object>> results) {
		this.results = results;
	}
	public List<?> getResultsList() {
		return resultsList;
	}
	public void setResultsList(List<?> resultsList) {
		this.resultsList = resultsList;
	}
	
}
