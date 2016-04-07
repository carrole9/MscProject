
function Top10IMSI(names, numbers, graphTitle, subTitle, xTitle, yTitle) {
        var options = {
            chart: {
                renderTo: 'Top10IMSI',
				type: 'column',
				zoomType: 'x'
            },
            title: {
                text: graphTitle
            },
            subtitle: {
                text: subTitle
            },
            xAxis: {
                categories: names,
                title: {
                    text: xTitle
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: yTitle,
//                    align: 'center'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
            	headerFormat: '<span style="color:{point.color}"> IMSI</span>: <b>{point.x}</b> <br>',
            	pointFormat: '<span style="color:{point.color}">{series.name}</span>: <b>{point.y}</b> Failures<br/>'
//                valueSuffix: yTitle
            },
            plotOptions: {
            	series: {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,
//                        format: '{point.y:.1f}%'
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: '#FFFFFF',
                shadow: true,
				enabled: false
            },
			exporting: { enabled: true},
            credits: {
                enabled: false
            },
            series: [ {
            	colorByPoint: true,
                name: yTitle,
                data: numbers
            }]
        };
		
		var chart = new Highcharts.Chart(options);
    }

function failuresGraph(names, numbers, graphTitle, subTitle, xTitle, yTitle, durations) {
    var options = {
        chart: {
            renderTo: 'failuresGraph',
			type: 'bar'
        },
        title: {
            text: graphTitle
        },
        subtitle: {
            text: subTitle
        },
        xAxis: {
            categories: names,
            title: {
                text: xTitle
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: yTitle,
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ''
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: '#FFFFFF',
            shadow: true,
			
			
			
			
        },
		exporting: { enabled: false},
        credits: {
            enabled: false
        },
        series: [ {
            name: "Failures",
            data: numbers
        },
        
        {
        	name: 'Durations (1000 seconds)',
        	data: durations
        }]
    };
	
	var chart = new Highcharts.Chart(options);
}

function durationsGraph(names, numbers, graphTitle, subTitle, xTitle, yTitle) {
    var options = {
        chart: {
            renderTo: 'durationsGraph',
			type: 'bar'
        },
        title: {
            text: graphTitle
        },
        subtitle: {
            text: subTitle
        },
        xAxis: {
            categories: names,
            title: {
                text: xTitle
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: yTitle,
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: yTitle
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: '#FFFFFF',
            shadow: true,
			enabled: false
			
			
			
        },
		exporting: { enabled: false},
        credits: {
            enabled: false
        },
        series: [ {
            name: yTitle,
            data: numbers
        }]
    };
	
	var chart = new Highcharts.Chart(options);
}