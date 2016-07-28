package com.s4you.flybeau.webapi.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;
import org.nutz.json.Json;
import org.springframework.util.FileCopyUtils;

import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.msg.UserR2S;
import com.s4you.flybeau.utils.CodecUtils;
import com.s4you.flybeau.utils.CommonUtils;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.utils.JsonBinder;
import com.s4you.flybeau.utils.SendMailUtil;
import com.s4you.flybeau.webapi.dao.UserMngmApiDAO;

/**
 * UserManageService
 * @author ThienMV
 * Date: 12/5/2016
 */
public class UserMngmApiService {

	UserMngmApiDAO userManageDAO = new UserMngmApiDAO();
	
	private final String LOCAL_PATH_PARENT_FOLDER = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_PARENT_FOLDER);
	private final String LOCAL_PATH_AVATAR = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_AVATAR);
	private final String MAIL_CONTENT = CommonUtils.readProperties(ConstantUtil.PROPERTY_MAIL_CONTENT);
	private final String MAIL_TITLE_RESETPASSWD = CommonUtils.readProperties(ConstantUtil.PROPERTY_MAIL_TITLE_RESETPASSWD);
	
	private final String PATH_IMAGEMAGICK = CommonUtils.readProperties(ConstantUtil.PROPERTIES_PATH_IMAGEMAGICK);
	
	private final int AVATAR_SIZE = CommonUtils.parseInt(CommonUtils.readProperties(ConstantUtil.PROPERTIES_AVARTAR_SIZE));
	
	/**
	 * Get User Information
	 * @param user
	 * @return UserDTO
	 */
	public UserDTO getUserInfo(int userId) {
		
		return userManageDAO.getUserById(userId);
	}
	
	/**
	 * User Login Service
	 * @param UserDTO
	 * @return UserR2S
	 */
	@SuppressWarnings("unchecked")
	public UserR2S login(String inputJsonStr) {	
		
		/* Convert Json to Map */
		Map<String, String> inputMap = Json.fromJson(Map.class, inputJsonStr);
		String username = inputMap.get(ConstantUtil.KEY_USERNAME);
		String password = inputMap.get(ConstantUtil.KEY_PASSWORD);
		
		UserR2S userRetStatus = new UserR2S();
		
		/* Encrypt Password */
		String encryptPasswd = CodecUtils.encrypt(password);
		
		/* Check Login */
		UserDTO userInfo =  userManageDAO.login(username, encryptPasswd);
		
		if(userInfo != null) {
			
			String usernameAndPassword = username + ":" + password;
			
			userInfo.setJsessionId(new String(com.sun.jersey.core.util.Base64.encode(usernameAndPassword)));
			
			/* Set Return code is normal */
			userRetStatus.setRetcode(ConstantUtil.RETCODE_NORMAL);
			
			/* Set User Information Return */
			userRetStatus.setUserInfo(userInfo);
		} else {
			userRetStatus.setRetcode(ConstantUtil.RETCODE_ABNORMAL);
		}
		
		return userRetStatus;
	}
	
	/**
	 * User Register
	 * @param UserDTO
	 * @return UserR2S
	 */
	public UserR2S register(UserDTO user) {
		
		UserR2S userRetStatus = new UserR2S();
		
		if(userManageDAO.getUser(user.getUsername(), user.getEmail()) == null) {
			
			/* Encrypt Password to MD5 */
			String encryptPasswd = CodecUtils.encrypt(user.getPassword());
			user.setPassword(encryptPasswd);
			
			/* Set Role For User */
			user.setRoleId(ConstantUtil.ROLE_USER);
			
			/* Set Registration Time By Current Time */
			user.setResgistrationTime(new Date());
			
			/* Set Avatar Is None Avatar */
			user.setAvatarUrl(ConstantUtil.PATH_NONE_AVATAR_IMG);
			
			/* Insert Into DB */
			if(userManageDAO.register(user)) {
				userRetStatus.setRetcode(ConstantUtil.RETCODE_NORMAL);
				userRetStatus.setUserInfo(userManageDAO.getUser(user.getUsername(), user.getEmail()));	
				
			} else {
				userRetStatus.setRetcode(ConstantUtil.RETCODE_ABNORMAL);
			}
			
			return userRetStatus;
		} 
		
		userRetStatus.setRetcode(ConstantUtil.RETCODE_ABNORMAL);
		return userRetStatus;
	}
	
	/**
	 * User Edit Profile
	 * @param User
	 * @return UserR2S
	 */
	public UserR2S editProfile(UserDTO userInfoNew) {
		
		UserR2S userRetStatus = new UserR2S();
		
		UserDTO userInfoOld = userManageDAO.getUserById(userInfoNew.getUserId());
		userInfoNew.setAvatarUrl(userInfoOld.getAvatarUrl());
		
		/* Checking Modify Email */
		if(userInfoOld.getEmail().equals(userInfoNew.getEmail())) {
			
			/* Edit Profile */
			if(userManageDAO.editProfile(userInfoNew)) {
				
				/* If Edit Successfully */
				userRetStatus.setRetcode(ConstantUtil.RETCODE_NORMAL);		
				userRetStatus.setUserInfo(userInfoNew);	
				return userRetStatus;
			}	
		} else {
			if(userManageDAO.getUser(userInfoNew.getUsername(), userInfoNew.getEmail()) == null) {
				
				/* Edit Profile */
				if(userManageDAO.editProfile(userInfoNew)) {
					
					/* If Edit Successfully */
					userRetStatus.setRetcode(ConstantUtil.RETCODE_NORMAL);		
					userRetStatus.setUserInfo(userInfoNew);	
					return userRetStatus;
				}	
				
			}
		}
		
		userRetStatus.setRetcode(ConstantUtil.RETCODE_ABNORMAL);
		return userRetStatus;
	}

	/**
	 * Reset Password
	 * @param email
	 * @return RetCode
	 */
	public int resetPasswd(String email) {				
		
		UserDTO userInfo = userManageDAO.getUser(ConstantUtil.INIT_STRING, email);
		
		if(userInfo != null) {
			
			/* Generation a random password */
			SecureRandom random = new SecureRandom();
			String newPassword = new BigInteger(130, random).toString(32).substring(0, 10);
			
			/* Send new password to email */
			int sendMailRetCode = SendMailUtil.sendMail(email, MAIL_TITLE_RESETPASSWD, MAIL_CONTENT);
			
			if(sendMailRetCode == ConstantUtil.RETCODE_NORMAL) {
				
				/* Convert new password to MD5 and update into Database */
				userManageDAO.updatePassword(userInfo.getUserId(), CodecUtils.encrypt(newPassword));
			}

			return sendMailRetCode;
		}
		
		return ConstantUtil.RETCODE_ABNORMAL;
	}

	/**
	 * Get Three Top User Who updated many image
	 * @param competitionId
	 * @return List Top User
	 */
	public List<UserDTO> getTopUser(int competitionId) {
		
		/* Get Top User */
		return userManageDAO.getTopUser(competitionId);
	}

	@SuppressWarnings("unchecked")
	public UserR2S loginfb(String inputJsonStr) {
		
		/* Convert Json to Map */
		Map<String, String> inputMap = Json.fromJson(Map.class, inputJsonStr);
		
		UserR2S userRetStatus = new UserR2S();
		/* Check Login */
		UserDTO userInfo =  userManageDAO.login(inputMap.get("qqId"), inputMap.get("qqToken"));
		
		if(userInfo != null) {
			
			/* Set Return code is normal */
			userRetStatus.setRetcode(ConstantUtil.RETCODE_NORMAL);
			
			/* Set User Information Return */
			userRetStatus.setUserInfo(userInfo);
		} else {
			userRetStatus.setRetcode(ConstantUtil.RETCODE_ABNORMAL);
		}
		
		return userRetStatus;
	}

	/**
	 * Change Password
	 * @param inputJsonStr
	 * @return Retcode
	 */
	@SuppressWarnings("unchecked")
	public String changePasswd(String inputJsonStr) {
		
		/* Convert Json to Map */
		Map<String, String> inputMap = Json.fromJson(Map.class, inputJsonStr);	
		
		int userId =  CommonUtils.parseInt(inputMap.get(ConstantUtil.KEY_USERID));
		String newPassword = inputMap.get(ConstantUtil.KEY_NEWPASSWORD);
		
		/* Encrypt New Password */
		String encryptOldPasswd = CodecUtils.encrypt(inputMap.get(ConstantUtil.KEY_OLDPASSWORD));
		
		if(!userManageDAO.getCheckOldPass(userId, encryptOldPasswd)) {
			return null;
		}
		
		/* Encrypt New Password */
		String encryptNewPasswd = CodecUtils.encrypt(newPassword);
		
		/* Convert new password to MD5 and update into Database */
		if(userManageDAO.updatePassword(userId, encryptNewPasswd)) {	
			
			/* Check Login */
			UserDTO userInfo =  userManageDAO.getUserById(userId);
			
			String usernameAndPassword = userInfo.getUsername() + ":" + newPassword;
			
			return new String(com.sun.jersey.core.util.Base64.encode(usernameAndPassword));
			
		} else {
			return null;
		}		
	}

	/**
	 * Get Three Top User Who updated many image in Group
	 * @param groupId
	 * @return List<UserDTO>
	 */
	public List<UserDTO> getTopUserInGroup(int groupId) {
		
		/* Get Top User */
		return userManageDAO.getTopUserInGroup(groupId);
	}

	/**
	 * Edit Avatar
	 * @param fileToBase64Str
	 * @return imageName
	 */
	public String editAvatar(String inputJson) {		
	
		/* Get Avatar Info From Json*/
		UserDTO userDTO = JsonBinder.fromJson(inputJson, UserDTO.class);
		
		/* Decode File From Base64 Encoding To File Image */
		Base64 decoder = new Base64(); 
		byte[] imgBytes = decoder.decode(userDTO.getFileToBase64());
		
		/* Set Random Name For Image */
		long currentTime = new Date().getTime();
		SecureRandom random = new SecureRandom();
		String randomString = new BigInteger(130, random).toString(32).substring(0, 10);
		String avatarName = currentTime + randomString + ConstantUtil.SUFFIX_IMAGE;
		
		try {
		
			/* Create Folder To Save Image */
			File parentFolder = new File(LOCAL_PATH_PARENT_FOLDER);
	        if (!parentFolder.exists()) {	        	
	            parentFolder.mkdir();
	        }
	        
	        File folder = new File(LOCAL_PATH_AVATAR);
	        if (!folder.exists()) {
	            folder.mkdir(); 
	        } 
	        
	        /* Save File To Disk */
			FileCopyUtils.copy(imgBytes, new File(LOCAL_PATH_AVATAR + ConstantUtil.ZERO_STRING + avatarName));
			
			/* Process Starter with Path To lib IMAGEMAGICK */
			ProcessStarter.setGlobalSearchPath(PATH_IMAGEMAGICK);
			
			/* Create command */
			ConvertCmd cmd = new ConvertCmd();

			/* Create the operation, add images and operators/options */
			IMOperation option = new IMOperation();
			option.addImage(LOCAL_PATH_AVATAR + ConstantUtil.ZERO_STRING + avatarName);
			option.thumbnail(AVATAR_SIZE, AVATAR_SIZE);
			option.quality(100d);
			option.unsharp(100d);
			option.addImage(LOCAL_PATH_AVATAR + avatarName);

			// Execute the operation
			cmd.run(option);
			
			/* Delete File Avatar Save */
			File fileWrote = new File(LOCAL_PATH_AVATAR + ConstantUtil.ZERO_STRING + avatarName);
			
			if(fileWrote != null) {
				fileWrote.delete();
			}
			
			/* Delete Old Avatar */
			File fileOld = new File(LOCAL_PATH_AVATAR + userDTO.getAvatarUrl());
			
			if(fileOld != null) {
				fileOld.delete();
			}
			
			/* Set File Name To Insert Database */
			userDTO.setAvatarUrl(avatarName);
			userManageDAO.editAvatar(userDTO);
			
		} catch (IOException e) {	
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (IM4JavaException e) {
			
			e.printStackTrace();
		} 
	
		return avatarName;
	}

	/**
	 * 
	 * @param inputJson
	 * @return 0 if exists, 1 if not exists 
	 */
	@SuppressWarnings("unchecked")
	public int checkUserEmail(String inputJson) {
		
		/* Convert Json to Map */
		Map<String, String> inputMap = Json.fromJson(Map.class, inputJson);
		
		String username = inputMap.get(ConstantUtil.KEY_USERNAME);
		String email = inputMap.get(ConstantUtil.KEY_EMAIL);
		
		/* Check email or username is exists */		
		if(userManageDAO.getUser(username, email) != null) {
			
			return ConstantUtil.RETCODE_ABNORMAL;
		} else {
			return ConstantUtil.RETCODE_NORMAL;
		}
	}	
	
	
	/**
	 * Check Api Authencation
	 * @param username
	 * @param password
	 * @return RetCode
	 */
	public int checkApiAuthencation(String username, String password) {	
		
		/* Check Login */
		UserDTO userInfo =  userManageDAO.login(username, CodecUtils.encrypt(password));
		
		if(userInfo != null) {
			
			/* Set Return code is normal */
			return ConstantUtil.RETCODE_NORMAL;
		}
			
		return ConstantUtil.RETCODE_ABNORMAL;
	}

	/**
	 * Get List Country
	 * @return list country convert to String json
	 */
	public String getListCountry() {
		
		List<String> listCountry = new ArrayList<String>();
		
		listCountry.add("Afghanistan");
		listCountry.add("Albania");
		listCountry.add("Algeria");
		listCountry.add("American Samoa");
		listCountry.add("Andorra");
		listCountry.add("Angola");
		listCountry.add("Anguilla");
		listCountry.add("Antarctica");
		listCountry.add("Antigua and Barbuda");
		listCountry.add("Argentina");
		listCountry.add("Armenia");
		listCountry.add("Aruba");
		listCountry.add("Australia");
		listCountry.add("Austria");
		listCountry.add("Azerbaijan");
		listCountry.add("Bahamas");
		listCountry.add("Bahrain");
		listCountry.add("Bangladesh");
		listCountry.add("Barbados");
		listCountry.add("Belarus");
		listCountry.add("Belgium");
		listCountry.add("Belize");
		listCountry.add("Benin");
		listCountry.add("Bermuda");
		listCountry.add("Bhutan");
		listCountry.add("Bolivia");
		listCountry.add("Bosnia and Herzegovina");
		listCountry.add("Botswana");
		listCountry.add("Brazil");
		listCountry.add("British Indian Ocean Territory");
		listCountry.add("British Virgin Islands");
		listCountry.add("Brunei");
		listCountry.add("Bulgaria");
		listCountry.add("Burkina Faso");
		listCountry.add("Burundi");
		listCountry.add("Cambodia");
		listCountry.add("Cameroon");
		listCountry.add("Canada");
		listCountry.add("Cape Verde");
		listCountry.add("Cayman Islands");
		listCountry.add("Central African Republic");
		listCountry.add("Chad");
		listCountry.add("Chile");
		listCountry.add("China");
		listCountry.add("Christmas Island");
		listCountry.add("Cocos Islands");
		listCountry.add("Colombia");
		listCountry.add("Comoros");
		listCountry.add("Cook Islands");
		listCountry.add("Costa Rica");
		listCountry.add("Croatia");
		listCountry.add("Cuba");
		listCountry.add("Curacao");
		listCountry.add("Cyprus");
		listCountry.add("Czech Republic");
		listCountry.add("Democratic Republic of the Congo");
		listCountry.add("Denmark");
		listCountry.add("Djibouti");
		listCountry.add("Dominica");
		listCountry.add("Dominican Republic");
		listCountry.add("East Timor");
		listCountry.add("Ecuador");
		listCountry.add("Egypt");
		listCountry.add("El Salvador");
		listCountry.add("Equatorial Guinea");
		listCountry.add("Eritrea");
		listCountry.add("Estonia");
		listCountry.add("Ethiopia");
		listCountry.add("Falkland Islands");
		listCountry.add("Faroe Islands");
		listCountry.add("Fiji");
		listCountry.add("Finland");
		listCountry.add("France");
		listCountry.add("French Polynesia");
		listCountry.add("Gabon");
		listCountry.add("Gambia");
		listCountry.add("Georgia");
		listCountry.add("Germany");
		listCountry.add("Ghana");
		listCountry.add("Gibraltar");
		listCountry.add("Greece");
		listCountry.add("Greenland");
		listCountry.add("Grenada");
		listCountry.add("Guam");
		listCountry.add("Guatemala");
		listCountry.add("Guernsey");
		listCountry.add("Guinea");
		listCountry.add("Guinea-Bissau");
		listCountry.add("Guyana");
		listCountry.add("Haiti");
		listCountry.add("Honduras");
		listCountry.add("Hong Kong");
		listCountry.add("Hungary");
		listCountry.add("Iceland");
		listCountry.add("India");
		listCountry.add("Indonesia");
		listCountry.add("Iran");
		listCountry.add("Iraq");
		listCountry.add("Ireland");
		listCountry.add("Isle of Man");
		listCountry.add("Israel");
		listCountry.add("Italy");
		listCountry.add("Ivory Coast");
		listCountry.add("Jamaica");
		listCountry.add("Japan");
		listCountry.add("Jersey");
		listCountry.add("Jordan");
		listCountry.add("Kazakhstan");
		listCountry.add("Kenya");
		listCountry.add("Kiribati");
		listCountry.add("Kosovo");
		listCountry.add("Kuwait");
		listCountry.add("Kyrgyzstan");
		listCountry.add("Laos");
		listCountry.add("Latvia");
		listCountry.add("Lebanon");
		listCountry.add("Lesotho");
		listCountry.add("Liberia");
		listCountry.add("Libya");
		listCountry.add("Liechtenstein");
		listCountry.add("Lithuania");
		listCountry.add("Luxembourg");
		listCountry.add("Macau");
		listCountry.add("Macedonia");
		listCountry.add("Madagascar");
		listCountry.add("Malawi");
		listCountry.add("Malaysia");
		listCountry.add("Maldives");
		listCountry.add("Mali");
		listCountry.add("Malta");
		listCountry.add("Marshall Islands");
		listCountry.add("Mauritania");
		listCountry.add("Mauritius");
		listCountry.add("Mayotte");
		listCountry.add("Mexico");
		listCountry.add("Micronesia");
		listCountry.add("Moldova");
		listCountry.add("Monaco");
		listCountry.add("Mongolia");
		listCountry.add("Montenegro");
		listCountry.add("Montserrat");
		listCountry.add("Morocco");
		listCountry.add("Mozambique");
		listCountry.add("Myanmar");
		listCountry.add("Namibia");
		listCountry.add("Nauru");
		listCountry.add("Nepal");
		listCountry.add("Netherlands");
		listCountry.add("Netherlands Antilles");
		listCountry.add("New Caledonia");
		listCountry.add("New Zealand");
		listCountry.add("Nicaragua");
		listCountry.add("Niger");
		listCountry.add("Nigeria");
		listCountry.add("Niue");
		listCountry.add("North Korea");
		listCountry.add("Northern Mariana Islands");
		listCountry.add("Norway");
		listCountry.add("Oman");
		listCountry.add("Pakistan");
		listCountry.add("Palau");
		listCountry.add("Palestine");
		listCountry.add("Panama");
		listCountry.add("Papua New Guinea");
		listCountry.add("Paraguay");
		listCountry.add("Peru");
		listCountry.add("Philippines");
		listCountry.add("Pitcairn");
		listCountry.add("Poland");
		listCountry.add("Portugal");
		listCountry.add("Puerto Rico");
		listCountry.add("Qatar");
		listCountry.add("Republic of the Congo");
		listCountry.add("Reunion");
		listCountry.add("Romania");
		listCountry.add("Russia");
		listCountry.add("Rwanda");
		listCountry.add("Saint Barthelemy");
		listCountry.add("Saint Helena");
		listCountry.add("Saint Kitts and Nevis");
		listCountry.add("Saint Lucia");
		listCountry.add("Saint Martin");
		listCountry.add("Saint Pierre and Miquelon");
		listCountry.add("Saint Vincent and the Grenadines");
		listCountry.add("Samoa");
		listCountry.add("San Marino");
		listCountry.add("Sao Tome and Principe");
		listCountry.add("Saudi Arabia");
		listCountry.add("Senegal");
		listCountry.add("Serbia");
		listCountry.add("Seychelles");
		listCountry.add("Sierra Leone");
		listCountry.add("Singapore");
		listCountry.add("Sint Maarten");
		listCountry.add("Slovakia");
		listCountry.add("Slovenia");
		listCountry.add("Solomon Islands");
		listCountry.add("Somalia");
		listCountry.add("South Africa");
		listCountry.add("South Korea");
		listCountry.add("South Sudan");
		listCountry.add("Spain");
		listCountry.add("Sri Lanka");
		listCountry.add("Sudan");
		listCountry.add("Suriname");
		listCountry.add("Svalbard and Jan Mayen");
		listCountry.add("Swaziland");
		listCountry.add("Sweden");
		listCountry.add("Switzerland");
		listCountry.add("Syria");
		listCountry.add("Taiwan");
		listCountry.add("Tajikistan");
		listCountry.add("Tanzania");
		listCountry.add("Thailand");
		listCountry.add("Togo");
		listCountry.add("Tokelau");
		listCountry.add("Tonga");
		listCountry.add("Trinidad and Tobago");
		listCountry.add("Tunisia");
		listCountry.add("Turkey");
		listCountry.add("Turkmenistan");
		listCountry.add("Turks and Caicos Islands");
		listCountry.add("Tuvalu");
		listCountry.add("U.S. Virgin Islands");
		listCountry.add("Uganda");
		listCountry.add("Ukraine");
		listCountry.add("United Arab Emirates");
		listCountry.add("United Kingdom");
		listCountry.add("United States");
		listCountry.add("Uruguay");
		listCountry.add("Uzbekistan");
		listCountry.add("Vanuatu");
		listCountry.add("Vatican");
		listCountry.add("Venezuela");
		listCountry.add("Việt Nam");
		listCountry.add("Wallis and Futuna");
		listCountry.add("Western Sahara");
		listCountry.add("Yemen");
		listCountry.add("Zambia");
		listCountry.add("Zimbabwe");

		return JsonBinder.toJson(listCountry);
	}
	
	/**
	 * Get List Country
	 * @return list country convert to String json
	 */
	public String getListCountryPhoneCode() {
		
		List<String> listCountryPhoneCode = new ArrayList<String>();
		
		listCountryPhoneCode.add("Afghanistan (+93)");
		listCountryPhoneCode.add("Albania (+355)");
		listCountryPhoneCode.add("Algeria (+213)");
		listCountryPhoneCode.add("American Samoa (+1-684)");
		listCountryPhoneCode.add("Andorra (+376)");
		listCountryPhoneCode.add("Angola (+244)");
		listCountryPhoneCode.add("Anguilla (+1-264)");
		listCountryPhoneCode.add("Antarctica (+672)");
		listCountryPhoneCode.add("Antigua and Barbuda (+1-268)");
		listCountryPhoneCode.add("Argentina (+54)");
		listCountryPhoneCode.add("Armenia (+374)");
		listCountryPhoneCode.add("Aruba (+297)");
		listCountryPhoneCode.add("Australia (+61)");
		listCountryPhoneCode.add("Austria (+43)");
		listCountryPhoneCode.add("Azerbaijan (+994)");
		listCountryPhoneCode.add("Bahamas (+1-242)");
		listCountryPhoneCode.add("Bahrain (+973)");
		listCountryPhoneCode.add("Bangladesh (+880)");
		listCountryPhoneCode.add("Barbados (+1-246)");
		listCountryPhoneCode.add("Belarus (+375)");
		listCountryPhoneCode.add("Belgium (+32)");
		listCountryPhoneCode.add("Belize (+501)");
		listCountryPhoneCode.add("Benin (+229)");
		listCountryPhoneCode.add("Bermuda (+1-441)");
		listCountryPhoneCode.add("Bhutan (+975)");
		listCountryPhoneCode.add("Bolivia (+591)");
		listCountryPhoneCode.add("Bosnia and Herzegovina (+387)");
		listCountryPhoneCode.add("Botswana (+267)");
		listCountryPhoneCode.add("Brazil (+55)");
		listCountryPhoneCode.add("British Indian Ocean Territory (+246)");
		listCountryPhoneCode.add("British Virgin Islands (+1-284)");
		listCountryPhoneCode.add("Brunei (+673)");
		listCountryPhoneCode.add("Bulgaria (+359)");
		listCountryPhoneCode.add("Burkina Faso (+226)");
		listCountryPhoneCode.add("Burundi (+257)");
		listCountryPhoneCode.add("Cambodia (+855)");
		listCountryPhoneCode.add("Cameroon (+237)");
		listCountryPhoneCode.add("Canada (+1)");
		listCountryPhoneCode.add("Cape Verde (+238)");
		listCountryPhoneCode.add("Cayman Islands (+1-345)");
		listCountryPhoneCode.add("Central African Republic (+236)");
		listCountryPhoneCode.add("Chad (+235)");
		listCountryPhoneCode.add("Chile (+56)");
		listCountryPhoneCode.add("China (+86)");
		listCountryPhoneCode.add("Christmas Island (+61)");
		listCountryPhoneCode.add("Cocos Islands (+61)");
		listCountryPhoneCode.add("Colombia (+57)");
		listCountryPhoneCode.add("Comoros (+269)");
		listCountryPhoneCode.add("Cook Islands (+682)");
		listCountryPhoneCode.add("Costa Rica (+506)");
		listCountryPhoneCode.add("Croatia (+385)");
		listCountryPhoneCode.add("Cuba (+53)");
		listCountryPhoneCode.add("Curacao (+599)");
		listCountryPhoneCode.add("Cyprus (+357)");
		listCountryPhoneCode.add("Czech Republic (+420)");
		listCountryPhoneCode.add("Democratic Republic of the Congo (+243)");
		listCountryPhoneCode.add("Denmark (+45)");
		listCountryPhoneCode.add("Djibouti (+253)");
		listCountryPhoneCode.add("Dominica (+1-767)");
		listCountryPhoneCode.add("Dominican Republic (+1-809)");
		listCountryPhoneCode.add("Dominican Republic (+1-8299)");
		listCountryPhoneCode.add("Dominican Republic (+1-849)");
		listCountryPhoneCode.add("East Timor (+670)");
		listCountryPhoneCode.add("Ecuador (+593)");
		listCountryPhoneCode.add("Egypt (+20)");
		listCountryPhoneCode.add("El Salvador (+503)");
		listCountryPhoneCode.add("Equatorial Guinea (+240)");
		listCountryPhoneCode.add("Eritrea (+291)");
		listCountryPhoneCode.add("Estonia (+372)");
		listCountryPhoneCode.add("Ethiopia (+251)");
		listCountryPhoneCode.add("Falkland Islands (+500)");
		listCountryPhoneCode.add("Faroe Islands (+298)");
		listCountryPhoneCode.add("Fiji (+679)");
		listCountryPhoneCode.add("Finland (+358)");
		listCountryPhoneCode.add("France (+33)");
		listCountryPhoneCode.add("French Polynesia (+689)");
		listCountryPhoneCode.add("Gabon (+241)");
		listCountryPhoneCode.add("Gambia (+220)");
		listCountryPhoneCode.add("Georgia (+995)");
		listCountryPhoneCode.add("Germany (+49)");
		listCountryPhoneCode.add("Ghana (+233)");
		listCountryPhoneCode.add("Gibraltar (+350)");
		listCountryPhoneCode.add("Greece (+30)");
		listCountryPhoneCode.add("Greenland (+299)");
		listCountryPhoneCode.add("Grenada (+1-473)");
		listCountryPhoneCode.add("Guam (+1-671)");
		listCountryPhoneCode.add("Guatemala (+502)");
		listCountryPhoneCode.add("Guernsey (+44-1481)");
		listCountryPhoneCode.add("Guinea (+224)");
		listCountryPhoneCode.add("Guinea-Bissau (+245)");
		listCountryPhoneCode.add("Guyana (+592)");
		listCountryPhoneCode.add("Haiti (+509)");
		listCountryPhoneCode.add("Honduras (+504)");
		listCountryPhoneCode.add("Hong Kong (+852)");
		listCountryPhoneCode.add("Hungary (+36)");
		listCountryPhoneCode.add("Iceland (+354)");
		listCountryPhoneCode.add("India (+91)");
		listCountryPhoneCode.add("Indonesia (+62)");
		listCountryPhoneCode.add("Iran (+98)");
		listCountryPhoneCode.add("Iraq (+964)");
		listCountryPhoneCode.add("Ireland (+353)");
		listCountryPhoneCode.add("Isle of Man (+44-1624)");
		listCountryPhoneCode.add("Israel (+972)");
		listCountryPhoneCode.add("Italy (+39)");
		listCountryPhoneCode.add("Ivory Coast (+225)");
		listCountryPhoneCode.add("Jamaica (+1-876)");
		listCountryPhoneCode.add("Japan (+81)");
		listCountryPhoneCode.add("Jersey (+44-1534)");
		listCountryPhoneCode.add("Jordan (+962)");
		listCountryPhoneCode.add("Kazakhstan (+7)");
		listCountryPhoneCode.add("Kenya (+254)");
		listCountryPhoneCode.add("Kiribati (+686)");
		listCountryPhoneCode.add("Kosovo (+383)");
		listCountryPhoneCode.add("Kuwait (+965)");
		listCountryPhoneCode.add("Kyrgyzstan (+996)");
		listCountryPhoneCode.add("Laos (+856)");
		listCountryPhoneCode.add("Latvia (+371)");
		listCountryPhoneCode.add("Lebanon (+961)");
		listCountryPhoneCode.add("Lesotho (+266)");
		listCountryPhoneCode.add("Liberia (+231)");
		listCountryPhoneCode.add("Libya (+218)");
		listCountryPhoneCode.add("Liechtenstein (+423)");
		listCountryPhoneCode.add("Lithuania (+370)");
		listCountryPhoneCode.add("Luxembourg (+352)");
		listCountryPhoneCode.add("Macau (+853)");
		listCountryPhoneCode.add("Macedonia (+389)");
		listCountryPhoneCode.add("Madagascar (+261)");
		listCountryPhoneCode.add("Malawi (+265)");
		listCountryPhoneCode.add("Malaysia (+60)");
		listCountryPhoneCode.add("Maldives (+960)");
		listCountryPhoneCode.add("Mali (+223)");
		listCountryPhoneCode.add("Malta (+356)");
		listCountryPhoneCode.add("Marshall Islands (+692)");
		listCountryPhoneCode.add("Mauritania (+222)");
		listCountryPhoneCode.add("Mauritius (+230)");
		listCountryPhoneCode.add("Mayotte (+262)");
		listCountryPhoneCode.add("Mexico (+52)");
		listCountryPhoneCode.add("Micronesia (+691)");
		listCountryPhoneCode.add("Moldova (+373)");
		listCountryPhoneCode.add("Monaco (+377)");
		listCountryPhoneCode.add("Mongolia (+976)");
		listCountryPhoneCode.add("Montenegro (+382)");
		listCountryPhoneCode.add("Montserrat (+1-664)");
		listCountryPhoneCode.add("Morocco (+212)");
		listCountryPhoneCode.add("Mozambique (+258)");
		listCountryPhoneCode.add("Myanmar (+95)");
		listCountryPhoneCode.add("Namibia (+264)");
		listCountryPhoneCode.add("Nauru (+674)");
		listCountryPhoneCode.add("Nepal (+977)");
		listCountryPhoneCode.add("Netherlands (+31)");
		listCountryPhoneCode.add("Netherlands Antilles (+599)");
		listCountryPhoneCode.add("New Caledonia (+687)");
		listCountryPhoneCode.add("New Zealand (+64)");
		listCountryPhoneCode.add("Nicaragua (+505)");
		listCountryPhoneCode.add("Niger (+227)");
		listCountryPhoneCode.add("Nigeria (+234)");
		listCountryPhoneCode.add("Niue (+683)");
		listCountryPhoneCode.add("North Korea (+850)");
		listCountryPhoneCode.add("Northern Mariana Islands (+1-670)");
		listCountryPhoneCode.add("Norway (+47)");
		listCountryPhoneCode.add("Oman (+968)");
		listCountryPhoneCode.add("Pakistan (+92)");
		listCountryPhoneCode.add("Palau (+680)");
		listCountryPhoneCode.add("Palestine (+970)");
		listCountryPhoneCode.add("Panama (+507)");
		listCountryPhoneCode.add("Papua New Guinea (+675)");
		listCountryPhoneCode.add("Paraguay (+595)");
		listCountryPhoneCode.add("Peru (+51)");
		listCountryPhoneCode.add("Philippines (+63)");
		listCountryPhoneCode.add("Pitcairn (+64)");
		listCountryPhoneCode.add("Poland (+48)");
		listCountryPhoneCode.add("Portugal (+351)");
		listCountryPhoneCode.add("Puerto Rico (+1-787)");
		listCountryPhoneCode.add("Puerto Rico (+1-939)");
		listCountryPhoneCode.add("Qatar (+974)");
		listCountryPhoneCode.add("Republic of the Congo (+242)");
		listCountryPhoneCode.add("Reunion (+262)");
		listCountryPhoneCode.add("Romania (+40)");
		listCountryPhoneCode.add("Russia (+7)");
		listCountryPhoneCode.add("Rwanda (+250)");
		listCountryPhoneCode.add("Saint Barthelemy (+590)");
		listCountryPhoneCode.add("Saint Helena (+290)");
		listCountryPhoneCode.add("Saint Kitts and Nevis (+1-869)");
		listCountryPhoneCode.add("Saint Lucia (+1-758)");
		listCountryPhoneCode.add("Saint Martin (+590)");
		listCountryPhoneCode.add("Saint Pierre and Miquelon (+508)");
		listCountryPhoneCode.add("Saint Vincent and the Grenadines (+1-784)");
		listCountryPhoneCode.add("Samoa (+685)");
		listCountryPhoneCode.add("San Marino (+378)");
		listCountryPhoneCode.add("Sao Tome and Principe (+239)");
		listCountryPhoneCode.add("Saudi Arabia (+966)");
		listCountryPhoneCode.add("Senegal (+221)");
		listCountryPhoneCode.add("Serbia (+381)");
		listCountryPhoneCode.add("Seychelles (+248)");
		listCountryPhoneCode.add("Sierra Leone (+232)");
		listCountryPhoneCode.add("Singapore (+65)");
		listCountryPhoneCode.add("Sint Maarten (+1-721)");
		listCountryPhoneCode.add("Slovakia (+421)");
		listCountryPhoneCode.add("Slovenia (+386)");
		listCountryPhoneCode.add("Solomon Islands (+677)");
		listCountryPhoneCode.add("Somalia (+252)");
		listCountryPhoneCode.add("South Africa (+27)");
		listCountryPhoneCode.add("South Korea (+82)");
		listCountryPhoneCode.add("South Sudan (+211)");
		listCountryPhoneCode.add("Spain (+34)");
		listCountryPhoneCode.add("Sri Lanka (+94)");
		listCountryPhoneCode.add("Sudan (+249)");
		listCountryPhoneCode.add("Suriname (+597)");
		listCountryPhoneCode.add("Svalbard and Jan Mayen (+47)");
		listCountryPhoneCode.add("Swaziland (+268)");
		listCountryPhoneCode.add("Sweden (+46)");
		listCountryPhoneCode.add("Switzerland (+41)");
		listCountryPhoneCode.add("Syria (+963)");
		listCountryPhoneCode.add("Taiwan (+886)");
		listCountryPhoneCode.add("Tajikistan (+992)");
		listCountryPhoneCode.add("Tanzania (+255)");
		listCountryPhoneCode.add("Thailand (+66)");
		listCountryPhoneCode.add("Togo (+228)");
		listCountryPhoneCode.add("Tokelau (+690)");
		listCountryPhoneCode.add("Tonga (+676)");
		listCountryPhoneCode.add("Trinidad and Tobago (+1-868)");
		listCountryPhoneCode.add("Tunisia (+216)");
		listCountryPhoneCode.add("Turkey (+90)");
		listCountryPhoneCode.add("Turkmenistan (+993)");
		listCountryPhoneCode.add("Turks and Caicos Islands (+1-649)");
		listCountryPhoneCode.add("Tuvalu (+688)");
		listCountryPhoneCode.add("U.S. Virgin Islands (+1-340)");
		listCountryPhoneCode.add("Uganda (+256)");
		listCountryPhoneCode.add("Ukraine (+380)");
		listCountryPhoneCode.add("United Arab Emirates (+971)");
		listCountryPhoneCode.add("United Kingdom (+44)");
		listCountryPhoneCode.add("United States (+1)");
		listCountryPhoneCode.add("Uruguay (+598)");
		listCountryPhoneCode.add("Uzbekistan (+998)");
		listCountryPhoneCode.add("Vanuatu (+678)");
		listCountryPhoneCode.add("Vatican (+379)");
		listCountryPhoneCode.add("Venezuela (+58)");
		listCountryPhoneCode.add("Việt Nam (+84)");
		listCountryPhoneCode.add("Wallis and Futuna (+681)");
		listCountryPhoneCode.add("Western Sahara (+212)");
		listCountryPhoneCode.add("Yemen (+967)");
		listCountryPhoneCode.add("Zambia (+260)");
		listCountryPhoneCode.add("Zimbabwe (+263)");

		return JsonBinder.toJson(listCountryPhoneCode);
	}

	/**
	 * Save Prefer Language
	 * @param inputJson
	 * @return retcode
	 */
	@SuppressWarnings("unchecked")
	public int saveLang(String inputJson) {
		
		Map<String, String> inputMap = Json.fromJson(Map.class, inputJson);
		String userId = inputMap.get(ConstantUtil.KEY_USERID);
		String lang = inputMap.get(ConstantUtil.KEY_LANG);
		
		if(userManageDAO.saveLang(CommonUtils.parseInt(userId), lang)) {
			return ConstantUtil.RETCODE_NORMAL;
		} 
		
		return ConstantUtil.RETCODE_ABNORMAL;
	}
}