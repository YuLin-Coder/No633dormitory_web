package edu.school.dao;

import java.util.List;

import edu.school.entity.Dormitory;

public interface DormitoryDao {

	List<Dormitory> dormitoryListLike(String dno);

	List<Dormitory> getDormitoryListPage(int pageNum, int pageSize,String did);

	int queryDormitoryCount(String did);

	void deleteDormitory(String id);

	void addDormitory(Dormitory d);

	Dormitory selectDormitoryById(String id);

	void updateDormitory(Dormitory d);

	Dormitory selectDormitoryByNo(String dno);

}
