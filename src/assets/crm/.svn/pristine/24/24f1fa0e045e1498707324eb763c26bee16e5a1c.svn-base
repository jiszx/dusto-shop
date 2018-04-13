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
    <title>管理系统-产品管理</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/ztree/css/zTreeStyle/metro.css" rel="stylesheet">
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="static/css/search-bar.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    	.input-box-list-title{
    	text-align:left !important;}
    </style>
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>产品管理<small>为产品添加照片，质检报告</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"> 数据管理</a></li>
        <li class="active">产品管理</li>
    </ol>
</section>
<div class="form-horizontal row"  id="searchForm" style="padding: 0 0 20px 21px;border-bottom:1px solid hsla(255,0%,90%,1); ">
	<form >
    <div class="form-group  input-box-list col-md-3 col-sm-6" >
        <label class=" font12 input-box-list-title">产品品牌：</label>
        <div class="input-box-list-value">
            <select id="selectbrand" class="form-control no-appearance">
            	<option value="">全部</option>
            </select>
        </div>
    </div>
    <div class="form-group  input-box-list col-md-3 col-sm-6" >
        <label class=" font12 input-box-list-title">副品牌：</label>
        <div class="input-box-list-value">
            <select id="selectvicebrand" class="form-control no-appearance">
            	<option value="">全部</option>
            </select>
        </div>
    </div>
    <div class="form-group input-box-list col-md-3 col-sm-6">
        <label class=" font12 input-box-list-title">系列：</label>
        <div class="input-box-list-value">
            <select id="selectseries" class="form-control no-appearance">
            	<option value="">全部</option>
            </select> <!-- <i class="icon icon-caret-down" style="float: right; margin: -25px 10px 0 0;"></i> -->
        </div>
    </div>
    <div class="form-group input-box-list col-md-3 col-sm-6">
        <label class=" font12 input-box-list-title">产品内包装：</label>
        <div class="input-box-list-value">
            <select id="selectipackage" class="form-control no-appearance">
            	<option value="">全部</option>
            </select> <!-- <i class="icon icon-caret-down" style="float: right; margin: -25px 10px 0 0;"></i> -->
        </div>
    </div>
    <div class="form-group  input-box-list col-md-3 col-sm-6">
        <label class=" font12 input-box-list-title">产品名称：</label>
        <div class="input-box-list-value">
           <input id="selectsku" name="sku" type="text" class="form-control" placeholder="请输入产品名称">
        </div>
    </div>
    <div class="form-group  input-box-list col-md-3 col-sm-6 mustShow">
        <label class=" font12 input-box-list-title">物料编码：</label>
        <div class="input-box-list-value">
           <input id="selectsapid" name="sapid" type="text" class="form-control" placeholder="请输入物料编码">
        </div>
    </div>
    <div class="form-group  input-box-list col-md-3 col-sm-6">
        <label class=" font12 input-box-list-title">工厂：</label>
        <div class="input-box-list-value">
           <select id="selectfactory" name="factory" class="form-control">
           </select>
        </div>
    </div>
    <div class="form-group col-md-offset-9 col-md-3 input-box-list col-sm-offset-9 col-sm-3 mustShow">
        <!-- Split button -->
        <div class="btn-group">
            <button type="button" class="btn btn-success"  id="searchBtn"><i class="icon icon-search">&nbsp;&nbsp查询</i></button>
            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
                <span class="sr-only">Toggle Dropdown</span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="#" id="moreSearch">筛选</a></li>
            </ul>
        </div>
    </div>
    <input type="submit" value="Submit" onClick="searchMaterial();return false" class="hide"/>
    </form>
</div>
<div class="col-md-12" id="dictTool">
    <div class="btn-group btn-group-sm" role="group" aria-label="...">
        <button id="addPicButton" type="button" class="btn btn-primary" ><i class='icon icon-plus'></i> 添加照片</button>
        <!-- <button id="classifyButton" type="button" class="btn btn-warning" ><i class='icon icon-plus'></i> 分类</button> -->
        <button id="showDetail" class="btn btn-success" ><i class='icon icon-eye-open'></i> 查看详情</button>
        <button id="exportBtn" class="btn btn-warning" ><i class='icon icon-share'></i>导出</button>
        <button id="showAdjust" class="btn btn-primary" ><i class='icon icon-edit'></i>价格调整</button>
    </div>
</div>
<div class="col-md-12">
    <table id="dictTable"></table>
</div>
<!-- addDialog-->
<div class="modal fade" id="addDictModal" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">添加照片</h4>
            </div>
            <div class="modal-body" style="height: 400px;overflow-y: auto">
                <%-- Edit by wuxun 2016-08-03 15:56 --%>
                <%-- 质检报告 --%>
                <div class="row">
                    <div class="col-md-12">
                        <p class=" col-md-12 border-bottom-default">质检报告</p>
                    </div>
                    <div id="license">
                    
                    </div>
                    
                    <div class="col-md-3">
                        <a href="#"  onclick="$('#uploadFile').click();"class="thumbnail text-muted" style="line-height: 100px;border: 1px solid #ccc; text-align: center; font-size: 24px;"><i class="icon icon-plus"></i></a>
                    </div>
                </div>
                <%-- 产品图片 --%>
                <div class="row">
                    <div class="col-md-12">
                        <p class=" col-md-12 border-bottom-default">产品图片</p>
                    </div>
                    <div id="productImg">
                    	
                    </div>
                    
                    <div class="col-md-3">
                        <a href="#" onclick="$('#uploadFile2').click();" class="thumbnail text-muted" style="line-height: 100px;border: 1px solid #ccc; text-align: center; font-size: 24px;"><i class="icon icon-plus"></i></a>
                    </div>
                </div>
                <!-- 产品图片上传  -->
                <form class="form-horizontal" method="post" action="product/upload.json" style="display: none;" enctype="multipart/form-data" id="addDictForm">
                	<input type="hidden" class="form-control" id="addid" name="objectName">
                    <div class="form-group">
                        <label for="attachmentType" class="col-sm-2 control-label">添加类型</label>
                        <div class="col-sm-6">
                        	<input type="hidden" class="form-control" value="1" id="attachmentType" name="attachmentType">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="uploadFile" class="col-sm-2 control-label">文件</label>
                        <div class="col-sm-6">
                            <input type="file" class="form-control" id="uploadFile" name="file" accept="image/jpeg,image/png,image/bmp,image/gif" onchange="javascript:$('#btn-add').click();" >
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
                <!-- 执照图片上传  -->
                <form class="form-horizontal" method="post" action="product/upload.json" style="display: none;" enctype="multipart/form-data" id="addDictForm2">
                	<input type="hidden" class="form-control" id="addid2" name="objectName">
                    <div class="form-group">
                        <label for="attachmentType" class="col-sm-2 control-label">添加类型</label>
                        <div class="col-sm-6">
                        	<input type="hidden" class="form-control" value="2" name="attachmentType">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="uploadFile" class="col-sm-2 control-label">文件</label>
                        <div class="col-sm-6">
                            <input type="file" class="form-control" id="uploadFile2" name="file" accept="image/jpeg,image/png,image/bmp,image/gif" onchange="javascript:$('#btn-add2').click();" >
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer hidden">
                <button type="submit" id="btn-add"  class="btn btn-primary btn-save" form="addDictForm" >保存</button>
            </div>
            <div class="modal-footer hidden">
                <button type="submit" id="btn-add2"  class="btn btn-primary btn-save" form="addDictForm2" >保存</button>
            </div>
        </div>
    </div>
</div>
<!-- classify-->
<div class="modal fade" id="classifyModal" tabindex="-1" role="dialog"
     aria-labelledby="dictLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">修改分类</h4>
            </div>
            <div class="modal-body">
            	<div class="modal-body" style="height: 300px;overflow-y:auto;">
	                <ul id="groupTree"class="ztree" style="width: 100%;height: 100%;"></ul>
	            </div>
            
                <!-- <form class="form-horizontal" method="post" action="product/upload.json" enctype="multipart/form-data" id="addDictForm">
                	<input type="hidden" class="form-control" id="addid" name="objectName">
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">添加类型</label>
                        <div class="col-sm-6">
                            <select name="attachmentType" class="form-control">
                                <option value="1">产品照片</option>
                                <option value="2">质检报告</option>
                            </select>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="chooseVal" class="col-sm-2 control-label">文件</label>
                        <div class="col-sm-6">
                            <input type="file" class="form-control" id="chooseVal" name="file">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-6 col-sm-offset-2">
                            <img src="" id="uploadImg" class="img-rounded" style="width: 100px;height: 100px;"/>
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                </form> -->
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" id="addClassifyButton"  class="btn btn-primary btn-save" >保存</button>
            </div>
        </div>
    </div>
</div>
<!-- editDialog-->
<div class="modal fade" id="editDictModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editdictLabel">修改字典</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/system/dict/edit" method="post" id="editDictForm">
                    <div class="form-group">
                        <label for="colName" class="col-sm-2 control-label">字典字段</label>
                        <div class="col-sm-6">
                            <input type="hidden" id="editid" name="id">
                            <input type="text" class="form-control" id="editcolName" name="colName">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="chooseVal" class="col-sm-2 control-label">字典值</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editchooseVal" name="chooseVal">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="showText" class="col-sm-2 control-label">显示文本</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editshowText" name="showText">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>
                    <div class="form-group">
                        <label for="orders" class="col-sm-2 control-label">顺序</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editorders" name="orders">
                        </div>
                        <small class="help-block col-sm-4"></small>
                    </div>

                    <div class="form-group">
                        <label for="canChoose" class="col-sm-2 control-label">是否可选</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="editcanChoose" name="canChoose">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="display" class="col-sm-2 control-label">display</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="editdisplay" name="display">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
                <button type="submit" id="btn-edit-save" form="editDictForm" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<%-- detailsDialog --%>
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">产品详情</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal row" style="font-size: 12px;">
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">产品名称：</label>
                        <div class="input-box-list-value"><input id="showmaterialName" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">物料编码：</label>
                        <div class="input-box-list-value"><input id="showsapId" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">销售组织：</label>
                        <div class="input-box-list-value"><input id="showorgname" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">邮编：</label>
                        <div class="input-box-list-value"><input id="showzipcode" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">品牌：</label>
                        <div class="input-box-list-value"><input id="showbrand" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">系列：</label>
                        <div class="input-box-list-value"><input id="showseries" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">品名：</label>
                        <div class="input-box-list-value"><input id="showpName" type="text" class="form-control" readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">包装形式：</label>
                        <div class="input-box-list-value"><input id="showoPackage" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">包装规格：</label>
                        <div class="input-box-list-value"><input id="showspecifications" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">符号：</label>
                        <div class="input-box-list-value"><input id="showsymbol" type="text" class="form-control" readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">省份：</label>
                        <div class="input-box-list-value"><input id="showprovId" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">城市：</label>
                        <div class="input-box-list-value"><input id="showcityId" type="text" class="form-control" readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">碘添加：</label>
                        <div class="input-box-list-value"><input id="showiodine" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">防松剂：</label>
                        <div class="input-box-list-value"><input id="showunloose" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">辅料种类：</label>
                        <div class="input-box-list-value"><input id="showaccessories" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">原盐：</label>
                        <div class="input-box-list-value"><input id="showsalt" type="text" class="form-control" readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">颗粒：</label>
                        <div class="input-box-list-value"><input id="showgrain" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">味形：</label>
                        <div class="input-box-list-value"><input id="showflavor" type="text" class="form-control"  readonly></div>
                    </div>
                    <div class="form-group col-md-3 input-box-list">
                        <label class=" font12 input-box-list-title-2">产出形式：</label>
                        <div class="input-box-list-value"><input id="showproduceType" type="text" class="form-control"  readonly></div>
                    </div>
                </div>
                <div id="imgList" class="img-list-horizontal clearfix">
                    <!-- <p style="border-bottom:1px solid hsla(255, 0%, 90%, 1); margin-bottom: 20px;">产品图片:</p>
                    <a href="static/images/temp.png" target="_blank">
                        <img src="static/images/temp.png">
                        <span>示例图片1</span>
                    </a>
                    <a href="static/images/temp.png" target="_blank">
                        <img src="static/images/temp.png">
                        <span>示例图片2</span>
                    </a>
                    <a href="static/images/temp.png" target="_blank">
                        <img src="static/images/temp.png">
                        <span>示例图片3</span>
                    </a> -->
                </div>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="exportModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">导出</h4>
            </div>
            <div class="modal-body" id="generateFile">
                                                         正在生成excel，请耐心等待...
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
<script type="text/javascript" src="static/js/sapData/product.js"></script>
<script type="text/javascript">
    $(function() {
        $("#searchForm .form-group").hide();
        $("#searchForm .mustShow").show();
        $("#moreSearch").bind("click",function(){
            if($(this).html()=="筛选"){
                $(this).html("收起");
                $("#searchForm .form-group").show();
            }else{
                $("#searchForm .form-group").hide();
                $("#searchForm .mustShow").show();
                $(this).html("筛选");
            }
        })
    })
</script>
</body>
</html>
