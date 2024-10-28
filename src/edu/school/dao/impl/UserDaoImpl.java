package edu.school.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import edu.school.dao.UserDao;
import edu.school.entity.Student;
import edu.school.entity.User;
import edu.school.util.C3p0Utils;

public class UserDaoImpl implements UserDao{
	
	private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());


	@Override
	public List<User> getUserListPage(int pageNum, int pageSize) {
		String sql="select * from user  limit ?,?";
        int startNo=(pageNum-1)*pageSize;
        List<User> list=null;
        try {
            list= runner.query(sql, new BeanListHandler<User>(User.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
            return list;
        } catch (Exception e) {//捕获异常
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public int queryUserCount() {
		String sql="select count(*) from user";
        try {
            Long count =  (Long) runner.query(sql, new ScalarHandler());
            return count.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

	@Override
	public List<User> studentListLike(String uname) {
		String sql="select * from user where 1 = 1";
        List<User> list=null;
        List<String> list1 = new ArrayList<String>();
        Object[] params = {};
        if (uname != null && !uname.equals("")) {
            sql += " and name like  ? ";
            list1.add("%" + uname + "%");
        }
        if(list1.size() > 0){
            params = list1.toArray();
        }
        try {
            list=runner.query(sql, params, new BeanListHandler<User>(User.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		Integer ids = Integer.parseInt(id);
		try {
            runner.update("delete from user where id=?",
            		ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public User selectUserByUserName(String username) {
		try {//返回查询的信息
            return runner.query("select * from user where username =?", 
            		new BeanHandler<User>(User.class),username);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		try {
            runner.update("insert into user (username,tx,pwd,name,sex,phone,floor) values (?,?,?,?,?,?,?)",
            		user.getUsername(),user.getTx(),user.getPwd(),user.getName()
            		,user.getSex(),user.getPhone(),user.getFloor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public User selectUserById(int parseInt) {
		try {//返回查询的信息
            return runner.query("select * from user where id =?", 
            		new BeanHandler<User>(User.class),parseInt);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		if(user.getTx() == null || user.getTx().equals("")){
			try {
	            runner.update("update  user set pwd=?,name=?,sex=?,phone=?,floor=? where username =?",
	            		user.getPwd(),user.getName()
	            		,user.getSex(),user.getPhone(),user.getFloor(),user.getUsername());
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
		}else{
			try {
	            runner.update("update  user set tx=?,pwd=?,name=?,sex=?,phone=?,floor=? where username =?",
	            		user.getTx(),user.getPwd(),user.getName()
	            		,user.getSex(),user.getPhone(),user.getFloor(),user.getUsername());
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
		}
		
	}


}
