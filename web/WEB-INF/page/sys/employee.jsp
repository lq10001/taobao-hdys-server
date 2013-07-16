<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<div class="pageContent">
	<form method="post" action="<%=path%>/sys/employee/save" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" id="employeeid" name="employeeid" value="${employee.employeeid}">
		<input type="hidden" id="deptid" name="deptid" value="${employee.deptid}">
		
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>姓名</label>
				<input name="employeename" class="required" type="text" size="30" value="${employee.employeename}" alt=""/>
			</p>
			<p>
				<label>用户名</label>
				<input name="employeeno" class="required" type="text" size="30" value="${employee.employeeno}" alt=""/>
			</p>
			<p>
				<label>部分名称</label>
				<input name="deptname" class="required" type="text" size="30" value="${employee.deptname}" alt=""/>				
			</p>
			<p>
				<label>电话</label>
				<input name="phone" class="required" type="text" size="30" value="${employee.phone}" alt=""/>
			</p>
			<p>
				<label>使用否：</label>		
				<select name="state" class="combox">
						<option value="1" <c:if test="${employee.state==1}"> selected="selected"</c:if>>
							使用
						</option>
						<option value="2"
							<c:if test="${employee.state==2}"> selected="selected"</c:if>>
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
