package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;


//DAO
//자동으로 bean등록이 된다.
public interface UserRepository extends JpaRepository<User, Integer>{
	

	
}
//JPA Naming 쿼리전략
//Select * FROM user WHERE username = ? AND password = ?
// 첫번째 ? 에는 첫번째 매게변수가 들어간다.
//User findByUsernameAndPassword(String username, String password);

//	@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);
	//위 방법도 가능. 흡사 노드..?