<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="../../top.jsp"></jsp:include>
<html>
<body>
	<div class="position">
		<span>
			<c:choose>
				<c:when test="${hospital==null}">
	   				<b>当前位置：医院管理 >> 添加医院</b>
	   		</c:when>
				<c:otherwise>
	   				<b>当前位置：医院管理 >> 修改医院</b>
	   		</c:otherwise>
			</c:choose>
		</span>
	</div>
	
	<form id="form1" action="<%=path %>/sys/hospital/save.do" method="POST" onsubmit="return save()">
		<input type="hidden" id="ac" name="ac" value="${ac}">
		<input type="hidden" id="hospitalid" name="hospitalid"
			value="${hospital.hospitalid}">
		<table width="100%" class="bg">
			<tr>
				<td class="thWidth">
					医院编号
				</td>
				<td>
					<input type="text" id="hospitalname" name="hospitalno"
						value="${hospital.hospitalno}" class="width200 nn" maxlength="50" />
						<span style="color:red">*</span>
				</td>
			</tr>
			<tr>
				<td class="thWidth">
					医院名称
				</td>
				<td>
					<input type="text" id="hospitalname" name="hospitalname"
						value="${hospital.hospitalname}" class="width200 nn" maxlength="50" />
						<span style="color:red">*</span>
				</td>
			</tr>
			<tr>
				<td class="thWidth">
					别名
				</td>
				<td>
					<input type="text" id="code" name="code" value="${hospital.code}"
						class="width200" maxlength="100" />
				</td>
			</tr>
			<tr>
				<td class="thWidth">
					联系人
				</td>
				<td>
					<input type="text" id="linkman" name="linkman"
						value="${hospital.linkman}" class="width200" maxlength="100" />
				</td>
			</tr>
			<tr>
				<td class="thWidth">
					电话
				</td>
				<td>
					<input type="text" id="phone" name="phone"
						value="${hospital.hospitalname}" class="width200" maxlength="50" />
				</td>
			</tr>
			<tr>
				<td class="thWidth">
					使用否
				</td>
				<td>
					<select name="state" class="width200">
						<option value="1"
							<c:if test="${hospital.state==1}"> selected="selected"</c:if>>
							使用
						</option>
						<option value="2"
							<c:if test="${hospital.state==2}"> selected="selected"</c:if>>
							不使用
						</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="thWidth">
					地址
				</td>
				<td>
					<input type="text" id="addr" name="addr"
						value="${hospital.addr}" class="width200" maxlength="100" style="width: 400px;"/>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td>
					<c:if test="${ac!=1}">
						<input type="submit" id="btnAdd" value="保存" class="separatorBtn"/>
						<input type="reset" id="btnreset" value="取消" class="separatorBtn" />
					</c:if>
					<input type="button" id="btnReturn" value="返回" class="separatorBtn"
						onClick="list('<%=path %>/sys/hospital/list.do')" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
