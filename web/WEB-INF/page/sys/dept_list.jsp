<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="../../top.jsp"></jsp:include>	
<script type="text/javascript">
function add(){
	var ifr = document.getElementById("ifr");
	ifr.src="<%=path %>/sys/dept/add.do";
}

function del(){
	var id =$("#id").val()
	if(id==''){
		alert('请选择需要删除的角色');
		return;
	}
	if (confirm("确认要删除角色？")){
		location.href="<%=path %>/sys/dept/del.do?deptid="+id;
	}
}

function level(){
	var id =$("#id").val()
	if(id==''){
		alert('请选择角色');
		return;
	}
	var ifr = document.getElementById("ifr");
	ifr.src="<%=path %>/sys/deptmenu/edit.do?deptid="+id;
}

function chg(val,txt){
	if(val==0){
		return;
	}
	$("#id").val(val);
	$("#txt").val(txt);
	var ts= "角色<font color='red'>["+txt+"]</font>详细信息";
	$("#lbinfo").html(ts);
	var ifr = document.getElementById("ifr");
	ifr.src="<%=path %>/sys/dept/edit.do?deptid="+val;
}
</script>
<body class="easyui-layout">
	<div region="north" border="false" style="height:31px;">
		<div class="position">
			<span><b>当前位置：系统管理》角色管理</b></span>
		</div>
	</div>
	
	<div region="west" split="true" title="&nbsp;角色列表" style="width:250px;padding-left:8px;">
		<input type="hidden" id="id" value="" />
		<input type="hidden" id="txt" value="" />
		
		<input type="button"  onclick="add()" value="添加" style="width: 60px;"/>
		<input type="button"  onclick="level()" value="设置权限" style="width: 60px;"/>
		<input type="button" onclick="del()" value="删除" style="width: 60px;"/>
		<div id="dd"></div>
	</div>
	<div id="center" region="center" title="角色详细信息" style="overflow:hidden;">
		<div id="div1" style="width:100%;height: 100%">
			<iframe id="ifr" name="ifr" frameborder="0" src=""></iframe>
		</div>
	</div> 
</body>
<script type="text/javascript">
	${dept_tree};
	document.getElementById('dd').innerHTML=t0;
</script>

