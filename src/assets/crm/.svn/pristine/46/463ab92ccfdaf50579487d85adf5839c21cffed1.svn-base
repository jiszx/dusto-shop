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
    <title>客户数据</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <jsp:include page="/common/lightGallery.jsp"></jsp:include>
    <link rel="stylesheet" href="static/wizards/css/bwizard.min.css">
    <link rel="stylesheet" href="static/table/new/bootstrap-table.css">
    <style>
        body {
            font-family: "微软雅黑";
        }

        .nav.nav-pills > li {
            padding: 5px 0;
        }

        .nav.nav-pills > li > a {
            padding: 5px 15px;
            background: rgba(0, 0, 0, .05);
            border: none;
            transition: 0.2s;
            font-size: 13px;

        }

        .nav.nav-pills > li.active > a, .nav.nav-pills > li.active > a:hover {
            color: #fff;
            background: #3c8dbc;
            font-weight: normal;
        }

        .nav.nav-pills > li > a:hover {
            background: hsla(202, 68%, 74%, .35);
            font-weight: normal;
        }

        #stepLink2 {
            position: fixed;
            top: 0;
            right: 0;
            padding: 15px;
            z-index: 2000;
            background-color: rgba(255, 255, 255, .75);
            box-shadow: -2px 3px 5px hsla(0, 0%, 0%, .15);
            transition: 0.3s;
            opacity: 0;
        }

        #stepLink2:hover {
            background-color: #fff;
        }

        .font12 {
            font-size: 12px;
        }

        .form-horizontal .input-box-list {
            display: table;
            margin: 10px 0 0 0;
            padding-left: 10px;
        }

        .input-box-list-title, .input-box-list-value {
            display: table-cell;
        }

        .input-box-list-title {
            width: 173px;
            text-align: right;
            vertical-align: middle;
            padding-right: 5px;
        }

        textarea {
            resize: none;
        }

        input::-webkit-input-placeholder, textarea::-webkit-input-placeholder {
            font-size: 12px;
        }

        .btn-long {
            padding: 8px 6.18%;
        }

        .block-save-link {
            float: right;
            font-size: 13px;
            padding: 4px 10px 0;
        }

        .block-save-link:hover {
            text-decoration: underline;
        }

        .contact-box, .contact-box-new, .contact-box-add {
            border: 1px solid hsla(0, 0%, 95%, 1);
            background-color: #fff;
            height: 239px;
            padding: 20px 15px;
            max-height: 239px;
            overflow: hidden;
        }

        .bank-box, .bank-box-add {
            border: 1px solid hsla(0, 0%, 95%, 1);
            background-color: #fff;
            height: 190px;
            padding: 20px 15px;
            max-height: 190px;
            overflow: hidden;
        }

        .bank-box-add {
            text-align: center;
            line-height: 180px;
            font-size: 18px;
            color: hsla(0, 0%, 65%, 1);
            border: 1px dashed hsla(0, 0%, 90%, 1);
            cursor: pointer;
            transition: 0.2s;
        }
        .contract-box {
            border-radius: 0;
        }

        .contract-box thead {
            background-color: hsla(255, 0%, 95%, 1);
        }

        .contract-box tbody {
            background-color: #FFF;
        }

        .contract-box td, .lawyer-box th {
            font-size: 12px;
            text-align: center;
        }
        .contract_box{
            width:100%;
        }
        .contract_box tr td{
            border:1px solid #ccc;
            line-height:30px;
            height:30px;
            background-color:#fff;
        }
        .title{
        position: absolute;
		height: 60px !important;
		width: 100px;
		line-height: 60px !important;
		border；1px solid #ccc;
		left: 14px;
}
.label-control{
	        width:200px;
	        text-align:right;
        }
        .label-section{
        	width:173px;
        }
        .label-base{
        	width:90px;
        }
        .exampleInputName2{
        	width:300px;
        	text-align:left;
        }
        .contact-box:before, .bank-box:before {
            content: '';
            height: 3px;
            display: block;
            background-color: #3c8dbc;
            margin: -20px -15px 0 -15px;;
        }

        .file-list {
            list-style: none;
            font-family: 'FontAwesome';
            padding-left: 10px;
            padding-top: 20px;
            max-height: 239px;
            overflow: auto;
        }

        .file-list > li {
            list-style: none;
            padding: 6px 0;
            border-bottom: 1px dashed hsla(0, 0%, 90%, 1);
        }

        .file-list > li > span.file-name {
            padding-left: 5px;
        }

        .file-list > li:before {
            content: "\f0f6";
        }
    </style>
</head>
<body class="container-fluid content">

<section>
<!-- Content Header (Page header) -->
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
         <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">客户全名：</label>
            <div class="input-box-list-value">
                <input type="text" name="name" class="form-control" id="input1" value="${custBase.name }" disabled placeholder="客户名称">
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">客户常用名：</label>
            <div class="input-box-list-value">
                <input type="text" name="abbrName" class="form-control" value="${custBase.abbrName }" id="input1" placeholder="客户简称" disabled>
            </div>
        </div>
        <div class="form-group col-md-3 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">所属地区：</label>
            <div class="input-box-list-value">
                <input type="text" class="form-control" id="input1" value="${custBase.provName }${custBase.cityName }${custBase.countyName }" disabled>
            </div>
        </div>
        <!-- <div class="form-group col-md-3 input-box-list">
            <label for="input1" class=" font12 input-box-list-title">渠道类型：</label>
            <div class="input-box-list-value">
                <input type="text" class="form-control" id="input1" disabled>
            </div>
        </div> -->
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">采购经理：</label>
            <div class="input-box-list-value">
                <input type="text" name="contactName" value="${custBase.contactName }" class="form-control" id="input1" placeholder="采购经理" disabled>
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">采购联系电话：</label>
            <div class="input-box-list-value">
                <input type="text" name="contactTel" value="${custBase.contactTel }" class="form-control" id="input1" placeholder="业务电话" disabled>
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">客户编号：</label>
            <div class="input-box-list-value">
                <input type="text" name="sapCustomerId" value="${custBase.sapCustomerId }" class="form-control" id="input1" placeholder="客户编号" disabled>
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">财务经理：</label>
            <div class="input-box-list-value">
                <input type="text" name="lpName" value="${custBase.lpName }"  class="form-control" id="input1" placeholder="财务经理" disabled>
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">财务电话：</label>
            <div class="input-box-list-value">
                <input type="text" name="tel" value="${custBase.tel }" class="form-control" id="input1" placeholder="财务电话" disabled>
            </div>
        </div>
        <input type="hidden" name="custType" value="5" class="form-control">
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">客户渠道：</label>
            <div class="input-box-list-value">
                <select name="channelId" class="form-control no-appearance" value="${custBase.channelId }" id="input-agent-type" disabled>
                	<c:forEach items="${dict.get('CUSTOMER_CHANNEL') }" var="cust_type">
						<option value="${cust_type.chooseVal}"  ${custBase.channelId eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}</option>
	                </c:forEach>
                	<!-- TODO 字典字段完成后修改字段设置默认选中值 -->
                	<%-- <c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
						<option value="${cust_type.chooseVal}" ${custBase.custType eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}</option>
	                </c:forEach> --%>
                </select>
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">网点类型：</label>
            <div class="input-box-list-value">
                <select name="openingType" class="form-control no-appearance" id="input-agent-type" disabled>
                	<c:forEach items="${dict.get('CUSTOMER_WEBSITE') }" var="cust_type">
						<option value="${cust_type.chooseVal}"  ${custBase.openingType eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}</option>
	                </c:forEach>
                	<!-- TODO 字典字段完成后修改字段设置默认选中值 -->
                	<%-- <c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
						<option value="${cust_type.chooseVal}" ${custBase.custType eq cust_type.chooseVal? 'selected':''}>${cust_type.showText}</option>
	                </c:forEach> --%>
                </select>
            </div>
        </div>

        <div class="form-group col-md-8 col-xs-12 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">详细地址：</label>
            <div class="input-box-list-value">
                <input type="text" name="address" value="${custBase.address }" class="form-control" id="input1" placeholder="详细地址" disabled>
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input_businessLicense" class=" font12 input-box-list-title label-base">营业执照号：</label>
            <div class="input-box-list-value">
                <input type="text" name="businessLicense" value="${custBase.businessLicense }" class="form-control" id="input_businessLicense" placeholder="营业执照号" disabled>
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">是否开票：</label>
            <div class="input-box-list-value">
                <select name="isInvoice" class="form-control no-appearance" id="input-agent-type" disabled>
                	<c:forEach items="${dict['IS_BILLING']}" var="it">
						<option value="${it.chooseVal}" <c:if test="${custBase.isInvoice eq it.chooseVal}">selected</c:if>>${it.showText}</option>
					</c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group col-md-4 col-sm-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-base">所属配送商：</label>
            <div class="input-box-list-value">
                 <input type="text"  value="${pcust.name }" class="form-control" readonly />
            </div>
        </div>
    </div>
    <%-- block 2 --%>

    <%-- block 3 --%>
    <div class="page-header" id="block-3">
        <h4 class="text-info"><strong>3.&nbsp;</strong>开票信息&nbsp;-&nbsp;
            <small>Receipt Information.</small>
        </h4>
    </div>
    <div class="row" style="margin: 0 0 20px 0;">
        <div class="col-md-12">
            <table id="receipt-table" class="contract-box contract_box">
                <thead>
                <tr>
                    <td>开票名称</td>
                    <td>税号</td>
                    <td>注册地址</td>
                    <td>账户</td>
                    <td>电话</td>
                    <td>开户行</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                <td>${custBase.invoiceName}</td>
                <td>${custBase.invoiceTaxNum}</td>
                <td>${custBase.invoiceAddress}</td>
                <td>${custBase.invoiceAccount}</td>
                <td>${custBase.invoiceTel}</td>
                <td>${custBase.invoiceBankName}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    
    <%-- block 6 --%>
    <div class="page-header" id="block-7">
        <h4 class="text-info"><strong>7.&nbsp;</strong>附件&nbsp;-&nbsp;
            <small>Enclosure.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
        </h4>
    </div>
    <%-- <div class="row" style="margin: 0 0 20px 0;">
        <div class="col-md-4 file-list-box">
            <div class="contact-box">
                <ul class="file-list">
                	<c:forEach items="${custBase.attachments }" var="attachment" varStatus="attStatus">
                		<li>
	                        <span class="file-name" data-file-src=""><a href="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}" target="_blank" >${attachment.attachmentName }</a></span>
	                    </li>
                	</c:forEach>
                </ul>
            </div>
        </div> --%>
      <div class="demo-gallery " >
        <ul id="lightgallery" class="list-unstyled row">
           <c:forEach items="${custBase.attachments }" var="attachment" varStatus="attStatus">
           	  <c:if test="${attachment.attachmentType=='1' }">
              	<li class="col-xs-6 col-sm-4 col-md-3" data-responsive="${attachmentBASEURI}${attachment.objectName}${attachment.fileName} 375" data-src="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}" data-sub-html="<h4></h4><p>${attachment.attachmentName }</p>">
                 	<a href="#" class="thumbnail">
                    	<img class="img-rounded thumbnail-image" src="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}">
                 	</a>
              	</li>
           	  </c:if>
           	  <c:if test="${attachment.attachmentType !='1' }">
              	<li class="col-xs-6 col-sm-4 col-md-3" data-responsive="${attachmentBASEURI}${attachment.objectName}${attachment.fileName} 375" data-src="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}" data-sub-html="<h4></h4><p>${attachment.attachmentName }</p>">
                 	<a href="#" class="thumbnail">
                    	<img class="img-rounded thumbnail-image" src="static/lightGallery/img/file.jpg">
                 	</a>
              	</li>
           	  </c:if>
           </c:forEach>
       </ul>
   	</div>
   <!-- <div class="text-center" style="padding-top: 30px;border-top:1px solid rgba(0,0,0,.15);">
        <button class="btn btn-warning" style="padding: 8px 25px;" type="button" id="return"><i class="icon icon-save"></i>&nbsp;&nbsp;返回
        </button>
    </div> -->
    
</form>


<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/wizards/js/bwizard.js"></script>
<script type="text/javascript" src="static/js/customer/index.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table.js"></script>
<script type="text/javascript" src="static/table/new/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript">
    $(function () {
    	$('#lightgallery').lightGallery();
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
        $("#return").click(function(){
        	window.location.href="customer/retail.html";
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
                    title: '收货客户名称',
                    field: 'name',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle',
                    visible: true
                },{
                    title: '物流',
                    field: 'logistics',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle',
                    visible: true
                },{
                    title: '物流到站',
                    field: 'site',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle',
                    visible: true
                },{
                    title: '联系人',
                    field: 'contacter',
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