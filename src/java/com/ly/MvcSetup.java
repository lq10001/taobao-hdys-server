package com.ly;

import com.ly.comm.AppContext;
import com.ly.comm.CacheData;
import net.sf.ehcache.CacheManager;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

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
                CacheManager manager =  CacheManager.create();
                System.out.println("-------------------- " +manager);
                for (String str1 : manager.getCacheNames()) {
                    System.out.println("=========== " + str1);
                
            }
                
	}

}
