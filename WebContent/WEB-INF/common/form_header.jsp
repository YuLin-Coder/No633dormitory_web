<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>宿舍管理系统</title>
<link rel="stylesheet" type="text/css" href="layui/css/layui.css"> 
<link rel="stylesheet" type="text/css" href="layui/css/admin.css"> 
<link rel="stylesheet" type="text/css" href="layui/css/login.css"> 
</head>