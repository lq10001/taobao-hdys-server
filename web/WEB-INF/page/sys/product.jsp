<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>

<div class="pageContent">
	<form method="post" action="<%=path%>/sys/product/save" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" id="productid" name="productid" value="${product.productid}">
		
		<div class="pageFormContent" layoutH="56">
                    
                    <p style="">
                            <label>名称</label>
                            <input name="title" class="required" type="text" size="40" value="${product.title}" alt=""/>
                    </p>
                    <div class="divider"></div>    
                    
                    <p>
                            <label>编号</label>
                            <input name="num_iid" class="required" type="text" size="40" value="${product.num_iid}" alt=""/>
                    </p>
                    <div class="divider"></div>    
                    
                    <p>
                            <label>价格</label>
                            <input name="price" class="required" type="text" size="40" value="${product.price}" alt=""/>
                    </p>
                    <div class="divider"></div>    
                    
                    <p>
                            <label>详情地址</label>
                            <input name="url" class="required" type="text" size="40" value="${product.url}" alt=""/>
                    </p>
                    <div class="divider"></div>  
                    
												
                    <p>
                            <label>图片地址</label>
                            <input name="pic_url" class="required" type="text" size="40" value="${product.pic_url}" alt=""/>
                    </p>
                    <div class="divider"></div>


              

																																				
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
