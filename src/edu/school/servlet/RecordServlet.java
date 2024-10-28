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
import edu.school.entity.Dormitory;
import edu.school.entity.Record;
import edu.school.entity.Student;
import edu.school.entity.User;
import edu.school.service.RecordService;
import edu.school.service.impl.DormitoryServiceImpl;
import edu.school.service.impl.RecordServiceImpl;
import edu.school.service.impl.StudentServiceImpl;

public class RecordServlet extends HttpServlet{
	
	private RecordService service = new RecordServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		String action=request.getParameter("action");//接受请求的参数
	    if(action != null && action.equals("recordListLike")) {
	    	recordListLike(request, response);
		}else if(action != null && action.equals("recordList")){
			recordList(request, response);
		}else if(action != null && action.equals("deleteRecord")){
			deleteRecord(request, response);
		}else if(action != null && action.equals("toAddRecord")){
			toAddRecord(request, response);
		}else if(action != null && action.equals("addRecord")){
			addRecord(request, response);
		}else if(action != null && action.equals("toUpdateRecord")){
			toUpdateRecord(request, response);
		}else if(action != null && action.equals("updateRecord")){
			updateRecord(request, response);
		}
		}
	private void updateRecord(HttpServletRequest request, HttpServletResponse response) {
		String stuno = request.getParameter("stuno");
		String dno = request.getParameter("dno");
		String detail = request.getParameter("detail");
		String status = request.getParameter("status");
		String rid = request.getParameter("rid");

		Record record = new Record();
		record.setDetail(detail);
		record.setStuno(stuno);
		record.setName(new StudentServiceImpl().selectBookByNo(stuno).getName());
		record.setDno(dno);
		record.setStatus(status);
		record.setId(Integer.parseInt(rid));
		try {
			service.updateRecord(record);
 			response.sendRedirect(request.getContextPath()+"/RecordServlet?action=recordList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void toUpdateRecord(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String id = request.getParameter("id");
			Record record = service.selecRecordById(Integer.parseInt(id));
			List<Student> studentList = service.selectStudentList();
			request.setAttribute("studentList", studentList);
			request.setAttribute("record", record);
			request.getRequestDispatcher("/WEB-INF/views/record/updateRecord.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void addRecord(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String flag = request.getSession().getAttribute("flag").toString();
		Record record = new Record();
		if(flag.equals("1")){
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			record.setJlr(admin.getNickname());
		}else{
			User user = (User)request.getSession().getAttribute("user");
			record.setJlr(user.getName());
			
		}
		String stuno = request.getParameter("stuno");
		String dno = request.getParameter("dno");
		String detail = request.getParameter("detail");
		String status = request.getParameter("status");
		String jlr = request.getParameter("jlr");
		record.setDetail(detail);
		record.setStuno(stuno);
		record.setName(new StudentServiceImpl().selectBookByNo(stuno).getName());
		record.setDno(dno);
		record.setStatus(status);
		record.setKqrq(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		try {
			service.addRecord(record);
 			response.sendRedirect(request.getContextPath()+"/RecordServlet?action=recordList");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void toAddRecord(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			List<Student> studentList = service.selectStudentList();
			request.setAttribute("studentList", studentList);
			request.getRequestDispatcher("/WEB-INF/views/record/addRecord.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void deleteRecord(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		try {
			service.deleteRecord(id);
 			response.sendRedirect(request.getContextPath()+"/RecordServlet?action=recordList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void recordList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");//接收页码

		String p=request.getParameter("p");//接收页码
        int pageSize=4;//每页显示5条
        int pageNum=1; //默认第一页
        if(p!=null){
            pageNum= Integer.parseInt(p);
        }
        //调用分页查询
        List<Record> list=service.getRecordListPage(pageNum,pageSize,id);
//        if(list !=null && list.size()>0){
//        	for(int i =0;i<list.size();i++){
//        		Dormitory d = new DormitoryServiceImpl().selectDormitoryById(list.get(i).getD_id().toString());
//        	    list.get(i).setFloor(d!=null?d.getFloor():"");
//        	}
//        }
        //携带参数到页面
        int nums=service.queryRecordCount(id); //查询总数
        //计算总页数
        int totalPage=(nums%pageSize==0)? (nums/pageSize):(nums/pageSize+1);
        request.setAttribute("cp",pageNum); //当前页
        request.setAttribute("tp",totalPage); //总页数
		try {
         request.removeAttribute("msg");
         request.setAttribute("list" ,list);
           request.getRequestDispatcher("/WEB-INF/views/record/recordList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void recordListLike(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
	   		   String sname = request.getParameter("sname");
	           List<Record> list=service.recordListLike(sname);
	           request.setAttribute("list" ,list);
	           request.getRequestDispatcher("/WEB-INF/views/record/recordList.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
