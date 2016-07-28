package com.s4you.flybeau.dto;

import java.util.Date;
import java.util.List;

import com.s4you.flybeau.utils.JsonBinder;

/**
 *
 * GroupDTO
 * Date: 05/10/2016
 * ThienMV
 *
 * */
public class GroupDTO {

	private int groupId;
	private String groupName;
	private String groupLogoUrl;
	private String description;
	private Date insertTime;
	private int imageNum;
	private int participants;
	private long totalPoint;
	private int status; 
	
	private String fileToBase64;

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	private List<UserDTO> listUser;

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
	 * @return the totalPoint
	 */
	public long getTotalPoint() {
		return totalPoint;
	}

	/**
	 * @param totalPoint the totalPoint to set
	 */
	public void setTotalPoint(long totalPoint) {
		this.totalPoint = totalPoint;
	}

	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
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

	/**
	 * @return the groupLogoUrl
	 */
	public String getGroupLogoUrl() {
		return groupLogoUrl;
	}

	/**
	 * @param groupLogoUrl the groupLogoUrl to set
	 */
	public void setGroupLogoUrl(String groupLogoUrl) {
		this.groupLogoUrl = groupLogoUrl;
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
	 public String getFileToBase64() {
		return fileToBase64;
	}

	public void setFileToBase64(String fileToBase64) {
		this.fileToBase64 = fileToBase64;
	}

	public static void main(String[] args) {
		System.out.println(JsonBinder.toJson(new GroupDTO()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + groupId;
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result
				+ ((insertTime == null) ? 0 : insertTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupDTO other = (GroupDTO) obj;
		if (groupId != other.groupId)
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroupDTO [groupId=" + groupId + ", groupName=" + groupName
				+ ", totalPoint=" + totalPoint + ", status=" + status + "]";
	}
}
