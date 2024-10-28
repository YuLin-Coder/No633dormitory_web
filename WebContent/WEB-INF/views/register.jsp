<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<!-- 样 式 文 件 -->
		<link rel="stylesheet" href="resource/component/pear/css/pear.css" />
		<link rel="stylesheet" href="resource/admin/css/other/login.css" />
	</head>
    <!-- 代 码 结 构 -->
	<body background="resource/admin/images/background.svg" style="background-size: cover;">
		<form class="layui-form" action="StudentServlet?action=addStudent" method="post"  id="loginForm" name="loginForm" enctype="multipart/form-data" >
			 <input id="te" type="hidden" name="te" value="1" />
			<div class="layui-form-item">
				<img class="logo" src="resource/admin/images/logo.png" />
				<div class="title">注&emsp;&emsp;册</div>
				<div class="desc">
					 宿   舍   管  理  系  统
				</div>
			</div>
			<div class="layui-form-item">
				<input type="text" placeholder="请输入姓名" name="name" id="name" hover class="layui-input"  />
				<span id="msg" style="color:red">${message}</span>
			</div>
			<div class="layui-form-item">
				<input id="stuno" type="text" name="stuno" placeholder="请输入学号" hover class="layui-input"  />
			</div>
			<div class="layui-form-item">
				<select name="sex" id ="sex">
				        <option value="">请选择性别</option>
				        <option value="男">男</option>
				        <option value="女">女</option>
				      </select>
			</div>
				<div class="layui-form-item">
				<select name="d_id" id ="d_id">
				        <option value="">请选择宿舍</option>
				        <c:forEach items="${dormitoryList}" var="u">
						    <option value="${u.id }">${u.floor }</option>
				        </c:forEach>
				      </select>
			</div>
			 <div class="layui-form-item">
				<input id="bj" type="text" name="bj" placeholder="请输入班级" hover class="layui-input"  />
			</div>
			 <div class="layui-form-item">
				<input id="phone" type="text" name="phone" placeholder="请输入手机号" hover class="layui-input"  />
			</div>
			 <div class="layui-form-item">
				<input id="pwd" type="text" name="pwd" placeholder="请输入登录密码" hover class="layui-input"  />
			</div>
			 <div class="layui-form-item">
				<input id="major" type="text" name="major" placeholder="请输入专业" hover class="layui-input"  />
			</div>
			 <div class="layui-form-item">
				 <input type="file" name="imgUrl" id="imgUrl"  placeholder="点击上传头像" class="layui-input"  /> 
			</div>
			 <div class="layui-form-item">
                            <img src="" id="preview_img" width="350px" height="200px" alt="">
			</div>
			<div class="layui-form-item">
				<button type="submit" class="pear-btn pear-btn-success login" id="login">
					注册
				</button>
			</div>
			 <div class="layui-form-item">
				<a href="LoginServlet?action=toLogin" class="pear-btn pear-btn-warming  layui-btn-fluid">返回</a>
			</div> 
		</form>
		<!-- 资 源 引 入 -->
		<script src="resource/component/layui/layui.js"></script>
		<script src="resource/component/pear/pear.js"></script>
		<script>
		layui.use(['layer', 'form','jquery','laydate'], function(){
			var form = layui.form,
			 layer = layui.layer,
			 laydate=layui.laydate,
			 $= layui.jquery;
			 form.render();//这句一定要加，占坑
		   
			 laydate.render({
				    elem: '#time'
				  });
		 $("#login").on("click", function() {
			  var stuno = $("#stuno").val();
		        var sex = $("#sex").val();
		        var classes = $("#classes").val();
		        var time = $("#time").val();
		        var xy = $("#xy").val();
		        var phone = $("#phone").val();
		        var pwd = $("#pwd").val();
		        var stno = $("#stno").val();
		        if(stuno == null || stuno== ""){
		        	layer.msg('学号不能为空！');
		        	return false;
		        }
		        if(stuno != null && stuno.length > 11){
		        	layer.msg('学号不能大于11位！');
		        	return false;
		        }
		      /*   if(name == null || name== ""){
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

		setTimeout(function(){ $("#msg").html("")},3000);
		setTimeout(function(){ $("#msg2").html("")},3000);
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
