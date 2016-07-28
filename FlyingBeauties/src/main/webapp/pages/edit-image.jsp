<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %> 
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code='label.editimage' /></title>
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

<!-- Upload Image Plugin -->
<script type="text/javascript" src="<c:url value='/js/jquery.filer.min.js?v=1.0.5'/>"></script>
<script type="text/javascript" src="<c:url value='/js/custom.js?v=1.0.5'/>"></script>

<!-- API Google Map -->
<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyC62BHocVpODwRFBRNCWprT-mrG4CftMzo"></script>
<script src="<c:url value='/js/script.js'/>"></script>
<script src="<c:url value='/js/googlemap-api.js'/>"></script>
</head>
<body class="upload-image-page">
	 <!-- Include Header -->
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<jsp:include page="left-nav-bar.jsp"></jsp:include>
		<div class="col-md-10 col-xs-12">
			<div class="desciption-area border-green row">
				<div class="form-cap"><i class="glyphicon glyphicon-edit"></i>&nbsp;<spring:message code='label.editimage' /></div>
				<form id="editImageForm">
					<div class="col-md-4 col-sm-4 col-xs-12">
						<div class="main-content border-gray">
							<div class="profile-area row">
								<img class="pull-left" src="<c:url value='/avatars/${userInfo.avatarUrl}'/>" />
								<a><h2>${userInfo.firstName}</h2></a>
							</div>
							<div class="form-group"> 
								<span><spring:message code='label.specieslb' /></span>
								<input type="hidden" value="${imageInfo.speciesId}" id="speciesIdHdn"/>
								<select class="form-control" id="speciesId">
									<c:forEach items="${listSpecies}" var="species">
										<c:choose>
											<c:when test="${species.taxonId == imageInfo.speciesId}">
												<option value="${species.nameId}" selected>${species.taxonName}</option>
											</c:when>
											<c:otherwise>
												<option value="${species.nameId}">${species.taxonName}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>	
									<c:choose>
										<c:when test="${imageInfo.speciesId == 0}">
											<option value="0" selected><spring:message code='label.other' /></option>
										</c:when>
										<c:otherwise>
											<option value="0"><spring:message code='label.other' /></option>
										</c:otherwise>
									</c:choose>								
									
								</select>								
							</div>
							<div class="form-group"> 
								<span><spring:message code='label.description' /></span>
								<textarea class="form-control" id="description" name="description" >${imageInfo.description}</textarea>
							</div>
							<div class="form-group">
								<span><spring:message code='label.competition' /></span>
								<input type="hidden" value="${imageInfo.competitionId}" id="competitionIdHdn"/>
								<select class="form-control" id="competitionId">
									<c:forEach items="${listCompetition}" var="competition">
										<c:if test="${competition.joined == 1}">
											<c:choose>
												<c:when test="${competition.competitionId == imageInfo.competitionId}">
													<option value="${competition.competitionId}" selected>${competition.competitionName}</option>
												</c:when>
												<c:otherwise>
													<option value="${competition.competitionId}">${competition.competitionName}</option>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach>						
								</select>
							</div>
							<div class="right-btn">
								<input type="submit" class="style-btn" value="<spring:message code='button.edit' />" />
							</div>
						</div>
					</div>
					<div class="col-lg-8 col-sm-8 col-xs-12">
						<div class="upload-area border-gray">						
							 <img class="image"  src="<c:url value='/uploadimages/${imageInfo.imageUrl}_m.jpg'/>" />	
							 <input type="hidden" value="${imageInfo.imageId}" id="imageIdHdn"/>				
						</div>
						<div class="view-map border-gray">
							<div class="form-group">
								<div class="input-group">
								  	<span class="input-group-addon" id="basic-addon1"><i class="glyphicon glyphicon-map-marker"></i></span>
								  	<input type="text" class="form-control" value="${imageInfo.locationName}" name="locationName" id="locationName" placeholder="<spring:message code='placeholder.location' />" aria-describedby="basic-addon1">
								</div>
								<input type="hidden" id="lngHdn" value="${imageInfo.lng}" />
								<input type="hidden" id="latHdn" value="${imageInfo.lat}" />								
							</div>
							<div class="right-btn">
								<button type="button" class="style-btn" data-toggle="modal" data-target="#myModalEditImage">
								<i class="glyphicon glyphicon-search"></i>&nbsp;<spring:message code='button.location' /></button>
							</div>
							<!-- Modal -->
							<div class="modal fade" id="myModalEditImage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							  	<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button>
										</div>
										<div class="modal-body">
											<div id="googleMap" class="googleMap"></div>										
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close' /></button>
											<button type="button" class="btn btn-primary" id="savePositionBtn" data-dismiss="modal"><spring:message code='button.save' /></button>
									  	</div>
									</div>
							  	</div>  
							</div>
						</div>					
					</div>	
				</form>			
			</div>				
		</div>			
	</div>	
	<div class="mgbt100">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>