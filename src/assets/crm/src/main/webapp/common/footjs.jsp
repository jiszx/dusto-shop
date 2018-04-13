<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="modal fade" id="showDetailModal" tabindex="-1" role="dialog"
	aria-labelledby="showTitle">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="showTitle">查看</h4>
			</div>
			<div class="modal-body"></div>
		</div>
	</div>
</div>
<script src="${applicationScope.STATIC}static/js/jquery.json-2.2.js" type="text/javascript"></script>
<script src="${applicationScope.STATIC}static/bootstrap/js/bootstrap.min.js"type="text/javascript"></script>
<script src="${applicationScope.STATIC}static/js/jquery.form.min.js" type="text/javascript"></script>
<script src="${applicationScope.STATIC}static/js/jquery.bootstrap.min.js" type="text/javascript"></script>
<script src="${applicationScope.STATIC}static/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${applicationScope.STATIC}static/js/messages_zh.min.js" type="text/javascript"></script>
<script src="${applicationScope.STATIC}static/js/common.js" type="text/javascript"></script>
<script type="text/javascript">
	$(".dropdown-postName").on("click", "a", function() {
		var selected = $(this).html();
		var caret = '<span class="caret" style="color:#999;"></span>';
		$("#postName").html(selected + caret);
	});
	//全局ajax请求处理
	$(document).ajaxComplete(function( event, xhr, settings ) {
		try {
			var reponse = $.parseJSON(xhr.responseText);
			if(reponse.errorCode == '403'){
				/* $.messager.alert(reponse.errorMessage); */
				//console.log("权限不足->"+xhr.responseText);
			}
		} catch (e) {
			/* console.log("response->"+xhr.responseText); */
		}
	});
	$(".dropdown-postName").on(
			"click",
			"li",
			function() {
				var id = $(this).val();
				var url = "swithstation?id=" + id;
				$.get(url, function(data) {
					if (data.data == "1") {
						$.messager.popup("岗位切换成功");
						var localhostPaht = window.document.location.href;
						var pathurl = localhostPaht.substr(localhostPaht
								.lastIndexOf("/") + 1);
						window.location.href = pathurl;
						var userOrgInfoUrl2 = "userorg"
							$.get(userOrgInfoUrl2, function(data) {
								if (data.data) {
									$("#userOrgInfo").text(data.data);
								} else {
									$("#userOrgInfo").text("");
								}
						})
					} else {
						$.messager.alert("提示", "岗位切换失败，请联系后台管理人员");
					}
				});
			});

    $(function(){
        $.validator.setDefaults({
            errorElement:"span",
            errorClass:"text-danger",
            success:function(ele){
                ele.removeClass("text-danger").addClass("text-success").html("<i class='glyphicon glyphicon-ok'></i>");
            },
            errorPlacement : function(error, element,eless) {
                $(element).next(".form-control-feedback").show();
                if($(element).parent().hasClass("input-group")){
                    $(element).parent().parent().next(".help-block").html(error);
                }else{
                    $(element).parent().next(".help-block").html(error);
                }

            }
        });
        var panelHeight=($(".scroll").parents("html").height()-100)+"px";
        var bodyHeight=($(".scroll").parents("html").height()-100)+"px";
        if($(".scroll .panel-heading")){
            //console.debug($(".scroll .panel-heading"));
            bodyHeight=($(".scroll").parents("html").height()-140)+"px";
		}else{
            bodyHeight=($(".scroll").parents("html").height()-100)+"px";
		}
        $(".scroll").height(panelHeight);
        $(".scroll").css("padding","0px");
        $(".scroll .panel-body").css("padding","0px 10px");
        $(".scroll").parents("body").css("padding-bottom","0px");
        $(".scroll").css("margin-bottom","0px");
        $(".scroll .panel-body").slimScroll({
            height:bodyHeight,
            size: '5px',
            position: 'right',
            color: '#ffcc00',
            alwaysVisible: false,
            distance: '0px',
            railVisible: true,
            railColor: '#222',
            railOpacity: 0.3,
            wheelStep: 1,
            allowPageScroll: false,
            disableFadeOut: false
        });
	})
</script>