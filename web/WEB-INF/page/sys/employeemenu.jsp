<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="../../top.jsp"></jsp:include>
<script type="text/javascript">
	function save(){
		var employeeid = $("#employeeid").val();
		if(employeeid == ''){
			alert("请选择员工");
			return;
		}
		var vals=getCheckValues(false);
		if(vals == ''){
			alert("请选择要菜单项");
			return;
		}
		location.href="<%=path%>/sys/employeemenu/save.do?ids="+vals+"&employeeid="+employeeid;
	}
</script>
<body>
	<div class="position">
	    <span><b>当前位置：系统管理》员工管理》设置员工权限</b></span>
	</div>
	<div style="margin-left: 10px;">
	<input type="button" value="保存" onclick="save()" class="btn" />
	<input type="button" id="btnReturn" value="返回" class="btn" onClick="list('<%=path%>/sys/employee/list.do')" />
	<input type="hidden" id="employeeid" name="employeeid" value="${employeeid}"/>
	<script type="text/javascript">
		${menu_tree}
	</script>
	</div>
</body>
