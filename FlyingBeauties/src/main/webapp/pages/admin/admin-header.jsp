<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ page isELIgnored="false" %>
<script src="<c:url value='/js/bootstrap-datetimepicker.min.js'/>"></script>
<c:if test="${pageContext.response.locale eq 'vi'}">
	<script src="<c:url value='/js/messages_vi.min.js'/>"></script>
</c:if>
<!-- Login User Id Hidden -->
<input type="hidden" id="loginUserId" value="${userInfo.userId}" />
<input type="hidden" id="lang" value="${pageContext.response.locale}" />
<jsp:include page="../base.jsp"></jsp:include>
<div class="header admin-header">
    <div class="container">
        <div class="pull-left">
            <div class="logo pull-left hidden-sm hidden-xs">
                <a href="<c:url value='/admin'/>"><img src="<c:url value='/images/FlyingBeautiesLogo_88x70.png'/>"  /></a>
            </div>
            <div class="visible-xs visible-sm pull-left nav-bar-reponsive">
                <a href="#" class="nav-bar-toggle"><i class="glyphicon glyphicon-align-justify"></i></a>
                <div class="nav-bar reponsive" style="display:none;">
                    <ul>
                        <li class="sup-li menu-profile">		
							<a href="<c:url value='/user/profile'/>"><i class="glyphicon glyphicon-user"></i>&nbsp;<spring:message code='label.editprofile' /></a>
						</li>
						<li class="sup-li">
							<a href="<c:url value='/admin'/>">Users Management</a>
						</li>
						<li class="sup-li">
							<a href="<c:url value='/admin/group'/>">Group</a>
						</li>
						<li class="sup-li">
							<a href="<c:url value='/admin/competition'/>">Competition</a>
						</li>
                        <li class="sup-li"><a href="<c:url value='/j_spring_security_logout'/>"><i class="glyphicon glyphicon-off">&nbsp;</i><spring:message code='label.logout' /></a></li>
                    </ul>
                </div>
            </div>
            <div class="menu-items pull-right">
                <ul>                
                    <li class="style-nav menu-home"><a href="<c:url value='/admin'/>"><i class="glyphicon glyphicon-home"></i>&nbsp;FLYINGBEATIES ADMINISTRATOR</a></li>
                </ul>
            </div>                              
        </div>
        <div class="pull-right dropdown">
            <div class="profile-area pull-right">
                <a href="<c:url value='/user/profile'/>"><img id="header-avatar" src="<c:url value='/avatars/${userInfo.avatarUrl}'/>"/>&nbsp;${userInfo.firstName}</a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="glyphicon glyphicon-triangle-bottom"></i></a>
                <ul class="dropdown-menu">
                    <li class="menu-profile"><a href="<c:url value='/user/profile'/>"><i class="glyphicon glyphicon-user">&nbsp;</i><spring:message code='label.profile' /></a></li>
                    <li class="divider"></li>
                    <li><a href="<c:url value='/j_spring_security_logout'/>"><i class="glyphicon glyphicon-off">&nbsp;</i><spring:message code='label.logout' /></a></li>
                </ul>   
            </div>          
        </div>              
    </div>
</div>