package edu.school.entity;

public class User {//宿舍管理员人
	private Integer id;
	private String username;
	private String tx;
	private String name;
	private String phone;
	private String sex;
	private String pwd;
	private String floor;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTx() {
		return tx;
	}
	public void setTx(String tx) {
		this.tx = tx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public User(Integer id, String username, String tx, String name, String phone, String sex, String pwd,
			String floor) {
		super();
		this.id = id;
		this.username = username;
		this.tx = tx;
		this.name = name;
		this.phone = phone;
		this.sex = sex;
		this.pwd = pwd;
		this.floor = floor;
	}
	public User() {
		super();
	}

}
