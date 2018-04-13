/**
 * Created by Administrator on 2016/7/8.
 */

var dummyJsonData= function(){

    this.jsonFIFAHeaders=[{
        columns:[
            {dataIndex:'structure'},
            {dataIndex:'organizationNum'},
            /*{dataIndex:'histologicType'},*/
            /*{dataIndex:'supOrganization'},*/
            /*{dataIndex:'jobName'},*/
            {dataIndex:'principal'},
            {dataIndex:'positionType'},
            /*{dataIndex:'authotatus'},*/
            {dataIndex:'operate'}
        ],
        dataObject:{structure:'组织名称',organizationNum:'组织代码',/*jobName:'岗位名称',*/principal:'负责人',
            positionType:'职位类型',operate:'操作'},
        trAttributeNames:['classStyle','style'],
        trAttributeValueObject:{classStyle:'headerbg',style:''}
    }/*	,
     {
     columns:[
     {dataIndex:'title4',
     tdAttributeNames:['colSpan','id'],
     tdAttributeValueObject:{colSpan:2,id:'sdfsdf'}
     },
     {dataIndex:'title5'}
     ],
     dataObject:{title4:'title4',title5:'title5'},
     trAttributeNames:['classStyle','style'],
     trAttributeValueObject:{classStyle:'headerbg',style:''}
     }*/

    ];

    this.jsoninitNodes=[
        {id:'group_a',order:1,dataObject:{structure:'调味品事业部',organizationNum:'10002',principal:'宋健',positionType:'全职'},userObject:{isGroup:true}},
        {id:'structure_1',pid:'group_a',dataObject:{structure:'食用盐',organizationNum:'10002',principal:'XXX',positionType:'全职'}},
        {id:'structure_2',pid:'structure_1',dataObject:{structure:'成都益盐堂',organizationNum:'10002',principal:'XXX',positionType:'全职'}},
        {id:'structure_3',pid:'structure_2',dataObject:{structure:'陈少华大区',organizationNum:'10002',principal:'XXX',positionType:'全职'}},
        {id:'structure_4',pid:'structure_3',dataObject:{structure:'川渝地区',organizationNum:'10002',principal:'XXX',positionType:'全职'}},
    ];
}

//ExpandNodeEvent
function fifaExpandNodeEvent(node, tree){
    if (node.isLoad == false) {
        tree.startLoadingNode(node);

        //you logic,you can call ajax here , when call success ,use function 'endLoadingNode(node)' to init the loading node;

        //example for my dummy data logic
        var userObject=node.userObject;
        if(userObject!=null){
            var jsonName=userObject.jsonName;
            if(jsonName!=null){
                var jsonNodes=eval('new dummyJsonData().'+jsonName);
                if(jsonNodes!=null){
                    tree.loadingAddNodes(jsonNodes,node.id);
                }
            }
        }
        /////////////////////////////////

        tree.endLoadingNode(node);
    }
}

//infoObj {dataValue:,node:,tabletreeObj:,rowObj:,rowIndex:,container:,columnIndex:}
function operateRenderFunction(infoObj){
    var value=infoObj.dataValue;
    var node=infoObj.node;
    var tree=infoObj.tabletreeObj;
    var aElobj1="<a  class='update'>"+"编辑</a>"+"<a href='#' class='grant  hide'>"+" 授权</a>"+"<a href='javascritp:void(0);'  class='del'>"+" 删除</a>";
    $('.del').each(function(){
    	$(this).click(function(){
    		$(this).parent().parent().parent().remove();
    		return false;
    	})
    })
$(".update").each(function(){
	$(this).click(function(){
        var name = $(this).parents("tr").find("td:eq(0)").text();
        var num = $(this).parents("tr").find("td:eq(1)").text();
        var principalName = $(this).parents("tr").find("td:eq(3)").text();
        $("#organizationName").val(name);
        $("#supOrganization").val(num);
       $("#principal option").each(function(index,elent){
    	   if($(elent).text() == principalName){
    		   $(elent).attr("selected","selected");
               $("#principal").next().find(".selectbox").html(principalName);
    	   }
       });
        $("#principal option").each(function(index,elent){
            if($(elent).text() == principalName){
                $(elent).attr("selected","selected");
                $("#principal").next().find(".selectbox").html(principalName);
            }
        });
		$('#myModal').modal('show')
	})
    });
    return aElobj1;
}

///////////////////////////////////////////////////////////

//the flow of build tabletree

//create and config tabletree object
var fifaGirdTree=new Core4j.toolbox.TableTree4j({
    columns:[
        {isNodeClick:true,dataIndex:'structure',width:'15%',overflow:'hidden'},
        {dataIndex:'organizationNum',width:'5%'},
        /*{dataIndex:'histologicType',width:'7%',canSort:true},*/
     /*   {dataIndex:'supOrganization',width:'7%',canSort:true},
        {dataIndex:'businessArea',width:'7%',canSort:true},*/
        /*{dataIndex:'jobName',width:'7%',canSort:true},*/
        {dataIndex:'principal',width:'7%',canSort:true},
        {dataIndex:'positionType',width:'7%',canSort:true},
      /*  {dataIndex:'productCategory',width:'7%',canSort:true},
        {dataIndex:'creater',width:'7%',canSort:true},
        {dataIndex:'createTime',width:'7%',canSort:true},
        {dataIndex:'authotatus',width:'7%',canSort:true},*/
        {width:'10%',canSort:false,renderFunction:operateRenderFunction}
    ],
    treeMode:'gird',
    renderTo:'worldcupgird',
    useLine:true,
    useIcon:true,
    id:'myworldcupgirdtree',
    useCookie:false,
    onExpandNodeEvents:[fifaExpandNodeEvent],
    headers:new dummyJsonData().jsonFIFAHeaders,
    //footers:jsonfooters,
    themeName:'arrow',
    selectMode:'single'
    //floatRight:true
});

//build tree by nodes
fifaGirdTree.build(new dummyJsonData().jsoninitNodes,true);

////////////////////////////////////////////////////////////////

function sortTree(index){
    if(index==null){
        fifaGirdTree.getNodeById();
    }else{
        fifaGirdTree.sortByColumnIndex(index);
    }
}

function changeStyle(theme){
    fifaGirdTree.changeTheme(theme);
}
var setting = {
    check : {
        enable : false
    },
    view : {
        dblClickExpand : false,
        showLine : true,
        selectedMulti : false
    },
    async : {
        dataType : "json",
        enable : true,
        type : "post",
        url : "Org/list",
        dataFilter: function(treeId, parentNode, responseData){
            return responseData.data;
        }
    },
    data : {
        key : {
            name : "name"
        },
        simpleData : {
            enable : true,
            idKey : "id",
            pIdKey : "pid",
            rootPId : "0"
        }
    },
    callback : {
        onClick : function(event, treeId, treeNode, clickFlag) {
            showNodeInfo(treeNode);
        }
    }
};
$(function() {
    var t = $("#tree");
    t = $.fn.zTree.init(t, setting);
});
function showNodeInfo(treeNode,validate) {
    if (treeNode.pid == "0") {
        $("#baseInfo").addClass("hidden");
    } else {
        autoEdit(treeNode);

        if (treeNode.mgrLevel == 2) {
            if (treeNode.branchType == 0) {
                $("#editmgrLevel").val("-1");
            } else {
                $("#editmgrLevel").val("1");
            }
        }
        $("#baseInfo").removeClass("hidden");
    }
}
$("#jobName").tinyselect();
$("#principal").tinyselect();
$(document).ready(function(){
    $("#editOrganization").validate({
        rules: {
            organizationName: "required",
            organizationNum: "required",
            principal:{
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
            }

        }
    });
    $.cookie("username","");
    $('#worldcupgird  tbody tr').click(function () {
        var value = $(this).find('td:eq(0)').text();
        $.cookie("username",value);
    });
    $("#addOrganization").on("click",function(){
    	if($.cookie("username") == ""|| $.cookie("username") == null||$.cookie("username") == "undefined"){
    		alert("未选择上级组织，请先选着上级组织")
    	}else{
            $("#supOrganization").val($.cookie("username"));
            $('#myModal').modal('show');
        }
    });
    $(".modal-footer button:first-child").click(function(){
        $.cookie("username","");
        $("#organizationName").val("");
    });
    $(".modal-header .close").click(function(){
    	$.cookie("username","");
        $("#organizationName").val("");
        /*alert($("#principal option:selected").val());*/
    });
 });

