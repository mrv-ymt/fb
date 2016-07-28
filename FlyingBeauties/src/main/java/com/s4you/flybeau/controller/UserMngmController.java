package com.s4you.flybeau.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.msg.UserR2S;
import com.s4you.flybeau.security.CustomAuthenticationProvider;
import com.s4you.flybeau.service.FlyingBeautiesService;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.utils.JsonBinder;
import com.s4you.flybeau.utils.LoadWebAPIUtil;
import com.s4you.flybeau.utils.SessionDataUtils;

@Controller
@RequestMapping(value="user")
public class UserMngmController {
	public static Logger logger = LoggerFactory.getLogger(UserMngmController.class);
	
	
	@Autowired
	FlyingBeautiesService flyingBeautiesService;
	
	/**
	 * Register Controller
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public int register(@RequestBody UserDTO user, HttpServletRequest request) {

		UserR2S userR2S = new UserR2S();
		
		/* Call API Register Handler */
		String outputJson = LoadWebAPIUtil.sendPost(ConstantUtil.URL_REGISTER, JsonBinder.toJson(user));
		
		userR2S = JsonBinder.fromJson(outputJson, UserR2S.class);

		/* Login successful */
		if (ConstantUtil.RETCODE_NORMAL == userR2S.getRetcode()) {
			
			CustomAuthenticationProvider cusAuth = new CustomAuthenticationProvider();
			Authentication requestAuth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			Authentication result = cusAuth.authenticate(requestAuth);
			
			//Set authentication
			SecurityContextHolder.getContext().setAuthentication(result);			
		}

		/* Response JSON Data */		
		return userR2S.getRetcode();
	}
	
	/**
	 * Edit Avatar Controller
	 * 
	 * @return Avatar name
	 */
	@RequestMapping(value = "/editavatar", method = RequestMethod.POST)
	@ResponseBody
	public String editAvatar(@RequestBody UserDTO user, HttpServletRequest request) {

		UserDTO userInfo = SessionDataUtils.getSessionAttribute(request);
		
		user.setAvatarUrl(userInfo.getAvatarUrl());
		String editResult = LoadWebAPIUtil.authenticatedPost(SessionDataUtils.getApiToken(), ConstantUtil.URL_EDITAVATAR, JsonBinder.toJson(user));

		/* Call API Edit Avatar */
		if(editResult != null) {
			
			userInfo.setAvatarUrl(editResult);
			SessionDataUtils.saveSessionApiToken(userInfo);			
		}
		
		return editResult;
	}
	
	/**
	 * Edit Profile Init
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String editProfileInit(HttpServletRequest request, Model model) {
		
		UserDTO userInfo = SessionDataUtils.getSessionAttribute(request);		
		model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION, flyingBeautiesService.getListCompetition(userInfo.getUserId()));
		
		/* Get List Country */
		String listCountryJson = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETLISTCOUNTRY);
		model.addAttribute(ConstantUtil.KEY_LISTCOUNTRY, JsonBinder.getListFromJson(listCountryJson, String.class));
		
		/* Get List Country Phone Code */
		String listCountryPhoneCodeJson = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETLISTCOUNTRYPHONECODE);
		model.addAttribute(ConstantUtil.KEY_LISTCOUNTRYPHONECODE, JsonBinder.getListFromJson(listCountryPhoneCodeJson, String.class));
		
		return "profile";		
	}
	
	/**
	 * Edit Profile
	 * @param userDTO
	 * @param request
	 * @param model
	 * @return Retcode
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	@ResponseBody
	public int editProfileHandle(@RequestBody UserDTO userDTO, HttpServletRequest request, Model model) {
		
		UserR2S userR2S = new UserR2S();
		
		/* Call API Edit Profile Handler Service */
		String outputJson = LoadWebAPIUtil.authenticatedPost(SessionDataUtils.getApiToken(), ConstantUtil.URL_EDITPROFILE, JsonBinder.toJson(userDTO));
		
		userR2S = JsonBinder.fromJson(outputJson, UserR2S.class);

		/* Login successful */
		if (ConstantUtil.RETCODE_NORMAL == userR2S.getRetcode()) {
			request.getSession().setAttribute(ConstantUtil.SESSION_KEY, userR2S.getUserInfo());
		}

		/* Response JSON Data */		
		return userR2S.getRetcode();
	}
	
	/**
	 * Reset Password
	 * @param email
	 * @param request
	 * @return Retcode
	 */
	@RequestMapping(value="/resetpasswd", method=RequestMethod.POST)
	@ResponseBody
	public String resetPassword(@RequestBody String emailReset, HttpServletRequest request) {
		
		return LoadWebAPIUtil.sendGet(ConstantUtil.URL_RESETPASSWORD + emailReset);		
	}
	
	/**
	 * Change Password
	 * @param data
	 * @param request
	 * @return Retcode
	 */
	@RequestMapping(value="/changepasswd", method=RequestMethod.POST)
	@ResponseBody
	public String changePassword(@RequestBody String data, HttpServletRequest request) {
		
		String result = LoadWebAPIUtil.authenticatedPost(SessionDataUtils.getApiToken(), ConstantUtil.URL_CHANGEPASSWORD, data);
		
		if(result != null) {
			UserDTO userInfo = SessionDataUtils.getSessionAttribute(request);
			userInfo.setJsessionId(result);
			SessionDataUtils.saveSessionApiToken(userInfo);
			
			return String.valueOf(ConstantUtil.RETCODE_NORMAL);	
		}
		
		return String.valueOf(ConstantUtil.RETCODE_ABNORMAL);		
	}
	
	/**
	 * View User's Profile
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userprofile/{userId}", method = RequestMethod.GET)
	public String showUserProfole(HttpServletRequest request, Model model, @PathVariable int userId) {
		
		UserDTO userInfo = SessionDataUtils.getSessionAttribute(request);		
		model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION, flyingBeautiesService.getListCompetition(userInfo.getUserId()));
		
		model.addAttribute(ConstantUtil.KEY_USERDTO, 
				JsonBinder.fromJson(LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETUSERINFO + userId), UserDTO.class));
		
		return "profile-view";		
	}
	
	/**
	 * Change Password
	 * @param data
	 * @param request
	 * @return Retcode
	 */
	@RequestMapping(value="/savelang/{lang}", method=RequestMethod.POST)
	@ResponseBody
	public String savePrefferdLang( @PathVariable String lang, HttpServletRequest request) {
		
		UserDTO userInfo = SessionDataUtils.getSessionAttribute(request);	
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put(ConstantUtil.KEY_USERID, String.valueOf(userInfo.getUserId()));
		mapData.put(ConstantUtil.KEY_LANG, lang);
		
		userInfo.setPreferredLanguage(lang);
		SessionDataUtils.saveSessionApiToken(userInfo);
		
		return LoadWebAPIUtil.authenticatedPost
				(SessionDataUtils.getApiToken(), ConstantUtil.URL_SAVELANG, JsonBinder.toJson(mapData));
		
	}
}