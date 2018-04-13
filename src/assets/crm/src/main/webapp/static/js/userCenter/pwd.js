$(function(){
    var addValidator = initValidate("#editUserForm");
    $("#editUserForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:true,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return addValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            if(responseText.errorCode != 0){
                $.messager.alert("修改密码失败",responseText.errorMessage);
            }else{
                $.messager.popup("修改密码信息成功")
            }
        }
    });
})
function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            oldPwd:{required:true},
            renewPwd:{required:true,minlength:8},
            newPwd:{required:true,minlength:8,equalTo:"#newPwd"}
        }
    });
    return validator;
}