package com.ly.sys.srv;

import org.nutz.ioc.loader.annotation.IocBean;
import com.ly.comm.BaseSrv;
import com.ly.comm.Page;
import com.ly.sys.vo.Product;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;

@IocBean(name="productSrv",fields = { "dao" })
public class ProductSrv extends BaseSrv<Product>
{
    public List<Product> queryProducts(Page p)
    {
        String cacheName = "product_page_"+p.getPageNum();
        CacheManager manager = CacheManager.getInstance();
        Cache cache = manager.getCache("product");
        Element element = cache.get(cacheName);
        if(element == null)
        {
            List<Product> product_list = this.query(Cnd.wrap("productid >0  order by ordernew"), p);
            cache.put(new Element(cacheName, product_list));
            return product_list;
        }else{
            return (List<Product>)element.getValue();
        }
    }
    
    
    public List<Product> querySaleProducts(Page p)
    {
        String cacheName = "sale_product_page_"+p.getPageNum();
        CacheManager manager = CacheManager.getInstance();
        Cache cache = manager.getCache("product");
        Element element = cache.get(cacheName);
        if(element == null)
        {
            List<Product> product_list = this.query(Cnd.wrap("productid >0  order by ordernum"), p);
            cache.put(new Element(cacheName, product_list));
            return product_list;
        }else{
            return (List<Product>)element.getValue();
        }
    }

	
}

