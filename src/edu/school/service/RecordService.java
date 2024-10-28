package edu.school.service;

import java.util.List;

import edu.school.entity.Record;
import edu.school.entity.Student;
import edu.school.entity.User;

public interface RecordService {

	List<Record> getRecordListPage(int pageNum, int pageSize,String id);

	int queryRecordCount(String id);

	List<Record> recordListLike(String sname);

	void deleteRecord(String id);

	List<User> selectUserList();

	void addRecord(Record record);

	Record selecRecordById(int parseInt);

	void updateRecord(Record record);

	List<Student> selectStudentList();

}
