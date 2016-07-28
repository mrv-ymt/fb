<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
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
<link rel="stylesheet" href="<c:url value='/plugins/select2/css/select2.min.css'/>" type="text/css" />

<script src="<c:url value='/js/jquery-2.2.3.min.js'/>"></script>
<script src="<c:url value='/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/bootstrap-datetimepicker.min.js'/>"></script>
<script src="<c:url value='/js/jquery.mCustomScrollbar.concat.min.js'/>"></script>
<script src="<c:url value='/js/jquery.validate.js'/>"></script>
<script src="<c:url value='/js/submit-handler.js'/>"></script>
<script src="<c:url value='/js/script.js'/>"></script>
<script src="<c:url value='/plugins/select2/js/select2.full.min.js'/>"></script>
<c:set var="baseUrl">
	<c:url value='/'/>
</c:set>
</head>
<body id="list-competition-page">
	<!-- Include Header -->
	<sec:authorize access="isAuthenticated()">
		<jsp:include page="header.jsp"></jsp:include>
	 </sec:authorize>
	 <sec:authorize access="isAnonymous()">
		<jsp:include page="header-login.jsp"></jsp:include>
	 </sec:authorize>
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<jsp:include page="left-nav-bar.jsp"></jsp:include>
		<div class="col-md-10 col-sm-12 col-xs-12 pull-right">
			<c:if test="${notJoinCptt}">
				<div class="inform-choose-cptt border-green"><p><spring:message code='inform.choosecptt' /></p></div>
			</c:if>
			<div class="list-competition">
				<ul>
					<c:forEach items="${listCompetition}" var="competitionInfo">
						<li class="border-green">
							<div class="competition-content row">
								<c:if test="${competitionInfo.hotRewards == 1}">
									<div class="icon-hot">
										<img src="<c:url value='/images/icon_hot.png'/>" />
									</div>
								</c:if>									
								<div class="col-md-5 col-xs-12 competition-ava">
									<img src="<c:url value='/avatars/${competitionInfo.competitionLogoUrl}'/>" />
								</div>	
								<div class="col-md-7 col-xs-12  main-info">
									<div class="statistic">									
										<a href="<c:url value='/competition?id=${competitionInfo.competitionId}'/>"><h3>${competitionInfo.competitionName}</h3></a>
										<p><spring:message code='label.totalgroup' />: ${competitionInfo.groupNum}</p>
										<p><spring:message code='label.totalmember' />: ${competitionInfo.participants}</p>
										<p><spring:message code='label.totalimage' />: ${competitionInfo.imageNum}</p>	
									</div>
									<div class="btn-join">
										<input type=hidden id="termAndCondition-cptt-${competitionInfo.competitionId}" value="${competitionInfo.termAndCondition}" />
										<c:choose>
											<c:when test="${competitionInfo.joined == 1}">
												<button type="button" class="style-btn disabled pull-left" disabled="disabled"><spring:message code='button.joined' /></button>
											</c:when>
											<c:otherwise>
												 <sec:authorize access="isAuthenticated()">
													<button type="button" class="style-btn pull-left" onclick="joinCompetition(${competitionInfo.competitionId}, ${baseUrl});"><spring:message code='button.join' /></button>
												 </sec:authorize>
												 <sec:authorize access="isAnonymous()">
													<button type="button" class="style-btn pull-left" onclick="joinCompetition(${competitionInfo.competitionId},${baseUrl}, true);"><spring:message code='button.join' /></button>
												 </sec:authorize>
											</c:otherwise>
										</c:choose>										
										<a class="pull-right">${competitionInfo.beginTime} <i class="glyphicon glyphicon-arrow-right"></i> ${competitionInfo.endTime}</a>
									</div>									
								</div>						
							</div>												
						</li>	
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="survey-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button>
					<span class="modal-title" id="myModalLabel">Survey</span>
				</div>
				<div class="modal-body row">
					<div class="survey-content center border-green">
					
					</div>
						
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
					<div class="submit-survey-btn"></div>
				</div>
			</div>
		</div>  
	</div>	
	<div class="modal fade" id="competition-group-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button>
					<span class="modal-title" id="myModalLabel"><spring:message code='label.group' /></span>
				</div>
				<div class="modal-body ">
					<select class="competition-group form-group" style="width: 100%"></select>
					<div class="squaredThree">
						<input type="checkbox" name="confirmTerm" checked id="confirmTerm"/>
						<label class="chk-label" for="confirmTerm"></label><label class="label-term" for="confirmTerm">&nbsp;&nbsp;<a href="#" id="term-flyingbeauties-link"><spring:message code='label.termcompetition' /></a></label>
					</div>	
					<p id="cptt-term" class="border-gray"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.close' /></button>
					<button type="button" class="btn btn-primary btn-group"><spring:message code='button.save' /></button>
				</div>
			</div>
		</div>  
	</div>	
	<div class="mgbt100">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>