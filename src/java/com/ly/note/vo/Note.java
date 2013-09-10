package com.ly.note.vo;

import java.util.Date;


import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("note")
public class Note {
	@Id
	@Column
	private Integer noteid;
	
	@Column
	private Date  day;
	
	@Column
	private String  info;

	public Integer getNoteid() {
		return noteid;
	}

	public void setNoteid(Integer noteid) {
		this.noteid = noteid;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	

}
