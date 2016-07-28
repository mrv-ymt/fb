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
<script src="<c:url value='/js/submit-handler.js'/>"></script>
<script src="<c:url value='/js/script.js'/>"></script>
</head>
<body id="profile-page">
	<input type="hidden" value="${userInfo.roleId}" id="roleId" />
	<!-- Include Header -->
	<c:choose>
		<c:when test="${userInfo.roleId == 1}">
			<jsp:include page="admin/admin-header.jsp"></jsp:include>
		</c:when>
		<c:when test="${userInfo.roleId == 3}">
			<jsp:include page="admin/admin-header.jsp"></jsp:include>
		</c:when>
		<c:otherwise>
			<jsp:include page="header.jsp"></jsp:include>
		</c:otherwise>
	</c:choose>
	<div class="container content">
		<!-- Include Left Navigation Bar -->
		<c:choose>
			<c:when test="${userInfo.roleId == 1}">
				<jsp:include page="admin/admin-left-nav-bar.jsp"></jsp:include>
			</c:when>
			<c:when test="${userInfo.roleId == 3}">				
			</c:when>
			<c:otherwise>
				<jsp:include page="left-nav-bar.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
		
		<div class="col-md-10 col-xs-12">
			<div class="row border-green">
				<div class="form-cap"><i class="glyphicon glyphicon-user"></i>&nbsp;<spring:message code='label.profile' /></div>
				<div class="loading">
				 	<p><img src="<c:url value='/images/ajax-loader_1.gif'/>"/> <spring:message code="label.pleasewait" /></p>
				</div>				
				<div class="col-lg-4 col-md-4 col-xs-12">
					<div class="alert alert-info">
					  	<strong><spring:message code='label.notice' />:</strong> <spring:message code='label.notice.content' />
					</div>
					<div  class="avatar-area">
						<img src="<c:url value='/avatars/${userInfo.avatarUrl}'/> "  id="avatar" class="img-thumbnail"/> 
						<a class="camera-edit"><img src="<c:url value='/images/camera-edit.png'/> "/></a>					
							<input type="file" id="file-upload" onchange="loadFile(event)" accept="image/*"/>	
							<input type="hidden" id="fileToBase64"/>
						<input class="style-btn save-avatar"  type="submit" value="<spring:message code='button.save' />" />
					</div>
				</div>	
				<div class="col-lg-8 col-md-8 col-xs-12">						
					<div class="table-responsive border-gray info-area"> 
						<table class="table">							
							<tr class="full-name">
								<td class="col-1"><spring:message code='label.firstname' /></td>
								<td class="col-2" >
									<div class="static-text">${userInfo.firstName}</div>
								</td>
							</tr>
							<tr class="full-name">
								<td class="col-1"><spring:message code='label.lastname' /></td>
								<td class="col-2" >
									<div class="static-text">${userInfo.lastName}</div>
								</td>
							</tr>
							<tr class="email">
								<td class="col-1"><spring:message code='label.email' /></td>
								<td class="col-2" >
									<div class="static-text">${userInfo.email}</div>
								</td>
							</tr>								
							<tr class="phone-number">
								<td class="col-1"><spring:message code='label.phonenumber' /></td>
								<td class="col-2" >
									<div class="static-text">${userInfo.phoneNumber}</div>
								</td>
							</tr>	
							<tr class="birthday">
								<td class="col-1"><spring:message code='label.birthday' /></td>
								<td class="col-2" >
									<div class="static-text">${userInfo.birthday}</div>
								</td>
							</tr>							
							<tr class="country">
								<td class="col-1"><spring:message code='label.country' /></td>
								<td class="col-2" >
									<div class="static-text">${userInfo.country}</div>
								</td>
							</tr>	
							<tr class="city">
								<td class="col-1"><spring:message code='label.city' /></td>
								<td class="col-2" >
									<div class="static-text">${userInfo.city}</div>
								</td>
							</tr>
							<tr>	
								<td class="col-1"><spring:message code='label.password' /></td>
									<td class="col-2" >
										<div class="static-text">**************</div>
									</td>
							</tr>
						</table>
					</div>
					<div class="edit-open-modal">
						<button type="button" class="style-btn" data-toggle="modal" data-target="#edit-profile-popup"><spring:message code='button.changeprofile' /></button>						
					</div>
				</div>
				<!-- Modal Edit Profile -->
				<div class="modal fade" id="edit-profile-popup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog modal-md" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button>
                            <div class="modal-title" id="myModalLabel">
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#edit-profile-area" aria-controls="login-area" role="tab" data-toggle="tab"><spring:message code='label.editprofile' /></a></li>
                                <li role="presentation"><a href="#change-password-area" aria-controls="change-password-area" role="tab" data-toggle="tab"><spring:message code='label.changepass' /></a></li>
                            </ul>
                            </div>
                        </div>
                        <div class="modal-body row">                            
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="edit-profile-area">
                                     <div class="register-area center border-green">
                                    	<div class="loading">
										 	<p><img src="<c:url value='/images/ajax-loader_1.gif' />"/><spring:message code='label.pleasewait' /></p>
										</div>
                                        <form id="editProfileForm"  method="POST">
                                            <div class="form-group row full-name">
                                            	<div class="pull-left" >
                                            		<input type="text" name="firstName" id="firstName" value="${userInfo.firstName}" class="form-control pull-left" placeholder="<spring:message code='placeholder.firstname' />" />
                                            		<label class="mandatory-asterix">*</label>
                                            	</div>
	                                            <div class="pull-right" >
	                                            	<input type="text" name="lastName" id="lastName"  value="${userInfo.lastName}" class="form-control pull-left" placeholder="<spring:message code='placeholder.lastname' />" />
	                                            	<label class="mandatory-asterix">*</label>
                                            	</div>
                                            </div>
                                            <div class="form-group">
                                                <input type="email" class="form-control pull-left" name="email" id="email" value="${userInfo.email}"  placeholder="<spring:message code='placeholder.email' />">
                                            	<label class="mandatory-asterix">*</label>
                                            </div>
                                            <div class="form-group phone-number">
                                            	<input type="hidden" value="${userInfo.phoneNumber}" id="phoneNumberHdn" />
                                            	<input type="hidden" value="${listCountryPhoneCode}" id="listCountryPhoneCodeHdn" />
			                                 	<select class="form-control pull-left" name="countryPhoneCode"  id="countryPhoneCode">
			                                         <c:forEach items="${listCountryPhoneCode}" var="countryPhoneCode">
			                                         	<option value="${countryPhoneCode}">${countryPhoneCode}</option>
			                                         </c:forEach>
			                                    </select>
			                                    <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="<spring:message code='placeholder.phonenumber' />">
			                                 	<label class="mandatory-asterix hidden"></label>
			                                 </div>
                                             <div class="form-group">
                                             	<div id="date_picker" class="input-group date">                                             		
													<input  class="form-control" id="birthday" name="birthday" id="birthday" type="text" data-format="MM-dd-yyyy" value="${userInfo.birthday}" placeholder="<spring:message code='placeholder.birthday' />"></input>
													<span class="add-on input-group-addon">
														<i data-time-icon="icon-time" data-date-icon="icon-calendar" class="fa fa-calendar" aria-hidden="true"></i>
													</span>
												</div>                                               
                                            </div>
                                            <div class="form-group">
                                            	<input type="hidden" id="countryHdn" value="${userInfo.country}" />
                                                <select class="form-control pull-left" name="country" id="country">
			                                     	<option selected disabled>---<spring:message code='placeholder.choosecountry' />---</option>
			                                         <c:forEach items="${listCountry}" var="country">
			                                         	<option value="${country}">${country}</option>
			                                         </c:forEach>
			                                     </select>
			                                     <label class="mandatory-asterix">*</label>
                                            </div>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="city" id="city" placeholder="<spring:message code='placeholder.city' />" value="${userInfo.city}">
                                            </div>
                                            <div class="right-btn">
                                                <input type="submit" class="btn btn-primary" id="save" value="<spring:message code='button.save' />">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="change-password-area">
                                	<div class="login-area center border-green">
                                        <form id="changePasswordForm" method="POST">
                                        	<div class="loading">
											 	<p><img src="<c:url value='/images/ajax-loader_1.gif'/>"/><spring:message code='label.pleasewait' /></p>
											</div>
											 <div class="form-group">
                                             	 <input type="password" name="oldPassword" id="oldPassword" class="form-control" placeholder="<spring:message code='placeholder.oldpassword' />" />
                                             </div>
                                             <div class="form-group">
                                             	<input type="password" name="newPassword" id="newPassword" class="form-control" placeholder="<spring:message code='placeholder.newpassword' />" />
                                             </div>
                                             <div class="form-group">
                                             	<input type="password" name="confirmPassword" id="confirmPassword" class="form-control" placeholder="<spring:message code='placeholder.confirmpassword' />" />
                                             </div>
                                             <div class="right-btn">
                                                <input type="submit" class="btn btn-primary" id="save" value="<spring:message code='button.save' />">
                                            </div>
                                        </form>
                                    </div> 
                                </div>
                            </div>                          
                        </div>              
                    </div>  
                </div>  
            </div>  
			</div>
		</div>
	</div>
	<div class="mgbt100">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>