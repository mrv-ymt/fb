package com.s4you.flybeau.dto;

import java.util.Date;
import java.util.List;

/**
 * 
 * ImageInfoDTO 
 * Date: 11/05/2016 
 * ThienMV
 * 
 * */
public class ImageInfoDTO {
	
	private int imageId;
	private int competitionId;
	private String competitionName;
	private Date uploadTime;	
	private int likeCount;
	private int voteCount;
	private int point;
	private String imageUrl;
	private int imageStatus;
	private String lng;
	private String lat;
	private String locationName;
	private int speciesId;
	private String description;
	private UserDTO user;
	private String lifeTime;
	private int commentNum;
	private String fileToBase64;
	private String groupName;
	private String originalImageName;
	private String taxonName;
    
	private List<CommentDTO> listCommentDTO;

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
	 * @return the competitionId
	 */
	public int getCompetitionId() {
		return competitionId;
	}

	/**
	 * @param competitionId the competitionId to set
	 */
	public void setCompetitionId(int competitionId) {
		this.competitionId = competitionId;
	}

	/**
	 * @return the uploadTime
	 */
	public Date getUploadTime() {
		return uploadTime;
	}

	/**
	 * @param uploadTime the uploadTime to set
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	/**
	 * @return the likeCount
	 */
	public int getLikeCount() {
		return likeCount;
	}

	/**
	 * @param likeCount the likeCount to set
	 */
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	/**
	 * @return the voteCount
	 */
	public int getVoteCount() {
		return voteCount;
	}

	/**
	 * @param voteCount the voteCount to set
	 */
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}	

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the imageStatus
	 */
	public int getImageStatus() {
		return imageStatus;
	}

	/**
	 * @param imageStatus the imageStatus to set
	 */
	public void setImageStatus(int imageStatus) {
		this.imageStatus = imageStatus;
	}

	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @return the speciesId
	 */
	public int getSpeciesId() {
		return speciesId;
	}

	/**
	 * @param speciesId the speciesId to set
	 */
	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the listCommentDTO
	 */
	public List<CommentDTO> getListCommentDTO() {
		return listCommentDTO;
	}

	/**
	 * @param listCommentDTO the listCommentDTO to set
	 */
	public void setListCommentDTO(List<CommentDTO> listCommentDTO) {
		this.listCommentDTO = listCommentDTO;
	}

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}

	/**
	 * @return the competitionName
	 */
	public String getCompetitionName() {
		return competitionName;
	}

	/**
	 * @param competitionName the competitionName to set
	 */
	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
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

	/**
	 * @return the commentNum
	 */
	public int getCommentNum() {
		return commentNum;
	}

	/**
	 * @param commentNum the commentNum to set
	 */
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	/**
	 * @return the fileToBase64
	 */
	public String getFileToBase64() {
		return fileToBase64;
	}

	/**
	 * @param fileToBase64 the fileToBase64 to set
	 */
	public void setFileToBase64(String fileToBase64) {
		this.fileToBase64 = fileToBase64;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getOriginalImageName() {
		return originalImageName;
	}

	public void setOriginalImageName(String originalImageName) {
		this.originalImageName = originalImageName;
	}
	/*
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}

	public String getTaxonName() {
		return taxonName;
	}

	public void setTaxonName(String taxonName) {
		this.taxonName = taxonName;
	}
}