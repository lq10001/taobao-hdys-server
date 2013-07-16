package com.ly.sys.act;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import com.ly.comm.CommAction;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;

import com.ly.sys.vo.Shop;
import com.ly.sys.srv.ShopSrv;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.filter.CheckSession;


@IocBean
@InjectName("shopAct")
@At("/sys/shop")
@Fail("json")
@Filters(
     @By(type = CheckSession.class, args = {"username", "/WEB-INF/login.jsp"}))

public class ShopAct extends CommAction{
	
	private static final Log log = Logs.getLog(ShopAct.class);

	@Inject
	private ShopSrv shopSrv;

	@At
	@Ok("jsp:/WEB-INF/page/sys/shop_list.jsp")
	public void list(@Param("..") Page p, HttpServletRequest request,@Param("..") Shop  shop) {
		Cnd c = new ParseObj(shop).getCnd();
		List<Shop>  shop_list =  shopSrv.query(c, p);
		p.setRecordCount(shopSrv.cnt(c));
		
		request.setAttribute("shop",  shop);
		request.setAttribute("shop_list", shop_list);
		request.setAttribute("page", p);
	}
        
        /*
        @At
	@Ok("json")
	public Map list_json() {
		List<Ad>  shop_list =  shopSrv.queryObjs();
                Map<Object, Object> map = new HashMap<Object, Object>();  
                map.put("shoplist", shop_list);
                return map;
	}
        * */

	@At
	@Ok("jsp:/WEB-INF/page/sys/shop.jsp")
	public void edit(@Param("shopid")Long shopid,HttpServletRequest request) {
		if(shopid == null || shopid == 0){
			request.setAttribute("shop", null);
		}else{
			Shop shop  = shopSrv.queryObj(shopid);
			request.setAttribute("shop", shop);
		}
	}

	@At
	@Ok("json")
	public Map<String,String> save(@Param("..")Shop shop){
        Object rtnObj;
		if(shop.getShopid()==null || shop.getShopid() == 0){
			rtnObj = shopSrv.insert(shop);
		}else{
			rtnObj = shopSrv.update(shop);
		}
		return super.tabMap((rtnObj != null)?"200":"300", "shop","closeCurrent");
	}
	
	@At
	@Ok("json")
	public Map<String, String> del(@Param("shopId")Long shopId){
		Object rtnObj =  shopSrv.del(shopId);
		return super.tabMap((rtnObj != null)?"200":"300", "shop","");
	}
}

