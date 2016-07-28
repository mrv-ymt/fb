package com.s4you.flybeau.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.s4you.flybeau.dbconnection.DatabaseConnection;
import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.GroupDTO;
import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.utils.CommonUtils;
import com.s4you.flybeau.utils.ConstantUtil;

/**
 * 
 * AdministratorDAO Date: 05/07/2016 ThienMV
 * 
 * */
@Repository
public class AdministratorDAOImpl implements AdministratorDAO {

	PreparedStatement preparedStatement;

	/* =============== USER MANAGEMENT START ================== */
	/**
	 * Get List User
	 * 
	 * @return List<UserDTO>
	 */
	@Override
	public List<UserDTO> getListUser(int beginNum, int pageSize) {

		String sql = "SELECT * FROM admin_getlistuser(?,?)";
		List<UserDTO> listUser = new ArrayList<UserDTO>();

		UserDTO userDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){

			/* Open Connection To Execute SQL Statement */
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, beginNum - 1);
			preparedStatement.setInt(2, pageSize);

			/* Set Input Parameter */

			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();

			/* Get Result From Database */
			while (resultSet.next()) {

				userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("UserId"));
				userDTO.setUsername(resultSet.getString("Username"));
				userDTO.setFirstName(resultSet.getString("FirstName"));
				userDTO.setLastName(resultSet.getString("LastName"));
				userDTO.setCountry(resultSet.getString("Country"));
				userDTO.setAvatarUrl(resultSet.getString("AvatarUrl"));
				userDTO.setEmail(resultSet.getString("Email"));
				userDTO.setImageNum(resultSet.getInt("ImageNum"));
				userDTO.setSumPoint(resultSet.getInt("SumPoint"));
				userDTO.setStatus(resultSet.getInt("Status"));
				userDTO.setGroupName(resultSet.getString("GroupName"));

				/* Add User to list */
				listUser.add(userDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listUser;
	}

	/**
	 * Get Total Number of User
	 * 
	 * @return Total Number of User
	 */
	@Override
	public int getUserNumber() {

		String sql = "SELECT * FROM admin_countalluser()";
		int numberOfUser = 0;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();

			/* Get Result From Database */
			while (resultSet.next()) {

				numberOfUser = resultSet.getInt("UserNumber");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numberOfUser;
	}

	/**
	 * Active/Deactive User
	 * 
	 * @param userId
	 * @param status
	 * @return Result
	 */
	@Override
	public boolean deactiveToogleUserRole(int userId, int status) {

		boolean result = false;
		String sql = "SELECT * FROM admin_deactivatetoogleuserrole(?,?)";

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Set Input Parameter */
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, status);

			result = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/* =============== USER MANAGEMENT END ================== */

	/* =============== COMPETITION MANAGEMENT START ================== */

	/**
	 * Get List Competition
	 * 
	 * @return List<CompetitionDTO>
	 */
	@Override
	public List<CompetitionDTO> getListCompetition() {

		String sql = "SELECT * FROM admin_getlistcompetition()";
		List<CompetitionDTO> listCompetition = new ArrayList<CompetitionDTO>();
		CompetitionDTO competitionDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();

			/* Get Result From Database */
			while (resultSet.next()) {

				competitionDTO = new CompetitionDTO();

				competitionDTO.setCompetitionId(resultSet
						.getInt("CompetitionId"));
				competitionDTO.setCompetitionName(resultSet
						.getString("CompetitionName"));
				competitionDTO.setCompetitionLogoUrl(resultSet
						.getString("CompetitionLogoUrl"));
				competitionDTO.setDescription(resultSet
						.getString("Description"));
				competitionDTO.setBeginTime(CommonUtils
						.timestampToDate(resultSet.getTimestamp("BeginTime")));
				competitionDTO.setEndTime(CommonUtils.timestampToDate(resultSet
						.getTimestamp("EndTime")));
				competitionDTO
						.setParticipants(resultSet.getInt("Participants"));
				competitionDTO.setHotRewards(resultSet.getInt("HotRewards"));
				competitionDTO.setImageNum(resultSet.getInt("ImageNum"));
				competitionDTO.setGroupNum(resultSet.getInt("GroupNum"));
				competitionDTO.setCompetitionStatus(resultSet
						.getInt("CompetitionStatus"));

				/* Add competition to list */
				listCompetition.add(competitionDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listCompetition;
	}

	/**
	 * Add a new Competition
	 * 
	 * @param competition
	 * @return Result
	 */
	@Override
	public boolean addCompetition(CompetitionDTO competition) {

		String sql = "SELECT * FROM admin_addcompetition(?,?,?,?,?,?,?,?,?,?)";

		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Set Input Parameter */
			preparedStatement.setString(1, competition.getCompetitionName());
			preparedStatement.setString(2, competition.getCompetitionLogoUrl());
			preparedStatement.setInt(3, competition.getHotRewards());
			preparedStatement.setString(4, competition.getCompetitionRewards());
			preparedStatement.setString(5, competition.getDescription());
			preparedStatement.setTimestamp(6, CommonUtils
					.dateTimeStrToTimeStamp(competition.getBeginTime()));
			preparedStatement.setTimestamp(7, CommonUtils
					.dateTimeStrToTimeStamp(competition.getEndTime()));
			preparedStatement.setTimestamp(8,
					new Timestamp(new Date().getTime()));
			preparedStatement.setString(9, competition.getTermAndCondition());
			preparedStatement.setInt(10, competition.getInitPoint());

			result = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Edit Competition
	 * 
	 * @param competition
	 * @return Result
	 */
	@Override
	public boolean editCompetition(CompetitionDTO competition) {

		String sql = "SELECT * FROM admin_editcompetition2(?,?,?,?,?,?,?,?,?,?,?)";

		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Set Input Parameter */
			preparedStatement.setInt(1, competition.getCompetitionId());
			preparedStatement.setString(2, competition.getCompetitionName());
			preparedStatement.setString(3, competition.getCompetitionLogoUrl());
			preparedStatement.setInt(4, competition.getHotRewards());
			preparedStatement.setString(5, competition.getCompetitionRewards());
			preparedStatement.setString(6, competition.getDescription());
			preparedStatement.setTimestamp(7, CommonUtils
					.dateTimeStrToTimeStamp(competition.getBeginTime()));
			preparedStatement.setTimestamp(8, CommonUtils
					.dateTimeStrToTimeStamp(competition.getEndTime()));
			preparedStatement.setString(9, competition.getTermAndCondition());
			preparedStatement.setInt(10, competition.getInitPoint());
			Object[]groupIds = new Object[competition.getListGroup().size()];
			for (int i = 0; i < competition.getListGroup().size(); i++) {
				groupIds[i] = Integer.parseInt(competition.getListGroup().get(i));
			}
			Array groupIdArray = connection.createArrayOf("integer", groupIds);
			preparedStatement.setArray(11, groupIdArray);

			result = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Delete Competition
	 * 
	 * @param competitionId
	 * @return Result
	 */
	@Override
	public boolean deleteCompetition(int competitionId) {

		String sql = "SELECT * FROM admin_deletecompetition(?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Set Input Parameter */
			preparedStatement.setInt(1, competitionId);

			result = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Get Competition Info
	 * 
	 * @param competitionId
	 * @return CompetitionDTO
	 */
	@Override
	public CompetitionDTO getCompetitionInfo(int competitionId) {

		String sql = "SELECT * FROM admin_getcompetitioninfo(?)";
		CompetitionDTO competitionDTO = new CompetitionDTO();

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, competitionId);

			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();

			/* Get Result From Database */
			while (resultSet.next()) {

				competitionDTO.setCompetitionId(resultSet
						.getInt("CompetitionId"));
				competitionDTO.setCompetitionName(resultSet
						.getString("CompetitionName"));
				competitionDTO.setCompetitionLogoUrl(resultSet
						.getString("CompetitionLogoUrl"));
				competitionDTO.setDescription(resultSet
						.getString("Description"));
				competitionDTO.setBeginTime(CommonUtils
						.timestampToDate(resultSet.getTimestamp("BeginTime")));
				competitionDTO.setEndTime(CommonUtils.timestampToDate(resultSet
						.getTimestamp("EndTime")));
				competitionDTO
						.setParticipants(resultSet.getInt("Participants"));
				competitionDTO.setHotRewards(resultSet.getInt("HotRewards"));
				competitionDTO.setImageNum(resultSet.getInt("ImageNum"));
				competitionDTO.setGroupNum(resultSet.getInt("GroupNum"));
				competitionDTO.setCompetitionStatus(resultSet
						.getInt("CompetitionStatus"));
				competitionDTO.setCompetitionRewards(resultSet
						.getString("CompetitionRewards"));
				competitionDTO.setTermAndCondition(resultSet
						.getString("TermAndCondition"));
				competitionDTO.setInitPoint(resultSet.getInt("InitPoint"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return competitionDTO;
	}

	/**
	 * Get Competition By Name
	 * 
	 * @param competitionName
	 * @return Result
	 */
	@Override
	public int getCompetitionByName(String competitionName, int competitionId) {

		String sql = "";

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){

			if (competitionId != 0) {
				sql = "SELECT * FROM admin_getcompetitionbyname(?,?)";

				preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setString(1, competitionName);
				preparedStatement.setInt(2, competitionId);

			} else {
				sql = "SELECT * FROM admin_getcompetitionbyname(?)";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, competitionName);
			}

			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();

			/* Get Result From Database */
			if (resultSet.next() && (resultSet.getInt("CompetitionId") != 0)) {
				return ConstantUtil.RETCODE_ABNORMAL;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ConstantUtil.RETCODE_NORMAL;
	}

	/* =============== COMPETITION MANAGEMENT END ================== */

	/* =============== GROUP MANAGEMENT START ================== */

	/**
	 * Get List Group
	 * 
	 * @return List<GroupDTO>
	 */
	@Override
	public List<GroupDTO> getListGroup() {

		String sql = "SELECT * FROM admin_getlistgroup()";
		List<GroupDTO> listGroup = new ArrayList<GroupDTO>();

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();

			/* Get Result From Database */
			while (resultSet.next()) {

				GroupDTO groupDTO = new GroupDTO();

				groupDTO.setGroupId(resultSet.getInt("GroupId"));
				groupDTO.setGroupName(resultSet.getString("GroupName"));
				groupDTO.setGroupLogoUrl(resultSet.getString("GroupLogoUrl"));
				groupDTO.setDescription(resultSet.getString("Description"));
				groupDTO.setParticipants(resultSet.getInt("Participants"));
				groupDTO.setImageNum(resultSet.getInt("ImageNum"));
				groupDTO.setTotalPoint(resultSet.getLong("TotalPoint"));
				groupDTO.setStatus(resultSet.getInt("Status"));

				/* Add Group to list */
				listGroup.add(groupDTO);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listGroup;
	}

	/**
	 * Add a new Group
	 * 
	 * @param groupDTO
	 * @return Result
	 */
	@Override
	public boolean addGroup(GroupDTO groupDTO) {

		String sql = "SELECT * FROM admin_addgroup(?,?,?,?)";

		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Set Input Parameter */
			preparedStatement.setString(1, groupDTO.getGroupName());
			preparedStatement.setString(2, groupDTO.getGroupLogoUrl());
			preparedStatement.setString(3, groupDTO.getDescription());
			preparedStatement.setTimestamp(4,
					new Timestamp(new Date().getTime()));

			result = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Edit Group
	 * 
	 * @param groupDTO
	 * @return Result
	 */
	@Override
	public boolean editGroup(GroupDTO groupDTO) {

		String sql = "SELECT * FROM admin_editgroup(?,?,?,?)";

		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Set Input Parameter */
			preparedStatement.setInt(1, groupDTO.getGroupId());
			preparedStatement.setString(2, groupDTO.getGroupName());
			preparedStatement.setString(3, groupDTO.getGroupLogoUrl());
			preparedStatement.setString(4, groupDTO.getDescription());

			result = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Delete Group
	 * 
	 * @param groupId
	 * @return Result
	 */
	@Override
	public boolean deleteGroup(int groupId) {

		String sql = "SELECT * FROM admin_deletegroup(?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Set Input Parameter */
			preparedStatement.setInt(1, groupId);

			result = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Add group into competition
	 * 
	 * @param groupId
	 * @param competitionId
	 * @return Result
	 */
	@Override
	public boolean addGroupIntoCompetition(int groupId, int competitionId) {

		String sql = "SELECT * FROM admin_addgroupintocompetition(?,?)";

		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);

			/* Set Input Parameter */
			preparedStatement.setInt(1, groupId);
			preparedStatement.setInt(2, competitionId);

			result = preparedStatement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/* =============== GROUP MANAGEMENT END ================== */

	@Override
	public GroupDTO getGroup(int groupId) {
		String sql = "SELECT * FROM fb_group where \"GroupId\"= ?";

		GroupDTO result = new GroupDTO();

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){

			/* Open Connection To Execute SQL Statement */
			
			preparedStatement = connection.prepareStatement(sql);

			/* Set Input Parameter */
			preparedStatement.setInt(1, groupId);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result.setGroupId(resultSet.getInt("GroupId"));
				result.setGroupName(resultSet.getString("GroupName"));
				result.setGroupLogoUrl(resultSet.getString("GroupLogoUrl"));
				result.setDescription(resultSet.getString("Description"));
				result.setStatus(resultSet.getInt("Status"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<GroupDTO> getListGroup(int competitionId) {
		String sql = "SELECT grp.* "
				+ "FROM fb_competition_group comp_group, fb_group grp "
				+ "WHERE comp_group.\"CompetitionId\"= ? and comp_group.\"GroupId\" = grp.\"GroupId\" and grp.\"Status\"=1";

		List<GroupDTO> result = new ArrayList<GroupDTO>();

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){

			/* Open Connection To Execute SQL Statement */
			
			preparedStatement = connection.prepareStatement(sql);

			/* Set Input Parameter */
			preparedStatement.setInt(1, competitionId);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				GroupDTO group = new GroupDTO(); 
				group.setGroupId(resultSet.getInt("GroupId"));
				group.setGroupName(resultSet.getString("GroupName"));
				group.setGroupLogoUrl(resultSet.getString("GroupLogoUrl"));
				group.setDescription(resultSet.getString("Description"));
				group.setStatus(resultSet.getInt("Status"));
				result.add(group);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static void main(String[] args) {
//		String sql = grp
//		System.out.println(sql);
	}
}
