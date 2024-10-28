<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/common/form_header.jsp"/>

<body>

	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-header">报修申请列表</div>
			<div class="layui-card-body">
				<span style="color: red" class="help-block m-b-none">${msg}</span>
					<c:if test="${flag == '3' }">
			  <form action="QcshServlet?action=qcshListLike" method="post">
				 <div class="layui-form-item">
					      <div class="layui-input-inline">
					       		<input type="text" placeholder="根据登记人姓名" name="name" id ="name" class="layui-input"  />
					      </div>
						    <div class="layui-inline">
						      <button type="submit" class="layui-btn layui-btn-sm">查询</button>
						    </div>
						  </div>
					
		           </form>   
			</c:if>
				<table class="layui-table">
					<thead>
						<tr>
						    <th>登记人</th>
							<th>宿舍编号</th>
							<th>故障类型</th>
							<th>照片</th>
							<th>报修时间</th>
							<th>备注</th>
							<th>联系方式</th>
							<th>状态</th>
								<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="u">
							<tr>
							    <td>${u.charger }</td>
								<td>${u.d_no }</td>
								<td>${u.type }</td>
								<td><img src="<%=path %>${u.imgUrl}" width="50px" height="50px" alt=""></td>
								<td>${u.create_time }</td>
								<td>${u.notice }</td>
								<td>${u.phone }</td>
								<td>
								    <c:if test="${u.status == '0' }">
								          <span style="color:blue"> 已报修</span>
								  </c:if>
								<c:if test="${u.status == '1' }">
																          <span style="color:green">    已处理</span>
								
								  </c:if>
								  <c:if test="${u.status == '2' }">
								  								          <span style="color:red">   拒绝处理</span>
								         
								  </c:if>
								
								</td>
								
								<td>  
								  <input id ="${u.id}" value="${u.id}" type="hidden" class="weui-input"/>
								   <c:if test="${u.status == '0' && flag == '3'}">
								          <a href="javascript:void(0)" id="update"
									class="layui-btn layui-btn-sm layui-btn-normal"><i
										class="layui-icon">已处理</i></a>
										<a href="javascript:void(0)" id="update"
									class="layui-btn layui-btn-sm layui-btn-warm"><i
										class="layui-icon">拒绝处理</i></a>
								  </c:if>
								
										 <a  href='javascript:void(0)' class="layui-btn layui-btn-sm layui-btn-danger"><i
										class="layui-icon">删除</i></a>
										</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
               <div style="text-align: center">
					<div class="layui-btn-group">
						<a class="layui-btn layui-btn-primary"
							href="QcshServlet?action=qcshList&p=1">首页</a>
						<%--        判断是否有上一页--%>
						<c:if test="${cp>1}">
							<a class="layui-btn layui-btn-primary"
								href="QcshServlet?action=qcshList&p=${cp-1}">上一页</a>
						</c:if>
						<%--        循环显示页码--%>
						<c:forEach begin="${cp-2>1 ? (cp-2) :1}"
							end="${cp+2>tp?tp:(cp+2)}" var="e">
							<%--            判断是否是当前页--%>
							<c:if test="${cp==e}">
								<a class="layui-btn layui-btn-danger"
									href="QcshServlet?action=qcshList&p=${e}">${e}</a>
							</c:if>
							<c:if test="${cp!=e}">
								<a class="layui-btn layui-btn-primary"
									href="QcshServlet?action=qcshList&p=${e}">${e}</a>
							</c:if>

						</c:forEach>

						<%--        判断是否有下一页--%>
						<c:if test="${cp<tp}">
							<a class="layui-btn  layui-btn-primary"
								href="QcshServlet?action=qcshList&p=${cp+1}">下一页</a>
						</c:if>
						<a class="layui-btn  layui-btn-primary"
							href="QcshServlet?action=qcshList&p=${tp}">尾页</a> <a
							clss="layui-btn" href="QcshServlet?action=qcshList&p=2">2</a>
						<a clss="layui-btn" href="QcshServlet?action=qcshList&p=3">3</a>
						<a clss="layui-btn" href="QcshServlet?action=qcshList&p=4">4</a>
					</div>
				</div>

			</div>
		</div>
	</div>

	<script src="layui/jquery-1.9.1.min.js"></script>
	<script src="layui/layui.js"></script>
	
	<script>
    layui.use(['layer','form','jquery'], function () {//调用layui组件
		var form = layui.form;
   		var layer=layui.layer;
	    $=layui.jquery;  
	    form.render();
	     $('a.layui-btn-danger').click(function(e){
   		            var id = $(e.currentTarget).parent().find('.weui-input').attr('id');
			   		if(confirm("确定要删除吗？")){
			   		  $.post("QcshServlet?action=deleteqcsh&id="+id, function(data) {
			   			   layer.msg('删除成功！',{icon:6,offset:"auto",time:2000},function(){
						    	document.location.reload();//当前页面
						    });
			   		   }); 
			   	}
   		})
   		  $('a.layui-btn-normal').click(function(e){
   		            var id = $(e.currentTarget).parent().find('.weui-input').attr('id');
			   		if(confirm("确定已经处理了吗？")){
			   		  $.post("QcshServlet?action=agreeqcsh&id="+id, function(data) {
			   			   layer.msg('处理成功！',{icon:6,offset:"auto",time:2000},function(){
						    	document.location.reload();//当前页面
						    });
			   		   }); 
			   	}
   		})
   		  $('a.layui-btn-warm').click(function(e){
   		            var id = $(e.currentTarget).parent().find('.weui-input').attr('id');
			   		if(confirm("确定拒绝处理吗？")){
			   		  $.post("QcshServlet?action=refuseqcsh&id="+id, function(data) {
			   			   layer.msg('拒绝成功！',{icon:6,offset:"auto",time:2000},function(){
						    	document.location.reload();//当前页面
						    });
			   		   }); 
			   	}
   		})
  });

</script>

</body>
</html>