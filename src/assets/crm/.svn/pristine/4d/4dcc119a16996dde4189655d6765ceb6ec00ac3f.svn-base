$(function () {
    $("#wizard").bwizard({
        backBtnText:"上一步",
        nextBtnText:"下一步",
        stepHeaderTemplate:"<li><em>#{title}</em></li>"
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
    $("#stepLink > li").click(function () {
        $("#stepLink > li").removeClass("active");
        $(this).addClass("active");
    });
    function changeLinkState(activeLink) {
        $("#stepLink > li").removeClass("active");
        activeLink.addClass("active");
    }

    //区域初始化
    initAreas();
    
    loadCustomerSelection();

    $('#provIdSelect').focus(function () {
        initAreas();
    });

    //地市下拉
    $('#provIdSelect').change(function () {
    	loadCity($(this));
    });
    $('.modal #form_distribute_input_provId').on("change",function () {
    	loadCity($(this));
    });

    //区县下拉
    $('#cityIdSelect').change(function () {
    	loadCounty($(this));
    });
    $('.modal #form_distribute_input_cityId').on("change",function () {
    	loadCounty($(this));
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
    $("#saveForm").on("click","a[name='editDisBtn']",function(){
    	var editDiv = $(this).closest("div[id^='distribution_']");
    	$("#editRecevingModal [id^='form_distribute_input']").each(function(){
    		var sourceName = $(this).attr("id").split("form_distribute_input_")[1];
    		if($(this)[0].tagName == 'SELECT'){
    			$(this).parent().find('input[id="'+sourceName+'Ele"]').val(editDiv.find("[name='"+sourceName+"']:eq(0)").val());
    		}
    		$(this).val(editDiv.find("[name='"+sourceName+"']:eq(0)").val());
    	});
    	$('#editRecevingModal').attr("sourceId",editDiv.attr("id")).modal();
    })
    
    //删除送达方信息
    $("#saveForm").on("click","a[name='delDisBtn']",function(){
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
    	$("#editRecevingModal [id^='form_distribute_input']").each(function () {
            var inputName = $(this).attr("id").split('form_distribute_input_')[1];
            var inputEle = editDiv.find('input[name="' + inputName + '"]');
        	if($(this)[0].tagName == 'SELECT'){
        		inputEle.val($(this).val());
        		editDiv.find('span[name="' + inputName.replace("Id","Name") + '"]').text($(this).find("option:selected").text());
        	}else{
        		inputEle.val($(this).val()).next('span').text($(this).val());
        	}
        });
    });
    
    //保存按钮
    $("#saveBtn").click(function(){
    	if(validate("#saveForm").form()){
	    	distributionsRename();
	    	$("#saveForm").attr("action","customer/receiver/save.html").submit();
    	}
    })
    
    //提交按钮
    $("#submitBtn").click(function(){
    	if(validate("#saveForm").form()){
	    	distributionsRename();
	    	$("#saveForm").attr("action","customer/receiver/submit.html").submit();
    	}
    })
    
    //电话号校验规则添加
    jQuery.validator.addMethod("isTelNo", function(value, element) {   
        var tel = /(^1\d{10}$)|(\d{3,4}\d{5,10})/;
        return this.optional(element) || (tel.test(value));
    }, "请正确填写您的电话号码!");
    
    $('#customerSelection').chosen({
		no_results_text : "没有找到",
		allow_single_de : true,
		search_contains: true,
		width:"100%"
	});
    
    $('#addRecevingModal,#editRecevingModal').on('shown.bs.modal', function () {
    	//地区下拉框
    	var provIdSelect = $(this).find('select[title="provIdSelect"]');
        if (provIdSelect.data('areas')) {
            return;
        }
        loadProv(provIdSelect);
	})
    
});

/**
 * 字段校验
 */
function validate(formId){
    var validator = $(formId).validate({
        rules: {
        	pid: {required: true},
        	tel: {isTelNo: true},
        	name: {required: true},
        	provId: {required: true},
        	cityId: {required: true},
        	countyId: {required: true}
        },
        showErrors: function(errorMap, errorList) {
        	if(errorList.length > 0){
        		var errors;
        		$.each(errorMap, function(key, val) {
        			if(typeof errors == 'undefined'){
        				errors = errorMap[key];
        			}else{
        				errors = errors + "<br/>" + errorMap[key];
        			}
        		});
        		$("html,body").animate({scrollTop:$("#"+errorList[0].element.id).offset().top},100)
        		var infodiv = '<div class="alert alert-danger" role="alert">'+errorList[0].message+'</div>';
        		$("#infoModal").html(infodiv);
        		$("#infoModal").modal();
        		setTimeout(function(){$("#infoModal").modal("hide")},2000);
        	}
        	this.defaultShowErrors();
        	//处理chosen
        	if($('.chosen-container')[0]){
        		var chosenId = $('.chosen-container').attr("id");
        		var sourceSelectId = chosenId.split("_")[0];
        		if($("#"+sourceSelectId+"")[0]){
        			if($("#"+sourceSelectId+"").is(".input-error")){
        				$("#"+chosenId+"").parent().addClass("input-error")
        			}else{
        				$("#"+chosenId+"").parent().removeClass("input-error")
        			}
        		}
        	}
 	   },
 	   onclick: false,
 	   onkeyup: false,
 	   errorClass: "input-error",
 	   ignore: ""
    });
    return validator;
}

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
    $("#addRecevingModal [id^='form_distribute_input']").each(function () {
        var inputName = $(this).attr("id").split('form_distribute_input_')[1];
        var inputEle = distribute_last.find('input[name="' + inputName + '"]');
    	if($(this)[0].tagName == 'SELECT'){
    		inputEle.val($(this).val());
    		distribute_last.find('span[name="' + inputName.replace("Id","Name") + '"]').text($(this).find("option:selected").text());
    	}else{
    		inputEle.val($(this).val());
        	inputEle.after($('<span>').text($(this).val()));
    	}
    });
    distribute_last.show();
    $("#addRecevingModal input[id^='form_distribute_input']").val('');
}

/**
 * 初始化地区省市信息
 */
function initAreas() {
    //地区下拉框
	var provIdSelect = $('#provIdSelect');
    if (provIdSelect.data('areas')) {
        return;
    }
    loadProv(provIdSelect);
}

function loadProv(provIdSelect){
	$.ajax({
        type: 'POST',
        url: 'pub/area/list',
        dataType: 'json',
        success: function (result) {
            if (typeof result != 'undefined' && result.errorCode == '0') {
                data = result.data;
                provIdSelect.data('areas', data);
                var provs = new Array();
                var provOpt = "<option selected value=''>-省份-</option>";
                for (var i in data) {
                    if (data[i].areaLevel == '1' || data[i].pid == '0000') {
                        provs.push(data[i]);
                        provOpt = provOpt + "\r <option value=" + data[i].id + " >" + data[i].name + "</option>";
                    }
                }
                provIdSelect.html(provOpt);
                provIdSelect.data('provs', provs);
                //初始化完成时校验是否存在初始值
                var sourceProvId = provIdSelect.parent().find("#provIdEle").val();
                if(typeof sourceProvId != 'undefined' && $.trim(sourceProvId) != ''){
                	provIdSelect.val(sourceProvId);
                	provIdSelect.trigger('change')
                }
            } else {
            	$.messager.popup("无法获取区域信息！");
            }
        }
    });
}

function loadCity(provIdSelect){
	var selectedProvOpt = provIdSelect.find('option:selected');
	provIdSelect.parent().find('input[name="provName"]').val(selectedProvOpt.text());
    var selectedProv = selectedProvOpt.val();
    var data;
    var cities = new Array();
    var citiesOpt = "<option selected value=''>-地市-</option>";
    var cityIdSelect = provIdSelect.next('select');
    if (cityIdSelect.data(selectedProv)) {
        data = cityIdSelect.data(selectedProv);
        for (var i in data) {
            if (data[i].areaLevel == '2' && data[i].pid == selectedProv) {
                cities.push(data[i]);
                citiesOpt = citiesOpt + "\r <option value=" + data[i].id + " >" + data[i].name + "</option>";
            }
        }
        cityIdSelect.html(citiesOpt);
        cityIdSelect.data(selectedProv, cities);
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
                    cityIdSelect.html(citiesOpt);
                    cityIdSelect.data(selectedProv, cities);
                    //初始化完成时校验是否存在初始值
                    var sourceCityId = cityIdSelect.parent().find("#cityIdEle").val();
                    if(typeof sourceCityId != 'undefined' && $.trim(sourceCityId) != ''){
                    	cityIdSelect.val(sourceCityId);
                    	cityIdSelect.trigger('change')
                    }
                } else {
                	$.messager.alert("无法获取地市信息！");
                }
            }
        });
    }
}

function loadCounty(cityIdSelect){
	var selectedCityOpt = cityIdSelect.find('option:selected');
	var countyIdSelect = cityIdSelect.next('select');
	cityIdSelect.parent().find('input[name="cityName"]').val(selectedCityOpt.text());
    var selectedCity = selectedCityOpt.val();
    if (countyIdSelect.data(selectedCity)) {
        var data = countyIdSelect.data(selectedCity);
        var countiesOpt = "<option selected value=''>-区县-</option>";
        for (var i in data) {
            if (data[i].areaLevel == '3' && data[i].pid == selectedCity) {
                countiesOpt = countiesOpt + "\r <option value=" + data[i].id + " >" + data[i].name + "</option>";
            }
        }
        countyIdSelect.html(countiesOpt);
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
                    countyIdSelect.html(countiesOpt);
                    countyIdSelect.data(selectedCity, counties);
                    //初始化完成时校验是否存在初始值
                    var sourceCountyId = countyIdSelect.parent().find("#countyIdEle").val();
                    if(typeof sourceCountyId != 'undefined' && $.trim(sourceCountyId) != ''){
                    	countyIdSelect.val(sourceCountyId);
                    	countyIdSelect.parent().find('input[name="countyName"]').val($('#countyIdSelect option:selected').text());
                    }
                } else {
                	$.messager.alert("无法获取区县信息！");
                }
            }
        });
    }
    
    $.ajax({
        type: 'POST',
        url: 'pub/area/rdc',
        dataType: 'text',
        data: {cityId: selectedCity},
        success: function (result) {
            if (typeof result != 'undefined') {
            	//选中市后显示发货仓库
                if($("#rdc_show")[0]){
                	$("#rdc_show input").val(result);
                }else{
                	var vwHtmlContainer = $("<div>").attr("class","form-group col-md-4 col-sm-6 input-box-list").attr("id","rdc_show");
                	var vwHtmlLabel = $("<label>").attr("class","font12 input-box-list-title label-base").text("RDC仓库：");
                	var vwHtmlDiv = $("<div>").attr("class","input-box-list-value");
                	var vwHtmlDivInput = $("<input>").attr("class","form-control").attr("type","text").attr("readonly","true").val(result);
                	vwHtmlDiv = vwHtmlDiv.append(vwHtmlDivInput);
                	vwHtmlContainer.append(vwHtmlLabel).append(vwHtmlDiv);
                	$('#cityIdSelect').closest("div.form-group").after(vwHtmlContainer);
                }
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
 * 加载售达方
 */
function loadCustomerSelection(){
	$.ajax({
	        type:'POST',
	        url:'order/util/customer',
	        data:'custType=1,4,7,70,9&type=1',
	        dataType : 'json',
	        success : function(result) {
	        	if(typeof result != 'undefined' && result.rows && result.rows.length > 0){
	        		data = result.rows;
	  	          	var custOpts = "";
	  	          	for(var i in data){
	  	          		if(data[i].id == $("#customerHiddenInput").val()){
	  	          			custOpts = custOpts + "\r <option value="+ data[i].id +" selected>"+data[i].custname+"</option>";
	  	          		}else{
	  	          			custOpts = custOpts + "\r <option value="+ data[i].id +" >"+data[i].custname+"</option>";
	  	          		}
	  	            }
	  	          	$('#customerSelection').html(custOpts);
	  	          	$("#customerSelection").trigger("chosen:updated");
	        	}/*else{
	        		$.messager.popup("没有售达方信息！");
	        	}*/
	        },
	        error : function(){
	        	$.messager.alert("无法获取售达方信息！");
	        	$('#customerSelection').html("");
	        }
  	});
}
