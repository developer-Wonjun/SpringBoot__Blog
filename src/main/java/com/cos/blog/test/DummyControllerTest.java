package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


//html파일이 아니라 data를 리턴해주는 controller = RestController (Rest API)
@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입
	private UserRepository userRepository;
	
	//save함수는 id를 전달하지 않으면 insert를 해주고
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
	
	@DeleteMapping("dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
		userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제 실패 : 해당 아이디는 존재하지 않습니다.";
 		}
		return "삭제되었습니다." + id;
	}
	
	@Transactional //save를 하지않아도 update가 된다. //함수 종료시에 자동 commit이 됨 
	@PutMapping("/dummy/user/{id}") //회원정보 수정 id를 받아서 요청한 email,password사용
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id : " +id);
		System.out.println("password :  " +requestUser.getPassword());
		System.out.println("email :  " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

//		userRepository.save(user);
		
		//더티체킹
		
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	// 한페이지당 2건의 데이터를 리턴받을 예정.
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		List<User> users= pagingUser.getContent();
		
		return users;
	}
	
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
		// 요청 : 웹브라우저 
		return user; //user객체 = 자바 오브젝트 => 변환이 필요.
							//변환 필요 ( 웹브라우저가 이해할 수 있는 데이터 ) -> Json
							// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
							//->만약에 자바 오브젝트를 리턴하게되면 MessageConverter가 Jackson라이브러리를 이용하여
							//User 오브젝트로 Json으로 변환하여 브라우저에게 던져준다.
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
