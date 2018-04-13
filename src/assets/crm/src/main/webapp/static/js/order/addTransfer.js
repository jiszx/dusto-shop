var $table = $('#order-table');
var dataid = 1;
var lineamt = 0;
var lineorderamt = 0;
var linenum = 0;
var customerdata=[];
var states='1';
var flag="0";
var editrow;
var merch;
var keepSku = false;
var materialdata;
var data = [ {
	"id" : '0',
	"sku" : "合计",
	"num" : "0",
	"amt" : '0',
	"orderAmt" : '0'
} ]
$(function() {
	$("#del-bstb-row").bind("click",doDelLine);
	$("#edit-bstb-row").bind("click",doEdit);
	$("#num").blur(function(){
		validateLine();
	});
	$("#price").blur(function(){
		validateLine();
	});
	$("#editnum").blur(function(){
		editvalidateline();
	});
	$("#btn-add-agent").bind("click", function(){
		setTimeout(btnaddagent(),1000);
	});
	initTable();
	// 初始化客户数据
	$('#merchCustId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true
	});
	var url = 'order/util/distributCustomer?custType=5';
	$('#merchCustId').html('');
	$('#merchCustId').append("<option></option>");
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			customerdata=customerdata.concat(data.rows);
			for (var i = 0; i < data.rows.length; i++) {
				$('#merchCustId').append(
						"<option value='" + data.rows[i].id + "'>"
								+ data.rows[i].name + "</option>");
			}
			$("#merchCustId").trigger("chosen:updated");
			$('#merchCustId').chosen({
				no_results_text : "没有找到",
				allow_single_de : true
			});
		}
	});
	/*$.get('order/util/distributCustomer?custType=3', function(data) {
		if (data.rows && data.rows.length > 0) {
			customerdata=customerdata.concat(data.rows);
			for (var i = 0; i < data.rows.length; i++) {
				$('#merchCustId').append(
						"<option value='" + data.rows[i].id + "'>"
						+ data.rows[i].name + "</option>");
			}
			$("#merchCustId").trigger("chosen:updated");
			$('#merchCustId').chosen({
				no_results_text : "没有找到",
				allow_single_de : true
			});
		}
	});*/

	// 初始化物料选择
	$('#material').chosen({
		no_results_text : "没有找到",
		search_contains:true,
		width:"100%" 
	});
	$("#merchCustId").change(
			function() {
				$('#order-table').bootstrapTable("removeAll");
				var str = {
						"id" : '0',
						"sku" : "合计",
						"num" : "0",
						"amt" : '0',
						"orderAmt" : '0'
					};
				$('#order-table').bootstrapTable('insertRow', {
					index : 0,
					row : str
				});
				cleanLine();
			    keepSku = false;
				var merchid = $("#merchCustId").val();
				//获取对应的岗位ID，销售组织ID,货补比例
				for(var i=0;i<customerdata.length;i++){
					if(merchid==customerdata[i].id){
						if($("#merchPid").val()==customerdata[i].pid){
							keepSku = true;
						}
						$("#orgid").val(customerdata[i].organizationId);
						$("#merchPid").val(customerdata[i].pid);
						$("#distributename").val(customerdata[i].pname)
					}
				}
				if(keepSku==false){
					lineamt = 0;
				    lineorderamt = 0;
				    linenum = 0;
					$('#order-table').bootstrapTable("removeAll");
					$('#order-table').bootstrapTable('insertRow', {
						index : 0,
						row : str
					});
				}
				// 获取用户的产品信息
				$("#material").empty();
				$("#material").trigger("chosen:updated");
				var materialurl = "order/util/retailProduct?merchid=" + $("#merchPid").val()+"&orgid="+$("#orgid").val();
				$.get(materialurl, function(data) {
					if (data.rows && data.rows.length > 0) {
						$.each(data.rows, function(i, val){
							val.unitprice = priceChange(val.unitprice, val.amounts, true).toFixed(4);
							val.hPrice = priceChange(val.hPrice, val.amounts, true).toFixed(4);
							val.basePrice = priceChange(val.basePrice, val.amounts, true).toFixed(4);
							val.invnum = priceChange(val.invnum, val.amounts).toFixed(3);
						});
						materialdata = data.rows;
						$("#material").append("<option value=''></option>");
						for (var i = 0; i < data.rows.length; i++) {
							if(parseFloat(data.rows[i].unitprice) >0){								
								$('#material').append(
										"<option value='" + data.rows[i].materialId
										+ "'>"+data.rows[i].materialId+'-'+ data.rows[i].sku
										+ "</option>");
							}
						}
						$("#material").trigger("chosen:updated");
					}
				});
			})
	$("#material").change(function() {
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
				$("#materialName").val(materialdata[i].sku);// 物料描述
				$("#price").val(materialdata[i].unitprice);// 订单单价
				if (materialdata[i].unit == '吨') {
					$("#unit").val(materialdata[i].unit);//							
				} else {
					$("#unit").val("箱");
				}
				$("#wlPrice").val(materialdata[i].price);// 物料物流价
				$("#hPrice").val(materialdata[i].hPrice);// 物料高卖价
				$("#invnum").val(materialdata[i].invnum);// 物料高卖价
				$("#orderPrice").val(materialdata[i].unitprice);
				$("#amounts").val(materialdata[i].amounts);
			}
		}
	});
	$("#btn-save").bind('click', doSave);
	$("#btn-save-audit").bind("click",doSaveAndAudit);
	$("#btn-edit-save").bind("click", function(){
		setTimeout(doEditSave(),1000);
	});
})

function btnaddagent(){
	if(!$("#material").val()){
		$.messager.popup("请选择购买产品");
		return;
	}
	if(validateNum($('#num').val()) ==false){
		$.messager.popup("只能输入大于0的数字，输入小于三位小数");
		return ;
	}
	if(validatePrice($("#price").val()) ==false){
		$.messager.popup("单价大于0的四位小数");
		return ;
	}
	
	if(parseFloat($("#price").val())<=0){
		$.messager.popup("单价为0的订单不允许保存");
		return ;
	}
	var invnum = $("#invnum").val();
	if(!invnum || parseFloat(invnum)<parseFloat($('#num').val())){
		$.messager.popup("库存不足！");
		return;
	}
	$("#orderPrice").val($("#price").val());
	var agentItem = {
		id : dataid,
		sku : $('#materialName').val(),
		materialId : $("#material").val(),
		unit : $('#unit').val(),
		price : $('#price').val(),
		orderPrice :$("#orderPrice").val(),
		num : $('#num').val(),
		amt : $("#amt").val(),
		orderAmt : $("#orderAmt").val(),
		amounts:$("#amounts").val()
	}
	addAgent(agentItem);
	$("#material").val("");
	$("#material").trigger("chosen:updated");
	//$("#material").empty();
}

// 清空行数据
function cleanLine() {
	$('#materialName').val('');
	$('#unit').val('');
	$('#price').val('0');
	$('#hPrice').val('0');
	$("#wlPrice").val('0');
	$('#num').val('0');
	$("#amt").val('0');
	$("#orderAmt").val('0');
	$("#orderPrice").val('0');
	$("#invnum").val('');
}
function addAgent(agent) {
	var mid = agent.materialId;
	var agents = $table.bootstrapTable('getData');
	var isMerge = false;
	$.each(agents, function(i, val){
		if(val.materialId==mid){
			val.num = parseFloat(val.num)+parseFloat(agent.num);
			val.orderAmt = parseFloat(val.orderAmt)+parseFloat(agent.orderAmt);
			$table.bootstrapTable('load', agents);
			isMerge = true;
		}
	});
	if(isMerge==false){
		$table.bootstrapTable('insertRow', {
			index : 0,
			row : agent
		});
	}
	
	dataid = dataid + 1;
	lineamt = parseFloat(lineamt) + parseFloat(agent.amt);
	lineamt = lineamt.toFixed(2);
	
	linenum = parseFloat(linenum) + parseFloat(agent.num);
	linenum = linenum.toFixed(3);
	
	lineorderamt = parseFloat(lineorderamt) + parseFloat(agent.orderAmt)
	lineorderamt= lineorderamt.toFixed(2);
	
	changeTotalAndamt();
	cleanLine();
}

function changeTotalAndamt() {
	var totaldata = {
		"id" : '0',
		"sku" : "合计",
		"num" : linenum,
		"amt" : lineamt,
		"orderAmt" : lineorderamt

	};
	$table.bootstrapTable('updateByUniqueId', {
		id : '0',
		row : totaldata
	});
}
function validateLine(){
	var num =parseFloat($("#num").val());//订单数量
	if(!num){
		$("#num").val('0');
		num =0;
	}
	var price =parseFloat($("#price").val());//订单单价
	if(!price){
		$("#price").val('0');
		price =0;
	}
	var amt = parseFloat(price*num);
	$("#amt").val(parseFloat(price*num).toFixed(2));
	$("#orderAmt").val((parseFloat(price*num)).toFixed(2));

}
function editvalidateline(){
	var num =parseFloat($("#editnum").val());//订单数量
	if(!num){
		$("#editnum").val('0');
		num =0;
	}
	/*if(validateNum(num) ==false){
		$("#editerror").html('数量只能输入大于0的数字，输入小于三位小数');
		return ;
	}*/
	var price =parseFloat($("#editprice").val());//订单单价
	var amt = parseFloat(price*num);
	//订单金额
	$("#editamt").val((parseFloat(price*num)).toFixed(2));
	$("#editorderAmt").val((parseFloat(price*num)).toFixed(2));
}

function initTable() {
	$table.bootstrapTable({
				// url : 'static/table/new/data.json',
				data : data,
				idField : 'id',
				uniqueId : 'id',
				showFooter:false,
				clickToSelect:true,
				search: false,
				showRefresh: false,
				columns : [
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
							title : '单价(元)',
							field : 'orderPrice',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true
						},
						{
							title : '数量',
							field : 'num',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true
						},{
							title : '金额',
							field : 'orderAmt',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							sortable : true
						},{
							title : '包含数',
							field : 'amounts',
							rowspan : 1,
							align : 'center',
							valign : 'middle',
							visible : false
						}]
			});
}

function doEdit(){
	var row =$table.bootstrapTable('getSelections');
	if (row && row.length > 0 && row[0].id !="0") {
		var tmp = JSON.parse(JSON.stringify(row[0]));
		autoEdit(tmp);
		editrow =row;
		$.each(materialdata, function(i, val){
			if(tmp.materialId==val.materialId){
				$("#editinvnum").val(val.invnum);
			}
		});
		$("#editLineModal").modal("show");
		//获取当前可售库存
//		var invnum = "order/util/invnum?materialId="+row[0].materialId+"&merchCustId="+$("#merchPid").val()+"&orgid="+$("#orgid").val();
//		$.get(invnum,function(data){
//			if(data.data){
//				$("#editinvnum").val(data.data);
//				$("#editLineModal").modal("show");
//			}
//		})
	}else {
		$.messager.alert("请选择要出合计外的行。");
	}
}
function doEditSave(){
	var num =parseFloat($("#editnum").val());//订单数量
	if(validateNum($("#editnum").val()) ==false){
		$.messager.popup("数量只能输入大于0的数字，输入小于三位小数");
		return;
	}
	if(parseFloat($("#editprice").val())<=0){
		$.messager.popup("单价为0的订单不允许保存");
		return ;
	}
	var invnum = $("#editinvnum").val();
	if(!invnum || parseFloat(invnum)<num){
		$.messager.popup("库存不足！");
		return;
	}
	$table.bootstrapTable('remove', {
		field : 'id',
		values :[ editrow[0].id ]
	});
	lineamt = parseFloat(lineamt) - parseFloat(editrow[0].amt);
	linenum = parseFloat(linenum) - parseFloat(editrow[0].num);
	lineorderamt = parseFloat(lineorderamt) - parseFloat(editrow[0].orderAmt);
	changeTotalAndamt();
	var agentItem = {
			id : dataid,
			sku : $('#editsku').val(),
			materialId : $("#editmaterialId").val(),
			unit : $('#editunit').val(),
			price : $('#editprice').val(),
			orderPrice :$("#editorderPrice").val(),
			num : $('#editnum').val(),
			amt : $("#editamt").val(),
			orderAmt : $("#editorderAmt").val(),
			amounts:$("#editamounts").val()
		}
	$table.bootstrapTable('insertRow', {
		index : 0,
		row : agentItem
	});
	dataid = dataid + 1;
	lineamt = parseFloat(lineamt) + parseFloat($("#editamt").val());
	lineamt= lineamt.toFixed(2);
	
	linenum = parseFloat(linenum) + parseFloat($("#editnum").val());
	linenum= linenum.toFixed(3);
	
	lineorderamt = parseFloat(lineorderamt) + parseFloat($("#editorderAmt").val());
	lineorderamt= lineorderamt.toFixed(2);
	
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
		lineamt=lineamt.toFixed(2);
		
		linenum = parseFloat(linenum) - parseFloat(row[0].num);
		linenum= linenum.toFixed(3);
		
		lineorderamt = parseFloat(lineorderamt) - parseFloat(row[0].orderAmt);
		lineorderamt =lineorderamt.toFixed(2);
		
		changeTotalAndamt();
	} else {
		$.messager.alert("请选择要出合计外的行。");
	}
}


// 保存销售订单
function doSave() {
	var data = $table.bootstrapTable('getData');
	var merchCustId = $("#merchCustId").val();
	if( !merchCustId){
		$.messager.alert("提示", "客户不能为空");
		return ;
	}
	if( data.length <2){
		$.messager.alert("提示", "订单行不能为空");
		return ;
	}
	$("#btn-save").attr("disabled","disabled");
	$("#btn-save-audit").attr("disabled","disabled");
	for(var i=0;i<data.length;i++){
		if(data[i].sku !='合计'){
			data[i].num = data[i].amounts*data[i].num;
			data[i].price =(data[i].price/data[i].amounts).toFixed(4);
			data[i].orderPrice =(data[i].orderPrice/data[i].amounts).toFixed(4);
		}else{
			data.splice(i,1);
		}
	}
	var url = "order/addOrder.json"
	$.post("order/addOrder", {
		"states":"1",
		"merchCustId" : merchCustId,
		"shipId" : $("#merchPid").val(),
		"stationid" : 0,
		"orgid" : $("#orgid").val(),
		"xjamt" :0,
		"sxamt":0,
		"hbamt":0,
		"orderType":"6",
		"lines" : JSON.stringify(data)
	}, function(res) {
		var result =  eval("("+res.data+")");  
		if (result.type == "500") {
			$.messager.alert("提示", "保存订单出错!"+data.code);
		}else if(result.type=='200'){
			if(flag=="1"){
				$.post("order/orderAudit", {"headerid":result.id,"states":"2","orderType":6}, function(res){
					if(res.data=="200"){
						window.location.href="order/transfer/transferOrder.html";
					}else{
						$.messager.alert("提示","保存成功，提交审批失败");
						window.location.href="order/transfer/transferOrder.html";
					}
				});
			}else{
				$.messager.popup("保存订单成功!");
				window.location.href="order/transfer/transferOrder.html";
			}
			
		}
		$("#btn-save").removeAttr("disabled");
		$("#btn-save-audit").removeAttr("disabled");
	});
}

function doSaveAndAudit(){
	flag='1';
	doSave();
}

function priceChange(price, amounts, isMulti){
	if(isMulti){
		return (parseFloat(price)*parseFloat(amounts));
	}
	return (parseFloat(price)/parseFloat(amounts));
}

function validateNum(value){
	var ir = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,3})?$/;
	/*var a=value.toString().split(".")[1];
	if(a && a.length>3){
		return false;
	}else{
		return true;
	}*/
	return ir.test(value);
}
function validatePrice(value){
	var ir = /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,4})?$/;
	return ir.test(value);
}