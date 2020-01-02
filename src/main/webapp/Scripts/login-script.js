function tryLogin() {
	
	let loginForm = document.loginForm;
	console.log("after try log function");
	let username = loginForm.username.value;
	let password = loginForm.password.value;
	let loginInfo = {
			username: username,
			password: password,
	}
	console.log(loginInfo);
	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() {
		console.log(this.readyState);
		if((this.readyState ===4) && (this.status === 200)) {
			sessionStorage.setItem('currentUser', this.responseText);
			window.location = "http://localhost:8080/project-1-chacond100/HTML/Project-1-EmpReimbView.html";
		}
		if(this.readyState === 4 && this.status === 204) {
			alert("Failed to login succesfully");
		}
	};

	xhr.open("POST", "http://localhost:8080/project-1-chacond100/login");
	xhr.send(JSON.stringify(loginInfo));
	
}