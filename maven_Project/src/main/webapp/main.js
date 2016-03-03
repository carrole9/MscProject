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
	var $basedatas = $('#basedatas');
	var $logout = $('logout');
	var $logout1 = $('logout1');
	var $logout2 = $('logout2');
	
	function close_window(currentURL, newURL){
	    var newWindow = window.open(newURL, '_self', ''); //open the new window
	    window.close(url); //close the current window
	 }
	
	function addUser(user) {
		var row = $("<tr><td>" + user.id
				+ "</td><td>" + user.username 
				+ "</td><td>" + user.password 
				+ "</td><td>" + user.usertype			
				+"</td></tr>");
        $("#userData").append(row);
	}
	
	$.ajax({
	async:false,
	type: 'GET',
	url: 'rest/basedata/getAllBaseData',
	success: function(data){

		$.each(data, function(i, basedatas){
			
			$.each(basedatas, function(i,basedata){
				var row = $("<tr><td>" + basedata.dataId
						+ "</td><td>" + basedata.dateTime 
						+ "</td><td>" + basedata.eventCause.eventId 
						+ "</td><td>" + basedata.failure.failureId
						+ "</td><td>" + basedata.userEquipment.userEquipmentId
						+ "</td><td>" + basedata.operator.mcc
						+ "</td><td>" + basedata.operator.mnc
						+ "</td><td>" + basedata.cellId
						+ "</td><td>" + basedata.duration
						+ "</td><td>" + basedata.eventCause.causeCode
						+ "</td><td>" + basedata.neVersion
						+ "</td><td>" + basedata.imsi
						+ "</td><td>" + basedata.hier3Id
						+ "</td><td>" + basedata.hier32Id
						+ "</td><td>" + basedata.hier321Id
						+"</td></tr>");
                $("#myData").append(row);
			})
	})
	},
	
	error: function(){
		alert('error loading users');
	}
	
});
	
	
	
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
//				success: function(){	
//					alert('success');
//				},
//				error: function(){
//					alert('error saving user');
//				}
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
	
	
	$('#user').on('click', function(){

				var currentURL = "http://localhost:8080/maven_Project/selectusertype.html";
				var newURL = "http://localhost:8080/maven_Project/try3.html";
//				var newURL ="http://localhost:8080/maven_Project/rest/basedata/getAll"
				
				close_window(currentURL, newURL);
		
	});
	
	$('#logout').on('click', function(){

		var currentURL = "http://localhost:8080/maven_Project/selectusertype.html";
		var newURL = "http://localhost:8080/maven_Project/";
//		var newURL ="http://localhost:8080/maven_Project/rest/basedata/getAll"
		
		close_window(currentURL, newURL);

});
	$('#logout1').on('click', function(){

		var currentURL = "http://localhost:8080/maven_Project/try1.html";
		var newURL = "http://localhost:8080/maven_Project/";
//		var newURL ="http://localhost:8080/maven_Project/rest/basedata/getAll"
		
		close_window(currentURL, newURL);

});
	$('#logout2').on('click', function(){

		var currentURL = "http://localhost:8080/maven_Project/try3.html";
		var newURL = "http://localhost:8080/maven_Project/";
//		var newURL ="http://localhost:8080/maven_Project/rest/basedata/getAll"
		
		close_window(currentURL, newURL);

});
});
