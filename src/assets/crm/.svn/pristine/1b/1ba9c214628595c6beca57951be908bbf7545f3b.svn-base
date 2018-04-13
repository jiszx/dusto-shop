var $materialTable = null;
var id =0;
var rebateid=0;
$(function(){
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
            visible:false,
            align: 'left'
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
    $("#btn-back").click(function(){
    	window.location.href="combination/index.html";
    })
})

