package edu.school.service.impl;

import java.util.List;

import edu.school.dao.WskhDao;
import edu.school.dao.impl.WskhDaoImpl;
import edu.school.entity.Wskh;
import edu.school.service.WskhService;

public class WskhServiceImpl implements WskhService{
	private WskhDao dao=new WskhDaoImpl();

	@Override
	public List<Wskh> getWskhListPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getWskhListPage(pageNum, pageSize);
	}

	@Override
	public int queryWskhCount() {
		// TODO Auto-generated method stub
		return dao.queryWskhCount();
	}

	@Override
	public List<Wskh> WskhListLike(String dno) {
		// TODO Auto-generated method stub
		return dao.WskhListLike(dno);
	}

	@Override
	public List<Wskh> findByDno(String dno) {
		// TODO Auto-generated method stub
		return dao.findByDno(dno);
	}

	@Override
	public void deleteWskh(int id) {
		// TODO Auto-generated method stub
		dao.deleteWskh(id);
	}

	@Override
	public Wskh findById(int id) {
		// TODO Auto-generated method stub
		return dao.query(id);
	}

	@Override
	public void addWskh(Wskh w) {
		// TODO Auto-generated method stub
		dao.addWskh(w);
	}

	@Override
	public void updateWskh(Wskh w) {
		// TODO Auto-generated method stub
		dao.updateWskh(w);
	}

}
