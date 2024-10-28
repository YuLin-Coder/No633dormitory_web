package edu.school.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.school.entity.Dormitory;
import edu.school.entity.Student;
import edu.school.entity.Wskh;
import edu.school.service.WskhService;
import edu.school.service.impl.DormitoryServiceImpl;
import edu.school.service.impl.WskhServiceImpl;

public class WskhServlet  extends HttpServlet{//处理卫生考核的请求
	
	private WskhService service = new  WskhServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action=request.getParameter("action");//接受请求的参数
		    if(action != null && action.equals("wskhListLike")) {
		    	wskhListLike(request, response);
			}else if(action != null && action.equals("wskhList")){
				wskhList(request, response);
			}else if(action != null && action.equals("deleteWskh")){
				deleteWskh(request, response);
			}else if(action != null && action.equals("toAddWskh")){
				toAddWskh(request, response);
			}else if(action != null && action.equals("addWskh")){
				addWskh(request, response);
			}else if(action != null && action.equals("toUpdateWskh")){
				toUpdateWskh(request, response);
			}else if(action != null && action.equals("updateWskh")){
				updateWskh(request, response);
			}else if(action != null && action.equals("findMyDormWskh")){
				findMyDormWskh(request, response);
			}
		}

	//修改卫生考核

	private void updateWskh(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Wskh wskh = new Wskh();
		wskh.setDno(request.getParameter("dno"));
		wskh.setScore(request.getParameter("score"));
		wskh.setJlr(request.getParameter("jlr"));	
		wskh.setNote(request.getParameter("note"));
		wskh.setId(Integer.parseInt(request.getParameter("id")));
		try {
			service.updateWskh(wskh);			
	 		response.sendRedirect(request.getContextPath()+"/WskhServlet?action=wskhList");		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void toUpdateWskh(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			int id=Integer.parseInt(request.getParameter("id"));			
			Wskh wskh = service.findById(id);			
			request.setAttribute("wskh", wskh);
			request.getRequestDispatcher("/WEB-INF/views/wskh/updateWskh.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//添加人员登记
	private void addWskh(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		Wskh wskh = new Wskh();
		wskh.setDno(request.getParameter("dno"));
		wskh.setScore(request.getParameter("score"));
		wskh.setJlr(request.getParameter("jlr"));	
		wskh.setNote(request.getParameter("note"));
		try {
			service.addWskh(wskh);	
	 		response.sendRedirect(request.getContextPath()+"/WskhServlet?action=wskhList");		
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}

	
	
	//跳转到人员登记界面
	private void toAddWskh(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {			
			request.getRequestDispatcher("/WEB-INF/views/wskh/addWskh.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteWskh(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));		
		try {
			service.deleteWskh(id);
 			response.sendRedirect(request.getContextPath()+"/WskhServlet?action=wskhList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//我宿舍的打分
	private void findMyDormWskh(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Student student=(Student) request.getSession().getAttribute("student");
		Dormitory dormitory=new DormitoryServiceImpl().selectDormitoryById(String.valueOf(student.getD_id()));
	System.out.println(dormitory.getDno()+student.getId()+"123456"); 
	if(dormitory!=null) {
			try {
				List<Wskh> list=service.findByDno(dormitory.getDno());
				  request.setAttribute("list" ,list);
		           request.getRequestDispatcher("/WEB-INF/views/wskh/myWshkList.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			PrintWriter out = response.getWriter();			
			out.write("<script>");
			out.write("alert('请先登录');");
			out.write("location.href='LoginServlet?action=loginOut'");
			out.write("</script>");
			out.close();	
		}
		
	}
	
	
	

	private void wskhList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String p=request.getParameter("p");//接收页码
        int pageSize=4;//每页显示5条
        int pageNum=1; //默认第一页
        if(p!=null){
            pageNum= Integer.parseInt(p);
        }
        //调用分页查询
        List<Wskh> list=service.getWskhListPage(pageNum,pageSize);
      /**/
        //携带参数到页面
        int nums=service.queryWskhCount(); //查询总数
        //计算总页数
        int totalPage=(nums%pageSize==0)? (nums/pageSize):(nums/pageSize+1);
        request.setAttribute("cp",pageNum); //当前页
        request.setAttribute("tp",totalPage); //总页数
		try {
         request.removeAttribute("msg");
         request.setAttribute("list" ,list);
           request.getRequestDispatcher("/WEB-INF/views/wskh/wskhList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//模糊查询
	private void wskhListLike(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
	   		   String dno = request.getParameter("dno");
	           List<Wskh> list=service.WskhListLike(dno);
	           request.setAttribute("list" ,list);
	           request.getRequestDispatcher("/WEB-INF/views/wskh/listWskh.jsp").forward(request, response);
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
