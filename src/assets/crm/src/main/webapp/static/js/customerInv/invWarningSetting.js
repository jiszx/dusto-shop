var $table = $('#setting-table');
var $CT = 'QUANT';//默认的检查类型
function initTable() {
	$table.bootstrapTable({
				method: 'get',
				url : 'customer/config/inv/warning/materialList?custId='+$("#custId").val(),
				pagination : true,
				toolbar: "#dictTool",
				searchOnEnterKey : true,
				sidePagination : "server",
				idField : "id",
				pageSize : 10,
				pageList : [ "10", "20", "50", "100" ],
				clickToSelect : true,
				singleSelect : false,
				search : true,
				showRefresh: true,
				showColumns: true,
				searchOnEnterKey:true,
				cache: false,
				queryParams: function(params){
		            var custId = $("#custId").val();
		            params.custId = custId;
		            return params;
		        },
				columns : [
						{
							title : 'SKU',
							field : 'materialId',
							align : 'center',
							valign : 'middle',
						},{
							title : '物料名称',
							field : 'materialBase.materialName',
							align : 'center',
							valign : 'middle',
						},{
							title : '现有库存',
							field : 'invNum',
							align : 'center',
							valign : 'middle',
						},
						{
							title : '检查类型',
							field : 'custInvWarningItem.warningType',
							align : 'center',
							valign : 'middle',
							visible : false,
							formatter : function(value, row, index) {
								var formattedVal ;
								if(typeof value == 'undefined' || value == 'undefined'){
									formattedVal = $CT;
								}else{
									formattedVal = value;
								}
								$table.data('warningType'+index,formattedVal);
								return formattedVal;
							},
							editable : {
								type : 'select',
								title : '检查类型:',
						        source: [
						              /*{value: 'RATIO', text: '比例'},*/ //由于比例设置需要基数，暂不处理
						              {value: 'QUANT', text: '数量'}
						           ],
						        autotext:'auto',
								validate : function(value) {
									value = $.trim(value);
									if (!value) {
										return '请选择一个库存检查类型';
									}
									var ptr = $(this).closest('tr');
									var dataIndex = ptr.attr('data-index');
									$table.data('warningType'+dataIndex,value);
								}
							}
						},
						{
							title : '目标数量',
							field : 'custInvWarningItem.target',
							align : 'center',
							valign : 'middle',
							formatter : function(value, row, index) {
								var formattedVal ;
								if(typeof value == 'undefined' || value == 'undefined'){
									formattedVal = '0';
								}else{
									formattedVal = value;
								}
								var warningType = $table.data('warningType'+index);
								//如果目标数据format时没有检测类型，则默认检查类型为数量
								if(typeof warningType == 'undefined' || warningType == ''){
									$table.data('warningType'+index, $CT);
								}
								return formattedVal;
							},
							editable : {
								type : 'text',
								title : '目标数量/比例',
								display: function(value) {
						        	if(typeof value == 'undefined' || value == 'undefined'){
						        		$(this).text('Empty');
						        	}else{
						        		var index = $(this).closest('tr').attr('data-index');
										var warningType = $table.data('warningType'+index);
						        		$(this).text(value);
						        		if(warningType == 'QUANT'){
						        			var row = $table.bootstrapTable('getData')[index];
											//数量
						        			$(this).text(value+' '+row.materialBase.unit);
										}
						        		if(warningType == 'RATIO'){
											//比例
						        			$(this).text(Number(value)*100+'%');
										}
						        	}
						        },
								validate : function(value) {
									value = $.trim(value);
									if (!value) {
										return '请输入目标数量或比例';
									}
									if (!/^[1-9]\d*\.{0,1}\d*$|0\.\d*$/.test(value)) {
										return '请输入正确的数值,如100或0.25'
									}
									var index = $(this).closest('tr').attr('data-index');
									var warningType = $table.data('warningType'+index);
									var value = Number(value);
									if(warningType == 'RATIO'){
										//比例
										if (!/^0\.\d{1,2}$/.test(value)) {
											return '请输入正确的比例(0<比例<1),如0.25';
										}
									}
									if(warningType == 'QUANT'){
										//数量
										//do nothing
									}
									var row = $table.bootstrapTable('getData')[index];
									editTarget(row, value, warningType);
									return '';
								}
							}
						}
						]
			});
}

function editTarget(row, target, warningType){
	$.post('customer/config/inv/warning/setting',{'custInvId':row.id, 'target':target, 'warningType':warningType}, function(res){
		if(res.errorCode!=0){
			$.messager.alert('错误','更改失败');
		}else{
			$table.bootstrapTable("refresh");
		}
	});
}

initTable();

function changeEnablestatus(){
	var rows = $("#contract-table").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.post("customer/sku/changeEnable.json",{"id":rows[0].id}, function(res){
        	if(res.data==1){
        		$.messager.popup("更改成功");
        		$("#contract-table").bootstrapTable("refresh");
        	}else{
        		$.messager.popup("更改失败");
        	}
        });
    }else{
        $.messager.alert("提示", "请选择要更改的记录!");
    }
}