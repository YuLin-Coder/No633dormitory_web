<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
      <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="/WEB-INF/common/form_header.jsp"/>
<body class="layui-layout-body">
  
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect>
            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
            <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
            </a>
          </li>
         
         
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
          
         
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
               <c:if test="${flag == 1}">
              <cite><span>欢迎管理员:</span>
              
              ${admin.nickname}</cite>
              </c:if>
              <c:if test="${flag == 2}">
              <cite><span>欢迎学生:</span>

				<img src="<%=path %>${student.tx}" alt="只是圆形图片" style="width:20px; height:20px; border-radius:100%; overflow:hidden;" />

              ${student.name}</cite>
              </c:if>
               <c:if test="${flag == 3}">
              <cite><span>欢迎宿管:</span>
              				<img src="<%=path %>${user.tx}" alt="只是圆形图片" style="width:20px; height:20px; border-radius:100%; overflow:hidden;" />
              ${user.name}</cite>
              </c:if>
            </a>
            <dl class="layui-nav-child">
             
            
               <dd><a href="LoginServlet?action=LoginOutAct">退出登录</a></dd>
              <dd layadmin-event="logout" style="text-align: center;"></dd>
            </dl>
          </li>
          
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
          <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
            <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
        </ul>
      </div>
      
      <!-- 侧边菜单 -->
      <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
          <div class="layui-logo" lay-href="home/console.html">
            <span>学 生宿  舍  管 理 系 统</span>
          </div>
          
          <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
            <li data-name="home" class="layui-nav-item layui-nav-itemed">
              <a href="javascript:;" lay-tips="主页" lay-direction="2">
                <i class="layui-icon layui-icon-console"></i>
                <cite>主页</cite>
              </a>
              <dl class="layui-nav-child">
                <dd data-name="console" class="layui-this">
                  <a href="LoginServlet?action=welcome"  target="mainFrame">控制台</a>
                </dd>
                
              </dl>
            </li>
            <c:if test="${flag==1}">
            <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-user"></i>
                <cite>个人信息管理</cite>
              </a>
              <dl class="layui-nav-child">
               <dd class="layui-nav-itemed">
                  <a href="AdminServlet?action=detailAdmin&id=${admin.id }" target="mainFrame">查看个人信息</a>
                </dd>
                <dd class="layui-nav-itemed">
                  <a href="AdminServlet?action=toUpdateAdmin&id=${admin.id }" target="mainFrame">编辑个人信息</a>
                </dd>
            </dl>
            </li>
             <li data-name="set" class="layui-nav-item">
              <a href="javascript:;" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>学生管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="StudentServlet?action=studentList" target="mainFrame">学生信息列表</a>
                </dd>
                <dd class="layui-nav-itemed">
                  <a href="StudentServlet?action=toAddStudent" target="mainFrame">新增学生信息</a>
                </dd>
              </dl>
            </li>
            <li data-name="set" class="layui-nav-item">
              <a href="javascript:;" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>宿舍管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
               
                  <dl class="layui-nav-child">
                    <dd><a href="DormitoryServlet?action=dormitoryList" target="mainFrame">宿舍信息列表</a></dd>
                     <dd><a href="DormitoryServlet?action=toAddDormitory" target="mainFrame">新增宿舍信息</a></dd>
                  </dl>
                </dd>
              </dl>
            </li>
            <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>宿管人员管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="UserServlet?action=userList" target="mainFrame">宿管人员列表</a>
                </dd>
                <dd class="layui-nav-itemed">
                 <a href="UserServlet?action=toAddUser" target="mainFrame">新增宿管人员</a>
                </dd>
              </dl>
            </li>
            
            <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>缺勤学生管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="RecordServlet?action=recordList" target="mainFrame">缺勤学生列表</a>
                </dd>
                <dd class="layui-nav-itemed">
                 <a href="RecordServlet?action=toAddRecord" target="mainFrame">新增缺勤学生</a>
                </dd>
              </dl>
            </li>
            
          
            
            
             <li data-name="set" class="layui-nav-item">
              <a href="javascript:;" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>公告管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="BoardServlet?action=boardList" target="mainFrame">公告列表</a>
                </dd>
                <dd class="layui-nav-itemed">
                  <a href="BoardServlet?action=toAddBoard" target="mainFrame">新增公告</a>
                </dd>
              </dl>
            </li>
            
            
            </c:if>
            
            <c:if test="${flag==3}">
            <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-user"></i>
                <cite>个人信息维护管理</cite>
              </a>
              <dl class="layui-nav-child">
               <dd class="layui-nav-itemed">
                  <a href="UserServlet?action=detailUser&uid=${user.id }" target="mainFrame">查看个人信息</a>
                </dd>
                <dd class="layui-nav-itemed">
                  <a href="UserServlet?action=toUpdateUser&uid=${user.id }" target="mainFrame">编辑个人信息</a>
                </dd>
                      
              </dl>
            </li>
            
            <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>缺勤学生管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="RecordServlet?action=recordList" target="mainFrame">缺勤学生列表</a>
                </dd>
                <dd class="layui-nav-itemed">
                 <a href="RecordServlet?action=toAddRecord" target="mainFrame">新增缺勤学生</a>
                </dd>
              </dl>
            </li>
            
               <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>宿舍报修管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="QcshServlet?action=qcshList" target="mainFrame">报修审批</a>
                </dd>
            
              </dl>
            </li>
            
             <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>人员登记管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="RydjServlet?action=rydjList" target="mainFrame">人员登记列表</a>
                </dd>
                <dd class="layui-nav-itemed">
                 <a href="RydjServlet?action=toAddRydj" target="mainFrame">外来人员登记</a>
                </dd>
              </dl>
            </li>
            
             <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>卫生考核管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="WskhServlet?action=wskhList" target="mainFrame">卫生考核列表</a>
                </dd>
                <dd class="layui-nav-itemed">
                 <a href="WskhServlet?action=toAddWskh" target="mainFrame">新增考核</a>
                </dd>
              </dl>
            </li>
             <li data-name="set" class="layui-nav-item">
              <a href="javascript:;" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>宿舍管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
               
                  <dl class="layui-nav-child">
                    <dd><a href="DormitoryServlet?action=dormitoryList" target="mainFrame">宿舍信息列表</a></dd>
                     <dd><a href="DormitoryServlet?action=toAddDormitory" target="mainFrame">新增宿舍信息</a></dd>
                  </dl>
                </dd>
              </dl>
            </li>
            <li data-name="set" class="layui-nav-item">
              <a href="javascript:;" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>公告管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="BoardServlet?action=boardList" target="mainFrame">查看公告</a>
                </dd>
              </dl>
            </li>
          </c:if>
              
              
              
           <c:if test="${flag==2}">
            <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-user"></i>
                <cite>个人信息维护管理</cite>
              </a>
              <dl class="layui-nav-child">
               <dd class="layui-nav-itemed">
                  <a href="StudentServlet?action=detailStudent&stuno=${student.stuno }" target="mainFrame">查看个人信息</a>
                </dd>
                <dd class="layui-nav-itemed">
                  <a href="StudentServlet?action=toUpdateStudent&stuno=${student.stuno }" target="mainFrame">编辑个人信息</a>
                </dd>
                      
              </dl>
            </li>
            
            <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>我的宿舍信息</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="DormitoryServlet?action=dormitoryList&did=${student.d_id }" target="mainFrame">查看宿舍信息</a>
                </dd>
              </dl>
            </li>
             <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>我的缺勤记录</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="RecordServlet?action=recordList&id=${student.stuno }" target="mainFrame">查看缺勤记录</a>
                </dd>
              </dl>
            </li>
            <li data-name="set" class="layui-nav-item">
              <a href="javascript:;" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>公告管理</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="BoardServlet?action=boardList" target="mainFrame">查看公告</a>
                </dd>
              </dl>
            </li>
            
            <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>宿舍报修管理</cite>
              </a>
              <dl class="layui-nav-child">
              <dd class="layui-nav-itemed">
                  <a href="QcshServlet?action=toAddqcsh" target="mainFrame">申请报修</a>
                </dd>
                <dd class="layui-nav-itemed">
                  <a href="QcshServlet?action=qcshList" target="mainFrame">查看我的报修记录</a>
                </dd>
            
              </dl>
            </li>
            
              <li data-name="" class="layui-nav-item">
              <a href="javascript:;" lay-tips="设置" lay-direction="2">
                <i class="layui-icon layui-icon-list"></i>
                <cite>寝室卫生</cite>
              </a>
              <dl class="layui-nav-child">
                <dd class="layui-nav-itemed">
                  <a href="WskhServlet?action=findMyDormWskh" target="mainFrame">查看寝室分数</a>
                </dd>
               
              </dl>
            </li>
            
               
            
          </c:if>
          
          </ul>
        </div>
      </div>

      <!-- 页面标签 -->
      <div class="layadmin-pagetabs" id="LAY_app_tabs">
         <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div> 
        <div class="layui-icon layadmin-tabs-control layui-icon-down">
          <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"></a>
              
            </li>
          </ul>
        </div>
        <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
          <ul class="layui-tab-title" id="LAY_app_tabsheader">
            <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
          </ul>
        </div>
      </div>
      
      
      <!-- 主体内容 -->
      <div class="layui-body" id="LAY_app_body">
        <div class="layadmin-tabsbody-item layui-show">
          <iframe src="LoginServlet?action=welcome" id="mainFrame" name="mainFrame" style="width:100%;height:100%;"  frameborder="0" class="mainFrame"></iframe>
        </div>
      </div>
      
      <!-- 辅助元素，一般用于移动设备下遮罩 -->
      <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
  </div>

<script src="layui/layui.js"></script>
<script>

layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>