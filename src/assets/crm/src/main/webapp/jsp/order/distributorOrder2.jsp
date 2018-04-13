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
    <title>管理系统-配送商订单</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link href="static/table/bootstrap-table.min.css" rel="stylesheet">
    <style>
        .col-md-3 {
            margin-bottom: 5px;
        }
    </style>
</head>
<body class="container-fluid content">

<section class="content-header">
    <h1>
        订单管理
        <small>零售商订单录入</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#"><i class="icon icon-home"></i> 订单管理</a></li>
        <li class="active">配送商下单</li>
    </ol>
</section>

<div class="col-md-12" style="background-color: #FFF;">
    <div class="col-md-3 col-sm-4">
        <div class="col-md-12">
            <img class="img img-rounded img-responsive" src="static/images/yan.jpg">
        </div>
        <label class="text-red col-md-12">￥ 28.20</label>
        <label class="col-md-12">
            <small>浙盐蓝海星代工绿色加碘健康平衡盐300G*60躺袋纸箱装</small>
        </label>
        <div class="col-md-12">
            <button class="btn btn-info btn-xs pull-rgith">加入购物车</button>
        </div>
    </div>

    <div class="col-md-3 col-sm-4">
        <div class="col-md-12">
            <img class="img img-rounded img-responsive" src="static/images/yan.jpg">
        </div>
        <label class="text-red col-md-12">￥ 28.20</label>
        <label class="col-md-12">
            <small>浙盐蓝海星代工绿色加碘健康平衡盐300G*60躺袋纸箱装</small>
        </label>
        <div class="col-md-12">
            <button class="btn btn-info btn-xs pull-rgith">加入购物车</button>
        </div>
    </div>

    <div class="col-md-3 col-sm-4">
        <div class="col-md-12">
            <img class="img img-rounded img-responsive" src="static/images/yan.jpg">
        </div>
        <label class="text-red col-md-12">￥ 28.20</label>
        <label class="col-md-12">
            <small>浙盐蓝海星代工绿色加碘健康平衡盐300G*60躺袋纸箱装</small>
        </label>
        <div class="col-md-12">
            <button class="btn btn-info btn-xs pull-rgith">加入购物车</button>
        </div>
    </div>


    <div class="col-md-3 col-sm-4">
        <div class="col-md-12">
            <img class="img img-rounded img-responsive" src="static/images/yan.jpg">
        </div>
        <label class="text-red col-md-12">￥ 28.20</label>
        <label class="col-md-12">
            <small>浙盐蓝海星代工绿色加碘健康平衡盐300G*60躺袋纸箱装</small>
        </label>
        <div class="col-md-12">
            <button class="btn btn-info btn-xs pull-rgith">加入购物车</button>
        </div>
    </div>

    <div class="col-md-3 col-sm-4">
        <div class="col-md-12">
            <img class="img img-rounded img-responsive" src="static/images/yan.jpg">
        </div>
        <label class="text-red col-md-12">￥ 28.20</label>
        <label class="col-md-12">
            <small>浙盐蓝海星代工绿色加碘健康平衡盐300G*60躺袋纸箱装</small>
        </label>
        <div class="col-md-12">
            <button class="btn btn-info btn-xs pull-rgith">加入购物车</button>
        </div>
    </div>


</div>


<!-- Modal -->
<!-- editDialog-->
<div class="modal fade" id="editLineModal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="dictLabel">行数量修改</h4>
            </div>
            <div class="modal-body">
                <form action="" class="form-horizontal">
                    <div class="form-group">
                        <label for="payName" class="col-sm-3 control-label">物料名称</label> <input
                            type="hidden" id="editid" name="id">
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editsku"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <input id="editid" type="hidden">
                    <input type="hidden" id="editmaterialId">
                    <div class="form-group">
                        <label for="payAmt" class="col-sm-3 control-label">单位</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editunit"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group">
                        <label for="payAmt" class="col-sm-3 control-label">单价(元)</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editorderPrice"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <div class="form-group hide">
                        <label for="editinvnum" class="col-sm-3 control-label">可售库存</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editinvnum"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <input type="hidden" id="editprice">
                    <input type="hidden" id="edithPrice">
                    <input type="hidden" id="editwlPrice">
                    <div class="form-group">
                        <label for="payCity" class="col-sm-3 control-label">数量</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editnum">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                    <input type="hidden" id="editamt">
                    <div class="form-group">
                        <label for="editpayDate" class="col-sm-3 control-label">金额(元)</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="editorderAmt"
                                   readonly="readonly">
                        </div>
                        <small class="help-block col-sm-3"></small>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btn-edit-save" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript"
        src="static/table/bootstrap-table.min.js"></script>
<script type="text/javascript"
        src="static/table/new/bootstrap-table.js"></script>
<script type="text/javascript"
        src="static/table/new/bootstrap-table-export.js"></script>
<script type="text/javascript"
        src="static/table/new/bootstrap-table-editable.js"></script>
<script type="text/javascript"
        src="static/table/new/bootstrap-editable.js"></script>
<script type="text/javascript"
        src="static/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript" src="static/js/order/distributorOrder.js"></script>
<script type="text/javascript">
    function getverification(value) {
        var data = new Object();
        <c:forEach items="${dict['POLICY_TYPE_VERIFICATION']}" var="type">
        data["${type.chooseVal}"] = "${type.showText}"
        </c:forEach>
        if (data[value]) {
            return data[value];
        } else {
            return "未知";
        }
    }
</script>
</body>
</html>
