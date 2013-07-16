<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<div class="pageContent">
	<form method="post" action="<%=path%>/sys/global/save" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" id="globalid" name="globalid" value="${global.globalid}">
		
		<div class="pageFormContent" layoutH="56">
                    <p>
                        <div>淘宝应用会话会保存一年时间，除非特殊情况，否则不要随便更改！</div>
                    </p>
                    
                     <div class="divider"></div>
                    
			<p>
				<label>淘宝应用Key</label>
                                <label>${global.appkey}</label>
			</p>
                        
                        <div class="divider"></div>
         
			<p>
				<label>淘宝应用Secret</label>
                                <label>${global.appsecret}</label>
			</p>
                        
                        <div class="divider"></div>
                        
			<p>
				<label>淘宝应用会话</label>
				<input name="sessionkey" class="required" type="text" size="100" value="${global.sessionkey}" alt=""/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
