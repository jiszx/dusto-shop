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
    <title>管理系统-客户库存上报</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <style type="text/css">
    .customizeWidth{
    width:70%;
    display:inline-block;
    }
    .add-on {
    display: inline-block;
    width: auto;
    height: 20px;
    min-width: 16px;
    padding: 4px 5px;
    font-size: 14px;
    font-weight: normal;
    line-height: 20px;
    text-align: center;
    text-shadow: 0 1px 0 #fff;
    background-color: #eee;
    border: 1px solid #ccc;
}
.checkmonth1 span,.checkmonth span{
	color:#a94442;
}
.checkmonth1 i,.checkmonth i{
	    color: #3c763d;
}
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>客户库存上报<small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">库存上报</li>
    </ol>
</section>
<div class="col-md-12" style="padding:0;">
    <div class="form-group col-md-3 col-sm-6">
        <label>客户名称:</label>
        <input type="text" id="customerName" class="form-control customizeWidth" placeheader="请输入客户名称">
    </div>
    <div class="form-group col-md-3 col-sm-6">
        <label>产品名称:</label>
        <input type="text" id="productName" class="form-control customizeWidth" placeheader="请输入产品名称">
    </div>
    <div class="form-group col-md-3 col-sm-6">
        <label>盘点月份:</label>
        <div class="input-append date" id="datetimepicker"  data-date-format="yyyy-mm" style="width:70%;display:inline-block;line-height:30px;">
	        <input class="span2" size="16" name="date" id="date" type="text" style="width:80%;padding: 6px 12px;height:36px;" readonly>
	        <span class="add-on" style="vertical-align: top;margin-left: -5px;width: 30px;height:36px;"><i class="icon-th" style="vertical-align: middle;margin-left: 0; margin-top: 4px;display: inline-block;"></i></span>
        </div>            
        <!-- <select id="date" class="form-control customizeWidth">
            <option value="">全部</option>
            <option>2016-01</option>
            <option>2016-02</option>
            <option>2016-03</option>
        </select> -->
    </div>
    <div class="form-group col-md-3 col-sm-6" style="display: none;">
	    <label for="custType">客户类型</label>
	    <select class="form-control selectWidth" name="custType" id="custTypeSelection">
	        <option value="">全部</option>
	        <c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
				<option value="${cust_type.chooseVal}">${cust_type.showText}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group col-md-3 col-sm-6" style="display: none;">
    	<c:set var="customerStates" value="<%=com.hhnz.customer.enu.CustomerBaseStateEnu.values() %>"/>
	    <label for="exampleInputEmail3">客户状态</label>
        <select class="form-control selectWidth" name="status" id="customerStatesSelection">
            <option value="">全部</option>
            <c:forEach var="state" items="${customerStates}">
    			<option value="${state.getCode()}" en="${state.toString() }">${state.getDesc()}</option>
			</c:forEach>
        </select>
    </div>
    <div class="form-group col-md-2 col-sm-6">
        <button type="button" id="searchButton" class="btn btn-primary"><i class="icon icon-search">&nbsp;&nbsp查询</i>
    </div>
</div>
<div class="col-md-12" id="dictTool">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button type="button" class="btn btn-primary" id="addBtn" data-toggle="modal" data-target="#addModal"><i class='icon icon-plus'></i> 添加</button>
        <button type="button" class="btn btn-warning" id="editBtn" data-toggle="modal" ><i class='icon icon-edit'></i> 修改</button>
        <button type="button" class="btn btn-del btn-danger"><i class='icon icon-remove'></i> 删除</button>
    </div>
</div>
<div class="col-md-12">
    <table id="custinvTable"></table>
</div>
<input type="hidden" id="orgid" value="${station.orgid}">
<!-- addDialog-->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">库存上报</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="customer/custinv/add" id="addForm">
                	<input name="materialId" type="hidden" id="materialId">
                    <div class="form-group">
                        <label for="merchCustId" class="col-sm-3 control-label">客户</label>
                        <div class="col-sm-6">
                            <select class="form-control no-appearance" id="merchCustId" name="merchCustId">
			                	<option selected>加载中...</option>
			                </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <!-- <div class="form-group">
                        <label for="organizationId" class="col-sm-2 control-label">销售组织</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="organizationId" name="organizationId">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div> -->
                    <div class="form-group">
                    <label for="checkmonth1" class="col-sm-3 control-label">盘点月份</label>
				        <div class="input-append date col-sm-6" id="datetimepicker_e"  data-date-format="yyyy-mm">
					        <input class="span2" size="16" type="text" name="createTs" style="padding: 6px 12px;width:89%;height:36px;" id="checkmonth1" >
					        <span class="add-on" style="vertical-align: top;margin-left: -5px;width: 30px;height:36px;"><i class="icon-th" style="vertical-align: middle;margin-left: 0; margin-top: 4px;display: inline-block;"></i></span>
				        </div>
				        <small class="help-block col-sm-3 checkmonth1"></small>
                    </div>
                    <div class="form-group">
                        <label for="attribute4" class="col-sm-3 control-label">SKU</label>
                        <div class="col-sm-6">
                            <!-- <input type="text" class="form-control" required="required" id="attribute4" name="attribute4"> -->
                            <select class="form-control no-appearance" id="attribute4" name="attribute4" >
                            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="batchNum" class="col-sm-3 control-label">批次号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="batchNum" name="batchNum">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="attribute1" class="col-sm-3 control-label">进货数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="attribute1" name="attribute1">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="invNum" class="col-sm-3 control-label">库存数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="invNum" name="invNum">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="attribute2" class="col-sm-3 control-label">良品数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="attribute2" name="attribute2">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="attribute3" class="col-sm-3 control-label">残次品数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="attribute3" name="attribute3">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="btn-add"  class="btn btn-primary btn-save" form="addForm" >保存</button>
            </div>
        </div>
    </div>
</div>
<!-- editDialog-->
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">客户库存修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="customer/custinv/edit" method="post" id="editForm">
                    <div class="form-group">
                    	<input name="id" type="hidden" id="editid">
                    	<input name="materialId" type="hidden" id="editmaterialId">
                        <label for="editmerchCustId" class="col-sm-3 control-label">客户</label>
                        <div class="col-sm-6">
                            <select class="form-control no-appearance" id="editmerchCustId" name="merchCustId">
			                	<option selected>加载中...</option>
			                </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <!-- <div class="form-group">
                        <label for="editorganizationId" class="col-sm-2 control-label">销售组织</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editorganizationId" name="organizationId">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div> -->
                    <div class="form-group">
                    <label for="checkmonth" class="col-sm-3 control-label">盘点月份</label>
				        <div class="input-append date col-sm-6" id="datetimepicker_d"  data-date-format="yyyy-mm" style="line-height:30px;float:left;">
					        <input class="span2" size="16" type="text" id="editcreateTsStr" name="createTs" style="padding: 6px 12px;width:88%;height:36px;" readonly>
					        <span class="add-on" style="vertical-align: top;margin-left: -5px;width: 12%;height:36px;"><i class="icon-th" style="vertical-align: middle;margin-left: 0; margin-top: 4px;display: inline-block;"></i></span>
				        </div>
				        <small class="help-block col-sm-3 checkmonth"></small>
                        <!-- <label for="editcreateTsStr" class="col-sm-2 control-label">盘点月份</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editcreateTsStr" name="createTs">
                        </div>
                        <small class="help-block col-sm-4"></small> -->
                    </div>
                    <div class="form-group">
                        <label for="editattribute4" class="col-sm-3 control-label">SKU</label>
                        <div class="col-sm-6">
                            <!-- <input type="text" class="form-control" required="required" id="editattribute4" name="attribute4"> -->
                            <select class="form-control no-appearance" id="editattribute4" name="attribute4" >
                            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editbatchNum" class="col-sm-3 control-label">批次号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editbatchNum" name="batchNum">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editattribute1" class="col-sm-3 control-label">进货数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editattribute1" name="attribute1">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editinvNum" class="col-sm-3 control-label">库存数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editinvNum" name="invNum">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editattribute2" class="col-sm-3 control-label">良品数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editattribute2" name="attribute2">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="editattribute3" class="col-sm-3 control-label">残次品数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editattribute3" name="attribute3">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style"margin-top:25px;">
                <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
                <button type="submit" id="btn-edit-save" form="editForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<jsp:include page="/common/footjs-table.jsp"></jsp:include>
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="static/js/customer/custinv.js"></script>
<script type="text/javascript">
	$("#datetimepicker").datetimepicker({
		 minView: "month",
		 format: "yyyy-mm", //选择日期后，文本框显示的日期格式
         language: 'zh-CN', //汉化
         autoclose:true //选择日期后自动关闭
	});
	$("#datetimepicker_d").datetimepicker({
		 minView: "month",
		 format: "yyyy-mm", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true //选择日期后自动关闭
	});
	$("#datetimepicker_e").datetimepicker({
		 minView: "month",
		 format: "yyyy-mm", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose:true //选择日期后自动关闭
	});
	$("#btn-add").click(function(){
		if($("#checkmonth1").val()==""||$("#checkmonth").val()==null){
			$(".checkmonth1").html("<span>这是必填字段</span>");
		}else{
			$(".checkmonth1").html("<i class='glyphicon glyphicon-ok'></i>");
		}
	})
	$("#btn-edit-save").click(function(){
		if($("#editcreateTsStr").val()==""||$("#editcreateTsStr").val()==null){
			$(".checkmonth").html("<span>这是必填字段</span>");
		}else{
			$(".checkmonth").html("<i class='glyphicon glyphicon-ok'></i>");
		}
	})
</script>
</body>
</html>
