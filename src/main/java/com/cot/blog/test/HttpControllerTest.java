package com.cot.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 > 응답(HTML 파일)
// @Controller 
// 사용자가 요청 > 응답(Data)
@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest: ";

	@GetMapping("/http/lombok")
	public String lombokTest() {// new Member(1, "aa", "1234", "a@a.com");
		Member m = Member.builder().username("asd").password("1234").email("asd@aaa.com").build();
		System.out.println(TAG + "getter: " + m.getUsername());
		m.setUsername("dsa");
		System.out.println(TAG + "setter: " + m.getUsername());
		return "lombok test 완료";
	}

	// http://localhost:8080/http/get // get?id=1&username=aa
	@GetMapping("/http/get")
	// Member m으로 한번에 받기
	public String getTest(Member m) { // 각각 받는 방식 > @RequestParam int id, @RequestParam String username >
										// MessageConverter (스프링부트)
		return "get 요청 : " + m.getId() + "/ " + m.getUsername() + "/ " + m.getPassword();
	}

	// http://localhost:8080/http/post
	@PostMapping("/http/post") // text/plan > @RequestBody String text, application/json > @RequestBody Member
								// m
	public String postTest(@RequestBody Member m) { // MessageConverter (스프링부트)
		return "post 요청 : " + m.getId() + "/ " + m.getUsername() + "/ " + m.getPassword();
	}
	/*
	 * @PostMapping("/http/post") public String postTest(Member m) { return
	 * "post 요청"+m.getId()+"/"+m.getUsername()+"/"+m.getPassword(); }
	 */

	// http://localhost:8080/http/put
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + "/ " + m.getUsername() + "/ " + m.getPassword();
	}

	// http://localhost:8080/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청 : ";
	}

}
