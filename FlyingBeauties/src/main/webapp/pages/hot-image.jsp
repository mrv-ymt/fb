<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>FLying Beauties</title>
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
<body id="hot-image-page">    
    <jsp:include page="header-login.jsp"></jsp:include>
    <div class="container content">
        <div class="list-picture">
            <ul>                
                <c:forEach items="${imageInfoR2S.listImage}" var="imageInfoDTO">
                	<li class="border-green">
	                    <div class="video-wraper form-group">	                        
	                        <div class="profile-area">
	                        	<div class="pull-left">
 	                            	<img  src="<c:url value='/avatars/${imageInfoDTO.user.avatarUrl}'/>"/> 
	                            </div>
	                            <div class="overflow">
	                            	<h3>${imageInfoDTO.user.firstName}</h3>
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
		                            &nbsp;&nbsp;<a><i class="fa fa-comments" aria-hidden="true">&nbsp;</i>${imageInfoDTO.commentNum}</a>
	                            </div>
	                        </div>
	                    </div>
						<!--  Modal -->
	                    <div class="modal fade" id="modal${imageInfoDTO.imageId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button>
                                    </div>
                                    <div id="feedback"></div>
                                    <div class="modal-body row">
                                        <div class="col-md-7 col-xs-12 full-size-image form-group">
                                            <input type="hidden" id="pathFullImage-${imageInfoDTO.imageId}" value="<c:url value='/uploadimages/${imageInfoDTO.imageUrl}_m.jpg'/>" />
                                           	<img id="image-${imageInfoDTO.imageId}" />
                                        </div>
                                        <div class="col-md-5 col-xs-12">
                                            <div class="comment-area">
                                                <div class="profile-area">
						                        	<div class="pull-left">
						                            	<img  src="<c:url value='/avatars/${imageInfoDTO.user.avatarUrl}'/>"/> 
						                            </div>
						                            <div>
						                            	<h3>${imageInfoDTO.user.firstName}</h3>
						                            	<p><spring:message code='label.from' /> <b>${imageInfoDTO.groupName}</b></p>
						                            	<p class="cptt-name"><spring:message code='label.in' /> <b>${imageInfoDTO.competitionName}</b></p>
						                            </div>
						                        </div>     
                                                <div>                                                   
                                                    <p class="description">${imageInfoDTO.description}</p>
                                                    <p><spring:message code='label.at' /> <b>${imageInfoDTO.locationName}</b></p>
                                                </div>
                                                <div class="point-comment-info row">
                                                   <p class="pull-left lifeTime">&nbsp;${imageInfoDTO.lifeTime}</p>
						                            <div class="pull-right">
							                            <a><i class="glyphicon glyphicon-star-empty"></i>&nbsp;${imageInfoDTO.point}</a>
							                            &nbsp;&nbsp;<a><i class="fa fa-comments" aria-hidden="true">&nbsp;</i>${imageInfoDTO.commentNum}</a>
						                            </div>
                                                </div>                                                                                                                                         
                                                <div class="mCustomScrollbar list-comment row" data-mcs-theme="inset">
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
			<ul class="pagination pagination-lg">		
				<li class="available-${imageInfoR2S.pager.firstPage}">
					<a href="<c:url value='/welcome/${imageInfoR2S.pager.firstPage}'/>"><i class="glyphicon glyphicon-fast-backward"></i></a>
				</li>
				<li class="available-${imageInfoR2S.pager.previousPage}">
					<a href="<c:url value='/welcome/${imageInfoR2S.pager.previousPage}'/>"><i class="glyphicon glyphicon-step-backward"></i></a>
				</li>		    
			    <c:forEach items="${imageInfoR2S.pager.pageNumberList}" var="pageNumber">		    
					<c:choose>
					    <c:when test="${pageNumber.equals(imageInfoR2S.pager.currentPage)}">
					    	<li class="active"><a href="<c:url value='/welcome/${pageNumber}'/> ">${pageNumber}</a></li>
					    </c:when>
					    <c:otherwise>
					    	<li><a href="<c:url value='/welcome/${pageNumber}'/> ">${pageNumber}</a></li>
					    </c:otherwise>
					</c:choose>  
			    </c:forEach>
			    <li class="available-${imageInfoR2S.pager.nextPage}">
					<a href="<c:url value='/welcome/${imageInfoR2S.pager.nextPage}'/>"><i class="glyphicon glyphicon-step-forward"></i></a>
				</li>
				<li class="available-${imageInfoR2S.pager.lastPage}">
					<a href="<c:url value='/welcome/${imageInfoR2S.pager.lastPage}'/>"><i class="glyphicon glyphicon-fast-forward"></i></a>
				</li>
			</ul>
		</div>            
    </div>
    <div class="footer-area-fixed">
        <div class="container">
            <div class="footer-application">              
                <h3 class="pull-left"><spring:message code='label.downloadappdirect' /></h3>
                <a href="#"><img src="<c:url value='/images/download1.png'/>"></a>
                <a href="#"><img src="<c:url value='/images/download3.png'/>"></a>                
            </div>
            <div class="footer-close-btn">
                <a href="#"><img src="<c:url value='/images/close.png'/>"></a>
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
</body>
</html>