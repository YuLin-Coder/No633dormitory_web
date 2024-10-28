package edu.school.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import edu.school.dao.LoginDao;
import edu.school.entity.Admin;
import edu.school.entity.Student;
import edu.school.entity.User;
import edu.school.util.C3p0Utils;

public class LoginDaoImpl implements LoginDao{

	private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

	
	@Override
	public Admin selectAdmin(Admin admin) {
		try {//返回查询的信息
            return runner.query("select * from admin where username  =? and pwd =? ", 
            		new BeanHandler<Admin>(Admin.class),admin.getUsername(),admin.getPwd());
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}


	@Override
	public Student selectStudent(Student student) {
		try {//返回查询的信息
            return runner.query("select * from student where stuno  =? and pwd =? ", 
            		new BeanHandler<Student>(Student.class),student.getStuno(),student.getPwd());
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}


	@Override
	public User selectUser(String userName, String password) {
		try {//返回查询的信息
            return runner.query("select * from user where username  =? and pwd =? ", 
            		new BeanHandler<User>(User.class),userName,password);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}

}
