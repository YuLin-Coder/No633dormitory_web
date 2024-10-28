package edu.school.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.school.entity.Admin;
import edu.school.service.AdminService;
import edu.school.service.impl.AdminServiceImpl;

public class AdminServlet extends HttpServlet{

	private AdminService service = new AdminServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		   String action=request.getParameter("action");//接受请求的参数
		   if(action != null && action.equals("toUpdateAdmin")) {//去修改管理员数据页面
			   toUpdateAdmin(request, response);
			}else if(action != null && action.equals("updateAdmin")) {//修改管理员
		    	updateAdmin(request, response);
			}else if(action != null && action.equals("detailAdmin")) {//详情
				detailAdmin(request, response);
			}
		}

	private void detailAdmin(HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	String id = request.getParameter("id");
			Admin admin = service.selectAdmin(id);
	    	request.setAttribute("adminDate", admin);
		    request.getRequestDispatcher("/WEB-INF/views/admin/detailAdmin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateAdmin(HttpServletRequest request, HttpServletResponse response) {
    	String adminId = request.getParameter("adminId");
    	String username = request.getParameter("username");
    	String pwd = request.getParameter("pwd");
    	String nickname = request.getParameter("nickname");
    	Admin admin = new Admin();
    	admin.setId(Integer.parseInt(adminId));
    	admin.setUsername(username);
    	admin.setPwd(pwd);
    	admin.setNickname(nickname);
    	 try {
 			service.updateAdmin(admin);
	    	request.setAttribute("adminDate", admin);
 		    request.getRequestDispatcher("/WEB-INF/views/admin/updateAdmin.jsp").forward(request, response);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}

		
	}

	private void toUpdateAdmin(HttpServletRequest request, HttpServletResponse response) {
	    try {
	    	String id = request.getParameter("id");
			Admin admin = service.selectAdmin(id);
	    	request.setAttribute("adminDate", admin);
		    request.getRequestDispatcher("/WEB-INF/views/admin/updateAdmin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
