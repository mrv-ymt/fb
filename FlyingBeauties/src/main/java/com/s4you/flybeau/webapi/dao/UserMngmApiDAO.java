package com.s4you.flybeau.webapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.s4you.flybeau.dbconnection.DatabaseConnection;
import com.s4you.flybeau.dto.UserDTO;

/**
 * UserManageDAO
 * @author ThienMV
 * Date: 12/5/2016
 */
@Repository
@Transactional
public class UserMngmApiDAO {
	
	Connection connection;
	PreparedStatement preparedStatement;
	
	/**
	 * User Login DAO
	 * @param userNameOrMail
	 * @param password
	 * @param FacebookToken
	 * @param FacebookId
	 * @return UserDTO
	 */
	public UserDTO login(String id, String token) {				
			
		String sql =  "SELECT * FROM login(?,?)";
		UserDTO user =  null;
		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setString(1, id.trim());
			preparedStatement.setString(2, token.trim());	
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next() && (resultSet.getInt("userId") != 0)) {
				
				/* Get Data From Result Set */
				user = new UserDTO();
				user.setUserId(resultSet.getInt("UserId"));
				user.setUsername(resultSet.getString("Username"));
				user.setFirstName(resultSet.getString("FirstName"));	
				user.setLastName(resultSet.getString("LastName"));	
				user.setCountry(resultSet.getString("Country"));
				user.setCity(resultSet.getString("City"));
				user.setAvatarUrl(resultSet.getString("AvatarUrl"));
				user.setBirthday(resultSet.getString("Birthday"));
				user.setEmail(resultSet.getString("Email"));
				user.setPhoneNumber(resultSet.getString("PhoneNumber"));
				user.setRoleId(resultSet.getInt("RoleId"));
				user.setGroupId(resultSet.getInt("GroupId"));			
				user.setImageNum(resultSet.getInt("ImageNum"));
				user.setSumPoint(resultSet.getInt("SumPoint"));
				user.setPreferredLanguage(resultSet.getString("PreferredLanguage"));
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return user;
	}
	
	

	/**
	 * Get a user by email or username 
	 * @param userNameOrMail
	 * @param email 
	 * @return UserDTO
	 */
	public UserDTO getUser(String userName, String email) {			
		
		UserDTO user = null;
		String sql =  "SELECT * FROM getuser(?,?)";

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, email);		
						
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next() && (resultSet.getInt("userId") != 0)) {	
				
				/* Get Data From Result Set */
				user = new UserDTO();
				user.setUserId(resultSet.getInt("UserId"));
				user.setUsername(resultSet.getString("Username"));
				user.setFirstName(resultSet.getString("FirstName"));	
				user.setLastName(resultSet.getString("LastName"));	
				user.setCountry(resultSet.getString("Country"));
				user.setCity(resultSet.getString("City"));
				user.setAvatarUrl(resultSet.getString("AvatarUrl"));
				user.setBirthday(resultSet.getString("Birthday"));
				user.setEmail(resultSet.getString("Email"));
				user.setPhoneNumber(resultSet.getString("PhoneNumber"));
				user.setRoleId(resultSet.getInt("RoleId"));
				user.setGroupId(resultSet.getInt("GroupId"));	
				user.setPreferredLanguage(resultSet.getString("PreferredLanguage"));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return user;
	}

	/**
	 * Registration a new user
	 * @param UserDTO
	 * @return Insert Result
	 */
	public boolean register(UserDTO user) {
		
		String sql =  "SELECT * FROM register(?,?,?,?,?,?,?,?,?,?,?,?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setString(4, user.getCity());
			preparedStatement.setInt(5, user.getRoleId());
			preparedStatement.setString(6, user.getPhoneNumber());
			preparedStatement.setString(7, user.getFirstName());
			preparedStatement.setString(8, user.getLastName());
			preparedStatement.setString(9, user.getEmail());
			preparedStatement.setTimestamp(10, new Timestamp(user.getResgistrationTime().getTime()));
			preparedStatement.setString(11, user.getBirthday());
			preparedStatement.setString(12, user.getAvatarUrl());
						
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;
	}

	/**
	 * Update User Profile
	 * @param UserDTO
	 * @return Update Result
	 */
	public boolean editProfile(UserDTO user) {

		String sql =  "SELECT * FROM editprofile(?,?,?,?,?,?,?,?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPhoneNumber());			
			preparedStatement.setString(6, user.getCountry());
			preparedStatement.setString(7, user.getCity());
			preparedStatement.setString(8, user.getBirthday());		
						
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;
	}
	
	/**
	 * 
	 * @param userId
	 * @param newPassword
	 * @return Update Result
	 */
	public boolean updatePassword(int userId, String newPassword) {
		
		String sql =  "SELECT * FROM resetpassword(?,?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, newPassword);
					
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;
		
	}	
	
	/**
	 * Get Three Top User Who updated many image in competition
	 * @param competitionId
	 * @return List Top User
	 */
	public List<UserDTO> getTopUser(int competitionId) {
		
		String sql =  "SELECT * FROM gettopuser_competition(?)";
		List<UserDTO> listUser = new ArrayList<UserDTO>();		
		UserDTO user;		

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, competitionId);			
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				user = new UserDTO();
				user.setUserId(resultSet.getInt("UserId"));
				user.setFirstName(resultSet.getString("FirstName"));
				user.setAvatarUrl(resultSet.getString("AvatarUrl"));
				user.setImageNum(resultSet.getInt("ImageNum"));
				user.setSumPoint(resultSet.getInt("SumPoint"));
				user.setGroupName(resultSet.getString("GroupName"));
				listUser.add(user);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return listUser;
	}
	
	/**
	 * Get Three Top User Who updated many image in Group
	 * @param groupId
	 * @return List Top User
	 */
	public List<UserDTO> getTopUserInGroup(int groupId) {
		
		String sql =  "SELECT * FROM gettopuser_group(?)";
		List<UserDTO> listUser = new ArrayList<UserDTO>();		
		UserDTO user;		

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, groupId);			
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				user = new UserDTO();
				user.setUserId(resultSet.getInt("UserId"));
				user.setFirstName(resultSet.getString("FirstName"));
				user.setAvatarUrl(resultSet.getString("AvatarUrl"));
				user.setImageNum(resultSet.getInt("ImageNum"));
				user.setSumPoint(resultSet.getInt("SumPoint"));
				listUser.add(user);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
				
		return listUser;
	}

	/**
	 * Check Old Pass
	 * @param userId
	 * @param encryptOldPasswd
	 * @return Check Result
	 */
	public boolean getCheckOldPass(int userId, String encryptOldPasswd) {
		
		String sql =  "SELECT * FROM getcheckoldpass(?,?)";

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, encryptOldPasswd);		
						
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next() && (resultSet.getInt("userId") != 0)) {	
				return true;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return false;
	}

	/**
	 * Edit Avatar Url To Database
	 * @param user
	 * @return Retcode
	 */
	public boolean editAvatar(UserDTO user) {

		String sql =  "SELECT * FROM editavatar(?,?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, user.getUserId());
			preparedStatement.setString(2, user.getAvatarUrl());			
						
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;
	}
	
	/**
	 * Get a user by userID
	 * @param userId
	 * @return UserDTO
	 */
	public UserDTO getUserById(int userId) {			
		
		UserDTO user = null;
		String sql =  "SELECT * FROM getuserbyid(?)";

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, userId);
						
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next() && (resultSet.getInt("userId") != 0)) {	
				
				/* Get Data From Result Set */
				user = new UserDTO();
				user.setUserId(resultSet.getInt("UserId"));
				user.setFirstName(resultSet.getString("FirstName"));	
				user.setLastName(resultSet.getString("LastName"));	
				user.setCountry(resultSet.getString("Country"));
				user.setCity(resultSet.getString("City"));
				user.setAvatarUrl(resultSet.getString("AvatarUrl"));
				user.setBirthday(resultSet.getString("Birthday"));
				user.setEmail(resultSet.getString("Email"));
				user.setPhoneNumber(resultSet.getString("PhoneNumber"));
				user.setSumPoint(resultSet.getInt("SumPoint"));
				user.setImageNum(resultSet.getInt("ImageNum"));
				user.setUsername(resultSet.getString("Username"));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return user;
	}



	public boolean saveLang(int userId, String lang) {
		
		String sql =  "SELECT * FROM savelang(?,?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, lang);
					
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;
	}
}