package edu.school.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import edu.school.dao.AdminDao;
import edu.school.entity.Admin;
import edu.school.util.C3p0Utils;

public class AdminDaoImpl implements AdminDao{

	private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());

	@Override
	public Admin selectAdmin(String id) {
		try {//返回查询的信息
            return runner.query("select * from admin where id =? ", 
            		new BeanHandler<Admin>(Admin.class),id);
        } catch (Exception e) {
            throw new RuntimeException(e);//抛出运行异常
        }
	}

	@Override
	public void updateAdmin(Admin admin) {
		try {
            runner.update("update  admin set username =?,nickname=?,pwd =? where id = ?",
            		admin.getUsername(),admin.getNickname(),admin.getPwd(),admin.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

}
