package edu.school.entity;

public class Admin {//管理员

	private Integer id;
	private String username;
	private String nickname;
	private String pwd;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Admin(Integer id, String username, String nickname, String pwd) {
		super();
		this.id = id;
		this.username = username;
		this.nickname = nickname;
		this.pwd = pwd;
	}
	public Admin() {
		super();
	}

}
