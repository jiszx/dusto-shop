<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<link rel="shortcut icon" type="image/ico" href="favicon.ico">
<link href="${applicationScope.STATIC}static/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- font Awesome-->
<link href="static/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" /> 
<link href="${applicationScope.STATIC}static/css/commons.css" rel="stylesheet" type="text/css" />
<%-- <script id="myjs" src="${applicationScope.STATIC}static/js/jquery.min.js" type="text/javascript"></script> --%>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if IE]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<script type="text/javascript">
</script>
<![endif]-->
<c:if test="${noCache == 'YES' }">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</c:if>
<script type="text/javascript">
function loadJS(id,url){
    var  xmlHttp = null;
    if (!!window.ActiveXObject || "ActiveXObject" in window) {
    //if(window.ActiveXObject)//IE{
        try {
            //IE6以及以后版本中可以使用
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        }
        catch (e) {
            //IE5.5以及以后版本可以使用
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    else if(window.XMLHttpRequest)//Firefox，Opera 8.0+，Safari，Chrome
    {
        xmlHttp = new XMLHttpRequest();
    }
    //采用同步加载
    xmlHttp.open("GET",url,false);
    //发送同步请求，如果浏览器为Chrome或Opera，必须发布后才能运行，不然会报错
    xmlHttp.send(null);
    //4代表数据发送完毕
    if ( xmlHttp.readyState == 4 )
    {
        //0为访问的本地，200到300代表访问服务器成功，304代表没做修改访问的是缓存
        if((xmlHttp.status >= 200 && xmlHttp.status <300) || xmlHttp.status == 0 || xmlHttp.status == 304)
        {
            var myHead = document.getElementsByTagName("HEAD").item(0);
            var myScript = document.createElement( "script" );
            myScript.language = "javascript";
            myScript.type = "text/javascript";
            myScript.id = id;
            try{
                //IE8以及以下不支持这种方式，需要通过text属性来设置
                myScript.appendChild(document.createTextNode(xmlHttp.responseText));
            }
            catch (ex){
                myScript.text = xmlHttp.responseText;
            }
            myHead.appendChild( myScript );
            return true;
        }
        else
        {
            return false;
        }
    }
    else
    {
        return false;
    }
}
</script>
<script type="text/javascript">
    var currentUser = "${user.loginName}";
    window.IMAGE_BASEURI='<%= application.getAttribute("attachmentBASEURI")%>'
    var url;
    if (!!window.ActiveXObject || "ActiveXObject" in window) {
    	url ="static/js/jquery.min.1.11.js";
    }else{
    	url ="static/js/jquery.min.js";
    }
    loadJS("myjs",url);
</script>
