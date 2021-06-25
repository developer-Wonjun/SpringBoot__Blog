package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Object 를 테이블로 매핑해주는 기술 (변수만들면 테이블 필드로 만들어줌)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더패턴.
@Entity //User 클래스가 MySQL에 자동으로 테이블이 생성된다.
//@DynamicInsert  ==> insert시에 null인 필드를 제외시켜준다.
public class User {
	
	@Id //프라이머리 키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라감
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable=false, length=30)
	private String username; // 아이디
	
	@Column(nullable=false, length=100)
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	//@ColumnDefault("user")
	@Enumerated(EnumType.STRING)	// DB는 RoleType이 없다. 따라서 고지해줘야함.
	private RoleType role; // enum을 쓰는게 좋다. enum은 데이터에 도메인을 만들어줌. // admin,user,manager
	//권한을 주는 컬럼
	
	@CreationTimestamp //시간이 자동으로 입력
	private Timestamp createDate;
}
