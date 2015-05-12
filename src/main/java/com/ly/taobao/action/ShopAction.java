package com.ly.taobao.action;

import com.ly.comm.Bjui;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.CacheManager;


import com.ly.taobao.vo.Shop;
import com.ly.taobao.service.ShopService;


@IocBean
@At("/shop")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class ShopAction {

	private static final Log log = Logs.getLog(ShopAction.class);
	
	@Inject
	private ShopService shopService;

    @At("/")
    @Ok("beetl:/WEB-INF/taobao/shop_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")Shop shop,
                      HttpServletRequest request){

        Cnd c = new ParseObj(shop).getCnd();
        if (c == null || c.equals(""))
        {
            p.setRecordCount(shopService.listCount(c));
            request.setAttribute("list_obj", shopService.queryCache(c,p));
        }else{
            p.setRecordCount(shopService.count(c));
            request.setAttribute("list_obj", shopService.query(c,p));
        }

        request.setAttribute("page", p);
        request.setAttribute("shop", shop);
    }

    @At
    @Ok("beetl:/WEB-INF/taobao/shop.html")
    public void edit(@Param("action")int action,
                     @Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("shop", null);
        }else{

            Shop shop = shopService.fetch(id);

            request.setAttribute("shop", shop);
        }
        request.setAttribute("action", action);
    }

    @At
    @Ok("json")
    public Map<String,String> save(@Param("action")int action,
                                @Param("..")Shop shop){
        Object rtnObject;
        if (shop.getId() == null || shop.getId() == 0) {
            rtnObject = shopService.dao().insert(shop);
        }else{
            if (action == 3) {
                shop.setId(null);
                rtnObject = shopService.dao().insert(shop);
            }else{
                rtnObject = shopService.dao().updateIgnoreNull(shop);
            }
        }
        CacheManager.getInstance().getCache(ShopService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((rtnObject == null) ? false : true, "tab_shop", true);

    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  shopService.delete(id);
        CacheManager.getInstance().getCache(ShopService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((num > 0) ? true : false , "tab_shop",false);
    }

}
