package com.ly.sys.vo;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;

@Table("product")
public class Product{
    
    	@Id
	
	@Column
	private Long  productid;

	
	@Column
	private String  num_iid;	

	@Column
	private String  url;	

        
	@Column
	private String  pic_url;	

	
	@Column
	private String  price;	

	
	@Column
	private String  title;	

	
	@Column
	private String  nick;	

	
	@Column
	private Long  num;	

	
	@Column
	private Long  cid;	
        

        public String getNum_iid() {
            return num_iid;
        }

        public void setNum_iid(String num_iid) {
            this.num_iid = num_iid;
        }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
        
        

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }



	public String getPrice() {
		return price;
	}

	public void setPrice(String  price) {
		this.price = price;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String  title) {
		this.title = title;
	}


	public String getNick() {
		return nick;
	}

	public void setNick(String  nick) {
		this.nick = nick;
	}


	public Long getNum() {
		return num;
	}

	public void setNum(Long  num) {
		this.num = num;
	}


	public Long getCid() {
		return cid;
	}

	public void setCid(Long  cid) {
		this.cid = cid;
	}


	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long  productid) {
		this.productid = productid;
	}

}

