<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<script src="https://cdn.bootcss.com/Chart.js/2.7.1/Chart.min.js"></script>
</head>
<body>
	<jsp:include page="../../../common/header.jsp"></jsp:include>
	<jsp:include page="../../../common/left.jsp"></jsp:include>
	<div id="right" style="width:600px;height:500px;display:inline-block;margin-left:0px">
		<canvas id="userGenderChartCanvas"></canvas>
	</div>
	<div id="rightTemp" style="width:500px;height:500px;display:inline-block">
		<canvas id="userCreateChartCanvas"></canvas>
	</div>
	<script type="text/javascript">
	// 用户性别统计参数（饼状图）
    var userGenderChartConfig = {
        type: 'pie',
        data: {
            labels: ${userGenderData.names},
            datasets: [{
                data: ${userGenderData.values },
                backgroundColor: ["#FF6384", "#36A2EB"]
            }]
        },
        options: {
            title: {
                display: true,
                text: '用户性别分布统计'
            },
            responsive: true
        }
    };

    // 用户创建日期统计参数（折线图）
    var userCreateChartConfig = {
        type: 'line',
        data: {
            labels: ${userCreateData.names },
            datasets: [{
                label: "创建日期",
                fill: false,
                data: ${userCreateData.values }
            }]
        },
        options: {
            title: {
                display: true,
                text: '用户创建日期统计'
            },
            responsive: true
        }
    };

    $(function() {
        // 生成用户性别统计图
        var userGenderChartCtx = document.getElementById("userGenderChartCanvas").getContext("2d");
        var userGenderChartCanvas = new Chart(userGenderChartCtx, userGenderChartConfig);

        // 生成用户创建日期统计图            
        var userCreateChartCtx = document.getElementById("userCreateChartCanvas").getContext("2d");
        var userCreateChartCanvas = new Chart(userCreateChartCtx, userCreateChartConfig);
    });
	
	</script>
</body>
</html>