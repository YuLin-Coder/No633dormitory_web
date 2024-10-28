
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
          <div class="layui-card-header">新增缺勤记录</div>
          <div class="layui-card-body">
             <form action="RecordServlet?action=addRecord" method="post" id="registerForm" name="registerForm" class="layui-form" >
              <div class="layui-form-item">
	               <label class="layui-form-label">学生:</label>
	                <div class="layui-input-inline">
	                    <select name="stuno" id ="stuno" style="width:13%;height:38px">
	                       <option value="">请选择学生</option>
	                       <c:forEach items="${studentList}" var="u">
	                       <option value="${u.stuno }" >${u.name }</option>
	                    </c:forEach>
				      </select>
				    </div>
              </div>
              <div class="layui-form-item">
	               <label class="layui-form-label">状态:</label>
	               	                <div class="layui-input-inline">
	               
	                    <select name="status" id ="status" style="width:13%;height:38px">
	                       <option value="2" >缺勤</option>
	                       <option value="1" >在宿舍</option>
	                       
				      </select>
				       </div>
              </div>
               <div class="layui-form-item">
                <label class="layui-form-label">宿舍号:</label>
                <div class="layui-input-inline">
				<input name="dno" id="dno" class="layui-input" /> 
                </div>
              </div>
              
               <div class="layui-form-item">
                <label class="layui-form-label">缺勤原因:</label>
                <div class="layui-input-inline">
				<textarea style="width:300px;height:100px" name="detail" id="detail" ></textarea> 
                </div>
              </div>
              
              <br>
                <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" type="submit" id="sub_btn">提交</button>
                  <a href="RecordServlet?action=toAddRecord" class="layui-btn layui-btn-primary" >重置</a>
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
		    	    var studentId = $("#studentId").val();
			        var bookId = $("#bookId").val();
			       
			       /*  if(studentId == null || studentId== ""){
			        	layer.msg('学生不能为空！');
			        	return false;
			        }
			        if(bookId == null || bookId== ""){
			        	layer.msg('图书不能为空！');
			        	return false;
			        } */
		    });
		    setTimeout(function(){ $("#errorMsg").html("")},3000);
	});
	 setTimeout(function(){ $("#msg3").html("")},3000);

});
</script>
</body>
</html>