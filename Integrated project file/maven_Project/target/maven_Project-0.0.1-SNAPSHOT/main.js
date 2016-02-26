var User = function(id, username, password, usertype) {
		    this.id = id;
		    this.username = username;
		    this.password = password;
		    this.usertype = usertype;
		    }



$(function (){
	
	var $print = $('#print');
	var $users = $('#users');
	var $id = $('#Id');
	var $username = $('#UserName');
	var $password = $('#Password');
	var $usertype = $('#UserType');
	var $loginusername = $('#LoginUserName');
	var $loginpassword = $('#LoginPassword');
	
	function close_window(currentURL, newURL){
	    var newWindow = window.open(newURL, '_self', ''); //open the new window
	    window.close(url); //close the current window
	 }
	
	function addUser(user) {
		$users.append('<li> Id: '+user.id+' UserName: '+user.username+' Password: '+
				user.password+' Usertype: '+user.usertype+'</li>');
	}
	
	$.ajax({
		async:false,
		type: 'GET',
		url: 'rest/users',
		success: function(data){
			$.each(data, function(i, users){
				
					$.each(users, function(i,user){
						addUser(user);
					})
			})
		},
		
		error: function(){
			alert('error loading users');
		}
		
	});
	
	
	$('#add-user').on('click', function(){	
		
		var user = new User ($id.val(), $username.val(), $password.val(),$usertype.val());		
		
		$.ajax({
			type: 'POST',
			url: 'rest/users',
			contentType: "application/json",
			data: JSON.stringify(user),
			success: function(newUser){
				addUser(user);
			},
			error: function(){
				alert('error saving user');
			}
		})
	});
	
	
	$('#load-excel-user').on('click', function(){
			
			$.ajax({
				
				url: 'rest/users/excel',
	//			success: function(){	
	//				alert('success');
	//			},
	//			error: function(){
	//				alert('error saving user');
	//			}
			})
		});


	$('#login').on('click', function(){
			
		$.ajax({
			type: 'POST',
			url: 'rest/users/login',
			contentType: "application/json",
			data: JSON.stringify({username: $loginusername.val(), password: $loginpassword.val()}),
			success: function(data){	
					if(data){
						
						$print.text('');
						
						var currentURL = "http://localhost:8080/maven_Project/login.html";
						var newURL = "http://localhost:8080/maven_Project/selectusertype.html";
						
						close_window(currentURL, newURL);
					}
					else
						$print.text('User Does Not Exist!!!');	
			}
		})
	});

	$('#admin').on('click', function(){
		
		$.ajax({
			type: 'POST',
			url: 'rest/users/selecttype',
			contentType: "application/json",
			data: "admin",
			success: function(data){
				
					if(data){
						
						var currentURL = "http://localhost:8080/maven_Project/selectusertype.html";
						var newURL = "http://localhost:8080/maven_Project/try1.html";
						
						close_window(currentURL, newURL);
						
					}
					else
						alert('wrong');			
			}
		})
	});
	
});
