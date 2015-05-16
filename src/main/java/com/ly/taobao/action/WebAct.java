package com.ly.taobao.action;

import com.ly.comm.CommAction;
import com.ly.comm.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ly.taobao.service.ProductService;
import com.ly.taobao.service.ProductimgService;
import com.ly.taobao.service.SearchService;
import com.ly.taobao.service.ShopService;
import com.ly.taobao.vo.Search;
import com.ly.taobao.vo.Shop;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.nutz.dao.Cnd;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@IocBean
@InjectName("webAct")
@At("/web")
@Fail("json")
public class WebAct extends CommAction {

    private static final Log log = Logs.getLog(WebAct.class);

    @Inject
    private ShopService shopService;
    @Inject
    private SearchService searchService;
    @Inject
    private ProductService productService;
    @Inject
    private ProductimgService productimgService;

    @At
    @Ok("json")
    public Map shop_list() {
        List<Shop> shop_list = shopService.queryCache(null,new Page());
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("ad_list", shop_list);
        return map;
    }

    @At
    @Ok("json")
    public Map search_list() {
        List<Search> search_list = searchService.queryCache(null,new Page());
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("search_list", search_list);
        return map;
    }

    @At
    @Ok("json")
    public Map product_list(@Param("..") Page p) {
        
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("product_list", productService.queryCache(null, p));
        return map;
    }

    @At
    @Ok("json")
    public Map productSale_list(@Param("..") Page p) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("product_list", productService.querySaleProducts(p));
        return map;
    }

    @At
    @Ok("json")
    public Map productimg_list(@Param("productid") Long productid) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("productimg_list", productimgService.queryCache(Cnd.where("productid","=",productid),new Page()));
        return map;
    }
}
