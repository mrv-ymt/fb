package com.s4you.flybeau.webapi.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.GroupDTO;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.webapi.service.ApiTokenSecurirty;
import com.s4you.flybeau.webapi.service.CompetitionMngmApiService;

/**
 * CompetitionMngmController
 * @author ThienMV
 * Date: 13/5/2016
 */
@Path("/competition")
@Produces(MediaType.APPLICATION_JSON)
public class CompetitionMngmApiController {

	CompetitionMngmApiService competitionMngmService = new CompetitionMngmApiService();
	
	/**
	 * Get List Competition 
	 * @param userId
	 * @return List<CompetitionDTO>
	 */
	@GET
	@Path("/getlist/{userId}")
	public List<CompetitionDTO> getListCptt(@PathParam("userId") int userId) {
		return competitionMngmService.getListCptt(userId);
	}		
	
	/**
	 * Join in competition
	 * @param userId
	 * @param competitionId
	 * @return RetCode
	 */
	@GET
	@Path("/join/{userId}/{competitionId}/{groupId}")
	public int joinCompetition(@PathParam("userId") int userId, @PathParam("competitionId") Integer competitionId, 
			@PathParam("groupId") Integer groupId, 
			@Context HttpHeaders headers) {		

		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			return ConstantUtil.RETCODE_NOAUTHENCATION;
		}
		return competitionMngmService.joinCompetition(userId, competitionId, groupId);
	}
	
	/**
	 * Get List Group
	 * @return List<GroupDTO>
	 */
	@GET
	@Path("/getlistgroup")
	public List<GroupDTO> getListGroup() {

		return competitionMngmService.getListGroup();
	}
	
	/**
	 * Get List Top Group In Competition 
	 * @return List<GroupDTO>
	 */
	@GET
	@Path("/gettopgroup/{competitionId}")
	public List<GroupDTO> getTopGroup(@PathParam("competitionId") int competitionId) {

		return competitionMngmService.getTopGroup(competitionId);
	}
	
	/**
	 * Get List All Group In Competition 
	 * @return List<GroupDTO>
	 */
	@GET
	@Path("/getlistgroupcptt/{competitionId}")
	public List<GroupDTO> getListGroupCptt(@PathParam("competitionId") Integer competitionId) {

		return competitionMngmService.getListGroupCptt(competitionId);
	}
	
	/**
	 * Get Group Info 
	 * @return GroupDTO
	 */
	@GET
	@Path("/getgroupinfo/{groupId}")
	public GroupDTO getGroupInfo(@PathParam("groupId") int groupId) {

		return competitionMngmService.getGroupInfo(groupId);
	}
}
