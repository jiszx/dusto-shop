/**
 * Created by Administrator on 2016/8/4.
 */
var discounttype =$('#discounttype');
var editdiscounttype =$('#editdiscounttype');
$(function(){
    getpolicy();
    geteditpolicy();
    getOrderVerification();
    $('#policyTypeTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'salepolicy/policyType/applyTypeList',
        cache: false,
        toolbar:"#promotionInTool",
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
            title: '',
            radio:true
        }, {
            field: 'id',
            title: 'id',
            align: 'left',
            visible : false
        }, {
            field: 'name',
            title: '类型描述',
            align: 'left'
        }, {
            field: 'type',
            title: '类型区分',
            align: 'left',
            formatter : function(value) {
                return getType(value + "");
            }
        }, {
            field: 'benchmark',
            title: '类型基准',
            align: 'left',
            formatter : function(value) {
                return getbenchmarkType(value + "");
            }
        }, {
            field: 'assessment',
            title: '考核方式',
            align: 'left',
            formatter : function(value) {
                return getassessmentType(value + "");
            }
        }, {
            field: 'assessmentTarget',
            title: '考核目标',
            align: 'left'
        },{
            field: 'verification',
            title: '核销方式',
            align: 'left',
            formatter : function(value) {
                return getverificationType(value + "");
            }
        }
        /*,{
            field: 'verificationDiscount',
            title: '奖励（返利/送产品）',
            align: 'left'
        },{
            field: 'verificationDiscountNum',
            title: '奖励数量',
            align: 'left'
        }*/
        ]
    });
    var addValidator = initValidate("#addPolicyTypeForm");
    var editValidator = initValidate("#editPolicyTypeForm");
    $("#editPolicyTypeForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return editValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            $("#editPolicyTypeModal").modal("hide");
            $("#policyTypeTable").bootstrapTable("refresh")
        }
    });
    $("#addPolicyTypeForm").ajaxForm({
        target:'#btn-add',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return addValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            $("#addPolicyTypeModal").modal("hide");
            $("#policyTypeTable").bootstrapTable("refresh")
        }
    });
    $(".btn-edit").bind("click",doEdit);
    $(".btn-del").bind("click",doDel);
    //$(".btn-add-modal").bind("click",openAddModal);
    
    //自动核销，人工核销判断
    addchangetype();
    $("#type").change(function(){
        addchangetype();
    })
    editchangetype();
    $("#edittype").change(function(){
        editchangetype();
    })
    //类型基准(活动，订单)
    $("#benchmark").change(function(){
    	var benchmark = $("#benchmark").val();
    	if(benchmark=="1"){
    		//活动
    		 $("#cassessment").css('display','block');
    		 $("#verification").empty();
             $("#verification").append('<option value="3">返现金</option>');
             $("#verification").append('<option value="4">返货补</option>');
    	}else{
    		//订单
    		 $("#cassessment").css('display','none');
    		 $("#cassessmentTarget").css('display','none');
    		 $("#verification").empty();
    		 getOrderVerification();
    	}
    })
    //考核方式变动
    $("#assessment").change(function(){
        $("#targethtml").html();
        var assessment =$('#assessment').val();
        if(assessment =='1'){
            $("#cassessmentTarget").css('display','none');
        }else{
            $("#cassessmentTarget").css('display','block');
            if(assessment=='2'){
                $("#targethtml").html('销售量目标');
            }else{
                $("#targethtml").html('销售额目标(元)');
            }
        }
    })
    $("#editassessment").change(function(){
        $("#edittargethtml").html();
        var assessment =$('#editassessment').val();
        if(assessment =='1'){
            $("#ceditassessmentTarget").css('display','none');
        }else{
            $("#ceditassessmentTarget").css('display','block');
            if(assessment=='2'){
                $("#edittargethtml").html('销售量目标');
            }else{
                $("#edittargethtml").html('销售额目标(元)');
            }
        }
    })
    //addchangepolicytype();
    $('#verification').change(function(){
        //addchangepolicytype();
    })
    //editchangepolicytype();
    $('#editverification').change(function(){
        //editchangepolicytype();
    })
});
/**
 * 验证数据
 * @param formId
 * @returns
 */
function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            name: "required",
            type: "required",
            assessmentTarget:"number",
            verificationDiscountNum: "number"
        }
    });
    return validator;
}
function addchangetype(){
    var type=$("#type").val();
    if(type=='1'){
        //自动核销
        $("#typeid").css('display','block');
    }else{
        //人工核销
        $("#typeid").css('display','none');
        //清空数据
        //discounttype.empty();
        $('#benchmark').val("");
        $('#assessment').val("");
        $('#assessmentTarget').val("");
        $('#verificationDiscount').val("");
        $('#verificationDiscountNum').val("");
    }
}
function editchangetype(){
    var type=$("#edittype").val();
    if(type=='1'){
        //自动核销
        $("#edittypeid").css('display','block');
    }else{
        //人工核销
        $("#edittypeid").css('display','none');
        //清空数据
        //discounttype.empty();
        $('#editbenchmark').val("");
        $('#editassessment').val("");
        $('#editassessmentTarget').val("");
        $('#editverificationDiscount').val("");
        $('#editverificationDiscountNum').val("");
    }
}

/**
 * 编辑按钮
 */
function doEdit(){
    var rows = $("#policyTypeTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        if(rows[0].type=='1'){
            //自动核销
            $("#edittypeid").css('display','block');
            if(rows[0].benchmark=='1'){
            	//活动基准
            	$("#editverification").empty();
                $("#editverification").append('<option value="3">返现金</option>');
                $("#editverification").append('<option value="4">返货补</option>');
                $("#ceidtassessment").css('display','block');
                $("#ceditassessmentTarget").css('display','block');
            }else{
            	$("#ceidtassessment").css('display','none');
                $("#ceditassessmentTarget").css('display','none');
                $("#editassessment").val('');
                $("#assessmentTarget").val('');
            	$("#editverification").empty();
            	getEditOrderVerification();
            }
        }else{
            //人工核销
            $("#edittypeid").css('display','none');
        }
        autoEdit(rows[0]);
        $("#editPolicyTypeModal").modal("show");
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}

function doDel(){
    var rows = $("#policyTypeTable").bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        $.messager.confirm("警告", "您确认要删除此记录吗?", function() {
            $.post("salepolicy/policyType/delPolicyType",{"id":rows[0].id},function(){$("#policyTypeTable").bootstrapTable("refresh");})
        });
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}