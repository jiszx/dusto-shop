/**
 * Created by Administrator on 2016/7/11.
 */
$(document).ready(function() {
// 在键盘按下并释放及提交后验证提交表单
    $("#editOrganization").validate({
        rules: {
            organizationName: "required",
            organizationNum: "required",
            principal:{
                min:1
            },
            authotatus:{
                min:1
            },
            jobName:{
                min:1
            }
        },
        messages: {
            organizationName: "请输入组织名称",
            organizationNum: "请输入组织编号",
            jobName:{
                min:'请选择岗位级别'
            },
            principal:{
                min:'请选择负责人'
            },
            authotatus:{
                min:'请选授权状态'
            }

        }
    });
    $("#supOrganization-img").click(function(){
        $(".panel-info").removeClass("hide");
    })
    $(".close").click(function(){
        $(".panel-info").addClass("hide");
    })

});