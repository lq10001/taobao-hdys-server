package com.ly.comm;

import org.nutz.ioc.Ioc;

public class AppContext {
    
    public static String TAOBAO_URL = "http://gw.api.tbsandbox.com/router/rest";
    
    
    
	
	public static Ioc ioc;

	public static <T> T getBean(Class<T> type) {
		if (ioc != null) {
			return ioc.get(type);
		}
		return null;
	}

}
