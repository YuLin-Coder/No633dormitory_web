package edu.school.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import edu.school.dao.QcshDao;
import edu.school.entity.Qcsh;
import edu.school.util.C3p0Utils;

public class QcshDaoImpl implements QcshDao{
	
	private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());


	@Override
	public List<Qcsh> selectqcshList(String name) {
		String sql="select * from qcsh where 1 = 1";
        List<Qcsh> list=null;
        List<String> list1 = new ArrayList<String>();
        Object[] params = {};
        if (name != null && !name.equals("")) {
            sql += " and charger like  ? ";
            list1.add("%" + name + "%");
        }
        if(list1.size() > 0){
            params = list1.toArray();
        }
        try {
            list=runner.query(sql, params, new BeanListHandler<Qcsh>(Qcsh.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
	}


	@Override
	public List<Qcsh> getqcshListPage(int pageNum, int pageSize, String id) {
		int startNo=(pageNum-1)*pageSize;
        List<Qcsh> list=null;
        try {
        	if(id != null && !id.equals("")){
        		String sql="select * from qcsh  limit ?,?";
                list= runner.query(sql, new BeanListHandler<Qcsh>(Qcsh.class),new Object[] {id,startNo,pageSize});//添加实体类的适配器进行类的反射

        	}else{
        		String sql="select * from qcsh  limit ?,?";
                list= runner.query(sql, new BeanListHandler<Qcsh>(Qcsh.class),new Object[] {startNo,pageSize});//添加实体类的适配器进行类的反射
        	}
            return list;
        } catch (Exception e) {//捕获异常
            throw new RuntimeException(e);//抛出运行异常
        }
	}


	@Override
	public int queryqcshCount(String id) {
		 try {
	           
	            if(id != null && !id.equals("")){
	            	String sql="select count(*) from qcsh    ";
	       		    Long count =  (Long) runner.query(sql,id, new ScalarHandler());
	                return count.intValue();
	
	        	}else{
	        	  	String sql="select count(*) from qcsh";
	        		 Long count =  (Long) runner.query(sql, new ScalarHandler());
	                 return count.intValue();
	        	}
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	}


	@Override
	public void deleteqcsh(String id) {
		// TODO Auto-generated method stub
		Integer ids = Integer.parseInt(id);
		try {
            runner.update("delete from qcsh where id=?",
            		ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}


	@Override
	public void addqcsh(Qcsh ss) {
		// TODO Auto-generated method stub
		try {
            runner.update("insert into qcsh (d_no,Type,ImgUrl,Notice,create_time,charger,phone,status) values (?,?,?,?,?,?,?,?)",
            		ss.getD_no(),ss.getType(),ss.getImgUrl(),ss.getNotice()
            		,ss.getCreate_time(),ss.getCharger(),ss.getPhone(),"0");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

	}


	@Override
	public void updateqcsh(String id, String qcsh) {
		// TODO Auto-generated method stub
		try {
            runner.update("update  qcsh set status=? where id = ?",
            		qcsh ,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

}
