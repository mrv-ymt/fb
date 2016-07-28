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
import com.s4you.flybeau.dto.CommentDTO;
import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.utils.CommonUtils;

/**
 * CommentMngmDAO
 * @author ThienMV
 * Date: 14/5/2016
 */
@Repository
@Transactional
public class CommentMngmApiDAO {
	
	Connection connection;
	PreparedStatement preparedStatement;
	
	/**
	 * Add Comment To Database
	 * @param comment
	 * @return TRUE if insert successfully, FALSE if insert fail
	 */
	public boolean addComment(CommentDTO comment) {

		String sql =  "SELECT * FROM addcomment(?,?,?,?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, comment.getImageId());
			preparedStatement.setString(2, comment.getComment());
			preparedStatement.setInt(3, comment.getUser().getUserId());
			preparedStatement.setTimestamp(4, new Timestamp(new Date().getTime()));
					
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;
	}

	/**
	 * Get List Comment From Image
	 * @param imageId
	 * @return List<CommentDTO>
	 */
	public List<CommentDTO> getListComment(int imageId) {
		
		String sql =  "SELECT * FROM getlistcomment(?)";
		List<CommentDTO> listComment = new ArrayList<CommentDTO>();		
		CommentDTO commentDTO;		
		UserDTO userDTO;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, imageId);
					
			/* Execute SQL Statement */
			ResultSet resultSet = preparedStatement.executeQuery();		
			
			/* Get Result From Database */
			while(resultSet.next()) {
				commentDTO = new CommentDTO();
				commentDTO.setCommentId(resultSet.getInt("CommentId"));
				commentDTO.setComment(resultSet.getString("Comment"));
				commentDTO.setCommentTime(resultSet.getTimestamp("CommentTime"));
				commentDTO.setImageId(imageId);
				commentDTO.setLifeTime(CommonUtils.getLifeTime(resultSet.getTimestamp("CommentTime")));						
				
				userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("UserId"));
				userDTO.setFirstName(resultSet.getString("FirstName"));
				userDTO.setAvatarUrl(resultSet.getString("AvatarUrl"));
				
				commentDTO.setUser(userDTO);
				
				/* Add Comment to list */
				listComment.add(commentDTO);				
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return listComment;
	}

	/**
	 * Edit Comment
	 * @param comment
	 * @return TRUE if update successfully, FALSE update insert fail
	 */
	public boolean editComment(CommentDTO comment) {
		
		String sql =  "SELECT * FROM editcomment(?,?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, comment.getCommentId());
			preparedStatement.setString(2, comment.getComment());
					
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;
	}

	/**
	 * Delete Comment
	 * @param commentId
	 * @return TRUE if delete successfully, FALSE delete insert fail
	 */
	public boolean deleteComment(int commentId) {
		String sql =  "SELECT * FROM deletecomment(?)";
		boolean result = false;

		try (Connection connection = DatabaseConnection.getInstance()
				.getDataSource().getConnection()){
			preparedStatement = connection.prepareStatement(sql);
			
			/* Set Input Parameter */
			preparedStatement.setInt(1, commentId);
					
			result = preparedStatement.execute();			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} 
				
		return result;
	}
}