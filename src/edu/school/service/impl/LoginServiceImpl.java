package edu.school.service.impl;

import edu.school.dao.LoginDao;
import edu.school.dao.impl.LoginDaoImpl;
import edu.school.entity.Admin;
import edu.school.entity.Student;
import edu.school.entity.User;
import edu.school.service.LoginService;

public class LoginServiceImpl implements LoginService{
	
	private LoginDao dao = new LoginDaoImpl();

	@Override
	public Admin selectAdmin(Admin admin) {
		return dao.selectAdmin(admin);
	}

	@Override
	public Student selectStudent(Student student) {
		return dao.selectStudent(student);
	}

	@Override
	public User selectUser(String userName, String password) {
		return dao.selectUser(userName,password);
	}

}
