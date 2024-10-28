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
				<form action="WskhServlet?action=wskhListLike" method="post">
					 <div class="layui-form-item">
						    <div class="layui-inline">
						        <div class="layui-input-inline">
						       		<input type="text" placeholder="根据入宿舍编号" name="dno" id ="dno" class="layui-input"  />
						      </div>
						    </div>
						    <div class="layui-inline">
						      <button type="submit" class="layui-btn layui-btn-sm layui-btn-normal">查询</button>
						  </div>
						  </div>

		           </form>   
				<table class="layui-table">
					<thead>
						<tr>
						    <th>宿舍编号</th>
						    <th>考核分数</th>
							<th>考核人</th>
							<th>备注</th>
							<th>考核日期</th>
						
							
								<th>操作</th>
							
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

								
							
								<td>  
									<input id ="${u.id}" value="${u.id}" type="hidden" class="weui-input"/>
								
								<a href="WskhServlet?action=toUpdateWskh&id=${u.id}" id="update"
									class="layui-btn layui-btn-sm layui-btn-normal"><i
										class="layui-icon">修改</i></a>
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
							href="WskhServlet?action=studentList&p=1">首页</a>
						<%--        判断是否有上一页--%>
						<c:if test="${cp>1}">
							<a class="layui-btn layui-btn-primary"
								href="WskhServlet?action=studentList&p=${cp-1}">上一页</a>
						</c:if>
						<%--        循环显示页码--%>
						<c:forEach begin="${cp-2>1 ? (cp-2) :1}"
							end="${cp+2>tp?tp:(cp+2)}" var="e">
							<%--            判断是否是当前页--%>
							<c:if test="${cp==e}">
								<a class="layui-btn layui-btn-danger"
									href="WskhServlet?action=studentList&p=${e}">${e}</a>
							</c:if>
							<c:if test="${cp!=e}">
								<a class="layui-btn layui-btn-primary"
									href="WskhServlet?action=studentList&p=${e}">${e}</a>
							</c:if>

						</c:forEach>

						<%--        判断是否有下一页--%>
						<c:if test="${cp<tp}">
							<a class="layui-btn  layui-btn-primary"
								href="WskhServlet?action=studentList&p=${cp+1}">下一页</a>
						</c:if>
						<a class="layui-btn  layui-btn-primary"
							href="WskhServlet?action=studentList&p=${tp}">尾页</a> 
					</div>
				</div>

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
				   		  $.post("WskhServlet?action=deleteWskh&id="+id, function(data) {
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