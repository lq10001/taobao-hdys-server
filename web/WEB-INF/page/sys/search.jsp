<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<div class="pageContent">
	<form method="post" action="<%=path%>/sys/search/save" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" id="searchid" name="searchid" value="${search.searchid}">
		
		<div class="pageFormContent" layoutH="56">

												
			<p>
				<label>名称</label>
				<input name="name" class="required" type="text" size="40" value="${search.name}" alt=""/>
			</p>
                        <div class="divider"></div>

			
                        <p>
				<label>搜索URL</label>
				<input name="url" class="required" type="text" size="40" value="${search.url}" alt=""/>
			</p>
			
                        <div class="divider"></div>

                        <p>
				<label>排序</label>
				<input name="sort" class="required" type="text" size="40" value="${search.sort}" alt=""/>
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
