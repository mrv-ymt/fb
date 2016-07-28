package com.s4you.flybeau.dto;

import java.util.Date;
import java.util.List;

/**
 * 
 * CompetitionDTO
 * Date: 05/10/2016
 * ThienMV
 * 
 * */
public class CompetitionDTO {
	
	private int competitionId;
	private int competitionStatus;
	private String competitionName;
	private String competitionLogoUrl;
	private String competitionRemarks;
	private int hotRewards;
	private int initPoint;
	private String competitionRewards;
	private Date insertTime;
	private String beginTime;
	private String endTime;
	private String description;	
	private int participants;
	private int joined;
	private int imageNum;
	private int groupNum;
	private int ownImageNum;
	private String fileToBase64;
	private String termAndCondition;

	private List<ImageInfoDTO> listImageInfo;
	private List<UserDTO> listUser;
	private List<GroupDTO> listGroup;

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
	 * @return the competitionStatus
	 */
	public int getCompetitionStatus() {
		return competitionStatus;
	}

	/**
	 * @param competitionStatus the competitionStatus to set
	 */
	public void setCompetitionStatus(int competitionStatus) {
		this.competitionStatus = competitionStatus;
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
	 * @return the competitionLogoUrl
	 */
	public String getCompetitionLogoUrl() {
		return competitionLogoUrl;
	}

	/**
	 * @param competitionLogoUrl the competitionLogoUrl to set
	 */
	public void setCompetitionLogoUrl(String competitionLogoUrl) {
		this.competitionLogoUrl = competitionLogoUrl;
	}

	/**
	 * @return the competitionRemarks
	 */
	public String getCompetitionRemarks() {
		return competitionRemarks;
	}

	/**
	 * @param competitionRemarks the competitionRemarks to set
	 */
	public void setCompetitionRemarks(String competitionRemarks) {
		this.competitionRemarks = competitionRemarks;
	}

	/**
	 * @return the hotRewards
	 */
	public int getHotRewards() {
		return hotRewards;
	}

	/**
	 * @param hotRewards the hotRewards to set
	 */
	public void setHotRewards(int hotRewards) {
		this.hotRewards = hotRewards;
	}

	/**
	 * @return the insertTime
	 */
	public Date getInsertTime() {
		return insertTime;
	}

	/**
	 * @param insertTime the insertTime to set
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	/**
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	 * @return the listImageInfo
	 */
	public List<ImageInfoDTO> getListImageInfo() {
		return listImageInfo;
	}

	/**
	 * @param listImageInfo the listImageInfo to set
	 */
	public void setListImageInfo(List<ImageInfoDTO> listImageInfo) {
		this.listImageInfo = listImageInfo;
	}

	/**
	 * @return the listUser
	 */
	public List<UserDTO> getListUser() {
		return listUser;
	}

	/**
	 * @param listUser the listUser to set
	 */
	public void setListUser(List<UserDTO> listUser) {
		this.listUser = listUser;
	}

	/**
	 * @return the joined
	 */
	public int getJoined() {
		return joined;
	}

	/**
	 * @param joined the joined to set
	 */
	public void setJoined(int joined) {
		this.joined = joined;
	}

	/**
	 * @return the participants
	 */
	public int getParticipants() {
		return participants;
	}

	/**
	 * @param participants the participants to set
	 */
	public void setParticipants(int participants) {
		this.participants = participants;
	}

	/**
	 * @return the imageNum
	 */
	public int getImageNum() {
		return imageNum;
	}

	/**
	 * @param imageNum the imageNum to set
	 */
	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}

	/**
	 * @return the groupNum
	 */
	public int getGroupNum() {
		return groupNum;
	}

	/**
	 * @param groupNum the groupNum to set
	 */
	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	/**
	 * @return the ownImageNum
	 */
	public int getOwnImageNum() {
		return ownImageNum;
	}

	/**
	 * @param ownImageNum the ownImageNum to set
	 */
	public void setOwnImageNum(int ownImageNum) {
		this.ownImageNum = ownImageNum;
	}

	/**
	 * @return the listGroup
	 */
	public List<GroupDTO> getListGroup() {
		return listGroup;
	}

	/**
	 * @param listGroup the listGroup to set
	 */
	public void setListGroup(List<GroupDTO> listGroup) {
		this.listGroup = listGroup;
	}

	/**
	 * @return the competitionRewards
	 */
	public String getCompetitionRewards() {
		return competitionRewards;
	}

	/**
	 * @param competitionRewards the competitionRewards to set
	 */
	public void setCompetitionRewards(String competitionRewards) {
		this.competitionRewards = competitionRewards;
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
	 * @return the termAndCondition
	 */
	public String getTermAndCondition() {
		return termAndCondition;
	}

	/**
	 * @param termAndCondition the termAndCondition to set
	 */
	public void setTermAndCondition(String termAndCondition) {
		this.termAndCondition = termAndCondition;
	}

	/**
	 * @return the initPoint
	 */
	public int getInitPoint() {
		return initPoint;
	}

	/**
	 * @param initPoint the initPoint to set
	 */
	public void setInitPoint(int initPoint) {
		this.initPoint = initPoint;
	}
}