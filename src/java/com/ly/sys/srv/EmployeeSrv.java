package com.ly.sys.srv;

import org.nutz.ioc.loader.annotation.IocBean;
import com.ly.comm.BaseSrv;
import com.ly.sys.vo.Employee;



@IocBean(name="employeeSrv",fields = { "dao" })
public class EmployeeSrv  extends BaseSrv<Employee> {
	

}

