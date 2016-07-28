<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Administrator</title>
<link rel="shortcut icon" type="image/png" href="<c:url value='/images/logo2.png'/>" />

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
<body id="admin-home-page">
    <!-- Include Header -->
	<jsp:include page="admin-header.jsp"></jsp:include>
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<jsp:include page="admin-left-nav-bar.jsp"></jsp:include>
		<div class="col-md-10 col-sm-12 col-xs-12 pull-right">
			<c:if test="${notJoinCptt}">
				<div class="inform-choose-cptt border-green"><p><spring:message code='inform.choosecptt' /></p></div>
			</c:if>
			<div class="list-competition">
				<ul>
					<li class="border-green">
							<div class="competition-content row">
								<div class="col-md-5 col-xs-12 competition-ava">
									<img src="<c:url value='/avatars/1467347620052lnlrkui8em.png'/>" />
								</div>	
								<div class="col-md-7 col-xs-12  main-info">
									<div class="statistic">									
										<h3 class="group-name">Tour Guild</h3>
										<p><spring:message code='label.totalmember' />: 20</p>
										<p><spring:message code='label.totalimage' />: 20</p>	
										<p><spring:message code='label.totalpoint' />: 20</p>	
									</div>
									<div class="btn-join">
										<button type="button" class="style-btn pull-left">Choose Competition</button>
									</div>									
								</div>						
							</div>												
						</li>	
						<li class="border-green">
							<div class="competition-content row">
								<div class="col-md-5 col-xs-12 competition-ava">
									<img src="<c:url value='/avatars/1467347620052lnlrkui8em.png'/>" />
								</div>	
								<div class="col-md-7 col-xs-12  main-info">
									<div class="statistic">									
										<p class="group-name">Tour Guild</p>
										<p><spring:message code='label.totalmember' />: 20</p>
										<p><spring:message code='label.totalimage' />: 20</p>	
										<p>Description: 20</p>	
									</div>
									<div class="btn-join">
										<button type="button" class="style-btn pull-left">Choose Competition</button>
									</div>									
								</div>						
							</div>												
						</li>	
					<c:forEach items="${listCompetition}" var="competitionInfo">
						<li class="border-green">
							<div class="competition-content row">
								<div class="col-md-5 col-xs-12 competition-ava">
									<img src="<c:url value='/avatars/1467347620052lnlrkui8em.png'/>" />
								</div>	
								<div class="col-md-7 col-xs-12  main-info">
									<div class="statistic">									
										<p class="group-name">Tour Guild</p>
										<p><spring:message code='label.totalmember' />: 20</p>
										<p><spring:message code='label.totalimage' />: 20</p>	
										<p>Description: 20</p>	
									</div>
									<div class="btn-join">
										<button type="button" class="style-btn pull-left">Choose Competition</button>
									</div>									
								</div>						
							</div>												
						</li>	
					</c:forEach>
				</ul>
			</div>
		</div>
    </div>
    <div class="modal fade confirm-modal" id="confirmDeleteComment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">			
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button>
					<span class="modal-title" id="myModalLabel"><spring:message code='message.confirmdeletecomment' /></span>											
				</div>
				<div class="modal-footer">			
					<div class="accept-btn pull-left"></div>		
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code='button.no' /></button>													
			  </div> 
			</div>
		</div>									
	</div>
</body>
</html>