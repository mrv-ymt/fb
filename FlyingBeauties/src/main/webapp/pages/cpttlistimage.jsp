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
<title>${competitionInfo.competitionName}</title>
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
<body id="cptt-list-image-page">
	<!-- Include Header -->
	<sec:authorize access="isAuthenticated()">
		<jsp:include page="header.jsp"></jsp:include>
	 </sec:authorize>
	 <sec:authorize access="isAnonymous()">
		<jsp:include page="header-login.jsp"></jsp:include>
	 </sec:authorize>
	
	<!-- 	Hidden -->
	<input type="hidden" class="competitionHdn" value="${competitionInfo.competitionId}" />
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<jsp:include page="left-nav-bar.jsp"></jsp:include>
        <div class="col-md-10 col-sm-12 col-xs-12 pull-right">
        	<div class="group-info border-green form-group row">
       			<div class="col-xs-5 col-sm-4 col-md-3">
       				<img class="group-logo" src="<c:url value='/avatars/${competitionInfo.competitionLogoUrl}'/>" />
       			</div>
       			<div class="col-xs-7 col-sm-5 col-md-5 row cptt-info">
       				<p class="group-name">${competitionInfo.competitionName}</p>
       				<p><spring:message code='label.totalgroup' />: ${competitionInfo.groupNum}</p>
       				<p><spring:message code='label.totalmember' />: ${competitionInfo.participants}</p>
       				<p><spring:message code='label.totalimage' />: ${competitionInfo.imageNum}</p>
       				<p><spring:message code='label.period' />: ${competitionInfo.beginTime} <i class="glyphicon glyphicon-arrow-right"></i> ${competitionInfo.endTime}</p>
       				<p>${competitionInfo.description}</p>
       				<c:if test="${competitionInfo.hotRewards == 1}">
						<div class="icon-hot">
							<img src="<c:url value='/images/icon_hot.png'/>" />
						</div>
					</c:if>	
       			</div>
       			<div class="top-user-area col-xs-12 col-sm-3 col-md-3">
       				<p class="title"><spring:message code='label.topuser' /></p>
       				<ul>
       					<c:forEach items="${competitionInfo.listUser}" var="userInfo">
       						<li class="col-sm-12 col-xs-4">
	       						<div class="profile-area">
		                        	<div class="pull-left">
	 	                            	<a href="<c:url value='/user/userprofile/${userInfo.userId}'/>"><img  src="<c:url value='/avatars/${userInfo.avatarUrl}'/>"/></a>
		                            </div>
		                            <div class="overflow">
		                            	<a href="<c:url value='/user/userprofile/${userInfo.userId}'/>"><h3>${userInfo.firstName}</h3></a>
		                            	<p class="pull-left"><i class="glyphicon glyphicon-star-empty"></i>&nbsp;<b>${userInfo.sumPoint}</b></p>
		                            	<p><i class="glyphicon glyphicon-picture"></i>&nbsp;<b>${userInfo.imageNum}</b></p>
		                            	
		                            </div>
		                        </div>
	       					</li>
       					</c:forEach>       					
       				</ul>
       			</div>
       			<div class="top-group-area col-xs-12">
       				<p class="title"><spring:message code='label.topgroup' /></p>
       				<ul>
       					<c:forEach items="${competitionInfo.listGroup}" var="groupInfo">
       						<li class="col-xs-12 col-md-4">
	       						<div class="profile-area">
		                        	<div class="pull-left">
	 	                            	<img  src="<c:url value='/avatars/${groupInfo.groupLogoUrl}'/>"/> 
		                            </div>
		                            <div class="info">
		                            	<h3>${groupInfo.groupName}</h3>
		                            	<p class="pull-left"><i class="glyphicon glyphicon-user"></i>&nbsp;<b>${groupInfo.participants}</b></p>
		                            	<p class="pull-left"><i class="glyphicon glyphicon-star-empty"></i>&nbsp;<b>${groupInfo.totalPoint}</b></p>
		                            	<p class="pull-left"><i class="glyphicon glyphicon-picture"></i>&nbsp;<b>${groupInfo.imageNum}</b></p>		                            	
		                            </div>
		                        </div>
	       					</li>
       					</c:forEach>       					
       				</ul>
       			</div>       			
        	</div>
        	<div class="order-by-choose row">					
				<label class="pull-left"><spring:message code='label.orderby' />&nbsp;</label>
				<div class="col-md-3 col-sm-4 col-xs-8">
					<input type="hidden" id="orderByHidden" value="${orderBy}" />
					<select class="form-control" id="orderBy-cptt-list-image">
						<option value="1"><spring:message code='label.orderby.date' /></option>
						<option value="2"><spring:message code='label.orderby.location' /></option>
						<option value="3"><spring:message code='label.orderby.species' /></option>
						<option value="4"><spring:message code='label.orderby.point' /></option>
					</select>
				</div>
			</div>
        	<div class="list-picture row">
	            <ul>                
	                <c:forEach items="${imageInfoR2S.listImage}" var="imageInfoDTO">
	                	<li class="border-green">
		                    <div class="video-wraper form-group">	                        
		                        <div class="profile-area">
		                        	<div class="pull-left">
	 	                            	<a href="<c:url value='/user/userprofile/${imageInfoDTO.user.userId}'/>"><img  src="<c:url value='/avatars/${imageInfoDTO.user.avatarUrl}'/>"/></a>
		                            </div>
		                            <div class="overflow">
		                            	<a href="<c:url value='/user/userprofile/${imageInfoDTO.user.userId}'/>"><h3>${imageInfoDTO.user.firstName}</h3></a>
		                            	<p><spring:message code='label.from' /> <b data-toggle="tooltip" title="${imageInfoDTO.groupName}">${imageInfoDTO.groupName}</b></p>
		                            </div>
		                        </div>
		                        <div class="main-image">
		                            <a href="#video" onclick="openModal(${imageInfoDTO.imageId});"><img class="image-orgi" src="<c:url value='/uploadimages/${imageInfoDTO.imageUrl}_t.jpg'/>" /></a>
		                            <div class="zoom-view-location">
		                            	<input type="hidden" class="pathFullImageFile" value="<c:url value='/uploadimages/${imageInfoDTO.imageUrl}_b.jpg'/>" />
		                            	<img class="thumbnail-image" src="<c:url value='/images/editor-zoom-in-plus-add-glyph.png'/>"/>
		                            </div>
		                        </div>
		                        <div class="detail-area">
		                            <p class="taxonName"><b><spring:message code='label.taxonName' /></b> ${imageInfoDTO.taxonName}</p>
	                            	<p class="description"><b><spring:message code='label.image.desc' /></b> ${imageInfoDTO.description}</p>
		                            <a href='#' onclick="openModal(${imageInfoDTO.imageId});" style="display: none;">...  <spring:message code='label.seemore' /></a>
		                        </div>
		                        <div class="point-comment-info">
		                        	<p class="pull-left lifeTime">&nbsp;${imageInfoDTO.lifeTime}</p>
		                            <div class="pull-right">
			                            <a><i class="glyphicon glyphicon-star-empty"></i>&nbsp;${imageInfoDTO.point}</a>
			                            &nbsp;&nbsp;<a class="comment-num-${imageInfoDTO.imageId}" ><i class="fa fa-comments" aria-hidden="true">&nbsp;</i><span>${imageInfoDTO.commentNum}</span></a>
		                            </div>
		                        </div>
		                    </div>
							<!--  Modal -->
		                    <div class="modal fade" id="modal${imageInfoDTO.imageId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	                        	<div class="modal-dialog modal-lg" role="document">
	                                <div class="modal-content">
	                                    <div class="modal-header">
	                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button>
	                                        <span class="modal-title" id="myModalLabel">Image</span>
	                                    </div>
	                                    <div id="feedback"></div>
	                                    <div class="modal-body row">
	                                    	<input type="hidden" id="uploader-userId-${imageInfoDTO.imageId}" value="${imageInfoDTO.user.userId}" />
	                                        <div class="col-md-7 col-xs-12 full-size-image form-group">
	                                            <input type="hidden" id="pathFullImage-${imageInfoDTO.imageId}" value="<c:url value='/uploadimages/${imageInfoDTO.imageUrl}_m.jpg'/>" />
                                            	<img id="image-${imageInfoDTO.imageId}" />
	                                        </div>
	                                        <div class="col-md-5 col-xs-12">
	                                            <div class="comment-area">
	                                                <div class="profile-area">
							                        	<div class="pull-left">
							                            	<a href="<c:url value='/user/userprofile/${imageInfoDTO.user.userId}'/>"><img  src="<c:url value='/avatars/${imageInfoDTO.user.avatarUrl}'/>"/></a> 
							                            </div>
							                            <div>
							                            	<a href="<c:url value='/user/userprofile/${imageInfoDTO.user.userId}'/>"><h3>${imageInfoDTO.user.firstName}</h3></a>
							                            	<p><spring:message code='label.from' /> <b>${imageInfoDTO.groupName}</b></p>
						                            		<p class="cptt-name"><spring:message code='label.in' /> <b>${imageInfoDTO.competitionName}</b></p>
							                            </div>
							                        </div>     
	                                                <div>                                                   
	                                                    <p>${imageInfoDTO.description}</p>
	                                                    <p><spring:message code='label.at' /> <b>${imageInfoDTO.locationName}</b></p>
	                                                </div>
	                                                <div class="point-comment-info row">
	                                                   <p class="pull-left lifeTime">&nbsp;${imageInfoDTO.lifeTime}</p>
							                            <div class="pull-right">
								                            <a><i class="glyphicon glyphicon-star-empty"></i>&nbsp;${imageInfoDTO.point}</a>
								                            &nbsp;&nbsp;<a class="comment-num-md-${imageInfoDTO.imageId}"><i class="fa fa-comments" aria-hidden="true">&nbsp;</i><span>${imageInfoDTO.commentNum}</span></a>
							                            </div>
	                                                </div>
	                                               <div class="write-comment row">
														<div class="avatar"> 
															<a href="#"><img  src="<c:url value='/avatars/${userInfo.avatarUrl}'/>"/> </a>
														</div>
														<div class="input-comment">
															<input type="text" class="form-control" onkeypress="saveComment(event, ${imageInfoDTO.imageId}, ${userInfo.userId}, this);" placeholder="<spring:message code='placeholder.writecomment' />..." >
														</div>
													</div>	                                                                                        
	                                                <div class="mCustomScrollbar list-comment row">
	                                                    <ul id="list-comment-${imageInfoDTO.imageId}"></ul>	                                                    
	                                               </div>	                                               
	                                            </div>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>  
	                        </div>
		                </li>   
	                </c:forEach>
	            </ul>
	        </div>  
	        <div class="pager row">
	        	<input type="hidden" id="currentPage" value="${imageInfoR2S.pager.currentPage}" />
				<ul class="pagination pagination-lg">		
					<li class="available-${imageInfoR2S.pager.firstPage}">
						<a href="<c:url value='/competition/${imageInfoR2S.pager.firstPage}/${orderBy}?id=${competitionInfo.competitionId}'/>"><i class="glyphicon glyphicon-fast-backward"></i></a>
					</li>
					<li class="available-${imageInfoR2S.pager.previousPage}">
						<a href="<c:url value='/competition/${imageInfoR2S.pager.previousPage}/${orderBy}?id=${competitionInfo.competitionId}'/>"><i class="glyphicon glyphicon-step-backward"></i></a>
					</li>		    
				    <c:forEach items="${imageInfoR2S.pager.pageNumberList}" var="pageNumber">		    
						<c:choose>
						    <c:when test="${pageNumber.equals(imageInfoR2S.pager.currentPage)}">
						    	<li class="active"><a href="<c:url value='/competition/${pageNumber}/${orderBy}?id=${competitionInfo.competitionId}'/> ">${pageNumber}</a></li>
						    </c:when>
						    <c:otherwise>
						    	<li><a href="<c:url value='/competition/${pageNumber}/${orderBy}?id=${competitionInfo.competitionId}'/> ">${pageNumber}</a></li>
						    </c:otherwise>
						</c:choose>  
				    </c:forEach>
				    <li class="available-${imageInfoR2S.pager.nextPage}">
						<a href="<c:url value='/competition/${imageInfoR2S.pager.nextPage}/${orderBy}?id=${competitionInfo.competitionId}'/>"><i class="glyphicon glyphicon-step-forward"></i></a>
					</li>
					<li class="available-${imageInfoR2S.pager.lastPage}">
						<a href="<c:url value='/competition/${imageInfoR2S.pager.lastPage}/${orderBy}?id=${competitionInfo.competitionId}'/>"><i class="glyphicon glyphicon-fast-forward"></i></a>
					</li>
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
	<!-- The Modal -->
    <div class="full-view">
		<div id="view-full-image-modal" class="modal">
		  	<span class="close-modal">×</span>
		  	<img class="modal-content" id="img01">
		  	<div id="caption"></div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>