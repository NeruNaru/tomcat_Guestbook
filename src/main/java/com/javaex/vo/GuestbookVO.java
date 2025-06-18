package com.javaex.vo;

import java.time.LocalDateTime;

public class GuestbookVO {
	//field
	private int no;
	private String name;
	private String password;
	private String content;
	private LocalDateTime regDate;
	//editor
	public GuestbookVO() {
		super();
	}
	public GuestbookVO(String name, String password, String content, LocalDateTime regDate) {
		super();
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = regDate;
	}

	public GuestbookVO(int no, String name, String password, String content, LocalDateTime regDate) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.content = content;
		this.regDate = regDate;
	}
	//method g/s
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	//method normal
	@Override
	public String toString() {
		return "GuestbookVO [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content
				+ ", regDate=" + regDate + "]";
	}
}
