/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ly.comm;

import com.ly.sys.srv.ProductSrv;
import com.ly.sys.vo.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.nutz.dao.Cnd;

/**
 *
 * @author zw
 */
public class CacheData {
    
    private CacheData() {}
    //已经自行实例化 
    private static final CacheData single = new CacheData();
    //静态工厂方法 
    public static CacheData getInstance() {
        return single;
    }
    
    private Map newProductMap;
    private Map saleProductMap;
    

    public void init()
    {
        
        Page p = new Page();
        p.setNumPerPage(20);
        p.setPageNum(1);
        
        ProductSrv productSrv = AppContext.getBean(ProductSrv.class);
        //new
        List<Product>  product_list =  productSrv.query(Cnd.wrap("productid >0  order by productid desc"), p);
        
        Map<Object, Object> map = new HashMap<Object, Object>();  
        map.put("product_list", product_list);
        this.newProductMap = map;
        
        //sale
        List<Product>  sale_product_list =  productSrv.query(Cnd.wrap("productid >0  order by ordernum"), p);
        
        Map<Object, Object> map2 = new HashMap<Object, Object>();  
        map2.put("product_list", sale_product_list);
        this.saleProductMap = map2;
        
        
    }

    public Map getNewProductMap() {
        return newProductMap;
    }

    public void setNewProductMap(Map newProductMap) {
        this.newProductMap = newProductMap;
    }

    public Map getSaleProductMap() {
        return saleProductMap;
    }

    public void setSaleProductMap(Map saleProductMap) {
        this.saleProductMap = saleProductMap;
    }


    
    
}
