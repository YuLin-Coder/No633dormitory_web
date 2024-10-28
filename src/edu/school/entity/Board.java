package edu.school.entity;

public class Board {//公告
	private Integer id;
	private String title;
	private String editor;
	private String content;
	private String fbsj;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFbsj() {
		return fbsj;
	}
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Board(Integer id, String title, String editor, String content, String fbsj) {
		super();
		this.id = id;
		this.title = title;
		this.editor = editor;
		this.content = content;
		this.fbsj = fbsj;
	}

}
