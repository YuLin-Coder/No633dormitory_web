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
			<div class="layui-card-header">寝室卫生考核列表</div>
			<div class="layui-card-body">
	  	
	
				<table class="layui-table">
					<thead>
						<tr>
						    <th>宿舍编号</th>
						    <th>考核分数</th>
							<th>考核人</th>
							<th>备注</th>
							<th>考核日期</th>
						
						
							
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="u">
							<tr>
							   <td>${u.dno }</td>
							    <td>${u.score }</td>
								<td>${u.jlr }</td>
								<td>${u.note }</td>
								
					 <td> <fmt:formatDate value="${u.create_time }" pattern="yyyy-MM-dd HH:mm:ss "></fmt:formatDate></td> 	              

								
							
							
								
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
	 
	 
	 
	 
	 laydate.render({
		    elem: '#time'
		    ,type: 'year'
	});

});

</script>

</body>
</html>