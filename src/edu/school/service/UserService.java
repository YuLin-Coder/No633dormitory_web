package edu.school.service;

import java.util.List;

import edu.school.entity.Student;
import edu.school.entity.User;

public interface UserService {

	List<User> getUserListPage(int pageNum, int pageSize);

	int queryUserCount();

	List<User> studentListLike(String uname);

	void deleteUser(String id);

	User selectUserByUserName(String username);

	void addUser(User user);

	User selectUserById(int parseInt);

	void updateUser(User user);

}
