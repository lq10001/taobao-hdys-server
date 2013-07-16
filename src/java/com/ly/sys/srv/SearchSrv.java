package com.ly.sys.srv;

import org.nutz.ioc.loader.annotation.IocBean;
import com.ly.comm.BaseSrv;
import com.ly.sys.vo.Search;

@IocBean(name="searchSrv",fields = { "dao" })
public class SearchSrv extends BaseSrv<Search>
{
	
}

