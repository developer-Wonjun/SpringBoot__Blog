package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	//컨트롤러에서 세션을 찾는 방법.
	@GetMapping({"","/"})
	public String index(Model model) { //메인페이지로갈때 데이터(게시글정보)를 가져가야함 
		model.addAttribute("boards", boardService.글목록());
		return "index";
	}
	
	//User권한 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
