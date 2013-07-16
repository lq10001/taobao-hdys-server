<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="../../top.jsp"></jsp:include>
<script type="text/javascript">
function save(){
	var deptid = $("#deptid").val();
	if(deptid == ''){
		alert("请选部门");
		return;
	}
	var vals=getCheckValues(false);
	if(vals == ''){
		alert("请选择要菜单项");
		return;
	}
	location.href="<%=path%>/sys/deptmenu/save.do?ids="+vals+"&deptid="+deptid;
}
</script>
<body style="margin-left: 10px;">
	<input type="button" value="保存" onclick="save()" class="btn" />
	<input type="hidden" id="deptid" name="deptid" value="${deptid}"/>
	<script type="text/javascript">
		${menu_tree}
	</script>
</body>
