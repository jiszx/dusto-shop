$(function() {
	$('#promotionListTable').bootstrapTable({
		url : 'promotion/material/invlist',
		method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		toolbar : "#promotionListTool",
		striped : true,
		pagination : true,
		searchOnEnterKey : true,
		sidePagination : "server",
		idField : "id",
		sortName : "materialname",
		smartDisplay : true,
		pageSize : 10,
		pageList : [ "10", "20", "50", "100" ],
		//search : true,
		showColumns : true,
		showRefresh : true,
		clickToSelect : true,
		singleSelect : true,
		columns : [ {
			field : 'ck',
			title : '编号',
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
			field : 'materialname',
			title : '促销品名称',
			align : 'left'
		}, {
			field : 'unit',
			title : '规格',
			align : 'left'
		}, {
			field : 'storesname',
			title : '库房名称',
			align : 'left'
		}, {
			field : 'price',
			title : '当前单价',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		}, {
			field : 'num',
			title : '库存数量',
			align : 'left'
		}, {
			field : 'amt',
			title : '库存金额',
			align : 'left',
			formatter : function(value) {
				return value ? value.formatMoney() : value;
			}
		} ]
	});
	$("#btn-search").bind("click", dosearch);
	// 获取销售组织数据
	/*var url = "promotion/util/org.json";
	$('#searchorgid').append("<option value=''></option>");
	$.get(url, function(data) {
		if (data.rows && data.rows.length > 0) {
			for (var i = 0; i < data.rows.length; i++) {
				$('#searchorgid').append(
						"<option value='" + data.rows[i].id + "'>"
								+ data.rows[i].name + "</option>");

			}
		}
	});*/
	$("#searchorgid").change(function() {
				// 库房
				$("#searchstore").html("");
				var storesurl = "promotion/util/stores.json?orgid=" + $("#searchorgid").val();
				$.get(storesurl, function(data) {
					if (data.rows && data.rows.length > 0) {
						$('#searchstore').append("<option>全部</option>");
						for (var i = 0; i < data.rows.length; i++) {
							$('#searchstore').append(
									"<option value='" + data.rows[i].id + "'>"
											+ data.rows[i].name + "</option>");
						}
					}
				});
			})
});

function dosearch() {
	var name = $("#searchname").val();
	name = encodeURI(name);
	name = encodeURI(name);
	$("#promotionListTable").bootstrapTable(
			"refresh",
			{
				'url' : 'promotion/material/invlist?name=' + name + "&orgid="
						+ $("#searchorgid").val() + "&storesid="
						+ $("#searchstore").val()
			});

}
