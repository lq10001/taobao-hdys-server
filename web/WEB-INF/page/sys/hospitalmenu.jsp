<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="../../top.jsp"></jsp:include>
<script type="text/javascript">
	function save(){
		var hospitalid = $("#hospitalid").val();
		if(hospitalid == ''){
			alert("请选择医院");
			return;
		}
		var vals=getCheckValues(false);
		if(vals == ''){
			alert("请选择要菜单项");
			return;
		}
		location.href="<%=path%>/sys/hospitalmenu/save.do?ids="+vals+"&hospitalid="+hospitalid;
	}
</script>
<body>
	<div class="position">
	    <span><b>当前位置：系统管理》医院管理》设置医院权限</b></span>
	</div>
	
	<div style="margin-left: 10px;">
		<input type="button" value="保存" onclick="save()" class="btn" />
		<input type="button" id="btnReturn" value="返回" class="btn" onClick="list('<%=path%>/sys/hospital/list.do')" />
		<input type="hidden" id="hospitalid" name="hospitalid" value="${hospitalid}"/>
		<script type="text/javascript">
			${menu_tree}
		</script>
	</div>
</body>
