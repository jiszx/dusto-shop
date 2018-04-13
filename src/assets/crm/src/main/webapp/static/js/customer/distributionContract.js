var table=null;
var flag=0;
var $table = $('#contract-table');
$(function(){
    initTalbe();
    initLine();
    $('#add-bstb-row').click(function () {
    	flag=0;
        $('.add-bstb-box').addClass("add-bstb-box-open");
    });
    $('#btn-hide-bstb-add-box').click(function () {
        $('.add-bstb-box').removeClass("add-bstb-box-open");
    });
    $("#chooseCust_btn").bind("click",chooseCust);
    $("#agent-type").bind("change",realoadSelection);
    $("#addPaperForm").ajaxForm({
        target:'#btn-savePaper',   // target element(s) to be updated with server response
        clearForm:false,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
        	$(".borderColor").removeClass("borderColor");
        	var f = true;
        	function p(s) {
        	    return s < 10 ? '0' + s: s;
        	}
        	var nowDate = new Date();
        	var year = nowDate.getFullYear();
        	var month=nowDate.getMonth()+1;
        	var date=nowDate.getDate(); 
        	var nowtime=year+'/'+p(month)+"/"+p(date);
        	var timestamp = Date.parse(new Date(nowtime));
        	timestamp = timestamp / 1000;
        	var endtime = $("#endTime").val();
        	var endtimetemp = Date.parse(new Date(endtime));
        		endtimetemp = endtimetemp/1000;
        	if(endtimetemp<timestamp){
        		$("#endTime").parent().addClass("borderColor");
        		$.messager.alert("提示","合同结束时间必须大于当前时间!");
        		f = false;
        	}
            if($("#custId").val().length <=0){
            	$("#custId").parent().addClass("borderColor");
                //$.messager.alert("请选择客户");
            	f = false;
            }
            if($("#organizationId").val()==null||$("#organizationId").val()==undefined){
            	$("#organizationId").parent().addClass("borderColor");
            	f = false;
            }else if($("#organizationId").val().length<=0){
            	$("#organizationId").parent().addClass("borderColor");
            	f = false;
            }
            if($("#settleType").val().length <=0){
            	$("#settleType").parent().addClass("borderColor");
                f = false;
            }
            if($("#startTime").val().length <=0){
            	$("#startTime").parent().addClass("borderColor");
                f = false;
            }
            if($("#endTime").val().length <=0 || $("#endTime").val()<=$("#startTime").val()){
            	$("#endTime").parent().addClass("borderColor");
                f = false;
            }
            var ir = /^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/;
			if(ir.test($("#yearAmt").val())==false){
				$.messager.popup("年度进货目标只能输入大于0的数字，并且小于2位小数");
				 f = false;
			}
			
			if(ir.test($("#rebate").val())==false){
				$.messager.popup("年度返利只能输入大于0的数字，并且小于2位小数");
				 f = false;
			}if(ir.test($("#rebate").val())>=100){
				$.messager.popup("年度返利不能大于100%");
				 f = false;
			}
			$.each(formData, function(i, val){
					if(val.name=='lineData'){
						var lineData =$("#contract-table").bootstrapTable('getData');
						val.value=JSON.stringify(lineData);
					}
			});
			if(f ==false){
	           $("#btn-save").removeAttr("disabled");
	        }
            return f;
        },
//        success:function(responseText, statusText, xhr, $form){
//            if(responseText.errorCode != 0){
//                $.messager.alert("添加合同信息失败",responseText.errorMessage);
//            }else{
//                $.messager.popup("合同头信息保存成功");
//                $("#contractId").val(responseText.data);
//                $("#addAgentForm").submit();
//            }
//        }
        success:function(responseText, statusText, xhr, $form){
        	successHandler(responseText, statusText, xhr, $form)
        }
    });

    $("#addAgentForm").ajaxForm({
        //target:'#btn-add-agent',   // target element(s) to be updated with server response
        clearForm:false,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
        	var ir = /^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/;
        	$(".borderColor").removeClass("borderColor");
        	var f = true;
            if($("#contractId").val().length <=0){
                $.messager.alert("请选择客户");
                return false;
            }
            if($("#yAmt").val().length <=0 || ir.test($("#yAmt").val())==false  ){
            	$("#yAmt").parent().addClass("borderColor");
            	f = false;
            }
            if($("#yRatio").val().length <=0 || ir.test($("#yRatio").val())==false || $("#yRatio").val()>100 ){
            	$("#yRatio").parent().addClass("borderColor");
            	f = false;
            }
            if($("#agentId").val().length <=0){
            	$("#agentId").parent().addClass("borderColor");
            	f = false;
            }
            return f;
        },
        success:function(responseText, statusText, xhr, $form){
            if(responseText.errorCode != 0){
                $.messager.alert("添加合同信息失败",responseText.errorMessage);
                flag=0;
            }else{
                $.messager.popup("添加成功")
                $("#yAmt").val('');
                $("#yRatio").val('');
                $("#qAmt").val('0');
                $("#qRatio").val('0');
                if(flag==3){
                	window.location.href="customer/page.html";
                }else{                	
                	flag=1;
                }
                /*if(flag>0){
                	
                }else{                	
                	$table.bootstrapTable("refresh");
                }*/
                $table.bootstrapTable("refresh");
               // $("#editLinesModel").modal("hide");
            }
        }
    });
    $("#settleType").change(function(){
    	var settleType =$("#settleType").val();
    	$("#aPeriod").html('');
    	if(settleType=='2'){
    		$("#Period").css('display','block');
    		periods();
    	}else{
    		$("#Period").css('display','none');
    	}
    });
    $("#btn-add-agent").bind("click",doAddAgent);
    $("#btn-save").bind("click",doSave);
    $("#btn-submit").bind("click",doSubmit);
    $("#btn_choose_area").bind("click",doAddArea);
    initTree();
    
    
});
var treeObj=null;
function doAddArea(){
	var checks = treeObj.getCheckedNodes(true);
	if(checks || checks.length > 0){
		var tokenArray = new Array();
		for(var i=0;i<checks.length;i++){
			tokenArray.push({ value: checks[i].id, label: checks[i].name })
			$('#agentArea').tokenfield('setTokens',tokenArray);
		}
	}
	$("#chooseArea").modal("hide");
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
    	        url : "pub/area/list",
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
    var t = $("#areaTrea");
    t = $.fn.zTree.init(t, setting);
    treeObj = t;
}


var type=0;
function doAddAgent(){
	if($("#contractId").val().length <=0){
		type = 1;
		$("#addPaperForm").submit();
    }else{
    	$("#addAgentForm").submit();
    }
}
function successHandler(responseText, statusText, xhr, $form){
	if(responseText.errorCode != 0){
      $.messager.alert("添加合同信息失败",responseText.errorMessage);
  }else{
      $.messager.popup("合同头信息保存成功");
      $("#id").val("responseText.data")
      $("#contractId").val(responseText.data);
      /*if(type > 0 && responseText.data){
    	  $("#addAgentForm").submit();
      }*/
      window.location.href="customer/page.html";
  }
}
function realoadSelection(){
	var type = $(this).val();
	var data = $("#contract-table").bootstrapTable('getData');
	if (data.length > 0) {
		$.messager.confirm("警告", "变更代理类型后会删除原有的代理产品，您确认要变更代理类型吗?", function() {
			$("#contract-table").bootstrapTable("removeAll");
			addLine(type);
		});
	}else{
		addLine(type);
	}
}
function addLine(type){
	//获取对应的品牌信息
	$.get("customer/contract/bondsByModel?model="+type,function(res){
		if(res.rows && res.rows.length >0){
			for(var i=0;i<res.rows.length;i++){
				var conctractline = {
						id:i,
						agentType : type,
						agentId: res.rows[i].brand,
						name :res.rows[i].brand,
						yAmt :0,
						yRatio:0,
						qAmt:0,
						qRatio:0
					};
				$("#contract-table").bootstrapTable('insertRow', {
					index : 0,
					row : conctractline
				});
			}
		}
	});
}
function findParent(data, id){
	var result = '';
	$.each(data, function(i, val){
		if(val.id==id){
			result = val.name+' - ';
			return false;
		}
	});
	return result;
}

function initTalbe(){
    table = $('#customerTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'customer/list?status=3&custType='+custType,
        cache: false,
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"name",
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
            title: '编号',
            radio:true
        }, {
            field: 'name',
            title: '客户名称',
            align: 'left'
        }, {
            field: 'sapCustomerId',
            title: 'SAP客户编码',
            align: 'left'
        }, {
            field: 'custTypeName',
            title: '客户类型',
            align: 'left'
        }, {
            field: 'salesOrgName',
            title: '所属销售组织',
            align: 'left'
        }, {
            field: 'salesAreaName',
            title: '所在大区',
            align: 'left'
        }, {
            field: 'salesProvName',
            title: '所在省区',
            align: 'left'
        }, {
            field: 'positionName',
            title: '对应岗位',
            align: 'left'
        }, {
            field: 'salesman',
            title: '对应销售人员',
            align: 'left'
        }, {
            field: 'statesDesc',
            title: '客户状态',
            align: 'left'
        }]
    });
}

function chooseCust() {
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length > 0){
        var row = rows[0];
        $("#custId").val(row.id);
        $("#custName").val(row.name);
        $("#organizationId").val(row.salesOrgId);
        $("#chooseCustomer").modal("hide");
        $.get("customer/contract/getvirtualWarehouse?merchId="+row.id,function(res){
        	$("#virtualWarehouse").val(res.data);
        	//$.messager.popup(res.data);
        })
      //获取申请可选销售组织
//    	var orgurl="org/powerOrg"
//    	$.get(orgurl,function(data){
//    		if(data.rows && data.rows.length>0){
//    			$("#organizationId").html('');
//    			$("#organizationId").append("<option></option>");
//    			for(var i=0;i<data.rows.length;i++){
//    				$("#organizationId").append("<option value='"+data.rows[i].orgid+"'>"+data.rows[i].orgname+"</option>")
//    			}
//    		}
//    	})
    }
    
}

function initLine(){
    $table.bootstrapTable({
            url: 'customer/contract/lines',
            sidePagination:"server",
            showRefresh: true,
            onCheck:function(row,element){
            },
            clickToSelect: true,
            queryParams:function(params){
                params.id=$("#contractId").val();
                return params;
            },
            columns: [
                {
                    field: 'ck',
                    radio: true,
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: 'ID',
                    field: 'typeId',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle',
                    visible: false
                },
                {
                    title: '名称',
                    field: 'name',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle'
                },
                /*{
                    title: '代理类别',
                    field: 'agentType',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle'
                },*/
                {
                    title: '配送目标(元)',
                    field: 'yAmt',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle',
                    editable : {
                        type : 'text',
                        title : '年度进货目标(元)',
                        validate : function(value,s) {
                            value = $.trim(value);
                            var data = $('#contract-table').bootstrapTable('getData'), index = $(
                                this).parents('tr').data('index');
                            if (!value) {
                                return 'This field is required';
                            }
                            if (!/^0$|(^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$)/
                                    .test(value)) {
                                return '请输入正确的数值,并保留2位小数'
                            }
                            data[index].yAmt = value;
        					$('#contract-table').bootstrapTable("updateRow",{
        						index:index,
        						row:data[index]
        					})
                            return '';
                        }
                    }
                },
                {
                    title: '配送返利点(%)',
                    field: 'yRatio',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    editable : {
                        type : 'text',
                        title : '年度进货比例',
                        validate : function(value,s) {
                            value = $.trim(value);
                            var data = $('#contract-table').bootstrapTable('getData'), index = $(
                                this).parents('tr').data('index');
                            if (!value) {
                                return 'This field is required';
                            }
                            if (!/^([0-9]\d?(\.\d{1,2})?|0\.\d{1,2}|100)$/.test(value)) {
                                return '请输入小于100的数值,并保留2位小数'
                            }
                            data[index].yRatio = value;
        					$('#contract-table').bootstrapTable("updateRow",{
        						index:index,
        						row:data[index]
        					})
                            return '';
                        }
                    }
                    
                }, {
                    title: '时间',
                    field: 'testTime',
                    rowspan: 1,
                    align: 'center',
                    valign: 'middle',
                    visible: false,
                    editable: {
                        type: 'date',
                        title: '选择修改时间:'
                    }
                }]
        });
    $("#del-bstb-row").bind("click",doDelete);
    /*$("#del-bstb-row").bind("click",doDelete);
    $("#edit-bstb-row").bind("click",doEdit)*/
    var factoryurl ="factory/list.json?limit=100";
	$.get(factoryurl,function(res){
		$("#factoryId").html('');
	  	if(res.rows && res.rows.length>0){
	  	  $("#factoryId").append("<option></option>");
	  	  for(var i=0;i<res.rows.length;i++){
		  $("#factoryId").append("<option value='"+res.rows[i].id+"'>"+res.rows[i].name+"</option>")
	  	  }
	  	}
	});
}

function doSave(){
	/*if(flag==1){		
		window.location.href="customer/page.html";
	}else{
		flag=3;
		doAddAgent();
	}*/
	$("#btn-save").attr("disabled","true");
	$("#addPaperForm").submit();
}

function doSubmit(){
	var contractId =$("#contractId").val();
	if(contractId.length>0){
		$.post("customer/contract/updateContractStates",
	    		{"contractid":contractId,"states":"2"},
	    		function(data){
	    			if(data.data=="200"){
	    				$.messager.popup("提交审批成功!");
	    				doSave();
	    			}else{
	    				$.messager.alert("提示", "提交审批失败!");
	    			}
	    })
	}
}
function doEditSubmit(){
	$("#addUpaccountForm").submit();
	submitUpaccount();
}

function doAddSubmit(){
	$("#editUpaccountForm").submit();
	submitUpaccount();
}
$(document).ready(function(){
	$("#startTime").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
	$("#endTime").datetimepicker({
    	format: 'yyyy-mm-dd',
    	minView:2,
    	language:'zh-CN',
    	autoclose:true
    });
});
function doDelete(){
	var rows = $table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
          /*  $.post("customer/contract/delPaperLines",{"id":rows[0].id},function(){$table.bootstrapTable("refresh");})*/
        	$table.bootstrapTable('remove', {
    			field : 'id',
    			values :[ rows[0].id ]
    		});
        });
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}
