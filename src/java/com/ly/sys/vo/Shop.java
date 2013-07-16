package com.ly.sys.vo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("shop")
public class Shop{

	
	@Id
	
	@Column
	private Integer  shopid;	

	
	@Column
	private String  name;	

	
	@Column
	private String  imgurl;	

	
	@Column
	private String  shopurl;	

	
	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer  shopid) {
		this.shopid = shopid;
	}


	public String getName() {
		return name;
	}

	public void setName(String  name) {
		this.name = name;
	}


	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String  imgurl) {
		this.imgurl = imgurl;
	}

    public String getShopurl() {
        return shopurl;
    }

    public void setShopurl(String shopurl) {
        this.shopurl = shopurl;
    }

}

