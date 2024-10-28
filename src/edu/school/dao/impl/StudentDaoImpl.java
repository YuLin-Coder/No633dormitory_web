package edu.school.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import edu.school.dao.StudentDao;
import edu.school.entity.Dormitory;
import edu.school.entity.Student;
import edu.school.util.C3p0Utils;

public class StudentDaoImpl implements StudentDao{
	
	private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());


	@Override
	public List<Student> getStudentListPage(int pageNum, int pageSize) {
		String sql="select * from student  limit ?,?";
        int startNo=(pageNum-1)*pageSize;
        List<Student> list=null;
        try {
            list= runner.query(sql, new BeanListHandler<Student>(Student.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
            return list;
        } catch (Exception e) {//捕获异常
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public int queryStudentCount() {
		String sql="select count(*) from student";
        try {
            Long count =  (Long) runner.query(sql, new ScalarHandler());
            return count.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<Student> studentListLike(String sname) {
		String sql="select * from student where 1 = 1";
        List<Student> list=null;
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
            list=runner.query(sql, params, new BeanListHandler<Student>(Student.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}

	@Override
	public void deleteStudent(String id) {
		Integer ids = Integer.parseInt(id);
		try {
            runner.update("delete from student where id=?",
            		ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public Student selectBookByNo(String stuno) {
		try {//返回查询的信息
            return runner.query("select * from student where stuno =?", 
            		new BeanHandler<Student>(Student.class),stuno);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		try {
            runner.update("insert into student (stuno,tx,pwd,name,major,bj,sex,phone,d_id) values (?,?,?,?,?,?,?,?,?)",
            		student.getStuno(),student.getTx(),student.getPwd(),student.getName(),student.getMajor(),student.getBj()
            		,student.getSex(),student.getPhone(),student.getD_id());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


	}

	@Override
	public List<Dormitory> selectList() {
		String sql="select * from dormitory";
        List<Dormitory> list=null;
        try {
            list=runner.query(sql, new BeanListHandler<Dormitory>(Dormitory.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}

	@Override
	public void updateStudent(Student student) {
		if(student.getTx() == null || student.getTx().equals("")){
			try {
	            runner.update("update student set pwd=?,name=?,major=?,bj=?,sex=?,phone=?,d_id=? where stuno=?",
	            		student.getPwd(),student.getName(),student.getMajor(),student.getBj()
	            		,student.getSex(),student.getPhone(),student.getD_id(),student.getStuno());
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
		}else{
			try {
	            runner.update("update student set tx=?,pwd=?,name=?,major=?,bj=?,sex=?,phone=?,d_id=? where stuno=?",
	            		student.getTx(),student.getPwd(),student.getName(),student.getMajor(),student.getBj()
	            		,student.getSex(),student.getPhone(),student.getD_id(),student.getStuno());
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
		}
		// TODO Auto-generated method stub
		
	}

}
