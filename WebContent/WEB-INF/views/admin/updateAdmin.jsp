<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
          <div class="layui-card-header">个人信息修改</div>
          <div class="layui-card-body" >
             <form action="AdminServlet?action=updateAdmin" method="post" >
                <input name="adminId" id="adminId" value="${adminDate.id}" class="layui-input" type="hidden" />
              <div class="layui-form-item">
                <label class="layui-form-label">用户名：</label>
                <div class="layui-input-inline">
                    <input name="username" id="username" value="${adminDate.username}" class="layui-input" />
                </div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">密码:</label>
                <div class="layui-input-inline">
                   <input name="pwd" id="pwd" value="${adminDate.pwd}"  class="layui-input" />
                </div>
              </div>
              
               <div class="layui-form-item">
                <label class="layui-form-label">昵称:</label>
                <div class="layui-input-inline">
                   <input name="nickname" id="nickname" value="${adminDate.nickname}" class="layui-input" />
                </div>
              </div>
              
              <br>
                <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" type="submit" id="updateRecord">修改</button>
                  <a href="AdminServlet?action=toUpdateAdmin&id=${adminDate.id}" class="layui-btn layui-btn-primary" >重置</a>
                </div>
              </div>  
              </form>
            </div>
            
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
	 
	 $('#updateRecord').click(function(){  
		  var username = $.trim($('#username').val());
		  var pwd = $.trim($('#pwd').val());
		  var nickname = $.trim($('#nickname').val());
		  if(userName == ''){  
		         layer.msg('用户名不能为空',{icon:6},function() {time:2000}); 
		         return false;  
		   }
		  if(pwd == ''){  
		         layer.msg('密码不能为空',{icon:6},function() {time:2000}); 
		         return false;  
		   }
		  if(nickName == ''){  
		         layer.msg('昵称不能为空',{icon:6},function() {time:2000}); 
		         return false;  
		   }
	 });
   

});
</script>
</body>
</html>