<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<div class="pageContent">
	<form method="post" action="<%=path%>/sys/menu/save" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" id="menuid" name="menuid" value="${menu.menuid}">
		
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>名称</label>
				<input name="menuname" class="required" type="text" size="30" value="${menu.menuname}" alt=""/>
			</p>
			<p>
				<label>URL</label>
				<input name="url" class="required" type="text" size="30" value="${menu.url}" alt=""/>
			</p>
			<p>
				<label>父菜单</label>
				<input name="pmenuname" class="required" type="text" size="30" value="${menu.pmenuname}" alt=""/>				
			</p>
			<p>
				<label>使用否：</label>		
				<select name="state" class="combox">
						<option value="1" <c:if test="${menu.state==1}"> selected="selected"</c:if>>
							使用
						</option>
						<option value="2"
							<c:if test="${menu.state==2}"> selected="selected"</c:if>>
							不使用
						</option>
					</select>
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
