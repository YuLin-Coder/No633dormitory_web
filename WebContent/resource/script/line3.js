layui.use(['echarts','jquery'], function() {
	let echarts = layui.echarts;
	$= layui.jquery;

	let line3 = echarts.init(document.getElementById('line3'));
	let count = $("#count").val();
	let bookNameDate =[];
	let tempDate = [];
	for(let i = 0 ; i< count ;i++){
		bookNameDate.push($("#tempName"+i).val());
	}
	for(let i = 0 ; i< count ;i++){
		tempDate.push($("#temp"+i).val());
	}
	const colorList = ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']
	
	option = {
	    backgroundColor: '#fff',
	    title: {
	        text: "图书排名（按借出次数排名）",
	        left: "18px",
	        top: "0",
	        textStyle: {
	            color: "#999",
	            fontSize: 18,
	            fontWeight: '400'
	        }
	    },
	    color: ['#73A0FA', '#73DEB3', '#FFB761'],
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            crossStyle: {
	                color: '#999'
	            },
	            lineStyle: {
	                type: 'dashed'
	            }
	        }
	    },
	    grid: {
	        left: '25',
	        right: '25',
	        bottom: '24',
	        top: '75',
	        containLabel: true
	    },
	    legend: {
	        data: [ '借书次数'],
	        orient: 'horizontal',
	        icon: "rect",
	        show: true,
	        left: 20,
	        top: 25,
	    },
	    xAxis: {
	        type: 'category',
	        //data: [ time1, '${temp}', '爱立信HSS', '中兴HSS', '华为HSS', '华为智能网', '中兴VIMS'],
	        data: bookNameDate ,
	        splitLine: {
	            show: false
	        },
	        axisTick: {
	            show: false
	        },
	        axisLine: {
	            show: false
	        },
	    },
	    yAxis: {
	        type: 'value',
	        // max: max_value>=100? max_value + 100: max_value+10,
	        // max: max_value > 100 ? max_value * 2 : max_value + 10,
	        // interval: 10,
	        // nameLocation: "center",
	        axisLabel: {
	            color: '#999',
	            textStyle: {
	                fontSize: 12
	            },
	        },
	        splitLine: {
	            show: true,
	            lineStyle: {
	                color: '#F3F4F4'
	            }
	        },
	        axisTick: {
	            show: false
	        },
	        axisLine: {
	            show: false
	        },
	    },
	    series: [{
	            name: '总借阅数',
	            type: 'line',
	            smooth: true,
	           // data: [1800,1000]
	             data: tempDate
	        }
	    ]
	};
	
	line3.setOption(option);

	window.onresize = function() {
		line3.resize();
	}
})
