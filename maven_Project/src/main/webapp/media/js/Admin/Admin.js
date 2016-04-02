function load(){
	var type = window.sessionStorage.getItem("type");
	var option = window.sessionStorage.getItem("SEoption");
	
	if(type=="1"){
		if(window.sessionStorage.getItem("greeting") == "true"){
			alert("Welcome back "+window.sessionStorage.getItem("UserName"));
			window.sessionStorage.setItem("greeting", "false");
		}
	}else if(type=="2"){
		window.location.replace("http://localhost:8080/maven_Project/CustomerServiceRep.html");
	}else if(type=="3"){
		window.location.replace("http://localhost:8080/maven_Project/SupportEngineerAccess.html");
	}else if(type=="4"){
		window.location.replace("http://localhost:8080/maven_Project/NetworkManagementAccess.html");
	}else{
		window.location.replace("http://localhost:8080/maven_Project/");
	}
}

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
	    window.close(currentURL); //close the current window
	 }
	
	function addUser(user) {
		var row = $("<tr><td>" + user.id
				+ "</td><td>" + user.username 
				+ "</td><td>" + user.password 
				+ "</td><td>" + user.usertype			
				+"</td></tr>");
		$("#userData tbody").append(row);         
	}
//	
//	function addmyDataIMSI(newUser) {
//		alert(newUser[1].imsi);    
//		for(var i =0;i < newUser.length-1;i++)
//		{
//		  var item = newUser[i];
//		  alert(item.imsi);
//		}
//		alert(newUser);  
//	}
	
	$('#ViewData').on('click', function(){
		$.ajax({
			async:false,
			type: 'GET',
			url: 'rest/basedata/getAllErrorData',
			success: function(data){
				if(data.length == 0)
					window.alert("No Error data in DataBase");
				else{
					//$("#myData tr:gt(0)").remove();
					$(".odd").remove();
					$(".even").remove();
					document.getElementById("myData").style.display = "table";
					//document.getElementById("myData").style.visibility = "visible";
	
					$.each(data, function(i, basedata){
							var row = $("<tr><td>" + basedata.dataId
									+ "</td><td>" + basedata.dateTime 
									+ "</td><td>" + basedata.eventId 
									+ "</td><td>" + basedata.failureId
									+ "</td><td>" + basedata.ueType
									+ "</td><td>" + basedata.market
									+ "</td><td>" + basedata.operator
									+ "</td><td>" + basedata.cellId
									+ "</td><td>" + basedata.duration
									+ "</td><td>" + basedata.cause_Code
									+ "</td><td>" + basedata.neVersion
									+ "</td><td>" + basedata.imsi
									+ "</td><td>" + basedata.hier3_Id
									+ "</td><td>" + basedata.hier32_Id
									+ "</td><td>" + basedata.hier321_Id
									+"</td></tr>");
							$("#myData tbody").append(row);
					})
				}
			$("#myData").trigger('update'); 
		},
			error: function(){
				alert('error loading users');
			}
		})
	});
	
	
	$('#ImportData').on('click', function(){
		$.ajax({
			async:false,
			type: 'GET',
			url: 'rest/database/populateDB',
			success: function(){
				alert('data is loaded');
				$('#ViewData').click();
			},
			error: function(){
				alert('Data is successfully loaded');
				//$('#ViewData').click();
			}
		})
		
	});
	
	$('#view-users').on('click', function(){
		$.ajax({
			async:false,
			type: 'GET',
			url: 'rest/users',
			success: function(data){
				//$("#userData tr:gt(0)").remove();
				//document.getElementById("userData").style.visibility = "visible";
				$(".odd").remove();
				$(".even").remove();
				document.getElementById("userData").style.display = "table";
				$.each(data, function(i, users){
						$.each(users, function(i,user){
							addUser(user);
						})
				})
				$("#userData").trigger('update'); 
			},
			error: function(){
				alert('error loading users');
			}
		})
	});

		$('#add-user').on('click', function(){	
			if($("#username").val()==""){
				window.window.alert('Please enter a username');
				document.getElementById('username').focus();
			}else if($("#password").val()==""){
				window.window.alert('Please enter a password');
				document.getElementById('password').focus();
			}
			else{
				
				$.ajax({
					async:false,
					type: 'POST',
					url: 'rest/users/checkUserExistence',
					contentType: "application/json",
					data: $("#username").val(),
					success: function(data){
						if(data){
							var user = new User (0, $("#username").val(), $("#password").val(),$("#usertype").val());		
							var my_arr = [];
							my_arr.push(1);
							my_arr.push($("#username").val());
							my_arr.push($("#password").val());
							my_arr.push($("usertype").val());
						    var jsonString = JSON.stringify(user);
							
						    $.ajax({
								type: 'POST',
								url: 'rest/users/add',
								contentType: "application/json",
								data: jsonString,
								success: function(newUser){
									document.getElementById("username").value = "";
									document.getElementById("password").value = "";
									$('#view-users').click();
									alert("user has been successfully created");
								},
								error: function(){
									alert('ERROR saving user.\nPlease Check input & try agin');
								}
							})
						}							
							else
								alert("The Entered username has been taken! \nPlease try " +
										"different username.");
					},
					error: function(){
						alert('error loading users');
					}
				})
			}
		});
	
	$('#semenu1').on('click', function() {
//		$("#myData tr:gt(0)").remove();
//		document.getElementById("myData").style.visibility = "hidden";
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myData").style.display = "none";
		$("#myData").trigger('update');
	});
	$('#semenu2').on('click', function() {
		document.getElementById("username").value = "";
		document.getElementById("password").value = "";
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("userData").style.display = "none";
		$("#userData").trigger('update');
//		$("#userData tr:gt(0)").remove();
//		document.getElementById("userData").style.visibility = "hidden";
	});
	$('#logout').on('click', function(){
		window.sessionStorage.setItem("SEoption","0");
		window.sessionStorage.setItem("type","0");
		window.sessionStorage.setItem("UserName", "");
		window.location.replace("http://localhost:8080/maven_Project/");
	});
	
});
