<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>${applicationScope.PROJECT_NAME}</title>
    <jsp:include page="/common/head.jsp"></jsp:include>
    <style type="text/css">
    	.floatNav{
    	position: fixed;
		bottom: 32px;
		text-align: right;
		right: 0px;
		background-color: rgb(122, 110, 110);
		padding: 10px;
		vertical-align: middle;
		color: rgb(255, 255, 255);
		opacity: 0.5;
		cursor: pointer;}
		.opinions-sugges{
			width:100%;
			height:150px;
		}
		.contact-way span{
			display:inline-block;
			width:20%;
			text-align:center;
			line-height:30px;
		}
		.contact-way input{
		width:70%;
		line-height:30px;
		}
    </style>
</head>
<body class="skin-blue">
<div id="navigator" class="alert alert-danger" style="display:none;">为了更好的体验，请你使用IE9以上版本或者非IE内核版本的浏览器</div>
<jsp:include page="/common/header.jsp"></jsp:include>
<div class="wrapper row-offcanvas row-offcanvas-left">
    <jsp:include page="/common/leftMenu.jsp"></jsp:include>
    <aside class="right-side">
        <iframe id="view_mainFrame" name="view_mainFrame" class="content embed-responsive-item" style="border:none;padding:0px;" width="100%" src="dashboard.html"></iframe>
    </aside>
	<div class="floatNav" id="floatNav" data-toggle="modal" data-target="#showfankuiModal" >
		<em style="width:40px;padding-right:10px;display:none;">反馈</em><i class="icon icon-edit" style="width:35px;text-align:center;"></i>
	</div>
</div>
<%--<nav class="navbar navbar-fixed-bottom" style="min-height: 20px;">

    &lt;%&ndash;<div class="container-fluid">
        <div class="col-md-12" style="color:#fff;"><small class="pull-right"><label>组织：调味品事业部--${station.orgname}</label><label id="userOrgInfo"></label></small></div>
    </div>&ndash;%&gt;
</nav>--%>
<div class="modal fade" id="showfankuiModal" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="showdictLabel">反馈调整</h4>
				</div>
				<div class="modal-body" style="padding:0 15px 15px;">
					<h6>
						<p>尊敬的用户：</p> 
						<p>您好！为了给您提供更好的服务，我们希望收集您使用本系统时的看法或建议。对您的配合和支持表示衷心感谢！</p>
					</h6>
					<div class="signtext">
						<textarea id="feedbackContent" class="opinions-sugges"></textarea>
					</div>
					<h6>
						我们会不定期邀请用户参与面对面的交流。如果您有意参与，请填写如下信息，方便我们与您联系，谢谢！（信息仅作为内部资料绝不外泄）
					</h6>
					<div class="contact-way" style="border:1px solid #EEF6FF;padding:15px 0;">
						<div class="form-group">
							<span>姓名:</span><input id="feedbackName" type="text" class="form-contral">
						</div>
						<div class="form-group">
							<span>电话:</span><input id="feedbackPhone" type="text" class="form-contral">
						</div>
					</div>
				</div>
				<div class="modal-footer" style="padding:0 15px 15px;text-align:center;">
					<button type="button" id="feedbackBtn" class="btn btn-save btn-primary">提交</button>
				</div>
			</div>
		</div>			
</div>

<div class="modal fade" id="processModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="progress">
					<div class="progress-bar progress-bar-striped active" id="progressbar" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
						系统正在努力的处理中........
					</div>
				</div>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<jsp:include page="/common/footjs.jsp"></jsp:include>
<script type="text/javascript" src="static/js/jquery.slimscroll.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
        var mainheight = ($(window).height()-125)+"px";
        var contentHeight=($(window).height()-50)+"px";
        $(".wrapper").css({"height":contentHeight});
	    $(".sidebar-menu").slimScroll({
            height:mainheight,
            size: '5px',
            position: 'right',
            color: '#ffcc00',
            alwaysVisible: false,
            distance: '0px',
            //start: $('#child_image_element'),
            railVisible: true,
            railColor: '#222',
            railOpacity: 0.3,
            wheelStep: 10,
            allowPageScroll: false,
            disableFadeOut: false
        });
		$(".floatNav").hover(function(){
			$(".floatNav").css("background-color","red");
			$(".floatNav em").fadeIn("slow");
		},function(){
		    $(".floatNav").css("background-color","#7a6e6e");
		    $(".floatNav em").fadeOut("fast");
		});
		$('#feedbackBtn').bind('click',function(){
			var content = $("#feedbackContent").val();
			var name = $("#feedbackName").val();
			var phone = $("#feedbackPhone").val();
			$.post('feedback/feedback.json',{'message':"内容："+content+"\n姓名："+name+"\n电话："+phone},function(res){
				if(res.data && res.data==1){
					$("#showfankuiModal").modal('hide');
					$.messager.popup('发送成功');
				}else{
					$.messager.alert('提示','发送失败');
				}
				
			});
		});
	});
	$("#view_mainFrame").load(function(){
		/*var mainheight = $(window).height()-55;
		/!* var mainheight = $(this).contents().find("body").height()-50;
		/!* if(window.innerHeight > mainheight){
			$(this).height(window.innerHeight);
		}else{ *!/
			$(this).css({"min-height":mainheight,"height":"auto"});
		/!* } *!/ */
		processBar(false)
	});

	var currentsetInterval= null;
	var start = 20;
	function processBar(lock) {

		if(lock){
			$("#processModal").modal("show");
			currentsetInterval = setInterval(loading,1000);
		}else{
			clearInterval(currentsetInterval)
			$("#processModal").modal("hide");
		}
	}
	function loading(){
		start = start+5;
		if(start >100){
			start = 5;
		}
		$("#progressbar").attr("aria-valuenow",start+5);
		$("#progressbar").css("width",start+"%");
	}
	$(function(){
		$(".leftNav").click(function(e){
			//e.preventDefault();
			processBar(true)
		})
	})
</script>
</body>
</html>
