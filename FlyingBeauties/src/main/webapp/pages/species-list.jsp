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
</head>
<body id="species-list-page">
	<!-- Include Header -->
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<jsp:include page="left-nav-bar.jsp"></jsp:include>
		<div class="col-md-10 col-sm-12 col-xs-12 pull-right">
			<c:if test="${notJoinCptt}">
				<div class="inform-choose-cptt border-green"><p><spring:message code='inform.choosecptt' /></p></div>
			</c:if>
			<div class="list-competition">
				<ul>
					<li class="border-green">
						<div class="competition-content row">					
							<div class="col-md-5 col-xs-12 competition-ava">
								<img src="<c:url value='/avatars/14676157906277p3ihvjmmt.png'/>" />
							</div>	
							<div class="col-md-7 col-xs-12  main-info">
								<div class="statistic">									
									<h3>Yellow Bee</h3>
									<p>Scientific Name: Yellow Bee</p>
									<p>Yellow Bee is a beautiesful bee</p>	
								</div>						
							</div>						
						</div>												
					</li>	
					<li class="border-green">
						<div class="competition-content row">					
							<div class="col-md-5 col-xs-12 competition-ava">
								<img src="<c:url value='/avatars/146760613235657f722gip7.png'/>" />
							</div>	
							<div class="col-md-7 col-xs-12  main-info">
								<div class="statistic">									
									<h3>Yellow Bee</h3>
									<p>Scientific Name: Yellow Bee</p>
									<p>Yellow Bee is a beautiesful bee</p>	
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
									<h3>Yellow Bee</h3>
									<p>Scientific Name: Yellow Bee</p>
									<p>Yellow Bee is a beautiesful bee</p>	
								</div>						
							</div>						
						</div>												
					</li>	
					<li class="border-green">
						<div class="competition-content row">					
							<div class="col-md-5 col-xs-12 competition-ava">
								<img src="<c:url value='/avatars/1467600583918rv80t2og38.png'/>" />
							</div>	
							<div class="col-md-7 col-xs-12  main-info">
								<div class="statistic">									
									<h3>Yellow Bee</h3>
									<p>Scientific Name: Yellow Bee</p>
									<p>Yellow Bee is a beautiesful bee</p>	
								</div>						
							</div>						
						</div>												
					</li>	
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
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="What is your favourite insect?">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="What is your favourite butterfly? ">
							</div>
							<div class="form-group">
								<input type="text" class="form-control"  placeholder="Do you like take a photo about insect?">
							</div>
							<div class="form-group">
								<select class="form-control">
									<option>Choose Group</option>
									<option>Group A</option>
									<option>Group B</option>
									<option>Group C</option>
									<option>Group D</option>
								</select>
							</div>
							<div class="form-group">
								<select class="form-control">
									<option>Viet Nam</option>
									<option>Philipin</option>
								</select>
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="City">
							</div>													
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
					<div class="submit-survey-btn"></div>
				</div>
			</div>
		</div>  
	</div>
	<div class="mgbt100">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>	
</body>
</html>