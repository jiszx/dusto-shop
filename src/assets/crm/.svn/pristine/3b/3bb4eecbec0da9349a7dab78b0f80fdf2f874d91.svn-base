<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Left side column. contains the logo and sidebar -->
<aside class="left-side sidebar-offcanvas">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <!--
            <div class="pull-left image">
                <img src="static/images/avatar3.png" class="img-circle" alt="User Image" />
            </div>
            -->
            <div class="pull-left info">
                <p>您好, ${user.name}</p>
				
                <%-- 岗位切换js 写在footjs --%>
                <c:if test="${user.isSalesman=='1'}">
                    <c:if test="${stations.size() > 0}">
                <div class="dropdown">
                    <a href="javascript:;" class="dropdown-toggle" id="postName" data-toggle="dropdown" style="font-size: 12px; color:#f39c12;">
                      		<small>${station.orgname}-${station.provName}-${station.stationname}</small>
                        <span class="caret" style="color:#999;"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-postName" aria-labelledby="postName">
                       <!--  <li><a href="javascript:;">岗位A</a></li>
                        <li><a href="javascript:;">岗位B</a></li>
                        <li><a href="javascript:;">岗位C</a></li> -->
                        <c:forEach items="${stations}" var="userstations" varStatus="status">
                        	<%-- <li value="${status.index}"><a href="javascript:;">${userstations.orgname}-${userstations.provName}-${userstations.stationname}</a></li> --%>
                        	<li value="${userstations.stationid}"><a href="javascript:;">${userstations.orgname}-${userstations.provName}-${userstations.stationname}</a></li>
                        </c:forEach>
                    </ul>
                </div>
                </c:if>
                </c:if>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li>
                <a href="index.jhtml">
                    <i class="icon icon-dashboard"></i> <span>总览</span>
                </a>
            </li>
            <c:forEach items="${user_menus}" var="menu">
                <li class="treeview">
                    <a href="#">
                        <i class="${menu.node.iconclass}"></i>
                        <span>${menu.node.resName}</span>
                        <i class="icon icon-double-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <c:forEach items="${menu.nodes}" var="sub">
                            <li><a href="${sub.resUrl}" class="leftNav" target="view_mainFrame"><i class="${sub.iconclass}"></i> ${sub.resName}</a></li>
                        </c:forEach>
                    </ul>
                </li>
            </c:forEach>
        </ul>
    </section>
</aside>

