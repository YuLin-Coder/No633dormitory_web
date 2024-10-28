package edu.school.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.school.entity.Dormitory;
import edu.school.entity.Student;
import edu.school.service.StudentService;
import edu.school.service.impl.DormitoryServiceImpl;
import edu.school.service.impl.StudentServiceImpl;

public class StudentServlet  extends HttpServlet{
	
	private StudentService service = new  StudentServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action=request.getParameter("action");//接受请求的参数
		    if(action != null && action.equals("studentListLike")) {
		    	studentListLike(request, response);
			}else if(action != null && action.equals("studentList")){
				studentList(request, response);
			}else if(action != null && action.equals("deleteStudent")){
				deleteStudent(request, response);
			}else if(action != null && action.equals("toAddStudent")){
				toAddStudent(request, response);
			}else if(action != null && action.equals("addStudent")){
				addStudent(request, response);
			}else if(action != null && action.equals("toUpdateStudent")){
				toUpdateStudent(request, response);
			}else if(action != null && action.equals("updateStudent")){
				updateStudent(request, response);
			}else if(action != null && action.equals("detailStudent")){
				detailStudent(request, response);
			}
		}

	private void detailStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String stuno = request.getParameter("stuno");
			Student student = service.selectBookByNo(stuno);
			Dormitory d = new DormitoryServiceImpl().selectDormitoryById(student.getD_id().toString());
			student.setFloor(d!=null?d.getFloor():"");
			request.setAttribute("student", student);
			request.getRequestDispatcher("/WEB-INF/views/student/detailStudent.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Student student = fileUpload(request);
		String flag = request.getSession().getAttribute("flag").toString();

		try {
			service.updateStudent(student);
			if(flag.equals("2")){
	 			response.sendRedirect(request.getContextPath()+"/StudentServlet?action=detailStudent&stuno="+student.getStuno());
			}else{
	 			response.sendRedirect(request.getContextPath()+"/StudentServlet?action=studentList");
			}
			/*bs.saveBook(book);
 			response.sendRedirect(request.getContextPath()+"/BookServlet?action=bookList");*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void toUpdateStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String stuno = request.getParameter("stuno");
			Student student = service.selectBookByNo(stuno);
			List<Dormitory> dormitoryList = service.selectList();
			request.setAttribute("dormitoryList", dormitoryList);
			request.setAttribute("student", student);
			request.getRequestDispatcher("/WEB-INF/views/student/updateStudent.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Student student = fileUpload(request);
		try {
			Student b1 = service.selectBookByNo(student.getStuno());
			if(student.getTe()!= null && student.getTe().equals("1")){
				if(b1 != null){
					request.setAttribute("message", "学号重复！");
					request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request,response);
				}else{
					request.removeAttribute("message");
					service.addStudent(student);
				    request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
				}
			}else{
				if(b1 != null){
					request.setAttribute("message", "学号重复！");
					request.getRequestDispatcher("/WEB-INF/views/student/addStudent.jsp").forward(request,response);
				}else{
					request.removeAttribute("message");
					service.addStudent(student);
		 			response.sendRedirect(request.getContextPath()+"/StudentServlet?action=studentList");
				}
			}
			
			
			/*bs.saveBook(book);
 			response.sendRedirect(request.getContextPath()+"/BookServlet?action=bookList");*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Student fileUpload(HttpServletRequest request) {
		Student book=new Student();
		try {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> list = upload.parseRequest(request);
		for(FileItem item : list){
			if(item.isFormField()){
				String name = item.getFieldName();
				String value = item.getString("UTF-8");
				BeanUtils.setProperty(book, name, value);
			}else{
				String filename = item.getName();
				if(filename == null || filename.equals("")){
					System.out.println("没有文件操作");
					item.delete();
				}else{
					String savefilename = makeFileName(filename);
					//String savepath="F:\\workspace\\dormitory_web\\WebContent\\img\\";
					String savepath=this.getServletContext().getRealPath("/img");
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savepath + "\\" + savefilename);
					int len = 0;
					byte buffer[] = new byte[1024];
					while((len = in.read(buffer)) > 0){
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
					book.setTx("/img/"+savefilename);
					System.out.println(book.getTx());
				}

				
			}
				}
		        return book;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}
	
	private String makeFileName(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		return UUID.randomUUID().toString() + "." + ext;
	}

	private void toAddStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			List<Dormitory> dormitoryList = service.selectList();
			request.setAttribute("dormitoryList", dormitoryList);
			request.getRequestDispatcher("/WEB-INF/views/student/addStudent.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		try {
			service.deleteStudent(id);
 			response.sendRedirect(request.getContextPath()+"/StudentServlet?action=studentList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void studentList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String p=request.getParameter("p");//接收页码
        int pageSize=4;//每页显示5条
        int pageNum=1; //默认第一页
        if(p!=null){
            pageNum= Integer.parseInt(p);
        }
        //调用分页查询
        List<Student> list=service.getStudentListPage(pageNum,pageSize);
        if(list !=null && list.size()>0){
        	for(int i =0;i<list.size();i++){
        		Dormitory d = new DormitoryServiceImpl().selectDormitoryById(list.get(i).getD_id().toString());
        	    list.get(i).setFloor(d!=null?d.getFloor():"");
        	}
        }
        //携带参数到页面
        int nums=service.queryStudentCount(); //查询总数
        //计算总页数
        int totalPage=(nums%pageSize==0)? (nums/pageSize):(nums/pageSize+1);
        request.setAttribute("cp",pageNum); //当前页
        request.setAttribute("tp",totalPage); //总页数
		try {
         request.removeAttribute("msg");
         request.setAttribute("list" ,list);
           request.getRequestDispatcher("/WEB-INF/views/student/studentList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void studentListLike(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
	   		   String sname = request.getParameter("sname");
	           List<Student> list=service.studentListLike(sname);
	           request.setAttribute("list" ,list);
	           request.getRequestDispatcher("/WEB-INF/views/student/studentList.jsp").forward(request, response);
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
