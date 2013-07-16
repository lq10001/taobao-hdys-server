package com.ly.sys.srv;

import org.nutz.ioc.loader.annotation.IocBean;

import com.ly.comm.BaseSrv;
import com.ly.sys.vo.Shop;

@IocBean(name="shopSrv",fields = { "dao" })
public class ShopSrv extends BaseSrv<Shop>
{
	
}

