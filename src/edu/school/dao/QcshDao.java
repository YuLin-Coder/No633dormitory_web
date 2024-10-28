package edu.school.dao;

import java.util.List;

import edu.school.entity.Qcsh;

public interface QcshDao {

	List<Qcsh> selectqcshList(String name);

	List<Qcsh> getqcshListPage(int pageNum, int pageSize, String id);

	int queryqcshCount(String id);

	void deleteqcsh(String id);

	void addqcsh(Qcsh ss);

	void updateqcsh(String id, String string);

}
