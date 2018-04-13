var nodes = {};
var treeObj = null;
var hasLimit = true;
$(function() {
	$('#dictTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		url : 'product/list.json',
		cache : false,
		toolbar : "#dictTool",
		striped : true,
		showExport : true,
		exportDataType : 'all', // basic, all, selected
		exportTypes : [ 'csv', 'txt', 'excel' ],
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "colName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : false,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '编号',
			radio : true
		}, {
			field : 'nonexistentimage',
			title : '图片',
			align : 'left',
			formatter:function(val, row){
				var containerId = "image"+row.sapId;
            	var container = $('<div>').attr("id",containerId);
            	var html = '';
            	$.get('product/attachment.json?id='+row.sapId, function(res){
            		if(res.rows && res.rows.length>0){
            			html += '<img src="'+window.IMAGE_BASEURI+res.rows[0].objectName+res.rows[0].fileName + '" style="max-width:100px">';
            			container.html(html);
    	        		if($('#'+containerId)[0]){
    	        			$('#'+containerId).html(html);
    	        		}else{
    	        			return container.prop("outerHTML");
    	        		}
            		}
            	});
            	return container.prop("outerHTML");
			}
		}, {
			field : 'sku',
			title : '名称',
			align : 'left'
		}, {
			field : 'sapId',
			title : '物料编码',
			align : 'left'
		}, {
			field : 'factoryName',
			title : '工厂名称',
			align : 'left'
		}, {
			field : 'orgname',
			title : '销售组织',
			align : 'left'
		}, {
			field : 'zipcode',
			title : '邮编',
			align : 'left'
		}, {
			field : 'category',
			title : '品类',
			align : 'left',
			visible : false
		}, {
			field : 'brand',
			title : '品牌',
			align : 'left'
		}, {
			field : 'series',
			title : '系列',
			align : 'left'
		}, {
			field : 'pName',
			title : '品名',
			align : 'left'
		}, {
			field : 'iPackage',
			title : '内包装',
			align : 'left'
		}, {
			field : 'oPackage',
			title : '包装形式',
			align : 'left'
		}, {
			field : 'specifications',
			title : '包装规格',
			align : 'left'
		}, {
			field : 'symbol',
			title : '符号',
			align : 'left',
			visible : false
		}, {
			field : 'provId',
			title : '省',
			align : 'left',
			visible : false
		}, {
			field : 'cityId',
			title : '市',
			align : 'left',
			visible : false
		}, {
			field : 'iodine',
			title : '碘添加',
			align : 'left'
		}, {
			field : 'unloose',
			title : '防松剂',
			align : 'left',
			visible : false
		}, {
			field : 'accessories',
			title : '辅料种类',
			align : 'left',
			visible : false
		}, {
			field : 'salt',
			title : '原盐',
			align : 'left'
		}, {
			field : 'grain',
			title : '颗粒',
			align : 'left',
			visible : false
		}, {
			field : 'flavor',
			title : '味形',
			align : 'left',
			visible : false
		}, {
			field : 'produceType',
			title : '产出形式',
			align : 'left'
		}, {
			field : 'basePrice',
			title : '标准价',
			align : 'left'
		} ]
	});
	initTree();
	
	var addValidator = initValidate("#addDictForm");
	var addValidator2 = initValidate("#addDictForm2");
	var editValidator = initValidate("#editDictForm");
	$("#editDictForm").ajaxForm({
		target : '#btn-edit-save', // target element(s) to be updated with
		// server response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return editValidator.valid();
		},
		success : function(responseText, statusText, xhr, $form) {
			$("#editDictModal").modal("hide");
			$("#dictTable").bootstrapTable("refresh")
		}
	});
	$("#addDictForm").ajaxForm({
		target : '#btn-add', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addValidator.valid();
		},
		success : function(res, statusText, xhr, $form) {
			if (res.data && res.data.length > 1) {
				addPicture();
			} else {
				$.messager.alert("提示", res.errorMessage? res.errorMessage:"上传失败");
			}
		}
	});
	$("#addDictForm2").ajaxForm({
		target : '#btn-add', // target element(s) to be updated with server
		// response
		clearForm : true,
		dataType : 'json',
		beforeSubmit : function(formData, jqForm, options) {
			return addValidator2.valid();
		},
		success : function(res, statusText, xhr, $form) {
			if (res.data && res.data.length > 1) {
				addPicture();
			} else {
				$.messager.alert("提示", res.errorMessage? res.errorMessage:"上传失败");
			}
		}
	});
	$(".btn-edit").bind("click", doEdit);
	$(".btn-del").bind("click", doDel);
	$("#showDetail").bind("click", doShow);
	$("#searchBtn").bind('click', searchMaterial);
	$("#addPicButton").bind('click', addPicture);
	$("#classifyButton").bind('click', showClassify);
	$("#addClassifyButton").bind('click', addClassify);
	$("#filterNull").bind('click', filterNull);
	$("#filterProduct").bind('click',filterProduct);
	$("#exportBtn").bind("click", doExport);
	$("#showAdjust").bind("click", showAdjust);
	
	initSearchbar();
	
//	$.getJSON("product/categroy/list",{},function(data){
//		if(data.errorCode == "0"){
//			var items = data.data;
//			for(var ind=0;ind<items.length;ind++){
//				$("#selectcategory").append("<option value='"+items[ind].id+"'>"+items[ind].name+"</option>")
//			}
//		}else{
//			$.messager.popup("提示",data.errorMessage)
//		}
//	});
//	$("#selectcategory").change(function () {
//		var id = $(this).val();
//		if(id.length<1){
//			$("#selectbrand>option:gt(0)").remove();
//			$("#selectseries>option:gt(0)").remove();
//			return;
//		}
//		$.getJSON("product/categroy/list",{"id":id},function(data){
//			if(data.errorCode == "0"){
//				var items = data.data;
//				$("#selectbrand>option:gt(0)").remove();
//				$("#selectseries>option:gt(0)").remove();
//				for(var ind=0;ind<items.length;ind++){
//					$("#selectbrand").append("<option value='"+items[ind].id+"'>"+items[ind].name+"</option>")
//				}
//			}else{
//				$.messager.popup("查询条件加载错误")
//			}
//		});
//	});
//	$("#selectbrand").change(function () {
//		var id = $(this).val();
//		if(id.length<1){
//			$("#selectbrand>option:gt(0)").remove();
//			$("#selectseries>option:gt(0)").remove();
//			return;
//		}
//		$.getJSON("product/categroy/list",{"id":id},function(data){
//			if(data.errorCode == "0"){
//				var items = data.data;
//				$("#selectseries>option:gt(0)").remove();
//				for(var ind=0;ind<items.length;ind++){
//					$("#selectseries").append("<option value='"+items[ind].id+"'>"+items[ind].name+"</option>")
//				}
//			}else{
//				$.messager.popup("查询条件加载错误")
//			}
//		});
//	});
});

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			colName : "required",
			chooseVal : "required",
			showText : {
				required : true,
				minlength : 2
			},
			orders : {
				required : true,
				number : 5
			}
		}
	});
	return validator;
}
/**
 * 编辑按钮
 */
function doEdit() {
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		autoEdit(rows[0]);
		$("#editDictModal").modal("show")
	} else {
		$.messager.alert("提示", "请选择要编辑的记录!");
	}
}
function doShow() {
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$("#detailModal").find(':input').not(':button, :submit, :reset').val('');
		$.get("product/attachment.json?id=" + rows[0].sapId,
			function(res) {
				fillDetail(rows[0]);
				var pics = res.rows;
				var html = '<p style="border-bottom:1px solid hsla(255, 0%, 90%, 1); margin-bottom: 20px;">产品图片:</p>';
				for (var i = 0; i < pics.length; i++) {
					html += '<a href="' + window.IMAGE_BASEURI+pics[i].objectName+pics[i].fileName
							+ '" target="_blank"><img src="'
							+ window.IMAGE_BASEURI+pics[i].objectName+pics[i].fileName + '"><span>'
							+ pics[i].attachmentName
							+ '</span></a>'
				}
				$("#imgList").html(html);
				$("#detailModal").modal('show');
			});

	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function searchMaterial() {
	var selectCategory = $("#selectcategory").val();
	var selectbrand = $("#selectbrand").val();
	var selectvicebrand = $("#selectvicebrand").val();
	var selectSeries = $("#selectseries").val();
	var selectSku = $("#selectsku").val();
	var selectIpackage = $("#selectipackage").val();
	var selectSapid = $("#selectsapid").val();
	var selectFactoryid = $("#selectfactory").val();
	var url = 'product/list.json?brand='
			+ selectbrand + '&series=' + selectSeries+'&sku='+selectSku+'&iPackage='+selectIpackage
			+'&sapId='+selectSapid+'&factoryid='+selectFactoryid+'&attribute1='+selectvicebrand;
	$("#dictTable").bootstrapTable("refresh", {
		'url' : url
	});
}

function getMaterialName(id){
	var name = "";
	$.each(nodes, function(i, val){
		if(val.id==id){
			name = val.name;
			return false;
		}
	});
	return name;
}

function addPicture() {
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.get("product/attachment.json?id=" + rows[0].sapId, function(res){
			var licenseHtml = "";
			var productHtml = "";
			if(res.rows){
				$.each(res.rows, function(i, val){
					if(val.attachmentType=="1"){
						licenseHtml += '<div class="col-md-3"><button type="button" id="'+val.id+'" onClick="deletePic(this.id)" class="close" data-dismiss="alert" aria-label="Close"><i class="icon icon-remove" style="font-size: 14px"></i></button>'+
						'<a href="'+window.IMAGE_BASEURI+val.objectName+val.fileName+'" target="_blank" class="thumbnail"><img src="'+window.IMAGE_BASEURI+val.objectName+val.fileName+'" style="height: 100px;"></a></div>'
					}else if(val.attachmentType=="2"){
						productHtml += '<div class="col-md-3"><button type="button" id="'+val.id+'" onClick="deletePic(this.id)" class="close" data-dismiss="alert" aria-label="Close"><i class="icon icon-remove" style="font-size: 14px"></i></button>'+
						'<a href="'+window.IMAGE_BASEURI+val.objectName+val.fileName+'" target="_blank" class="thumbnail"><img src="'+window.IMAGE_BASEURI+val.objectName+val.fileName+'" style="height: 100px;"></a></div>'
					}
				});
				$("#license").html(licenseHtml);
				$("#productImg").html(productHtml);
			}
		});
		$("#addid").val(rows[0].sapId);
		$("#addid2").val(rows[0].sapId);
		$("#addDictModal").modal('show');
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function deletePic(id){
	$.post("product/attachment/delete/"+id+".json", function(res){
		addPicture();
	});
}

function doDel() {
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
			$.post("system/dict/del", {
				"id" : rows[0].id
			}, function() {
				$("#dictTable").bootstrapTable("refresh");
			})
		});
	} else {
		$.messager.alert("提示", "请选择要删除的记录!");
	}
}

function showClassify() {
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(!hasLimit){
			$.messager.alert("提示", "无权限");
			return;
		}
		treeObj.cancelSelectedNode();
		$("#classifyModal").modal('show');
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function filterNull(){
	var options = $("#dictTable").bootstrapTable("getOptions");
	var url = options.url;
	if(url.indexOf("isFilter=")==-1){
		if(url.indexOf("?")==-1){
			url += '?isFilter=1'
		}else{
			url += '&isFilter=1';
		}
	}
	$("#dictTable").bootstrapTable("refresh", {'url' : url});
}

function filterProduct(){
	var rows = $("#dictTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		var options = $("#dictTable").bootstrapTable("getOptions");
		var url = options.url;
		if(url.indexOf("sapId=")==-1){
			if(url.indexOf("?")==-1){
				url = url+'?sapId='+rows[0].sapId;
			}else{
				url = url+'&sapId='+rows[0].sapId;
			}
		}
		$("#dictTable").bootstrapTable("refresh", {'url' : url});
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}

function addClassify() {
	var rows = $("#dictTable").bootstrapTable("getSelections");
	var nodes = treeObj.getCheckedNodes(true);
	var node = null;
	for (var i = nodes.length-1; i >=0 ; i--) {
		if (nodes[i].level == 2) {
			node = nodes[i];
		}
	}
	if (node) {
		var brand = node.getParentNode();
		var category = brand.getParentNode();
		$.post("product/material/edit.json", {
			'sapId' : rows[0].sapId,
			'brand' : brand.id,
			'category' : category.id,
			'series' : node.id
		}, function(res) {
			$("#classifyModal").modal('hide');
			$("#dictTable").bootstrapTable("refresh");
		});
	} else {
		$.messager.alert("提示", "请勾选系列");
	}
}

function fillDetail(Obj) {
	for ( var i in Obj) {
		if (Obj[i] == null) {
			continue;
		}
		$("#show" + i).val(Obj[i] + "");
	}
}

function filter(node){
	return node.level>0;
}

function initTree() {
	var setting = {
		check : {
			enable : true,
			chkStyle : "radio",
			radioType : "all"
		},
		view : {
			dblClickExpand : false,
			showLine : true,
			selectedMulti : false
		},
		async : {
			dataType : "json",
			enable : true,
			type : "post",
			url : "product/categroy/categroys.json",
			dataFilter : function(treeId, parentNode, responseData) {
				if(responseData.errorMessage){
					hasLimit = false;
				}
				nodes = responseData.data;
				return responseData.data;
			}
		},
		data : {
			key : {
				name : "name"
			},
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pid",
				rootPId : "0"
			}
		}
	};
	var t = $("#groupTree");
	t = $.fn.zTree.init(t, setting);
	treeObj = t;
}

function initSearchbar(){
	$.get('product/series.json?limit=100', function(res){
		if(!res.rows){
			return;
		}
		var html = '<option value="">全部</option>';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.series+'">'+val.series+'</option>';
		});
		$("#selectseries").html(html);
	});
	$.get('product/brand.json?limit=100', function(res){
		if(!res.rows){
			return;
		}
		var html = '<option value="">全部</option>';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.brand+'">'+val.brand+'</option>';
		});
		$("#selectbrand").html(html);
	});
	$.get('product/vicebrand.json?limit=100', function(res){
		if(!res.rows){
			return;
		}
		var html = '<option value="">全部</option>';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.brand+'">'+val.brand+'</option>';
		});
		$("#selectvicebrand").html(html);
	});
	$.get('product/iPackage.json?limit=100', function(res){
		if(!res.rows){
			return;
		}
		var html = '<option value="">全部</option>';
		$.each(res.rows, function(i, val){
			html += '<option value="'+val.iPackage+'">'+val.iPackage+'</option>';
		});
		$("#selectipackage").html(html);
	});
	$.get('factory/list.json?limit=100', function(res){
		var html = '<option value="">全部</option>';
		if(res.rows && res.rows.length>0){
			$.each(res.rows, function(i, val){
				html += '<option value="'+val.id+'">'+val.name+'</option>';
			});
		}
		$("#selectfactory").html(html);
	});
}

function doExport(){
	$("#generateFile").html("正在生成excel，请耐心等待...");
	$("#exportModal").modal();
	$.ajax({
		url:'monitor/export/generate.json?key=materialDetail',
		timeout:300000,
		error:function(){
		},
		success:function(res, textStatus, XMLHttpRequest){
		    if(res.data.length<2){
		    	$.messager.alert("提示","数据太多，无法导出");
		    }else{
		    	$("#generateFile").html('<a href="'+window.IMAGE_BASEURI+res.data+'" target="_blank">生成成功，点击下载</a>')
		    }
		} 
	});
}

function showAdjust(){
	window.location.href="product/price/adjust.html";
}