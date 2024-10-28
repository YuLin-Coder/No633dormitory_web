package edu.school.entity;

public class Student {//学生表

	private Integer id;
	private String stuno;
	private String tx;
	private String pwd;
	private String name;
	private String major;
	private String bj;
	private String sex;
	private String phone;
	private Integer d_id;
	private String floor;
    private String te;
	
	public String getTe() {
		return te;
	}
	public void setTe(String te) {
		this.te = te;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getTx() {
		return tx;
	}
	public void setTx(String tx) {
		this.tx = tx;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getD_id() {
		return d_id;
	}
	public void setD_id(Integer d_id) {
		this.d_id = d_id;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(Integer id, String stuno, String tx, String pwd, String name, String major, String bj, String sex,
			String phone, Integer d_id) {
		super();
		this.id = id;
		this.stuno = stuno;
		this.tx = tx;
		this.pwd = pwd;
		this.name = name;
		this.major = major;
		this.bj = bj;
		this.sex = sex;
		this.phone = phone;
		this.d_id = d_id;
	}

}
