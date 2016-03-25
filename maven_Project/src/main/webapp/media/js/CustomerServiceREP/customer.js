function CUload(){
	document.getElementById("back").style.display = "none";
	var type = window.sessionStorage.getItem("type");
	var option = window.sessionStorage.getItem("SEoption");
	//alert(document.getElementById("back").innerHTML);
	
	if(type=="2"){
		if(window.sessionStorage.getItem("greeting") == "true"){
			alert("Welcome back "+window.sessionStorage.getItem("UserName"));
			window.sessionStorage.setItem("greeting", "false");
		}
	}else if(type=="1"){
		window.location.replace("http://localhost:8080/maven_Project/Admin.html");
	}else if(type=="3" && option=="2"){
		document.getElementById("back").style.display = "block";
	}else if(type=="3"){
		window.location.replace("http://localhost:8080/maven_Project/SupportEngineerAccess.html");
	}else if(type=="4" && option=="5"){
		document.getElementById("back").style.display = "block";
	}else if(type=="4"){
		window.location.replace("http://localhost:8080/maven_Project/NetworkManagementAccess.html");
	}else{
		window.location.replace("http://localhost:8080/maven_Project/");
	}
}

function addDropDownFeatures1(){	

    $.ajax({
			async:false,
			type: 'GET',
			url: 'rest/basedata/getAllIMSI',
			success: function(data){
			//alert(data);
			             for(i=0;i<data.length;i++){
						 var x = document.getElementById("myIMSI");
						// var y = document.getElementById("FmyIMSI");
					     var option = document.createElement("option");
						    option.text = data[i];
						    x.add(option);
						  // y.add(option);
						   // alert(data[i])
			             }
			   
		},
			error: function(){
				alert('error loading users');
			}
		})
 
   
	}
function addDropDownFeatures2(){	

    $.ajax({
			async:false,
			type: 'GET',
			url: 'rest/basedata/getAllIMSI',
			success: function(data){
			//alert(data);
			             for(i=0;i<data.length;i++){
						 var x = document.getElementById("FmyIMSI");
						// var y = document.getElementById("FmyIMSI");
					     var option = document.createElement("option");
						    option.text = data[i];
						    x.add(option);
						  // y.add(option);
						   // alert(data[i])
			             }
			   
		},
			error: function(){
				alert('error loading users');
			}
		})
 
   
	}
function addDropDownFeatures3(){	

    $.ajax({
			async:false,
			type: 'GET',
			url: 'rest/basedata/getAllIMSI',
			success: function(data){
			//alert(data);
			             for(i=0;i<data.length;i++){
						 var x = document.getElementById("myIMSICauseCode");
						// var y = document.getElementById("FmyIMSI");
					     var option = document.createElement("option");
						    option.text = data[i];
						    x.add(option);
						  // y.add(option);
						   // alert(data[i])
			             }
			   
		},
			error: function(){
				alert('error loading users');
			}
		})
 
   
	}

$(function (){

	function close_window(currentURL, newURL){
	    var newWindow = window.open(newURL, '_self', ''); //open the new window
	    window.close(currentURL); //close the current window
	}
	$('#logout').on('click', function(){
		window.sessionStorage.setItem("SEoption","0");
		window.sessionStorage.setItem("type","0");
		window.sessionStorage.setItem("UserName", "");
		window.location.replace("http://localhost:8080/maven_Project/");
	});
	
	
	
	
//User Story 4 CS
$('#submitIMSI').on('click', function(){	

     var jsonString = JSON.stringify($("#myIMSI").val());
    // alert(jsonString);
  
    // $("#myEventID tr:gt(0)").remove();
     $.ajax({
    	async:false, 
			type: 'POST',
			url: 'rest/customer/findEventandCausecodeByIMSI/',
			contentType: "application/json",
			data: jsonString,
			success: function(data){
				//alert(data.length);
				//document.getElementById("myEventID").style.visibility = "visible";
				$(".odd").remove();
				$(".even").remove();
				document.getElementById("myEventID").style.display = "table";
				$.each(data, function(i, basedatas){
					
					$.each(basedatas, function(i,basedata){
						var row = $("<tr><td>"  + basedata.causeCode
								+"</td><td>" + basedata.eventId
								+"</td><td>" + basedata.description
								+"</td></tr>");
								
		                $("#myEventID tbody").append(row);
					})
			})
				
			$("#myEventID").trigger('update');
	        
			},
			error: function(){
				alert('error');
			}
		})
	});


//user story 5 CS
$('#submitFailureCount').on('click', function(){	

	var my_arr = [];
	my_arr.push($("#x").val());
	my_arr.push($("#y").val());
	my_arr.push($("#FmyIMSI").val());
    var jsonString = JSON.stringify(my_arr);
    // alert(jsonString);
    
    
     $.ajax({
    	async:false, 
			type: 'POST',
			url: 'rest/customer/findFailurebyTimeandIMSI/',
			contentType: "application/json",
			data: jsonString,
			success: function(data){
				if(data=="0")
					alert("Entered data doesn't exist in DataBase \nPlease check input & try again.");
				else{
					$("#myFailureCount tr:gt(0)").remove();
					document.getElementById("myFailureCount").style.display="table";
					var row = $("<tr><td>"  + data +"</td></tr>");
			
					$("#myFailureCount").append(row);
				}
			},
			error: function(){
				alert('ERROR \nPlease check input & try again.');
			}
		})
	});

$('#submitCauseCode').on('click', function(){	

	 var jsonString = JSON.stringify($("#myIMSICauseCode").val());
	 
   
    
     $.ajax({
    	async:false, 
			type: 'POST',
			url: 'rest/customer/findUniqueCauseCodebyImsi/',
			contentType: "application/json",
			data: jsonString,
			success: function(data){
				//alert(data.length);
//				 $("#myCauseCode tr:gt(0)").remove();
//				document.getElementById("myCauseCode").style.visibility = "visible";
				$(".odd").remove();
				$(".even").remove();
				document.getElementById("myCauseCode").style.display = "table";
				for(i =0; i<data.length; i++){
					
				 var row = $("<tr><td>" + data[i][0]
					         +"</td><td>" + data[i][1]
				 			+"</td><td>" + data[i][2]
				 			+"</td><td>" + data[i][3]
							+"</td></tr>");
			
				 $("#myCauseCode tbody").append(row);
					
				}
				$("#myCauseCode").trigger('update'); 
			},
			error: function(){
				alert('error');
			}
		})
	});

$('#datetimepicker6').datetimepicker({
    //useCurrent: false, 
    locale:'en-gb'//Important! See issue #1075
});
$('#datetimepicker7').datetimepicker({
    useCurrent: false, 
    locale:'en-gb'//Important! See issue #1075
});
$("#datetimepicker6").on("dp.change", function (e) {
    $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
});
$("#datetimepicker7").on("dp.change", function (e) {
    $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
});

$('#semenu1').on('click', function() {
//	$("#myEventID tr:gt(0)").remove();
//	document.getElementById("myEventID").style.visibility = "hidden";
	$(".odd").remove();
	$(".even").remove();
	document.getElementById("myEventID").style.display = "none";
	$("#myEventID").trigger('update');
});
$('#semenu2').on('click', function() {
	$("#myFailureCount tr:gt(0)").remove();
	document.getElementById("x").value = "";
	document.getElementById("y").value = "";
	document.getElementById("myFailureCount").style.display = "none";
});
$('#semenu3').on('click', function() {
//	$("#myCauseCode tr:gt(0)").remove();
//	document.getElementById("myCauseCode").style.visibility = "hidden";
	$(".odd").remove();
	$(".even").remove();
	document.getElementById("myCauseCode").style.display = "none";
	$("#myCauseCode").trigger('update');
});

$('#back').on('click', function(){
	var option = window.sessionStorage.getItem("SEoption");
	if(option=="2"){
		window.location.replace("http://localhost:8080/maven_Project/SupportEngineerAccess.html");
		
	}else if(option=="5"){
		window.location.replace("http://localhost:8080/maven_Project/NetworkManagementAccess.html");
	}else
		window.history.back();
});


});
