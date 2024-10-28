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
          <div class="layui-card-header">登记人员</div>
          <div class="layui-card-body">
             <form action="RydjServlet?action=addRydj" method="post" id="registerForm" name="registerForm"  class="layui-form" >
 
                <div class="layui-form-item">
                <label class="layui-form-label">来访者:</label>
                <div class="layui-input-inline">
				<input name="name" id="name" class="layui-input" /> 
                </div>
                
              </div>
                           
		<div class="layui-form-item">
                <label class="layui-form-label">寝室编号:</label>
                <div class="layui-input-inline">
				<input name="dno" id="dno" placeholder="请输入要访问的宿舍编号" class="layui-input" /> 
				  <span id="msg3" style="color:red">${message}</span>
                </div>
              </div>
             
              <div class="layui-form-item">
                <label class="layui-form-label">性别:</label>
                	 <div class="layui-input-inline">
                
	                    <select name="sex" id ="sex" style="width:13%;height:38px">
				        <option value="">请选择性别</option>
				        <option value="男">男</option>
				        <option value="女">女</option>
				      </select>
				       </div>
              </div>
          
             
              
              <div class="layui-form-item">
                <label class="layui-form-label">被访者:</label>
                <div class="layui-input-inline">
				<input name="sname" id="sname" value="" class="layui-input" /> 
                </div>
              </div>
              
              
               <div class="layui-form-item">
                <label class="layui-form-label">联系方式:</label>
                <div class="layui-input-inline">
				<input name="phone" id="phone" value="" class="layui-input" /> 
                </div>
              </div>
              
               <div class="layui-form-item">
                <label class="layui-form-label">备注:</label>
                <div class="layui-input-inline">
			  <textarea id="note" name="note" placeholder="请输入备注" class="layui-textarea"></textarea>
                </div>
              </div>
              
            
              <br>
                <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" type="submit" id="sub_btn">提交</button>
                  <a href="RydjServlet?action=rydjList" class="layui-btn layui-btn-primary" >返回</a>
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
	 
	 laydate.render({
		    elem: '#time'
		  });
	 
	 $(function () {
		    // 给注册绑定单击事件i
		    $("#sub_btn").click(function () {
		        var dno = $("#dno").val();
		        var name = $("#name").val();
		        var sname = $("#sname").val();
		        var phone = $("#phone").val();
		    
		        if(dno == null || dno== ""){
		        	layer.msg('宿舍编号不能为空！');
		        	return false;
		        }
		        
		        if(name == null || name== ""){
		        	layer.msg('来访者不能为空！');
		        	return false;
		        }
		        
		        if(sname == null || sname== ""){
		        	layer.msg('被访者不能为空！');
		        	return false;
		        }
		        
		        if(phone == null || phone== ""){
		        	layer.msg('被访者不能为空！');
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