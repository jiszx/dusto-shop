$(function(){
	 $('.tokenfield').tokenfield();
     $('#tcnsp').bootstrapTable({
    	url :'combination/linesdata?headerId='+$("#id").val(),
        method: 'get',
        classes:"table table-hover table-condensed",
        cache: false,
        striped: true,
        pagination : true,
        idField:"materialId",
        toolbar:"#tcnspTools",
        sidePagination : "server",
        sortName:"sku",
        clickToSelect: true,
        singleSelect:true,
        smartDisplay : true,
        showColumns : true,
        columns: [ {
            field: 'materialId',
            title: '商品编码'
        }, {
            field: 'sku',
            title: '商品名称',
            align: 'left'
        }, {
            field: 'num',
            title: '数量',
            align: 'left'
        }, {
            field: 'price',
            title: '价格',
            align: 'left',
            visible:false
        }]
    });

    $('#tss').bootstrapTable({
    	url:'combination/rebatedata?headerId='+$("#id").val(),
        method: 'get',
        classes:"table table-hover table-condensed",
        cache: false,
        striped: true,
        idField:"id",
        toolbar:"#tssTools",
        sortName:"colName",
        sidePagination : "server",
        clickToSelect: true,
        singleSelect:true,
        smartDisplay : true,
        showColumns : true,
        columns: [{
            field: 'limitNum',
            title: '数量限制',
            align: 'left'
        }, {
            field: 'ratio',
            title: '返利比例',
            align: 'left',
            formatter:function(value){
            	return value +'%';
            }
        }, {
            field: 'materialId',
            title: '产品编码',
            align: 'left'
        }, {
            field: 'price',
            title: '价格',
            visible:false,
            align: 'left'
        }]
    });
    var tokenArray = new Array();
	//获取组织数据
	var areaurl="combinationApply/rangeArea?applyId="+$("#applyId").val();
	$.get(areaurl,function(data){
		if(data.rows && data.rows.length>0){
			for(var i=0;i<data.rows.length;i++){
				tokenArray.push({ value: data.rows[i].id, label: data.rows[i].name })
			}
			$("#range").tokenfield('setTokens',tokenArray);
		}
	})
})

