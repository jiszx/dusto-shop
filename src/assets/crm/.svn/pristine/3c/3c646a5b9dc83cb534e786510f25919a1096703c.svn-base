var $table = $('#order-table');
var dataid = 1;
var lineamt = 0;
var linenum = 0;
var linehbAmt = 0;
var linehbNum = 0;
var lineweight =0;
var linediscountAmt =0;
var lineorderAmt =0;
var customerdata;
var states='1';
var flag="0";
var editrow;
var data = [ {
	"id" : '0',
	"sku" : "合计",
	"num" : "0",
	"amt" : '0',
	"orderAmt" : '0',
	"hbNum":'0',
	"hbAmt":'0',
	"weight":'0'
} ]
var str = {
		"id" : '0',
		"sku" : "合计",
		"num" : "0",
		"amt" : '0',
		"hbNum":'0',
		"hbAmt":'0',
		"orderAmt" : '0',
		"weight":'0'
	};
$(function() {
	$('#add-bstb-row').click(function() {
		$('.add-bstb-box').addClass("add-bstb-box-open");
	});
	$('#btn-hide-bstb-add-box').click(function() {
		$('.add-bstb-box').removeClass("add-bstb-box-open");
	});
	$("#del-bstb-row").bind("click",doDelLine);
	$("#edit-bstb-row").bind("click",doEdit);
	$("#num").blur(function(){
		var num =$("#num").val();
		var hbNum =$("#hbNum").val();
		if(!num){
			num=0;
		}
		if(!hbNum){
			hbNum=0;
		}
		var orderPrice =$("#orderPrice").val();
		var amt = parseFloat(orderPrice*num);
		var hbAmt =  parseFloat(orderPrice*hbNum);
		if(validateDecimal($('#num').val()) ==false){
			$.messager.popup("请输入大于0的数字，并且小于三位小数");
			$("#num").val(0);
			$("#orderAmt").val(0);
			//return ;
		}
		if($('#unit').val() =='TO'){
			$("#weight").val(parseFloat(num)+parseFloat(hbNum));
		}else{
			$("#weight").val(((parseFloat(num)+parseFloat(hbNum))*parseFloat($('#amounts').val())*parseFloat($("#specifications").val())/1000000).toFixed(3));
		}
		
		$("#orderAmt").val(amt.toFixed(2));
		$("#amt").val((parseFloat(amt)+parseFloat(hbAmt)).toFixed(2));
	});
	$("#hbNum").blur(function(){
		var num =$("#num").val();
		if(!num){
			num=0;
		}
		var hbNum =$("#hbNum").val();
		if(!hbNum){
			hbNum=0;
		}
		var orderPrice =$("#orderPrice").val();
		var amt = parseFloat(orderPrice*num);
		var hbAmt=parseFloat(orderPrice*hbNum);
		if(validateDecimal($('#hbNum').val()) ==false){
			$.messager.popup("请输入大于0的数字，并且小于三位小数");
			$("#hbNum").val(0);
			$("#hbAmt").val(0);
			//return ;
		}
		
		$("#hbAmt").val(hbAmt.toFixed(2));
		if($('#unit').val() =='TO'){
			$("#weight").val(parseFloat(num)+parseFloat(hbNum));
		}else{
			$("#weight").val(((parseFloat(num)+parseFloat(hbNum))*parseFloat($('#amounts').val())*parseFloat($("#specifications").val())/1000000).toFixed(3));
		}
		$("#amt").val((parseFloat(amt)+parseFloat(hbAmt)).toFixed(2));
	});
	$("#editnum").blur(function(){
		var num =$("#editnum").val();
		if(!num){
			num=0;
		}
		var hbNum =$("#edithbNum").val();
		if(!hbNum){
			hbNum=0;
		}
		var orderPrice =$("#editorderPrice").val();
		if(validateDecimal($('#editnum').val()) ==false){
			$.messager.popup("请输入大于0的数字，并且小于三位小数");
			$("#editnum").val(0);
			$("#editorderAmt").val(0);
			//return ;
		}
		var hbAmt =parseFloat(orderPrice*hbNum);
		var amt = parseFloat(orderPrice*num);
		
		if($('#editunit').val() =='TO'){
			$("#editweight").val($('#num').val());
		}else{
			$("#editweight").val((parseFloat($('#editnum').val())*parseFloat($('#editamounts').val())*parseFloat($("#editspecifications").val())/1000000).toFixed(3));
		}
		$("#editorderAmt").val(amt.toFixed(2));
		$("#editamt").val((parseFloat(amt)+parseFloat(hbAmt)).toFixed(2));
	});
	$("#edithbNum").blur(function(){
		var num =$("#editnum").val();
		if(!num){
			num =0;
		}
		var hbNum =$("#edithbNum").val();
		if(!hbNum){
			hbNum=0;
		}
		var orderPrice =$("#editorderPrice").val();
		var hbAmt=parseFloat(orderPrice*hbNum);
		var amt=parseFloat(orderPrice*num);
		if(validateDecimal($('#edithbNum').val()) ==false){
			$.messager.popup("请输入大于0的数字，并且小于三位小数");
			$("#edithbNum").val(0);
			$("#edithbAmt").val(0);
			//return ;
		}
		if($('#editunit').val() =='TO'){
			$("#editweight").val(parseFloat(num)+parseFloat(hbNum));
		}else{
			$("#editweight").val(((parseFloat(num)+parseFloat(hbNum))*parseFloat($('#editamounts').val())*parseFloat($("#editspecifications").val())/1000000).toFixed(3));
		}
		$("#edithbAmt").val(hbAmt.toFixed(2));
		$("#editamt").val((parseFloat(amt)+parseFloat(hbAmt)).toFixed(2));
	});
	$('#btn-add-agent').click(function() {
		if(!$("#materialId").val()){
			$.messager.popup("请选择购买产品");
			return;
		}
		if(parseFloat($("#price").val())<=0){
			$.messager.popup("单价为0的订单不允许保存");
			return ;
		}
		if(validateDecimal($('#num').val()) ==false){
			$.messager.popup("只能输入大于等于0的数字，输入小于三位小数");
			return ;
		}
		var num =parseFloat($("#num").val());
		var orderPrice =$("#orderPrice").val();
		var amt = parseFloat(orderPrice*num);
		
		var hbNum =$("#hbNum").val();
		var hbAmt=parseFloat(orderPrice*hbNum);
		//授信余额判断
		if(amt>parseFloat($("#creditAmt").text())){
			$.messager.popup('授信不足');
			return;
		}
		if(hbAmt>parseFloat($("#subsidyAmt").text())){
			$.messager.popup('货补余额不足');
			return;
		}
		var invnum =$("#invnum").val();
		if(parseFloat(invnum)<(parseFloat(num)+parseFloat(hbNum))){
			$.messager.popup("库存数量不足");
			return ;
		}
		$("#amt").val((parseFloat(hbAmt)+parseFloat(amt)).toFixed(2));
		var agentItem = {
			id : dataid,
			sku : $('#materialName').val(),
			materialId : $("#materialId").val(),
			unit : $('#unit').val(),
			price : $('#price').val(),
			orderPrice :$("#orderPrice").val(),
			highPrice : $('#highPrice').val(),
			num : $('#num').val(),
			hbNum : $('#hbNum').val(),
			hbAmt :$("#hbAmt").val(),
			amt : $("#amt").val(),
			orderAmt : $("#orderAmt").val(),
			amounts : $("#amounts").val(),
			specifications :$("#specifications").val(),
			invnum :$("#invnum").val(),
			attribute11 :$('#materialType').val(),
			weight :$("#weight").val(),
			discountAmt:$("#hbAmt").val()
		}
		addAgent(agentItem);
		$("#materialId").val("");
		$("#materialId").trigger("chosen:updated");
		$("#creditAmt").text((parseFloat($("#creditAmt").text())-amt).toFixed(2));
		$("#subsidyAmt").text((parseFloat($("#subsidyAmt").text())-hbAmt).toFixed(2));
	});
	initTable();
	var materialdata;
	var policydata;
	// 初始化客户数据
	$('#merchCustId').chosen({
		no_results_text : "没有找到",
		placeholder_text : "请选择客户信息...",
		allow_single_de : true,
		search_contains: true,
	});
	//仓储服务商
	var url = 'order/util/customer?custType=7,70,8&all=1&type=1';
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			$('#merchCustId').html('');
			$('#merchCustId').append("<option></option>");
			customerdata=data.rows;
			for (var i = 0; i < data.rows.length; i++) {
				var sapCustomerId ='';
				if(data.rows[i].sapCustomerId !='0'){
					sapCustomerId =data.rows[i].sapCustomerId;
				}
				if(data.rows[i].custType=='7'){
					$('#merchCustId').append(
							"<option value='" + data.rows[i].id + "'>"+sapCustomerId
									+ data.rows[i].custname +"-仓储服务商"+ "</option>");
				}else if(data.rows[i].custType=='70'){
					$('#merchCustId').append(
							"<option value='" + data.rows[i].id + "'>"+sapCustomerId
									+ data.rows[i].custname +"-合作仓储服务商"+ "</option>");
				}else{
				$('#merchCustId').append(
						"<option value='" + data.rows[i].id + "'>"+sapCustomerId
						+ data.rows[i].custname +"-物流商"+ "</option>");
			}
				
			}
			$("#merchCustId").trigger("chosen:updated");
		}
	});
	$('#shipId').chosen({
		no_results_text : "没有找到",
		search_contains: true,
		allow_single_de : true
	});
	// 初始化物料选择
	$('#materialId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%" 
	});
	$("#deliveryType").change(function(){
		var deliveryType =$("#deliveryType").val();
		if(deliveryType =='2'){
			$("#freighthide").removeClass("hide");			
		}else{
			$("#freight").val('0');
			$("#freighthide").addClass("hide");	
		}
		$('#order-table').bootstrapTable("removeAll");
		lineamt = 0;
		linenum = 0;
		linehbAmt = 0;
		linehbNum = 0;
		lineweight =0;
		linediscountAmt =0;
		lineorderAmt =0;
		$('#order-table').bootstrapTable('insertRow', {
			index : 0,
			row :  {
				"id" : '0',
				"sku" : "合计",
				"num" : "0",
				"amt" : '0',
				"hbNum":'0',
				"hbAmt":'0',
				"orderAmt" : '0',
				"weight":'0'
			}
		});
		$("#materialId").val("");
		$("#materialId").trigger("chosen:updated");
		cleanLine();
	})
	$("#merchCustId").change(function() {
		var custType ;
		
		$('#order-table').bootstrapTable("removeAll");
		$("#logistics").html('');
		$("#site").html('');
		$("#contacter").html('');
		$("#mobile").html('');
		$("#address").html('');
		$("#cashAmt").html('');
		$("#subsidyAmt").html('');
		$("#creditAmt").html('');
		$("#bondAmt").html('');
		$("#allamt").html('');
		$("#shipId").empty();
		 lineamt = 0;
		 linenum = 0;
		 linehbAmt = 0;
		 linehbNum = 0;
		 lineweight =0;
		 linediscountAmt =0;
		 lineorderAmt =0;
		$("#shipId").trigger("chosen:updated");
		$('#order-table').bootstrapTable('insertRow', {
			index : 0,
			row :  {
				"id" : '0',
				"sku" : "合计",
				"num" : "0",
				"amt" : '0',
				"hbNum":'0',
				"hbAmt":'0',
				"orderAmt" : '0',
				"weight":'0'
			}
		});
		$("#materialId").val("");
		$("#materialId").trigger("chosen:updated");
		cleanLine();
		var merchid = $("#merchCustId").val();
		//获取对应的岗位ID，销售组织ID,货补比例
		for(var i=0;i<customerdata.length;i++){
			if(merchid==customerdata[i].id){
				$("#stationid").val(customerdata[i].stationid);
				$("#orgid").val(customerdata[i].organizationId);
				$("#hbratio").val(customerdata[i].hbratio);
				$("#rdcCode").val(customerdata[i].rdcCode);
				custType = customerdata[i].custType;
			}
		}
		// 获取用户的可用资金
		var accounturl = 'order/util/customerAccount?merchid='
				+ merchid+"&orgid="+$("#orgid").val();
		$.get(accounturl, function(data) {
			if (data.rows && data.rows.length > 0) {
				$("#cashAmt").html(data.rows[0].cashAmt);
				$("#subsidyAmt").html(data.rows[0].subsidyAmt);
				$("#creditAmt").html(data.rows[0].creditAmt);
				/*$("#bondAmt").html(data.rows[0].bondAmt);*/
				$("#allamt").html(
								data.rows[0].creditAmt
								+ data.rows[0].subsidyAmt
								+ data.rows[0].cashAmt);
				$(".customAccount").removeClass("hide");
			}
		});
		
		var url = 'order/util/customerShip?merchid=' + merchid+"&orgid="+$("#orgid").val();
		$('#shipId').empty();
		$("#shipId").trigger("chosen:updated");
		$.get(url, function(data) {
			if (data.rows && data.rows.length > 0) {
				shipdata=data.rows;
				$('#shipId').append("<option></option>");
				for (var i = 0; i < data.rows.length; i++) {
					$('#shipId').append(
							"<option value='" + data.rows[i].id + "'>"+ data.rows[i].address + "</option>");
				}
				$("#shipId").trigger("chosen:updated");
			}
		});

		// 获取用户的产品信息
		$("#materialId").empty();
		$("#materialId").trigger("chosen:updated");
		var materialurl;
		if(custType =='8'){
			materialurl= "order/util/custProduct?merchid=" + merchid+"&orgid="+$("#orgid").val()+"&combination=1";
		}else{			
			materialurl= "order/util/custProduct?merchid=" + merchid+"&orgid="+$("#orgid").val()+"&type=1"+"&combination=1";
		}
		$.get(materialurl, function(data) {
			if (data.rows && data.rows.length > 0) {
				materialdata = data.rows;
				$('#materialId').append("<option value=''></option>");
				for (var i = 0; i < data.rows.length; i++) {
					//if(parseFloat(data.rows[i].unitprice) >0){								
						$('#materialId').append(
								"<option value='" + data.rows[i].materialId+ "'>"+data.rows[i].materialId+'-'+ data.rows[i].sku+ "</option>");
					//}
				}
				$("#materialId").trigger("chosen:updated");
			}
		});
	})
	$("#materialId").change(function() {
		var deliveryType = $("#deliveryType").val();
		if(!deliveryType){
			$.messager.alert("请选择发货方式");
			return;
		}
		cleanLine();
		var materialid = $("#materialId").val();
		var rows = $table.bootstrapTable('getData');
		for (var i=0 ; i<rows.length;i++){
			if(rows[i].materialId == materialid){
				$.messager.popup("该物料行项目已经存在，请点击修改");
				//cleanLine();
				$("#materialId").val(" ");
				$("#materialId").trigger("chosen:updated");
			}
		}
		for (var i = 0; i < materialdata.length; i++) {
			if (materialdata[i].materialId == materialid) {
				var price =0;
				if(deliveryType =="1"){
					price = amtChange((materialdata[i].hPrice+materialdata[i].basePrice+materialdata[i].cifPrice), materialdata[i].amounts, true).toFixed(4);
				}else{
					price = amtChange((materialdata[i].hPrice+materialdata[i].basePrice), materialdata[i].amounts, true).toFixed(4);
				}
				$("#orderPrice").val(price);					
				$("#price").val(price);// 订单单价
				$("#materialName").val(materialdata[i].sku);// 物料描述
				$("#unit").val(materialdata[i].unit);// 物料基本价ID
				$("#highPrice").val(materialdata[i].hPrice);// 物料高卖价
				$("#amounts").val(materialdata[i].amounts);
				$("#specifications").val(materialdata[i].specifications);
				$("#invnum").val(amtChange(materialdata[i].invnum,materialdata[i].amounts).toFixed(3));
				$("#materialType").val(materialdata[i].materialType);
			}
		}
	});
	$("#btn-save").bind('click', doSave);
	$("#btn-save-audit").bind("click",doSaveAndAudit);
	$("#btn-edit-save").bind("click",doEditSave);
	
	
	//删除附件
    $(".file-list").on("click", "a.del-file", function () {
    	var delFileId = $(this).attr("id");
    	var fileItem = $(this).parent();
    	fileItem.remove();
    	if(typeof delFileId != 'undefined'){
    		//删除刚上传的附件
    		var fileInputEle = $("#file_"+delFileId.split("del_file_")[1]);
    		fileInputEle.remove();
    	}else{
    		//删除已存在的附件
    		var delAtts = $("#delAtts").val();
    		if(typeof delAtts != 'undefined' && $.trim(delAtts).length >0){
    			var delAttArray = delAtts.split(",");
    			delAttArray.push($(this).attr("source"));
    			$("#delAtts").val(delAttArray.join(","));
    		}else{
    			$("#delAtts").val($(this).attr("source"));
    		}
    	}
        checkFile();
    });
})
// 清空行数据
function cleanLine() {
	$('#materialName').val('');
	$('#unit').val('');
	$('#price').val('0');
	$('#highPrice').val('0');
	$('#num').val('0');
	$("#amt").val('0');
	$('#hbNum').val('0');
	$("#hbAmt").val('0');
	$("#orderAmt").val('0');
	$("#orderPrice").val('0');
	$("#amounts").val('');
	$("#specifications").val('');
	$("#invnum").val('');
	$("#materialType").val('');
	$("#weight").val('');
}
function addAgent(agent) {
	$table.bootstrapTable('insertRow', {
		index : 0,
		row : agent
	});
	dataid = dataid + 1;
	lineamt = parseFloat(lineamt) + parseFloat($("#amt").val());
	lineamt = lineamt.toFixed(2);
	
	lineorderAmt =parseFloat(lineorderAmt) + parseFloat($("#orderAmt").val());
	lineorderAmt = lineorderAmt.toFixed(2);
	
	linenum = parseFloat(linenum) + parseFloat($("#num").val());
	linenum = linenum.toFixed(3);
	
	linehbAmt = parseFloat(linehbAmt) + parseFloat($("#hbAmt").val());
	linehbAmt = linehbAmt.toFixed(2);
	
	linehbNum = parseFloat(linehbNum) + parseFloat($("#hbNum").val());
	linehbNum = linehbNum.toFixed(3);
	
	linediscountAmt =parseFloat(linediscountAmt) + parseFloat($("#hbAmt").val());
	linediscountAmt = linediscountAmt.toFixed(2);
	
	lineweight = parseFloat(lineweight)+ parseFloat($("#weight").val());
	lineweight = lineweight.toFixed(3);
	changeTotalAndamt();
	cleanLine();
}

function changeTotalAndamt() {
	var totaldata = {
		"id" : '0',
		"sku" : "合计",
		"num" : linenum,
		"amt" : lineamt,
		"hbNum":linehbNum,
		"hbAmt":linehbAmt,
		"orderAmt" : lineorderAmt,
		"weight":lineweight
	};
	$table.bootstrapTable('updateByUniqueId', {
		id : '0',
		row : totaldata
	});
}
function initTable() {
	$table.bootstrapTable({
	data : data,
	idField : 'id',
	uniqueId : 'id',
	showFooter:false,
	clickToSelect:true,
	search: false,
	showRefresh: false,
	columns : [
			{
				field : 'ck',
				radio : true,
				rowspan : 1,
				align : 'center',
				valign : 'middle'
			},
			{
				title : '编号',
				field : 'id',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				visible : false
			},
			{
				title : '产品名称',
				field : 'sku',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : false,
			},
			{
				title : '物料编码',
				field : 'materialId',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true
			},
			{
				title : '单位',
				field : 'unit',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
			},
			{
				title : '物料类型',
				field : 'attribute11',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : false,
				visible : false
			},
			{
				title : '规格',
				field : 'specifications',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
			},
			{
				title : '箱内数量',
				field : 'amounts',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
			},
			{
				title : '库存数量',
				field : 'invnum',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			},
			{
				title : '订价(元)',
				field : 'price',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			},
			{
				title : '高卖差价(元)',
				field : 'highPrice',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			},
			{
				title : '单价(元)',
				field : 'orderPrice',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true
			},
			{
				title : '调拨数量',
				field : 'num',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true
			},{
				title : '货补数量',
				field : 'hbNum',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '货补金额',
				field : 'hbAmt',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '调拨金额',
				field : 'orderAmt',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '总金额',
				field : 'amt',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true
			}, {
				title : '优惠金额',
				field : 'discountAmt',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true,
				visible : false
			},{
				title : '重量',
				field : 'weight',
				rowspan : 1,
				align : 'center',
				valign : 'middle',
				sortable : true
			}]
	});
}

function doEdit(){
	var row =$table.bootstrapTable('getSelections');
	if (row && row.length > 0 && row[0].id !="0") {
		autoEdit(row[0]);
		editrow =row;
		$("#editLineModal").modal("show");
		$("#editmaterialType").val(row[0].attribute11);
	}else {
		$.messager.popup("请选择要出合计外的行。");
	}
}
function doEditSave(){
	var num =parseFloat($("#editnum").val());//订单数量
	var hbNum = parseFloat($("#edithbNum").val());//货补数量
	if(validateDecimal($("#editnum").val()) ==false){
		$.messager.popup("调拨数量只能输入大于0数字，输入小于三位小数");
		return ;
	}
	if(validateDecimal($("#edithbNum").val()) ==false){
		$.messager.popup("货补数量只能输入大于等于0数字，输入小于三位小数");
		return ;
	}
	var orderPrice =$("#editorderPrice").val();
	var amt = parseFloat(orderPrice*num);
	var hbAmt = parseFloat(orderPrice*hbNum);
	var creditAmt = parseFloat($("#creditAmt").text());//授信余额
	$("#editamt").val((amt+hbAmt).toFixed(2));
	$("#editorderAmt").val(amt.toFixed(2));
	$("#edithbAmt").val(hbAmt.toFixed(2));
	if(amt>(parseFloat($("#creditAmt").text())+parseFloat(editrow[0].amt))){
		$.messager.popup('授信不足');
		return;
	}
	if(hbAmt>(parseFloat($("#subsidyAmt").text())+parseFloat(editrow[0].hbAmt))){
		$.messager.popup('货补余额不足');
		return;
	}
	var invnum =$("#editinvnum").val();
	if(parseFloat(invnum)<parseFloat(num+hbNum)){
		$.messager.popup("库存数量不足");
		return ;
	}
	$table.bootstrapTable('remove', {
		field : 'id',
		values :[ editrow[0].id ]
	});
	lineamt = parseFloat(lineamt) - parseFloat(editrow[0].amt);
	linenum = parseFloat(linenum) - parseFloat(editrow[0].num);
	linehbNum=parseFloat(linehbNum) - parseFloat(editrow[0].hbNum);
	linehbAmt =parseFloat(linehbAmt) - parseFloat(editrow[0].hbAmt);
	linediscountAmt==parseFloat(linediscountAmt) - parseFloat(editrow[0].hbAmt);
	lineweight =parseFloat(lineweight)-parseFloat(editrow[0].weight);
	lineorderAmt =parseFloat(lineorderAmt) + parseFloat(editrow[0].orderAmt);
	var agentItem = {
			id : dataid,
			sku : $('#editsku').val(),
			materialId : $("#editmaterialId").val(),
			unit : $('#editunit').val(),
			price : $('#editprice').val(),
			orderPrice :$("#editorderPrice").val(),
			highPrice : $('#edithighPrice').val(),
			num : $('#editnum').val(),
			amt : $("#editamt").val(),
			hbNum : $('#edithbNum').val(),
			hbAmt : $("#edithbAmt").val(),
			orderAmt : $("#editorderAmt").val(),
			amounts : $("#editamounts").val(),
			specifications :$("#editspecifications").val(),
			invnum :$("#editinvnum").val(),
			attribute11 :$("#editmaterialType").val(),
			weight :$("#editweight").val(),
			discountAmt :$("#edithbAmt").val()
		}
	$table.bootstrapTable('insertRow', {
		index : 0,
		row : agentItem
	});
	dataid = dataid + 1;
	lineamt = parseFloat(lineamt) + parseFloat($("#editamt").val());
	lineamt = lineamt.toFixed(2);
	
	lineorderAmt = parseFloat(lineorderAmt) + parseFloat($("#editorderAmt").val());
	lineorderAmt = lineorderAmt.toFixed(2);
	
	linenum = parseFloat(linenum) + parseFloat($("#editnum").val());
	linenum = linenum.toFixed(3);
	
	linehbAmt = parseFloat(linehbAmt) + parseFloat($("#edithbAmt").val());
	linehbAmt = linehbAmt.toFixed(2);
	
	linehbNum = parseFloat(linehbNum) + parseFloat($("#edithbNum").val());
	linehbNum = linehbNum.toFixed(3);
	
	linediscountAmt =parseFloat(linediscountAmt) + parseFloat($("#edithbAmt").val());
	linediscountAmt = linediscountAmt.toFixed(2);
	
	lineweight = parseFloat(lineweight) + parseFloat($("#editweight").val());
	lineweight = lineweight.toFixed(3);
	changeTotalAndamt();
	$("#creditAmt").text((parseFloat($("#creditAmt").text())+parseFloat(editrow[0].orderAmt)-amt).toFixed(2));
	$("#subsidyAmt").text((parseFloat($("#subsidyAmt").text())+parseFloat(editrow[0].hbAmt)-hbAmt).toFixed(2));
	$("#editLineModal").modal("hide");
}
function doDelLine(){
	var row =$table.bootstrapTable('getSelections');
	if (row && row.length > 0 && row[0].id !=0) {
		$table.bootstrapTable('remove', {
			field : 'id',
			values :[ row[0].id ]
		});
		$("#del-bstb-row").prop('disabled', true);
		lineamt = parseFloat(lineamt) - parseFloat(row[0].amt);
		lineamt = lineamt.toFixed(2);
		
		lineorderAmt = parseFloat(lineorderAmt)-parseFloat(row[0].orderAmt);
		lineorderAmt = lineorderAmt.toFixed(2);
		
		linenum = parseFloat(linenum) - parseFloat(row[0].num);
		linenum = linenum.toFixed(3);
		
		linehbAmt = parseFloat(linehbAmt) - parseFloat(row[0].hbAmt);
		linehbAmt = linehbAmt.toFixed(2);
		
		linehbNum = parseFloat(linehbNum) - parseFloat(row[0].hbNum);
		linehbNum = linehbNum.toFixed(3);
		
		linediscountAmt =parseFloat(linediscountAmt) - parseFloat(row[0].hbAmt);
		linediscountAmt = linediscountAmt.toFixed(2);
		
		lineweight = parseFloat(lineweight) - parseFloat(row[0].weight);
		lineweight = lineweight.toFixed(3);
		$("#creditAmt").text(parseFloat($("#creditAmt").text())+parseFloat(row[0].orderAmt));
		$("#subsidyAmt").text(parseFloat($("#subsidyAmt").text())+parseFloat(row[0].hbAmt));
		changeTotalAndamt();
	} else {
		$.messager.popup("请选择要出合计外的行。");
	}
}


// 保存销售订单
function doSave() {
	var data = $table.bootstrapTable('getData');
	var merchCustId = $("#merchCustId").val();
	var shipId = $("#shipId").val();
	var deliveryType =$("#deliveryType").val();
	var freight =$("#freight").val();
	if( !merchCustId){
		$.messager.alert("客户不能为空");
		return ;
	}
	if(!shipId){
		$.messager.alert("送达方不能为空");
		return ;
	}
	if( data.length <2){
		$.messager.alert("行不能为空");
		return ;
	}
	if(!deliveryType){
		$.messager.alert("发货方式不能为空");
		return ;
	}
	if(deliveryType=='2' && !freight){
		$.messager.alert("不包邮，请输入运费金额");
		return ;
	}
	if(deliveryType=='2' && freight){
		if (!/^0$|(^(-?)(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/.test(freight) || parseFloat(freight) <=0 ) {
			$.messager.alert("运费只能保留大于0的2位小数");
			return ;
		}
	}
	for(var i=0;i<data.length;i++){
		if(data[i].sku !='合计' && data[i].id !=0){
			data[i].num = amtChange(data[i].amounts, data[i].num, true).toFixed(3);
			data[i].hbNum = amtChange(data[i].amounts, data[i].hbNum, true).toFixed(3);
			data[i].price =amtChange(data[i].price, data[i].amounts).toFixed(4);
			data[i].orderPrice =amtChange(data[i].orderPrice, data[i].amounts).toFixed(4);
		}else{
			data.splice(i,1);
		}
	}
	var stationid = $("#stationid").val();
	var orgid = $("#orgid").val();
	var jsondata = {
		"merchCustId" : merchCustId,
		"shipId" : shipId,
		"stationid" : stationid,
		"lines" : data
	};
	$("#btn-save").attr("disabled","true");
	var url = "order/addOrder.json";
	
	$.post("order/addOrder", {
		"states":states,
		"merchCustId" : merchCustId,
		"stationid" : stationid,
		"shipId":shipId,
		"orgid" : orgid,
		"orderType":"5",
		"hbamt":linehbAmt,
		"remark":$("#remark").val(),
		"lines" : JSON.stringify(data),
		"stationid":$("#stationid").val(),
		"freight":freight,
		"deliveryType":deliveryType
	}, function(res) {
		var result =  eval("("+res.data+")");  
		if (result.type == "500") {
			$("#btn-save").removeAttr("disabled");
			$.messager.popup("保存订单出错!"+result.code);
		}else if(result.type=='200'){
			$('#headerId').val(result.id);
			$('#orderFrom').ajaxSubmit(function(){
				if(flag=="1"){
					$.post("customerInvAllocation/doAudit", {"headerid":result.id,"states":"2"}, function(res){
						if(res.data=="200"){
							window.location.href="customerInvAllocation/InvAllocationList.html";
						}else{
							$.messager.alert("提示","保存成功，提交审批失败");
							window.location.href="customerInvAllocation/InvAllocationList.html";
						}
					});
				}else{				
					$.messager.popup("保存订单成功!");
					window.location.href="customerInvAllocation/InvAllocationList.html";
				}
			});
		}
	});
}

function validateDecimal(value){
	var ir = /^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,3})?$)/;
	return ir.test(value);
}

function doSaveAndAudit(){
	flag='1';
	doSave();
}

function amtChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}

/**
 * 添加待上传的文件
 */
function addFiles() {
	var newFileId = '' + new Date().getTime() + Math.floor(Math.random() * 10);
	var fileBox = $("#file-select");
	var fileBoxNew = fileBox.clone();
	fileBox.after(fileBoxNew);
	var file = fileBox.prop('files')[0];
	var filePath = fileBox.val()
	var fileName = file.name;
	//upfiles = upfiles.push(0, filePath);
	fileBox.attr("id", "file_" + newFileId).removeAttr("onchange").attr("name",
			"files");
	fileBox.hide();
	if (file) {
		var item = '<li>'
				+ '<span class="file-name" data-file-src="'
				+ filePath
				+ '">'
				+ fileName
				+ '</span>'
				+ '<a href="javascript:;" class="pull-right del-file" id="del_file_'
				+ newFileId
				+ '" title="删除附件"><i class="icon icon-remove"></i></a>'
				+ $('<div>').append().html() + '</li>';
		$(".file-list").append(item);

	} else {
		$.messager.alert('文件读取错误。');
	}
	checkFile();
}
function checkFile() {
	var fileList = $(".file-list>li");
	if (fileList.length > 0) {
		$(".file-list-box").fadeIn();
	} else {
		$(".file-list-box").fadeOut();
	}
}