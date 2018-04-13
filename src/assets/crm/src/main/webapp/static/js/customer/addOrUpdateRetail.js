$(function () {
    // $('#merchAddWizard').bootstrapWizard({onTabClick: function(tab, navigation, index) {
    //     return false;
    // },tabClass: 'nav nav-pills'});
    $("#wizard").bwizard({
        backBtnText:"上一步",
        nextBtnText:"下一步",
        stepHeaderTemplate:"<li><em>#{title}</em></li>"
    });
    
    $('#addPid').chosen({
		no_results_text : "没有找到",
		placeholder_text : "请选择客户信息...",
		search_contains: true,
		disable_search_threshold: 10
	});
    
    var _sctop = $(document).scrollTop();
    $(document).scroll(function () {
        _sctop = $(document).scrollTop();
        if (_sctop > 170) {
            $("#stepLink2").css({opacity: 1});
        }
        else if (_sctop < 170) {
            $("#stepLink2").css({opacity: 0});
        }
    });
    $(document).click(function () {

    });
    $("#stepLink > li").click(function () {
        $("#stepLink > li").removeClass("active");
        $(this).addClass("active");
    });
    function changeLinkState(activeLink) {
        $("#stepLink > li").removeClass("active");
        activeLink.addClass("active");
    }

    //删除附件
    $(".file-list").on("click", "a.del-file", function () {
    	var delFileId = $(this).attr("id");
    	var fileItem = $(this).parent();
    	fileItem.remove();
    	if(typeof delFileId != 'undefined'){
    		//删除刚上传的附件
    		var fileInputEle = $("#file_"+delFileId.split("del_file_")[1]);
    		fileInputEle.remove();
    	}else{
    		//删除已存在的附件
    		var delAtts = $("#delAtts").val();
    		if(typeof delAtts != 'undefined' && $.trim(delAtts).length >0){
    			var delAttArray = delAtts.split(",");
    			delAttArray.push($(this).attr("source"));
    			$("#delAtts").val(delAttArray.join(","));
    		}else{
    			$("#delAtts").val($(this).attr("source"));
    		}
    	}
        checkFile();
    });

    //新开类型选项框处理
    $("input[name='openingType']").click(function(){
    	var rows = $(this).closest("td").attr("rowspan");
    	var allTrs = $(this).closest("tr").siblings();
    	//所有明细行input置disable
    	var allInputs = allTrs.find("input:not([name='openingType']):visible");
    	allInputs.prop("disabled",true).filter(":not([tempflag])").each(function(){
    		var sourceVal = $(this).val();
    		if(typeof sourceVal !='undefined' && $.trim(sourceVal) != '' ){
				$(this).attr("tempflag",sourceVal)
			}else{
				$(this).attr("tempflag","")
			}
    	});
    	//选中rowspan影响的行enable，如果标记中有值则从标记中取值赋上
    	if(typeof rows != "undefined"){
    		var currentTr = $(this).closest("tr");
    		for(var i=0;i<Number(rows);i++){
    			currentTr.find("input").removeAttr("disabled").each(function(){
    				var sourceVal = $(this).val();
    				if(typeof sourceVal =='undefined' || $.trim(sourceVal) == '' ){
    					$(this).val($(this).attr("tempflag"));
    				}
    			}).removeAttr("tempflag");
    			currentTr = currentTr.next();
    		}
    	}
    	//置空disable行input的值并标记
    	allTrs.find("input[tempflag]").each(function(){
    		var sourceVal = $(this).val();
			if(typeof sourceVal !='undefined' && $.trim(sourceVal) != '' ){
				$(this).attr("tempflag",$(this).val());
			}
    		$(this).val("");
    	});
    });
    var openingTypeEle = $("#openingTypeEle").val();
    if(typeof openingTypeEle != 'undefined' && $.trim(openingTypeEle).length > 0){
    	$("input[name='openingType']:eq("+(Number(openingTypeEle)-1)+")").click();
    }else{
    	$("input[name='openingType']:eq(0)").click();
    }
    
    //检查附件文件列表
    checkFile();

    //区域初始化
    initAreas();
    
    //所属配送商
    $.get("customer/list?custType=2,7,70&status=3&type=1", function(res){
    	var html = "";
    	if(res && res.rows){
    		$.each(res.rows, function(i, val){
    			var appendSelect = "";
    			if(val.id==pid){
    				appendSelect = "selected"
    			}
    			html += '<option value="'+val.id+'" '+appendSelect+'>'+val.name+'</option>';
    		});	
    		$("#addPid").html(html);
    		$("#addPid").trigger("chosen:updated");
    	}
    });

    $('#provIdSelect').focus(function () {
        initAreas();
    });

    //地市下拉
    $('#provIdSelect').change(function () {
    	var selectedProvOpt = $('#provIdSelect option:selected');
    	$('input[name="provName"]').val(selectedProvOpt.text());
        var selectedProv = selectedProvOpt.val();
        var data;
        var cities = new Array();
        var citiesOpt = "<option selected value=''>-地市-</option>";
        if ($('#cityIdSelect').data(selectedProv)) {
            data = $('#cityIdSelect').data(selectedProv);
            for (var i in data) {
                if (data[i].areaLevel == '2' && data[i].pid == selectedProv) {
                    cities.push(data[i]);
                    citiesOpt = citiesOpt + "\r <option value=" + data[i].id + " >" + data[i].name + "</option>";
                }
            }
            $('#cityIdSelect').html(citiesOpt);
            $('#cityIdSelect').data(selectedProv, cities);
        } else {
        	$.ajax({
                type: 'POST',
                url: 'pub/area/listChildren',
                dataType: 'json',
                data: {pid: selectedProv},
                success: function (result) {
                    if (typeof result != 'undefined' && result.errorCode == '0') {
                        data = result.data;
                        for (var i in data) {
                            if (data[i].areaLevel == '2' && data[i].pid == selectedProv) {
                                cities.push(data[i]);
                                citiesOpt = citiesOpt + "\r <option value=" + data[i].id + " >" + data[i].name + "</option>";
                            }
                        }
                        $('#cityIdSelect').html(citiesOpt);
                        $('#cityIdSelect').data(selectedProv, cities);
                        //初始化完成时校验是否存在初始值
                        var sourceCityId = $("#cityIdEle").val();
                        if(typeof sourceCityId != 'undefined' && $.trim(sourceCityId) != ''){
                        	$('#cityIdSelect').val(sourceCityId);
                        	$('#cityIdSelect').trigger('change')
                        }
                    } else {
                        alert("无法获取地市信息！");
                    }
                }
            });
        }
        
    });

    //区县下拉
    $('#cityIdSelect').change(function () {
    	var selectedCityOpt = $('#cityIdSelect option:selected');
    	$('input[name="cityName"]').val(selectedCityOpt.text());
        var selectedCity = selectedCityOpt.val();
        if ($('#countyIdSelect').data(selectedCity)) {
            var data = $('#countyIdSelect').data(selectedCity);
            var countiesOpt = "<option selected value=''>-区县-</option>";
            for (var i in data) {
                if (data[i].areaLevel == '3' && data[i].pid == selectedCity) {
                    countiesOpt = countiesOpt + "\r <option value=" + data[i].id + " >" + data[i].name + "</option>";
                }
            }
            $('#countyIdSelect').html(countiesOpt);
        } else {
            $.ajax({
                type: 'POST',
                url: 'pub/area/listChildren',
                dataType: 'json',
                data: {pid: selectedCity},
                success: function (result) {
                    if (typeof result != 'undefined' && result.errorCode == '0') {
                        data = result.data;
                        var counties = new Array();
                        var countiesOpt = "<option selected value=''>-区县-</option>";
                        for (var i in data) {
                            if (data[i].areaLevel == '3' && data[i].pid == selectedCity) {
                                counties.push(data[i]);
                                countiesOpt = countiesOpt + "\r <option value=" + data[i].id + " >" + data[i].name + "</option>";
                            }
                        }
                        $('#countyIdSelect').html(countiesOpt);
                        $('#countyIdSelect').data(selectedCity, counties);
                        //初始化完成时校验是否存在初始值
                        var sourceCountyId = $("#countyIdEle").val();
                        if(typeof sourceCountyId != 'undefined' && $.trim(sourceCountyId) != ''){
                        	$('#countyIdSelect').val(sourceCountyId);
                        }
                    } else {
                        alert("无法获取区县信息！");
                    }
                }
            });
        }
    });

    //区县选中
    $('#countyIdSelect').change(function(){
    	var selectedCountyOpt = $('#countyIdSelect option:selected');
    	$('input[name="countyName"]').val(selectedCountyOpt.text());
    })

    //添加送达方
    $('#confirmDistributionBtn').click(function () {
    	distributionFormToCard();
    });
    
    //编辑送达方信息
    $("a[name='editDisBtn']").on("click",function(){
    	var editDiv = $(this).closest("div[id^='distribution_']");
    	$("#editRecevingModal input[id^='form_distribute_input']").each(function(){
    		var sourceName = $(this).attr("id").split("form_distribute_input_")[1];
    		$(this).val(editDiv.find("input[name='"+sourceName+"']:eq(0)").val());
    	});
    	$('#editRecevingModal').attr("sourceId",editDiv.attr("id")).modal();
    })
    
    //删除送达方信息
    $("a[name='delDisBtn']").on("click",function(){
    	var editDiv = $(this).closest("div[id^='distribution_']");
    	var existedId = editDiv.find("input[name='id']").val()
    	if(typeof existedId != 'undefined' && $.trim(existedId) != ''){
    		//删除已存在的送达方
    		var delDists = $("#delDists").val();
    		if(typeof delDists != 'undefined' && $.trim(delDists).length >0){
    			var delDistArray = delDists.split(",");
    			delDistArray.push(existedId);
    			$("#delDists").val(delDistArray.join(","));
    		}else{
    			$("#delDists").val(existedId);
    		}
    	}
    	//删除送达方卡片
		editDiv.remove();
    })
    
    //送达方信息编辑完成后赋值
    $('#confirmDisUpdateBtn').click(function(){
    	var sourceId = $('#editRecevingModal').attr("sourceId");
    	var editDiv = $('div[id="'+sourceId+'"]');
    	$("#editRecevingModal input[id^='form_distribute_input']").each(function () {
            var inputName = $(this).attr("id").split('form_distribute_input_')[1];
            editDiv.find('input[name="' + inputName + '"]').val($(this).val()).next('span').text($(this).val());
        });
    });
    
    //保存按钮
    $("#saveBtn").click(function(){
    	var message = "";
    	if(!$("#contactName").val()){
    		message += "采购经理/";
    	}
    	if(!$("#tel").val()){
    		message += "财务电话/";
    	}
    	if(!$("#input_businessLicense").val()){
    		message += "营业执照号/";
    	}
    	if(!$("#addPid").val()){
    		message += "配送商/";
    	}
    	if(!$("#provIdSelect").val() || !$("#cityIdSelect").val() || !$("#countyIdSelect").val()){
    		message += "所属地区/";
    	}
    	if(message.length>0){
    		$.messager.popup(message+"不能为空");
    		return;
    	}
    	distributionsRename();
    	$("#saveForm").attr("action","customer/save.html?isRetail=1").submit();
    })
    
    //提交按钮
    $("#submitBtn").click(function(){
    	distributionsRename();
    	$("#saveForm").attr("action","customer/submit.html").submit();
    })
    
    //部分保存
    $('a.block-save-link').filter(':has(.icon-save)').click(function(){
    	distributionsRename();
    	$("#saveForm").attr("action","customer/partlySave.html").submit();
    });
});

/**
 * 重命名送达方信息
 */
function distributionsRename(){
	$("[id^='distribution_']:visible").each(function(index,dom){
		$(dom).attr("id","distribution_"+index);
		$(dom).find("input").each(function(){
			var oname = $(this).attr("name");
			var nname = "distributions["+index+"]."+oname;
			$(this).attr("name",nname);
		})
	});
	//阻止无效的dom元素提交
	$("[id^='distribution_']:hidden").find("input").attr("disabled","true");
}

/**
 * 送达方信息由表单转为卡片
 */
function distributionFormToCard() {
    var distribute_last = $("[id^='distribution_']:last");
    var distribute_box_index = distribute_last.attr("id").split('distribution_')[1]
    var distribute_x = distribute_last.clone();
    distribute_x.attr('id', 'distribution_' + (Number(distribute_box_index) + 1));
    distribute_last.after(distribute_x);
    $("#addRecevingModal input[id^='form_distribute_input']").each(function () {
        var inputName = $(this).attr("id").split('form_distribute_input_')[1];
        distribute_last.find('input[name="' + inputName + '"]').val($(this).val());
        distribute_last.find('input[name="' + inputName + '"]').after($('<span>').text($(this).val()));
    });
    distribute_last.show();
    $("#addRecevingModal input[id^='form_distribute_input']").val('');
}

/**
 * 初始化地区省市信息
 */
function initAreas() {
    //地区下拉框
    if ($('#provIdSelect').data('areas')) {
        return;
    }
    $.ajax({
        type: 'POST',
        url: 'pub/area/list',
        dataType: 'json',
        success: function (result) {
            if (typeof result != 'undefined' && result.errorCode == '0') {
                data = result.data;
                $('#provIdSelect').data('areas', data);
                var provs = new Array();
                var provOpt = "<option selected value=''>-省份-</option>";
                for (var i in data) {
                    if (data[i].areaLevel == '1' || data[i].pid == '0000') {
                        provs.push(data[i]);
                        provOpt = provOpt + "\r <option value=" + data[i].id + " >" + data[i].name + "</option>";
                    }
                }
                $('#provIdSelect').html(provOpt);
                $('#provIdSelect').data('provs', provs);
                //初始化完成时校验是否存在初始值
                var sourceProvId = $("#provIdEle").val();
                if(typeof sourceProvId != 'undefined' && $.trim(sourceProvId) != ''){
                	$('#provIdSelect').val(sourceProvId);
                	$('#provIdSelect').trigger('change')
                }
            } else {
                alert("无法获取区域信息！");
            }
        }
    });
}

function checkFile() {
    var fileList = $(".file-list>li");
    if (fileList.length > 0) {
        $(".file-list-box").fadeIn();
    } else {
        $(".file-list-box").fadeOut();
    }
}

/**
 * 添加待上传的文件
 */
function addFiles() {
	var newFileId = ''+new Date().getTime()+Math.floor(Math.random()*10);
    var fileBox = $("#file-select");
    var fileBoxNew = fileBox.clone();
    fileBox.after(fileBoxNew);
    var file = fileBox.prop('files')[0];
    var filePath = fileBox.val()
    var fileName = file.name;
    fileBox.attr("id","file_"+newFileId).removeAttr("onchange").attr("name","files");
    fileBox.hide();
    if (file) {
        var item = '<li>' +
                '<span class="file-name" data-file-src="' +
                filePath + '">' +
                fileName +
                '</span>' +
                '<a href="javascript:;" class="pull-right del-file" id="del_file_'+newFileId+'" title="删除附件"><i class="icon icon-remove"></i></a>' +
                $('<div>').append().html() +
                '</li>';
        $(".file-list").append(item);

    }
    else {
        alert('文件读取错误。');
    }
    checkFile();
}