package com.s4you.flybeau.dao;

import java.util.List;

import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.GroupDTO;
import com.s4you.flybeau.dto.UserDTO;

public interface AdministratorDAO {
	
	/**
	 * Get List User
	 * @return List<UserDTO>
	 */
	public List<UserDTO> getListUser(int beginNum, int pageSize);
	
	/**
	 * Active/Deactive User 
	 * @param userId
	 * @param status
	 * @return Result
	 */
	public boolean deactiveToogleUserRole(int userId, int status);
	
	/**
	 * Get List Competition
	 * @return List<CompetitionDTO>
	 */
	public List<CompetitionDTO> getListCompetition();
	
	/**
	 * Add a new Competition
	 * @param competition
	 * @return Result
	 */
	public boolean addCompetition(CompetitionDTO competition);
	
	/**
	 * Edit Competition
	 * @param competition
	 * @return Result
	 */
	public boolean editCompetition(CompetitionDTO competition);
	
	/**
	 * Delete Competition
	 * @param competitionId
	 * @return Result
	 */
	public boolean deleteCompetition(int competitionId);
	
	/**
	 * Get List Group
	 * @return List<GroupDTO>
	 */
	public List<GroupDTO> getListGroup();
	
	/**
	 * Add a new Group
	 * @param groupDTO
	 * @return Result
	 */
	public boolean addGroup(GroupDTO groupDTO);
	
	/**
	 * Edit Group
	 * @param groupDTO
	 * @return Result
	 */
	public boolean editGroup(GroupDTO groupDTO);
	
	/**
	 * Delete Group
	 * @param groupId
	 * @return Result
	 */
	public boolean deleteGroup(int groupId);
	
	/**
	 * Add group into competition
	 * @param groupId
	 * @param competitionId
	 * @return Result
	 */
	public boolean addGroupIntoCompetition(int groupId, int competitionId);

	/**
	 * Get Total Number of User
	 * @return Total Number of User
	 */
	public int getUserNumber();

	/**
	 * Get Competition Info
	 * @param competitionId
	 * @return CompetitionDTO
	 */
	public CompetitionDTO getCompetitionInfo(int competitionId);

	/**
	 * Get Competition By Name
	 * @param competitionName
	 * @param competitionId 
	 * @return Result
	 */
	public int getCompetitionByName(String competitionName, int competitionId);

	public GroupDTO getGroup(int groupId);

	public List<GroupDTO> getListGroup(int competitionId);
}
