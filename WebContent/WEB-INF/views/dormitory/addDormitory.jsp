
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
          <div class="layui-card-header">新增宿舍</div>
          <div class="layui-card-body">
             <form action="DormitoryServlet?action=addDormitory" method="post" id="registerForm" name="registerForm"  class="layui-form">
              <div class="layui-form-item">
                <label class="layui-form-label">编号:</label>
                <div class="layui-input-inline">
				<input name="dno" id="dno" class="layui-input" /> 
				  <span id="msg3" style="color:red">${message}</span>
                </div>
              </div>
              
                <div class="layui-form-item">
                <label class="layui-form-label">宿舍长:</label>
                <div class="layui-input-inline">
				<input name="leader" id="leader" class="layui-input" /> 
                </div>
              </div>
              
               <div class="layui-form-item">
                <label class="layui-form-label">宿舍人数:</label>
                <div class="layui-input-inline">
				<input name="nums" id="nums" class="layui-input" /> 
                </div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">宿舍楼层:</label>
                <div class="layui-input-inline">
				<input name="floor" id="floor" class="layui-input" /> 
                </div>
              </div>
              
              <br>
                <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" type="submit" id="sub_btn">提交</button>
                  <a href="DormitoryServlet?action=toAddDormitory" class="layui-btn layui-btn-primary" >重置</a>
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
		    	 var bookNo = $("#bookNo").val();
			        var bookName = $("#bookName").val();
			        var bookZz = $("#bookZz").val();
			        var bookCbs = $("#bookCbs").val();
			        var type = $("#type").val();
			        var price = $("#price").val();
			        var bookNum = $("#bookNum").val();
			        var content = $("#content").val();
			       
			       /*  if(bookNo == null || bookNo== ""){
			        	layer.msg('图书编号不能为空！');
			        	return false;
			        }
			        if(bookNum == null || bookNum== ""){
			        	layer.msg('图书库存不能为空！');
			        	return false;
			        }
			        if(bookName == null || bookName== ""){
			        	layer.msg('书名不能为空！');
			        	return false;
			        }
			        if(bookZz == null || bookZz== ""){
			        	layer.msg('作者不能为空！');
			        	return false;
			        }
			        if(bookCbs == null || bookCbs== ""){
			        	layer.msg('出版社不能为空！');
			        	return false;
			        }
			        if(type == null || type== ""){
			        	layer.msg('类型不能为空！');
			        	return false;
			        }
			        if(price == null || price== ""){
			        	layer.msg('价格不能为空！');
			        	return false;
			        }
			        if(content == null || content== ""){
			        	layer.msg('简介不能为空！');
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