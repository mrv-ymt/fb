package com.s4you.flybeau.dto;

import java.util.Date;

/**
 * 
 * CommentDTO
 * Date: 05/10/2016
 * ThienMV
 * 
 * */
public class CommentDTO {
	private int commentId;
	private int imageId;
	private String comment;
	private Date commentTime;
	private UserDTO user;
	private String lifeTime;
	
	/**
	 * @return the commentId
	 */
	public int getCommentId() {
		return commentId;
	}
	
	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	/**
	 * @return the imageId
	 */
	public int getImageId() {
		return imageId;
	}
	
	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * @return the commentTime
	 */
	public Date getCommentTime() {
		return commentTime;
	}
	
	/**
	 * @param commentTime the commentTime to set
	 */
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	/**
	 * @return the userDTO
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param userDTO the userDTO to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}

	/**
	 * @return the lifeTime
	 */
	public String getLifeTime() {
		return lifeTime;
	}

	/**
	 * @param lifeTime the lifeTime to set
	 */
	public void setLifeTime(String lifeTime) {
		this.lifeTime = lifeTime;
	}
}