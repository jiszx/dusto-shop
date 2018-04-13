var areas = {};
$(function(){
    $('#costAdjustTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'cost/adjust/adjustlist',
        cache: false,
        toolbar:"#costAdjustTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"colName",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: false,
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
            title: '编号',
            align: 'left',
            visible : false
        }, {
            field: 'orgname',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'reginname',
            title: '大区',
            align: 'left'
        }, {
            field: 'costTypeid',
            title: '费用类型',
            align: 'left',
            formatter : function(value) {
				return getCostType(value + "");
			}
        }, {
            field: 'type',
            title: '调整类型',
            align: 'left',
            formatter : function(value) {
				return getAdjustType(value + "");
			}
        }, {
            field: 'adjustAmt',
            title: '调整金额',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        }, {
            field: 'createTs',
            title: '调整时间',
            align: 'left'
        }, {
            field: 'creater',
            title: '调整人',
            align: 'left'
        }, {
            field: 'reason',
            title: '调整原因',
            align: 'left'
        }, {
            field: 'auditname',
            title: '审批人',
            align: 'left'
        }, {
            field: 'auditTs',
            title: '审批时间',
            align: 'left'
        }, {
            field: 'states',
            title: '状态',
            align: 'left',
            formatter : function(value) {
				return getStatus(value + "");
			}
        }]
    });
    initAddForm();
    var addValidator = initValidate("#addCostAdjustForm");
    var editValidator = initValidate("#editForm");
    $("#editForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
            	 $("#editModal").modal("hide");
                 $("#costAdjustTable").bootstrapTable("refresh");
            }
        }
    });
    $("#addCostAdjustForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return addValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
        	if(responseText.errorCode != 0){
                $.messager.alert("新增信息失败",responseText.errorMessage);
            }else{
            	$("#addCostAdjustModal").modal("hide");
                $("#costAdjustTable").bootstrapTable("refresh");
            }
        }
    });
    $("#btn-detail").bind("click",doShow);
    $("#deleteButton").bind('click', deleteAdjust);
    $("#editButton").bind('click', editAdjust);
    $("#censorButton").bind('click', censorAdjust);
    $("#searchButton").bind('click', search);
});

function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
        	reason: "required",
        	provId: "required",
        	reginId:"required",
        	costTypeid :"required",
            adjustAmt: {required: true,minlength: 2},
            organizationId:"required"
        }
    });
    return validator;
}

function doShow(){
    var rows = $("#dictTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        showDetail("system/dict/detail?id="+rows[0].id);
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}

function deleteAdjust() {
	var rows = $("#costAdjustTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(rows[0].states!='1'){
			$.messager.alert("提示",'状态必须为编辑！');
			return;
		}
		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
			$.post("cost/adjust/delete.json", {"id" : rows[0].id}, function(res) {
				if(res.errorCode==0 && res.data==1){
					$("#costAdjustTable").bootstrapTable("refresh");
				}else{
					$.messager.alert('异常','删除失败');
				}
			})
		});
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}

function editAdjust(){
	var rows = $("#costAdjustTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(rows[0].states!='1'){
			$.messager.alert("提示",'状态必须为编辑！');
			return;
		}
		fillDetail(rows[0]);
		$("#editcostTypeid").val(getCostType(rows[0].costTypeid+""));
		$("#editModal").modal('show');
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function censorAdjust(){
	var rows = $("#costAdjustTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(rows[0].states!='1'){
			$.messager.alert("提示",'状态必须为编辑！');
		}else{
			$.post("cost/adjust/censor.json", {'id':rows[0].id}, function(res){
				if(res.errorCode != 0){
	                $.messager.alert("送审失败",res.errorMessage);
	            }else{
	            	$.messager.popup('送审成功');
	                $("#costAdjustTable").bootstrapTable("refresh");
	            }
			});
		}
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function fillDetail(Obj) {
	for ( var i in Obj) {
		if (Obj[i] == null) {
			continue;
		}
		$("#edit" + i).val(Obj[i] + "");
	}
}

function initAddForm(){
	$.get('Org/list.json', function(res){
		areas = res.data;
//		$("#selectregin").append(regionHtml);
	});
}

function search(){
	var org = $("#selectorg").val();
	var regin = $("#selectregin").val();
	var cost = $("#selectcost").val();
	var url = "cost/adjust/adjustlist.json?reginId="+regin+"&organizationId="+org+"&costTypeid="+cost;
	$("#costAdjustTable").bootstrapTable("refresh", {'url':url});
}

function changeRegin(pid){
	var reginHtml = '<option value="">全部</option>';
	$.each(areas, function(n, value){
		if(value && value.pid==pid){
			reginHtml += '<option value="'+value.id+'">'+value.name+'</option>';
		}
	});
	$("#selectregin").html(reginHtml);
}

function changeFormRegin(pid){
	var reginHtml = '<option value=""></option>';
	$.each(areas, function(n, value){
		if(value && value.pid==pid){
			reginHtml += '<option value="'+value.id+'">'+value.name+'</option>';
		}
	});
	$("#reginId").html(reginHtml);
}

function changeFormProv(pid){
	var provHtml = '<option value=""></option>';
	$.each(areas, function(n, value){
		if(value && value.pid==pid){
			provHtml += '<option value="'+value.id+'">'+value.name+'</option>';
		}
	});
	$("#provId").html(provHtml);
}