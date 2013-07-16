package com.ly.sys.act;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.ly.comm.CommAction;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;
import com.ly.sys.srv.SearchSrv;
import com.ly.sys.vo.Search;



@IocBean
@InjectName("searchAct")
@At("/sys/search")
@Fail("json")
public class SearchAct extends CommAction{
	
	private static final Log log = Logs.getLog(SearchAct.class);

	@Inject
	private SearchSrv searchSrv;

	@At
	@Ok("jsp:/WEB-INF/page/sys/search_list.jsp")
	public void list(@Param("..") Page p, HttpServletRequest request,@Param("..") Search  search) {
		Cnd c = new ParseObj(search).getCnd();
		List<Search>  search_list =  searchSrv.query(c, p);
		p.setRecordCount(searchSrv.cnt(c));
		
		request.setAttribute("search",  search);
		request.setAttribute("search_list", search_list);
		request.setAttribute("page", p);
	}

	@At
	@Ok("jsp:/WEB-INF/page/sys/search.jsp")
	public void edit(@Param("searchid")Long searchid,HttpServletRequest request) {
		if(searchid == null || searchid == 0){
			request.setAttribute("search", null);
		}else{
			Search search  = searchSrv.queryObj(searchid);
			request.setAttribute("search", search);
		}
	}

	@At
	@Ok("json")
	public Map<String,String> save(@Param("..")Search search){
        Object rtnObj;
		if(search.getSearchid() == null || search.getSearchid() == 0){
			rtnObj = searchSrv.insert(search);
		}else{
			rtnObj = searchSrv.update(search);
		}
		return super.tabMap((rtnObj != null)?"200":"300", "search","closeCurrent");
	}
	
	@At
	@Ok("json")
	public Map<String, String> del(@Param("searchId")Long searchId){
		Object rtnObj =  searchSrv.del(searchId);
		return super.tabMap((rtnObj != null)?"200":"300", "search","");
	}
}

