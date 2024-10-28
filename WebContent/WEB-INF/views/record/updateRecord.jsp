
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
          <div class="layui-card-header">修改缺勤记录</div>
          <div class="layui-card-body">
             <form action="RecordServlet?action=updateRecord" method="post" id="registerForm" name="registerForm" class="layui-form">
             	<input name="rid" id="rid" value="${record.id }"  type = "hidden" /> 
              <div class="layui-form-item">
	               <label class="layui-form-label">学生:</label>
	                <div class="layui-input-inline">
	                    <select name="stuno" id ="stuno" style="width:13%;height:38px">
	                       <c:forEach items="${studentList}" var="u">
	                        <c:if test="${u.stuno == record.stuno }">
	                           <option value="${u.stuno }" selected>${u.name }</option>
	                        </c:if>
	                         <c:if test="${u.stuno != record.stuno }">
	                           <option value="${u.stuno }" >${u.name }</option>
	                        </c:if>
	                    </c:forEach>
				      </select>
				    </div>
              </div>
                <div class="layui-form-item">
	               <label class="layui-form-label">状态:</label>
	               	                <div class="layui-input-inline">
	               
	                    <select name="status" id ="status" style="width:13%;height:38px">
	                       <c:if test="${record.status == '1' }">
	                       <option value="2" >缺勤</option>
	                       <option value="1" selected>在宿舍</option>
	                       </c:if>
	                       <c:if test="${record.status == '2' }">
	                       <option value="2" selected>缺勤</option>
	                       <option value="1" >在宿舍</option>
	                       </c:if>
				      </select>
				       </div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">宿舍号:</label>
                <div class="layui-input-inline">
				<input name="dno" id="dno" value="${record.dno}" class="layui-input" /> 
                </div>
              </div>
              
               <div class="layui-form-item">
                <label class="layui-form-label">缺勤原因:</label>
                <div class="layui-input-inline">
				<textarea style="width:300px;height:100px" name="detail" id="detail" >${record.detail}</textarea> 
                </div>
              </div>
              
              
              
              
              <br>
                <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" type="submit" id="sub_btn">修改</button>
                  <a href="RecordServlet?action=recordList" class="layui-btn layui-btn-primary" >返回</a>
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
			        var content = $("#content").val();
			       
			     /*    if(bookNo == null || bookNo== ""){
			        	layer.msg('图书编号不能为空！');
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

});
</script>
</body>
</html>