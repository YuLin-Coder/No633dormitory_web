package edu.school.servlet;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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


import edu.school.entity.Qcsh;
import edu.school.entity.Student;
import edu.school.service.QcshService;
import edu.school.service.impl.QcshServiceImpl;

public class QcshServlet extends HttpServlet{
	
	private QcshService service = new QcshServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action=request.getParameter("action");//接受请求的参数
		    if(action != null && action.equals("qcshListLike")) {
		    	qcshListLike(request, response);
			}else if(action != null && action.equals("qcshList")){
				qcshList(request, response);
			}else if(action != null && action.equals("deleteqcsh")){
				deleteqcsh(request, response);
			}else if(action != null && action.equals("toAddqcsh")){
				toAddqcsh(request, response);
			}else if(action != null && action.equals("addqcsh")){
				addqcsh(request, response);
			}else if(action != null && action.equals("agreeqcsh")){
				agreeqcsh(request, response);
			}else if(action != null && action.equals("refuseqcsh")){
				refuseqcsh(request, response);
			}
		    
		}
	
	

	private Qcsh fileUpload(HttpServletRequest request) {
		Qcsh ss=new Qcsh();
		try {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> list = upload.parseRequest(request);
		for(FileItem item : list){
			if(item.isFormField()){
				String name = item.getFieldName();
				String value = item.getString("UTF-8");
				BeanUtils.setProperty(ss, name, value);
			}else{
				String filename = item.getName();
				String savefilename = makeFileName(filename);
				String  savepath=this.getServletContext().getRealPath("/img");
				//String savepath="F:\\workspace\\dormitory_sys\\WebContent\\img\\";
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
				ss.setImgUrl("/img/"+savefilename);
				System.out.println(ss.getImgUrl());
			}
				}
		        return ss;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}

	private String makeFileName(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		return UUID.randomUUID().toString() + "." + ext;
	}

	/**
	 * 拒绝调换
	 * @param request
	 * @param response
	 */
	private void refuseqcsh(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		 try {
				String id = request.getParameter("id");
				service.updateqcsh(id,"2");
				response.sendRedirect(request.getContextPath()+"/QcshServlet?action=qcshList");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * 同意调换
	 * @param request
	 * @param response
	 */
	private void agreeqcsh(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	    try {
			String id = request.getParameter("id");
			service.updateqcsh(id,"1");
			response.sendRedirect(request.getContextPath()+"/QcshServlet?action=qcshList");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 添加报修记录
	 * @param request
	 * @param response
	 */
	private void addqcsh(HttpServletRequest request,
			HttpServletResponse response) {
		Qcsh ss = fileUpload(request);
		ss.setStatus("0");
		ss.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		try {
			request.removeAttribute("message");
			service.addqcsh(ss);
 			response.sendRedirect(request.getContextPath()+"/QcshServlet?action=qcshList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 去添加报修记录页面
	 * @param request
	 * @param response
	 */
	private void toAddqcsh(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			/* List<Dorm> dormList = new DormServiceImpl().selectdormList("");*/
			/*	request.setAttribute("dormList", dormList);*/
			request.getRequestDispatcher("/WEB-INF/views/qcsh/addQcsh.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除报修记录
	 * @param request
	 * @param response
	 */
	private void deleteqcsh(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			service.deleteqcsh(id);
 			response.sendRedirect(request.getContextPath()+"/QcshServlet?action=qcshList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 分页查询报修记录
	 * @param request
	 * @param response
	 */
	private void qcshList(HttpServletRequest request,
			HttpServletResponse response) {
		Object flag = request.getAttribute("flag");
		Student student = (Student)request.getSession().getAttribute("student");
		String id = "";
		if(flag != null && flag.toString().equals("2")){
			id = student.getId().toString();
		}
		String p=request.getParameter("p");//接收页码
        int pageSize=4;//每页显示5条
        int pageNum=1; //默认第一页
        if(p!=null){
            pageNum= Integer.parseInt(p);
        }
        //调用分页查询
        List<Qcsh> list=service.getqcshListPage(pageNum,pageSize,id);
        //携带参数到页面
        int nums=service.queryqcshCount(id); //查询总数
        //计算总页数
        int totalPage=(nums%pageSize==0)? (nums/pageSize):(nums/pageSize+1);
        request.setAttribute("cp",pageNum); //当前页
        request.setAttribute("tp",totalPage); //总页数
		try {
         request.removeAttribute("msg");
         request.setAttribute("list" ,list);
           request.getRequestDispatcher("/WEB-INF/views/qcsh/qcshList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 模糊查询报修记录
	 * @param request
	 * @param response
	 */
	private void qcshListLike(HttpServletRequest request,
			HttpServletResponse response) {
		try {
	   		   String name = request.getParameter("name");
	           List<Qcsh> list=service.selectqcshList(name);
	           request.setAttribute("list" ,list);
	           request.getRequestDispatcher("/WEB-INF/views/qcsh/qcshList.jsp").forward(request, response);
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
