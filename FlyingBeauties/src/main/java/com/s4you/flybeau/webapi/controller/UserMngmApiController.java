package com.s4you.flybeau.webapi.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.msg.UserR2S;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.webapi.service.ApiTokenSecurirty;
import com.s4you.flybeau.webapi.service.UserMngmApiService;

/**
 * UserMngmController
 * @author ThienMV
 * Date: 12/5/2016
 */
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON  + "; charset=UTF8")
public class UserMngmApiController {

	UserMngmApiService userManageService = new UserMngmApiService();
	
	/**
	 * Get User Information
	 * @param user
	 * @return UserDTO
	 */
	@Path("/{userId}")
	@GET
	public UserDTO getUserInfo(@PathParam("userId") int userId, @Context HttpHeaders headers) {

		return userManageService.getUserInfo(userId);
	}
	
	/**
	 * User Login 
	 * @param inputJsonStr
	 * @return UserR2S
	 */
	@POST
	@Path("/login")
	public UserR2S login(String inputJsonStr) {

		return userManageService.login(inputJsonStr);
	}
	
	/**
	 * User Login 
	 * @param inputJsonStr
	 * @return UserR2S
	 */
	@POST
	@Path("/loginfb")
	public UserR2S loginfb(String inputJsonStr) {

		return userManageService.loginfb(inputJsonStr);
	}
	
	/**
	 * User Register
	 * @param user
	 * @return UserR2S
	 */
	@Path("/register")
	@POST
	public UserR2S register(UserDTO user) {

		return userManageService.register(user);
	}
	
	/**
	 * User Edit Profile
	 * @param user
	 * @return UserR2S
	 */
	@Path("/editprofile")
	@POST
	public UserR2S editProfile(UserDTO user, @Context HttpHeaders headers) {

		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			
			UserR2S userR2S = new UserR2S();
			userR2S.setRetcode(ConstantUtil.RETCODE_ABNORMAL);
			return userR2S;
		}
		return userManageService.editProfile(user);
	}
	
	/**
	 * Reset Password
	 * @param email
	 * @return RetCode
	 */
	@Path("/resetpasswd/{email}")
	@GET
	public int resetPasswd(@PathParam("email") String email) {		

		return userManageService.resetPasswd(email);
	}
	
	/**
	 * Get Three Top User Who updated many image in competition
	 * @param competitionId
	 * @return List<UserDTO>
	 */
	@Path("/gettopusercompetition/{competitionId}")
	@GET
	public List<UserDTO> getTopUser(@PathParam("competitionId") int competitionId) {

		return userManageService.getTopUser(competitionId);
	}
	
	/**
	 * Get Three Top User Who updated many image in Group
	 * @param groupId
	 * @return List<UserDTO>
	 */
	@Path("/gettopusergroup/{groupId}")
	@GET
	public List<UserDTO> getTopUserInGroup(@PathParam("groupId") int groupId) {

		return userManageService.getTopUserInGroup(groupId);
	}
	
	/**
	 * Change Password
	 * @param email
	 * @return RetCode
	 */
	@Path("/changepasswd")
	@POST
	public String changePasswd(String inputJson, @Context HttpHeaders headers) {		

		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			return String.valueOf(ConstantUtil.RETCODE_NOAUTHENCATION);
		}
		return userManageService.changePasswd(inputJson);
	}
	
	/**
	 * Edit Avatar
	 * @param inputJson
	 * @return imageName
	 */
	@Path("/editavatar")
	@POST
	public String editAvatar(String inputJson, @Context HttpHeaders headers) {
		
		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			return String.valueOf(ConstantUtil.RETCODE_NOAUTHENCATION);
		}
		return userManageService.editAvatar(inputJson);
	}
	
	/**
	 * Check user, email is exists
	 * @param inputJson
	 * @return 0 if exists, 1 if not exists 
	 */
	@Path("/checkuseremail")
	@POST
	public int checkUserEmail(String inputJson) {
		
		return userManageService.checkUserEmail(inputJson);
	}
	
	/**
	 * Get List Country
	 * @return list country convert to String json
	 */
	@Path("/listCountry")
	@GET
	public String getListCountry() {
		
		return userManageService.getListCountry();
	}
	
	/**
	 * Get List Country Phone Code
	 * @return list country phone code convert to String json
	 */
	@Path("/listCountryPhoneCode")
	@GET
	public String getListCountryPhoneCode() {
		
		return userManageService.getListCountryPhoneCode();
	}
	
	/**
	 * Save Prefer Language
	 * @param inputJson
	 * @return retcode
	 */
	@Path("/savelang")
	@POST
	public int saveLang(String inputJson, @Context HttpHeaders headers) {
		
		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			return ConstantUtil.RETCODE_NOAUTHENCATION;
		}
		return userManageService.saveLang(inputJson);
	}
}