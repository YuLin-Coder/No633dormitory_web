package edu.school.entity;

public class Record {//考情记录表

	private Integer id;
	private String stuno;
	private String name;
	private String dno;
	private String detail;
	private String status;
	private String jlr;
	private String kqrq;
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
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJlr() {
		return jlr;
	}
	public void setJlr(String jlr) {
		this.jlr = jlr;
	}
	public String getKqrq() {
		return kqrq;
	}
	public void setKqrq(String kqrq) {
		this.kqrq = kqrq;
	}
	public Record(Integer id, String stuno, String name, String dno, String detail, String status, String jlr,
			String kqrq) {
		super();
		this.id = id;
		this.stuno = stuno;
		this.name = name;
		this.dno = dno;
		this.detail = detail;
		this.status = status;
		this.jlr = jlr;
		this.kqrq = kqrq;
	}
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

}
