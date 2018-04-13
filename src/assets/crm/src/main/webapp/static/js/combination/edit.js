var $materialTable = null;
$(function(){
     $('#tcnsp').bootstrapTable({
    	url :'combination/linesdata?headerId='+$("#id").val(),
        method: 'get',
        classes:"table table-hover table-condensed",
        cache: false,
        striped: true,
        pagination : true,
        idField:"materialId",
        toolbar:"#tcnspTools",
        sidePagination : "server",
        sortName:"sku",
        clickToSelect: true,
        singleSelect:true,
        smartDisplay : true,
        showColumns : true,
        columns: [{
            field: 'ck',
            radio:true
        },{
            field: 'id',
            visible:false
        }, {
            field: 'materialId',
            title: '商品编码'
        }, {
            field: 'sku',
            title: '商品名称',
            align: 'left'
        }, {
            field: 'num',
            title: '数量',
            editable : {
                type : 'text',
                title : '分配数量',
                validate : function(value,s) {
                    value = $.trim(value);
                    var data = $('#tcnsp').bootstrapTable('getData'), index = $(
                        this).parents('tr').data('index');
                    if (!value) {
                        return 'This field is required';
                    }
                    if (!/^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,3})?$)/
                            .test(value)) {
                        return '请输入正确的数值,并保留3位小数'
                    }
                    data[index].num = value;
					$('#tcnsp').bootstrapTable("updateRow",{
						index:index,
						row:data[index]
					})
                    return '';
                }
            },
            visible:true,
            align: 'left'
        }
        /*, {
            field: 'price',
            title: '价格',
            editable : {
                type : 'text',
                title : '单品价格',
                validate : function(value,s) {
                    value = $.trim(value);
                    var data = $('#tcnsp').bootstrapTable('getData'), index = $(
                        this).parents('tr').data('index');
                    if (!value) {
                        return 'This field is required';
                    }
                    if (!/^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/
                            .test(value)) {
                        return '请输入正确的数值,并保留2位小数'
                    }
                    data[index].price = value;
					$('#tcnsp').bootstrapTable("updateRow",{
						index:index,
						row:data[index]
					});
					var rows = $('#tcnsp').bootstrapTable('getSelections');
					$.post("combination/edit/addLine",
						{
						"headerId":$("#id").val(),
	                	"materialId":rows[0].materialId,
	                	"num":1,
	                	"price":value,
	                	"id":rows[0].id
						}
					,function(res){
						//$('#tcnsp').bootstrapTable("refresh");
					});
					updatePrice();
                    return '';
                }
            },
            align: 'left'
        }*/
        ]
    });

    $('#tss').bootstrapTable({
    	url:'combination/rebatedata?headerId='+$("#id").val(),
        method: 'get',
        classes:"table table-hover table-condensed",
        cache: false,
        striped: true,
        idField:"id",
        toolbar:"#tssTools",
        sortName:"colName",
        sidePagination : "server",
        clickToSelect: true,
        singleSelect:true,
        smartDisplay : true,
        showColumns : true,
        columns: [{
            field: '',
            title: '',
            radio:true
        },{
            field: 'id',
            visible:false
        }, {
            field: 'limitNum',
            title: '数量限制',
            editable : {
                type : 'text',
                title : '数量',
                validate : function(value,s) {
                    value = $.trim(value);
                    var data = $('#tcnsp').bootstrapTable('getData'), index = $(
                        this).parents('tr').data('index');
                    if (!value) {
                        return 'This field is required';
                    }
                    if (!/^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,3})?$)/
                            .test(value)) {
                        return '请输入正确的数值,并保留3位小数'
                    }
                    data[index].limitNum = value;
					$('#tcnsp').bootstrapTable("updateRow",{
						index:index,
						row:data[index]
					})
                    return '';
                }
            },
            align: 'left'
        }, {
            field: 'ratio',
            title: '返利比例',
            editable : {
                type : 'text',
                title : '比例',
                validate : function(value,data) {
                    value = $.trim(value);
                    var data = $('#tcnsp').bootstrapTable('getData');
                    index = $(this).parents('tr').data('index');
                    if (!value) {
                        return 'This field is required';
                    }
                    if (!/^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/
                            .test(value)) {
                        return '请输入正确的数值,并保留2位小数'
                    }
                    data[index].ratio = value;
					$('#tcnsp').bootstrapTable("updateRow",{
						index:index,
						row:data[index]
					})
                    return '';
                }
            },
            align: 'left',
            formatter:function(value){
            	return value +'%';
            }
        }, {
            field: 'materialId',
            title: '产品编码',
            align: 'left'
        }
        /*, {
            field: 'price',
            title: '价格',
            editable : {
                type : 'text',
                title : '价格',
                validate : function(value,s) {
                    value = $.trim(value);
                    var data = $('#tcnsp').bootstrapTable('getData'), index = $(
                        this).parents('tr').data('index');
                    if (!value) {
                        return 'This field is required';
                    }
                    if (!/^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/
                            .test(value)) {
                        return '请输入正确的数值,并保留2位小数'
                    }
                    data[index].price = value;
					$('#tcnsp').bootstrapTable("updateRow",{
						index:index,
						row:data[index]
					})
                    return '';
                }
            },
            align: 'left'
        }*/
        ]
    });
    //初始化产品table
    initProductTable();
    $('#materialTable').bootstrapTable('refresh',{
		'url':'combination/product?modelType='+$("#modelType").val()
	});
    //点击添加产品按钮
    $("#btn_add_products").click(function (e) {
    	var modelType =$("#modelType").val();
    	if(!modelType){
    		$.messager.popup("请选择生意模式");
    		return;
    	}
    	$('#materialTable').bootstrapTable('refresh',{
    		'url':'combination/product?modelType='+$("#modelType").val()
    	});
        $("#addProductModal").modal("show");
    });
    
    //添加产品确定按钮
    $("#btn-appendData").click(function (e) {
        var rows = $('#materialTable').bootstrapTable("getAllSelections");
        if(rows && rows.length > 0){
            for(var i = 0;i<rows.length;i++){
                $.post("combination/edit/addLine",{
                	"headerId":$("#id").val(),
                	"materialId":rows[i].materialId,
                	"num":1,
                	"price":0
                },function(res){
                	 $("#tcnsp").bootstrapTable('refresh');
                	 //updatePrice();
                })
            }
            $("#addProductModal").modal("hide");
        }
    });
    
    //添加返利产品
    $("#btn-add-point").click(function(e){
        $("#addPointsModal").modal("show")
    });
    
    // chosen 初始化
	$('#rebateMaterial').chosen({
		no_results_text : "没有找到.",
		placeholder_text : "请选择产品...",
		search_contains : true,
		width:"100%"
		//disable_search_threshold : 10
	});
    //初始化返利物料
    var rebateSkuUrl="combination/rebateMaterial"
    	$.get(rebateSkuUrl,function(res){
    		if(res.rows && res.rows.length>1){
    			$('#rebateMaterial').html();
    			$('#rebateMaterial').append("<option></option>");
    			for (var i=0;i<res.rows.length;i++){
    				$('#rebateMaterial').append(
    						"<option value='" + res.rows[i].sapId + "'>"+ res.rows[i].sapId + res.rows[i].sku + "</option>");
    			}
    			$("#rebateMaterial").trigger("chosen:updated");
    		}
    	});
   //添加返利产品确定按钮事件
   var addRebateLineValidator = initRebateValidate("#addRebateLineForm");
   $("#addRebateLineForm").ajaxForm({
		target : '#btn-addRbate', 
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
	    	return addRebateLineValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			 $("#rebateMaterial").val('');
			 $("#rebateMaterial").trigger("chosen:updated");
			 $("#addPointsModal").modal("hide");
			 $('#tss').bootstrapTable("refresh");
		}
	});
   
   var editValidator = initValidate("#editCombinationForm");
   $("#editCombinationForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			var tcnsp =$("#tcnsp").bootstrapTable('getData');
	    	if(tcnsp.length <1){
	    		$.messager.popup("产品不可以为空");
	    		return false;
	    	}
	    	/*if(!$("#price").val()|| parseFloat($("#price").val())<=0){
	    		$.messager.popup("价格必须大于0");
	    		return false;
	    	}*/
	    	$.each(formData, function(i, val){
				if(val.name=='linedata'){
					var tcnsp =$("#tcnsp").bootstrapTable('getData');
					val.value=JSON.stringify(tcnsp);
				}else if(val.name=='rebatedata'){
					var tss =$("#tss").bootstrapTable('getData');
					val.value=JSON.stringify(tss);
				}
			});
	    	return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
               $.messager.popup("新增录入信息失败",responseText.errorMessage);
           }else{
   			if(responseText.data=="S"){
   				window.location.href="combination/index.html";
   			}
           }
		}
	});
   
   //刪除产品按钮
   $("#btn_del_product").click(function(){
	   var rows = $("#tcnsp").bootstrapTable('getSelections');
	   if(rows){
		   $.post("combination/edit/delLine?id="+rows[0].id,function(res){
			   $("#tcnsp").bootstrapTable('refresh');
			  // updatePrice();
		   })
	   }else{
		   $.messager.popup("请选择产品");  
	   }
   })
    //刪除返利按钮
   $("#btn_del_rebate").click(function(){
	   var rows = $("#tss").bootstrapTable('getSelections');
	   if(rows){
		   $.post("combination/edit/delRebate?id="+rows[0].id,function(res){
			   $("#tss").bootstrapTable('refresh')
		   })
	   }else{
		   $.messager.popup("请选择产品");  
	   }
   })
})
$(document).ready(function(){
	$("#bDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#eDate").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
});
function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			code:{
				required:true,
				maxlength:20
			},
			name:{
				required:true,
				maxlength:50
			},
			weight:{
				required:true,
				maxlength:20
			},
			modelType:"required",
			bDate:"required",
			eDate:"required"
		}
	});
	return validator;
}
function initRebateValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			ratio:{
				required:true,
				number:true,
				max:100
			},
			limitNum:{
				required:true,
				number:true,
				min:0.001
			},
			materialId:"required"
		}
	});
	return validator;
}
function initProductTable(){
	$('#materialTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		dataType: "json",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "materialId",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : true,
		showColumns : true,
		pagination: true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : false,
        columns : [ {
            field : '',
            title : '',
            checkbox : true
        }, {
            field : 'sku',
            title : '名称',
            formatter:function (value) {
                return "<small>"+value+"</small>";
            },
            align : 'left'
        }, {
            field : 'materialId',
            title : '物料编码',
            align : 'left'
        }, {
            field : 'brand',
            title : '品牌',
            align : 'left'
        }
        /*,{
            field : 'price',
            title : '标准价',
            align : 'left'
        } */
        ]
    });
}

function updatePrice(){
	/*var amt =0;
	var tcnsp =$("#tcnsp").bootstrapTable('getData');
	if(tcnsp && tcnsp.length>0){
		for(var i=0;i<tcnsp.length;i++){
			amt = amt + parseFloat(tcnsp[i].price);
		}
	}
	$("#price").val(amt.toFixed(2));
	$.post("combination/updatePrice", {
		"id" : $("#id").val(),
		"price" : $("#price").val()
	}, function(res) {
		if (res.data != 'S') {
			$.messager.popup("更新套餐单价失败");
		}
	})*/
}