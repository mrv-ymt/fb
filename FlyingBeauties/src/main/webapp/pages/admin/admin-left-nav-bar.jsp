<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ page isELIgnored="false" %>
<div class="col-md-2 nav-bar hidden-sm hidden-xs">
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
	</ul>
</div>