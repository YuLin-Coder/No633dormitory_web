package edu.school.dao;

import edu.school.entity.Admin;
import edu.school.entity.Student;
import edu.school.entity.User;

public interface LoginDao {

	Admin selectAdmin(Admin admin);

	Student selectStudent(Student student);

	User selectUser(String userName, String password);

}
