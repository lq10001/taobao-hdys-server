package  com.ly.taobao.service;

import com.ly.taobao.vo.Product;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.nutz.dao.Cnd;
import com.ly.comm.Page;

import java.util.List;


@IocBean(fields = { "dao" })
public class ProductService extends IdEntityService<Product> {

	public static String CACHE_NAME = "product";
    public static String CACHE_COUNT_KEY = "product_count";

    public List<Product> queryCache(Condition c,Page p)
    {
        List<Product> list_product = null;
        String cacheKey = "product_list_" + p.getPageCurrent();

        Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
        if(cache.get(cacheKey) == null)
        {
            list_product = this.query(c, p);
            cache.put(new Element(cacheKey, list_product));
        }else{
            list_product = (List<Product>)cache.get(cacheKey).getObjectValue();
        }
        return list_product;
    }

    public int listCount(Condition c)
    {
        Long num = 0L;
        Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
        if(cache.get(CACHE_COUNT_KEY) == null)
        {
            num = Long.valueOf(this.count(c));
            cache.put(new Element(CACHE_COUNT_KEY, num));
        }else{
            num = (Long)cache.get(CACHE_COUNT_KEY).getObjectValue();
        }
        return num.intValue();
    }

    public List<Product> querySaleProducts(Page p)
    {
        String cacheName = "sale_product_page_"+p.getPageNumber();
        CacheManager manager = CacheManager.getInstance();
        Cache cache = manager.getCache("product");
        Element element = cache.get(cacheName);
        if(element == null)
        {
            List<Product> product_list = this.query(Cnd.wrap("id >0  order by ordernum"), p);
            cache.put(new Element(cacheName, product_list));
            return product_list;
        }else{
            return (List<Product>)element.getValue();
        }
    }



}


