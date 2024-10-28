package edu.school.entity;

import java.util.Date;

public class Rydj {//人员登记表

	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Date getDjsj() {
		return djsj;
	}
	public void setDjsj(Date djsj) {
		this.djsj = djsj;
	}
	public String getNote() {
		return note;
	}
	public Rydj() {
		super();
	}
	public void setNote(String note) {
		this.note = note;
	}
	private String name;
	private String dno;
	private String phone;
	private String sname;
	private Date djsj;
	private String note;
	private String sex;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	

}
