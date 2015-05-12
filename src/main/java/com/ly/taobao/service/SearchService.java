package  com.ly.taobao.service;

import com.ly.taobao.vo.Search;
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
public class SearchService extends IdEntityService<Search> {

	public static String CACHE_NAME = "search";
    public static String CACHE_COUNT_KEY = "search_count";

    public List<Search> queryCache(Condition c,Page p)
    {
        List<Search> list_search = null;
        String cacheKey = "search_list_" + p.getPageCurrent();

        Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
        if(cache.get(cacheKey) == null)
        {
            list_search = this.query(c, p);
            cache.put(new Element(cacheKey, list_search));
        }else{
            list_search = (List<Search>)cache.get(cacheKey).getObjectValue();
        }
        return list_search;
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



}


