<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="../../top.jsp"></jsp:include>

<script type="text/javascript">
<!--
	
$(function()
{
	$("#pdeptname").click(function()
	{	
		$("#DivTree").css({
			left:$(this).offset().left+"px",
			top:$(this).offset().top+22+"px",
			width:$(this).width(),
			height:'80px'
		});
		$("#DivTree").toggle();
	});
});

function chg(val,txt){
	$("#pdeptid").val(val);
	$("#pdeptname").val(txt);
	$("#DivTree").css("display","none");
}

	//-->
</script>
<html>
<body>
	<form id="form1" action="<%=path%>/sys/dept/save.do" method="POST" onsubmit="return save()">
		<input type="hidden" id="deptid" name="deptid" value="${dept.deptid}">
		<input type="hidden" id="pdeptid" name="pdeptid" value="${dept.pdeptid}">
		
			<table width="100%" class="bg">
				
				<tr>
					<td width="80px;">
						角色编号
					</td>
					<td>
						<input type="text" id="deptno" name="deptno"
							value="${dept.deptno}" class="width200 no nn" maxlength="10" />
							<span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td width="80px;">
						角色名称
					</td>
					<td>
						<input type="text" id="deptname" name="deptname"
							value="${dept.deptname}" class="width200 nn" maxlength="50" />
							<span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td width="80px;">
						角色简码
					</td>
					<td>
						<input type="text" id="code" name="code" value="${dept.code}"
							class="width200 nn" maxlength="50" />
							<span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td width="80px;">
						上级角色
					</td>
					<td>
						<input type="text" id="pdeptname" name="pdeptname" 
							value="${dept.pdeptname}" class="width200 nn" maxlength="50" />
							<span style="color:red">*</span>
					</td>
				</tr>
				<tr>
					<td width="80px;">
						使用否
					</td>
					<td>
						<select name="state" class="width200">
							<option value="1" <c:if test="${dept.state==1}"> selected="selected"</c:if>>
								使用
							</option>
							<option value="2"
								<c:if test="${dept.state==2}"> selected="selected"</c:if>>
								不使用
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="80px;">
						备注
					</td>
					<td>
						<input type="text" id="memo" name="memo" value="${dept.memo}"
							style="width:400px;" maxlength="100" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						<input type="submit" id="btnAdd" value="保存" class="btn"/>
						<input type="reset" id="btnreset" value="取消" class="btn" />
					</td>
				</tr>
			</table>
	</form>

	<iframe style="position:absolute;z-index:-1;width:e xpression(this.nextSibling.offsetWidth);height:e­xpression(this.nextSibling.offsetHeight);top:e xpression(this.nextSibling.offsetTop);left:e­xpression(this.nextSibling.offsetLeft);"   frameborder="0">
    </iframe>

	<div id="DivTree"  style="overflow-y:scroll;display:none;border:1px solid #A2B5D3;position:absolute;background-color:#FFE;">
	</div>

</body>
</html>
<script type="text/javascript">
	${dept_tree};
	document.getElementById('DivTree').innerHTML=t0;
</script>
