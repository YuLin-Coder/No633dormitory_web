package edu.school.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import edu.school.dao.RydjDao;
import edu.school.entity.Rydj;
import edu.school.util.C3p0Utils;

public class RydjDaoImpl implements RydjDao{
	
	private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());


	@Override
	public List<Rydj> getRydjListPage(int pageNum, int pageSize) {
		String sql="select * from rydj  limit ?,?";
        int startNo=(pageNum-1)*pageSize;
        List<Rydj> list=null;
        try {
            list= runner.query(sql, new BeanListHandler<Rydj>(Rydj.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
            return list;
        } catch (Exception e) {//捕获异常
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public int queryRydjCount() {
		String sql="select count(*) from rydj";
        try {
            Long count =  (Long) runner.query(sql, new ScalarHandler());
            return count.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

	public List<Rydj> RydjListLike(String dno) {
		String sql="select * from rydj where 1 = 1";
        List<Rydj> list=null;
        List<String> list1 = new ArrayList<String>();
        Object[] params = {};
        if (dno != null && !dno.equals("")) {
            sql += " and dno like  ? ";
            list1.add("%" + dno + "%");
        }
        if(list1.size() > 0){
            params = list1.toArray();
        }
        try {
            list=runner.query(sql, params, new BeanListHandler<Rydj>(Rydj.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}

	@Override
	public void deleteRydj(int id) {
	
		try {
            runner.update("delete from rydj where id=?",
            		id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}


	public Rydj query(int id) {
		try {//返回查询的信息
            return runner.query("select * from rydj where id =?", 
            		new BeanHandler<Rydj>(Rydj.class),id);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}


	public void addRydj(Rydj rydj) {
		// TODO Auto-generated method stub
		try {
            runner.update("insert into rydj (name,dno,phone,sname,note,sex,djsj) values (?,?,?,?,?,?,now())",
            		rydj.getName(),rydj.getDno(),rydj.getPhone(),rydj.getSname(),rydj.getSex(),rydj.getNote());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


	}


	
	public void updateRydj(Rydj rydj) {	
			try {
	            runner.update("update rydj set name=?,dno=?,phone=?,sname=?,note=?,sex=? where id=?",
	            		rydj.getName(),rydj.getDno(),rydj.getPhone(),rydj.getSname(),rydj.getNote(),rydj.getSex(),rydj.getId());
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
		
		// TODO Auto-generated method stub
		
	}


}
