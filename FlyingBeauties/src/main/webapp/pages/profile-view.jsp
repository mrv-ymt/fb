<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code='label.profile' /></title>
<link rel="icon" type="image/gif" href="<c:url value='/images/logo2.png'/>" />

<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>" type="text/css" />
<link rel="stylesheet" href="<c:url value='/css/font-awesome.min.css'/>" type="text/css" />
<link rel="stylesheet" href="<c:url value='/css/bootstrap-datetimepicker.min.css'/>" type="text/css" />
<link rel="stylesheet" href="<c:url value='/css/jquery.mCustomScrollbar.css'/>" type="text/css" />
<link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css" />

<script src="<c:url value='/js/jquery-2.2.3.min.js'/>"></script>
<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/bootstrap-datetimepicker.min.js'/>"></script>
<script src="<c:url value='/js/jquery.mCustomScrollbar.concat.min.js'/>"></script>
<script src="<c:url value='/js/jquery.validate.js'/>"></script>
<script src="<c:url value='/js/submit-handler.js'/>"></script>
<script src="<c:url value='/js/script.js'/>"></script>
</head>
<body id="user-profile-page">
	<!-- Include Header -->
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<jsp:include page="left-nav-bar.jsp"></jsp:include>
		<div class="col-md-10 col-xs-12">
			<div class="row border-green">
				<div class="form-cap"><i class="glyphicon glyphicon-user"></i>&nbsp;${userDTO.firstName} ${userDTO.lastName}</div>				
				<div class="col-lg-4 col-md-4 col-xs-12">
					<div  class="avatar-area">
						<img src="<c:url value='/avatars/${userDTO.avatarUrl}'/> "  id="avatar" class="img-thumbnail"/> 
					</div>
				</div>	
				<div class="col-lg-8 col-md-8 col-xs-12">						
					<div class="table-responsive border-gray info-area"> 
						<table class="table">							
							<tr class="full-name">
								<td class="col-1"><spring:message code='label.firstname' /></td>
								<td class="col-2" >
									<div class="static-text">${userDTO.firstName}</div>
								</td>
							</tr>
							<tr class="full-name">
								<td class="col-1"><spring:message code='label.lastname' /></td>
								<td class="col-2" >
									<div class="static-text">${userDTO.lastName}</div>
								</td>
							</tr>
							<tr class="country">
								<td class="col-1"><spring:message code='label.country' /></td>
								<td class="col-2" >
									<div class="static-text">${userDTO.country}</div>
								</td>
							</tr>	
							<tr class="city">
								<td class="col-1"><spring:message code='label.city' /></td>
								<td class="col-2" >
									<div class="static-text">${userDTO.city}</div>
								</td>
							</tr>
							<tr>
								<td class="col-1"><spring:message code='label.totalimage' /></td>
								<td class="col-2" >
									<div class="static-text">${userDTO.imageNum}</div>
								</td>
							</tr>
							<tr>
								<td class="col-1"><spring:message code='label.totalpoint' /></td>
								<td class="col-2" >
									<div class="static-text">${userDTO.sumPoint}</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="edit-open-modal">
						<button type="button" class="style-btn" onclick="window.history.back();"><spring:message code='button.back' /></button>						
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="mgbt139">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>