package com.s4you.flybeau.webapi.service;

import java.util.List;

import javax.ws.rs.GET;

import com.s4you.flybeau.dao.AdministratorDAO;
import com.s4you.flybeau.dao.AdministratorDAOImpl;
import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.GroupDTO;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.webapi.dao.CompetitionMngmApiDAO;

/**
 * CompetitionMngmDAO
 * @author ThienMV
 * Date: 13/5/2016
 */
public class CompetitionMngmApiService {
	
	CompetitionMngmApiDAO competitionMngmDAO = new CompetitionMngmApiDAO();
	AdministratorDAO administratorDAO = new AdministratorDAOImpl();
	
	/**
	 * Get List Competition 
	 * @param userId
	 * @return List<CompetitionDTO>
	 */
	public List<CompetitionDTO> getListCptt(int userId) {
		
		return competitionMngmDAO.getListCptt(userId);
	}

	/**
	 * Join in competition
	 * @param userId
	 * @param competitionId
	 * @param groupId 
	 * @return RetCode
	 */
	public int joinCompetition(int userId, int competitionId, int groupId) {

		if(competitionMngmDAO.joinCompetition(userId, competitionId, groupId)) {
			return ConstantUtil.RETCODE_NORMAL;
		}
		return ConstantUtil.RETCODE_ABNORMAL;
	}
	
	/**
	 * Get List Group
	 * @return List<GroupDTO>
	 */
	public List<GroupDTO> getListGroup() {
		
		/* Get List Group */
		return competitionMngmDAO.getListGroup();
	}

	/**
	 * Get Top Group in competition
	 * @param competitionId
	 * @return List<GroupDTO>
	 */
	public List<GroupDTO> getTopGroup(int competitionId) {
		
		List<GroupDTO> listTopGroup = competitionMngmDAO.getListGroupCptt(competitionId, true);
		
		if(listTopGroup != null) {
			
			for (int i = 0; i < listTopGroup.size(); i++) {
				if(listTopGroup.get(i).getTotalPoint() == 0.0) {
					listTopGroup.remove(i);
				}				
			}
			
		}
		
		return listTopGroup;
	}

	/**
	 * Get List All Group In Competition 
	 * @return List<GroupDTO>
	 */
	@GET
	public List<GroupDTO> getListGroupCptt(int competitionId) {
		
		/* Get List Group */
//		return competitionMngmDAO.getListGroupCptt(competitionId, false);
		return administratorDAO.getListGroup(competitionId);
	}
	
	/**
	 * Get Group Info 
	 * @return GroupDTO
	 */
	public GroupDTO getGroupInfo(int groupId) {
		
		return competitionMngmDAO.getGroupInfo(groupId);
	}
}