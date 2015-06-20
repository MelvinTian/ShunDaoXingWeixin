package com.sdx.utils.page;

import java.io.Serializable;
import java.util.List;
/***
 * 分页信息封装
 * @author 张昭
 *
 */
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 587754556498974978L;
    //总页数
    private int totalPage;
    //总记录数
    private int totalResult;
    //表示从当前记录开始查询,显示到的ID, 在mysql limit 中就是第一个参数.
    private int currentResult;
    private String sortField;
    private String order;
    private List<?> resultsList;//返回的数据
    private int rows;//每页显示数量
    private int page;//当前页码
    
    
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if (page <=1){
			page = 1;
		}
		this.page = page;
	}
	public List<?> getResultsList() {
		return resultsList;
	}
	public void setResultsList(List<?> resultsList) {
		this.resultsList = resultsList;
	}
	public int getTotalPage() {
		if (totalResult % rows == 0) {
			this.totalPage = totalResult / rows;
		} else {
			this.totalPage = (totalResult / rows) + 1;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentResult() {
		currentResult = (page-1) * rows;
		return currentResult;
	}
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
    
}
