package edu.school.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import edu.school.dao.RecordDao;
import edu.school.entity.Record;
import edu.school.entity.Student;
import edu.school.entity.User;
import edu.school.util.C3p0Utils;

public class RecordDaoImpl implements RecordDao{
	
	private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

	@Override
	public List<Record> getRecordListPage(int pageNum, int pageSize,String id) {
		if(id != null && !id.equals("")){
			String sql="select * from record where stuno =? limit ?,?";
	        int startNo=(pageNum-1)*pageSize;
	        List<Record> list=null;
	        try {
	            list= runner.query(sql, new BeanListHandler<Record>(Record.class),new Object[] {id,startNo,pageSize});//添加实体类的适配器进行类的反射
	            return list;
	        } catch (Exception e) {//捕获异常
	            throw new RuntimeException(e);//抛出运行异常
	        }
		}else{
			String sql="select * from record  limit ?,?";
	        int startNo=(pageNum-1)*pageSize;
	        List<Record> list=null;
	        try {
	            list= runner.query(sql, new BeanListHandler<Record>(Record.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
	            return list;
	        } catch (Exception e) {//捕获异常
	            throw new RuntimeException(e);//抛出运行异常
	        }
		}
		
	}

	@Override
	public int queryRecordCount(String id) {
		if(id != null && !id.equals("")){
			String sql="select count(*) from record where stuno =? ";
	        try {
	            Long count =  (Long) runner.query(sql, id,new ScalarHandler());
	            return count.intValue();
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
		}else{
			String sql="select count(*) from record";
	        try {
	            Long count =  (Long) runner.query(sql, new ScalarHandler());
	            return count.intValue();
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
		}
		
	}

	@Override
	public List<Record> recordListLike(String sname) {
		String sql="select * from record where 1 = 1";
        List<Record> list=null;
        List<String> list1 = new ArrayList<String>();
        Object[] params = {};
        if (sname != null && !sname.equals("")) {
            sql += " and name like  ? ";
            list1.add("%" + sname + "%");
        }
        if(list1.size() > 0){
            params = list1.toArray();
        }
        try {
            list=runner.query(sql, params, new BeanListHandler<Record>(Record.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}

	@Override
	public void deleteRecord(String id) {
		// TODO Auto-generated method stub
		Integer ids = Integer.parseInt(id);
		try {
            runner.update("delete from record where id=?",
            		ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<User> selectUserList() {
		String sql="select * from record ";
        List<User> list=null;
        try {
            list=runner.query(sql, new BeanListHandler<User>(User.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}

	@Override
	public void addRecord(Record record) {
		// TODO Auto-generated method stub
		try {
            runner.update("insert into record (stuno,name,dno,detail,status,jlr,kqrq) values (?,?,?,?,?,?,?)",
            		record.getStuno(),record.getName(),record.getDno(),record.getDetail(),record.getStatus(),
            		record.getJlr(),record.getKqrq());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

	}

	@Override
	public Record selecRecordById(int id) {
		try {//返回查询的信息
            return runner.query("select * from record where id =?", 
            		new BeanHandler<Record>(Record.class),id);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public void updateRecord(Record record) {
		// TODO Auto-generated method stub
		try {
            runner.update("update record set stuno=?,name=?,dno=?,detail=?,status=? where id =?",
            		record.getStuno(),record.getName(),record.getDno(),record.getDetail(),record.getStatus(),
            		record.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<Student> selectStudentList() {
		String sql="select * from student ";
        List<Student> list=null;
        try {
            list=runner.query(sql, new BeanListHandler<Student>(Student.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}


}
