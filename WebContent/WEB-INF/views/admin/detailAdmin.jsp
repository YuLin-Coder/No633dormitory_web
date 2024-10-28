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
          <div class="layui-card-header">个人信息查看</div>
          <div class="layui-card-body" >
             <form action="" method="post" >
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
              
              </form>
            </div>
            
          </div>
        </div>
      </div>
    </div>
  </div>
	

<script src="layui/layui.js"></script>
<script src="layui/jquery-1.9.1.min.js"></script>
</body>
</html>