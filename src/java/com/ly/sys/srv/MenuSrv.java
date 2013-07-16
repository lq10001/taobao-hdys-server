package com.ly.sys.srv;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import com.ly.comm.BaseSrv;
import com.ly.sys.vo.Menu;



@IocBean(name="menuSrv",fields = { "dao" })
public class MenuSrv  extends BaseSrv<Menu> {

	public List<Menu> queryMenuByHID(Cnd c) {
		String sqls ="select m.menuid,m.menuno,m.menuname,m.url,m.pmenuid,m.pmenuname,m.memo,m.menutype,m.state,hm.hospitalid as OID" +
				" from menu m left join (select * from hospitalmenu $condition) hm on m.menuid=hm.menuid order by menuno";
		return queryObjs(sqls, c);
	}

	public List<Menu> queryMenuByDID(Cnd c) {
		String sqls ="select m.menuid,m.menuno,m.menuname,m.url,m.pmenuid,m.pmenuname,m.memo,m.menutype,m.state,hm.deptid as OID" +
				" from menu m left join (select * from deptmenu $condition) hm on m.menuid=hm.menuid order by menuno";
		return queryObjs(sqls, c);
	}
	
	public List<Menu> queryMenuByEID(Cnd c) {
		String sqls ="select m.menuid,m.menuno,m.menuname,m.url,m.pmenuid,m.pmenuname,m.memo,m.menutype,m.state,hm.employeeid as OID" +
				" from menu m left join (select * from employeemenu $condition) hm on m.menuid=hm.menuid order by menuno";
		return queryObjs(sqls, c);
	}
}

