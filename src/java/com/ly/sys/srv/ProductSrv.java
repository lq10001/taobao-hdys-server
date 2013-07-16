package com.ly.sys.srv;

import org.nutz.ioc.loader.annotation.IocBean;
import com.ly.comm.BaseSrv;
import com.ly.sys.vo.Product;

@IocBean(name="productSrv",fields = { "dao" })
public class ProductSrv extends BaseSrv<Product>
{
	
}

