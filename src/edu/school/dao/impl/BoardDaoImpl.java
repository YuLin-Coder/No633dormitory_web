package edu.school.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import edu.school.dao.BoardDao;
import edu.school.entity.Board;
import edu.school.util.C3p0Utils;

public class BoardDaoImpl implements BoardDao {

	private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

	@Override
	public void updateBoard(Board board) {
		try {
            runner.update("update  board set content =?,title=? where id = ?",
            		board.getContent(),board.getTitle(),board.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public Board selectBoardById(String id) {
		Integer ids = Integer.parseInt(id);
		try {//返回查询的信息
            return runner.query("select * from board where id =?", 
            		new BeanHandler<Board>(Board.class),ids);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public void saveBoard(Board board) {
		try {
            runner.update("insert into board (title,content,fbsj,editor) values (?,?,?,?)",
            		board.getTitle(),board.getContent(),board.getFbsj(),board.getEditor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void deleteBoard(String id) {
		Integer ids = Integer.parseInt(id);
		try {
            runner.update("delete from board where id=?",
            		ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<Board> getBoardListPage(int pageNum, int pageSize) {
		String sql="select * from board where 1 = 1 order by fbsj desc limit ?,?";
        int startNo=(pageNum-1)*pageSize;
        List<Board> list=null;
        try {
            list= runner.query(sql, new BeanListHandler<Board>(Board.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
            return list;
        } catch (Exception e) {//捕获异常
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public int queryBoardCount() {
		String sql="select count(*) from board";
        try {
            Long count =  (Long) runner.query(sql, new ScalarHandler());
            return count.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<Board> selectBoardList(String name) {
		String sql="select * from board where 1 = 1";
        List<Board> list=null;
        List<String> list1 = new ArrayList<String>();
        Object[] params = {};
        if (name != null && !name.equals("")) {
            sql += " and title like  ? ";
            list1.add("%" + name + "%");
        }
        if(list1.size() > 0){
            params = list1.toArray();
        }
        sql += " order by fbsj desc ";
        try {
            list=runner.query(sql, params, new BeanListHandler<Board>(Board.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}

}
