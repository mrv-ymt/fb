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
<script src="<c:url value='/js/script.js'/>"></script>
<script src="<c:url value='/js/admin/admin-script.js'/>"></script>
<script src="<c:url value='/plugins/notify/notify.min.js'/>"></script>
</head>
<body id="admin-edit-competition-page">
	<!-- Include Header -->
	<jsp:include page="admin-header.jsp"></jsp:include>
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<jsp:include page="admin-left-nav-bar.jsp"></jsp:include>
		<div class="col-md-10 col-xs-12">
			<div class="row border-green">
				<div class="form-cap"><i class="glyphicon glyphicon-user"></i>&nbsp;Group Info</div>
				<form id="addGroupForm" method="POST" action="<c:url value='/admin/addgroup'/>">
					<div class="col-lg-3 col-md-3 col-xs-12">
						<div  class="avatar-area">
							<img src="<c:url value='/images/nophoto.jpg'/>"  id="avatar" class="img-thumbnail"/>
							<a class="camera-edit"><img src="<c:url value='/images/camera-edit.png'/> "/></a>
							<input type="file" id="file-upload" onchange="loadFile(event)" accept="image/*" name="groupLogoUrl"/>
							<input type="hidden" id="fileToBase64"/>
						</div>
					</div>
					<div class="col-lg-9 col-md-9 col-xs-12">
						<div class="table-responsive border-gray competition-info-area">
							<table class="table">
								<tr>
									<td class="col-1"><spring:message code='label.group.name' />
									</td>
									<td class="col-2" >
										<input type="text" name="groupName" id="groupName" class="form-control"/>
									</td>
								</tr>
								<tr>
									<td class="col-1"><spring:message code='label.group.description' /></td>
									<td class="col-2" >
										<input type="text" name="groupDescription" id="groupDescription" class="form-control" />
									</td>
								</tr>
							</table>
						</div>
						<div class="edit-open-modal">
							<input type="submit" class="style-btn" value="Save" />
							<input class="style-btn btn btn-warning" type="button" value="Cancel" id="cancelBtn" data-goBackUrl ="<c:url value='/admin/group'/>" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
