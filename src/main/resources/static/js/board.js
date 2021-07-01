let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});

	},

	save: function() {
		let data={
			title:$("#title").val(),
			content:$("#content").val(),
		};
		
		//console.log(data)
		
		// ajax호출시 default가 비동기 호출
		$.ajax({
			//회원가입 수행 요청
			type:"POST",
			url:"/api/board",
			data:JSON.stringify(data), //JSON문자열 // http body 데이터
			contentType:"application/json; charset=utf-8", // body 데이터가 어떤 타입인지. (MIME)
			dataType:"json" // 요청에 대한 응답이 왔을 때, 기본적으로 모든 것이 문자열이다.
			//하지만 생긴게 json 이라면 => javascript오브젝트로 변경 해준다.)
		}).done(function(resp){ //성공
		
			alert("게시글이 등록되었습니다.");
			//console.log(resp);
			location.href="/";
		}).fail(function(error){ //실패시
			alert(JSON.stringify(error));
			
		}); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
	}
	
	
}

index.init();