/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ly.sys.vo;

/**
 *
 * @author zw
 */
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("global")
public class Global {

    @Id
    @Column
    private Integer globalid;
    @Column
    private String sessionkey;
    @Column
    private String appkey;
    @Column
    private String appsecret;
    

    public Integer getGlobalid() {
        return globalid;
    }

    public void setGlobalid(Integer globalid) {
        this.globalid = globalid;
    }

    public String getSessionkey() {
        return sessionkey;
    }

    public void setSessionkey(String sessionkey) {
        this.sessionkey = sessionkey;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    
    
}
