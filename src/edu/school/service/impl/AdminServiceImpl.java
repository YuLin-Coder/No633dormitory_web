package edu.school.service.impl;

import edu.school.dao.AdminDao;
import edu.school.dao.impl.AdminDaoImpl;
import edu.school.entity.Admin;
import edu.school.service.AdminService;

public class AdminServiceImpl implements AdminService{
	
	private AdminDao dao = new AdminDaoImpl();

	@Override
	public Admin selectAdmin(String id) {
		return dao.selectAdmin(id);
	}

	@Override
	public void updateAdmin(Admin admin) {
		 dao.updateAdmin(admin);
	}

}
