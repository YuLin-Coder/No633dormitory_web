package edu.school.dao;

import java.util.List;

import edu.school.entity.Wskh;

public interface WskhDao {//卫生考核Dao

	List<Wskh> getWskhListPage(int pageNum, int pageSize);

	int queryWskhCount();

	List<Wskh> WskhListLike(String dno);
	List<Wskh> findByDno(String dno);

	void deleteWskh(int id);

	Wskh query(int  id);

	void addWskh(Wskh w);


	void updateWskh(Wskh w);

}
