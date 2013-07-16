package com.ly.sys.act;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import com.ly.comm.CommAction;
import com.ly.comm.Page;

import com.ly.sys.vo.Shop;
import com.ly.sys.srv.ShopSrv;
import com.ly.sys.srv.ProductImgSrv;
import com.ly.sys.srv.ProductSrv;
import com.ly.sys.srv.SearchSrv;
import com.ly.sys.vo.Product;
import com.ly.sys.vo.ProductImg;
import com.ly.sys.vo.Search;
import java.util.HashMap;
import org.nutz.dao.Cnd;
import org.nutz.mvc.annotation.Param;


@IocBean
@InjectName("webAct")
@At("/web")
@Fail("json")
public class WebAct extends CommAction{
	
	private static final Log log = Logs.getLog(WebAct.class);

	@Inject
	private ShopSrv shopSrv;
        
        @Inject
        private SearchSrv searchSrv;
        
        @Inject
        private ProductSrv productSrv;
        
        @Inject
        private ProductImgSrv productImgSrv;

        @At
	@Ok("json")
	public Map shop_list() {
		List<Shop>  shop_list =  shopSrv.queryObjs();
                Map<Object, Object> map = new HashMap<Object, Object>();  
                map.put("ad_list", shop_list);
                return map;
	}
        
        @At
	@Ok("json")
	public Map search_list() {
		List<Search>  search_list =  searchSrv.queryObjs();
                Map<Object, Object> map = new HashMap<Object, Object>();  
                map.put("search_list", search_list);
                return map;
	}
        
        @At
	@Ok("json")
	public Map product_list(@Param("..") Page p) {
		List<Product>  product_list =  productSrv.query(Cnd.wrap("productid >0  order by productid desc"), p);
                Map<Object, Object> map = new HashMap<Object, Object>();  
                map.put("product_list", product_list);
                return map;
	}
        
        @At
	@Ok("json")
	public Map productimg_list(@Param("productid") Long productid) {
		List<ProductImg>  list =  productImgSrv.queryObjs(Cnd.wrap("productid = "+productid));
                Map<Object, Object> map = new HashMap<Object, Object>();  
                map.put("productimg_list", list);
                return map;
	}
        
}

