function SEload(){
	var type = window.sessionStorage.getItem("type");
	var option = window.sessionStorage.getItem("SEoption");
	//alert(type +" "+option);
	
	if(type=="3" && option=="0"){
		window.location.replace("http://localhost:8080/maven_Project/SupportEngineerAccess.html");
	}else if(type=="3"){
		
	}else if(type=="1"){
		window.location.replace("http://localhost:8080/maven_Project/Admin.html");
	}else if(type=="2"){
		window.location.replace("http://localhost:8080/maven_Project/CustomerServiceRep.html");
	}else if(type=="4" && option=="4"){
		
	}else if(type=="4"){
		window.location.replace("http://localhost:8080/maven_Project/NetworkManagementAccess.html");
	}else{
		window.location.replace("http://localhost:8080/maven_Project/");
	}
}

function clearMenu(){
	document.getElementById("mySTime").value = "";
	document.getElementById("myETime").value = "";
	document.getElementById("myIMSI").style.display = "none";
	document.getElementById("FstartTime").value = "";
	document.getElementById("FendTime").value = "";
	document.getElementById("myFailureCount").style.display = "none";
	document.getElementById("myIMSIDisplay").style.display = "none";
	document.getElementById("myEventID").style.display = "none";
	document.getElementById("x").value = "";
	document.getElementById("y").value = "";
	document.getElementById("myFailureCount1").style.display = "none";
	document.getElementById("myCauseCode").style.display = "none";
}

function addDropDownFeatures3(){	

    $.ajax({
			async:false,
			type: 'GET',
			url: 'rest/basedata/getAllFailureID',
			success: function(data){
			//alert(data);
			             for(i=0;i<data.length;i++){
						 var x = document.getElementById("myFailure");
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

function ChangeModel(){	
	document.getElementById("FstartTime").value = "";
	document.getElementById("FendTime").value = "";
	$("#myFailureCount tr:gt(0)").remove();
	document.getElementById("myFailureCount").style.display = "none";
	$("#myIMSIDisplay").trigger('update');
	var jsonString = JSON.stringify($("#myManufacturer").val());
	
    
    document.getElementById("myPhoneModel").options.length=0;
    $.ajax({
			async:false,
			type: 'POST',
			url: 'rest/basedata/getAllModels/',
			contentType : "application/json",
			data : jsonString,
			
			success: function(data){
			//alert(data);
				
			             for(i=0;i<data.length;i++){
						 var x = document.getElementById("myPhoneModel");
						// var y = document.getElementById("FmyIMSI");
					     var option = document.createElement("option");
						    option.text = data[i];
						    x.add(option);
						  // y.add(option);
						   // alert(data[i])
			             }
			   
		},
			error: function(){
				alert('error loading models');
			}
		})
 
   
	}

function addManufacturersDropDownFeatures(){	

    $.ajax({
			async:false,
			type: 'GET',
			url: 'rest/basedata/getAllManufacturers',
			success: function(data){
			//alert(data);
			             for(i=0;i<data.length;i++){
						 var x = document.getElementById("myManufacturer");
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
function addModelDropDownFeatures(){	

    $.ajax({
			async:false,
			type: 'GET',
			url: 'rest/basedata/getAllManufacturers',
			success: function(data){
			//alert(data);
			             for(i=0;i<data.length;i++){
						 var x = document.getElementById("myManufacturer");
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


$(function() {
	
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
	
	$('#suppSub1').on('click', function() {
		
		if($("#mySTime").val()==""){
			document.getElementById('mySTime').focus();
			window.alert('Please pick a Start Date');
		}else if($("#myETime").val()==""){
			document.getElementById('myETime').focus();
			window.alert('Please pick a End Date');
		}else{
			
			var no = 1;
			var my_arr = [];
			my_arr.push($("#mySTime").val());
			my_arr.push($("#myETime").val());
			var jsonString = JSON.stringify(my_arr);

			$.ajax({
				async : false,
				type : 'POST',
				url : 'rest/supportEngineer/findIMSIandFailurebyTime/',
				contentType : "application/json",
				data : jsonString,
				success : function(data) {
					$.each(data, function(i, basedatas) {
						if(basedatas=="")
							alert("Entered data doesn't exist in DataBase \nPlease check input & try again.");
						else{
							$(".odd").remove();
							$(".even").remove();
							document.getElementById("myIMSI").style.display = "table";
							
							$.each(basedatas, function(i, basedata) {
								//alert(basedata.imsi);
								//var row = $("<tr><td>" + basedata + "</td></tr>");
								var row = $("<tr><td class='active'>"+ +no+"</td><td class='info'>"+basedata+"</td></tr>");
								$("#myIMSI tbody").append(row);
								no+=1;
							})
							//document.getElementById("myIMSI").style.visibility = "visible";
						}
							
					})
					$("#myIMSI").trigger('update'); 
				},
				error : function() {
					alert('ERROR \nPlease check input & try again.');
				}
			})
		}
	});

	$('#suppSub2').on('click', function() {
		if($("#FstartTime").val()==""){
			document.getElementById('FstartTime').focus();
			window.alert('Please pick a Start Date');
		}else if($("#FendTime").val()==""){
			document.getElementById('FendTime').focus();
			window.alert('Please pick a End Date');
		}else{
			var my_arr = [];
			my_arr.push($("#FstartTime").val());
			my_arr.push($("#FendTime").val());
			my_arr.push($("#myPhoneModel").val());
			var jsonString = JSON.stringify(my_arr);
			//alert(jsonString);
	
			$("#myFailureCount tr:gt(0)").remove();
			$.ajax({
				async : false,
				type : 'POST',
				url : 'rest/supportEngineer/findNoOfFailuresByPeriodAndModel/',
				contentType : "application/json",
				data : jsonString,
				success : function(data) {
					if(data=="0"){
						alert("Entered data doesn't exist in DataBase \nPlease check input & try again.");
					}
					else{
						document.getElementById("myFailureCount").style.display = "table";
						var row = $("<tr><td>" + data + "</td></tr>");
						$("#myFailureCount").append(row);
					}
				},
				error : function() {
					alert('ERROR \nPlease check input & try again.');
				}
			})
		}
	});

	$('#suppSub3').on('click', function() {
		var value = ($("#myFailure").val());
		var no = 1;
		document.getElementById("myIMSIDisplay").style.display = "none";
		$.ajax({
			async : false,
			type : 'POST',
			url : 'rest/supportEngineer/findIMSIbyFailureId/',
			contentType : "application/json",
			data : value,
			success : function(data) {
				$(".odd").remove();
				$(".even").remove();
				document.getElementById("myIMSIDisplay").style.display = "table";
				
					$.each(data, function(i, string) {
						if(string === undefined || data === undefined)
							document.getElementById("myIMSIDisplay").style.display = "none";
//							document.getElementById("myIMSIDisplay").style.visibility = "hidden";
						else{
//							$("#myIMSIDisplay tr:gt(0)").remove();
//							document.getElementById("myIMSIDisplay").style.visibility = "visible";
							//var row = $("<tr><td>" + string + "</td></tr>");
							
							var row = $("<tr><td class='active'>"+ +no+"</td><td class='info'>"+string+"</td></tr>");
							$("#myIMSIDisplay tbody").append(row);
							no+=1;
						}
					})
				$("#myIMSIDisplay").trigger('update');
			},
			error : function() {
				alert('ERROR\nPlease check the INPUT & try again...');
			}
		})
	});
	
	

	$('#semenu1').on('click', function() {
		document.getElementById("mySTime").value = "";
		document.getElementById("myETime").value = "";
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myIMSI").style.display = "none";
		$("#myIMSIDisplay").trigger('update');
//		$("#myIMSI tr:gt(0)").remove();
//		document.getElementById("myIMSI").style.visibility = "hidden";
	});
	$('#semenu2').on('click', function() {
		//document.getElementById("FmyModel").value = "";
		document.getElementById("FstartTime").value = "";
		document.getElementById("FendTime").value = "";
		$("#myFailureCount tr:gt(0)").remove();
		document.getElementById("myFailureCount").style.display = "none";
		$("#myIMSIDisplay").trigger('update');
	});
	$('#semenu3').on('click', function() {
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myIMSIDisplay").style.display = "none";
		$("#myIMSIDisplay").trigger('update');
//		$("#myIMSIDisplay tr:gt(0)").remove();
//		document.getElementById("myIMSIDisplay").style.visibility = "hidden";
	});
	
	
	$('#datetimepicker6').datetimepicker({
		// useCurrent: false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$('#datetimepicker7').datetimepicker({
		useCurrent : false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$("#datetimepicker6").on("dp.change", function(e) {
		$('#datetimepicker7').data("DateTimePicker").minDate(e.date);
	});
	$("#datetimepicker7").on("dp.change", function(e) {
		$('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
	});

	$('#datetimepicker16').datetimepicker({
		// useCurrent: false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$('#datetimepicker17').datetimepicker({
		useCurrent : false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$("#datetimepicker16").on("dp.change", function(e) {
		$('#datetimepicker17').data("DateTimePicker").minDate(e.date);
	});
	$("#datetimepicker17").on("dp.change", function(e) {
		$('#datetimepicker16').data("DateTimePicker").maxDate(e.date);
	});

	
	
	
});

function addDropDownFeatures1(){	

    $.ajax({
			async:false,
			type: 'GET',
			url: 'rest/basedata/getAllIMSI',
			success: function(data){
			//alert(data);
			             for(i=0;i<data.length;i++){
						 var x1 = document.getElementById("myIMSI1");
						// var y = document.getElementById("FmyIMSI");
					     var option = document.createElement("option");
						    option.text = data[i];
						    x1.add(option);
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
function addDropDownFeatures4(){	

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

     var jsonString = JSON.stringify($("#myIMSI1").val());
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
	if($("#x").val()==""){
		document.getElementById('x').focus();
		window.alert('Please pick a Start Date');
	}else if($("#y").val()==""){
		document.getElementById('y').focus();
		window.alert('Please pick a End Date');
	}else{

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
						$("#myFailureCount1 tr:gt(0)").remove();
						document.getElementById("myFailureCount1").style.display="table";
						var row = $("<tr><td>"  + data +"</td></tr>");
				
						$("#myFailureCount1").append(row);
					}
				},
				error: function(){
					alert('ERROR \nPlease check input & try again.');
				}
			})
	}
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

$('#datetimepicker26').datetimepicker({
    //useCurrent: false, 
    locale:'en-gb'//Important! See issue #1075
});
$('#datetimepicker27').datetimepicker({
    useCurrent: false, 
    locale:'en-gb'//Important! See issue #1075
});
$("#datetimepicker26").on("dp.change", function (e) {
    $('#datetimepicker27').data("DateTimePicker").minDate(e.date);
});
$("#datetimepicker27").on("dp.change", function (e) {
    $('#datetimepicker26').data("DateTimePicker").maxDate(e.date);
});



$('#semenu4').on('click', function() {
//	$("#myEventID tr:gt(0)").remove();
//	document.getElementById("myEventID").style.visibility = "hidden";
	$(".odd").remove();
	$(".even").remove();
	document.getElementById("myEventID").style.display = "none";
	$("#myEventID").trigger('update');
});
$('#semenu5').on('click', function() {
	$("#myFailureCount1 tr:gt(0)").remove();
	document.getElementById("x").value = "";
	document.getElementById("y").value = "";
	document.getElementById("myFailureCount1").style.display = "none";
});
$('#semenu6').on('click', function() {
//	$("#myCauseCode tr:gt(0)").remove();
//	document.getElementById("myCauseCode").style.visibility = "hidden";
	$(".odd").remove();
	$(".even").remove();
	document.getElementById("myCauseCode").style.display = "none";
	$("#myCauseCode").trigger('update');
});

});
