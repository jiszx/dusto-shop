var $table = $('#order-table');
var dataid = 1;
var lineamt = 0;
var lineorderamt = 0;
var linenum = 0;
var customerdata;
var states='1';
var flag="0";
var editrow;
var merch;
var keepSku = false;
var data = [ {
	"id" : '0',
	"orderPrice" : "合计",
	"num" : "0",
	"amt" : '0',
	"orderAmt" : '0'
} ]
var str = {
		"id" : '0',
		"orderPrice" : "合计",
		"num" : "0",
		"amt" : '0',
		"orderAmt" : '0'
	};
$(function() {
	$("#del-bstb-row").bind("click",doDelLine);
	$("#edit-bstb-row").bind("click",doEdit);
	$("#num").blur(function(){
		validateLine();
	});
	
	$("#editnum").blur(function(){
		editvalidateline();
	});
	;
	$('#btn-add-agent').click(
			function() {
				if(!$("#material").val()){
					$.messager.popup("请选择购买产品");
					return;
				}
				if(validateNum($('#num').val()) ==false){
					$.messager.popup("只能输入大于0的数字，输入小于三位小数");
					return ;
				}
				if(parseFloat($("#price").val())<=0){
					$.messager.popup("单价为0的订单不允许保存");
					return ;
				}
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
			});
	initTable();
	var materialdata;
	var policydata;
	// 初始化客户数据
	$('#merchCustId').chosen({
		no_results_text : "没有找到",
		allow_single_de : true
	});
	var url = 'order/util/distributCustomer?custType=5';
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			$('#merchCustId').html();
			$('#merchCustId').append("<option></option>");
			customerdata=data.rows;
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

	// 初始化物料选择
	$('#material').chosen({
		no_results_text : "没有找到",
		search_contains:true,
		width:"100%" 
	});
	$("#merchCustId").change(
			function() {
				str ={
						"id" : '0',
						"orderPrice" : "合计",
						"num" : "0",
						"amt" : '0',
						"orderAmt" : '0'
					};
			    
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
				var materialurl = "order/util/retailProduct?merchid=" + $("#merchPid").val()+"&orgid="+$("#orgid").val();
				$.get(materialurl, function(data) {
					if (data.rows && data.rows.length > 0) {
						$.each(data.rows, function(i, val){
							val.unitprice = priceChange(val.unitprice, val.amounts, true).toFixed(2);
							val.hPrice = priceChange(val.hPrice, val.amounts, true).toFixed(2);
							val.basePrice = priceChange(val.basePrice, val.amounts, true).toFixed(2);
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
						$('#material').chosen({
							no_results_text : "没有找到",
							allow_single_de : true
						});
					}
				});
			})
	$("#material").change(
			function() {
				cleanLine();
				var materialid = $("#material").val();
				for (var i = 0; i < materialdata.length; i++) {
					if (materialdata[i].materialId == materialid) {
						$("#materialName").val(materialdata[i].sku);// 物料描述
						$("#price").val(materialdata[i].unitprice);// 订单单价
						$("#unit").val(materialdata[i].unit);// 物料基本价ID
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
	$("#btn-edit-save").bind("click",doEditSave);
})
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
	lineorderamt = parseFloat(lineorderamt) + parseFloat(agent.orderAmt);
	changeTotalAndamt();
	cleanLine();
}

function changeTotalAndamt() {
	var totaldata = {
		"id" : '0',
		"orderPrice" : "合计",
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
		num =0;
	}
	var price =parseFloat($("#price").val());//订单单价
	var amt = parseFloat(price*num);
	$("#amt").val(parseFloat(price*num).toFixed(2));
	$("#orderAmt").val((parseFloat(price*num)).toFixed(2));
//	var num =parseFloat($("#num").val());
//	var url ="order/util/validateNum?materialId="+$("#material").val()
//			+"&orgid="+$("#orgid").val()
//			+"&merchCustId="+$("#merchPid").val()
//			+"&num="+num;
//	$.get(url,function(res){
//		if(res.data && res.data =="200"){
//			numchangeamt()
//		}else{
//			$("#num").val('0');
//			$("#orderAmt").val('0');
//			$("#amt").val('0');
//			$.messager.alert("提示", "当前可售库存不足，请联系销售内务");
//		}
//	});
}
function editvalidateline(){
	var num =parseFloat($("#editnum").val());//订单数量
	if(!num){
		num =0;
	}
	if(validateNum(num) ==false){
		$("#editerror").html('数量只能输入大于0的数字，输入小于三位小数');
		return ;
	}
	var price =parseFloat($("#editprice").val());//订单单价
	var amt = parseFloat(price*num);
	//订单金额
	$("#editamt").val((parseFloat(price*num)).toFixed(2));
	$("#editorderAmt").val((parseFloat(price*num)).toFixed(2));
//	var num =parseFloat($("#editnum").val());
//	var url ="order/util/validateNum?materialId="+$("#editmaterialId").val()
//			+"&orgid="+$("#orgid").val()
//			+"&merchCustId="+$("#merchPid").val()
//			+"&num="+num;
//	$.get(url,function(res){
//		if(res.data && res.data =="200"){
//			editnumchangeamt()
//		}else{
//			$("#editnum").val('0');
//			$("#editorderAmt").val('0');
//			$("#editamt").val('0');
//			$.messager.alert("提示", "当前可售库存不足，请联系销售内务");
//		}
//	});
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
	if(num < 0){
		$.messager.popup("数量不能小于0");
		return;
	}
	if(validateNum($("#editnum").val()) ==false){
		$.messager.popup("数量只能输入大于0的数字，输入小于三位小数");
		return;
	}
	if(parseFloat($("#editprice").val())<=0){
		$.messager.popup("单价为0的订单不允许保存");
		return ;
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
	linenum = parseFloat(linenum) + parseFloat($("#editnum").val());
	lineorderamt = parseFloat(lineorderamt) + parseFloat($("#editorderAmt").val());
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
		linenum = parseFloat(linenum) - parseFloat(row[0].num);
		lineorderamt = parseFloat(lineorderamt) - parseFloat(row[0].orderAmt);
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
	var temp = JSON.parse(JSON.stringify(data));
	$.each(temp, function(i, val){
		val.orderPrice = priceChange(val.orderPrice, val.amounts);
		val.price = priceChange(val.price, val.amounts);
		val.num = priceChange(val.num, val.amounts, true);
	});
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
		"orderType":"1",
		"lines" : JSON.stringify(temp)
	}, function(res) {
		if (res.errorCode === -1) {
            $.messager.alert("提示", "保存订单出错! "+res.errorMessage);
            return;
		}
		var result =  eval("("+res.data+")");  
		if (result.type == "500") {
			$.messager.alert("提示", "保存订单出错!"+result.code);
		}else if(result.type=='200'){
			if(flag=="1"){
				$.post("order/orderAudit", {"headerid":result.id,"states":"2","orderType":0}, function(res){
					if(res.data=="200"){
						window.location.href="order/index.html";
					}else{
						$.messager.alert("提示","保存成功，提交审批失败");
						window.location.href="order/index.html";
					}
				});
			}else{
				$.messager.popup("保存订单成功!");
				window.location.href="order/retail/index.html";
			}
			
		}
		$("#btn-save").removeAttr("disabled");
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