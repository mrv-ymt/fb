<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<%@ page isELIgnored="false" %>
<sec:authorize access="hasRole('ROLE_2')">
<div class="col-md-2 nav-bar hidden-sm hidden-xs">
	<ul>
		<li class="sup-li menu-profile">		
			<a href="<c:url value='/user/profile'/>"><i class="glyphicon glyphicon-user"></i>&nbsp;<spring:message code='label.editprofile' /></a>
		</li>
		<li class="sup-li image">
			<span><spring:message code='label.mypicture' /></span>			
			<ul class="sub-ul">
				<li class="no-image-uploaded hidden"><a href="<c:url value='/image/upload'/>"><spring:message code='lable.noimage' /></a></li>
				<c:forEach items="${listCompetition}" var="competition">
					<c:if test="${(competition.ownImageNum != 0) && (competition.joined == 1)}">
						<c:choose>
							<c:when test="${competition.competitionId == competitionId}">
								<li class="current-cptt-mypic cptt"><a href="<c:url value='/ownlistimage/${competition.competitionId}'/>"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;${competition.competitionName} (${competition.ownImageNum})</a></li>
							</c:when>
							<c:otherwise>
								<li class="cptt"><a href="<c:url value='/ownlistimage/${competition.competitionId}'/>"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;${competition.competitionName} (${competition.ownImageNum})</a></li>
							</c:otherwise>
						</c:choose>						
					</c:if>	
				</c:forEach>
			</ul>
		</li>
		<li class="sup-li cptt-title">
			<span><spring:message code='label.mycompetitions' /></span>
			<ul class="sub-ul">
				<li class="no-cptt-joined hidden"><a href="<c:url value='/competition/list'/>"><spring:message code='lable.nocompetition' /></a></li>
				<c:forEach items="${listCompetition}" var="competition">
					<c:if test="${competition.joined == 1}">
						<c:choose>
							<c:when test="${(competition.competitionId == competitionId) && (competition.joined == 1)}">
									<li class="current-cptt cptt"><a href="<c:url value='/competition?id=${competition.competitionId}'/>"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;${competition.competitionName} (${competition.imageNum})</a></li>
								</c:when>
								<c:otherwise>
									<li class="cptt"><a href="<c:url value='/competition?id=${competition.competitionId}'/>"><i class="fa fa-picture-o" aria-hidden="true"></i>&nbsp;${competition.competitionName} (${competition.imageNum})</a></li>
								</c:otherwise>
						</c:choose>		
					</c:if>								
				</c:forEach>
			</ul>
		</li>
	</ul>
</div>
</sec:authorize>