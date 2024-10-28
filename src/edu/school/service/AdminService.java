package edu.school.service;

import edu.school.entity.Admin;

public interface AdminService {

	Admin selectAdmin(String id);

	void updateAdmin(Admin admin);

}
