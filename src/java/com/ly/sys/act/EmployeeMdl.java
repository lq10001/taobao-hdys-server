package com.ly.sys.act;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.ly.comm.CommAction;
import com.ly.comm.PKID;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;
import com.ly.sys.srv.EmployeeSrv;
import com.ly.sys.srv.GlobalSrv;
import com.ly.sys.vo.Employee;
import com.ly.util.EnDeCode;

@IocBean
@InjectName("employeeMdl1")
@At("/sys/employee")
@Fail("json")
@Filters(
        @By(type = CheckSession.class, args = {"username", "/WEB-INF/login.jsp"}))
public class EmployeeMdl extends CommAction {

    private static final Log log = Logs.get();
    @Inject
    private Dao dao;
    @Inject
    private EmployeeSrv employeeSrv;
    
    @Inject
    private GlobalSrv globalSrv;

    @At
    @Ok("jsp:/WEB-INF/page/sys/employee_list.jsp")
    public void list(@Param("..") Page p, HttpServletRequest request, @Param("..") Employee employee) {

        employee.setState(1L);
        Cnd c = new ParseObj(employee).getCnd();
        List<Employee> employee_list = employeeSrv.query(c, p);//  dao.query(Employee.class, c, p);
        p.setRecordCount(employeeSrv.cnt(c));


        request.setAttribute("employee", employee);
        request.setAttribute("employee_list", employee_list);
        request.setAttribute("page", p);
    }
    
    @At
    public void productlist( HttpServletRequest request) {
    }
    
    @At
    @Ok("jsp:/WEB-INF/page/sys/employee.jsp")
    public void edit(@Param("employeeid") String employeeid, HttpServletRequest request) {
        if (employeeid == null || employeeid.equals("")) {
            request.setAttribute("employee", null);
        } else {
            Employee employee = dao.fetch(Employee.class, employeeid);
            request.setAttribute("employee", employee);
        }
    }

    @At
    @Ok("json")
    public Map<String, String> save(@Param("..") Employee employee) {
        Object rtnObj;
        if (employee.getEmployeeid() == null || employee.getEmployeeid().equals("")) {
            employee.setEmployeeid(PKID.getId());
            EnDeCode en = new EnDeCode();
            employee.setPassword(en.getMD5Str("user123"));
            rtnObj = dao.insert(employee);
        } else {
            rtnObj = dao.update(employee);
        }
        return super.tabMap((rtnObj != null) ? "200" : "300", "employee", "closeCurrent");
    }

    @At
    @Ok("json")
    public Map<String, String> del(@Param("employeeid") String employeeid) {
        Object rtnObj;
        Employee employee = dao.fetch(Employee.class, employeeid);
        employee.setState(3L);
        rtnObj = dao.update(employee);
        return super.tabMap((rtnObj != null) ? "200" : "300", "employee", "");
    }
}
