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
import net.sf.ehcache.CacheManager;
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
