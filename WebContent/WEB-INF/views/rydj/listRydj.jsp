<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
     　　<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/common/form_header.jsp"/>

<body>

	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-header">人员登记列表</div>
			<div class="layui-card-body">
			<a href="RydjServlet?action=rydjList" class="layui-btn layui-btn-sm layui-btn-normal"><i
										class="layui-icon">返回人员登记列表</i></a> 
				<table class="layui-table">
					<thead>
						<tr>
						   <th>来访者姓名</th>
						       <th>性别</th>
						    <th>宿舍编号</th>						
							<th>被访者</th>
							<th>联系方式</th>
							<th>备注</th>
							<th>登记时间</th>
								<th>操作</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="u">
							<tr>
							   <td>${u.name }</td>
							   <td>${u.dno }</td>
							    <td>${u.sex }</td>
								<td>${u.sname }</td>
								<td>${u.phone }</td>
								<td>${u.note }</td>								
					 <td> <fmt:formatDate value="${u.djsj }" pattern="yyyy-MM-dd HH:mm:ss "></fmt:formatDate></td> 	              

								
							
								<td>  
									<input id ="${u.id}" value="${u.id}" type="hidden" class="weui-input"/>
								
								<a href="RydjServlet?action=toUpdateRydj&id=${u.id}" id="update"
									class="layui-btn layui-btn-sm layui-btn-normal"><i
										class="layui-icon">修改</i></a>
								 <a  href='javascript:void(0)' class="layui-btn layui-btn-sm layui-btn-danger"><i
										class="layui-icon">删除</i></a>
										</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
        

			</div>
		</div>
	</div>

	<script src="layui/layui.js"></script>
<script src="layui/jquery-1.9.1.min.js"></script>
	<script type="application/javascript">

layui.use([ 'form','jquery','layer','laydate' ], function() {
	var form = layui.form,
	 layer = layui.layer,
	 laydate=layui.laydate,
	 $= layui.jquery;
	 form.render();//这句一定要加，占坑
	 
	 
	 $('a.layui-btn-danger').click(function(e){
	   		            var id = $(e.currentTarget).parent().find('.weui-input').attr('id');
				   		if(confirm("确定要删除吗？")){
				   		  $.post("RydjServlet?action=deleteRydj&id="+id, function(data) {
				   			   layer.msg('删除成功！',{icon:6,offset:"auto",time:2000},function(){
							    	document.location.reload();//当前页面
							    });
				   		   }); 
				   	}
	   		})
	 
	 laydate.render({
		    elem: '#time'
		    ,type: 'year'
	});

});

</script>

</body>
</html>