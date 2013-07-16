<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="../../top.jsp"></jsp:include>
<html>
<body>
	<div class="position">
		<span><b>当前位置：医院管理 >> 医院列表</b></span>
	</div>
	
	<div class="btnBg">
       	<label><input type="button" value="查看" class="btnAll" id="btnShow" onclick="show('<%=path %>/sys/hospital/edit.do?ac=1&hospitalid=')" /></label>
       	<label><input type="button" value="添加" class="btnAll" onclick="add('<%=path %>/sys/hospital/edit.do?ac=2')"/></label>
        <label><input type="button" value="修改" class="btnAll" onclick="edit('<%=path %>/sys/hospital/edit.do?ac=3&hospitalid=')"/></label>
        <label><input type="button" value="设置权限" class="btnAll" onclick="edit('<%=path %>/sys/hospitalmenu/edit.do?hospitalid=')"/></label>
        <label><input type="button" value="删除" class="btnAll" onclick="del('<%=path %>/sys/hospital/del.do?hospitalid=')"/></label>
	</div>
	
	<table id="table1" class="borderTable" border="0" cellpadding="0" cellspacing="0">
		<tr class="theadBg">
			<td style="width:30px;"></td>
			<td style="width:60px;">
				医院编号
			</td>
			<td >
				名称
			</td>
			<td style="width:100px;">
				别名
			</td>
			<td style="width:50px;">
				联系人
			</td>
			<td style="width:100px;">
				电话
			</td>
			<td >
				地址
			</td>
			<td style="width:40px;">
				使用否
			</td>
		</tr>
		<c:forEach var="hospital" items="${hospitals}">
			<tr onclick="clktr('${hospital.hospitalid}')">
				<td style="text-align: center;">
					<input  type="radio" id="${hospital.hospitalid}" name="rd" value="${hospital.hospitalid}" />
				</td>
				<td style="text-align: center;">
						${hospital.hospitalno}
				</td>
				<td>
					${hospital.hospitalname}
				</td>
				<td>
					${hospital.code}
				</td>
				<td style="text-align: center;">
					${hospital.linkman}
				</td>
				<td style="text-align: center;">
					${hospital.phone}
				</td>
				<td>
					${hospital.addr}
				</td>
				<td style="text-align: center;">
					<c:choose>
						<c:when test="${hospital.state==1}">
							使用
						</c:when>
						<c:otherwise>
							未使用
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div style="text-align:right;color:#666;padding:10px;">
		${ps}
	</div>
</body>
</html>