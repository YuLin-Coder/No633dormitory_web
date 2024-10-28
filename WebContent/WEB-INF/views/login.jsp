<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title> 宿   舍   管  理  系  统</title>
		<!-- 样 式 文 件 -->
		<link rel="stylesheet" href="resource/component/pear/css/pear.css" />
		<link rel="stylesheet" href="resource/admin/css/other/login.css" />
	</head>
    <!-- 代 码 结 构 -->
	<body background="resource/admin/images/background.svg" style="background-size: cover;">
		<form class="layui-form" action="LoginServlet?action=login" method="post"  id="loginForm" name="loginForm" >
			<div class="layui-form-item">
				<img class="logo" src="resource/admin/images/logo.png" />
				<div class="title">登&emsp;&emsp;录</div>
				<div class="desc">
					学生 宿   舍   管  理  系  统
				</div>
			</div>
			<div class="layui-form-item">
				<input type="text" placeholder="请输入用户名或者手机号" name="userName" id="userName" hover class="layui-input"  />
				<span id="msg3" style="color:red">${message}</span>
			</div>
			<div class="layui-form-item">
				<input id="password" type="password" name="password" placeholder="请输入密码" hover class="layui-input"  />
			</div>
			<div class="layui-form-item" style="text-align: center;">
			      <input type="radio" name="type" id="type" value="1" title="管理员" checked>
                  <input type="radio" name="type" id="type" value="2" title="学生" > 
                   <input type="radio" name="type" id="type" value="3" title="宿管" > 
			</div>
			<div class="layui-form-item">
				<button type="submit" class="pear-btn pear-btn-success login" id="login">
					登录
				</button>
			</div>
			 <div class="layui-form-item">
				<a href="LoginServlet?action=toRegister" class="pear-btn pear-btn-warming  layui-btn-fluid">去注册</a>
			</div> 
		</form>
		<!-- 资 源 引 入 -->
		<script src="resource/component/layui/layui.js"></script>
		<script src="resource/component/pear/pear.js"></script>
		<script>
		layui.use(['layer', 'form','jquery'], function(){
			var form = layui.form,
			 layer = layui.layer,
			 $= layui.jquery;
			 form.render();//这句一定要加，占坑
		   
		$("#login").on("click", function() {
		    var userName = $("#userName").val().trim(); // trim()去除空格
		    var password = $("#password").val().trim();
		    var type = $("#type").val();
		    
		    
		    if(userName == ""){
		    	layer.msg('用户名或者手机号不能为空！');
		    	return false;
		    }
		    if(password == ""){
		    	layer.msg('密码不能为空！');
		    	return false;
		    }
		    if(type == ""){
		    	layer.msg('请选择角色！');
		    	return false;
		    }

		});

		setTimeout(function(){ $("#msg").html("")},3000);
		setTimeout(function(){ $("#msg2").html("")},3000);
		setTimeout(function(){ $("#msg3").html("")},3000);
		});
		</script>
	</body>
</html>
