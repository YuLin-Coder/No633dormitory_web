package edu.school.service.impl;

import java.util.List;

import edu.school.dao.DormitoryDao;
import edu.school.dao.impl.DormitoryDaoImpl;
import edu.school.entity.Dormitory;
import edu.school.service.DormitoryService;

public class DormitoryServiceImpl implements DormitoryService{
	
	private DormitoryDao dao = new DormitoryDaoImpl();

	@Override
	public List<Dormitory> dormitoryListLike(String dno) {
		return dao.dormitoryListLike(dno);
	}

	@Override
	public List<Dormitory> getDormitoryListPage(int pageNum, int pageSize,String did) {
		return dao.getDormitoryListPage(pageNum,pageSize,did);
	}

	@Override
	public int queryDormitoryCount(String did) {
		return dao.queryDormitoryCount(did);
	}

	@Override
	public void deleteDormitory(String id) {
		 dao.deleteDormitory(id);
	}

	@Override
	public void addDormitory(Dormitory d) {
		 dao.addDormitory(d);
	}

	@Override
	public Dormitory selectDormitoryById(String id) {
		return  dao.selectDormitoryById(id);
	}

	@Override
	public void updateDormitory(Dormitory d) {
		dao.updateDormitory(d);
	}

	@Override
	public Dormitory selectDormitoryByNo(String dno) {
		// TODO Auto-generated method stub
		return dao.selectDormitoryByNo(dno);
	}

}
