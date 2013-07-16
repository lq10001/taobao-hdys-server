package com.ly.sys.vo;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;

@Table("productImg")
public class ProductImg{


    @Id
    @Column
    private Long  productimgId;	


    @Column
    private Long  productId;	

    @Column
    private String  imgurl;	

    public Long getProductimgId() {
        return productimgId;
    }

    public void setProductimgId(Long productimgId) {
        this.productimgId = productimgId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
        


}

