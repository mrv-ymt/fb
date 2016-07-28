package com.s4you.flybeau.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.utils.JsonBinder;
import com.s4you.flybeau.utils.LoadWebAPIUtil;
import com.s4you.flybeau.utils.SessionDataUtils;

/**
 * 
 * CompetitionMngmController Date: 14/06/2016 ThienMV
 * 
 * */
@Controller
@RequestMapping(value = "competition")
public class CompetitionMngmController {

	/**
	 * showListCompetition
	 * 
	 * @param request
	 * @param model
	 * @return List Competition
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String showListCompetition(
			@RequestParam(value = "notJoinCptt", required = false) String notJoinCptt,
			HttpServletRequest request, HttpServletResponse response, Model model) {

		UserDTO userInfo = (UserDTO) SessionDataUtils
				.getSessionValue(ConstantUtil.SESSION_KEY);
		
		String lang = userInfo == null ? null : userInfo.getPreferredLanguage();
		
		if(lang != null) {
			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
	        localeResolver.setLocale(request, response, StringUtils.parseLocaleString(userInfo.getPreferredLanguage())); 
		}

		/* Call API get list competition */
		int userId = userInfo == null ? -1 : userInfo.getUserId();
		String jsonOutput = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETLISTCPTT
				+ userId);
		model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION,
				JsonBinder.getListFromJson(jsonOutput, CompetitionDTO.class));
		model.addAttribute(ConstantUtil.KEY_NOTJOINTCPTT, notJoinCptt);

		return "competition";
	}

	/**
	 * joinCompetition
	 * 
	 * @param competitionId
	 * @param request
	 * @return Retcode
	 */
	@RequestMapping(value = "/join/{competitionId}/{groupId}", method = RequestMethod.POST)
	@ResponseBody
	public String joinCompetition(@PathVariable Integer competitionId,
			@PathVariable Integer groupId, HttpServletRequest request) {

		UserDTO userInfo = (UserDTO) SessionDataUtils
				.getSessionValue(ConstantUtil.SESSION_KEY);

		/* Call API Join Competition */
		return LoadWebAPIUtil.authenticatedGet(SessionDataUtils.getApiToken(),
				ConstantUtil.URL_JOINCPTT + userInfo.getUserId() + "/"
						+ competitionId + "/" + groupId);
	}
}
