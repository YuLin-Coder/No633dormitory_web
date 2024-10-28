package edu.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.school.entity.Admin;
import edu.school.entity.Dormitory;
import edu.school.entity.Student;
import edu.school.entity.User;
import edu.school.service.LoginService;
import edu.school.service.impl.LoginServiceImpl;
import edu.school.service.impl.StudentServiceImpl;

public class LoginServlet extends HttpServlet{
	
	private LoginService service = new LoginServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		   String action=request.getParameter("action");//接受请求的参数
		   if(action != null && action.equals("toLogin")) {//去登录页面
		    	toLogin(request, response);
			}else if(action != null && action.equals("login")) {//登录
		    	login(request, response);
			}else if(action != null && action.equals("LoginOutAct")) {//退出
				LoginOutAct(request, response);
			}else if(action != null && action.equals("welcome")) {//默认页面
				welcome(request, response);
			}else if(action != null && action.equals("toRegister")) {//去注册
				toRegister(request, response);
			}
		}

	
	private void toRegister(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Dormitory> dormitoryList = new StudentServiceImpl().selectList();
			request.setAttribute("dormitoryList", dormitoryList);
			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void welcome(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/views/console.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void LoginOutAct(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getSession().removeAttribute("admin");
			request.getSession().removeAttribute("student");
			request.getSession().removeAttribute("user");
			request.getSession().removeAttribute("flag");
			response.sendRedirect(request.getContextPath()+"/LoginServlet?action=toLogin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		removeAll(request,response);
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		if(type.equals("1")){
			Admin admin = new Admin();
			admin.setUsername(userName);
			admin.setPwd(password);
			Admin admin1 = service.selectAdmin(admin);
			  try {
					 if (admin1 == null){
			        	  request.setAttribute("message", "管理员用户不存在或者密码错误");
						  request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			         }else {
			        	  request.getSession().setAttribute("admin",admin1);
			        	  request.getSession().setAttribute("flag",1);
			              request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
			         }				
				 } catch (Exception e) {
				 	e.printStackTrace();
				 }
		 }else if(type.equals("2")){
			 Student student = new Student();
			 student.setStuno(userName);
			 student.setPwd(password);
			 Student student1 = service.selectStudent(student);
			  try {
					 if (student1 == null){
			        	  request.setAttribute("message", "学号不存在或者密码错误");
						  request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			         }else {
			        	  request.getSession().setAttribute("student",student1);
			        	  request.getSession().setAttribute("flag",2);
			              request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
			         }				
				 } catch (Exception e) {
				 	e.printStackTrace();
				 }
		 }else if(type.equals("3")){
			  User user = service.selectUser(userName,password);
			  try {
					 if (user == null){
			        	  request.setAttribute("message", "用户名不存在或者密码错误");
						  request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			         }else {
			        	  request.getSession().setAttribute("user",user);
			        	  request.getSession().setAttribute("flag",3);
			              request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
			         }				
				 } catch (Exception e) {
				 	e.printStackTrace();
				 }
		 }else{
			    request.setAttribute("message", "账号不存在或者密码错误");
			    try {
				request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				} 
		 }
	}


	private void removeAll(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute("admin");
		request.getSession().removeAttribute("student");
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("flag");

	}


	private void toLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
		    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}  
	   	return;
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
