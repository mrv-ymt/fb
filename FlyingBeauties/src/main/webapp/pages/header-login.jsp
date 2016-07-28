<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<%@ page isELIgnored="false" %>
<c:if test="${pageContext.response.locale eq 'vi'}">
	<script src="<c:url value='/js/messages_vi.min.js'/>"></script>
</c:if>
<input type="hidden" id="lang" value="${pageContext.response.locale}" />
<jsp:include page="base.jsp"></jsp:include>
<div class="header">
    <div class="container">
        <div class="pull-left">
            <div class="hidden-xs hidden-sm logo pull-left">
                <a href="<c:url value='/1'/>"><img src="<c:url value='/images/FlyingBeautiesLogo_88x70.png'/>"  /></a>
            </div>
            <div class="visible-xs pull-left nav-bar-reponsive">
                <a href="#" class="nav-bar-toggle"><i class="glyphicon glyphicon-align-justify"></i></a>
                <div class="nav-bar reponsive" style="display:none;">
                    <ul>
                        <li class="sup-li menu-home" ><a href="<c:url value='/1'/>"><i class="glyphicon glyphicon-home"></i>&nbsp;<spring:message code="label.home" /></a></li>
                        <li class="sup-li menu-hot-image"><a href="<c:url value='/hotimage/1'/>"><spring:message code="label.hotimage" /></a></li> 
                        <li class="sup-li menu-competition"><a href="<c:url value='/competition/list'/>">Competitions</a></li>
                        <li class="sup-li"><a href="#" class="download-app"><i class="glyphicon glyphicon-download-alt"></i>&nbsp;<spring:message code="label.downloadapp" /></a></li>
                    </ul>
                </div>
            </div>
<!--             <div class="input-group searching-form pull-left"> -->
<!--                 <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span> -->
<%--                 <input type="text" class="form-control" placeholder="<spring:message code='placeholder.searching' />" /> --%>
<!--             </div> -->
            <div class="menu-items hidden-xs pull-right">
                <ul class="pull-left">              
                    <li class="style-nav menu-home"><a href="<c:url value='/1'/>"><i class="glyphicon glyphicon-home"></i>&nbsp;<spring:message code="label.home" /></a></li>
                    <li class="style-nav menu-hot-image"><a href="<c:url value='/hotimage/1'/>"><spring:message code="label.hotimage" /></a></li>  
                    <li class="style-nav menu-competition"><a href="<c:url value='/competition/list'/>"><spring:message code="label.competitions" /></a></li>
                    <li class="style-nav"><a href="#" class="download-app"><i class="glyphicon glyphicon-download-alt"></i>&nbsp;<spring:message code="label.downloadapp" /></a></li>                      
                </ul>
            </div>                          
        </div>
        <div class="menu-items menu-right pull-right">              
            <ul>
            	<c:choose>
            		<c:when test="${pageContext.response.locale eq 'en'}">
            			<li class="flag"><a href="?lang=vi"><img src="<c:url value='/images/Vietnam-Flag-icon.png'/>" /></a></li>
            		</c:when>
            		<c:otherwise>
            			<li class="flag"><a href="?lang=en"><img src="<c:url value='/images/United-Kingdom-flag-icon.png'/>" /></a></li>
            		</c:otherwise>
            	</c:choose>
                <li class="style-nav"><a href="#" data-toggle="modal" data-target="#login-register-popup"><i class="glyphicon glyphicon-user"></i>&nbsp;<spring:message code="label.loginarea" /></a></li>
            </ul>   
        </div>                          
    </div>
</div>
<div class="modal fade" id="login-register-popup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
     <div class="modal-dialog modal-md" role="document">
         <div class="modal-content">
             <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span></button>
                 <div class="modal-title" id="myModalLabel">
                 <ul class="nav nav-tabs" role="tablist">
                     <li role="presentation" class="active"><a href="#login-area" aria-controls="login-area" role="tab" data-toggle="tab"><spring:message code="label.login" /></a></li>
                     <li role="presentation" class="register-tab"><a href="#register-area" aria-controls="register-area" role="tab" data-toggle="tab"><spring:message code="label.register" /></a></li>
                     <li role="presentation" class="forgot-pass-tab" style="display:none;"><a href="#forgot-pass" aria-controls="forgot-pass" role="tab" data-toggle="tab"><spring:message code="label.resetpassword" /></a></li>
                 	<li role="presentation" class="term-flyingbeauties-tab" style="display:none;"><a href="#term-flyingbeauties" aria-controls="term-flyingbeauties" role="tab" data-toggle="tab"><spring:message code="label.termflyingbeauties" /></a></li>
                 </ul>
                 </div>
             </div>
             <div class="modal-body row">                            
                 <!-- Tab panes -->
                 <div class="tab-content">
                     <div role="tabpanel" class="tab-pane fade in active" id="login-area">
                         <div class="login-area center border-green">
                         	<input type="hidden" value="${error}" id="login-error"/>
                             <form name='loginForm'  id='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
                             	<div class="loading">
								 	<p><img src="<c:url value='/images/ajax-loader_1.gif'/>"/> <spring:message code="label.pleasewait" /></p>
								</div>
                                 <table>
                                     <tr>                    
                                         <td colspan="2" class="col-md-8">
                                             <div class="form-group">
                                             	<input type="text" name="username" id="username" class="form-control" placeholder="<spring:message code='placeholder.usernameormail' />" />
                                             </div>
                                         </td>
                                     </tr>
                                     <tr>
                                         <td class="col-md-8">
                                             <div class="form-group">
                                                 <input type="password" name="password" id="password" class="form-control" placeholder="<spring:message code='placeholder.password' />" />
                                             </div>
                                         </td>
                                         <td class="col-md-4">
                                             <div class="forgot-pass pull-right">
                                                 <a id="forgot-pass-link" href="#"><spring:message code='label.forgotpass' /></a>
                                             </div>
                                         </td>
                                     </tr>
                                     <tr>
                                         <td class="col-xs-6">
                                             <div class="checkbox-remberme">
                                             	<div class="squaredThree">
													<input type="checkbox" id="rememberme"/>
													<label class="chk-label" for="rememberme"></label><label class="label-term" for="rememberme">&nbsp;&nbsp;<span><spring:message code='label.rememberme' /></span></label>
												</div>	
                                              </div>
                                         </td>
                                         <td class="col-xs-6"><input type="submit" class="btn btn-primary right" id="login" value="<spring:message code='button.login' />"/></td>
                                  	  </tr>
<!--                                                 <tr> -->
<!--                                                     <td colspan="2" class="facebook-login"><a href="#" class="btn btn-primary"><i class="fa fa-facebook-square" aria-hidden="true"></i>&nbsp;Facebook Login</a></td>                                                 -->
<!--                                                 </tr> -->
                                 </table>
                             </form>
                         </div>  
                     </div>
                     <div role="tabpanel" class="tab-pane fade" id="register-area">
                         <div class="register-area center border-green">
                         	<div class="loading">
								<p><img src="<c:url value='/images/ajax-loader_1.gif' />"/> <spring:message code="label.pleasewait" /></p>
							</div>
                              <form action="<c:url value='/register'/>" method="POST" id="registerForm">
                                 	<div class="form-group row full-name">
	                                 	<div class="pull-left" >
	                                 		<input type="text" name="firstName" id="firstName" class="form-control pull-left" placeholder="<spring:message code='placeholder.firstname' />" />
	                                 		<label class="mandatory-asterix">*</label>
	                               		</div>
                                  		<div class="pull-right" >
                                  			<input type="text" name="lastName" id="lastName" class="form-control pull-left" placeholder="<spring:message code='placeholder.lastname' />" />
                                  			<label class="mandatory-asterix">*</label>
                                  		</div>
                                 </div>
                                 <div class="form-group">
                                     <input type="text" class="form-control pull-left" name="username1" id="username1" placeholder="<spring:message code='placeholder.username' />">
                                     <label class="mandatory-asterix">*</label>
                                 </div>
                                 <div class="form-group">
                                     <input type="email" class="form-control pull-left" name="email" id="email" placeholder="<spring:message code='placeholder.email' />">
                                     <label class="mandatory-asterix">*</label>
                                 </div>
                                 <div class="form-group">
                                   	<input type="password" class="form-control pull-left" name="password1" id="password1" placeholder="<spring:message code='placeholder.password' />">
                                	<label class="mandatory-asterix">*</label>
                                 </div>
                                 <div class="form-group">
                                   <input type="password" class="form-control pull-left" name="confirmPassword" id="confirmPassword" placeholder="<spring:message code='placeholder.confirmpassword' />">
                                	<label class="mandatory-asterix">*</label>
                                 </div>
                                 <div class="form-group">
                                  	<div id="date_picker" class="input-group date">                                             		
										<input  class="form-control" id="birthday" name="birthday" id="birthday" type="text" placeholder="<spring:message code='placeholder.birthday' />"></input>
										<span class="add-on input-group-addon">
											<i data-time-icon="icon-time" data-date-icon="icon-calendar" class="fa fa-calendar" aria-hidden="true"></i>
										</span>
									</div>                                               
                                 </div>
                                 <div class="form-group phone-number">
                                 	<select class="form-control pull-left" name="countryPhoneCode"  id="countryPhoneCode">
                                         <c:forEach items="${listCountryPhoneCode}" var="countryPhoneCode">
                                         	<option value="${countryPhoneCode}">${countryPhoneCode}</option>
                                         </c:forEach>
                                    </select>
                                    <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="<spring:message code='placeholder.phonenumber' />">
                                 	<label class="mandatory-asterix hidden"></label>
                                 </div>
                                 <div class="form-group">
                                     <select class="form-control pull-left" name="country"  id="country">
                                     	<option selected disabled>---<spring:message code='placeholder.choosecountry' />---</option>
                                         <c:forEach items="${listCountry}" var="country">
                                         	<option value="${country}">${country}</option>
                                         </c:forEach>
                                     </select>
                                     <label class="mandatory-asterix">*</label>
                                 </div>
                                 <div class="form-group">
                                     <input type="text" class="form-control" name="city" id="city" placeholder="<spring:message code='placeholder.city' />">
                                 </div>
                                 <div class="form-group">
                                 	<div class="squaredThree">
										<input type="checkbox" name="confirmTerm" checked id="confirmTerm"/>
										<label class="chk-label" for="confirmTerm"></label><label class="label-term" for="confirmTerm">&nbsp;&nbsp;<a href="#" id="term-flyingbeauties-link"><spring:message code='label.term' /></a></label>
									</div>	
                                 </div>
                                 <div class="right-btn">
                                     <input type="submit" class="btn btn-primary" id="register" value="<spring:message code='button.register' />">
                                 </div>
                             </form>
                         </div>
                     </div>
                     <div role="tabpanel" class="tab-pane fade in" id="forgot-pass">
                         <div class="send-password-area center border-green">
                         	<div class="loading">
								<p><img src="<c:url value='/images/ajax-loader_1.gif' />"/> <spring:message code="label.pleasewait" /></p>
							</div>
                           <form id="resetPasswordForm" action="" method="GET">
                              <div class="form-group">
                                  <input type="text" class="form-control email-ret" id="emailReset" name="emailReset" placeholder="<spring:message code='placeholder.inputemail' />">
                              </div>
                              <div class="right-btn">
                                  <input type="submit" class="btn btn-primary right" id="sendpass" value="<spring:message code='button.sendpass' />" />
                              </div>      
                             </form>                                    
                         </div>  
                     </div>
                     <div role="tabpanel" class="tab-pane fade in" id="term-flyingbeauties">
                         <div class="center term-content-area border-green">
                         	<spring:message code='label.termcontent' />
                         </div>  
                         <div class="right-btn">
                             <input type="submit" class="btn btn-primary right" id="back-register" value="OK" />
                         </div> 
                     </div>
                 </div>                          
             </div>              
         </div>  
     </div>  
 </div> 