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


import com.ly.taobao.vo.Search;
import com.ly.taobao.service.SearchService;


@IocBean
@At("/search")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class SearchAction {

	private static final Log log = Logs.getLog(SearchAction.class);
	
	@Inject
	private SearchService searchService;

    @At("/")
    @Ok("beetl:/WEB-INF/taobao/search_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")Search search,
                      HttpServletRequest request){

        Cnd c = new ParseObj(search).getCnd();
        if (c == null || c.equals(""))
        {
            p.setRecordCount(searchService.listCount(c));
            request.setAttribute("list_obj", searchService.queryCache(c,p));
        }else{
            p.setRecordCount(searchService.count(c));
            request.setAttribute("list_obj", searchService.query(c,p));
        }

        request.setAttribute("page", p);
        request.setAttribute("search", search);
    }

    @At
    @Ok("beetl:/WEB-INF/taobao/search.html")
    public void edit(@Param("action")int action,
                     @Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("search", null);
        }else{

            Search search = searchService.fetch(id);

            request.setAttribute("search", search);
        }
        request.setAttribute("action", action);
    }

    @At
    @Ok("json")
    public Map<String,String> save(@Param("action")int action,
                                @Param("..")Search search){
        Object rtnObject;
        if (search.getId() == null || search.getId() == 0) {
            rtnObject = searchService.dao().insert(search);
        }else{
            if (action == 3) {
                search.setId(null);
                rtnObject = searchService.dao().insert(search);
            }else{
                rtnObject = searchService.dao().updateIgnoreNull(search);
            }
        }
        CacheManager.getInstance().getCache(SearchService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((rtnObject == null) ? false : true, "tab_search", true);

    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  searchService.delete(id);
        CacheManager.getInstance().getCache(SearchService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((num > 0) ? true : false , "tab_search",false);
    }

}
