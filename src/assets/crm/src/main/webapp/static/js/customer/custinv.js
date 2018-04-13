$(function(){
    $('#custinvTable').bootstrapTable({
        method: 'post',
        classes:"table table-hover table-condensed",
        url: 'customer/custinv/list',
        contentType: 'application/x-www-form-urlencoded',
        cache: false,
        toolbar:"#dictTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"id",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: false,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'no',
            title: '编号',
            radio:true
        }, {
            field: 'customerName',
            title: '客户名称',
            align: 'left'
        },  {
            field: 'sapCustomerId',
            title: 'SAP客户编码',
            align: 'left'
        },  {
            field: 'custType',
            title: '客户类型',
            align: 'left',
            formatter: function(value){
            	var text = $('#custTypeSelection').find('option[value="'+value+'"]').text();
				return text;
            }
        }, {
            field: 'salesOrgName',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'createTsStr',
            title: '盘点月份',
            align: 'left',
            visible: false
        }, {
            field: 'attribute4',
            title: 'SKU',
            align: 'left',
            visible: false
        }, {
            field: 'batchNum',
            title: '批次号',
            align: 'left',
            visible: false
        }, {
            field: 'attribute1',
            title: '进货量',
            align: 'left',
            visible: false
        }, {
            field: 'invNum',
            title: '库存量',
            align: 'left',
            visible: false
        }, {
            field: 'salesrepName',
            title: '盘点人',
            align: 'left',
            visible: false
        }, {
            field: 'attribute2',
            title: '良品数量',
            align: 'left',
            visible: false
        }, {
            field: 'attribute3',
            title: '残次品数量',
            align: 'left',
            visible: false
        }, {
            field: 'salesMan',
            title: '对应销售人员',
            align: 'left'
        }, {
            field: 'states',
            title: '客户状态',
            align: 'left',
            formatter: function(value){
            	var text = $('#customerStatesSelection').find('option[value="'+value+'"]').text();
				return text;
            }
        }]
    });
    var addValidator = initValidate("#addForm");
    var editValidator = initValidate("#editForm");
    $("#editForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            $("#editModal").modal("hide");
            $("#custinvTable").bootstrapTable("refresh")
        }
    });
    $("#addForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return addValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            $("#addModal").modal("hide");
            $("#custinvTable").bootstrapTable("refresh")
        }
        // other available options:
        //url:       url         // override for form's 'action' attribute
        //type:      type        // 'get' or 'post', override for form's 'method' attribute
        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type)
        //clearForm: true        // clear all form fields after successful submit
        //resetForm: true        // reset the form after successful submit
        //timeout:   3000
    });
    $("#editBtn").bind("click",doEdit);
    $(".btn-del").bind("click",doDel);
//    $("#btn-detail").bind("click",doShow);
    $("#searchButton").bind('click', searchCustInv);
    
    //加载客户列表
    loadCustomerSelection();
    
    //选择客户时加载
    $("#merchCustId").change(function(){
    	loadCustomerProduct('attribute4',$(this).val());
    });
    
    $("select[name='attribute4']").change(function(){
    	$(this).filter(":visible").closest('form').find('input[name="materialId"]').val($(this).filter(":visible").find(':selected').attr('mid'));
    });
});

function searchCustInv(){
	var customerName = $("#customerName").val();
	var productName = $("#productName").val();
	var date = $("#date").val();
	$("#custinvTable").bootstrapTable("refresh",
		{'url' : 'customer/custinv/list?customerName='+customerName+'&productName='+productName+'&date='+date}
	);
}

/**
 *	获取用户的产品信息
 */
function loadCustomerProduct(eleId,merchid,callback){
	var materialurl = "order/util/custProduct?merchid=" + merchid+"&orgid="+$("#orgid").val();
	$.get(materialurl, function(data) {
		if (data.rows && data.rows.length > 0) {
			materialdata = data.rows;
			var productOptions = "<option value=''></option>";
			for (var i = 0; i < data.rows.length; i++) {
				productOptions = productOptions + 
						"\r <option value='" + data.rows[i].sku
								+ "' mid='"+data.rows[i].materialId+"'>" + data.rows[i].materialId+'-'+data.rows[i].sku
								+ "</option>";
			}
			$("#"+eleId).html(productOptions);
			if(callback){
				callback();
			}
		}else{
			$("#"+eleId).html("");
		}
	});
}

function loadCustomerSelection(){
	$.ajax({
	        type:'POST',
	        url:'order/util/customer',
	        dataType : 'json',
	        success : function(result) {
	        	if(typeof result != 'undefined' && result.rows && result.rows.length > 0){
	        		data = result.rows;
	        		var custs = new Array();
	  	          	var custOpts = "";
	  	          	for(var i in data){
  	              		custs.push(data[i]);
  	              		custOpts = custOpts + "\r <option value="+ data[i].id +" >"+data[i].custname+"</option>";
	  	            }
	  	          	$('#merchCustId').html(custOpts);
	  	          	$('#editmerchCustId').html(custOpts);
	  	           	$('#merchCustId').data('custs',custs);
	  	           	
	  	          //加载sku
	  	          loadCustomerProduct('attribute4',$("#merchCustId").val());
	        	}else{
	        		alert("无法获取客户信息！");
	        	}
	        }
  	});
	$('#merchCustId').html();
}

function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
        	merchCustId: "required",
        	createTs: "required",
        	attribute4: {required: true,maxlength: 30},
        	batchNum: {required: true,minlength: 2,maxlength: 12},
        	attribute1: {required: true,number:true},
        	invNum: {required: true,number:true},
        	attribute2: {required: true,number:true},
        	attribute3: {required: true,number:true}
        }
    });
    return validator;
}

/**
 * 编辑按钮
 */
function doEdit(){
    var rows = $("#custinvTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        autoEdit(rows[0]);
        loadCustomerProduct('editattribute4',$("#editmerchCustId").val(),function(){$('#editattribute4').val(rows[0].attribute4);});
        $("#editModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}

/*function doShow(){
    var rows = $("#custinvTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        showDetail("system/dict/detail?id="+rows[0].id);
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}*/

function doDel(){
    var rows = $("#custinvTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
            $.post("customer/custinv/delete",{"id":rows[0].id},function(){$("#custinvTable").bootstrapTable("refresh");})
        });
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}