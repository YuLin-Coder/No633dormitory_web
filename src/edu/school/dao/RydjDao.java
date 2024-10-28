package edu.school.dao;

import java.util.List;

import edu.school.entity.Rydj;

public interface RydjDao {//人员登记Dao

	List<Rydj> getRydjListPage(int pageNum, int pageSize);

	int queryRydjCount();

	List<Rydj> RydjListLike(String dno);

	void deleteRydj(int id);

	Rydj query(int id);

	void addRydj(Rydj r);

	void updateRydj(Rydj r);

}
