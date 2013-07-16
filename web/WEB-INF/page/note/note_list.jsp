<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>


<form id="pagerForm" method="post" action="<%=path %>/note/list">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path %>/note/list" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
					<td>
					日期: 
					<input type="text" id="day" name="day"  size="30" class="date"
				 	value="<fmt:formatDate value="${note.day}" pattern="yyyy-MM-dd" />"/>
				 	</td>
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</td>
			</tr>
		</table>
		<div class="subBar">
		</div>
		
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="<%=path %>/note/edit?" target="navTab"><span>添加</span></a></li>
			<!-- 
			<li><a class="delete" href="<%=path %>/note/del?noteid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			 -->
			<li><a class="edit" href="<%=path %>/note/edit?noteid={sid_user}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="list" width="100%"  layoutH="138" nowrapTD="false">
		<thead>
			<tr>
				<th width="120">日期</th>
				<th>信息</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="note" items="${note_list}">
				<tr target="sid_user" rel="${note.noteid}" height="120">
					<td>
						<fmt:formatDate value="${note.day}" pattern="yyyy-MM-dd" />
					</td>
					<td height="120">
						<div width="100%" height="100">${note.info}</div>
						
						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="40">40</option>
				<option value="50">50</option>
			</select>
			<span>条，共${page.recordCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${page.recordCount}" numPerPage="20" pageNumShown="5" currentPage="${page.pageNumber}"></div>

	</div>
</div>
