/**
 * Created by 杨 on 2016/12/13.
 */
$(function(){
    $('#combinationTable').bootstrapTable({
    	url: 'combination/combinationList',
    	method : 'get',
		classes : "table table-hover table-condensed",
		// data:data,
		cache : false,
		toolbar : "#combinationTool",
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
        columns: [{
            field: '',
            title: '',
            radio:true
        }, {
            field: 'code',
            title: '套餐编号',
            align: 'left'
        }, {
            field: 'name',
            title: '套餐名称',
            align: 'left'
        }, {
            field: 'price',
            title: '套餐价格',
            align: 'left',
            visible:false,
            formatter:function(value){
            	return (value/100).formatMoney();
            }
        }, {
            field: 'weight',
            title: '套餐规格',
            align: 'left'
        }, {
            field: 'modelType',
            title: '生意模式',
            align: 'left',
            formatter:function(value){
            	return getModelType(value + "");
            }
        }, {
            field: 'bDate',
            title: '套餐开始时间',
            align: 'left'
        }, {
            field: 'eDate',
            title: '套餐结束时间',
            align: 'left'
        }, {
            field: 'states',
            title: '状态',
            align: 'left',
            formatter:function(value){
            	return value=='1'?'编辑':'完成';
            }
        }]
    });
    $("#btn-edit").click(function(){
    	var row =$('#combinationTable').bootstrapTable('getSelections');
    	if(row && row.length ==1 && row[0].states=='1'){
    		window.location.href="combination/edit.html?id="+row[0].id;
    	}else{
    		$.messager.popup("请选择编辑状态记录");  
    	}
    })
    
    
    $("#btn-audit").click(function(){
    	var row =$('#combinationTable').bootstrapTable('getSelections');
    	if(row && row.length ==1 && row[0].states=='1'){
    		$.post("combination/audit?id="+row[0].id,function(res){
    			if(res.errorCode !=0){
    				$.messager.popup("出错"); 
    			}
    			$('#combinationTable').bootstrapTable('refresh');
    		});
    	}else{
    		$.messager.popup("请选择记录");  
    	}
    })
    
    $("#btn-del").click(function(){
    	var row =$('#combinationTable').bootstrapTable('getSelections');
    	if(row && row.length ==1 && row[0].states=='1'){
    		$.post("combination/del?id="+row[0].id,function(res){
    			if(res.errorCode !=0){
    				$.messager.popup("刪除出错"); 
    			}
    			$('#combinationTable').bootstrapTable('refresh');
    		});
    	}else{
    		$.messager.popup("请选择记录");  
    	}
    })
    $("#btn-details").click(function(){
    	var row =$('#combinationTable').bootstrapTable('getSelections');
    	if(row && row.length ==1){
    		window.location.href="combination/view.html?id="+row[0].id;
    	}else{
    		$.messager.popup("请选择记录");  
    	}
    })
});

