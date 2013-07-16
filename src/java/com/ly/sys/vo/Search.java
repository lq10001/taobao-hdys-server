package com.ly.sys.vo;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;

@Table("search")
public class Search{

	
	@Id
	@Column
	private Integer  searchid;	

	
	@Column
	private String  name;	

	
	@Column
	private Integer  sort;	

	
	@Column
	private String  url;	

	
	public Integer getSearchid() {
		return searchid;
	}

	public void setSearchid(Integer  searchid) {
		this.searchid = searchid;
	}


	public String getName() {
		return name;
	}

	public void setName(String  name) {
		this.name = name;
	}


	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer  sort) {
		this.sort = sort;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String  url) {
		this.url = url;
	}

}

