/**
 * Created by 杨 on 2016/12/13.
 */
var $tcnsp=null;
var $materialTable = null;
var id =0;
var rebateid=0;
$(function(){
	/*$.validator.addMethod("validatecode",function(value,element){
		//套餐编码唯一性验证
    	$.post("combination/validateCode?code="+value,function(res){
    		if(res.data =='S'){
    			return true;
    		}else{
    			return false;
    		}
    	})
	},"套餐编码已被使用，请修改");*/
	
    $tcnsp = $('#tcnsp').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        cache: false,
        striped: true,
        idField:"sapId",
        toolbar:"#tcnspTools",
        sortName:"colName",
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'id',
            radio:true
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
        }]
    });

    $('#tss').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        cache: false,
        striped: true,
        idField:"id",
        toolbar:"#tssTools",
        sortName:"colName",
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'id',
            title: '',
            radio:true
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
        }]
    });
    $("#modelType").change(function(){
    	$tcnsp.bootstrapTable("removeAll");
    });
    //初始化产品table
    initProductTable();
    
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
                rows[i].num = "1";
                rows[i].id =id;
                id =id+1;
            }
            $tcnsp.bootstrapTable("append",rows);
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
   $("#btn-addRbate").click(function(){
	   var agentItem = {
			   limitNum : $('#limitNum').val(),
			   ratio : $("#rebateRatio").val(),
			   materialId : $('#rebateMaterial').val(),
			   price : 0,
			   id: rebateid
			}
	   rebateid =rebateid +1;
	   $('#tss').bootstrapTable("append",agentItem);
	   $("#rebateMaterial").val('');
	   $("#rebateMaterial").trigger("chosen:updated");
	   $("#addPointsModal").modal("hide");
   })
   var addValidator = initValidate("#addCombinationForm");
   $("#addCombinationForm").ajaxForm({
		target : '#btn-save', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			var tcnsp =$("#tcnsp").bootstrapTable('getData');
	    	if(tcnsp.length <1){
	    		$.messager.popup("产品不可以为空");
	    		return false;
	    	}
	    	$.post("combination/validateCode?code="+$("#code").val(),function(res){
	    		if(res.data !='S'){
	    			$.messager.popup("套餐编码已被使用，请修改");
	    			return false;
	    		}
	    	})
	    	$.each(formData, function(i, val){
				if(val.name=='linedata'){
					var tcnsp =$("#tcnsp").bootstrapTable('getData');
					val.value=JSON.stringify(tcnsp);
				}else if(val.name=='rebatedata'){
					var tss =$("#tss").bootstrapTable('getData');
					val.value=JSON.stringify(tss);
				}
			});
	    	return addValidator.valid();
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
		   $('#tcnsp').bootstrapTable('remove', {
				field : 'id',
				values :[ rows[0].id ]
			});
	   }else{
		   $.messager.popup("请选择产品");  
	   }
   })
   $("#btn_del_rebate").click(function(){
	   var rows = $("#tss").bootstrapTable('getSelections');
	   if(rows){
		   $('#tss').bootstrapTable('remove', {
				field : 'id',
				values :[ rows[0].id ]
			});
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
				//,validatecode:true
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
        }*/
        ]
    });
}

