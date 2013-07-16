<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>

<div class="pageContent">
	<form method="post" action="<%=path%>/note/save" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" id="noteid" name="noteid" value="${note.noteid}">
		
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>日期</label>
				<c:choose>
					<c:when test="${note.noteid > 0}">
						<input type="text" id="day" name="day"  size="30"  readonly="readonly"
				 			value="<fmt:formatDate value="${note.day}" pattern="yyyy-MM-dd" />"/>
					</c:when>
					<c:otherwise>
						<input type="text" id="day" name="day"  size="30" class="date"
				 	value="<fmt:formatDate value="${note.day}" pattern="yyyy-MM-dd" />"/>
				 	<a class="inputDateButton" href="javascript:;">选择</a>
					</c:otherwise>
				</c:choose>
			</p>
			<div class="unit">
				<label>笔记信息</label>
				<textarea class="editor" name="info"  rows="20" cols="100">${note.info}</textarea>
			</div>
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
