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
	
	function addUser(user) {
		$users.append('<li> Id: '+user.id+' UserName: '+user.username+' Password: '+
				user.password+' Usertype: '+user.usertype+'</li>');
	}
	
	$.ajax({
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
//			alert('success');
			
//				$("#content").load("http://localhost:8080/story3/try1.html");
//				window.open('http://localhost:8080/story3/try1.html', '_blank');
//				$print.append('<li>data</li>');		
				if(data){
//					alert('Cool');
//					$print.text('');
					$("#content").load("http://localhost:8080/story3/selectusertype.html");
					
//				$("#content").load("http://localhost:8080/story3/try1.html");
				}
				else
//					alert('UserDoseNotExisthttp://localhost:8080/story3/login.html');
					$print.text('User Does Not Exist!!!');
//					$print.('<li>User Does Not Exist!!!</li>');
				
		
		}
//		error: function(){
//			alert('error saving user');
//		}
	})
});

$('#admin').on('click', function(){
//	var type = "admin";
	
//	$.ajax({
//		type: 'GET',
//		url: 'rest/users/selecttype',
//		contentType: "json",
//		success: function(data){			
//			$.each(data, function(i, loginusertype){
//				if(loginusertype == "admin"){
//					$("#content").load("http://localhost:8080/story3/try1.html");
//				}
//		})
//		},
//		
////		error: function(){
////			alert('error loading users');
////		}
//		
//	});
	
	$.ajax({
		type: 'POST',
		url: 'rest/users/selecttype',
		contentType: "application/json",
		data: "admin",
		success: function(data){
//			alert('success');
			
//				$("#content").load("http://localhost:8080/story3/try1.html");
//				window.open('http://localhost:8080/story3/try1.html', '_blank');
//				$print.append('<li>data</li>');		
				if(data){
//					alert('Cool');
//					$print.text('');
					$("#content").load("http://localhost:8080/story3/try1.html");
					
//				$("#content").load("http://localhost:8080/story3/try1.html");
				}
				else
					alert('wrong');
//					$print.text('wrong!!!');
//					$print.('<li>User Does Not Exist!!!</li>');
				
		
		}
//		error: function(){
//			alert('error saving user');
//		}
	})
});
	
});
