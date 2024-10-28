package edu.school.entity;

import java.util.Date;

public class Wskh {//卫生考核

	private Integer id;
	

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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getJlr() {
		return jlr;
	}
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	public String getNote() {
		return note;
	}
	public Wskh(Integer id, String dno, String score, Date create_time, String jlr, String note) {
		super();
		this.id = id;
		this.dno = dno;
		this.score = score;
		this.create_time = create_time;
		this.jlr = jlr;
		this.note = note;
	}
	public Wskh() {
		super();
	}
	public void setNote(String note) {
		this.note = note;
	}
	private String dno;
	private String score;
	
	private Date create_time;
	private String jlr;
	private String note;

	

}
