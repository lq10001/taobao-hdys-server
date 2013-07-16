<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="../../top.jsp"></jsp:include>
		<script type="text/javascript">
function chgpwd() {
	var pwd = $("#newpwd").val();
	var pwd2 = $("#newpwd2").val();
	if (pwd != pwd2) {
		alert("新密码2次输入不一样");
		return;
	}
	var form = document.getElementById("form1");
	form.submit();
}
</script>
<html>
<body>

	<div class="position">
		<span><b>当前位置：系统管理》修改密码</b></span>
	</div>
	<form id="form1" action="<%=path%>/sys/sysuser/chgpwd.do" method="POST">

		<table width="100%" class="bg">
			<tr>
				<td></td>
				<td>
					<span style="color: red;"> </span>
				</td>
			</tr>
			<tr>

				<td align="right">
					原始密码
				</td>
				<td>
					<input type="password" id="password" name="password"
						class="width200" />
					<span style="color: red">*</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					新密码
				</td>
				<td>
					<input type="password" id="newpwd" name="newpwd" class="width200" />
					<span style="color: red">*</span>
				</td>
			</tr>
			<tr>
				<td align="right">
					新密码确认
				</td>
				<td>
					<input type="password" id="newpwd2" name="newpwd2" class="width200" />
					<span style="color: red">*</span>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;

				</td>
				<td colspan="2">
					<input type="button" id="btnAdd" value="修改密码" class="btn"
						onclick="chgpwd()" />
					<input type="reset" value="取消" class="btn" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>