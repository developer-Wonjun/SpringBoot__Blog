package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일) --> @Controller


//사용자가 요청 -> 응답(Data)을 해주는것이 컨트롤러.
@RestController 
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("wonjun").password("1234").email("asdf@asdf.com").build();
		System.out.println(TAG + "getter : "+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG + "getter : "+m.getUsername());
		return "lombok test 완료";
	}
	@GetMapping("/http/get") //id=1&username=ssar&password=1234&email=ssar@nate.com =>MessageConverter 
	public String getTest(Member m ) { //멤버오브젝트를 매개변수로
		return "get 요청" + m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	} //get방식으로 요청할때 어떤데이터를 요청하는지는 쿼리스트링 방법뿐.
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { //MessageConverter 
		return "post 요청 : "+ m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : "+ m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}


}
