package edu.school.dao;

import java.util.List;

import edu.school.entity.Dormitory;
import edu.school.entity.Student;

public interface StudentDao {

	List<Student> getStudentListPage(int pageNum, int pageSize);

	int queryStudentCount();

	List<Student> studentListLike(String sname);

	void deleteStudent(String id);

	Student selectBookByNo(String stuno);

	void addStudent(Student student);

	List<Dormitory> selectList();

	void updateStudent(Student student);

}
