<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<form id="pagerForm" method="post" action="<%=path %>/sys/shop/list">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path %>sys/shop/list" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
														
				<td>名称：<input type="text"  id="Name" name="Name" value=""/></td>
																											
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
			<li><a class="add" href="<%=path %>/sys/shop/edit.do?" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="<%=path %>/sys/shop/del.do?shopid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="<%=path %>/sys/shop/edit.do?shopid={sid_user}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>						
				<th>名称</th>
									
				<th>图片地址</th>
									
				<th>商店地址</th>
										
			</tr>
		</thead>
		<tbody>
			<c:forEach var="shop" items="${shop_list}">
				<tr target="sid_user" rel="${shop.shopid}">
                                    <td>
					${shop.name}
                                    </td>
                                    <td>
					${shop.imgurl}
                                    </td>
                                    <td>
					${shop.shopurl}
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
