package com.ly.sys.srv;

import org.nutz.ioc.loader.annotation.IocBean;
import com.ly.comm.BaseSrv;
import com.ly.sys.vo.ProductImg;

@IocBean(name="productImgSrv",fields = { "dao" })
public class ProductImgSrv extends BaseSrv<ProductImg>
{
	
}

