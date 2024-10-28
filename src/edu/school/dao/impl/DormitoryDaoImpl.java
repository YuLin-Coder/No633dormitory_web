package edu.school.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import edu.school.dao.DormitoryDao;
import edu.school.entity.Board;
import edu.school.entity.Dormitory;
import edu.school.util.C3p0Utils;

public class DormitoryDaoImpl implements DormitoryDao {
	
	private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

	@Override
	public List<Dormitory> dormitoryListLike(String floor) {
		String sql="select * from dormitory where 1 = 1";
        List<Dormitory> list=null;
        List<String> list1 = new ArrayList<String>();
        Object[] params = {};
        if (floor != null && !floor.equals("")) {
            sql += " and floor like  ? ";
            list1.add("%" + floor + "%");
        }
        if(list1.size() > 0){
            params = list1.toArray();
        }
        try {
            list=runner.query(sql, params, new BeanListHandler<Dormitory>(Dormitory.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}

	@Override
	public List<Dormitory> getDormitoryListPage(int pageNum, int pageSize,String did) {
		if(did != null && !did.equals("")){
			String sql="select * from dormitory where id =?  limit ?,?";
	        int startNo=(pageNum-1)*pageSize;
	        List<Dormitory> list=null;
	        try {
	            list= runner.query(sql, new BeanListHandler<Dormitory>(Dormitory.class),new Object[] {did,startNo,pageSize});//添加实体类的适配器进行类的反射
	            return list;
	        } catch (Exception e) {//捕获异常
	            throw new RuntimeException(e);//抛出运行异常
	        }
		}else{
			String sql="select * from dormitory  limit ?,?";
	        int startNo=(pageNum-1)*pageSize;
	        List<Dormitory> list=null;
	        try {
	            list= runner.query(sql, new BeanListHandler<Dormitory>(Dormitory.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
	            return list;
	        } catch (Exception e) {//捕获异常
	            throw new RuntimeException(e);//抛出运行异常
	        }
		}
		
	}

	@Override
	public int queryDormitoryCount(String did) {
		if(did != null && !did.equals("")){
			String sql="select count(*) from dormitory where id =?";
	        try {
	            Long count =  (Long) runner.query(sql,did, new ScalarHandler());
	            return count.intValue();
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
		}else{
			String sql="select count(*) from dormitory";
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
	public void deleteDormitory(String id) {
		Integer ids = Integer.parseInt(id);
		try {
            runner.update("delete from dormitory where id=?",
            		ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void addDormitory(Dormitory d) {
		try {
            runner.update("insert into dormitory (dno,leader,nums,floor) values (?,?,?,?)",
            	d.getDno(),d.getLeader(),d.getNums(),d.getFloor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

	}

	@Override
	public Dormitory selectDormitoryById(String id) {
		Integer ids = Integer.parseInt(id);
		try {//返回查询的信息
            return runner.query("select * from dormitory where id =?", 
            		new BeanHandler<Dormitory>(Dormitory.class),ids);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public void updateDormitory(Dormitory d) {
		try {
            runner.update("update  dormitory set dno =?,leader=?,nums=?,floor=? where id = ?",
            		d.getDno(),d.getLeader(),d.getNums(),d.getFloor(),d.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

	}

	@Override
	public Dormitory selectDormitoryByNo(String dno) {
		Integer dnos = Integer.parseInt(dno);
		try {//返回查询的信息
            return runner.query("select * from dormitory where dno =?", 
            		new BeanHandler<Dormitory>(Dormitory.class),dnos);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}


}
