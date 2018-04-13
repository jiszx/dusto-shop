var table = null;
$(function(){
    table = $('#notesTable').bootstrapTable({
        method: 'get',
        classes:"table table-hover table-condensed",
        url: 'system/notes/list',
        cache: false,
        toolbar:"#dictTool",
        striped: true,
        pagination: true,
        searchOnEnterKey:true,
        sidePagination:"server",
        idField:"id",
        sortName:"checkTs",
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
            field: 'title',
            title: '标题',
            align: 'left'
        }, {
            field: 'creator',
            title: '创建者',
            align: 'left'
        }, {
            field: 'checker',
            title: '审核者',
            align: 'left'
        }, {
            field: 'checkTs',
            title: '审核时间',
            align: 'left'
        }, {
            field: 'topFlag',
            title: '是否置顶',
            align: 'left',
            formatter:function(value){
                return genBoolean(value);
            }
        }, {
            field: 'createTs',
            title: '创建时间',
            align: 'left'
        }, {
            field: 'stat',
            title: '状态',
            align: 'left',
            formatter:function(value){
                return getDictValue(value);
            }
        }]
    });
    $(".btn-del").bind("click",doDel);
    $(".btn-edit").bind("click",doEdit);
    $("#btn-detail").bind("click",doShow);
    $("#btn-send").bind("click",doSend);
    $("#btn-pass").bind("click",doPass);

});
function doShow(){
    var rows =table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        showDetail("system/notes/look/"+rows[0].id+".html");
    }else{
        $.messager.alert("提示", "请选择记录!");
    }
}

function doDel(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
    	if(rows[0].stat != "1"){
    		$.messager.alert("提示","该公告不可删除")
    	}else{
    		$.messager.confirm("警告", "您确认要删除此记录吗?", function() {
                $.post("system/notes/del",{"id":rows[0].id},function(data){
                	if(data.errorCode == 0){
                		$("#notesTable").bootstrapTable("refresh");
                	}else{
                		$.messager.alert("提示","删除失败,"+data.errorMessage);
                	}
                	})
            });
    	}
        
    }else{
        $.messager.alert("提示", "请选择要删除的记录!");
    }
}
function doEdit(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        location.href="system/notes/preEdit?id="+rows[0].id
    }else{
        $.messager.alert("提示", "请选择要编辑的记录!");
    }
}
function doSend(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        if(rows[0].stat == 0){
            $.messager.popup("该公告已发布")
        }else if(rows[0].stat == 2){
            $.messager.popup("该公告正在审核中")
        }else if(rows[0].stat == 1){
            $.post("system/notes/apply",{"id":rows[0].id},function(data){
                if(data.errorCode==0){
                    $.messager.popup("该公告已提交审核");
                    table.bootstrapTable("refresh");
                }else{
                    $.messager.popup("提交审核失败,原因:"+data.errorMessage)
                }
            })
        }else{
        	$.messager.popup("该公告状态不可送审");
        }
    }else{
        $.messager.alert("提示", "请选择要提交审批的记录!");
    }
}
function doPass(){
    var rows = table.bootstrapTable("getSelections");
    if(rows && rows.length ==1){
        if(rows[0].stat != 2){
            $.messager.popup("该公告不能审批")
        }else{
            if(rows[0].creator == currentUser){
                $.messager.alert("提示","公告创建者不能审批该公告")
            }else{
                $("#Notetitle").html(rows[0].title);
                $("#showPassModal").modal("show");
                $("#btn_comit").one("click",function(){
                    $("#showPassModal").modal("hide");
                    $.post("system/notes/passed",{"id":rows[0].id},function(data){
                        if(data.errorCode==0){
                            $.messager.popup("公告通过");
                            table.bootstrapTable("refresh");
                        }else{
                            $.messager.popup("公告通过失败,原因:"+data.errorMessage)
                        }
                    });
                });
                $("#btn_back").one("click",function(){
                    $("#showPassModal").modal("hide");
                    $.post("system/notes/noPass",{"id":rows[0].id},function(data){
                        if(data.errorCode==0){
                            $.messager.popup("该公告已驳回");
                            table.bootstrapTable("refresh");
                        }else{
                            $.messager.popup("驳回失败,原因:"+data.errorMessage)
                        }
                    });
                })
            }
        }
    }else{
        $.messager.alert("提示", "请选择要审批的记录!");
    }
}