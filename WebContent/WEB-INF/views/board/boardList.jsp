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
			<div class="layui-card-header">公告列表</div>
			<div class="layui-card-body">
				<form action="BoardServlet?action=boardListLike" method="post">
		           <input type="text" placeholder="根据主题查询" name="name" id ="name" style="height:26px;" />
		           
		           <input type="submit" class="layui-btn layui-btn-sm layui-btn-normal" value="查询" />
		         <%--   <c:if test="${flag == 1}">
								 <a href="BoardServlet?action=toAddBoard"
					class="layui-btn layui-btn-sm layui-btn-normal">新增公告</a>
					</c:if> --%>
		           </form>   
				<span style="color: red" class="help-block m-b-none">${msg}</span>
				<table class="layui-table">
					<thead>
						<tr>
						    <th>主题</th>
							<th>主要内容</th>
							<th>作者</th>
							<th>发布时间</th>
							<c:if test="${flag == 1}">
								<th>操作</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="u">
							<tr>
							    <td>${u.title }</td>
								<td>${u.content }</td>
								<td>${u.editor }</td>
								<td>${u.fbsj }</td>
								<c:if test="${flag == 1}">
								<td>  
								  <input id ="${u.id}" value="${u.id}" type="hidden" class="weui-input"/>
								<a href="BoardServlet?action=toUpdateBoard&id=${u.id}" id="update"
									class="layui-btn layui-btn-sm layui-btn-norma"><i
										class="layui-icon">修改</i></a>
										 <a  href='javascript:void(0)' class="layui-btn layui-btn-sm layui-btn-danger"><i
										class="layui-icon">删除</i></a>
										</td>
								</c:if> 
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
               <div style="text-align: center">
					<div class="layui-btn-group">
						<a class="layui-btn layui-btn-primary"
							href="BoardServlet?action=boardList&p=1">首页</a>
						<%--        判断是否有上一页--%>
						<c:if test="${cp>1}">
							<a class="layui-btn layui-btn-primary"
								href="BoardServlet?action=boardList&p=${cp-1}">上一页</a>
						</c:if>
						<%--        循环显示页码--%>
						<c:forEach begin="${cp-2>1 ? (cp-2) :1}"
							end="${cp+2>tp?tp:(cp+2)}" var="e">
							<%--            判断是否是当前页--%>
							<c:if test="${cp==e}">
								<a class="layui-btn layui-btn-danger"
									href="BoardServlet?action=boardList&p=${e}">${e}</a>
							</c:if>
							<c:if test="${cp!=e}">
								<a class="layui-btn layui-btn-primary"
									href="BoardServlet?action=boardList&p=${e}">${e}</a>
							</c:if>

						</c:forEach>

						<%--        判断是否有下一页--%>
						<c:if test="${cp<tp}">
							<a class="layui-btn  layui-btn-primary"
								href="BoardServlet?action=boardList&p=${cp+1}">下一页</a>
						</c:if>
						<a class="layui-btn  layui-btn-primary"
							href="BoardServlet?action=boardList&p=${tp}">尾页</a> <a
							clss="layui-btn" href="BoardServlet?action=boardList&p=2">2</a>
						<a clss="layui-btn" href="BoardServlet?action=boardList&p=3">3</a>
						<a clss="layui-btn" href="BoardServlet?action=boardList&p=4">4</a>
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
			   		  $.post("BoardServlet?action=deleteBoard&id="+id, function(data) {
			   			   layer.msg('删除成功！',{icon:6,offset:"auto",time:2000},function(){
						    	document.location.reload();//当前页面
						    });
			   		   }); 
			   	}
   		})
  });

</script>

</body>
</html>