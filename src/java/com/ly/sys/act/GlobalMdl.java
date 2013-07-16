package com.ly.sys.act;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.ly.comm.CommAction;
import com.ly.sys.srv.GlobalSrv;
import com.ly.sys.vo.Global;

@IocBean
@InjectName("globalMdl1")
@At("/sys/global")
@Fail("json")
@Filters(
     @By(type = CheckSession.class, args = {"username", "/WEB-INF/login.jsp"}))
public class GlobalMdl extends CommAction {

    private static final Log log = Logs.get();
    @Inject
    private Dao dao;
    
    @Inject
    private GlobalSrv globalSrv;

    @At
    @Ok("jsp:/WEB-INF/page/sys/global.jsp")
    public void edit(HttpServletRequest request) {
        Global global = globalSrv.queryObj(1L);
        request.setAttribute("global", global);
    }

    @At
    @Ok("json")
    public Map<String, String> save(@Param("..") Global global) {
        Object rtnObj = dao.updateIgnoreNull(global);
        return super.tabMap((rtnObj != null) ? "200" : "300", "global", "");
    }
}
