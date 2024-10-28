package edu.school.service.impl;

import java.util.List;

import edu.school.dao.BoardDao;
import edu.school.dao.impl.BoardDaoImpl;
import edu.school.entity.Board;
import edu.school.service.BoardService;

public class BoardServiceImpl implements BoardService{
	
	private BoardDao dao = new BoardDaoImpl();

	@Override
	public void updateBoard(Board board) {
		dao.updateBoard(board);
	}

	@Override
	public Board selectBoardById(String id) {
		return dao.selectBoardById(id);
	}

	@Override
	public void saveBoard(Board board) {
		dao.saveBoard(board);
	}

	@Override
	public void deleteBoard(String id) {
		dao.deleteBoard(id);
	}

	@Override
	public List<Board> getBoardListPage(int pageNum, int pageSize) {
		return dao.getBoardListPage(pageNum,pageSize);
	}

	@Override
	public int queryBoardCount() {
		return  dao.queryBoardCount();
	}

	@Override
	public List<Board> selectBoardList(String name) {
		return dao.selectBoardList(name);
	}

}
