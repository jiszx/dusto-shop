<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>送达方数据</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <link rel="stylesheet" href="static/wizards/css/bwizard.min.css">
    <link rel="stylesheet" href="static/table/new/bootstrap-table.css">
    <link rel="stylesheet" href="static/css/pub.css">
</head>
<body class="container-fluid content">
<section class="content-header">
    <h1>
        送达方基本信息详情<small></small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="icon icon-home"></i> 首页</a></li>
        <li><a href="#">客户管理</a></li>
        <li class="active">送达方基本信息</li>
    </ol>
</section>
<form class="col-md-12" style=" min-height: 200px; padding:10px 0;">
    <%-- block 1 --%>
    <div class="page-header" id="block-1">
        <h4 class="text-info"><strong>1.&nbsp;</strong>基本资料&nbsp;-&nbsp;
            <small>Basic Information.</small>
            <%--<a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a>--%>
            <%--<a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-trash"></i>&nbsp;&nbsp;清空</a>--%>
        </h4>
    </div>
    <div class="form-horizontal row" style="margin: 0 0 20px 0;">
        <table class="table table-bordered info-table"  id="base">
            <tbody>
                <tr>
                    <th>送达方名称</th>
                    <td>${custBase.name }</td>
                    <th>送达方简称</th>
                    <td>${custBase.abbrName }</td>
                </tr>
                <tr>
                    <th>对应售达方</th>
                    <td>${saleToName }</td>
                    <th>所属地区</th>
                    <td>${custBase.provName }${custBase.cityName }${custBase.countyName }</td>
                </tr>
                <tr>
                    <th>送达方电话</th>
                    <td>${custBase.tel }</td>
                    <th>详细地址</th>
                    <td>${custBase.address }</td>
                </tr>
                <tr>
                    <th>送达方联系人</th>
                    <td>${custBase.contactName }</td>
                </tr>
            </tbody>
        </table>
    </div>
    <%-- block 2 --%>
    <div class="page-header" id="block-2">
        <h4 class="text-info"><strong>2.&nbsp;</strong>送达方信息&nbsp;-&nbsp;
            <small>Receiving Information.</small>
        </h4>
    </div>
    <div class="row" style="margin: 0 0 20px 0;">
        <div class="col-md-12">
            <table id="received-table" class="contract-box"
                   data-search="false"
                   data-show-refresh="false"
                   data-show-columns="false"
                   data-show-export="false"
                   data-detail-view="false"
                   data-id-field="contactID">
            </table>
            <input id="custDistributions" value='<c:out value="${custBase.jsonDistributions }"></c:out>' type="hidden">
        </div>
    </div>

</form>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/wizards/js/bwizard.js"></script>
<script type="text/javascript" src="static/js/customer/index4reveiver.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="static/chosen/chosen.jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
//    $("html,body").animate({scrollTop: $("#elementid").offset().top}, 1000);
        var _sctop = $(document).scrollTop();
        $(document).scroll(function () {
            _sctop = $(document).scrollTop();
            if (_sctop > 170) {
                $("#stepLink2").css({opacity: 1});
            }
            else if (_sctop < 170) {
                $("#stepLink2").css({opacity: 0});
            }
        });
        $(document).click(function () {

        });
        $("#stepLink > li").click(function () {
            $("#stepLink > li").removeClass("active");
            $(this).addClass("active");
        });
        function changeLinkState(activeLink) {
            $("#stepLink > li").removeClass("active");
            activeLink.addClass("active");
        }

        //联系人table
        var $table2 = $('#received-table');
        var distributesData = $("#custDistributions").val();
        if(typeof distributesData != 'undefined' && $.trim(distributesData) != ''){
            distributesData = $.parseJSON(distributesData);
        }else{
            distributesData=new Array();
        }
        initTable();
        function initTable() {
            $table2.bootstrapTable({
                data: distributesData,
                columns: [
                    {
                        title: 'ID',
                        field: 'ID',
                        rowspan: 1,
                        align: 'center',
                        valign: 'middle',
                        visible: false
                    },{
                        title: '到站',
                        field: 'site',
                        rowspan: 1,
                        align: 'center',
                        valign: 'middle',
                        visible: true
                    },{
                        title: '收货人名称',
                        field: 'name',
                        rowspan: 1,
                        align: 'center',
                        valign: 'middle',
                        visible: true
                    },{
                        title: '物流公司',
                        field: 'logistics',
                        rowspan: 1,
                        align: 'center',
                        valign: 'middle',
                        visible: true
                    }
                ]
            });
        }
    });
</script>
</body>
</html>