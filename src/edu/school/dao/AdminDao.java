package edu.school.dao;

import edu.school.entity.Admin;

public interface AdminDao {

	Admin selectAdmin(String id);

	void updateAdmin(Admin admin);

}
