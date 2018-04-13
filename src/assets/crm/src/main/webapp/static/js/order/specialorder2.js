var $table = $('#order-table');
var dataid = 1;
var lineamt = 0;
var linehNum = 0;
var linehbamt = 0;
var lineorderamt = 0;
var linediscountamt = 0;
var linenum = 0;
var customerdata;
var states='1';
var flag='0';
var editrow;
var shipdata;
var data = [ {
	"id" : '0',
	"sku" : "合计",
	"num" : "0",
	"hbNum" : '0',
	"amt" : '0',
	"discountAmt" : '0',
	"orderAmt" : '0'
} ];
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
		numchangeamt();
	});
	$("#hbNum").blur(function(){
		numchangeamt();
	});
	$("#editnum").blur(function(){
		editnumchangeamt();
	});
	$("#edithbNum").blur(function(){
		editnumchangeamt();
	});
	$("#btn-add-agent").bind("click",function(){
		setTimeout(btnaddagent(),300);
	});
	
	initTable();
	var materialdata;
	var policydata;
	// 初始化客户数据
	$('#merchCustId').chosen({
		no_results_text: "没有找到.",
		placeholder_text : "请选择客户信息...",
		allow_single_de : true,
		search_contains: true,
	});
	var url = 'order/util/customer?type=1&custType=9';
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			$('#merchCustId').html();
			$('#merchCustId').append("<option></option>");
			customerdata=data.rows;
			for (var i = 0; i < data.rows.length; i++) {
				$('#merchCustId').append(
						"<option value='" + data.rows[i].id + "'>"
								+ data.rows[i].custname + "</option>");
			}
			$("#merchCustId").trigger("chosen:updated");
			$('#merchCustId').chosen({
				no_results_text : "没有找到",
				allow_single_de : true
			});
		}
	});

	// 初始化送达方
	$('#shipId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true
	});
	// 初始化物料选择
	$('#material').chosen({
		no_results_text : "没有找到",
		placeholder_text : "请选择物料信息...",
		allow_single_de : true,
		search_contains: true,
		//disable_search_threshold: 10,
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
		linehNum = 0;
		linehbamt = 0;
		lineorderamt = 0;
		linediscountamt = 0;
		linenum = 0;
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
		$("#material").val("");
		$("#material").trigger("chosen:updated");
		cleanLine();
	})
	$("#merchCustId").change(
			function() {
				$('#order-table').bootstrapTable("removeAll");
				$("#cashAmt").html('');
				$("#subsidyAmt").html('');
				$("#creditAmt").html('');
				$("#bondAmt").html('');
				$("#allamt").html('');
				$("#shipId").empty();
				lineamt = 0;
				linehNum = 0;
				linehbamt = 0;
				lineorderamt = 0;
				linediscountamt = 0;
				linenum = 0;
				/*$('#order-table').bootstrapTable("load",data);*/
				$('#order-table').bootstrapTable('insertRow', {
					index : 0,
					row : {
						"id" : '0',
						"sku" : "合计",
						"num" : "0",
						"hbNum" : '0',
						"amt" : '0',
						"discountAmt" : '0',
						"orderAmt" : '0'
					}
				});
				cleanLine();
				$("#shipId").trigger("chosen:updated");
				$('#shipId').chosen({
					no_results_text : "没有找到",
					allow_single_de : true
				});
				var merchid = $("#merchCustId").val();
				//获取对应的岗位ID，销售组织ID,货补比例
				for(var i=0;i<customerdata.length;i++){
					if(merchid==customerdata[i].id){
						$("#stationid").val(customerdata[i].stationid);
						$("#orgid").val(customerdata[i].organizationId);
						$("#hbratio").val(customerdata[i].hbratio);
					}
				}
				var url = 'order/util/customerShip2?merchid=' + merchid+"&orgid="+$("#orgid").val();
				$('#shipId').html();
				$.get(url, function(data) {
					if (data.rows && data.rows.length > 0) {
						shipdata=data.rows;
						$('#shipId').append("<option></option>");
						for (var i = 0; i < data.rows.length; i++) {
							$('#shipId').append(
									"<option value='" + data.rows[i].id + "'>"+data.rows[i].sapCustomerId +"-"
											+ data.rows[i].name + "</option>");
						}
						$("#shipId").trigger("chosen:updated");
						$('#shipId').chosen({
							no_results_text : "没有找到",
							allow_single_de : true
						});
					}
				});
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
										(data.rows[0].creditAmt
										+ data.rows[0].subsidyAmt
										+ data.rows[0].cashAmt).toFixed(2));
						$(".customAccount").removeClass("hide");
					}
				});

				// 获取用户的产品信息
				$("#material").empty();
				var materialurl = "order/util/custProduct?merchid=" + merchid+"&orgid="+$("#orgid").val();
				$.get(materialurl, function(data) {
					if (data.rows && data.rows.length > 0) {
						materialdata = data.rows;
						$("#material").append("<option value=''></option>");
						for (var i = 0; i < data.rows.length; i++) {
							//if(parseFloat(data.rows[i].unitprice) >0){		
								$('#material').append("<option value='" + data.rows[i].materialId+ "'>" + data.rows[i].materialId+'-'+ data.rows[i].sku+ "</option>");
							//}
						}
						$("#material").trigger("chosen:updated");
					}
				});
			})
	$("#material").change(function() {
		var deliveryType = $("#deliveryType").val();
		if(!deliveryType){
			$.messager.alert("请选择发货方式");
			return;
		}
		cleanLine();
		var materialid = $("#material").val();
		var rows = $table.bootstrapTable('getData');
		for (var i=0 ; i<rows.length;i++){
			if(rows[i].materialId == materialid){
				$.messager.popup("该物料行项目已经存在，请点击修改");
				cleanLine();
				$("#materialId").val("");
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
				$("#materialName").val(materialdata[i].sku);// 物料描述
				$("#price").val(price);// 订单单价
				$("#orderPrice").val(price);// 订单单价
				$("#unit").val(materialdata[i].unit);// 物料基本价ID
				$("#highPrice").val(materialdata[i].hPrice.toFixed(4));// 物料高卖价
				$("#amounts").val(materialdata[i].amounts);
				$("#specifications").val(materialdata[i].specifications);
			}
		}
	});
	$("#btn-save").bind('click', doSave);
	$("#btn-save-audit").bind("click",doSaveAndAudit);
	$("#btn-edit-save").bind("click",doEditSave);
})

function btnaddagent(){
	if(!$("#material").val()){
		$.messager.popup("请选择购买产品");
		return;
	}
	if($("#amt").val()=='0'){
		numchangeamt();
	}
	if(parseFloat($("#price").val())<=0 || !$("#price").val()){
		$.messager.popup("单价为0的订单不允许保持");
		return ;
	}
	if(validateNum($("#num").val()) ==false){
		$.messager.alert("提示", "数量只能输入大于0,并且小于三位小数");
		$("#num").val('0');
		$("#orderAmt").val('0');
		$("#amt").val($("#hbAmt").val());
		$("#discountAmt").val($("#hbAmt").val());
		//return ;
	}
	if(validateNum($("#hbNum").val()) ==false){
		$.messager.alert("提示", "数量只能输入大于0的,并且小于三位小数");
		$("#hbAmt").val('0');
		$("#hbNum").val('0');
		$("#discountAmt").val("0");
		$("#amt").val($("#orderAmt").val());
		//return ;
	}
	if($('#num').val()==0 && $("#hbNum").val()==0){
		$.messager.popup("购买数量和货补数量为0，请填写购买数量");
		return ;
	}
	var agentItem = {
		id : dataid,
		sku : $('#materialName').val(),
		materialId : $("#material").val(),
		unit : $('#unit').val(),
		price : $('#price').val(),
		orderPrice :$("#orderPrice").val(),
		highPrice : $('#highPrice').val(),
		num : $('#num').val(),
		hbNum : $("#hbNum").val(),
		amt : $("#amt").val(),
		hbAmt : $("#hbAmt").val(),
		discountAmt : $("#discountAmt").val(),
		orderAmt : $("#orderAmt").val(),
		amounts :$("#amounts").val(),
		specifications :$("#specifications").val()
	}
	addAgent(agentItem);
	$("#material").val("");
	$("#material").trigger("chosen:updated");
}
// 清空行数据
function cleanLine() {
	$('#materialName').val('');
	$('#unit').val('');
	$('#price').val('0');
	$('#highPrice').val('0');
	$('#num').val('0');
	$("#hbNum").val('0');
	$("#hbAmt").val('0');
	$("#amt").val('0');
	$("#discountAmt").val('0');
	$("#orderAmt").val('0');
	$("#orderPrice").val('0');
	$("#specifications").val('');
	$("#amounts").val('');
}
function addAgent(agent) {
	$table.bootstrapTable('insertRow', {
		index : 0,
		row : agent
	});
	dataid = dataid + 1;
	lineamt = parseFloat(lineamt) + parseFloat($("#amt").val());
	lineamt = lineamt.toFixed(2);
	
	linenum = parseFloat(linenum) + parseFloat($("#num").val());
	linenum = linenum.toFixed(3);
	
	linediscountamt = parseFloat(linediscountamt)
			+ parseFloat($("#discountAmt").val());
	linediscountamt = linediscountamt.toFixed(2);
	
	linehNum = parseFloat(linehNum) + parseFloat($("#hbNum").val());
	linehNum = linehNum.toFixed(3);
	
	lineorderamt = parseFloat(lineorderamt) + parseFloat($("#orderAmt").val());
	lineorderamt = lineorderamt.toFixed(2);
	
	linehbamt = parseFloat(linehbamt)+parseFloat($("#hbAmt").val());
	linehbamt = linehbamt.toFixed(2);
	changeTotalAndamt();
	cleanLine();
}

function changeTotalAndamt() {
	var totaldata = {
		"id" : '0',
		"sku" : "合计",
		"num" : linenum,
		"hbNum" : linehNum,
		"amt" : lineamt,
		"orderAmt" : lineorderamt,
		"hbAmt" : linehbamt,
		"discountAmt" : linediscountamt
	};
	$table.bootstrapTable('updateByUniqueId', {
		id : '0',
		row : totaldata
	});
	var cashAmt = $("#cashAmt").text();
	var subsidyAmt = $("#subsidyAmt").text();
	var creditAmt = $("#creditAmt").text();
	if (parseFloat(lineorderamt) < parseFloat(cashAmt)) {
		$("#XJamt").html(lineorderamt);
	} else {
		$("#XJamt").html(cashAmt);
		$("#SXamt").html((lineorderamt - cashAmt).toFixed(2));
	}
	$("#HBamt").html(linehbamt);
}

function numchangeamt(){
	var hbnum =parseFloat($("#hbNum").val());//货补数量
	var num =parseFloat($("#num").val());//订单数量
	if(!num){
		$("#num").val('0');
		num =0;
	}
	if(!hbnum){
		$("#hbNum").val('0');
		hbnum=0;
	}
	var orderprice =parseFloat($("#orderPrice").val());//订单单价
	var amt = parseFloat(orderprice*num);
	var hbamt = parseFloat(orderprice*hbnum);
	
	var cashAmt = parseFloat($("#cashAmt").text());//现金余额
	var creditAmt = parseFloat($("#creditAmt").text());//授信余额
	var XJamt = parseFloat($("#XJamt").html());//应扣现金金额
	var SXamt = parseFloat($("#SXamt").html());//应扣授信金额
	if ((XJamt + SXamt + parseFloat(orderprice * num)) > (cashAmt + creditAmt)) {
		//如果应扣现金金额和应扣授信金额加上当前订单金额超过现金和授信账户余额则提示余额不足
		$.messager.popup("可用余额不足!");
		$("#num").val('0');
		num =0;
	}
	
	var HBamt = parseFloat($("#HBamt").html());//应扣货补金额
	var subsidyAmt = parseFloat($("#subsidyAmt").text());//货补账户金额
	if ((HBamt + parseFloat(hbnum * orderprice)) > subsidyAmt) {
		$.messager.popup("货补账户可用余额不足!");
		$("#hbNum").val('0');
		hbnum=0;
	}
	//订单金额
	$("#hbAmt").val((parseFloat(orderprice*hbnum)).toFixed(2));
	$("#orderAmt").val((parseFloat(orderprice*num)).toFixed(2));
	$("#amt").val((parseFloat(orderprice*num)+parseFloat(orderprice*hbnum)).toFixed(2));
	$("#discountAmt").val((parseFloat(orderprice*hbnum)).toFixed(2));
}
function editnumchangeamt(){
	var hbnum =parseFloat($("#edithbNum").val());//货补数量
	var num =parseFloat($("#editnum").val());//订单数量
	if(!num){
		$("#editnum").val('0');
		num =0;
	}
	if(!hbnum){
		$("#edithbNum").val('0');
		hbnum=0;
	}
	var orderprice =parseFloat($("#editorderPrice").val());//订单单价
	var amt = parseFloat(orderprice*num);
	var hbamt = parseFloat(orderprice*hbnum);
	var cashAmt = parseFloat($("#cashAmt").text());//现金余额
	var creditAmt = parseFloat($("#creditAmt").text());//授信余额
	var XJamt = parseFloat($("#XJamt").html());//应扣现金金额
	var SXamt = parseFloat($("#SXamt").html());//应扣授信金额
	if ((XJamt + SXamt -parseFloat(orderprice * editrow[0].num)+ parseFloat(orderprice * num)) > (cashAmt + creditAmt)) {
		//如果应扣现金金额和应扣授信金额加上当前订单金额超过现金和授信账户余额则提示余额不足
		$.messager.popup("可用余额不足!");
		$("#editnum").val(editrow[0].num);
		num =0;
	}
	
	var HBamt = parseFloat($("#HBamt").html());//应扣货补金额
	var subsidyAmt = parseFloat($("#subsidyAmt").text());//货补账户金额
	if ((HBamt-parseFloat(editrow[0].hbNum)*orderprice + parseFloat(hbnum * orderprice)) > subsidyAmt) {
		$.messager.popup("货补账户可用余额不足!");
		$("#edithbNum").val(editrow[0].hbNum);
		hbnum=0;
	}
	//订单金额
	$("#edithbAmt").val((parseFloat(orderprice*hbnum)).toFixed(2));
	$("#editorderAmt").val((parseFloat(orderprice*num)).toFixed(2));
	$("#editamt").val((parseFloat(orderprice*num)+parseFloat(orderprice*hbnum)).toFixed(2));
	$("#editdiscountAmt").val((parseFloat($("#editamt").val())-parseFloat($("#editorderAmt").val())).toFixed(2));
}

function initTable() {
	$table
			.bootstrapTable({
				// url : 'static/table/new/data.json',
				data : data,
				idField : 'id',
				uniqueId : 'id',
				showFooter:false,
				clickToSelect:true,
				showColumns:true,
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
							sortable : false
						},
						{
							title : '规格',
							field : 'specifications',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : false
						},
						{
							title : '箱内数量',
							field : 'amounts',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : false
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
							title : '物料订价(元)',
							field : 'price',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true,
							visible : false
						},
						{
							title : '高卖价(元)',
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
							title : '下单数量',
							field : 'num',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true
						},
						{
							title : '货补数量',
							field : 'hbNum',
							rowspan : 1,
							align : 'center',
							valign : 'middle'
						}, {
							title : '货补金额',
							field : 'hbAmt',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							visible : false
						}, {
							title : '应收金额',
							field : 'amt',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true
						}, {
							title : '折扣金额',
							field : 'discountAmt',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true
						}, {
							title : '实收金额',
							field : 'orderAmt',
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
		$("#editnum2").val(row[0].num);
		$("#editLineModal").modal("show");
	}else {
		$.messager.alert("请选择要出合计外的行。");
	}
}
function doEditSave(){
	if(validateNum($("#editnum").val()) ==false){
		$.messager.alert("提示", "数量只能输入大于0的数字，输入小于三位小数");
		$("#editnum").val('0');
		$("#editorderAmt").val('0');
		$("#editamt").val($("#edithbAmt").val());
		$("#editdiscountAmt").val($("#edithbAmt").val());
		//return ;
	}
	if(validateNum($("#edithbNum").val()) ==false){
		$.messager.alert("提示", "数量只能输入大于0数字，输入小于三位小数");
		$("#edithbAmt").val('0');
		$("#edithbNum").val('0');
		$("#editdiscountAmt").val("0");
		$("#editamt").val($("#editorderAmt").val());
		//return ;
	}
	var hbnum =parseFloat($("#edithbNum").val());//货补数量
	var num =parseFloat($("#editnum").val());//订单数量
	if(hbnum==0 && num ==0){
		$.messager.alert("提示", "两个数量不可以同时为0");
		return ;
	}
	$table.bootstrapTable('remove', {
		field : 'id',
		values :[ editrow[0].id ]
	});
	lineamt = parseFloat(lineamt) - parseFloat(editrow[0].amt);
	lineamt = lineamt.toFixed(2);
	
	linenum = parseFloat(linenum) - parseFloat(editrow[0].num);
	linenum = linenum.toFixed(3);
	
	linediscountamt = parseFloat(linediscountamt)
			- parseFloat(editrow[0].discountAmt);
	linediscountamt = linediscountamt.toFixed(2);
	
	linehNum = parseFloat(linehNum) - parseFloat(editrow[0].hbNum);
	linehNum = linehNum.toFixed(3);
	
	lineorderamt = parseFloat(lineorderamt) - parseFloat(editrow[0].orderAmt);
	lineorderamt = lineorderamt.toFixed(2);
	
	linehbamt = parseFloat(linehbamt) - parseFloat(editrow[0].hbAmt);
	linehbamt = linehbamt.toFixed(2);
	
	changeTotalAndamt();
	var agentItem = {
			id : dataid,
			sku : $('#editsku').val(),
			materialId : $("#editmaterialId").val(),
			unit : $('#editunit').val(),
			price : $('#editprice').val(),
			orderPrice :$("#editorderPrice").val(),
			highPrice : $('#edithPrice').val(),
			num : $('#editnum').val(),
			hbNum : $("#edithbNum").val(),
			amt : $("#editamt").val(),
			hbAmt : $("#edithbAmt").val(),
			discountAmt : $("#editdiscountAmt").val(),
			orderAmt : $("#editorderAmt").val(),
			amounts :$("#editamounts").val(),
			specifications :$("#editspecifications").val()
		}
	$table.bootstrapTable('insertRow', {
		index : 0,
		row : agentItem
	});
	dataid = dataid + 1;
	lineamt = parseFloat(lineamt) + parseFloat($("#editamt").val());
	lineamt = lineamt.toFixed(2);
	
	linenum = parseFloat(linenum) + parseFloat($("#editnum").val());
	linenum = linenum.toFixed(3);
	
	linediscountamt = parseFloat(linediscountamt)
			+ parseFloat($("#editdiscountAmt").val());
	linediscountamt = linediscountamt.toFixed(2);
	
	linehNum = parseFloat(linehNum) + parseFloat($("#edithbNum").val());
	linehNum = linehNum.toFixed(3);
	
	lineorderamt = parseFloat(lineorderamt) + parseFloat($("#editorderAmt").val());
	lineorderamt = lineorderamt.toFixed(2);
	
	linehbamt = parseFloat(linehbamt)+parseFloat($("#edithbAmt").val());
	linehbamt = linehbamt.toFixed(2);
	changeTotalAndamt();
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
		
		linenum = parseFloat(linenum) - parseFloat(row[0].num);
		linenum = linenum.toFixed(3);
		
		linediscountamt = parseFloat(linediscountamt)
				- parseFloat(row[0].discountAmt);
		linediscountamt = linediscountamt.toFixed(2);
		
		linehNum = parseFloat(linehNum) - parseFloat(row[0].hbNum);
		linehNum = linehNum.toFixed(3);
		
		lineorderamt = parseFloat(lineorderamt) - parseFloat(row[0].orderAmt);
		lineorderamt = lineorderamt.toFixed(2);
		
		linehbamt = parseFloat(linehbamt) - parseFloat(row[0].hbAmt);
		linehbamt = linehbamt.toFixed(2);
		changeTotalAndamt();
	} else {
		$.messager.alert("请选择要出合计外的行。");
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
		$.messager.alert("提示", "客户不能为空");
		return ;
	}
	if(!shipId){
		$.messager.alert("提示", "送达方不能为空");
		return ;
	}
	if( data.length <2){
		$.messager.alert("提示", "订单行不能为空");
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
	var stationid = $("#stationid").val();
	var orgid = $("#orgid").val();
	var hbratio =$("#hbratio").val();
	var XJamt = parseFloat($("#XJamt").html());//应扣现金金额
	var SXamt = parseFloat($("#SXamt").html());//应扣授信金额
	var HBamt = parseFloat($("#HBamt").html());//应扣货补金额
	if(hbratio){
		if(parseFloat(HBamt)/parseFloat(XJamt+SXamt+HBamt)>parseFloat(hbratio/100)){
			$.messager.alert("提示", "货补金额不能大于"+hbratio+"%");
			return ;
		}
	}
	for(var i=0;i<data.length;i++){
		if(data[i].sku !='合计'){
			data[i].num = data[i].amounts*data[i].num;
			data[i].hbNum = data[i].amounts*data[i].hbNum;
			data[i].price =(data[i].price/data[i].amounts).toFixed(4);
			data[i].orderPrice =(data[i].orderPrice/data[i].amounts).toFixed(4);
		}else{
			data.splice(i,1);
		}
	}
	var jsondata = {
		"merchCustId" : merchCustId,
		"shipId" : shipId,
		"stationid" : stationid,
		"lines" : data
	};
	var url = "order/addOrder.json"
	$.post("order/addOrder", {
		"states":states,
		"merchCustId" : merchCustId,
		"shipId" : shipId,
		"stationid" : stationid,
		"orgid" : orgid,
		"xjamt" :XJamt,
		"sxamt":SXamt,
		"hbamt":HBamt,
		"orderType":"4",
		"remark":$("#remark").val(),
		"lines" : JSON.stringify(data),
		"freight":freight,
		"deliveryType":deliveryType
	}, function(res) {
		var result =  eval("("+res.data+")");  
		if (result.type == "500") {
			$.messager.alert("提示", "保存订单出错!"+data.code);
		}else if(result.type=='200'){
			if(flag=="1"){
				$.post("order/orderAudit", {"headerid":result.id,"states":"2","orderType":4}, function(res){
					if(res.data=="200"){
						window.location.href="order/index.html";
					}else{
						$.messager.alert("提示","保存成功，提交审批失败");
						window.location.href="order/index.html";
					}
				});
			}else{				
				$.messager.popup("保存订单成功!");
				window.location.href="order/index.html";
			}
		}
	});
}
function validateNum(value){
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