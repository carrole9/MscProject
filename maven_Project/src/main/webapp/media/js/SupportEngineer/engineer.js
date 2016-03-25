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
		//alert("hi");
		var no = 1;
		var my_arr = [];
		my_arr.push($("#mySTime").val());
		my_arr.push($("#myETime").val());
		if($("#myETime").val()=="" || $("#myETime").val()=="")
			alert("Please Check input & try again");
		else{
			var jsonString = JSON.stringify(my_arr);
			//alert(jsonString);
	
			
			$.ajax({
				async : false,
				type : 'POST',
				url : 'rest/supportEngineer/findIMSIandFailurebyTime/',
				contentType : "application/json",
				data : jsonString,
				success : function(data) {
					//alert(data);
					//$("#myIMSI tr:gt(0)").remove();
					$(".odd").remove();
					$(".even").remove();
					document.getElementById("myIMSI").style.display = "table";
					$.each(data, function(i, basedatas) {
						if(basedatas=="")
							alert("Entered data doesn't exist in DataBase \nPlease check input & try again.");
						else{
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
		//alert("hi");
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

	
	
	$('#back').on('click', function(){
		var option = window.sessionStorage.getItem("SEoption");
		if(option < 4)
			window.location.replace("http://localhost:8080/maven_Project/SupportEngineerAccess.html");
		else
			window.location.replace("http://localhost:8080/maven_Project/NetworkManagementAccess.html");
	});
});