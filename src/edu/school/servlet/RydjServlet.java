package edu.school.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.school.entity.Rydj;
import edu.school.service.RydjService;
import edu.school.service.impl.RydjServiceImpl;

public class RydjServlet  extends HttpServlet{
	
	private RydjService service = new  RydjServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action=request.getParameter("action");//接受请求的参数
		    if(action != null && action.equals("rydjListLike")) {
		    	rydjListLike(request, response);
			}else if(action != null && action.equals("rydjList")){
				rydjList(request, response);
			}else if(action != null && action.equals("deleteRydj")){
				deleteRydj(request, response);
			}else if(action != null && action.equals("toAddRydj")){
				toAddRydj(request, response);
			}else if(action != null && action.equals("addRydj")){
				addRydj(request, response);
			}else if(action != null && action.equals("toUpdateRydj")){
				toUpdateRydj(request, response);
			}else if(action != null && action.equals("updateRydj")){
				updateRydj(request, response);
			}
		}

	//修改人员登记信息

	private void updateRydj(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Rydj rydj = new Rydj();
		rydj.setDno(request.getParameter("dno"));
		rydj.setName(request.getParameter("name"));
		rydj.setPhone(request.getParameter("phone"));
		rydj.setSname(request.getParameter("sname"));
		rydj.setNote(request.getParameter("note"));
		rydj.setId(Integer.parseInt(request.getParameter("id")));
		
		try {
			service.updateRydj(rydj);			
	 		response.sendRedirect(request.getContextPath()+"/RydjServlet?action=rydjList");		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void toUpdateRydj(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			int id=Integer.parseInt(request.getParameter("id"));			
			Rydj rydj = service.findById(id);			
			request.setAttribute("rydj", rydj);
			request.getRequestDispatcher("/WEB-INF/views/rydj/updateRydj.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//添加人员登记
	private void addRydj(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	
		Rydj rydj = new Rydj();
		rydj.setDno(request.getParameter("dno"));
		rydj.setName(request.getParameter("name"));
		rydj.setPhone(request.getParameter("phone"));
		rydj.setSname(request.getParameter("sname"));
		rydj.setNote(request.getParameter("note"));
		try {
			service.addRydj(rydj);	
	 		response.sendRedirect(request.getContextPath()+"/RydjServlet?action=rydjList");		
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}

	
	
	//跳转到人员登记界面
	private void toAddRydj(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {			
			request.getRequestDispatcher("/WEB-INF/views/rydj/addRydj.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteRydj(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(request.getParameter("id"));		
		try {
			service.deleteRydj(id);
 			response.sendRedirect(request.getContextPath()+"/RydjServlet?action=rydjList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void rydjList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String p=request.getParameter("p");//接收页码
        int pageSize=4;//每页显示5条
        int pageNum=1; //默认第一页
        if(p!=null){
            pageNum= Integer.parseInt(p);
        }
        //调用分页查询
        List<Rydj> list=service.getRydjListPage(pageNum,pageSize);
      /**/
        //携带参数到页面
        int nums=service.queryRydjCount(); //查询总数
        //计算总页数
        int totalPage=(nums%pageSize==0)? (nums/pageSize):(nums/pageSize+1);
        request.setAttribute("cp",pageNum); //当前页
        request.setAttribute("tp",totalPage); //总页数
		try {
         request.removeAttribute("msg");
         request.setAttribute("list" ,list);
           request.getRequestDispatcher("/WEB-INF/views/rydj/rydjList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//模糊查询
	private void rydjListLike(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
	   		   String dno = request.getParameter("dno");
	           List<Rydj> list=service.RydjListLike(dno);
	           request.setAttribute("list" ,list);
	           request.getRequestDispatcher("/WEB-INF/views/rydj/listRydj.jsp").forward(request, response);
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
