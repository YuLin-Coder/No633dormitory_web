package edu.school.service;

import java.util.List;

import edu.school.entity.Board;

public interface BoardService {

	void updateBoard(Board board);

	Board selectBoardById(String id);

	void saveBoard(Board board);

	void deleteBoard(String id);

	List<Board> getBoardListPage(int pageNum, int pageSize);

	int queryBoardCount();

	List<Board> selectBoardList(String name);
	

}
