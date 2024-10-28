package edu.school.service.impl;

import java.util.List;

import edu.school.dao.UserDao;
import edu.school.dao.impl.UserDaoImpl;
import edu.school.entity.Student;
import edu.school.entity.User;
import edu.school.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao dao = new UserDaoImpl();

	@Override
	public List<User> getUserListPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getUserListPage(pageNum,pageSize);
	}

	@Override
	public int queryUserCount() {
		// TODO Auto-generated method stub
		return dao.queryUserCount();
	}

	@Override
	public List<User> studentListLike(String uname) {
		// TODO Auto-generated method stub
		return dao.studentListLike(uname);
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		dao.deleteUser(id);
	}

	@Override
	public User selectUserByUserName(String username) {
		// TODO Auto-generated method stub
		return dao.selectUserByUserName(username);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		dao.addUser(user);
	}

	@Override
	public User selectUserById(int parseInt) {
		// TODO Auto-generated method stub
		return dao.selectUserById(parseInt);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		dao.updateUser(user);
	}

}
