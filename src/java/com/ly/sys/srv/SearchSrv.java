package com.ly.sys.srv;

import com.ly.comm.BaseSrv;
import com.ly.sys.vo.Search;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(name="searchSrv",fields = { "dao" })
public class SearchSrv extends BaseSrv<Search>
{
    public List<Search> querySearchs()
    {
        String cacheName = "searchs";
        CacheManager manager = CacheManager.getInstance();
        Cache cache = manager.getCache("search");
        Element element = cache.get(cacheName);
        if(element == null)
        {
           List<Search> list = this.queryObjs();
            cache.put(new Element(cacheName, list));
            return list;
        }else{
            return (List<Search>)element.getValue();
        }
    }
	
}

