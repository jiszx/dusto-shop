$(function(){
    // using(['combotree'], function(){
    //     $('#areaTree').combotree({
    //         url:'Org/listPid',
    //         idField:'id',
    //         treeField:'name',
    //         onBeforeLoad : function(row, param) {
    //             if (row) {
    //                 $(this).tree("options").url = 'Org/listPid?pid=' + row.id;
    //             } else {
    //                 //$(this).combotree("tree").tree("options").url = 'Org/listPid';
    //             }
    //         },
    //         onLoadSuccess:function(){
    //             $("#areaTree").combotree("setValue","T");
    //         },
    //         loadFilter:function(dd){
    //             var data = dd.data;
    //             for (var int = 0; int < data.length; int++) {
    //                 data[int].text = data[int].name;
    //                 if(data[int].levels == "4"){
    //                 }else{
    //                     data[int].children=[];
    //                     data[int].state="closed"
    //                 }
    //             }
    //             return data;
    //         }
    //     });
    // });
    var editor = KindEditor.create('#noteContent', {
        themeType : 'default',
        uploadJson : "system/notes/uploadImg",
        filePostName : "upload",
        allowPreviewEmoticons : false,
        formatUploadUrl : false,
        allowUpload : true, // 允许上传图片
        items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
            'bold', 'italic', 'underline', 'removeformat', 'wordpaste',
            '|', 'justifyleft', 'justifycenter', 'justifyright','|','table','hr',
            'insertorderedlist', 'insertunorderedlist', '|', 'image','source',
            'link','fullscreen', 'preview' ]
    });
    editor.sync();
    var validator = $("#addNotesForm").validate({
        rules: {
            colName: "required",
            chooseVal: "required",
            showText: {required: true,minlength: 2},
            orders: {required: true, number: 5}
        }
    });
    $("#btn-article-add").click(function(e){
        e.preventDefault();
        if (validator.form()) {
            editor.sync();
            document.forms["addNotesForm"].submit()
        }
    })
    $(".btn-delAtta").bind("click",function (e) {
        e.preventDefault();
        var id = $(this).attr("attr");
        var node = $(this);
        var nid= $("#editid").val();
        $.messager.confirm("提示","是否删除附件",function () {
            $.post("system/notes/delAtta",{"nid":nid,"id":id},function (data) {
                if(data.errorCode == "0"){
                    node.remove();
                }
            })
        })
    })

});