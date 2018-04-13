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
    <title>客户基本信息</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <jsp:include page="/common/lightGallery.jsp"></jsp:include>
    <link rel="stylesheet" href="static/wizards/css/bwizard.min.css">
    <link rel="stylesheet" href="static/table/new/bootstrap-table.css">
    <link rel="stylesheet" href="static/css/pub.css">
</head>
<body class="container-fluid content">

<section class="">
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
                <th class="basetitle">客户名称</th>
                <td>${custBase.name }</td>
                <th class="basetitle">客户简称</th>
                <td>${custBase.abbrName }</td>
            </tr>
            <tr>
                <th class="basetitle">所属地区</th>
                <td>${custBase.provName }${custBase.cityName }${custBase.countyName }</td>
                <th class="basetitle">法人名称</th>
                <td>${custBase.lpName }</td>
            </tr>
            <tr>
                <th class="basetitle">法人身份证号</th>
                <td>${custBase.lpNo }</td>
                <th class="basetitle">客户业务联系人</th>
                <td>${custBase.contactName }</td>
            </tr>
            <tr>
                <th class="basetitle">业务联系电话</th>
                <td>${custBase.contactTel }</td>
                <th class="basetitle">营业执照号</th>
                <td>${custBase.businessLicense }</td>
            </tr>
            <tr>
                <th class="basetitle">公司电话</th>
                <td>${custBase.tel }</td>
                <th class="basetitle">客户类型</th>
                <td> <c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
                    <c:if test="${custBase.custType eq cust_type.chooseVal}">
                        ${cust_type.showText}
                    </c:if>
                </c:forEach></td>
            </tr>
            <tr>
                <th class="basetitle">公司地址</th>
                <td>${custBase.address }</td>
                <th class="basetitle">是否开票</th>
                <td><c:forEach items="${dict['IS_BILLING']}" var="it">
                   <c:if test="${custBase.isInvoice eq it.chooseVal}">
                       ${it.showText}
                   </c:if>
                </c:forEach></td>
            </tr>
            <%-- <tr>
                <th class="basetitle">最小起订量</th>
                <td>${custBase.minOrder }吨</td>
            </tr> --%>
            </tbody>
        </table> 
    </div>
    <%-- block 2 --%>
    <div class="page-header" id="block-2">
        <h4 class="text-info"><strong>2.&nbsp;</strong>到站信息&nbsp;-&nbsp;
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

    <%-- block 3 --%>
    <div class="page-header" id="block-3">
        <h4 class="text-info"><strong>3.&nbsp;</strong>开票信息&nbsp;-&nbsp;
            <small>Receipt Information.</small>
        </h4>
    </div>
    <div class="row" style="margin: 0 0 20px 0;">
        <div class="col-md-12">
            <table id="receipt-table" class="table table-bordered info-table">
                <tr>
                    <th>开票名称</th>
                    <td>${custBase.invoiceName}</td>
                    <th>税号</th>
                    <td>${custBase.invoiceTaxNum}</td>
                </tr>
                <tr>
                    <th>地址</th>
                    <td>${custBase.invoiceAddress}</td>
                    <th>账户</th>
                    <td>${custBase.invoiceAccount}</td>
                </tr>
                <tr>
                    <th>电话</th>
                    <td>${custBase.invoiceTel}</td>
                    <th>常用打款银行名称</th>
                    <td>${custBase.invoiceBankName}</td>
                </tr>
                <tr>
                    <th>常用打款账户名称</th>
                    <td>${custBase.invoiceAccountName}</td>
                </tr>
            </table>
        </div>
    </div>
    <%-- block 4
    <div class="page-header">
        <h4 class="text-info"><strong>4.&nbsp;</strong>新开类型&nbsp;-&nbsp;
            <small>Receiving Information.</small>
        </h4>
    </div>
    <div class="row" style="margin: 0 0 20px 0;">
        <div class="col-md-12">
            <table  class="contract-box contract_box">
                <thead>
                <tr>
                    <td rowspan="2" class="title">
                    	<label>
                    	${custBase.openingType eq '1'?'新开':'' }
                    	${custBase.openingType eq '2'?'替代':'' }
                    	${custBase.openingType eq '3'?'升级':'' }
                    	</label>
                    </td>
                    <td>原上级经销商名称</td>
                    <td>与原上级经销商停止合作日期</td>
                    <td>${custBase.openingType eq '1'?'新开':''}
                    	${custBase.openingType eq '2'?'替代':''}
                    	${custBase.openingType eq '3'?'升级':''}原因</td>
                </tr>
                </thead>
                <tbody>
                <tr>
                <td style="border-left:none;"></td>
                <td>${custBase.openingMerchant }</td>
                <td><fmt:formatDate value="${custBase.openingCloseTs }" pattern="yyyyMMdd"/></td>
                <td>${custBase.openingReason }</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
     --%>
    <%-- block 5
    <div class="page-header" id="block-5">
        <h4 class="text-info"><strong>5.&nbsp;</strong>我司产品拓展计划&nbsp;-&nbsp;
            <small>Receiving Information.</small>
        </h4>
    </div>
    <div class="row form-horizontal" style="margin: 0 0 20px 0;">
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">计划拓展区域：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="planArea" value="${custBase.planArea }" class="form-control" id="input1"
                       >
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">计划拓展品牌：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="planBrand" value="${custBase.planBrand }" class="form-control" id="input1"
                       >
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">计划拓展品类：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="planCategory" value="${custBase.planCategory }" class="form-control" id="input1" >
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">KA渠道：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandKaPlace" value="${custBase.expandKaPlace }" class="form-control" id="input1" >
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">BC渠道：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandBcPlace" value="${custBase.expandBcPlace }" class="form-control" id="input1" placeholder="">
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">流通（农贸/批发）渠道：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandCirculatePlace" value="${custBase.expandCirculatePlace }" class="form-control" id="input1" placeholder="">
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">工厂：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandFactoryPlace" value="${custBase.expandFactoryPlace }" class="form-control" id="input1" placeholder="">
            </div>
        </div>
        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">学校渠道：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandSchoolPlace" value="${custBase.expandSchoolPlace }" class="form-control" id="input1" placeholder="">
            </div>
        </div>

        <div class="form-group col-md-4  col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">零售网点数（中型以下门店）：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandRetailPlace" value="${custBase.expandRetailPlace }" class="form-control" id="input1" placeholder="">
            </div>
        </div>
        <div class="form-group col-md-4  col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">该区域人口数：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandAreaPeoples" value="${custBase.expandAreaPeoples }" class="form-control" id="input1" placeholder="">
            </div>
        </div>

        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">该区域市场体量：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandAreaVolume" value="${custBase.expandAreaVolume }" class="form-control" id="input1" placeholder="">
            </div>
        </div>

        <div class="form-group col-md-4 col-xs-6 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section">计划生意体量：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandBusinesVolume" value="${custBase.expandBusinesVolume }" class="form-control" id="input1" placeholder="">
            </div>
        </div>

        <div class="form-group col-md-4 col-xs-12 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section" style="width:233px;">该客户将投入到我司产品的每月运营金额：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandSpentMamt" value="${custBase.expandSpentMamt }" class="form-control" id="input1" placeholder="">
            </div>
        </div>

        <div class="form-group col-md-4 col-xs-12 input-box-list">
            <label for="input1" class=" font12 input-box-list-title label-section" style="width:210px">该客户将投入到我司产品的首单金额：</label>
            <div class="input-box-list-value">
                <input type="text" disabled name="expandSpentFamt" value="${custBase.expandSpentFamt }" class="form-control" id="input1" placeholder="">
            </div>
        </div>

    </div>
    --%>
    <%-- block 6 --%>
    <div class="page-header" id="block-7">
        <h4 class="text-info"><strong>5.&nbsp;</strong>附件&nbsp;-&nbsp;
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
    <!-- <div class="page-header" id="block-5">
        <h4 class="text-info"><strong>5.&nbsp;</strong>审批意见&nbsp;-&nbsp;
            <small>Approval Opinion.</small>
        </h4>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="form-group" style="padding:0 10px;">
                <textarea id="yourOpinion" class="form-control" rows="5" placeholder="请输入审批意见..."></textarea>
            </div>
        </div>
    </div> -->
    <%-- submit button --%>
    <!-- <div class="text-center" style="padding-top: 10px;">
        <button class="btn btn-danger" style="padding: 8px 25px;" type="button"><i class="icon icon-reply"></i>&nbsp;&nbsp;驳回
        </button>
        <button class="btn btn-primary btn-long" type="submit"><i class="icon icon-check"></i>&nbsp;&nbsp;通过</button>
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
                },{
                    title: '联系电话',
                    field: 'mobile',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle',
                    visible: true
                },{
                    title: '收货地址',
                    field: 'address',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle',
                    visible: true,
                    formatter : function(value,data){
                    	return fuv(data.provName)+fuv(data.cityName)+fuv(data.countyName)+value;
                    }
                }
            ]
        });
        }
    });
    
    function fuv(val){
    	if(typeof val == 'undefined'){
    		return '';
    	}else{
    		return val;
    	}
    }
</script>
</body>
</html>