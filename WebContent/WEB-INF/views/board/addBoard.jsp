
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

 
  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header">新增公告</div>
          <div class="layui-card-body">
             <form action="BoardServlet?action=addBoard" method="post" id="registerForm" name="registerForm" >
              <div class="layui-form-item">
                <label class="layui-form-label">主题:</label>
                <div class="layui-input-inline">
				<input name="title" id="title" class="layui-input" /> 
				  <span id="msg3" style="color:red">${message}</span>
                </div>
              </div>
              
               <div class="layui-form-item" >
                <label class="layui-form-label">主要内容:</label>
                <div class="layui-input-inline">
                      <textarea name="content" id="content" placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
                </div>
                
              <br>
                <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" type="submit" id="sub_btn">提交</button>
                  <a href="BoardServlet?action=toAddBoard" class="layui-btn layui-btn-primary" >重置</a>
                </div>
              </div>  
              </form>
            </div>
            
          </div>
        </div>
      </div>
    </div>
		

<script src="layui/layui.js"></script>
<script src="layui/jquery-1.9.1.min.js"></script>
<script>

layui.use([ 'form','jquery','layer','laydate' ], function() {
	var form = layui.form,
	 layer = layui.layer,
	 laydate=layui.laydate,
	 $= layui.jquery;
	 form.render();//这句一定要加，占坑
	 
	 $(function () {
		    // 给注册绑定单击事件
		    $("#sub_btn").click(function () {
		        var title = $("#title").val();
		        var content = $("#content").val();
		        if(title == null || title== ""){
		        	layer.msg('主题不能为空！');
		        	return false;
		        }
		        if(content == null || content== ""){
		        	layer.msg('主要内容不能为空！');
		        	return false;
		        }
		    });
		    setTimeout(function(){ $("#errorMsg").html("")},3000);
	});
	 setTimeout(function(){ $("#msg3").html("")},3000);

});
</script>
</body>
</html>