function welcome() {
	var name = window.sessionStorage.getItem("UserName");
	document.getElementById('welcomeNE').innerHTML = "Welcome "+name;
}


function graph(dataArrayFinal,startTime, endTime) {

	$('#graphContainer')
			.highcharts(
					{
						chart : {
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false,
							type : 'pie'
						},
						title : {
							text : 'Top 10 Market/Operator/Cell ID combinations with call failures'
						},
						subtitle: {
			                text: 'Between '+startTime+' and ' +endTime
			            },
						tooltip : {
							pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
						},
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								dataLabels : {
									enabled : true,
									format : '<b>{point.name}</b>: {point.y}, {point.percentage:.1f} %',
									style : {
										color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
												|| 'black'
									}
								}
							}
						},
						 credits: {
				                enabled: false
				            },
						series : [ {
							name : 'Percentage',
							colorByPoint : true,
							data : dataArrayFinal
						} ]
					});

}
function load(){
	var type = window.sessionStorage.getItem("type");
	var option = window.sessionStorage.getItem("SEoption");
	//alert(type +" "+option);
	
	 if(type=="1"){
		window.location.replace("http://"+document.location.host+"/maven_Project/Admin.html");
	}else if(type=="2"){
		window.location.replace("http://"+document.location.host+"/maven_Project/CustomerServiceRep.html");
	}else if(type=="3"){
		window.location.replace("http://"+document.location.host+"/maven_Project/SupportEngineerAccess.html");
	}else if(type=="4"){
		
	}else{
		window.location.replace("http://"+document.location.host+"/maven_Project/");
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
			var names = new Array();
			var numbers = new Array();
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
							markup = [];	
							for (i = 0; i < data.length; i++) {
									
//									var row = $("<tr><td>" + data[i][0]
//												+ "</td><td>" + data[i][1]
//												+ "</td></tr>");
									
									markup.push("<tr>");
									markup.push("<td>"+ data[i][0] +"</td>");
									markup.push("<td>"+ data[i][1] + "</td>");
									markup.push("</tr>");
									
									
									names[i] = data[i][0];
									numbers[i] = data[i][1];
									
//									$("#myIMSICountTable tbody").append(row);

								}
							$("#myIMSICountTable tbody").append(markup.join(""));
							var start = $("#TopTenStart").val();
							var end = $("#TopTenEnd").val();
							var subTitle = "Between "+ start +" and "+ end;
							var title = "Top 10 IMSI with Failures";
							var xTitle = "IMSI's";
							var yTitle = " Failures";
							document.getElementById("Top10IMSI").style.display = "block";
							Top10IMSI(names, numbers, title, subTitle, xTitle, yTitle);
							
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
							markup = [];
							for (i = 0; i < data.length; i++) {

//								var row = $("<tr><td>" + data[i][0]
//											+ "</td><td>" + data[i][1]
//											+ "</td><td>" + data[i][2]
//											+ "</td></tr>");
//
//								$("#myNoOfFailureAndTotalDurationTable tbody").append(row);
								markup.push("<tr>");
								markup.push("<td>" + data[i][0] +"</td>");
								markup.push("<td>" + data[i][1] + "</td>");
								markup.push("<td>" + data[i][2] + "</td>");
								markup.push("</tr>");
							}
							$("#myNoOfFailureAndTotalDurationTable tbody").append(markup.join(""));
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
					markup = [];
					for (i = 0; i < data.length; i++) {
//						var row = $("<tr><td>" + data[i][0]
//									+ "</td><td>" + data[i][1]
//									+ "</td><td>" + data[i][2]
//									+ "</td><td>" + data[i][3]
//									+ "</td><td>" + data[i][4]
//									+ "</td><td>" + data[i][5]
//									+ "</td></tr>");
//						$("#myModelTable tbody").append(row);
						markup.push("<tr>");
						markup.push("<td>"+ data[i][0] +"</td>");
						markup.push("<td>"+ data[i][1] + "</td>");
						markup.push("<td>"+ data[i][2] + "</td>");
						markup.push("<td>"+ data[i][3] + "</td>");
						markup.push("<td>"+ data[i][4] + "</td>");
						markup.push("<td>"+ data[i][5] + "</td>");
						markup.push("</tr>");
					}
					$("#myModelTable tbody").append(markup.join(""));
					$("#myModelTable").trigger('update'); 
				},
				error : function() {
						alert('error');
				}
		})
	});

	$('#submitMarket').on('click',function() {
		var my_arr = [];

		my_arr.push($("#StartMarketTime").val());
		my_arr.push($("#EndMarketTime").val());
		var jsonString = JSON.stringify(my_arr);
		var NoOfAllFailures = 0;
		var NoOfTop10Failures = 0;
		var NoOfOtherFailures = 0;

		// $("#myMarketTable tr:gt(0)").remove();
		$
				.ajax({
					async : false,
					type : 'POST',
					url : 'rest/NetworkManagementEngineer/findTop10MarketOperatorCellCombo/',
					contentType : "application/json",
					data : jsonString,
					success : function(data) {
						$(".odd").remove();
						$(".even").remove();
						document.getElementById("myMarketTable").style.display = "table";
						markup = [];
						if (data == "") {
							alert("Entered data doesn't exist in DataBase \nPlease check input & try again.");
						} else {
							var name = [];
							var value = [];
							var dataArrayFinal = [];

							// document.getElementById("myMarketTable").style.visibility
							// = "visible";
							for (i = 0; i < data.length; i++) {

//								name[i] = data[i][1] + " / "
//										+ data[i][2] + " / "
//										+ data[i][3];
//								value[i] = data[i][4];
								//						
								//
								// for(j=0;j<name.length;j++) {
								// var temp = new
								// Array(name[j],data[j]);
								// dataArrayFinal[j] = temp;
								// }

								// var a = data[i][0];
								// alert(a);
							if(i<10){
								
								name[i] = data[i][1] + " / "
										+ data[i][2] + " / "
										+ data[i][3];
								value[i] = data[i][4];
								
//								var row = $("<tr><td>"
//										+ data[i][0]
//										+ "</td><td>"
//										+ data[i][1]
//										+ "</td><td>"
//										+ data[i][2]
//										+ "</td><td>"
//										+ data[i][3]
//										+ "</td><td>"
//										+ data[i][4]
//										+ "</td></tr>");
//								$("#myMarketTable tbody").append(row);
								
								markup.push("<tr>");
								markup.push("<td>"+ data[i][0] +"</td>");
								markup.push("<td>"+ data[i][1] +"</td>");
								markup.push("<td>"+ data[i][2] +"</td>");
								markup.push("<td>"+ data[i][3] +"</td>");
								markup.push("<td>"+ data[i][4] +"</td>");
								markup.push("</tr>");
							
								NoOfTop10Failures = NoOfTop10Failures + data[i][4];
							}
								NoOfAllFailures = NoOfAllFailures + data[i][4];	
							}
							
								NoOfOtherFailures = NoOfAllFailures - NoOfTop10Failures;
								name[name.length] = "Others"
								value[value.length] = NoOfOtherFailures;

							for (j = 0; j < name.length; j++) {
								var temp = new Array(name[j],
										value[j]);
								dataArrayFinal[j] = temp;
							}
							document.getElementById("graphContainer").style.display = "block";
							graph(dataArrayFinal,$("#StartMarketTime").val(),$("#EndMarketTime").val());
						}
						$("#myMarketTable tbody").append(markup.join(""));
						$("#myMarketTable").trigger('update');
					},
					error : function() {
						alert('ERROR \nPlease check input & try again.');
					}
				})
	});

	$('#datetimepicker46').datetimepicker({
		// useCurrent: false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$('#datetimepicker47').datetimepicker({
		useCurrent : false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$("#datetimepicker46").on("dp.change", function(e) {
		$('#datetimepicker47').data("DateTimePicker").minDate(e.date);
	});
	$("#datetimepicker47").on("dp.change", function(e) {
		$('#datetimepicker46').data("DateTimePicker").maxDate(e.date);
	});

	$('#datetimepicker36').datetimepicker({
		// useCurrent: false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$('#datetimepicker37').datetimepicker({
		useCurrent : false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$("#datetimepicker36").on("dp.change", function(e) {
		$('#datetimepicker37').data("DateTimePicker").minDate(e.date);
	});
	$("#datetimepicker37").on("dp.change", function(e) {
		$('#datetimepicker36').data("DateTimePicker").maxDate(e.date);
	});

	$('#datetimepicker56').datetimepicker({
		// useCurrent: false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$('#datetimepicker57').datetimepicker({
		useCurrent : false,
		locale : 'en-gb'// Important! See issue #1075
	});
	$("#datetimepicker56").on("dp.change", function(e) {
		$('#datetimepicker57').data("DateTimePicker").minDate(e.date);
	});
	$("#datetimepicker57").on("dp.change", function(e) {
		$('#datetimepicker56').data("DateTimePicker").maxDate(e.date);
	});

	$('#semenu7').on('click',function() {
		document.getElementById("NoOfFailureAndTotalDurationStart").value = "";
		document.getElementById("NoOfFailureAndTotalDurationEnd").value = "";
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myNoOfFailureAndTotalDurationTable").style.display = "none";
		$("#myNoOfFailureAndTotalDurationTable").trigger('update');
//		$("#myNoOfFailureAndTotalDurationTable tr:gt(0)").remove();
//		document.getElementById("myNoOfFailureAndTotalDurationTable").style.visibility = "hidden";
	});
	$('#semenu8').on('click',function() {
		document.getElementById("TopTenStart").value = "";
		document.getElementById("TopTenEnd").value = "";
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myIMSICountTable").style.display = "none";
		$("#myIMSICountTable").trigger('update');
		document.getElementById("Top10IMSI").style.display = "none";
//		$("#myIMSICountTable tr:gt(0)").remove();
//		document.getElementById("myIMSICountTable").style.visibility = "hidden";
	});
	$('#semenu9').on('click', function() {
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myModelTable").style.display = "none";
		
		$("#myModelTable").trigger('update');
//		$("#myModelTable tr:gt(0)").remove();
//		document.getElementById("myModelTable").style.visibility = "hidden";
	});
	$('#semenu10').on('click', function() {
		document.getElementById("StartMarketTime").value = "";
		document.getElementById("EndMarketTime").value = "";
		$(".odd").remove();
		$(".even").remove();
		document.getElementById("myMarketTable").style.display = "none";
		$("#myMarketTable").trigger('update');
		document.getElementById("graphContainer").style.display = "none";
//		$("#myMarketTable tr:gt(0)").remove();
//		document.getElementById("myMarketTable").style.visibility = "hidden";
	});

	$('#logout').on('click', function(){
		window.sessionStorage.setItem("SEoption","0");
		window.sessionStorage.setItem("type","0");
		window.sessionStorage.setItem("UserName", "");
		window.location.replace("http://"+document.location.host+"/maven_Project/");
	});
});