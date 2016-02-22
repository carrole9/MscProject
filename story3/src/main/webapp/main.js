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
//						$users.append('<li> Id: '+user.id+' UserName: '+user.username+' Password: '+
//								user.password+' Usertype: '+user.usertype+'</li>');
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
//				$users.append('<li> Id: '+user.id+' UserName: '+user.username+' Password: '+
//						user.password+' Usertype: '+user.usertype+'</li>');
				addUser(user);
			},
			error: function(){
				alert('error saving user');
			}
		})
	});
	
});
