package edu.school.entity;

public class Dormitory {//宿舍表
	
	private Integer id;
	private String dno;
	private String leader;
	private Integer nums;
	private String floor;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public Integer getNums() {
		return nums;
	}
	public void setNums(Integer nums) {
		this.nums = nums;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public Dormitory(Integer id, String dno, String leader, Integer nums, String floor) {
		super();
		this.id = id;
		this.dno = dno;
		this.leader = leader;
		this.nums = nums;
		this.floor = floor;
	}
	public Dormitory() {
		super();
		// TODO Auto-generated constructor stub
	}


}
