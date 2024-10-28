package edu.school.service.impl;

import java.util.List;

import edu.school.dao.RecordDao;
import edu.school.dao.impl.RecordDaoImpl;
import edu.school.entity.Record;
import edu.school.entity.Student;
import edu.school.entity.User;
import edu.school.service.RecordService;

public class RecordServiceImpl implements RecordService{
	
	private RecordDao dao = new RecordDaoImpl();

	@Override
	public List<Record> getRecordListPage(int pageNum, int pageSize,String id) {
		// TODO Auto-generated method stub
		return dao.getRecordListPage(pageNum,pageSize,id);
	}

	@Override
	public int queryRecordCount(String id) {
		// TODO Auto-generated method stub
		return dao.queryRecordCount(id);
	}

	@Override
	public List<Record> recordListLike(String sname) {
		// TODO Auto-generated method stub
		return dao.recordListLike(sname);
	}

	@Override
	public void deleteRecord(String id) {
		// TODO Auto-generated method stub
		dao.deleteRecord(id);
	}

	@Override
	public List<User> selectUserList() {
		// TODO Auto-generated method stub
		return dao.selectUserList();
	}

	@Override
	public void addRecord(Record record) {
		// TODO Auto-generated method stub
		dao.addRecord(record);
	}

	@Override
	public Record selecRecordById(int id) {
		// TODO Auto-generated method stub
		return dao.selecRecordById(id);
	}

	@Override
	public void updateRecord(Record record) {
		// TODO Auto-generated method stub
		dao.updateRecord(record);
	}

	@Override
	public List<Student> selectStudentList() {
		// TODO Auto-generated method stub
		return dao.selectStudentList();
	}

}
