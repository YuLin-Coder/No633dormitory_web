package edu.school.dao;

import java.util.List;

import edu.school.entity.Board;

public interface BoardDao {

	void updateBoard(Board board);

	Board selectBoardById(String id);

	void saveBoard(Board board);

	void deleteBoard(String id);

	List<Board> getBoardListPage(int pageNum, int pageSize);

	int queryBoardCount();

	List<Board> selectBoardList(String name);

}
