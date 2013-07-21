package com.ly.sys.act;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ly.comm.CommAction;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;
import com.ly.sys.srv.GlobalSrv;
import com.ly.sys.srv.ProductImgSrv;
import com.ly.sys.srv.ProductSrv;
import com.ly.sys.vo.Product;
import com.ly.util.LoadTaobaoData;

@IocBean
@InjectName("productAct")
@At("/sys/product")
@Fail("json")
public class ProductAct extends CommAction {

    private static final Log log = Logs.getLog(ProductAct.class);
    @Inject
    private ProductSrv productSrv;
    @Inject
    private GlobalSrv globalSrv;
    @Inject
    private ProductImgSrv productImgSrv;

    @At
    @Ok("json")
    public Map<String, String> refresh(@Param("..") Page page, HttpServletRequest request) {
        int rtnNum = 0;
        return super.tabMap((rtnNum > 0) ? "200" : "300", "product", "");
    }

    @At
    @Ok("jsp:/WEB-INF/page/sys/product_list.jsp")
    public void list(@Param("..") Page p, HttpServletRequest request, @Param("..") Product product) {
        Cnd c = new ParseObj(product).getCnd();
        List<Product> product_list = productSrv.query(c, p);
        p.setRecordCount(productSrv.cnt(c));

        request.setAttribute("product", product);
        request.setAttribute("product_list", product_list);
        request.setAttribute("page", p);
    }

    @At
    @Ok("jsp:/WEB-INF/page/sys/product.jsp")
    public void edit(@Param("productid") Long productid, HttpServletRequest request) {
        if (productid == null || productid == 0) {
            request.setAttribute("product", null);
        } else {
            Product product = productSrv.queryObj(productid);
            request.setAttribute("product", product);
        }
    }

    @At
    @Ok("json")
    public Map<String, String> save(@Param("..") Product product) {
        Object rtnObj;
        if (product.getProductid() == null || product.getProductid() == 0) {
            rtnObj = productSrv.insert(product);
        } else {
            rtnObj = productSrv.update(product);
        }
        return super.tabMap((rtnObj != null) ? "200" : "300", "product", "closeCurrent");
    }

    @At
    @Ok("json")
    public Map<String, String> del(@Param("productId") Long productId) {
        Object rtnObj = productSrv.del(productId);
        return super.tabMap((rtnObj != null) ? "200" : "300", "product", "");
    }

    @At
    @Ok("json")
    public Map<String, String> auto() {
        
        LoadTaobaoData load = new LoadTaobaoData();
        new Thread(load).start();
        
        return super.tabMap("200", "product", "");
    }
}