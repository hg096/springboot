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
		/*$("#btn-login").on("click", () => { //function(){} >> ()=>{} 이유 this를 바인딩하기 위해서
			this.login();
		});*/
	},
	
	save: function() {
		//alert('user의 join함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);

		// ajax 호출시 기본값이 비동기 호출
		// ajax통신을 통해 3개의 데이터를 json으로 insert 요청
		$.ajax({
			// 회원가입 수행 요청
			type: "post",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json;charset=utf-8", //body데이터가 어떤타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경
		}).done(function(resp) {
			alert("회원가입 완료");
			console.log(resp)
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}
	
	/*login: function() {
		//alert('user의 login함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
		}
		$.ajax({
			type: "post",
			url: "/api/user/login",
			data: JSON.stringify(data), // http body데이터
			contentType: "application/json;charset=utf-8", //body데이터가 어떤타입인지(MIME)
			dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열(생긴게 json이라면)=>javascript오브젝트로 변경
		}).done(function(resp) {
			alert("로그인 완료");
			console.log(resp)
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	}*/
}
index.init();


