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
import edu.school.service.BoardService;
import edu.school.service.impl.BoardServiceImpl;

public class BoardServlet extends HttpServlet{
	
	private BoardService bs = new BoardServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String action=request.getParameter("action");//接受请求的参数
		    if(action != null && action.equals("boardListLike")) {
		    	boardListLike(request, response);
			}else if(action != null && action.equals("boardList")){
				boardList(request, response);
			}else if(action != null && action.equals("deleteBoard")){
				deleteBoard(request, response);
			}else if(action != null && action.equals("toAddBoard")){
				toAddBoard(request, response);
			}else if(action != null && action.equals("addBoard")){
				addBoard(request, response);
			}else if(action != null && action.equals("toUpdateBoard")){
				toUpdateBoard(request, response);
			}else if(action != null && action.equals("updateBoard")){
				updateBoard(request, response);
			}
		}

	/**
	 * 修改公告
	 * @param request
	 * @param response
	 */
	private void updateBoard(HttpServletRequest request,
			HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("bid");
		Board board = new Board();
		board.setContent(content);
		board.setTitle(title);
		board.setId(Integer.parseInt(id));
		bs.updateBoard(board);
		try {
 			response.sendRedirect(request.getContextPath()+"/BoardServlet?action=boardList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 去修改公告页面
	 * @param request
	 * @param response
	 */
	private void toUpdateBoard(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
	    Board board = bs.selectBoardById(id);
		request.getSession().setAttribute("board", board);
		try {
			request.getRequestDispatcher("/WEB-INF/views/board/updateBoard.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加公告
	 * @param request
	 * @param response
	 */
	private void addBoard(HttpServletRequest request,
			HttpServletResponse response) {
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Board board = new Board();
		board.setContent(content);
		board.setTitle(title);
		board.setEditor(admin.getNickname());
		board.setFbsj(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		try {
			bs.saveBoard(board);
 			response.sendRedirect(request.getContextPath()+"/BoardServlet?action=boardList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 去添加公告页面
	 * @param request
	 * @param response
	 */
	private void toAddBoard(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/WEB-INF/views/board/addBoard.jsp").forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除公告
	 * @param request
	 * @param response
	 */
	private void deleteBoard(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			bs.deleteBoard(id);
 			response.sendRedirect(request.getContextPath()+"/BoardServlet?action=boardList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 分页查询公告
	 * @param request
	 * @param response
	 */
	private void boardList(HttpServletRequest request,
			HttpServletResponse response) {
		String p=request.getParameter("p");//接收页码
        int pageSize=4;//每页显示5条
        int pageNum=1; //默认第一页
        if(p!=null){
            pageNum= Integer.parseInt(p);
        }
        //调用分页查询
        List<Board> list=bs.getBoardListPage(pageNum,pageSize);
        //携带参数到页面
        int nums=bs.queryBoardCount(); //查询总数
        //计算总页数
        int totalPage=(nums%pageSize==0)? (nums/pageSize):(nums/pageSize+1);
        request.setAttribute("cp",pageNum); //当前页
        request.setAttribute("tp",totalPage); //总页数
		try {
         request.removeAttribute("msg");
         request.setAttribute("list" ,list);
           request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 模糊查询公告
	 * @param request
	 * @param response
	 */
	private void boardListLike(HttpServletRequest request,
			HttpServletResponse response) {
		try {
	   		   String name = request.getParameter("name");
	           List<Board> list=bs.selectBoardList(name);
	           request.setAttribute("list" ,list);
	           request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(request, response);
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
