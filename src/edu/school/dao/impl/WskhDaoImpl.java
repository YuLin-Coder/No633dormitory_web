package edu.school.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import edu.school.dao.WskhDao;
import edu.school.entity.Wskh;
import edu.school.util.C3p0Utils;

public class WskhDaoImpl implements WskhDao{
	
	private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());
	@Override
	public List<Wskh> findByDno(String dno) {
		// TODO Auto-generated method stub
		String sql="select * from wskh where dno=?";
        List<Wskh> list=null;
        
        try {
            list=runner.query(sql, dno, new BeanListHandler<Wskh>(Wskh.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}

	@Override
	public List<Wskh> getWskhListPage(int pageNum, int pageSize) {
		String sql="select * from wskh  limit ?,?";
        int startNo=(pageNum-1)*pageSize;
        List<Wskh> list=null;
        try {
            list= runner.query(sql, new BeanListHandler<Wskh>(Wskh.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
            return list;
        } catch (Exception e) {//捕获异常
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public int queryWskhCount() {
		String sql="select count(*) from wskh";
        try {
            Long count =  (Long) runner.query(sql, new ScalarHandler());
            return count.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

	public List<Wskh> WskhListLike(String dno) {
		String sql="select * from wskh where 1 = 1";
        List<Wskh> list=null;
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
            list=runner.query(sql, params, new BeanListHandler<Wskh>(Wskh.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}

	@Override
	public void deleteWskh(int id) {
	
		try {
            runner.update("delete from wskh where id=?",
            		id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}


	public Wskh query(int id) {
		try {//返回查询的信息
            return runner.query("select * from wskh where id =?", 
            		new BeanHandler<Wskh>(Wskh.class),id);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}


	public void addWskh(Wskh w) {
		// TODO Auto-generated method stub
		try {
            runner.update("insert into wskh (dno,score,jlr,note,create_time) values (?,?,?,?,now())",
            		w.getDno(),w.getScore(),w.getJlr(),w.getNote());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


	}


	
	public void updateWskh(Wskh w) {	
			try {
	            runner.update("update wskh set dno=?,score=?,jlr=?,note=? where id=?",
	            		w.getDno(),w.getScore(),w.getJlr(),w.getNote(),w.getId());
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
		
		// TODO Auto-generated method stub
		
	}




}
