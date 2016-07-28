package com.s4you.flybeau.webapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.s4you.flybeau.dbconnection.DatabaseConnection;
import com.s4you.flybeau.dto.ImageInfoDTO;
import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.utils.CommonUtils;

/**
 * ImageMngmDAO
 * @author ThienMV
 * Date: 18/5/2016
 */
@Repository
@Transactional
public class ImageMngmApiDAO {
	
	Connection connection;
	PreparedStatement preparedStatement;
	
	/**
	 * Get All Image 
	 * @param isHotImage
	 * @param beginNum
	 * @param pageSize
	 * @return List<ImageInfoDTO>
	 */
	public List<ImageInfoDTO> getAllImage(boolean isHotImage, int beginNum, int pageSize) {
		
		String sql =  "SELECT * FROM getallimage(?,?,?)";
		List<ImageInfoDTO> listImage = new ArrayList<ImageInfoDTO>();		
		ImageInfoDTO imageInfoDTO;		
		UserDTO userDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setBoolean(1, isHotImage);
			preparedStatement.setInt(2, beginNum - 1);
			preparedStatement.setInt(3, pageSize);
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				imageInfoDTO = new ImageInfoDTO();
				imageInfoDTO.setImageId(resultSet.getInt("ImageId"));
				imageInfoDTO.setCompetitionName(resultSet.getString("CompetitionName"));
				imageInfoDTO.setCompetitionId(resultSet.getInt("CompetitionId"));
				imageInfoDTO.setPoint(resultSet.getInt("Point"));
				imageInfoDTO.setImageUrl(resultSet.getString("ImageUrl"));
				imageInfoDTO.setLng(resultSet.getString("Lng"));
				imageInfoDTO.setLat(resultSet.getString("Lat"));
				imageInfoDTO.setLocationName(resultSet.getString("LocationName"));
				imageInfoDTO.setSpeciesId(resultSet.getInt("SpeciesId"));
				imageInfoDTO.setDescription(resultSet.getString("Description"));
				imageInfoDTO.setCommentNum(resultSet.getInt("CommentNum"));
				imageInfoDTO.setLifeTime(CommonUtils.getLifeTime(resultSet.getTimestamp("UploadTime")));
				imageInfoDTO.setGroupName(resultSet.getString("GroupName"));
				imageInfoDTO.setUploadTime(resultSet.getTimestamp("UploadTime"));
				imageInfoDTO.setTaxonName(resultSet.getString("speciesname"));
				
				userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("UserId"));
				userDTO.setFirstName(resultSet.getString("FirstName"));
				userDTO.setAvatarUrl(resultSet.getString("AvatarUrl"));
				imageInfoDTO.setUser(userDTO);	
				
				/* Add Image To List Image */
				listImage.add(imageInfoDTO);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
				
		return listImage;
	}

	/**
	 * Get List Image of User
	 * @param userId
	 * @param competitionId
	 * @param orderBy
	 * @param beginNum
	 * @param pageSize
	 * @return List<ImageInfoDTO>
	 */
	public List<ImageInfoDTO> getOwnListImageCptt(int userId, int competitionId,
			int orderBy, int beginNum, int pageSize) {
		
		String sql =  "SELECT * FROM getownimage_competition(?,?,?,?,?)";
		List<ImageInfoDTO> listImage = new ArrayList<ImageInfoDTO>();		
		ImageInfoDTO imageInfoDTO;		
		UserDTO userDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, competitionId);
			preparedStatement.setInt(3, orderBy);
			preparedStatement.setInt(4, beginNum - 1);
			preparedStatement.setInt(5, pageSize);
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				imageInfoDTO = new ImageInfoDTO();
				imageInfoDTO.setImageId(resultSet.getInt("ImageId"));
				imageInfoDTO.setCompetitionName(resultSet.getString("CompetitionName"));
				imageInfoDTO.setCompetitionId(resultSet.getInt("CompetitionId"));
				imageInfoDTO.setPoint(resultSet.getInt("Point"));
				imageInfoDTO.setImageUrl(resultSet.getString("ImageUrl"));
				imageInfoDTO.setLng(resultSet.getString("Lng"));
				imageInfoDTO.setLat(resultSet.getString("Lat"));
				imageInfoDTO.setLocationName(resultSet.getString("LocationName"));
				imageInfoDTO.setSpeciesId(resultSet.getInt("SpeciesId"));
				imageInfoDTO.setDescription(resultSet.getString("Description"));
				imageInfoDTO.setCommentNum(resultSet.getInt("CommentNum"));
				imageInfoDTO.setLifeTime(CommonUtils.getLifeTime(resultSet.getTimestamp("UploadTime")));
				imageInfoDTO.setUploadTime(resultSet.getTimestamp("UploadTime"));
				
				userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("UserId"));
				userDTO.setFirstName(resultSet.getString("FirstName"));
				userDTO.setAvatarUrl(resultSet.getString("AvatarUrl"));
				imageInfoDTO.setUser(userDTO);	
				
				/* Add Image To List Image */
				listImage.add(imageInfoDTO);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return listImage;
	}
	
	public List<ImageInfoDTO> getAllOwnImage(int userId, int orderBy,
			int beginNum, int pageSize) {
		
		String sql =  "SELECT * FROM getallownimage(?,?,?,?)";
		List<ImageInfoDTO> listImage = new ArrayList<ImageInfoDTO>();		
		ImageInfoDTO imageInfoDTO;		
		UserDTO userDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, orderBy);
			preparedStatement.setInt(3, beginNum - 1);
			preparedStatement.setInt(4, pageSize);
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				imageInfoDTO = new ImageInfoDTO();
				imageInfoDTO.setImageId(resultSet.getInt("ImageId"));
				imageInfoDTO.setCompetitionName(resultSet.getString("CompetitionName"));
				imageInfoDTO.setCompetitionId(resultSet.getInt("CompetitionId"));
				imageInfoDTO.setPoint(resultSet.getInt("Point"));
				imageInfoDTO.setImageUrl(resultSet.getString("ImageUrl"));
				imageInfoDTO.setLng(resultSet.getString("Lng"));
				imageInfoDTO.setLat(resultSet.getString("Lat"));
				imageInfoDTO.setLocationName(resultSet.getString("LocationName"));
				imageInfoDTO.setSpeciesId(resultSet.getInt("SpeciesId"));
				imageInfoDTO.setDescription(resultSet.getString("Description"));
				imageInfoDTO.setCommentNum(resultSet.getInt("CommentNum"));
				imageInfoDTO.setLifeTime(CommonUtils.getLifeTime(resultSet.getTimestamp("UploadTime")));
				imageInfoDTO.setUploadTime(resultSet.getTimestamp("UploadTime"));
				
				userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("UserId"));
				userDTO.setFirstName(resultSet.getString("FirstName"));
				userDTO.setAvatarUrl(resultSet.getString("AvatarUrl"));
				imageInfoDTO.setUser(userDTO);	
				
				/* Add Image To List Image */
				listImage.add(imageInfoDTO);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return listImage;
	}	

	/**
	 * Get List Image Of Competition
	 * @param competitionId
	 * @param orderBy
	 * @param beginNum
	 * @param pageSize
	 * @return List<ImageInfoDTO>
	 */
	public List<ImageInfoDTO> getCompetitionListImage(int competitionId,
			int orderBy, int beginNum, int pageSize) {
		String sql =  "SELECT * FROM getlistimage_competition(?,?,?,?)";
		List<ImageInfoDTO> listImage = new ArrayList<ImageInfoDTO>();		
		ImageInfoDTO imageInfoDTO;		
		UserDTO userDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, competitionId);
			preparedStatement.setInt(2, orderBy);
			preparedStatement.setInt(3, beginNum - 1);
			preparedStatement.setInt(4, pageSize);
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				imageInfoDTO = new ImageInfoDTO();
				imageInfoDTO.setImageId(resultSet.getInt("ImageId"));
				imageInfoDTO.setCompetitionName(resultSet.getString("CompetitionName"));
				imageInfoDTO.setCompetitionId(resultSet.getInt("CompetitionId"));
				imageInfoDTO.setUploadTime(resultSet.getTimestamp("UploadTime"));
				imageInfoDTO.setPoint(resultSet.getInt("Point"));
				imageInfoDTO.setImageUrl(resultSet.getString("ImageUrl"));
				imageInfoDTO.setLng(resultSet.getString("Lng"));
				imageInfoDTO.setLat(resultSet.getString("Lat"));
				imageInfoDTO.setLocationName(resultSet.getString("LocationName"));
				imageInfoDTO.setSpeciesId(resultSet.getInt("SpeciesId"));
				imageInfoDTO.setDescription(resultSet.getString("Description"));
				imageInfoDTO.setLifeTime(CommonUtils.getLifeTime(resultSet.getTimestamp("UploadTime")));
				imageInfoDTO.setCommentNum(resultSet.getInt("commentNum"));
				imageInfoDTO.setGroupName(resultSet.getString("GroupName"));
				
				userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("UserId"));
				userDTO.setFirstName(resultSet.getString("FirstName"));
				userDTO.setAvatarUrl(resultSet.getString("AvatarUrl"));
				imageInfoDTO.setUser(userDTO);	
				
				/* Add Image To List Image */
				listImage.add(imageInfoDTO);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return listImage;
	}

	/**
	 * Upload Image
	 * @param imageInfo
	 * @return TRUE if insert successfully, FALSE if insert fail
	 */
	public boolean uploadImage(ImageInfoDTO imageInfo) {

		String sql =  "SELECT * FROM uploadimage(?,?,?,?,?,?,?,?,?,?,?,?)";
		String sqlGetCompetitionInfo =  "SELECT * FROM admin_getcompetitioninfo(?)";
		boolean result = false;
		int initPoint = 0;
		
		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			
			PreparedStatement preparedStatementGetCompetitionInfo = connection.prepareStatement(sqlGetCompetitionInfo);
			preparedStatementGetCompetitionInfo.setInt(1, imageInfo.getCompetitionId());
			
			ResultSet resultSet = preparedStatementGetCompetitionInfo.executeQuery();
			if(resultSet.next()) {
				initPoint = resultSet.getInt("InitPoint");
			}
			
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, imageInfo.getUser().getUserId());
			preparedStatement.setInt(2, imageInfo.getCompetitionId());
			preparedStatement.setString(3, imageInfo.getImageUrl());
			preparedStatement.setString(4, imageInfo.getLng());
			preparedStatement.setString(5, imageInfo.getLat());
			preparedStatement.setString(6, imageInfo.getLocationName());
			preparedStatement.setInt(7, imageInfo.getSpeciesId());
			preparedStatement.setString(8, imageInfo.getDescription());
			preparedStatement.setInt(9, initPoint);
			preparedStatement.setInt(10, imageInfo.getImageStatus());
			preparedStatement.setTimestamp(11, new Timestamp(new Date().getTime()));
			preparedStatement.setString(12, imageInfo.getOriginalImageName());
					
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;
	}

	/**
	 * Edit Image
	 * @param imageInfo
	 * @return TRUE if update successfully, FALSE if update fail
	 */
	public boolean editImage(ImageInfoDTO imageInfo) {
		
		String sql =  "SELECT * FROM editimage(?,?,?,?,?,?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, imageInfo.getImageId());
			preparedStatement.setString(2, imageInfo.getLng());
			preparedStatement.setString(3, imageInfo.getLat());
			preparedStatement.setString(4, imageInfo.getLocationName());
			preparedStatement.setString(5, imageInfo.getDescription());
			preparedStatement.setInt(6, imageInfo.getSpeciesId());
					
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
				
		return result;		
	}

	/**
	 * Delete Image
	 * @param imageId
	 * @return TRUE if delete successfully, FALSE if delete fail
	 */
	public boolean deleteImage(int imageId) {

		String sql =  "SELECT * FROM deleteimage(?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, imageId);			
					
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;		
	}

	/**
	 * Get Info of Image
	 * @param imageId
	 * @return ImageInfoDTO
	 */
	public ImageInfoDTO getImage(int imageId) {

		String sql =  "SELECT * FROM getimage(?)";
		ImageInfoDTO imageInfoDTO = null;		
		UserDTO userDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, imageId);
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			if(resultSet.next()) {
				imageInfoDTO = new ImageInfoDTO();
				imageInfoDTO.setImageId(resultSet.getInt("ImageId"));
				imageInfoDTO.setCompetitionId(resultSet.getInt("CompetitionId"));
				imageInfoDTO.setCompetitionName(resultSet.getString("CompetitionName"));
				imageInfoDTO.setPoint(resultSet.getInt("Point"));
				imageInfoDTO.setImageUrl(resultSet.getString("ImageUrl"));
				imageInfoDTO.setLng(resultSet.getString("Lng"));
				imageInfoDTO.setLat(resultSet.getString("Lat"));
				imageInfoDTO.setLocationName(resultSet.getString("LocationName"));
				imageInfoDTO.setSpeciesId(resultSet.getInt("SpeciesId"));
				imageInfoDTO.setDescription(resultSet.getString("Description"));
				imageInfoDTO.setUploadTime(resultSet.getTimestamp("UploadTime"));
				
				userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("UserId"));
				userDTO.setFirstName(resultSet.getString("FirstName"));
				userDTO.setAvatarUrl(resultSet.getString("AvatarUrl"));
				imageInfoDTO.setUser(userDTO);	
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return imageInfoDTO;
	}

	/**
	 * Get List Image In Group
	 * @param userId
	 * @param orderBy
	 * @param beginNum
	 * @param pageSize
	 * @return List<ImageInfoDTO>
	 */
	public List<ImageInfoDTO> getListImageGroup(int userId, int orderBy,
			int beginNum, int pageSize) {
		String sql =  "SELECT * FROM getlistimage_group(?,?,?,?)";
		List<ImageInfoDTO> listImage = new ArrayList<ImageInfoDTO>();		
		ImageInfoDTO imageInfoDTO;		
		UserDTO userDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, orderBy);
			preparedStatement.setInt(3, beginNum - 1);
			preparedStatement.setInt(4, pageSize);
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				imageInfoDTO = new ImageInfoDTO();
				imageInfoDTO.setImageId(resultSet.getInt("ImageId"));
				imageInfoDTO.setCompetitionName(resultSet.getString("CompetitionName"));
				imageInfoDTO.setCompetitionId(resultSet.getInt("CompetitionId"));
				imageInfoDTO.setUploadTime(resultSet.getTimestamp("UploadTime"));
				imageInfoDTO.setPoint(resultSet.getInt("Point"));
				imageInfoDTO.setImageUrl(resultSet.getString("ImageUrl"));
				imageInfoDTO.setLng(resultSet.getString("Lng"));
				imageInfoDTO.setLat(resultSet.getString("Lat"));
				imageInfoDTO.setLocationName(resultSet.getString("LocationName"));
				imageInfoDTO.setSpeciesId(resultSet.getInt("SpeciesId"));
				imageInfoDTO.setDescription(resultSet.getString("Description"));
				imageInfoDTO.setLifeTime(CommonUtils.getLifeTime(resultSet.getTimestamp("UploadTime")));
				imageInfoDTO.setCommentNum(resultSet.getInt("commentNum"));
				imageInfoDTO.setTaxonName(resultSet.getString("speciesname"));
				
				userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("UserId"));
				userDTO.setFirstName(resultSet.getString("FirstName"));
				userDTO.setAvatarUrl(resultSet.getString("AvatarUrl"));
				imageInfoDTO.setUser(userDTO);	
				
				/* Add Image To List Image */
				listImage.add(imageInfoDTO);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return listImage;
	}

	/**
	 * Count All Image Number
	 * @return imageNumberTotal
	 */
	public int getImagePageNumberAll(int pageSize) {
		
		String sql =  "SELECT * FROM countallimage()";
		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(resultSet.next()) {
				return CommonUtils.getPageNum(resultSet.getInt(1), pageSize);	
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}				

		return 0;
	}

	/**
	 * Count All Image in Own List
	 * @param userId
	 * @param competitionId
	 * @return imageNumberTotal
	 */
	public int getImagePageNumberOwnList(int userId, int competitionId, int pageSize) {
		
		String sql =  "SELECT * FROM count_listimage_own(?,?)";
		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, competitionId);		
			
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(resultSet.next()) {
				return CommonUtils.getPageNum(resultSet.getInt(1), pageSize);			
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}				

		return 0;
	}

	/**
	 * Count All Image in Competition
	 * @param competitionId
	 * @return imageNumberTotal
	 */
	public int getImagePageNumberInCompetition(int competitionId, int pageSize) {
		
		String sql =  "SELECT * FROM count_listimage_competition(?)";
		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, competitionId);	
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(resultSet.next()) {
				return CommonUtils.getPageNum(resultSet.getInt(1), pageSize);				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}				

		return 0;
	}

	/**
	 * Count All Image in Group
	 * @param userId
	 * @return imageNumberTotal
	 */
	public int getImagePageNumberInGroup(int userId, int pageSize) {
		
		String sql =  "SELECT * FROM count_listimage_group(?)";
		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();	
			if(resultSet.next()) {
				return CommonUtils.getPageNum(resultSet.getInt(1), pageSize);			
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}				

		return 0;
	}
}