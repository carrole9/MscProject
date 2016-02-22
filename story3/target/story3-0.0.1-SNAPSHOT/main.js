var User = function(id, username, password, usertype) {
		    this.id = id;
		    this.username = username;
		    this.password = password;
		    this.usertype = usertype;

		}

$(function (){
	
	var $users = $('#users');
	var $id = $('#Id');
	var $username = $('#UserName');
	var $password = $('#Password');
	var $usertype = $('#UserType');
	
	$.ajax({
		type: 'GET',
		url: 'rest/users',
		success: function(data){
			$.each(data, function(i, users){
				
					$.each(users, function(i,user){
						$users.append('<li> Id: '+user.id+' UserName: '+user.username+' Password: '+
								user.password+' Usertype: '+user.usertype+'</li>');
					})
			})
		},
		
		error: function(){
			alert('error loading users');
		}
		
	});
	
	
	$('#add-user').on('click', function(){		
		
		var user = new User (6, $username.val(), $password.val(),$usertype.val());
		
		
		
		$.ajax({
			type: 'POST',
			url: 'rest/users',
			contentType: "application/json",
//			data: user,
			data: JSON.stringify(user),
			success: function(newUser){
				$users.append('<li> Id: '+newUser.id+' UserName: '+newUser.username+' Password: '+
						newUser.password+' Usertype: '+newUser.usertype+'</li>');
			},
			error: function(){
				alert('error saving user');
			}
		})
	});
	
});
