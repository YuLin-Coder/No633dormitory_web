
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
          <div class="layui-card-header">学生信息</div>
          <div class="layui-card-body">
             <form action="" method="post" id="registerForm" name="registerForm" enctype="multipart/form-data" class="layui-form" >
              <input name="stuno" id="stuno" value="${student.stuno }" type="hidden" class="layui-input" /> 
              <div class="layui-form-item">
                <label class="layui-form-label">姓名:</label>
                <div class="layui-input-inline">
				<input name="name" id="name" value="${student.name }"  class="layui-input" /> 
				  <span id="msg3" style="color:red">${message}</span>
                </div>
              </div>
            
              
              <div class="layui-form-item">
                <label class="layui-form-label">专业:</label>
                <div class="layui-input-inline">
				<input name="major" id="major" value="${student.major }" class="layui-input" /> 
                </div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">班级:</label>
                <div class="layui-input-inline">
				<input name="bj" id="bj" value="${student.bj }" class="layui-input" /> 
                </div>
              </div>
              
               <div class="layui-form-item">
                <label class="layui-form-label">手机号:</label>
                <div class="layui-input-inline">
				<input name="phone" id="phone" value="${student.phone }" class="layui-input" /> 
                </div>
              </div>
              
               <div class="layui-form-item">
                <label class="layui-form-label">登录密码:</label>
                <div class="layui-input-inline">
				<input name="pwd" id="pwd" value="${student.pwd }" class="layui-input" /> 
                </div>
              </div>
               
                   <div class="layui-form-item">
                     <label class="layui-form-label">头像展示:</label>
                        <div class="layui-input-inline">
                            <img src="<%=path %>${student.tx}" id="preview_img" width="350px" height="200px" alt="">
                        </div>
               </div> 
               
              
               <div class="layui-form-item">
                <label class="layui-form-label">性别:</label>
                <div class="layui-input-inline">
				<input name="sex" id="sex" value="${student.sex }" class="layui-input" /> 
                </div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">宿舍:</label>
                <div class="layui-input-inline">
				<input name="floor" id="floor" value="${student.floor }" class="layui-input" /> 
                </div>
              </div>
              
              
              <br>
                <div class="layui-form-item">
                <div class="layui-input-block">
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
		    // 给注册绑定单击事件
		      $("#sub_btn").click(function () {
		        var name = $("#name").val();
		        var sex = $("#sex").val();
		        var classes = $("#classes").val();
		        var time = $("#time").val();
		        var xy = $("#xy").val();
		        var phone = $("#phone").val();
		        var pwd = $("#pwd").val();
		        var stno = $("#stno").val();
		       /*  if(sex == null || sex== ""){
		        	layer.msg('性别不能为空！');
		        	return false;
		        }
		        if(name == null || name== ""){
		        	layer.msg('姓名不能为空！');
		        	return false;
		        }
		        if(classes == null || classes== ""){
		        	layer.msg('班级不能为空！');
		        	return false;
		        }
		        if(time == null || time== ""){
		        	layer.msg('入学时间不能为空！');
		        	return false;
		        }
		        if(xy == null || xy== ""){
		        	layer.msg('学院不能为空！');
		        	return false;
		        }
		        if(phone == null || phone== ""){
		        	layer.msg('手机号不能为空！');
		        	return false;
		        }
		        if(pwd == null || pwd== ""){
		        	layer.msg('密码不能为空！');
		        	return false;
		        }
		        if(stno == null || stno== ""){
		        	layer.msg('学号不能为空！');
		        	return false;
		        } */
		    });
		    setTimeout(function(){ $("#errorMsg").html("")},3000);
	});
	 setTimeout(function(){ $("#msg3").html("")},3000);
	 $("#imgUrl").change(function () {
		    //创建blob对象，浏览器将文件放入内存中，并生成标识
		    var img_src = URL.createObjectURL($(this)[0].files[0]);
		    //给img标检的src赋值
		    document.getElementById("preview_img").src=img_src;
		    //URL.revokeObjectURL(img_src);// 手动 回收，
		});

});
</script>
</body>
</html>