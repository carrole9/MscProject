function load(){
	var type = window.sessionStorage.getItem("type");
	var option = window.sessionStorage.getItem("SEoption");

	 if(type=="1"){
		window.location.replace("http://"+document.location.host+"/maven_Project/Admin.html");
	}else if(type=="2"){
		window.location.replace("http://"+document.location.host+"/maven_Project/CustomerServiceRep.html");
	}else if(type=="3"){
		window.location.replace("http://"+document.location.host+"/maven_Project/SupportEngineerAccess.html");
	}else if(type=="4"){
		window.location.replace("http://"+document.location.host+"/maven_Project/NetworkManagementAccess.html");
	}
}


$(function() {

	function close_window(currentURL, newURL){
	    var newWindow = window.open(newURL, '_self', ''); //open the new window
	    window.close(currentURL); //close the current window
	}
	$('#login').on(
			'click',
			function() {
				var $loginusername = $('#inputEmail');
				var $loginpassword = $('#inputPassword');

				if ($loginusername.val() == "" && $loginpassword.val() == "") {
					alert("Please enter user name and password to login");
				} else {
					if ($loginusername.val() == "") {
						alert("Please enter your user name!");
					}
					if ($loginpassword.val() == "") {
						alert("Please enter your password!");
					}
				}

				$.ajax({
					type : 'POST',
					url : 'rest/users/login',
					contentType : "application/json",
					data : JSON.stringify({
						username : $loginusername.val(),
						password : $loginpassword.val()
					}),
					success : function(data) {
						if ($loginusername.val() !== ""
								&& $loginpassword.val() !== "") {
							if (data) {
								$.ajax({
									type : 'POST',
									url : 'rest/users/checktype',
									contentType : "application/json",
									data : JSON.stringify({
										username : $loginusername.val(),
										password : $loginpassword.val()
									}),
									success : function(data) {
										window.sessionStorage.setItem("UserName", $loginusername.val());
										window.sessionStorage.setItem("type", data);
										window.sessionStorage.setItem("SEoption", "0");
										window.sessionStorage.setItem("greeting", "true");
											if (data==1) {
												window.location.replace("http://"+document.location.host+"/maven_Project/Admin.html");
											}
											else if (data==2) {
												window.location.replace("http://"+document.location.host+"/maven_Project/CustomerServiceRep.html");
											}
											else if (data==3) {		
												window.location.replace("http://"+document.location.host+"/maven_Project/SupportEngineerAccess.html");
											}
											else if (data==4) {
												window.location.replace("http://"+document.location.host+"/maven_Project/NetworkManagementAccess.html");
											}
											else
												alert(data);	
										
									}
								})
							} else
								alert("User Does Not Exist!!!");
							//$print.text('User Does Not Exist!!!');	
						}
					}
				})
			});
});