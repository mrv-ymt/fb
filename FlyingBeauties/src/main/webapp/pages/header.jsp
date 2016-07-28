<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<%@ page isELIgnored="false" %>
<script src="<c:url value='/js/bootstrap-datetimepicker.min.js'/>"></script>
<c:if test="${pageContext.response.locale eq 'vi'}">
	<script src="<c:url value='/js/messages_vi.min.js'/>"></script>
</c:if>
<!-- Login User Id Hidden -->
<input type="hidden" id="loginUserId" value="${userInfo.userId}" />
<input type="hidden" id="lang" value="${pageContext.response.locale}" />
<jsp:include page="base.jsp"></jsp:include>
<div class="header">
    <div class="container">
        <div class="pull-left">
            <div class="hidden-xs hidden-sm logo pull-left">
                <a href="<c:url value='/competition'/>"><img src="<c:url value='/images/FlyingBeautiesLogo_88x70.png'/>"  /></a>
            </div>
            <div class="visible-xs visible-sm pull-left nav-bar-reponsive">
                <a href="#" class="nav-bar-toggle"><i class="glyphicon glyphicon-align-justify"></i></a>
                <div class="nav-bar reponsive" style="display:none;">
                    <ul>
                        <li class="sup-li menu-profile">
                            <a href="<c:url value='/user/profile'/>"><i class="glyphicon glyphicon-user"></i>&nbsp;<spring:message code='label.editprofile' /></a>
                        </li>
                        <li class="sup-li">
							<span><spring:message code='label.mypicture' /></span>
							<ul class="sub-ul">
								<li class="no-image-uploaded hidden"><a href="<c:url value='/image/upload'/>"><spring:message code='lable.noimage' /></a></li>
								<c:forEach items="${listCompetition}" var="competition">
									<c:if test="${(competition.ownImageNum != 0) && (competition.joined == 1)}">
										<c:choose>
											<c:when test="${competition.competitionId == competitionId}">
												<li class="current-cptt-mypic"><a href="<c:url value='/ownlistimage/${competition.competitionId}'/>"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;${competition.competitionName} (${competition.ownImageNum})</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="<c:url value='/ownlistimage/${competition.competitionId}'/>"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;${competition.competitionName} (${competition.ownImageNum})</a></li>
											</c:otherwise>
										</c:choose>						
									</c:if>	
								</c:forEach>
							</ul> 
                        </li>
                        <li class="sup-li">
                            <span><spring:message code='label.mycompetitions' /></span>
							<ul class="sub-ul">
								<li class="no-cptt-joined hidden"><a href="<c:url value='/competition/list'/>"><spring:message code='lable.nocompetition' /></a></li>
								<c:forEach items="${listCompetition}" var="competition">
									<c:if test="${competition.joined == 1}">
										<c:choose>
											<c:when test="${(competition.competitionId == competitionId) && (competition.joined == 1)}">
													<li class="current-cptt"><a href="<c:url value='/competition?id=${competition.competitionId}'/>"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;${competition.competitionName} (${competition.imageNum})</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="<c:url value='/competition?id=${competition.competitionId}'/>"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;${competition.competitionName} (${competition.imageNum})</a></li>
											</c:otherwise>
										</c:choose>		
									</c:if>								
								</c:forEach>
							</ul>
                        </li>
                        <li class="sup-li"><a href="<c:url value='/j_spring_security_logout'/>"><i class="glyphicon glyphicon-off">&nbsp;</i><spring:message code='label.logout' /></a></li>
                    </ul>
                </div>
            </div>
<!--             <div class="input-group searching-form pull-left"> -->
<!--                 <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span> -->
<%--                 <input type="text" class="form-control" placeholder="<spring:message code='placeholder.searching' />" /> --%>
<!--             </div> -->
            <div class="menu-items pull-right">
                <ul>                
                    <li class="style-nav menu-competition"><a href="<c:url value='/competition/list'/>"><i class="glyphicon glyphicon-home"></i>&nbsp;<spring:message code='label.competitions' /></a></li>
                    <sec:authorize access="hasRole('ROLE_2')">
                        <li class="style-nav sup-li menu-upload-image"><a href="<c:url value='/image/upload'/>"><i class="glyphicon glyphicon-upload"></i>&nbsp;<spring:message code='label.uploadimage' /></a></li>
                    </sec:authorize>
                    <li class="style-nav sup-li menu-species-list"><a href="<c:url value='/specieslist'/>"><spring:message code='label.specieslist' /></a></li>   
                </ul>
            </div>                              
        </div>
        <div class="pull-right dropdown">
        	<div class="menu-items pull-left">
        		<ul>
	            	<c:choose>
	            		<c:when test="${pageContext.response.locale eq 'en'}">
	            			<li class="flag"><a href="#" onclick="savePrefferdLanguage('vi');"><img src="<c:url value='/images/Vietnam-Flag-icon.png'/>" /></a></li>
	            		</c:when>
	            		<c:otherwise>
	            			<li class="flag"><a href="#" onclick="savePrefferdLanguage('en');"><img src="<c:url value='/images/United-Kingdom-flag-icon.png'/>" /></a></li>
	            		</c:otherwise>
	            	</c:choose>
	            </ul> 
        	</div>
            <div class="profile-area pull-right">
                <sec:authorize access="hasRole('ROLE_2')">
                    <a href="<c:url value='/user/profile'/>"><img id="header-avatar" src="<c:url value='/avatars/${userInfo.avatarUrl}'/>"/>&nbsp;${userInfo.firstName}</a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="glyphicon glyphicon-triangle-bottom"></i></a>
                    <ul class="dropdown-menu">
                        <li class="menu-profile"><a href="<c:url value='/user/profile'/>"><i class="glyphicon glyphicon-user">&nbsp;</i><spring:message code='label.profile' /></a></li>
                        <li class="divider"></li>
                        <li><a href="<c:url value='/j_spring_security_logout'/>"><i class="glyphicon glyphicon-off">&nbsp;</i><spring:message code='label.logout' /></a></li>
                    </ul>   
                </sec:authorize>
            </div>          
        </div>              
    </div>
</div>