package edu.school.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.school.entity.Admin;
import edu.school.entity.Board;
import edu.school.entity.Dormitory;
import edu.school.entity.Student;
import edu.school.service.DormitoryService;
import edu.school.service.impl.DormitoryServiceImpl;

public class DormitoryServlet extends HttpServlet {
	
	private DormitoryService service = new  DormitoryServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action=request.getParameter("action");//接受请求的参数
		    if(action != null && action.equals("dormitoryListLike")) {
		    	dormitoryListLike(request, response);
			}else if(action != null && action.equals("dormitoryList")){
				dormitoryList(request, response);
			}else if(action != null && action.equals("deleteDormitory")){
				deleteDormitory(request, response);
			}else if(action != null && action.equals("toAddDormitory")){
				toAddDormitory(request, response);
			}else if(action != null && action.equals("addDormitory")){
				addDormitory(request, response);
			}else if(action != null && action.equals("toUpdateDormitory")){
				toUpdateDormitory(request, response);
			}else if(action != null && action.equals("updateDormitory")){
				updateDormitory(request, response);
			}
		}
	
	private void updateDormitory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String did = request.getParameter("did");
		String dno = request.getParameter("dno");
		String leader = request.getParameter("leader");
		String nums = request.getParameter("nums");
		String floor = request.getParameter("floor");
		Dormitory d = new Dormitory();
		d.setDno(dno);
		d.setLeader(leader);
		d.setNums(Integer.parseInt(nums));
		d.setFloor(floor);
		d.setId(Integer.parseInt(did));
		service.updateDormitory(d);
		try {
 			response.sendRedirect(request.getContextPath()+"/DormitoryServlet?action=dormitoryList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void toUpdateDormitory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String id = request.getParameter("id");
			Dormitory d = service.selectDormitoryById(id);
			request.getSession().setAttribute("dormitory", d);
			request.getRequestDispatcher("/WEB-INF/views/dormitory/updateDormitory.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addDormitory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String dno = request.getParameter("dno");
		String leader = request.getParameter("leader");
		String nums = request.getParameter("nums");
		String floor = request.getParameter("floor");
		Dormitory d = new Dormitory();
		d.setDno(dno);
		d.setLeader(leader);
		d.setNums(Integer.parseInt(nums));
		d.setFloor(floor);
		try {
			Dormitory  dd = service.selectDormitoryByNo(dno);
			if(dd != null){
				request.setAttribute("message", "编号重复！");
				request.getRequestDispatcher("/WEB-INF/views/dormitory/addDormitory.jsp").forward(request,response);
			}else{
				service.addDormitory(d);
	 			response.sendRedirect(request.getContextPath()+"/DormitoryServlet?action=dormitoryList");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void toAddDormitory(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/views/dormitory/addDormitory.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteDormitory(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			service.deleteDormitory(id);
 			response.sendRedirect(request.getContextPath()+"/DormitoryServlet?action=dormitoryList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//分页查询宿舍信息
	private void dormitoryList(HttpServletRequest request, HttpServletResponse response) {
		String did=request.getParameter("did");//接收页码

		String p=request.getParameter("p");//接收页码
        int pageSize=4;//每页显示5条
        int pageNum=1; //默认第一页	
        if(p!=null){
            pageNum= Integer.parseInt(p);
        }
        //调用分页查询
        List<Dormitory> list=service.getDormitoryListPage(pageNum,pageSize,did);
        //携带参数到页面
        int nums=service.queryDormitoryCount(did); //查询总数
        //计算总页数
        int totalPage=(nums%pageSize==0)? (nums/pageSize):(nums/pageSize+1);
        request.setAttribute("cp",pageNum); //当前页
        request.setAttribute("tp",totalPage); //总页数
		try {
         request.removeAttribute("msg");
         request.setAttribute("list" ,list);
           request.getRequestDispatcher("/WEB-INF/views/dormitory/dormitoryList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//模糊查询宿舍信息
	private void dormitoryListLike(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
	   		   String floor = request.getParameter("floor");
	           List<Dormitory> list=service.dormitoryListLike(floor);
	           request.setAttribute("list" ,list);
	           request.getRequestDispatcher("/WEB-INF/views/dormitory/dormitoryList.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
