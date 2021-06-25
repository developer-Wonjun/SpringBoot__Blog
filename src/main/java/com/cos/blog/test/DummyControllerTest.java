package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	//{id} 주소로 파마레터를 전달 받을 수 있음.
	@GetMapping("/dummy/user/{id}")
	public User detail (@PathVariable int id) {
		//값이 없으면 객체 하나 만들어서 유저에 넣어라..! => orElseGet
		//Supplier 타입 => 인터페이스. 익명객체를 만들어줌.
		//인터페이스는 new할 수 없다. new할라면 익명객체를 만들어야함.
		//따라서 오버라이드해서 익명객체 생성했음.
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id :" + id);
			}
		});
		return user;
	}
	
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate: "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
