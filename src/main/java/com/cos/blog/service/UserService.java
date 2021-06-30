package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // 스프링ㅇ ㅣ컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다. (메모리에 대신 띄어줌)
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; //객체가 DI가 돼서 주입이 된다.
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); // 패스워드 원문
		String encPassword = encoder.encode(rawPassword); //비밀번호 해쉬 완료
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	
	
}
