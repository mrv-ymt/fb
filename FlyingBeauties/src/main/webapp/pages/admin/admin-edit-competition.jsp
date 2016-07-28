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
</head>
<body id="admin-edit-competition-page">
	<input type="hidden" value="${competitionInfo.competitionId}" id="competitionIdEdit" />
	<input type="hidden" value="${competitionInfo.competitionLogoUrl}" id="competitionLogoUrl" />
	<!-- Include Header -->
	<jsp:include page="admin-header.jsp"></jsp:include>
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<jsp:include page="admin-left-nav-bar.jsp"></jsp:include>
		<div class="col-md-10 col-xs-12">
			<div class="row border-green">
				<div class="form-cap"><i class="glyphicon glyphicon-user"></i>&nbsp;Competition Info</div>			
				<form id="editCompetitionForm" action="<c:url value='/admin/editcompetition'/>" method="POST">
					<div class="col-lg-3 col-md-3 col-xs-12">
						<div  class="avatar-area">
							<img src="<c:url value='/avatars/${competitionInfo.competitionLogoUrl}'/>"  id="avatar" class="img-thumbnail"/> 
							<a class="camera-edit"><img src="<c:url value='/images/camera-edit.png'/> "/></a>					
							<input type="file" id="file-upload" onchange="loadFile(event)" accept="image/*" />	
							<input type="hidden" id="fileToBase64"/>
						</div>
					</div>	
					<div class="col-lg-9 col-md-9 col-xs-12">						
						<div class="table-responsive border-gray competition-info-area"> 
							<table class="table">							
								<tr>
									<td class="col-1">Competition Name</td>
									<td class="col-2" >
										<input type="text" value="${competitionInfo.competitionName}" name="competitionName" id="competitionName" class="form-control" placeholder="Competition Name" />
									</td>
								</tr>
								<tr>
									<td class="col-1">Competition Rewards</td>
									<td class="col-2" >
										<input type="text" value="${competitionInfo.competitionRewards}" name="competitionRewards" id="competitionRewards" class="form-control" placeholder="Competition Rewards" />
									</td>
								</tr>
								<tr>
									<td class="col-1">Hot Rewards Competition</td>
									<td class="col-2" >
										<div class="radio">
											<input type="hidden" value="${competitionInfo.hotRewards}" id="hotRewardsHdn"/>
											<label><input type="radio" name="hotRewards" value="1">Yes</label>
											<label><input type="radio" name="hotRewards" value="0">No</label>
										</div>
									</td>
								</tr>
								<tr>
									<td class="col-1">Add One Point</td>
									<td class="col-2" >
										<input type="number" name="initPoint" id="initPoint" class="form-control" value="${competitionInfo.initPoint}" placeholder="Point Add On" />
									</td>
								</tr>
								<tr>
									<td class="col-1">Begin Time</td>
									<td class="col-2" >
										<div id="date_picker1" class="input-group date">                                             		
											<input type="text" value="${competitionInfo.beginTime}" name="beginTime" id="beginTime" class="form-control datepicker" placeholder="DD-MM-YYYY" />
											<span class="add-on input-group-addon">
												<i data-time-icon="icon-time" data-date-icon="icon-calendar" class="fa fa-calendar" aria-hidden="true"></i>
											</span>
										</div>   
									</td>
								</tr>	
								<tr>
									<td class="col-1">End Time</td>
									<td class="col-2" >
										<div id="date_picker2" class="input-group date">                                             		
											<input type="text" value="${competitionInfo.endTime}" name="endTime" id="endTime" class="form-control datepicker" placeholder="DD-MM-YYYY" />
											<span class="add-on input-group-addon">
												<i data-time-icon="icon-time" data-date-icon="icon-calendar" class="fa fa-calendar" aria-hidden="true"></i>
											</span>
										</div>  
									</td>
								</tr>		
								<tr>
									<td class="col-1">Description</td>
									<td class="col-2" >
										<textarea rows="5" class="form-control" name="description" id="description" >${competitionInfo.description}</textarea>
									</td>
								</tr>	
								<tr>
									<td class="col-1">Group</td>
									<td class="col-2" >
										<select class="form-control" name="groups" id="groups" multiple="multiple">
											 <c:forEach var="selectedGroup" items="${selectedGroupTable[true]}">
			                                	<option value="${selectedGroup.groupId}" selected="selected">${selectedGroup.groupName}</option>
										    </c:forEach>
											<c:forEach var="nonSelectedGroup" items="${selectedGroupTable[false]}">
			                                	<option value="${nonSelectedGroup.groupId}">${nonSelectedGroup.groupName}</option>
										    </c:forEach>
										</select>
									</td>
								</tr>	
								<tr>
									<td class="col-1">Term and Condition</td>
									<td class="col-2" >
										<textarea rows="5" class="form-control" name="termAndCondition" id="termAndCondition" >${competitionInfo.termAndCondition}</textarea>
									</td>
								</tr>						
							</table>
						</div>
						<div class="edit-open-modal">
							<input type="submit" class="style-btn" value="Save" />	
							<input class="style-btn" type="button" value="Cancel" id="cancelBtn" data-goBackUrl ="<c:url value='/admin/competition'/>"/>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script src="<c:url value='/js/jquery.mCustomScrollbar.concat.min.js'/>"></script>
<script src="<c:url value='/js/jquery.validate.js'/>"></script>
<script src="<c:url value='/js/script.js'/>"></script>
<script src="<c:url value='/js/admin/admin-script.js'/>"></script>
</html>