<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>


<form id="pagerForm" method="post" action="<%=path %>/sys/employee/list">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path %>/sys/employee/list" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
					<td>姓名：<input type="text"  id="employeename" name="employeename" value="${employee.employeename}"/></td>
					<td>用户名：<input type="text"  id="employeeno" name="employeeno"/></td>
					<td>电话：<input type="text"  id="phone" name="phone" class="sn"/></td>
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
			<li><a class="add" href="<%=path %>/sys/employee/edit.do?" target="navTab"><span>添加</span></a></li>
			<li><a class="delete" href="<%=path %>/sys/employee/del.do?employeeid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="<%=path %>/sys/employee/edit.do?employeeid={sid_user}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th>员工编号</th>
				<th>姓名</th>
				<th>编码</th>
				<th>角色</th>
				<th>电话</th>		
			</tr>
		</thead>
		<tbody>
			<c:forEach var="employee" items="${employee_list}">
				<tr target="sid_user" rel="${employee.employeeid}">
					<td>
						${employee.employeeid}
					</td>
					<td>
						${employee.employeename}
					</td>
					<td>
						${employee.employeeno}
					</td>
					<td>
						${employee.deptname}
					</td>
					<td>
						${employee.phone}
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
