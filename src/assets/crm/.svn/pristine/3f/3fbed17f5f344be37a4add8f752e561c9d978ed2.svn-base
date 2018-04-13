$(function(){
    $('#salepolicyTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'salePolicy/policylist',
        //data:data,
        cache: false,
        toolbar:"#policyTool",
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
            field: 'ck',
            title: '',
            radio:true
        },{
            field: 'id',
            title: '编号',
            visible:false
        },{
            field: 'orgname',
            title: '销售组织',
            align: 'left'
        }, {
            field: 'typename',
            title: '政策类型',
            align: 'left'
        }, {
            field: 'description',
            title: '政策描述',
            align: 'left'
        }, {
            field: 'costTypeid',
            title: '费用类型',
            align: 'left',
            formatter : function(value) {
				return getcostType(value + "");
			}
        }, {
            field: 'cost',
            title: '成本中心',
            align: 'left'
        }, {
            field: 'bDate',
            title: '生效开始期间',
            align: 'left'
        }, {
            field: 'eDate',
            title: '生效结束期间',
            align: 'left'
        },{
            field: 'fanwei',
            title: '执行范围',
            align: 'left'
        },{
            field: 'amt',
            title: '申请金额',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        },{
            field: 'balanceAmt',
            title: '可用余额',
            align: 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
        },{
            field: 'states',
            title: '状态',
            align: 'left',
            formatter : function(value) {
				return getstates(value + "");
			}
        }]
    });
   $("#btn-details").bind("click",doDetails);
   $("#btn-audit").bind("click",doAudit);
   $("#btn-del").bind("click",doDel);
   $("#btn-edit").bind("click",doEdit);
   $("#btn-write-off").bind("click",btnWriteOff);
});

function doDetails(){
	var rows = $("#salepolicyTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href="salePolicy/policyDetails.html?id="+rows[0].id;
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}
function doEdit(){
	var rows = $("#salepolicyTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if(rows[0].states=='1'||rows[0].states=='4'){			
			window.location.href="salePolicy/policyEdit.html?id="+rows[0].id;
		}else{
			$.messager.alert("提示", "请选择编辑状态或者审批驳回的记录!");
		}
	} else {
		$.messager.alert("提示", "请选择记录!");
	}
}
function doDel(){
	var rows = $("#salepolicyTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if (rows[0].states == '1' ||rows[0].states=='4') {
			$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
				$.post("salePolicy/delpolicy", {"headerid":rows[0].id}, function(res){
					if(res.data==200){
						$.messager.popup("删除成功");
					}else{
						$.messager.alert("提示","删除失败");
					}
					$("#salepolicyTable").bootstrapTable("refresh");
				});
			});
		} else {
			$.messager.alert("提示", "编辑状态或者驳回的信息可删除!");
		}
	} else {
		$.messager.alert("提示", "请选择要审批的记录!");
	}
}
function doAudit(){
	var rows = $("#salepolicyTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		if (rows[0].states == 1 ||rows[o].states==4) {
			$.messager.confirm("警告", "您确认要提交此记录吗?", function() {
				$.post("salePolicy/submit", {"headerid":rows[0].id,"states":"2"}, function(res){
					if(res.data==200){
						$.messager.popup("提交成功");
					}else{
						$.messager.alert("提示","提交失败");
					}
					$("#salepolicyTable").bootstrapTable("refresh");
				});
			});
		} else {
			$.messager.alert("提示", "编辑状态或者驳回的信息可提交审批!");
		}
	} else {
		$.messager.alert("提示", "请选择的记录!");
	}
}
function btnWriteOff(){
	var rows = $("#salepolicyTable").bootstrapTable("getSelections");
	if (rows && rows.length == 1) {
		window.location.href="salePolicy/policyWriteOff.html?id="+rows[0].id;
	}else{
		$.messager.alert("提示", "请选择记录!");
	}
}

