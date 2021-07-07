/* 
회원가입시 Ajax를 사용하는 이유
요청응답을 html이 아닌 data(json)를 받기위해 >> 앱과 웹 호환을 위해 // 하나의 서버로 호환
비동기 통신을 위해서(순차적 진행이 아닌 능동적으로 진행)
*/

let index = {
	init: function() {
		$("#btn-save").on("click", () => { //function(){} >> ()=>{} 이유 this를 바인딩하기 위해서
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});
		$("#Count++").on("click", () => {
			this.count();
		});

	},

	// 글쓰기
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		// ajax 호출시 기본값이 비동기 호출
		// ajax통신을 통해 3개의 데이터를 json으로 insert 요청
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json;charset=utf-8", //body데이터가 어떤타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경
		}).done(function(resp) {
			alert("글쓰기 완료");
			console.log(resp)
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	// 글삭제
	deleteById: function() {
		let id = $("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"
		}).done(function(resp) {
			alert("삭제 완료");
			console.log(resp)
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

	// 수정
	update: function() {
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({
			type: "PUT",
			url: "/api/board" + id,
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json;charset=utf-8", //body데이터가 어떤타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경
		}).done(function(resp) {
			alert("글수정 완료");
			console.log(resp)
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	// 댓글 작성
	replySave: function() {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		};
		$.ajax({
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data), // http body데이터 <여기에 사용할 데이터 기입>
			contentType: "application/json;charset=utf-8", //body데이터가 어떤타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경
		}).done(function(resp) {
			alert("댓글작성 완료");
			console.log(resp)
			location.href = `/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	//  댓글 삭제
	replyDelete: function(boardId, replyId) {
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"
		}).done(function(resp) {
			alert("댓글삭제 성공");
			location.href = `/board/${boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	// 조회수 증가
	




};
index.init();


