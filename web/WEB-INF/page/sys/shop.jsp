<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<div class="pageContent">
	<form method="post" action="<%=path%>/sys/shop/save" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" id="shopid" name="shopid" value="${shop.shopid}">
		
		<div class="pageFormContent" layoutH="56">

												
			<p>
				<label>店铺名称</label>
				<input name="name" class="required" type="text" size="40" value="${shop.name}" alt=""/>
			</p>
<div class="divider"></div>
									
			<p>
				<label>店铺图片地址</label>
				<input name="imgurl" class="required" type="text" size="40" value="${shop.imgurl}" alt=""/>
			</p>

				<div class="divider"></div>					
			<p>
				<label>店铺手机地址</label>
				<input name="shopurl" class="required" type="text" size="40" value="${shop.shopurl}" alt=""/>
			</p>

						
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
