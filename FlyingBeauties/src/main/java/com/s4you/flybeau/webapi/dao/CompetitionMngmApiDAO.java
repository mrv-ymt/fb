package com.s4you.flybeau.webapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.s4you.flybeau.dbconnection.DatabaseConnection;
import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.GroupDTO;
import com.s4you.flybeau.utils.CommonUtils;
import com.s4you.flybeau.utils.ConstantUtil;

/**
 * CompetitionMngmDAO
 * @author ThienMV
 * Date: 13/5/2016
 */
@Repository
@Transactional
public class CompetitionMngmApiDAO {
	
	Connection connection;
	PreparedStatement preparedStatement;

	/**
	 * Get List Competition
	 * @param userId
	 * @return List<CompetitionDTO>
	 */
	public List<CompetitionDTO> getListCptt(int userId) {
		
		String sql =  "SELECT * FROM getlistcompetition(?)";
		List<CompetitionDTO> listCompetition = new ArrayList<CompetitionDTO>();		
		CompetitionDTO competitionDTO;	

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, userId);
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				competitionDTO = new CompetitionDTO();
				competitionDTO.setCompetitionId(resultSet.getInt("CompetitionId"));
				competitionDTO.setCompetitionName(resultSet.getString("CompetitionName"));
				competitionDTO.setCompetitionLogoUrl(resultSet.getString("CompetitionLogoUrl"));
				competitionDTO.setDescription(resultSet.getString("Description"));
				competitionDTO.setBeginTime(CommonUtils.timestampToDateMMdd(resultSet.getTimestamp("BeginTime")));
				competitionDTO.setEndTime(CommonUtils.timestampToDateMMdd(resultSet.getTimestamp("EndTime")));
				competitionDTO.setParticipants(resultSet.getInt("Participants"));
				competitionDTO.setJoined(resultSet.getInt("Joined"));
				competitionDTO.setImageNum(resultSet.getInt("ImageNum"));
				competitionDTO.setGroupNum(resultSet.getInt("GroupNum"));
				competitionDTO.setOwnImageNum(resultSet.getInt("OwnImageNum"));
				competitionDTO.setHotRewards(resultSet.getInt("HotRewards"));
				competitionDTO.setTermAndCondition(resultSet.getString("TermAndCondition"));
				
				/* Add competition to list */
				listCompetition.add(competitionDTO);				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return listCompetition;
	}

	/**
	 * 
	 * @param userId
	 * @param competitionId
	 * @return TRUE if join successfully, FALSE if join fail
	 */
	public boolean joinCompetition(int userId, int competitionId, int groupId) {

		String sql =  "SELECT * FROM joincompetition2(?,?, ?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, competitionId);
			preparedStatement.setInt(2, userId);
			preparedStatement.setInt(3, groupId);
					
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;
	}
	
	/**
	 * Get List Group
	 * @return List<GroupDTO>
	 */
	public List<GroupDTO> getListGroup() {
		
		String sql =  "SELECT * FROM getlistgroup()";
		List<GroupDTO> listGroup = new ArrayList<GroupDTO>();	
		GroupDTO groupDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				
				groupDTO = new GroupDTO();
				groupDTO.setGroupId(resultSet.getInt("GroupId"));
				groupDTO.setGroupName(resultSet.getString("GroupName"));
				groupDTO.setGroupLogoUrl(resultSet.getString("GroupLogoUrl"));
				groupDTO.setDescription(resultSet.getString("Description"));
				groupDTO.setParticipants(resultSet.getInt("Participants"));
				groupDTO.setImageNum(resultSet.getInt("ImageNum"));
				groupDTO.setTotalPoint(resultSet.getLong("TotalPoint"));
				
				listGroup.add(groupDTO);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return listGroup;
	}
	

	/**
	 * If isTopGroup = true then get Top Group in competition
	 * Else Get List All Group In Competition 
	 * @return List<GroupDTO>
	 */
	public List<GroupDTO> getListGroupCptt(int competitionId, boolean isTopGroup) {
		
		String sql = ConstantUtil.INIT_STRING; 
		if(isTopGroup) {
			sql =  "SELECT * FROM gettopgroup(?)";
		} else {
			sql =  "SELECT * FROM getlistgroup_competition(?)";
		}
		
		List<GroupDTO> listGroup = new ArrayList<GroupDTO>();	
		GroupDTO groupDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, competitionId);
			
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				groupDTO = new GroupDTO();
				groupDTO.setGroupId(resultSet.getInt("GroupId"));
				groupDTO.setGroupName(resultSet.getString("GroupName"));
				groupDTO.setGroupLogoUrl(resultSet.getString("GroupLogoUrl"));
				groupDTO.setDescription(resultSet.getString("Description"));
				groupDTO.setParticipants(resultSet.getInt("Participants"));
				groupDTO.setImageNum(resultSet.getInt("ImageNum"));
				groupDTO.setTotalPoint(resultSet.getLong("TotalPoint"));
				
				listGroup.add(groupDTO);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return listGroup;
	}
	
	/**
	 * Get Group Info 
	 * @return GroupDTO
	 */
	public GroupDTO getGroupInfo(int groupId) {

		String sql =  "SELECT * FROM getgroupinfo(?)";
		GroupDTO groupDTO = null;	

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, groupId);
			
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				groupDTO = new GroupDTO();
				groupDTO.setGroupId(resultSet.getInt("GroupId"));
				groupDTO.setGroupName(resultSet.getString("GroupName"));
				groupDTO.setGroupLogoUrl(resultSet.getString("GroupLogoUrl"));
				groupDTO.setDescription(resultSet.getString("Description"));
				groupDTO.setParticipants(resultSet.getInt("Participants"));
				groupDTO.setImageNum(resultSet.getInt("ImageNum"));
				groupDTO.setTotalPoint(resultSet.getLong("TotalPoint"));				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
		
		return groupDTO;
	}
}