package edu.school.service.impl;

import java.util.List;

import edu.school.dao.RydjDao;
import edu.school.dao.impl.RydjDaoImpl;
import edu.school.entity.Rydj;
import edu.school.service.RydjService;

public class RydjServiceImpl implements RydjService{
	private RydjDao dao=new RydjDaoImpl();

	@Override
	public List<Rydj> getRydjListPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getRydjListPage(pageNum, pageSize);
	}

	@Override
	public int queryRydjCount() {
		// TODO Auto-generated method stub
		return dao.queryRydjCount();
	}

	@Override
	public List<Rydj> RydjListLike(String dno) {
		// TODO Auto-generated method stub
		return dao.RydjListLike(dno);
	}

	@Override
	public void deleteRydj(int id) {
		// TODO Auto-generated method stub
		dao.deleteRydj(id);
	}

	@Override
	public Rydj findById(int id) {
		// TODO Auto-generated method stub
		return dao.query(id);
	}

	@Override
	public void addRydj(Rydj r) {
		// TODO Auto-generated method stub
		dao.addRydj(r);
	}

	@Override
	public void updateRydj(Rydj r) {
		// TODO Auto-generated method stub
		dao.updateRydj(r);
	}

}
