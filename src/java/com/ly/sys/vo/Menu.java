package com.ly.sys.vo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("menu")
public class Menu {

	@Name
	@Column
	private String menuid;

	@Column
	private Long menuno;

	@Column
	private String menuname;

	@Column
	private String url;

	@Column
	private String pmenuid;

	@Column
	private String pmenuname;

	@Column
	private String memo;

	@Column
	private Long state;

	@Column
	private Long menutype;

	@Column
	private String oid;

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public Long getMenuno() {
		return menuno;
	}

	public void setMenuno(Long menuno) {
		this.menuno = menuno;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPmenuid() {
		return pmenuid;
	}

	public void setPmenuid(String pmenuid) {
		this.pmenuid = pmenuid;
	}

	public String getPmenuname() {
		return pmenuname;
	}

	public void setPmenuname(String pmenuname) {
		this.pmenuname = pmenuname;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Long getMenutype() {
		return menutype;
	}

	public void setMenutype(Long menutype) {
		this.menutype = menutype;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

}
