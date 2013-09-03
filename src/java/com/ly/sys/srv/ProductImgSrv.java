package com.ly.sys.srv;

import org.nutz.ioc.loader.annotation.IocBean;
import com.ly.comm.BaseSrv;
import com.ly.comm.Page;
import com.ly.sys.vo.Product;
import com.ly.sys.vo.ProductImg;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.nutz.dao.Cnd;

@IocBean(name="productImgSrv",fields = { "dao" })
public class ProductImgSrv extends BaseSrv<ProductImg>
{
    public List<ProductImg> queryProductImgs(Long productId)
    {
        String cacheName = "productImg_"+productId;
        CacheManager manager = CacheManager.getInstance();
        Cache cache = manager.getCache("productimg");
        Element element = cache.get(cacheName);
        if(element == null)
        {
           List<ProductImg> list = this.queryObjs(Cnd.wrap("productid = " + productId));
            cache.put(new Element(cacheName, list));
            return list;
        }else{
            return (List<ProductImg>)element.getValue();
        }
    }
	
}

