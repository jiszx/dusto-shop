$(function() {
    $('#dictTable').bootstrapTable({
        method: 'get',
        classes: "table table-hover table-condensed",
        url: 'factory/list.json',
        cache: false,
        toolbar: "#dictTool",
        striped: true,
        pagination: true,
        searchOnEnterKey: true,
        sidePagination: "server",
        idField: "id",
        sortName: "colName",
        smartDisplay: true,
        pageSize: 10,
        pageList: ["10", "20", "50", "100"],
        search: false,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect: true,
        columns: [{
            field: 'ck',
            title: '编号',
            radio: true
        }, {
            field: 'id',
            title: 'id',
            visible : false
        }, {
            field: 'name',
            title: '工厂名称',
            align: 'left'
        }, {
            field: 'orgname',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'abbrName',
            title: '工厂简称',
            align: 'left'
        }, {
            field: 'canChoose',
            title: '别名',
            align: 'left'
        }, {
            field: 'city',
            title: '城市',
            align: 'left'
        }, {
            field: 'zipCode',
            title: '邮编',
            align: 'left'
        }]
    });

    $('#productTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'factory/materials.json',
        cache: false,
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"colName",
        smartDisplay:true,
        pageSize: 10,
        pageList:["10","20","50","100"],
        search: true,
        showColumns: true,
        showRefresh: true,
        clickToSelect: true,
        singleSelect:true,
        columns: [{
            field: 'sku',
            title: '别名',
            align: 'left',
            width:200
        }, {
            field: 'materialName',
            title: '名称',
            align: 'left',
            width:200
            	 
        }, {
            field: 'sapId',
            title: '物料编码',
            align: 'left'
        }, {
            field: 'brand',
            title: '品牌',
            align: 'left'
        }, {
            field: 'series',
            title: '系列',
            align: 'left'
        }, {
            field: 'pName',
            title: '品名',
            align: 'left'
        }, {
            field: 'oPackage',
            title: '包装形式',
            align: 'left'
        }, {
            field: 'specifications',
            title: '包装规格',
            align: 'left'
        }, {
            field: 'symbol',
            title: '符号',
            align: 'left'
        }, {
            field: 'provId',
            title: '省',
            align: 'left'
        }, {
            field: 'cityId',
            title: '市',
            align: 'left'
        }, {
            field: 'iodine',
            title: '碘添加',
            align: 'left'
        }, {
            field: 'unloose',
            title: '防松剂',
            align: 'left'
        }, {
            field: 'accessories',
            title: '辅料种类',
            align: 'left'
        }, {
            field: 'salt',
            title: '原盐',
            align: 'left'
        }, {
            field: 'grain',
            title: '颗粒',
            align: 'left'
        }, {
            field: 'flavor',
            title: '味形',
            align: 'left'
        }, {
            field: 'produceType',
            title: '产出形式',
            align: 'left'
        }]
    });
    initFormData();
    
    $("#showMaterials").bind('click',showMaterials);
    $("#editOrg").bind('click',editOrg);
    $("#search").bind('click', search);
    var editValidator = initValidate("#editOrgForm");
    $("#editOrgForm").ajaxForm({
		target : '#btn-edit-save', 
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			if(responseText.errorCode != 0){
                $.messager.alert("修改信息失败",responseText.errorMessage);
            }else{
            	$("#editOrgModal").modal("hide");
    			$("#dictTable").bootstrapTable("refresh")
            }
			$("#editOrgModal").modal("hide");
			$("#dictTable").bootstrapTable("refresh")
		}
	});
});

function showMaterials(){
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$('#productTable').bootstrapTable('refresh', {"url":'factory/materials.json?id='+rows[0].id});
		$("#showMaterialModal").modal('show');
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function editOrg(){
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		fill(rows[0]);
		$("#editOrgModal").modal('show');
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function search(){
	var name = $("#nameCondition").val();
	var city = $("#cityCondition").val();
	$("#dictTable").bootstrapTable("refresh",{'url':'factory/list.json?name='+name+'&city='+city});
}

function fill(Obj) {
	for ( var i in Obj) {
		if (Obj[i] == null) {
			continue;
		}
		$("#edit" + i).val(Obj[i] + "");
	}
}

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			editorgname:"required"
		}
	});
	return validator;
}

function initFormData(){
	$.get('Org/list.json', function(res){
		if(res.errorCode==0){
			var html = "";
			$.each(res.data	, function(n, value){
				if(value.levels=='2'){
					html += '<option value="'+value.id+'">'+getPname(res.data, value.pid)+value.name+'</option>';
				}
			});
			$("#editorganizationId").html(html);
		}
	});
}

function getPname(orgs, id){
	var result = "";
	$.each(orgs, function(i, val){
		if(val.id==id){
			result = val.name+'-';
			return false;
		}
	});
	return result;
}