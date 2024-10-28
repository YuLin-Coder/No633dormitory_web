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
import edu.school.entity.User;
import edu.school.service.UserService;
import edu.school.service.impl.DormitoryServiceImpl;
import edu.school.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet{
	private UserService service = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		String action=request.getParameter("action");//接受请求的参数
	    if(action != null && action.equals("userListLike")) {
	    	userListLike(request, response);
		}else if(action != null && action.equals("userList")){
			userList(request, response);
		}else if(action != null && action.equals("deleteUser")){
			deleteUser(request, response);
		}else if(action != null && action.equals("toAddUser")){
			toAddUser(request, response);
		}else if(action != null && action.equals("addUser")){
			addUser(request, response);
		}else if(action != null && action.equals("toUpdateUser")){
			toUpdateUser(request, response);
		}else if(action != null && action.equals("updateUser")){
			updateUser(request, response);
		}else if(action != null && action.equals("detailUser")){
			detailUser(request, response);
		}
	}

	private void detailUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String uid = request.getParameter("uid");
			User user = service.selectUserById(Integer.parseInt(uid));
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/user/detailUser.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User user = fileUpload(request);
		String flag = request.getSession().getAttribute("flag").toString();

		try {
			service.updateUser(user);
			User user2 = service.selectUserByUserName(user.getUsername());
			if(flag.equals("3")){
	 			response.sendRedirect(request.getContextPath()+"/UserServlet?action=detailUser&uid="+user2.getId());

			}else{
	 			response.sendRedirect(request.getContextPath()+"/UserServlet?action=userList");

			}
			/*bs.saveBook(book);
 			response.sendRedirect(request.getContextPath()+"/BookServlet?action=bookList");*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void toUpdateUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String uid = request.getParameter("uid");
			User user = service.selectUserById(Integer.parseInt(uid));
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/user/updateUser.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		User user = fileUpload(request);
		try {
			User b1 = service.selectUserByUserName(user.getUsername());
			if(b1 != null){
				request.setAttribute("message", "登录账号重复！");
				request.getRequestDispatcher("/WEB-INF/views/user/addUser.jsp").forward(request,response);
			}else{
				request.removeAttribute("message");
				service.addUser(user);
	 			response.sendRedirect(request.getContextPath()+"/UserServlet?action=userList");
			}
			/*bs.saveBook(book);
 			response.sendRedirect(request.getContextPath()+"/BookServlet?action=bookList");*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private User fileUpload(HttpServletRequest request) {
		User user=new User();
		try {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> list = upload.parseRequest(request);
		for(FileItem item : list){
			if(item.isFormField()){
				String name = item.getFieldName();
				String value = item.getString("UTF-8");
				BeanUtils.setProperty(user, name, value);
			}else{
				String filename = item.getName();
				if(filename == null || filename.equals("")){
					System.out.println("没有文件操作");
					item.delete();
				}else{
					String savefilename = makeFileName(filename);
					/*String savepath="F:\\workspace\\dormitory_web\\WebContent\\img\\";*/
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
					user.setTx("/img/"+savefilename);
					System.out.println(user.getTx());
				}

				
			}
				}
		        return user;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
	}
	
	private String makeFileName(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		return UUID.randomUUID().toString() + "." + ext;
	}

	private void toAddUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getRequestDispatcher("/WEB-INF/views/user/addUser.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		try {
			service.deleteUser(id);
 			response.sendRedirect(request.getContextPath()+"/UserServlet?action=userList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void userList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String p=request.getParameter("p");//接收页码
        int pageSize=4;//每页显示5条
        int pageNum=1; //默认第一页
        if(p!=null){
            pageNum= Integer.parseInt(p);
        }
        //调用分页查询
        List<User> list=service.getUserListPage(pageNum,pageSize);
//        if(list !=null && list.size()>0){
//        	for(int i =0;i<list.size();i++){
//        		Dormitory d = new DormitoryServiceImpl().selectDormitoryById(list.get(i).getD_id().toString());
//        	    list.get(i).setFloor(d!=null?d.getFloor():"");
//        	}
//        }
        //携带参数到页面
        int nums=service.queryUserCount(); //查询总数
        //计算总页数
        int totalPage=(nums%pageSize==0)? (nums/pageSize):(nums/pageSize+1);
        request.setAttribute("cp",pageNum); //当前页
        request.setAttribute("tp",totalPage); //总页数
		try {
         request.removeAttribute("msg");
         request.setAttribute("list" ,list);
           request.getRequestDispatcher("/WEB-INF/views/user/userList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void userListLike(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
	   		   String uname = request.getParameter("uname");
	           List<User> list=service.studentListLike(uname);
	           request.setAttribute("list" ,list);
	           request.getRequestDispatcher("/WEB-INF/views/user/userList.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
