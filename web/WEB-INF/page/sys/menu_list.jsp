<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>


<form id="pagerForm" method="post" action="<%=path %>/sys/menu/list">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path %>/sys/menu/list" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
					<td>名称: <input type="text"  id="menuname" name="menuname" value="${menu.menuname}"/></td>
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
			<li><a class="add" href="<%=path %>/sys/menu/edit?" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="<%=path %>/sys/menu/del?menuid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="<%=path %>/sys/menu/edit?menuid={sid_user}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th>编号</th>
				<th>名称</th>
				<th>URL</th>
				<th>父菜单</th>
				<th>使用否</th>		
			</tr>
		</thead>
		<tbody>
			<c:forEach var="menu" items="${menu_list}">
				<tr target="sid_user" rel="${menu.menuid}">
					<td>
						${menu.menuid}
					</td>
					<td>
						${menu.menuname}
					</td>
					<td>
						${menu.url}
					</td>
					<td>
						${menu.pmenuname}
					</td>
					<td>
						<c:choose>
							<c:when test="${menu.state==1}">
								使用
							</c:when>
							<c:otherwise>
								未使用
							</c:otherwise>
						</c:choose>
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
