<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<form id="pagerForm" method="post" action="<%=path %>/sys/product/list">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=path %>sys/product/list" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
							
				<td>产品编号：<input type="text"  id="num_iid" name="Num_Iid" value=""/></td>
																									
				<td>名称：<input type="text"  id="Title" name="Title" value=""/></td>
																																									
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
                        <li><a class="add" href="<%=path %>/sys/product/auto.do?" target="navTab"><span>手动获取数据</span></a></li>
                    	<li><a class="add" href="<%=path %>/sys/product/edit.do?" target="navTab"><span>添加</span></a></li>

			<li><a class="delete" href="<%=path %>/sys/product/del.do?productid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="<%=path %>/sys/product/edit.do?productid={sid_user}" target="navTab"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
						
				<th>产品编号</th>
                                
                                <th>名称</th>
																		
				<th>价格</th>
				
									
				<th>店铺名</th>
									
				<th>编号</th>
									
				<th>类别编号</th>
																
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${product_list}">
				<tr target="sid_user" rel="${product.productid}">
                                    <td>
					${product.num_iid}
                                    </td>
                                    
                                    <td>
					${product.title}
                                    </td>
           
                                    <td>
					${product.price}
                                    </td>
                                    
                                    <td>
					${product.nick}
                                    </td>
                                    <td>
					${product.num}
                                    </td>
                                    <td>
					${product.cid}
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
