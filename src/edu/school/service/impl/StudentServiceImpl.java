package edu.school.service.impl;

import java.util.List;

import edu.school.dao.StudentDao;
import edu.school.dao.impl.StudentDaoImpl;
import edu.school.entity.Dormitory;
import edu.school.entity.Student;
import edu.school.service.StudentService;

public class StudentServiceImpl implements StudentService{
	
	private StudentDao dao = new StudentDaoImpl();

	@Override
	public List<Student> getStudentListPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		return dao.getStudentListPage(pageNum,pageSize);
	}

	@Override
	public int queryStudentCount() {
		// TODO Auto-generated method stub
		return dao.queryStudentCount();
	}

	@Override
	public List<Student> studentListLike(String sname) {
		// TODO Auto-generated method stub
		return dao.studentListLike(sname);
	}

	@Override
	public void deleteStudent(String id) {
		// TODO Auto-generated method stub
		dao.deleteStudent(id);
	}

	@Override
	public Student selectBookByNo(String stuno) {
		// TODO Auto-generated method stub
		return dao.selectBookByNo(stuno);
	}

	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		dao.addStudent(student);
	}

	@Override
	public List<Dormitory> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		dao.updateStudent(student);
	}

}
