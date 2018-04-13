var packageData ;
var editpackageData;
var treeObj=null;
var edittreeObj=null;
$(function(){
	 $('.tokenfield').tokenfield();
     $('#combinationApplyTable').bootstrapTable({
    	url :'combinationApply/applyList?headerId='+$("#id").val(),
    	method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		toolbar : "#combinationApplyTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "code",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		onCheck:function(row){
            if(row.states=='3'){
            	$("#btn-merch").removeClass("hide");
            }else{
            	$("#btn-merch").addClass("hide");
            }
        },
        onUncheck:function(){
        	$("#btn-merch").addClass("hide");
        },
        columns: [{
            field: '',
            title: '',
            radio:true
        },{
            field: 'orgname',
            title: '所属销售组织'
        },{
            field: 'name',
            title: '套餐编码'
        },{
            field: 'code',
            title: '套餐名称',
            align: 'left'
        },{
            field: 'modelType',
            title: '生意模式',
            align: 'left',
            formatter:function(value){
            	return getModelType(value + "");
            }
        },{
            field: 'price',
            title: '价格',
            align: 'left',
            visible:false,
        },{
            field: 'weight',
            title: '规格',
            align: 'left'
        },{
            field: 'creater',
            title: '创建人',
            align: 'left'
        },{
            field: 'states',
            title: '状态',
            align: 'left',
            formatter:function(value){
            	return getStates(value + "");
            }
        }]
    });
     //初始化套餐选择chosen
     $('#packageId').chosen({
 		no_results_text : "没有找到",
 		allow_single_de : true,
 		search_contains: true,
 		width:"100%"
 	});
     
     $('#editpackageId').chosen({
  		no_results_text : "没有找到",
  		allow_single_de : true,
  		search_contains: true,
  		width:"100%"
  	});
     
     //新增-生意模式切换
     $("#modelType").change(function(){
    	 $("#packageId").empty();
    	 $("#price").val('');
		 $("#weight").val('');
		 $("#bDate").val('');
		 $("#eDate").val('');
    	 $("#packageId").trigger("chosen:updated");
    	 var modelType =$("#modelType").val();
    	 $.get("combinationApply/package?modelType="+modelType+"&orgid="+$("#organizationId").val(),function(res){
    		 if(res.rows && res.rows.length >0){
    			 packageData =res.rows;
    			 $("#packageId").append("<option></option>")
    			 for(var i=0;i<res.rows.length;i++){
    				 $("#packageId").append("<option value="+res.rows[i].id+">"+res.rows[i].code+res.rows[i].name+"</option>")
    			 }
    			 $("#packageId").trigger("chosen:updated");
    		 }else{
    			 $.messager.popup("没有可用的套餐：一个套餐在一个销售组织下只能申请一次，如果需要添加客户请在套餐申请中添加客户信息");
    		 }
    	 })
     })
     
     //套餐选择切换
     $("#packageId").change(function(){
    	 $("#price").val('');
		 $("#weight").val('');
		 $("#bDate").val('');
		 $("#eDate").val('');
    	 var packageId = $("#packageId").val();
    	 for(var i=0;i<packageData.length;i++){
    		 if(packageData[i].id==packageId){
    			 $("#price").val(packageData[i].price);
    			 $("#weight").val(packageData[i].weight);
    			 $("#bDate").val(packageData[i].bDate);
    			 $("#eDate").val(packageData[i].eDate);
    		 }
    	 }
     })
     
     //修改-生意模式切换
     $("#editmodelType").change(function(){
    	 $("#editpackageId").empty();
    	 $("#editprice").val('');
		 $("#editweight").val('');
		 $("#editbDate").val('');
		 $("#editeDate").val('');
    	 $("#editpackageId").trigger("chosen:updated");
    	 var modelType =$("#editmodelType").val();
    	 $.get("combinationApply/package?modelType="+modelType,function(res){
    		 if(res.rows && res.rows.length >0){
    			 editpackageData =res.rows;
    			 $("#editpackageId").append("<option></option>")
    			 for(var i=0;i<res.rows.length;i++){
    				 $("#editpackageId").append("<option value="+res.rows[0].id+">"+res.rows[0].code+res.rows[0].name+"</option>")
    			 }
    			 $("#editpackageId").trigger("chosen:updated");
    		 }
    	 })
     })
     //修改-套餐切换
     $("#editpackageId").change(function(){
    	 $("#editprice").val('');
		 $("#editweight").val('');
		 $("#editbDate").val('');
		 $("#editeDate").val('');
    	 var packageId = $("#editpackageId").val();
    	 for(var i=0;i<editpackageData.length;i++){
    		 if(editpackageData[i].id==packageId){
    			 $("#editprice").val(editpackageData[i].price);
    			 $("#editweight").val(editpackageData[i].weight);
    			 $("#editbDate").val(editpackageData[i].bDate);
    			 $("#editeDate").val(editpackageData[i].eDate);
    		 }
    	 }
     })
     //新增/修改
     var addValidator = initValidate("#addApplyForm");
     $("#addApplyForm").ajaxForm({
 		target : '#btn-add', // target element(s) to be updated with server
 		// response
 		clearForm : true,
 		dataType : 'json',
 		beforeSubmit : function(formData, jqForm, options) {
 			return addValidator.valid();
 		},
 		success : function(responseText, statusText, xhr, $form) {
 			if(responseText.errorCode != 0){
                 $.messager.popup("新增录入信息失败",responseText.errorMessage);
             }else{
            	 $("#combinationApplyTable").bootstrapTable("refresh");
            	 $("#addApplyModal").modal("hide");
             }
 		}
 	});
    $('#addApplyModal').on('hide.bs.modal', function() {
		$('#addApplyForm')[0].reset();
	})
	//按钮绑定
	$("#addRange").bind("click",doAddRange);
    $("#editaddRange").bind("click",doEditAddRange);
    $("#btn_choose_range").bind("click",doAddArea);
    $("#btn_edit_range").bind("click",doEditArea);
    
    //删除
    $("#btn-del").click(function(){
    	var row =$('#combinationApplyTable').bootstrapTable('getSelections');
    	if(row && row.length ==1 && (row[0].states=='1' || row[0].states=='4')){
    		$.post("combinationApply/del?id="+row[0].id,function(res){
    			if(res.errorCode !=0){
    				$.messager.popup("刪除出错"); 
    			}
    			$('#combinationApplyTable').bootstrapTable('refresh');
    		});
    	}else{
    		$.messager.popup("请选择编辑或者审批驳回的记录"); 
    	}
    });
    
    //详情
    $("#btn-details").click(function(){
    	var row =$('#combinationApplyTable').bootstrapTable('getSelections');
    	if(row && row.length ==1 ){
    		autoShow(row[0]);
    		initpolicyarea(row[0].id,"show");
			$("#showApplyModal").modal("show");
    	}else{
    		$.messager.popup("请选择记录"); 
    	}
    });
    
    //修改
    $("#btn-edit").click(function(){
    	var row =$('#combinationApplyTable').bootstrapTable('getSelections');
    	if(row && row.length ==1 && (row[0].states=='1' || row[0].states=='4')){
    		$.get("combinationApply/package?modelType="+row[0].modelType,function(res){
       		 if(res.rows && res.rows.length >0){
       			 editpackageData =res.rows;
       			 $("#editpackageId").append("<option></option>")
       			 for(var i=0;i<res.rows.length;i++){
       				 $("#editpackageId").append("<option value="+res.rows[0].id+">"+res.rows[0].code+res.rows[0].name+"</option>")
       			 }
       			 autoEdit(row[0]);
       			 $("#editpackageId").trigger("chosen:updated");
       		 }
       	 	})
    		autoEdit(row[0]);
    		initpolicyarea(row[0].id,"edit");
			$("#editApplyModal").modal("show");
    	}else{
    		$.messager.popup("请选择编辑或者审批驳回的记录"); 
    	}
    });
    //审批
    $("#btn-audit").click(function(){
    	var row =$('#combinationApplyTable').bootstrapTable('getSelections');
    	if(row && row.length ==1 && (row[0].states=='1' || row[0].states=='4')){
    		$.post("combinationApply/audit?applyId="+row[0].id,function(res){
    			if(res.errorCode !=0){
    				$.messager.popup("提交审批出错"); 
    			}
    			$('#combinationApplyTable').bootstrapTable('refresh');
    		});
    	}else{
    		$.messager.popup("请选择编辑或者审批驳回的记录"); 
    	}
    });
    initMerch();
    var applyId =0;
    $("#btn-merch").click(function(){
    	var row =$('#combinationApplyTable').bootstrapTable('getSelections');
    	if(row && row.length ==1 && row[0].states=='3' ){
    		applyId = row[0].id;//套餐申请id;
    		$('#merchTable').bootstrapTable('refresh',{
    			'url':'combinationApply/CombinationMerchs?id='+row[0].id
    		});
    		$("#merchModal").modal("show");
    	}else{
    		$.messager.popup("请选择审批通过的记录");
    	}
    });
    
    $("#del-merch-row").click(function(){
    	var row =$('#merchTable').bootstrapTable('getSelections');
    	if(row){
		 $.post("combinationApply/delCombinationMerch", {
				"lineId" : applyId,
				"merchCustId" : row[0].merchCustId,
				"id" : row[0].productId
			}, function(res) {
				if(res.data=="S"){
					$.messager.popup("移除客户成功");
				}else{
					$.messager.popup("移除客户失败");
				}
				$('#merchTable').bootstrapTable('refresh');
			});
    	}else{
    		$.messager.popup("请选择客户记录");
    	}
    });
    $("#add-merch-row").click(function(){
    	$("#addmerchTable").bootstrapTable('refresh',{
    		'url':'combinationApply/selectMerchs?id='+applyId+"&type=1"
    	});
    	$("#addmerchModal").modal('show');
    });
    
    $("#btn_add_merch").click(function(){
    	var rows =$('#addmerchTable').bootstrapTable('getSelections');
    	var merchids = [];
		if(rows && rows.length<1){
			$.messager.popup("请选择记录");
			return;
		}
		$.each(rows, function(i, val){
			merchids.push(val.merchCustId);
		});
		$.post("combinationApply/addCombinationMerch",{
			"merchids":merchids.toString(),
			"applyId":applyId
		},function(res){
			if(res.data=="S"){
				$.messager.popup("添加客户成功");
			}else{
				$.messager.popup("添加客户失败");
			}
			$("#addmerchModal").modal('hide');
			$('#merchTable').bootstrapTable('refresh');
		})
    })
})

function initValidate(formId) {
	var validator = $(formId).validate({
		rules : {
			packageId:"required",
			range:"required",
			modelType:"required",
			organizationId:"required"
		},
		ignore: ""
	});
	return validator;
}
function doAddRange(){
	var orgid = $("#organizationId").val();
	if(!orgid){
		$.messager.popup("请选择销售组织");
		return ;
	}
	initTree();
	$("#rangeAreaModel").modal("show");
}

function doEditAddRange(){
	var orgid = $("#editorganizationId").val();
	if(!orgid){
		$.messager.popup("请选择销售组织");
		return ;
	}
	editinitTree();
	$("#editrangeAreaModel").modal("show");
}
function doAddArea(){
	$('#range').val('');
	var checks = treeObj.getCheckedNodes(true);
	if(checks || checks.length > 0){
		var tokenArray = new Array();
		for(var i=0;i<checks.length;i++){
			tokenArray.push({ value: checks[i].id, label: checks[i].name })
			$('#range').tokenfield('setTokens',tokenArray);
		}
	}
	$("#rangeAreaModel").modal("hide");
}
function doEditArea(){
	$('#editrange').val('');
	var checks = edittreeObj.getCheckedNodes(true);
	if(checks || checks.length > 0){
		var tokenArray = new Array();
		for(var i=0;i<checks.length;i++){
			tokenArray.push({ value: checks[i].id, label: checks[i].name })
			$('#editrange').tokenfield('setTokens',tokenArray);
		}
	}
	$("#editrangeAreaModel").modal("hide");
}
function initTree(){
	var setting = {
    		check : {
    	        enable : true,
    	        chkboxType: { "Y": "", "N": "" }
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
    	        url : "salePolicy/areaList?orgid="+$("#organizationId").val(),
    	        dataFilter: function(treeId, parentNode, responseData){
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
    var t = $("#rangeTrea");
    t = $.fn.zTree.init(t, setting);
    treeObj = t;
}
function editinitTree(){
	var setting = {
    		check : {
    	        enable : true,
    	        chkboxType: { "Y": "", "N": "" }
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
    	        url : "salePolicy/areaList?orgid="+$("#editorganizationId").val(),
    	        dataFilter: function(treeId, parentNode, responseData){
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
    var t = $("#editrangeTrea");
    t = $.fn.zTree.init(t, setting);
    edittreeObj = t;
}
function autoShow(Obj){
    for(var i in Obj){
        if(Obj[i]==null){
            continue;
        }
        $("#show"+i).val(Obj[i]+"");
    }
}
function initpolicyarea(id,obj){
	var tokenArray = new Array();
	//获取组织数据
	var areaurl="combinationApply/rangeArea?applyId="+id;
	$.get(areaurl,function(data){
		if(data.rows && data.rows.length>0){
			for(var i=0;i<data.rows.length;i++){
				tokenArray.push({ value: data.rows[i].id, label: data.rows[i].name })
			}
			$('#'+obj+"range").tokenfield('setTokens',tokenArray);
		}
	})
}
function initMerch(){
	$('#merchTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		//toolbar : "#upAccountTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "code",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
        columns : [ {
            field: '',
            title: '',
            radio:true
        },{
            field : 'merchName',
            title : '客户名称',
            align : 'left',
            sortable :true,
            searchable:true,
            searchFormatter:true
        }, {
            field : 'sapCustomerId',
            title : 'SAP编码',
            align : 'left',
            sortable :true
        }, {
            field : 'reginName',
            title : '大区',
            align : 'left',
            sortable :true
        },{
            field : 'provName',
            title : '省区',
            align : 'left',
            sortable :true
        },{
            field : 'price',
            title : '价格',
            align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
        }  ]
    });
	
	$('#addmerchTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		//toolbar : "#upAccountTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "code",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : false,
        columns : [ {
            field: '',
            title: '',
            checkbox : true
        },{
            field : 'merchName',
            title : '客户名称',
            align : 'left',
            sortable :true,
            searchable:true,
            searchFormatter:true
        }, {
            field : 'sapCustomerId',
            title : 'SAP编码',
            align : 'left',
            sortable :true
        }, {
            field : 'reginName',
            title : '大区',
            align : 'left',
            sortable :true
        },{
            field : 'provName',
            title : '省区',
            align : 'left',
            sortable :true
        },{
            field : 'price',
            title : '价格',
            align : 'left',
            formatter:function(value){
            	return value.formatMoney();
            }
        }  ]
    });
}