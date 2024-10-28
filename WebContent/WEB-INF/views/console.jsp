<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="resource/component/pear/css/pear.css" />
		<link rel="stylesheet" href="resource/admin/css/other/console1.css" />
		<!-- 主 题 更 换 -->
		<style id="pearadmin-bg-color"></style>
	</head>
	<body class="pear-container">
	<a style="font-Size:50px">欢迎来到学生宿舍管理系统</a>
		<script src="resource/component/layui/layui.js"></script>
		<script src="resource/component/pear/pear.js"></script>
		<script>
			layui.use(['layer', 'echarts', 'element', 'count','topBar'], function() {
				var $ = layui.jquery,
					layer = layui.layer,
					element = layui.element,
					count = layui.count,
					echarts = layui.echarts;

				count.up("value1", {
					time: 4000,
					num: 355,
					regulator: 50
				})

				count.up("value2", {
					time: 4000,
					num: 120,
					regulator: 50
				})

				count.up("value3", {
					time: 4000,
					num: 50,
					regulator: 50
				})

				count.up("value4", {
					time: 4000,
					num: 500,
					regulator: 50
				})

				var echartsRecords = echarts.init(document.getElementById('echarts-records'), 'walden');

				var option = {
					tooltip: {
						trigger: 'axis'
					},
					xAxis: [{
						type: 'category',
						data: ['2015','2016', '2017', '2018', '2019', '2020', '2021'],
						axisLine: {
							lineStyle: {
								color: "#999"
							}
						}
					}],
					yAxis: [{
						type: 'value',
						splitNumber: 4,
						splitLine: {
							lineStyle: {
								type: 'dashed',
								color: '#DDD'
							}
						},
						axisLine: {
							show: false,
							lineStyle: {
								color: "#333"
							},
						},
						nameTextStyle: {
							color: "#999"
						},
						splitArea: {
							show: false
						}
					}],
					series: [{
						name: '使用人数',
						type: 'line',
						data: [5,10, 30, 55, 70, 85, 100],
						lineStyle: {
							normal: {
								width: 8,
								color: {
									type: 'linear',

									colorStops: [{
										offset: 0,
										color: '#A9F387' // 0% 处的颜色
									}, {
										offset: 1,
										color: '#48D8BF' // 100% 处的颜色
									}],
									globalCoord: false // 缺省为 false
								},
								shadowColor: 'rgba(72,216,191, 0.3)',
								shadowBlur: 10,
								shadowOffsetY: 20
							}
						},
						itemStyle: {
							normal: {
								color: '#fff',
								borderWidth: 10,
								borderColor: "#A9F387"
							}
						},
						smooth: true
					}]
				};
				echartsRecords.setOption(option);

				window.onresize = function() {
					echartsRecords.resize();
				}

			});
		</script>
	</body>
</html>
