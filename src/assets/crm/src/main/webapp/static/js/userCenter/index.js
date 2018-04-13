$(function(){
    var addValidator = initValidate("#editUserForm");
    $("#editUserForm").ajaxForm({
        target:'#btn-edit-save',   // target element(s) to be updated with server response
        clearForm:false,
        dataType:'json',
        beforeSubmit:  function(formData, jqForm, options){
            return addValidator.valid();
        },
        success:function(responseText, statusText, xhr, $form){
            if(responseText.errorCode != 0){
                $.messager.alert("修改员工信息失败",responseText.errorMessage);
            }else{
                $.messager.popup("修改个人信息成功")
            }
        }
    });
})
function initValidate(formId){
    var validator = $(formId).validate({
        rules: {
            name: "required",
            contactTel:{required:true,minlength:11},
            email: {required: true,email:true}
        }
    });
    return validator;
}