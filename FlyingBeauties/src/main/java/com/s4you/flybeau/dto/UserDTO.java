package com.s4you.flybeau.dto;

import java.util.Date;

/**
 * 
 * UserDTO 
 * Date: 11/05/2016 
 * ThienMV
 * 
 * */
public class UserDTO {
	
	private int userId;
	private String username;
	private String password;
	private String facebookId;
	private String facebookToken;
	private String googleId;
	private String googleToken;
	private String goldpay;
	private String country;
	private String city;
	private String avatarUrl;
	private int voterights;
	private int roleId;
	private int groupId;
	private String groupName;
	private String phoneNumber;
	private Date resgistrationTime;
	private String email;
	private String birthday;
	private int imageNum;
	private int sumPoint;
	private String firstName;
	private String lastName;
	private String fileToBase64;
	private String jsessionId;
	private int status;
	private String preferredLanguage;
	
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

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the goldpay
	 */
	public String getGoldpay() {
		return goldpay;
	}
	
	/**
	 * @param goldpay the goldpay to set
	 */
	public void setGoldpay(String goldpay) {
		this.goldpay = goldpay;
	}

	/**
	 * @return the avatarUrl
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}
	
	/**
	 * @param avatarUrl the avatarUrl to set
	 */
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	/**
	 * @return the voterights
	 */
	public int getVoterights() {
		return voterights;
	}
	
	/**
	 * @param voterights the voterights to set
	 */
	public void setVoterights(int voterights) {
		this.voterights = voterights;
	}
	
	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}
	
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @return the facebookId
	 */
	public String getFacebookId() {
		return facebookId;
	}
	
	/**
	 * @param facebookId the facebookId to set
	 */
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	
	/**
	 * @return the facebookToken
	 */
	public String getFacebookToken() {
		return facebookToken;
	}
	
	/**
	 * @param facebookToken the facebookToken to set
	 */
	public void setFacebookToken(String facebookToken) {
		this.facebookToken = facebookToken;
	}
	
	/**
	 * @return the googleId
	 */
	public String getGoogleId() {
		return googleId;
	}
	
	/**
	 * @param googleId the googleId to set
	 */
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
	
	/**
	 * @return the googleToken
	 */
	public String getGoogleToken() {
		return googleToken;
	}
	
	/**
	 * @param googleToken the googleToken to set
	 */
	public void setGoogleToken(String googleToken) {
		this.googleToken = googleToken;
	}
	
	/**
	 * @return the resgistrationTime
	 */
	public Date getResgistrationTime() {
		return resgistrationTime;
	}
	
	/**
	 * @param resgistrationTime the resgistrationTime to set
	 */
	public void setResgistrationTime(Date resgistrationTime) {
		this.resgistrationTime = resgistrationTime;
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
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * @return the sumPoint
	 */
	public int getSumPoint() {
		return sumPoint;
	}
	
	/**
	 * @param sumPoint the sumPoint to set
	 */
	public void setSumPoint(int sumPoint) {
		this.sumPoint = sumPoint;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the jsessionId
	 */
	public String getJsessionId() {
		return jsessionId;
	}

	/**
	 * @param jsessionId the jsessionId to set
	 */
	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}
}