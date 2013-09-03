package com.ly.sys.srv;

import com.ly.comm.BaseSrv;
import com.ly.sys.vo.Shop;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(name="shopSrv",fields = { "dao" })
public class ShopSrv extends BaseSrv<Shop>
{
    public List<Shop> queryShops()
    {
        String cacheName = "shops";
        CacheManager manager = CacheManager.getInstance();
        Cache cache = manager.getCache("shop");
        Element element = cache.get(cacheName);
        if(element == null)
        {
           List<Shop> list = this.queryObjs();
            cache.put(new Element(cacheName, list));
            return list;
        }else{
            return (List<Shop>)element.getValue();
        }
    }
	
}

