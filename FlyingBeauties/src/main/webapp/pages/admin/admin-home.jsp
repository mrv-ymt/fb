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
<script src="<c:url value='/js/admin/admin-script.js'/>"></script>

</head>
<body id="admin-home-page">
    <!-- Include Header -->
	<jsp:include page="admin-header.jsp"></jsp:include>
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<jsp:include page="admin-left-nav-bar.jsp"></jsp:include>
		<div class="col-md-10 col-xs-12">
	        <div class="list-group">
		      	<table class="table_02">
		  			 <thead>
		     			<tr>
				        	<th class="col-1">Name</th>
				        	<th class="col-2">Username</th>
				        	<th class="col-3">Email</th>
				        	<th class="col-1">Country</th>
				        	<th class="col-1">Group</th>
				        	<th class="deactive">Active</th>
				      	</tr>
				    </thead>
				    <tbody>
				    	<c:forEach items="${listUserR2S.listUser}" var="userInfo">
				    		<tr>
					        	<td><a href="<c:url value='/admin/userprofile/${userInfo.userId}'/>">${userInfo.firstName} ${userInfo.lastName}</a></td>
					        	<td>${userInfo.username}</td>
					       		<td>${userInfo.email}</td>
					       		<td>${userInfo.country}</td>
					       		<td>${userInfo.groupName}</td>
					       		<c:choose>
					       			<c:when test="${userInfo.status eq 0}">
					       				<td class="deactive"><div class="btn btn-primary" onclick="activeUser(${userInfo.userId}, ${listUserR2S.pager.currentPage});">Active</div></td>
					       			</c:when>
					       			<c:otherwise>
					       				<td class="deactive"><div class="btn btn-warning" onclick="deactiveUser(${userInfo.userId}, ${listUserR2S.pager.currentPage});">Deactive</div></td>
					       			</c:otherwise>
					       		</c:choose>
					      	</tr>
				    	</c:forEach>
				    </tbody>
				</table>
		        <div class="pager row">
		        	<input type="hidden" id="currentPage" value="${listUserR2S.pager.currentPage}" />
					<ul class="pagination pagination-lg">		
						<li class="available-${listUserR2S.pager.firstPage}">
							<a href="<c:url value='/admin/listuser/${listUserR2S.pager.firstPage}'/>"><i class="glyphicon glyphicon-fast-backward"></i></a>
						</li>
						<li class="available-${listUserR2S.pager.previousPage}">
							<a href="<c:url value='/admin/listuser/${listUserR2S.pager.previousPage}'/>"><i class="glyphicon glyphicon-step-backward"></i></a>
						</li>		    
					    <c:forEach items="${listUserR2S.pager.pageNumberList}" var="pageNumber">		    
							<c:choose>
							    <c:when test="${pageNumber.equals(listUserR2S.pager.currentPage)}">
							    	<li class="active"><a href="<c:url value='/admin/listuser/${pageNumber}'/> ">${pageNumber}</a></li>
							    </c:when>
							    <c:otherwise>
							    	<li><a href="<c:url value='/admin/listuser/${pageNumber}'/> ">${pageNumber}</a></li>
							    </c:otherwise>
							</c:choose>  
					    </c:forEach>
					    <li class="available-${listUserR2S.pager.nextPage}">
							<a href="<c:url value='/admin/listuser/${listUserR2S.pager.nextPage}'/>"><i class="glyphicon glyphicon-step-forward"></i></a>
						</li>
						<li class="available-${listUserR2S.pager.lastPage}">
							<a href="<c:url value='/admin/listuser/${listUserR2S.pager.lastPage}'/>"><i class="glyphicon glyphicon-fast-forward"></i></a>
						</li>
					</ul>
				</div>   
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