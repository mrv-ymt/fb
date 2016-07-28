<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code='label.competitions' /></title>
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
<script src="<c:url value='/js/admin/admin-script.js'/>"></script>
</head>
<body id="list-competition-page">
	<!-- Include Header -->
	<jsp:include page="admin-header.jsp"></jsp:include>
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<jsp:include page="admin-left-nav-bar.jsp"></jsp:include>
		<div class="col-md-10 col-sm-12 col-xs-12 pull-right">
			<div class="list-competition card-list">
				<ul>
					<c:forEach items="${listCompetition}" var="competitionInfo">
						<li class="border-green">
							<div class="competition-content card row">
								<c:if test="${competitionInfo.hotRewards == 1}">
									<div class="icon-hot">
										<img src="<c:url value='/images/icon_hot.png'/>" />
									</div>
								</c:if>									
								<div class="col-md-5 col-xs-12 competition-ava card-avatar">
									<img src="<c:url value='/avatars/${competitionInfo.competitionLogoUrl}'/>" />
								</div>	
								<div class="col-md-7 col-xs-12  main-info">
									<div class="statistic">									
										<a href="/web/admin/editcompetition/${competitionInfo.competitionId}"><h3>${competitionInfo.competitionName}</h3></a>
										<p><spring:message code='label.totalgroup' />: ${competitionInfo.groupNum}</p>
										<p><spring:message code='label.totalmember' />: ${competitionInfo.participants}</p>
										<p><spring:message code='label.totalimage' />: ${competitionInfo.imageNum}</p>	
									</div>
									<div class="btn-join">
										<a>${competitionInfo.beginTime} <i class="glyphicon glyphicon-arrow-right"></i> ${competitionInfo.endTime}</a>
									</div>	
									<div class="modal fade" id="myModalConfirm-${competitionInfo.competitionId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
										<div class="modal-dialog modal-sm" role="document">
											<div class="modal-content">
												<div class="modal-header">			
													<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button>
													<span class="modal-title" id="myModalLabel">Do you want delete competition?</span>											
												</div>
												<div class="modal-footer">
													<button type="button" onclick="deleteCompetition(${competitionInfo.competitionId});" class="btn btn-primary"><spring:message code='button.yes' /></button>
													<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.no' /></button>													
											  </div> 
											</div>
										</div>									
									</div>
								</div>	
								<div class="modify-cptt-area">
									<a href="/web/admin/editcompetition/${competitionInfo.competitionId}" class="pull-left" ><img  src='<c:url value='/images/iconpen.png' />'></a>
									&nbsp;<a href="#" data-toggle="modal" data-target="#myModalConfirm-${competitionInfo.competitionId}"><img  src='<c:url value='/images/icondelete.png' />'></a>
								</div>						
							</div>												
						</li>	
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="add-btn">
		<a href="/web/admin/addcompetition"><img src="<c:url value='/images/add_btn01_off.png' />" /></a>
	</div>
</body>
</html>