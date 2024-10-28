package edu.school.service;

import edu.school.entity.Admin;
import edu.school.entity.Student;
import edu.school.entity.User;

public interface LoginService {

	Admin selectAdmin(Admin admin);

	Student selectStudent(Student student);

	User selectUser(String userName, String password);

}
