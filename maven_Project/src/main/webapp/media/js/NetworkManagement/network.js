function load(){
	var type = window.sessionStorage.getItem("type");
	var option = window.sessionStorage.getItem("SEoption");
	//alert(type +" "+option);
	
	 if(type=="1"){
		window.location.replace("http://localhost:8080/maven_Project/Admin.html");
	}else if(type=="2"){
		window.location.replace("http://localhost:8080/maven_Project/CustomerServiceRep.html");
	}else if(type=="3"){
		window.location.replace("http://localhost:8080/maven_Project/SupportEngineerAccess.html");
	}else if(type=="4"){
		
	}else{
		window.location.replace("http://localhost:8080/maven_Project/");
	}
}

function ChangeNetworkModel(){	
	document.getElementById("myModelTable").style.display = "none";
	var jsonString = JSON.stringify($("#NetworkManufacturer").val());
	
    
    document.getElementById("NetworkModel").options.length=0;
    $.ajax({
			async:false,
			type: 'POST',
			url: 'rest/basedata/getAllModels/',
			contentType : "application/json",
			data : jsonString,
			
			success: function(data){
			//alert(data);
			             for(i=0;i<data.length;i++){
						 var x = document.getElementById("NetworkModel");
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





function addManufacturersDropDownFeaturesNetwork(){	

    $.ajax({
			async:false,
			type: 'GET',
			url: 'rest/basedata/getAllManufacturers',
			success: function(data){
			//alert(data);
			             for(i=0;i<data.length;i++){
						 var x = document.getElementById("NetworkManufacturer");
						// var y = document.getElementById("FmyIMSI");
					     var option = document.createElement("option");
						    option.text = data[i];
						    x.add(option);
						  // y.add(option);
						   // alert(data[i])
			             }
			   
		},
			error: function(){
				alert('error loading Manufacturers');
			}
		})
 
   
	}

$(function() {

	function close_window(currentURL, newURL){
	    var newWindow = window.open(newURL, '_self', ''); //open the new window
	    window.close(currentURL); //close the current window
	}
	$('#logout').on('click', function(){
		var currentURL = "http://localhost:8080/maven_Project/NetworkManagement.html";
		var newURL = "http://localhost:8080/maven_Project/";
		close_window(currentURL, newURL);
	});
	
	// user story 12 NM
	$('#submitTopTen').on('click',function() {
		if($("#TopTenStart").val()==""){
			document.getElementById('TopTenStart').focus();
			window.alert('Please pick a Start Date');
		}else if($("#TopTenEnd").val()==""){
			document.getElementById('TopTenEnd').focus();
			window.alert('Please pick a End Date');
		}else{
			var my_arr = [];
			my_arr.push($("#TopTenStart").val());
			my_arr.push($("#TopTenEnd").val());
			var jsonString = JSON.stringify(my_arr);

			//$("#myIMSICountTable tr:gt(0)").remove();
			$.ajax({
					async : false,
					type : 'POST',
					url : 'rest/NetworkManagementEngineer/findTop10IMSI/',
					contentType : "application/json",
					data : jsonString,

					success : function(data) {
						
						if(data === undefined){
							alert("Entered data doesn't exist in DataBase \nPlease check input & try again.");
						}
						else{
							$(".odd").remove();
							$(".even").remove();
							document.getElementById("myIMSICountTable").style.display = "table";
								for (i = 0; i < data.length; i++) {
									var row = $("<tr><td>" + data[i][0]
												+ "</td><td>" + data[i][1]
												+ "</td></tr>");

									$("#myIMSICountTable tbody").append(row);

								}
						}
						$("#myIMSICountTable").trigger('update');
					},
					error : function() {
						alert('ERROR \nPlease check input & try again.');
					}
			})
		}
	});

	// userstory 9
	$('#submitNoOfFailureAndTotalDuration').on('click',function() {
		if($("#NoOfFailureAndTotalDurationStart").val()==""){
			document.getElementById('NoOfFailureAndTotalDurationStart').focus();
			window.alert('Please pick a Start Date');
		}else if($("#NoOfFailureAndTotalDurationEnd").val()==""){
			document.getElementById('NoOfFailureAndTotalDurationEnd').focus();
			window.alert('Please pick a End Date');
		}else{
			var my_arr = [];
			my_arr.push($("#NoOfFailureAndTotalDurationStart").val());
			my_arr.push($("#NoOfFailureAndTotalDurationEnd").val());
			var jsonString = JSON.stringify(my_arr);

			//$("#myNoOfFailureAndTotalDurationTable tr:gt(0)").remove();
			$.ajax({
					async : false,
					type : 'POST',
					url : 'rest/NetworkManagementEngineer/getNoOfFailuresTotalDurationsForEachImsiByPeriod/',
					contentType : "application/json",
					data : jsonString,
					success : function(data) {
						
						if(data === undefined){
							alert("Entered data doesn't exist in DataBase \nPlease check input & try again.");
						}
						else {	
							$(".odd").remove();
							$(".even").remove();
							document.getElementById("myNoOfFailureAndTotalDurationTable").style.display = "table";
							for (i = 0; i < data.length; i++) {

								var row = $("<tr><td>" + data[i][0]
											+ "</td><td>" + data[i][1]
											+ "</td><td>" + data[i][2]
											+ "</td></tr>");

								$("#myNoOfFailureAndTotalDurationTable tbody").append(row);

							}
						}
						$("#myNoOfFailureAndTotalDurationTable").trigger('update'); 
					},
					error : function() {
						alert('ERROR \nPlease check input & try again.');
					}
			})
		}
	});

	// user stroy 10
	$('#submitModel').on('click',function() {

		var jsonString = JSON.stringify($("#NetworkModel").val());

		//$("#myModelTable tr:gt(0)").remove();
		$.ajax({
				async : false,
				type : 'POST',
				url : 'rest/NetworkManagementEngineer/findFailureCauseCodeAndOccurrences/',
				contentType : "application/json",
				data : jsonString,

				success : function(data) {
					$(".odd").remove();
					$(".even").remove();
					document.getElementById("myModelTable").style.display = "table";
					//document.getElementById("myModelTable").style.visibility = "visible";
					for (i = 0; i < data.length; i++) {
						var row = $("<tr><td>" + data[i][0]
									+ "</td><td>" + data[i][1]
									+ "</td><td>" + data[i][2]
									+ "</td><td>" + data[i][3]
									+ "</td><td>" + data[i][4]
									+ "</td><td>" + data[i][5]
									+ "</td></tr>");
						$("#myModelTable tbody").append(row);
					}
					$("#myModelTable").trigger('update'); 
				},
				error : function() {
						alert('error');
				}
		})
	});

	$('#submitMarket').on('click',function() {
		if($("#StartMarketTime").val()==""){
			document.getElementById('StartMarketTime').focus();
			window.alert('Please pick a Start Date');
		}else if($("#EndMarketTime").val()==""){
			document.getElementById('EndMarketTime').focus();
			window.alert('Please pick a End Date');
		}else{
			var my_arr = [];
			
			my_arr.push($("#StartMarketTime").val());
			my_arr.push($("#EndMarketTime").val());
			var jsonString = JSON.stringify(my_arr);
	
			//$("#myMarketTable tr:gt(0)").remove();
			$.ajax({
				async : false,
				type : 'POST',
				url : 'rest/NetworkManagementEngineer/findTop10MarketOperatorCellCombo/',
				contentType : "application/json",
				data : jsonString,
				success : function(data) {
					
					if(data ==""){
						alert("Entered data doesn't exist in DataBase \nPlease check input & try again.");
					}
					else {	
						$(".odd").remove();
						$(".even").remove();
						document.getElementById("myMarketTable").style.display = "table";
						for (i = 0; i < data.length; i++) {
							var row = $("<tr><td>" + data[i][0]
										+ "</td><td>" + data[i][1]
										+ "</td><td>" + data[i][2]
										+ "</td><td>" + data[i][3]
										+ "</td></tr>");
							$("#myMarketTable tbody").append(row);
						}
					}
					$("#myMarketTable").trigger('update'); 
				},
				error : function() {
					alert('ERROR \nPlease check input & try again.');
				}
			})
		}
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

	$('#datetimepicker26').datetimepicker({
		// useCurrent: false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$('#datetimepicker27').datetimepicker({
		useCurrent : false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$("#datetimepicker26").on("dp.change", function(e) {
		$('#datetimepicker27').data("DateTimePicker").minDate(e.date);
	});
	$("#datetimepicker27").on("dp.change", function(e) {
		$('#datetimepicker26').data("DateTimePicker").maxDate(e.date);
	});

	$('#semenu1').on('click',function() {
		document.getElementById("NoOfFailureAndTotalDurationStart").value = "";
		document.getElementById("NoOfFailureAndTotalDurationEnd").value = "";
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myNoOfFailureAndTotalDurationTable").style.display = "none";
		$("#myNoOfFailureAndTotalDurationTable").trigger('update');
//		$("#myNoOfFailureAndTotalDurationTable tr:gt(0)").remove();
//		document.getElementById("myNoOfFailureAndTotalDurationTable").style.visibility = "hidden";
	});
	$('#semenu2').on('click',function() {
		document.getElementById("TopTenStart").value = "";
		document.getElementById("TopTenEnd").value = "";
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myIMSICountTable").style.display = "none";
		$("#myIMSICountTable").trigger('update');
//		$("#myIMSICountTable tr:gt(0)").remove();
//		document.getElementById("myIMSICountTable").style.visibility = "hidden";
	});
	$('#semenu3').on('click', function() {
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myModelTable").style.display = "none";
		$("#myModelTable").trigger('update');
//		$("#myModelTable tr:gt(0)").remove();
//		document.getElementById("myModelTable").style.visibility = "hidden";
	});
	$('#semenu4').on('click', function() {
		document.getElementById("StartMarketTime").value = "";
		document.getElementById("EndMarketTime").value = "";
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myMarketTable").style.display = "none";
		$("#myMarketTable").trigger('update');
//		$("#myMarketTable tr:gt(0)").remove();
//		document.getElementById("myMarketTable").style.visibility = "hidden";
	});

	$('#logout').on('click', function(){
		window.sessionStorage.setItem("SEoption","0");
		window.sessionStorage.setItem("type","0");
		window.sessionStorage.setItem("UserName", "");
		window.location.replace("http://localhost:8080/maven_Project/");
	});
	
	$('#back').on('click', function(){
		window.location.replace("http://localhost:8080/maven_Project/NetworkManagementAccess.html");
	});
});