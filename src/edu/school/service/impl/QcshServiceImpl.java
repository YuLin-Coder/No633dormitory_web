package edu.school.service.impl;

import java.util.List;

import edu.school.dao.QcshDao;
import edu.school.dao.impl.QcshDaoImpl;
import edu.school.entity.Qcsh;
import edu.school.service.QcshService;

public class QcshServiceImpl implements QcshService{
	
	private QcshDao dao = new QcshDaoImpl();

	@Override
	public List<Qcsh> selectqcshList(String name) {
		// TODO Auto-generated method stub
		return dao.selectqcshList(name);
	}

	@Override
	public List<Qcsh> getqcshListPage(int pageNum, int pageSize, String id) {
		// TODO Auto-generated method stub
		return dao.getqcshListPage(pageNum,pageSize,id);
	}

	@Override
	public int queryqcshCount(String id) {
		// TODO Auto-generated method stub
		return  dao.queryqcshCount(id);
	}

	@Override
	public void deleteqcsh(String id) {
		// TODO Auto-generated method stub
		dao.deleteqcsh(id);
	}

	@Override
	public void addqcsh(Qcsh ss) {
		// TODO Auto-generated method stub
		dao.addqcsh(ss);
	}

	@Override
	public void updateqcsh(String id, String string) {
		// TODO Auto-generated method stub
		dao.updateqcsh(id,string);
	}

}
