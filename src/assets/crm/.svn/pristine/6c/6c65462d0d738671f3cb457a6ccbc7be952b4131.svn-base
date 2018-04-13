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
    <title>客户详情</title>
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
                    <th>客户名称</th>
                    <td>${custBase.name }</td>
                    <th>客户简称</th>
                    <td>${custBase.abbrName }</td>
                </tr>
                <tr>
                    <th>所属地区</th>
                    <td>${custBase.provName }${custBase.cityName }${custBase.countyName }</td>
                    <th>法人名称</td>
                    <td>${custBase.lpName }</td>
                </tr>
                <tr>
                    <th>法人身份证号</th>
                    <td>${custBase.lpNo }</td>
                    <th>客户联系人</th>
                    <td>${custBase.contactName }</td>
                </tr>
                <tr>
                    <th>业务电话</th>
                    <td>${custBase.contactTel }</td>
                    <th>营业执照号</th>
                    <td>${custBase.businessLicense }</td>
                </tr>
                <tr>
                    <th>客户电话</th>
                    <td>${custBase.tel }</td>
                    <th>客户类型</th>
                    <td><c:forEach items="${dict.get('CUST_MERCH_TYPE') }" var="cust_type">
                        <c:if test="${custBase.custType eq cust_type.chooseVal}">
                            ${cust_type.showText}
                        </c:if>
                    </c:forEach></td>
                </tr>
                <tr>
                    <th>详细地址</th>
                    <td>${custBase.address }</td>
                    <th>是否开票</th>
                    <td>
                        <c:forEach items="${dict['IS_BILLING']}" var="it">
                            <c:if test="${custBase.isInvoice eq it.chooseVal}">
                                ${it.showText}
                            </c:if>
                        </c:forEach></td>
                </tr>
               <%--  <tr>
                    <td>最小起订量</td>
                    <td>${custBase.minOrder }吨</td>
                </tr> --%>
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
                    <td>地址</td>
                    <td>账户</td>
                    <td>电话</td>
                    <td>常用打款银行名称</td>
                    <td>常用打款账户名称</td>
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
                <td>${custBase.invoiceAccountName}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <%-- block 4 --%>
    <%-- block 6 --%>
    <div class="page-header" id="block-7">
        <h4 class="text-info"><strong>4.&nbsp;</strong>附件&nbsp;-&nbsp;
            <small>Enclosure.</small>
            <!-- <a href="javascript:;" class=" text-info block-save-link"><i class="icon icon-save"></i>&nbsp;&nbsp;保存</a> -->
        </h4>
    </div>
   <%--  <div class="row" style="margin: 0 0 20px 0;">
        <div class="col-md-4 file-list-box">
            <div class="contact-box">
                <ul class="file-list">
                	<c:forEach items="${custBase.attachments }" var="attachment" varStatus="attStatus">
                		<li>
	                        <span class="file-name" data-file-src=""><a href="${attachmentBASEURI}${attachment.objectName}${attachment.fileName}" target="_blank" >${attachment.attachmentName }</a></span>
	                        <a href="javascript:;" class="pull-right del-file" source="${attachment.id }" title="删除附件"><i class="icon icon-remove"></i></a>
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