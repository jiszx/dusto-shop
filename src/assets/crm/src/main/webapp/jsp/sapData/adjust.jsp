<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>管理系统-产品价格管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/css/search-bar.css" rel="stylesheet" type="text/css" />
    <link href="static/bootstrap/css/bootstrap-datetimepicker.min.css"	rel="stylesheet">
    <style type="text/css">
    	.input-box-list-title{
    	text-align:left !important;}
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>产品价格调整<small>调整基础价及调整价</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 数据管理</a></li>
        <li class="active">产品价格调整</li>
    </ol>
</section>
<div class="form-horizontal row"  id="searchForm" style="padding: 0 0 20px 21px;border-bottom:1px solid hsla(255,0%,90%,1); ">
	<form id="searchForm" action="" method="get">
    <div class="form-group  input-box-list col-md-2" >
        <label class=" font12 input-box-list-title">调整类型：</label>
        <div class="input-box-list-value">
            <select id="selectType" name="adjustType" class="form-control no-appearance">
            	<option value="">全部</option>
            	<option value="1">标准价</option>
            	<option value="2">调整价</option>
            </select>
        </div>
    </div>
    <div class="form-group input-box-list col-md-3">
        <label class=" font12 input-box-list-title">处理时间：</label>
        <div class="input-box-list-value">
        	<span class="col-sm-5" style="padding: 0;">
	        	<input type="text" placeholder="从" class="form-control date" id="s_bdate" name="bdate" >
        	</span>
        	<span class="col-sm-1" style="text-align: center;">-</span>
        	<span class="col-sm-5"  style="padding: 0;">
	        	<input type="text" placeholder="到" class="form-control date col-sm-6" id="s_edate" name="edate" >
        	</span>
        </div>
    </div>
    <div class="form-group  input-box-list col-md-2">
        <label class=" font12 input-box-list-title">调整状态：</label>
        <div class="input-box-list-value">
           <select id="selectStatus" name="status" class="form-control">
           <option value="">全部</option>
           <option value="1">草稿</option>
           <option value="2">已提交</option>
           <option value="3">审批通过</option>
           <option value="4">驳回</option>
           </select>
        </div>
    </div>
    <div class="form-group input-box-list col-md-2">
        <label class=" font12 input-box-list-title" style="width:105px;">影响的物料编号：</label>
        <div class="input-box-list-value">
        	<input type="text" placeholder="影响的物料编号" class="form-control" name="relatedMaterialId" >
        </div>
    </div>
    <!-- <div class="form-group  input-box-list col-md-3 col-sm-6 mustShow">
        <label class=" font12 input-box-list-title">物料编码：</label>
        <div class="input-box-list-value">
           <input id="selectsapid" name="sapid" type="text" class="form-control" placeholder="请输入物料编码">
        </div>
    </div> -->
    <div class="form-group col-md-offset-9 col-md-3 input-box-list ">
        <button type="button" class="btn btn-success"  id="searchBtn" onclick="return false"><i class="icon icon-search">&nbsp;&nbsp查询</i></button>
    </div>
    </form>
</div>
<div class="col-md-12" id="dictTool">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button id="importPriceButton" type="button" class="btn btn-primary" ><i class='icon icon-plus'></i> 导入标准价</button>
        <button id="editBasePriceButton" class="btn btn-success" ><i class='icon icon-edit'></i> 编辑标准价</button>
        <button id="editAdjustPriceButton" class="btn btn-warning" ><i class='icon icon-edit'></i> 编辑调整价</button>
        <button id="editBatchAdjustPriceButton" class="btn btn-info" ><i class='icon icon-edit'></i> 批量处理调整价</button>
        <button id="submitAdjustButton" class="btn btn-primary" ><i class='icon approval_img'></i> 提交审批</button>
        <button id="delAdjustButton" class="btn btn-danger" ><i class='icon icon-trash'></i> 删除</button>
    </div>
</div>
<div class="col-md-12">
    <table id="dictTable"></table>
</div>
<!-- importDialog-->
<div class="modal fade" id="importModal" tabindex="-1" role="dialog"
     aria-labelledby="importLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">导入标准价</h4>
            </div>
            <div class="modal-body" style="overflow-y: auto">
                <!-- 产品图片上传  -->
                <form class="form-horizontal" method="post" action="product/price/import" enctype="multipart/form-data" id="importForm">
                	<input type="hidden" class="form-control" id="addid" name="objectName">
                    <div class="form-group">
                        <label for="uploadFile" class="col-sm-3 control-label">价格Excel文件</label>
                        <div class="col-sm-5">
                            <input type="file" class="form-control" id="uploadFile" name="file" >
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" id="btn-import"  class="btn btn-primary btn-save" form="importForm" >上传</button>
            </div>
        </div>
    </div>
</div>
<!-- addDialog-->
<div class="modal fade" id="editBasePriceModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editbpLabel">编辑标准价</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="product/price/edit" method="post" id="editBasePriceForm">
                    <div class="form-group">
                        <label for="colName" class="col-sm-3 control-label">物料编码</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="editid" name="id">
                            <input type="text" placeholder="物料编码" class="form-control" id="input_materialId" name="materialId">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="chooseVal" class="col-sm-3 control-label">销售组织</label>
                        <div class="col-sm-6">
                            <!-- <input type="text" placeholder="销售组织" class="form-control" id="input_organizationId" name="organizationId"> -->
                            <select class="form-control" id="input_organizationId" name="organizationId">
							</select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="showText" class="col-sm-3 control-label">渠道</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="渠道" class="form-control" id="input_channel" name="channel" value="10">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-3 control-label">价格</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="价格" class="form-control" id="input_price" name="price">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-3 control-label">有效期起始日</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="有效期起始日" class="form-control date" id="input_bdate" name="bdate" >
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-3 control-label">有效期截止日</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="有效期截止日" class="form-control date" id="input_edate" name="edate" >
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
                <button type="submit" id="btn-edit-save" form="editBasePriceForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<%-- editAdjustDialog --%>
<div class="modal fade" id="editAdjustPriceModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editbpLabel">编辑调整价</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="product/price/adjust/edit" method="post" id="editAdjustPriceForm">
                    <div class="form-group">
                        <label for="colName" class="col-sm-3 control-label">物料编码</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="editid" name="id">
                            <input type="text" placeholder="物料编码" class="form-control" id="input_materialId" name="materialId">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="chooseVal" class="col-sm-3 control-label">销售组织</label>
                        <div class="col-sm-6">
                            <!-- <input type="text" placeholder="销售组织" class="form-control" id="input_organizationId" name="organizationId"> -->
                            <select class="form-control" id="input_organizationId" name="organizationId">
							</select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="showText" class="col-sm-3 control-label">渠道</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="渠道" class="form-control" id="input_channel" name="channel" value="10">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-3 control-label">调整值</label>
                        <div class="col-sm-6">
                        	<div class="input-group">
					            <span class="input-group-addon">标准价</span>
					            <select class="form-control" name="adjustOpt" style="width: 27%; padding: 6px 1px;" onchange="var a = $(this).val();if(a == '1'){$(this).next().attr('placeholder','调整值').removeAttr('title')}if(a == '2'){$(this).next().attr('placeholder','倍数，系统会根据标准价转换为调整值').attr('title','倍数，系统会根据标准价转换为调整值')}" >
					            	<option value="1">加上</option>
					            	<option value="2">乘以</option>
					            </select>
					            <input type="text" placeholder="调整值" class="form-control" id="input_price" name="adjustVal" style="width: 73%;">
					        </div>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-3 control-label">有效期起始日</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="有效期起始日" class="form-control date" id="input_bdate_1" name="bdate" >
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-3 control-label">有效期截止日</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="有效期截止日" class="form-control date" id="input_edate_1" name="edate" >
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
                <button type="submit" id="btn-edit-adjust-save" form="editAdjustPriceForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<%-- editAdjustDialog --%>
<div class="modal fade" id="editBatchAdjustPriceModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editbpLabel">批量处理调整价</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="product/price/adjust/edit/batch" method="post" id="editAdjustPriceBatchForm">
                    <div class="form-group">
                        <label for="orders" class="col-sm-3 control-label">调整类别</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="input_adjustCategory" name="adjustCategory">
                            	<option value="1">根据副品牌调整</option>
                            	<option value="2">根据系列调整</option>
                            	<option value="3">根据物料编号调整</option>
                            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group" id="look_1">
                        <label for="orders" class="col-sm-3 control-label">品牌</label>
                        <div class="col-sm-6">
                            <select id="selectvicebrand" class="form-control no-appearance" name="adjustParam">
				            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group" style="display: none;"  id="look_2">
                        <label for="orders" class="col-sm-3 control-label">系列</label>
                        <div class="col-sm-6">
                            <select id="selectseries" class="form-control no-appearance" name="adjustParam">
				            </select>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group" style="display: none;"  id="look_3">
                        <label for="orders" class="col-sm-3 control-label">物料编号</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="物料编号,以英文逗号分隔" class="form-control" id="input_price" name="adjustParam">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-3 control-label">调整值</label>
                        <div class="col-sm-6">
                        	<div class="input-group">
					            <span class="input-group-addon">标准价</span>
					            <select class="form-control" name="adjustOpt" style="width: 27%; padding: 6px 1px;" onchange="var a = $(this).val();if(a == '1'){$(this).next().attr('placeholder','调整值').removeAttr('title')}if(a == '2'){$(this).next().attr('placeholder','倍数，系统会根据标准价转换为调整值').attr('title','倍数，系统会根据标准价转换为调整值')}" >
					            	<option value="1">加上</option>
					            	<option value="2">乘以</option>
					            </select>
					            <input type="text" placeholder="调整值" class="form-control" id="input_price" name="adjustVal" style="width: 73%;">
					        </div>
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-3 control-label">有效期起始日</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="有效期起始日" class="form-control date" id="input_bdate_2" name="bdate" >
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-3 control-label">有效期截止日</label>
                        <div class="col-sm-6">
                            <input type="text" placeholder="有效期截止日" class="form-control date" id="input_edate_2" name="edate" >
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
                <button type="submit" id="btn-edit-adjust-batch-save" form="editAdjustPriceBatchForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/table/extensions/export/bootstrap-table-export.min.js"></script>
<script type="text/javascript" src="static/table/extensions/export/tableExport.min.js"></script>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="static/js/sapData/product_adjust.js"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"	charset="UTF-8"></script>
</body>
</html>
