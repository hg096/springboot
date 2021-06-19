package com.cot.blog.test;

public class Member {

	// private 이유 값을 직접적으로 변경하지 않고 메서드를 통해서 변경하기 위해
	// ex) 배고픔지수(private)를 채우기 위해 먹기(메서드)가 필요 > 실행(main)
	private int id;
	private String username;
	private String password;
	private String email;

	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
