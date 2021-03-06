package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false, length= 100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; //섬머노트 라이브러리 사용. <html>태그가 섞여서 디자인이됨.
	
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) //연관 관계  // Many = Board  , One = User. //즉 한명의 유저는 여러개의 게시글 작성 가능
	@JoinColumn(name="userId") //필드값을 userId로.
	private User user; // DB는 오브젝트를 저장할수없다. 따라서 포린키사용. 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)// mappedBy 연관관계의 주인 x(난FK가아녜요) ->DB에 컬럼만들지마!!!
	private List<Reply> reply;  //단지 board에 달린 reply값을 받을 용도 그렇기에 리스트.
	
	@CreationTimestamp //자동으로 시간 걸어줌.
	private Timestamp creteDate;
	
	
}
