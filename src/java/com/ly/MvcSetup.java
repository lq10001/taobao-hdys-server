package com.ly;

import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.ly.comm.AppContext;

public class MvcSetup implements Setup {

	//private static final Log log = Logs.getLog(MvcSetup.class);

	@Override
	public void destroy(NutConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(NutConfig config) {
		AppContext.ioc = config.getIoc();
		
		//初始化基础信息数据
//		CBData.getInstance().init();
	}

}
