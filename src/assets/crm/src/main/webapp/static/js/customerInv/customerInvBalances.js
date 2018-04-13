$(function() {
	$('#invBalanceTable').bootstrapTable({
		url : 'customerInvSearch/balancesList',
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		// toolbar : "#upAccountTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "payName",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : '',
			title : '',
			radio : true
		}, {
			field : 'id',
			title : '编号',
			visible : false
		}, {
			field : 'orgname',
			title : '所属销售组织',
			align : 'left'
		}, {
			field : 'custname',
			title : '客户名称',
			align : 'left'
		}, {
			field : 'custname',
			title : '客户编码',
			align : 'left'
		}, {
			field : 'sku',
			title : '物料',
			align : 'left'
		}, {
			field : 'materialId',
			title : '物料编码',
			align : 'left'
		}, {
			field : 'amounts',
			title : '箱内数量',
			align : 'left'
		}, {
			field : 'rdcname',
			title : '虚拟仓',
			align : 'left'
		}, {
			field : 'ytd',
			title : '期初',
			align : 'left'
		}, {
			field : 'dNum',
			title : '本期增加',
			align : 'left'
		}, {
			field : 'cNum',
			title : '本期减少',
			align : 'left'
		}, {
			field : 'ptd',
			title : '期末',
			align : 'left'
		}, {
			field : 'period',
			title : '期间',
			align : 'left'
		} ]
	});
	// 搜索
	$("#btn-search").click(
			function() {
				$('#invBalanceTable').bootstrapTable(
						'refresh',
						{
							'url' : "customerInvSearch/balancesList?custname="
									+ $("#custname").val() + "&sku="
									+ $("#sku").val() + "&materialId="
									+ $("#materialId").val()
									+ "&organizationId="
									+ $("#organizationId").val() + "&rdcCode="
									+ $("#rdcCode").val() + "&period="
									+ $("#period").val()
						});
			})
	$('#balanceDetailsTable').bootstrapTable({
		method : 'get',
		classes : "table table-hover table-condensed",
		cache : false,
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'id',
			title : '编号',
			visible : false
		}, {
			field : 'type',
			title : '类型',
			align : 'left',
			formatter : function(value) {
				return getType(value + "");
			}
		}, {
			field : 'source',
			title : '来源',
			align : 'left',
			formatter : function(value) {
				return getSource(value + "");
			}
		}, {
			field : 'amounts',
			title : '箱内数量',
			align : 'left'
		}, {
			field : 'dNum',
			title : '增加数量',
			align : 'left'
		}, {
			field : 'cNum',
			title : '减少数量',
			align : 'left'
		}, {
			field : 'createTs',
			title : '创建时间',
			align : 'left'
		}, {
			field : 'creater',
			title : '操作人',
			align : 'left'
		}, {
			field : 'voucherId',
			title : '单号',
			align : 'left'
		}, {
			field : 'remark',
			title : '备注',
			align : 'left'
		} ]
	});
	$("#btn-detail").click(function() {
		var rows = $("#invBalanceTable").bootstrapTable("getSelections");
		if (rows && rows.length > 0) {
			var str = JSON.stringify(rows);
			$("#balanceDetailsModal").modal("show");
			$('#balanceDetailsTable').bootstrapTable('refresh', {
				'url' : "customerInvSearch/balanceDetailsList?id=" + rows[0].id
			});
		} else {
			$.messager.popup("请选择记录!");
		}
	})
	var url = "account/period/periodData";
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			$("#period").append("<option value=''>全部</option>");
			for (var i = 0; i < data.rows.length; i++) {
				$("#period").append(
						"<option value='" + data.rows[i].period + "'>"
								+ data.rows[i].period + "</option>")
			}
		}
	});

	$("#btn-update").click(function() {
		var rows = $("#invBalanceTable").bootstrapTable("getSelections");
		if (rows && rows.length > 0) {
			$.post("customerInvSearch/updateBalance", {
				"merchId" : rows[0].merchCustId,
				"materialId" : rows[0].materialId
			}, function(res) {
				if (res.data == 'S') {
					$.messager.popup("更新成功");
					$('#invBalanceTable').bootstrapTable('refresh');
				} else {
					$.messager.popup("更新出错");
				}
			})
		} else {
			$.messager.popup("请选择记录!");
		}

	})
});
