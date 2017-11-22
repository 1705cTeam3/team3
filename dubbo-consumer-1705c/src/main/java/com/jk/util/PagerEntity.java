package com.jk.util;

import java.io.Serializable;
import java.util.List;

public class PagerEntity<T> implements Serializable{

	private Integer cpage;
	private Integer pageSize;
	private Integer start;
	
	
	private Long total;
	private List<T> rows;
	
	
	
	
	public PagerEntity(Integer cpage, Integer pageSize) {
		super();
		if(cpage <= 0){
			cpage = 1;
		}
		if(pageSize <= 0){
			pageSize = 3;
		}
		this.cpage = cpage;
		this.pageSize = pageSize;
		this.start = (this.cpage - 1)* this.pageSize;
		
	}
	
	public PagerEntity() {
		super();
	}

	public Integer getCpage() {
		return cpage;
	}
	public void setCpage(Integer cpage) {
		this.cpage = cpage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
	
}
