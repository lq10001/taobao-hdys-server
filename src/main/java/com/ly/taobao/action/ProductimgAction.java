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


import com.ly.taobao.vo.Productimg;
import com.ly.taobao.service.ProductimgService;


@IocBean
@At("/productimg")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class ProductimgAction {

	private static final Log log = Logs.getLog(ProductimgAction.class);
	
	@Inject
	private ProductimgService productimgService;

    @At("/")
    @Ok("beetl:/WEB-INF/taobao/productimg_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")Productimg productimg,
                      HttpServletRequest request){

        Cnd c = new ParseObj(productimg).getCnd();
        if (c == null || c.equals(""))
        {
            p.setRecordCount(productimgService.listCount(c));
            request.setAttribute("list_obj", productimgService.queryCache(c,p));
        }else{
            p.setRecordCount(productimgService.count(c));
            request.setAttribute("list_obj", productimgService.query(c,p));
        }

        request.setAttribute("page", p);
        request.setAttribute("productimg", productimg);
    }

    @At
    @Ok("beetl:/WEB-INF/taobao/productimg.html")
    public void edit(@Param("action")int action,
                     @Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("productimg", null);
        }else{

            Productimg productimg = productimgService.fetch(id);

            request.setAttribute("productimg", productimg);
        }
        request.setAttribute("action", action);
    }

    @At
    @Ok("json")
    public Map<String,String> save(@Param("action")int action,
                                @Param("..")Productimg productimg){
        Object rtnObject;
        if (productimg.getId() == null || productimg.getId() == 0) {
            rtnObject = productimgService.dao().insert(productimg);
        }else{
            if (action == 3) {
                productimg.setId(null);
                rtnObject = productimgService.dao().insert(productimg);
            }else{
                rtnObject = productimgService.dao().updateIgnoreNull(productimg);
            }
        }
        CacheManager.getInstance().getCache(ProductimgService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((rtnObject == null) ? false : true, "tab_productimg", true);

    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  productimgService.delete(id);
        CacheManager.getInstance().getCache(ProductimgService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((num > 0) ? true : false , "tab_productimg",false);
    }

}
