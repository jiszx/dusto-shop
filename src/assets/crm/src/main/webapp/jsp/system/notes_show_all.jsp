<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>管理系统-所有公告</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <style type="text/css">
    	.btn-appraval{
    		    background-color: #9CBC3C !important;
    	}
    	#notesTable thead{
			display:none !important;    	
    	}
    	 .fixed-table-toolbar{
    	display:none !important;
    	}
    	#notesTable tbody tr{
    	border:none;
    	}
    	#notesTable tbody tr td{
    	border:none;
    	}
    	.fixed-table-container{
    	border:none !important;}
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>查看公告
    </h1>
</section>
<div class="col-md-12">
    <table id="notesTable"></table>
</div>

<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/js/system/notes_show_all.js"></script>
</body>
</html>
