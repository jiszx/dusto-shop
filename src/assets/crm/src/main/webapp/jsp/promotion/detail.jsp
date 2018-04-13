<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>管理系统-促销品管理</title>
<jsp:include page="/common/head.jsp"></jsp:include>
<link href="static/table/bootstrap-table.min.css" rel="stylesheet">
   <style>
        body {
            font-family: "微软雅黑";
        }
        ul,li{
            list-style: none;
        }
        select {
            -webkit-appearance: none;
            -webkit-border-radius: 0;
        }
        .nav.nav-pills > li{
            padding: 5px 0;
        }

        .nav.nav-pills > li > a{
            padding: 5px 15px;
            background: rgba(0,0,0,.05);
            border:none;
            transition: 0.2s;
            font-size: 13px;

        }
        .nav.nav-pills > li.active > a,.nav.nav-pills > li.active > a:hover{
            color: #fff;
            background: #3c8dbc;
            font-weight: normal;
        }
        .nav.nav-pills > li > a:hover{
            background: hsla(202,68%,74%,.35);
            font-weight: normal;
        }
        .font12{
            font-size: 12px;
        }
        .form-horizontal .input-box-list{
            display: table;
            margin:10px 0 0 0;
            padding-left: 10px;
        }
        .input-box-list-value textarea{
            resize: none;
        }
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{
            font-size: 12px;
        }
        .btn-long{
            padding:8px 6.18% ;
        }
        .block-save-link{
            float: right;
            font-size: 13px;
            padding:4px 10px 0;
        }
        .block-save-link:hover{
            text-decoration: underline;
        }
        .input-box-list-title, .input-box-list-value{
            display: table-cell;
        }
        .input-box-list-title{
            width: 100px;
            text-align: right;
            vertical-align: middle;
            padding-right: 5px;
        }
        .input-box-list-value textarea{
            resize: none;
        }
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{
            font-size: 12px;
        }
        /* this page */
        .user-select-panel{
            border: 1px solid hsla(0,0%,90%,1);
            background-color: #fff;
            padding: 0 10px;
        }
        .user-select-list{
            height: 146px;
            overflow-y: auto;

        }
        .user-select-panel li{
            border-bottom: 1px solid hsla(0,0%,95%,1);
            /*padding:5px 0 ;*/
        }
        .user-select-panel li .radio{
            padding-left: 10px;
        }
        .factory-select-box .dropdown{
            margin-bottom: 10px;
        }
        .factory-select-box .dropdown a.btn{
            padding-top:10px;
            padding-bottom:10px;
            background-color: #fff;
            border-radius: 0;
        }
        .factory-select-box .dropdown a.btn span.caret{
            margin-top:8px;
        }
        .factory-select-box .dropdown ul{
            width: 100%;;
        }
        .account-panel{
            border: 1px solid hsla(0,0%,90%,1);
            border-top: 2px solid #a94442;
            padding: 20px 20px 10px;
            background-color: #fff;
        }
        .account-panel>div>p{
            margin:0;
        }
        .account-item{
            margin-bottom: 15px;
        }
        .account-item>span{
            margin:0 5px;
        }
        .account-item:last-child{
            border-top: 1px dashed hsla(0,0%,90%,1);
            padding-top: 20px;
        }
        .order-accordion-item{
            border-radius: 0;
        }
        .order-accordion-item div.panel{
            border-radius: 0;
        }
        .order-accordion-item div.panel-heading{
            border-radius: 0;
            background-color: #fff;
            transition: 0.2s;
        }
        .order-accordion-item div.panel-heading:hover{
            background-color:hsla(202,68%,74%,.15);
        }
        .order-item-title-block{
            padding-right: 30px;
            margin-right: 20px;
            border-right: 1px solid hsla(0,0%,95%,1);
            color: #333;
        }
        .order-plus-box{
            margin-bottom: 0;

        }
        .order-plus-box thead{
            background-color: hsla(255,0%,95%,1);

        }
        .order-plus-box td,.order-plus-box th{
            font-size: 12px;
            text-align: center;

        }

        .order-plus-box-add{
            padding: 10px;
            text-align: center;
            width: 100%;
            font-size: 16px;
            background-color: #fff;
            color: hsla(0,0%,65%,1);
            border:1px dashed hsla(0,0%,90%,1);
            cursor: pointer;
            transition: 0.2s;
        }
        .order-plus-box-add:hover{
            color: hsla(0,0%,35%,1);
            border-color: hsla(0,0%,80%,1);
        }
        .add-bstb-box {
            /*min-height: 100px;*/
            background-color: #fff;
            /*border-bottom:1px solid hsla(255,0%,90%,1);*/
            /*padding: 10px  0;*/
            height: 0;
            opacity: 0;
            overflow: hidden;
            transition: 0.2s;
            clear: both;
        }

        .add-bstb-box-open {
            border-bottom: 1px solid hsla(255, 0%, 90%, 1);
            padding: 10px 0;
            opacity: 1;
            height: auto;
        }
        .order-item{
        	float:left;
        	line-height:30px;
        	margin-right:30px;
        }
        label{
	        text-align:right;
	        line-height:30px;
        }
       
    </style>
</head>
<body>
	<div class="form-group  col-md-4 col-sm-6">
		<label for="organizationId" class="col-md-5 col-sm-6 ">所属销售组织</label>
		<input type="hidden" name="type" id="type" value="1">
		<div class="col-md-7 col-sm-6">
			<input type="text" class="form-control"  readonly="readonly" value="${log.orgname }">
		</div>
		
	</div>
	<div class="form-group col-md-4 col-sm-6">
		<label for="storesId" class="col-md-5 col-sm-6 control-label">库房</label>
		<div class="col-md-7 col-sm-6">
			<input type="text" class="form-control"  readonly="readonly" value="${log.storesname }">
		</div>
		
	</div>
	<div class="form-group col-md-4 col-sm-6">
		<label for="promotionId" class="col-md-5 col-sm-6 control-label">促销品名称</label>
		<div class="col-md-7 col-sm-6">
			<input type="text" class="form-control"  readonly="readonly" value="${log.materialname }">
		</div>
		
	</div>
	<div class="form-group col-md-4 col-sm-6" class="col-sm-12 ">
		<label for="purId" class="col-sm-5 col-sm-6 control-label">采购方名称</label>
		<div class="col-md-7 col-sm-6">
			<input type="text" class="form-control"  readonly="readonly" value="${log.purname }">
		</div>
		<!-- <small class="help-block col-sm-4"></small> -->
	</div>
	<div class="form-group col-md-4 col-sm-6">
		<label for="price" class="col-md-5 col-sm-6 control-label">价格</label>
		<div class="col-md-7 col-sm-6">
			<input type="text" class="form-control"  readonly="readonly" value="${log.price }">
		</div>
		<!-- <small class="help-block col-sm-4"></small> -->
	</div>
	<div class="form-group col-md-4 col-sm-6">
		<label for="num" class="col-md-5 col-sm-6 control-label">采购数量</label>
		<div class="col-md-7 col-sm-6">
			<input type="text" class="form-control"  readonly="readonly" value="${log.num }">
		</div>
		<!-- <small class="help-block col-sm-4"></small> -->
	</div>
	<div class="form-group col-md-4 col-sm-6">
		<label for="amt" class="col-md-5 col-sm-6 control-label">采购金额</label>
		<div class="col-md-7 col-sm-6">
			<input type="text" class="form-control"  readonly="readonly" value="${log.amt }">
		</div>
		<!-- <small class="help-block col-sm-4"></small> -->
	</div>
</body>
</html>