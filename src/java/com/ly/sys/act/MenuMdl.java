package com.ly.sys.act;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.view.JspView;

import com.ly.comm.CommAction;
import com.ly.comm.PKID;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;
import com.ly.sys.srv.EmployeeSrv;
import com.ly.sys.srv.MenuSrv;
import com.ly.sys.vo.Employee;
import com.ly.sys.vo.Menu;
import com.ly.util.EnDeCode;

@IocBean
@InjectName("menuMdl1")
@At("/sys/menu")
@Fail("json")
public class MenuMdl extends CommAction{
	
	private static final Log log = Logs.getLog(MenuMdl.class);

	@Inject
	private Dao dao;
	
	@Inject
	private MenuSrv menuSrv;
	@Inject
	private EmployeeSrv employeeSrv;
	
	
	@At
	@Ok("jsp:/WEB-INF/index.jsp")
	public  View gologin()
	{		
		return new JspView("/WEB-INF/login.jsp");
	}

	
	@At
	@Ok("jsp:index")
	public  View login(HttpServletRequest request,HttpSession session,
			@Param("username")String username,
			@Param("password")String password,
			@Param("yz")String yz)
	{
		String kaptchaExpected = (String)request.getSession().getAttribute (com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (!yz.trim().equals(kaptchaExpected)) {
			request.setAttribute("err", "验证码错误！请从新输入！");
			return new JspView("/WEB-INF/login.jsp");
		}
		EnDeCode en = new EnDeCode();
		Employee e1 = employeeSrv.queryObj(Cnd.where("employeeno","=",username).and("password","=",en.getMD5Str(password.trim())));
		if (e1 == null) {
			request.setAttribute("err", "用户名或密码错误！请从新输入！");
			log.info("用户名和密码错误");
			return new JspView("/WEB-INF/login.jsp");
		}
		
		request.setAttribute("username", username);
		session.setAttribute("username", username);
		
		return new JspView("/WEB-INF/index.jsp");
	}
	
	@At
	@Ok("jsp:/WEB-INF/index.jsp")
	@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.jsp"}))
	public void index(HttpServletRequest request){
		//查询菜单
		List<Menu> list_m = menuSrv.queryObjs(Cnd.wrap("state=1 order by menuno"));
		List<Menu> list_pm = new LinkedList<Menu>();
		for(Menu m:list_m){
			if( m.getPmenuid().equals("0")){
				list_pm.add(m);
			}
		}
		request.setAttribute("list_m", list_m);
		request.setAttribute("list_pm", list_pm);
	}

	/*
	@At
	@Ok("jsp:/page/sys/menu_list.jsp")
	public void list(HttpServletRequest request) {
//		List<Menu> list_m = menuSrv.queryObjs(Cnd.wrap("state=1 order by menuno"));
//		String mtree = ObjTree.getMenuTree(list_m);
//		request.setAttribute("menu_tree", getMTree());
		
	}
	*/
	
	@At
	@Ok("jsp:page.sys.menu_list")
	@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.jsp"}))

	public void list(@Param("..") Page p,HttpServletRequest request,@Param("..")Menu menu) {
		menu.setState(1L);
		Cnd c = new ParseObj(menu).getCnd();
		List<Menu> menu_list = dao.query(Menu.class, c, p);
		p.setRecordCount(dao.count(Menu.class, c));
		request.setAttribute("menu", menu);
		request.setAttribute("menu_list", menu_list);
		request.setAttribute("page", p);
	}

	@At
	@Ok("jsp:/WEB-INF/page/sys/menu.jsp")
	@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.jsp"}))

	public void edit(@Param("menuid")String menuid,HttpServletRequest request) {
		log.debug(menuid);
//		request.setAttribute("dept_tree", getDeptTree());
		if(menuid == null || menuid.equals("")){
			request.setAttribute("menu", null);
		}else{
			Menu m  = menuSrv.queryObj(menuid);
			request.setAttribute("menu", m);
		}
	}

	
	@At
	@Ok("json")
	@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.jsp"}))

	public Map<String, String> save(@Param("..")Menu m){
		Object rtnObject;
		if(m.getMenuid().equals("")){
			m.setMenuid(PKID.getId());
			rtnObject =  this.menuSrv.insert(m);
		}else{
			rtnObject =  this.menuSrv.update(m);
		}
		return super.tabMap((rtnObject != null)?"200":"300", "menu","closeCurrent");
	}
	
	
	
	@At
	@Ok("json")
	@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.jsp"}))

	public Map<String, String> del(@Param("menuid")String mid){
		Menu m  = dao.fetch(Menu.class, mid);
		m.setState(3L);
		Object rtnObj =  dao.update(m);
		return super.tabMap((rtnObj != null)?"200":"300", "menu","");
	}
}
