var addPromotiondata;
var editPromotiondata;
$(function(){
    $('#promotionApplyTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'promotion/inv/list?type=2',
        cache: false,
        toolbar:"#promotionApplyTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"orgname",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: true,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'ck',
            title: '',
            radio:true
        }, {
            field: 'id',
            title: 'id',
            align: 'left',
            visible : false
        }, {
            field: 'orgname',
            title: '所属销售组织',
            align: 'left'
        }, {
            field: 'storesname',
            title: '库房名称',
            align: 'left'
        }, {
            field: 'materialname',
            title: '促销品名称',
            align: 'left'
        }, {
            field: 'price',
            title: '单价',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }, {
            field: 'num',
            title: '出库数量',
            align: 'left'
        }, {
            field: 'amt',
            title: '出库金额',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        },{
            field: 'creater',
            title: '申请人',
            align: 'left'
        },{
            field: 'createTs',
            title: '申请时间',
            align: 'left'
        },{
            field: 'states',
            title: '状态',
            align: 'left',
            formatter : function(value) {
				return getPromotionInValue(value + "");
			}
        }]
    });
    var addValidator = initValidate("#addPromotionApplyForm");
    var editValidator = initValidate("#editPromotionApplyForm");
    $("#editPromotionApplyForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            $("#editPromotionApplyModal").modal("hide");
            $("#promotionApplyTable").bootstrapTable("refresh")
        }
    });
    $("#addPromotionApplyForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return addValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            $("#addpromotionApplyModal").modal("hide");
            $("#promotionApplyTable").bootstrapTable("refresh")
        }
    });
    $(".btn-edit").bind("click",doEdit);
    $(".btn-del").bind("click",doDel);
    $("#btn-audit").bind("click",doAudit);
    $("#btn-search").bind("click",doSearch);
    //绑定销售组织数据
   /* var url="promotion/util/org.json";
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			//$('#organizationId').append("<option>全部</option>");
			$('#searchorgid').append("<option></option>");
			//$('#editorganizationId').append("<option>全部</option>");
			for (var i = 0; i < data.rows.length; i++) {
				$('#searchorgid').append("<option value='"+data.rows[i].id+"'>"+data.rows[i].name+"</option>");
				$('#organizationId').append("<option value='"+data.rows[i].id+"'>"+data.rows[i].name+"</option>");
				$('#editorganizationId').append("<option value='"+data.rows[i].id+"'>"+data.rows[i].name+"</option>");
			}
		}
	});*/
   
	//销售组织变化时获取对应的库房
    $('#organizationId').change(function(){
    	var orgid= $("#organizationId").val();
    	if(orgid){
    		 //获取销售组织对应的库房
    		//库房
    		$("#storesId").html("");
    		var storesurl="promotion/util/invstores.json?orgid="+orgid;
    		$.get(storesurl, function(data) {
    			if (data.rows && data.rows.length > 0) {
    				$('#storesId').append("<option></option>"); 
    				for (var i = 0; i < data.rows.length; i++) {
    					$('#storesId').append("<option value='"+data.rows[i].storesId+"'>"+data.rows[i].storesname+"</option>");
    				}
    			}
    		});
    	}
    })
    //库房获取对应的促销品
    $("#storesId").change(function(){
    	//促销品
    	$("#promotionId").html("");
    	var promotionurl ="promotion/util/OutPromotion?storesId="+$("#storesId").val();
    	$.get(promotionurl, function(data) {
    		if (data.rows && data.rows.length > 0) {
    			$('#promotionId').append("<option></option>");
    			addPromotiondata = data.rows;
    			for (var i = 0; i < data.rows.length; i++) {
    				$('#promotionId').append("<option value='"+data.rows[i].promotionId+"'>"+data.rows[i].materialname+"</option>");
    	
    			}
    		}
    	});	
    })
    
     $('#promotionId').change(function(){
    	var promotionid=$("#promotionId").val();
    	$("#price").val('');
    	$("#invnum").val('');
    	if(promotionId){
    		for(var i=0;i<addPromotiondata.length;i++){
    			if(promotionid == addPromotiondata[i].promotionId){
    				$("#invnum").val(addPromotiondata[i].num);
    				$("#price").val(addPromotiondata[i].price);
    			}
    		}
    	}
    })
    
    $('#editorganizationId').change(function(){
    	var orgid= $("#editorganizationId").val();
    	if(orgid){
    		 //获取销售组织对应的库房
    		//库房
    		$("#editstoresId").html("");
    		var storesurl="promotion/util/invstores.json?orgid="+orgid;
    		$.get(storesurl, function(data) {
    			if (data.rows && data.rows.length > 0) {
    				$('#editstoresId').append("<option></option>"); 
    				for (var i = 0; i < data.rows.length; i++) {
    					$('#editstoresId').append("<option value='"+data.rows[i].storesId+"'>"+data.rows[i].storesname+"</option>");
    				}
    			}
    		});
    	}
    })
    
    //库房获取对应的促销品
    $("#eidtstoresId").change(function(){
    	//促销品
    	$("#editpromotionId").html("");
    	var promotionurl ="promotion/util/OutPromotion?storesId="+$("#eidtstoresId").val();
    	$.get(promotionurl, function(data) {
    		if (data.rows && data.rows.length > 0) {
    			$('#editpromotionId').append("<option></option>");
    			editPromotiondata = data.rows;
    			for (var i = 0; i < data.rows.length; i++) {
    				$('#editpromotionId').append("<option value='"+data.rows[i].promotionId+"'>"+data.rows[i].materialname+"</option>");
    	
    			}
    		}
    	});	
    })
    $('#editpromotionId').change(function(){
    	var promotionid=$("#editpromotionId").val();
    	$("#editprice").val('');
    	$("#editinvnum").val('');
    	$("#editnum").val('');
    	if(promotionId){
    		for(var i=0;i<editPromotiondata.length;i++){
    			if(promotionid == editPromotiondata[i].promotionId){
    				$("#editinvnum").val(editPromotiondata[i].num);
    				$("#editprice").val(editPromotiondata[i].price);
    			}
    		}
    	}
    })
    
    $('#searchorgid').change(function(){
    	var orgid= $("#searchorgid").val();
    	if(orgid){
    		 searchdata(orgid);
    	}
    })
});
/**
 * 验证数据
 * @param formId
 * @returns
 */
function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
        	organizationId: "required",
        	storesId: "required",
        	promotionId :"required",
        	num: {required: true, number: true}
        }
    });
    return validator;
}
function calculation(){
	var price =$('#price').val();
	var num =$('#num').val();
	var amt =price*num;
	$('#amt').val(amt);
}

function updatecalculation(){
	var price =$('#editprice').val();
	var num =$('#editnum').val();
	var amt =price*num;
	$('#editamt').val(amt);
}
/**
 * 编辑按钮
 */
function doEdit(){
    var rows = $("#promotionApplyTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	$("#editstoresId").html("");
    	$("#editpromotionId").html("");
    	$("#editpurId").html("");
    	$("#editorganizationId").val(rows[0].organizationId);
    	
    	//库房
		$("#editstoresId").html("");
		var storesurl="promotion/util/invstores.json?orgid="+rows[0].organizationId;
		$.get(storesurl, function(data) {
			if (data.rows && data.rows.length > 0) {
				$('#editstoresId').append("<option></option>"); 
				for (var i = 0; i < data.rows.length; i++) {
					$('#editstoresId').append("<option value='"+data.rows[i].storesId+"'>"+data.rows[i].storesname+"</option>");
				}
				$('#editstoresId').val(rows[0].storesId);
			}
		});
       //促销品
    	$("#editpromotionId").html("");
    	var promotionurl ="promotion/util/OutPromotion?storesId="+rows[0].storesId;
    	$.get(promotionurl, function(data) {
    		if (data.rows && data.rows.length > 0) {
    			$('#editpromotionId').append("<option></option>");
    			editPromotiondata = data.rows;
    			for (var i = 0; i < data.rows.length; i++) {
    				$('#editpromotionId').append("<option value='"+data.rows[i].promotionId+"'>"+data.rows[i].materialname+"</option>");
    			}
    			$('#editpromotionId').val(rows[0].promotionId);
    		}
    	});	
       
        $("#editprice").val(rows[0].price);
        $("#editnum").val(rows[0].num);
        $("#editamt").val(rows[0].amt);
        $("#editid").val(rows[0].id);
        $("#editPromotionApplyModal").modal("show")
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}

function doDel(){
    var rows = $("#promotionApplyTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
            $.post("promotion/inv/delApply",{"id":rows[0].id},function(){$("#promotionApplyTable").bootstrapTable("refresh");})
        });
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}
/**
 * 查询
 */
function doSearch(){
	var searchname =$("#searchname").val();
	searchname =encodeURI(searchname);
	searchname =encodeURI(searchname);
	var searchorgid =$("#searchorgid").val();
	var searchstoresid =$("#searchstoresid").val();
	var searchpurid =$("#searchpurid").val();
	var searchstates=$("#searchstates").val();
	var url="promotion/inv/list?type=1&orgid="+searchorgid+"&name="+searchname+"&states="+searchstates
	+"&purid="+searchpurid+"&stores="+searchstoresid;
	$("#promotionApplyTable").bootstrapTable("refresh",{
		'url':url
	});
}
function searchdata(value) {

	// 库房
	$("#searchstoresid").html("");
	var storesurl = "promotion/util/stores.json?orgid=" + value;
	$.get(storesurl, function(data) {
		if (data.rows && data.rows.length > 0) {
			$('#searchstoresid').append("<option></option>");
			for (var i = 0; i < data.rows.length; i++) {
				$('#searchstoresid').append(
						"<option value='" + data.rows[i].id + "'>"
								+ data.rows[i].name + "</option>");
			}
		}
	});
	// 采购方
	$("#searchpurid").html("");
	var pururl = "promotion/util/pur.json?orgid=" + value;
	$.get(pururl, function(data) {
		if (data.rows && data.rows.length > 0) {
			$('#searchpurid').append("<option></option>");
			for (var i = 0; i < data.rows.length; i++) {
				$('#searchpurid').append(
						"<option value='" + data.rows[i].id + "'>"
								+ data.rows[i].name + "</option>");

			}
		}
	});

}
function doAudit(){
	var rows = $("#promotionApplyTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1 && rows[0].states =='1') {
		$.messager.confirm("警告", "您确认要提交此记录吗?", function() {
			$.post("promotion/inv/OutupdateStates", {
				"id" : rows[0].id,
				"states":'2'
			}, function(data) {
				if(data.data=="200"){	
					$.messager.popup("提交成功!");
					$("#promotionApplyTable").bootstrapTable("refresh");
				}else{
					$.messager.alert("提示", "提交失敗!");
				}
			})
		});
	} else {
		$.messager.alert("提示", "请选择要編輯的记录!");
	}
}
