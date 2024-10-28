
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
          <div class="layui-card-header">新增报修</div>
          <div class="layui-card-body">
             <form action="QcshServlet?action=addqcsh" method="post" id="registerForm" name="registerForm" enctype="multipart/form-data"  class="layui-form" >
             				<input name="charger" id="charger" value="${student.name }" type ="hidden" class="layui-input" /> 
             
             			<%--	<input name="s_id" id="s_id" value="${student.id }" type ="hidden" class="layui-input" />  --%>
             
            
                <div class="layui-form-item">
                <label class="layui-form-label">宿舍编号:</label>
                <div class="layui-input-inline">
				<input name="d_no" id="d_no" class="layui-input" /> 
                </div>
              </div>
              
              
                <div class="layui-form-item">
	               <label class="layui-form-label">故障类型:</label>
	                <div class="layui-input-inline">
	                    <select name="type" id ="type" style="width:13%;height:38px">
	                    	    <option value="">请选择故障类型</option>
	                           <option value="水电问题">水电问题</option>
	                           <option value="门窗问题">门窗问题</option>
	                           <option value="阳台问题">阳台问题</option>
	                           <option value="衣柜问题">衣柜问题</option>
	                           <option value="床铺问题">床铺问题</option>
	                           <option value="空调问题">空调问题</option>
	                           <option value="洗衣机问题">洗衣机问题</option>
	                           <option value="宽带问题">宽带问题</option>
	                           <option value="其他问题">其他问题</option>
				      </select>
				    </div>
              </div>
              
              <div class="layui-form-item">
                <label class="layui-form-label">联系方式:</label>
                <div class="layui-input-inline">
				<input name="phone" id="phone" class="layui-input" /> 
                </div>
              </div>
              
               <div class="layui-form-item" >
                <label class="layui-form-label">备注:</label>
                <div class="layui-input-inline">
                      <textarea name="notice" id="notice" placeholder="请输入内容" class="layui-textarea"></textarea>
                </div>
                </div>
                
                
                    <div class="layui-form-item">
                <label class="layui-form-label">上传损坏物品照片:</label>
                <div class="layui-input-inline">
                    <input type="file" name="imgUrl" id="imgUrl" class="layui-input"  /> 
                </div>
               </div>
               
                   <div class="layui-form-item">
                     <label class="layui-form-label">照片展示:</label>
                        <div class="layui-input-inline">
                            <img src="" id="preview_img" width="350px" height="200px" alt="">
                        </div>
               </div> 
              
              
              <br>
                <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" type="submit" id="sub_btn">提交</button>
                  <a href="QcshServlet?action=toAddqcsh" class="layui-btn layui-btn-primary" >重置</a>
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
		/*     $("#sub_btn").click(function () {
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
		    }); */
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