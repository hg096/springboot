package com.cot.blog.test;


// 방식2
/*import lombok.Getter;
import lombok.Setter;*/

/*@Getter
@Setter*/ 
// 방식2 끝


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


// 방식3
@Data
//@AllArgsConstructor
@NoArgsConstructor
// @RequiredArgsConstructor > 생성자에 final 붙이기
public class Member {

	// private 이유 값을 직접적으로 변경하지 않고 메서드를 통해서 변경하기 위해
	// ex) 배고픔지수(private)를 채우기 위해 먹기(메서드)가 필요 > 실행(main)
	private int id;
	private String username;
	private String password;
	private String email;
	
	
	@Builder // 이방식을 사용하게되면 순서를 지키지 않고 값을 지정하고 모두를 지정할 필요없이 자유롭게 지정가능
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	// 방식1
	/*
	 * public Member(int id, String username, String password, String email) {
	 * this.id = id; this.username = username; this.password = password; this.email
	 * = email; } public int getId() { return id; } public void setId(int id) {
	 * this.id = id; } public String getUsername() { return username; } public void
	 * setUsername(String username) { this.username = username; } public String
	 * getPassword() { return password; } public void setPassword(String password) {
	 * this.password = password; } public String getEmail() { return email; } public
	 * void setEmail(String email) { this.email = email; }
	 */

	
	
	
}
