package com.s4you.flybeau.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.GroupDTO;
import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.service.AdministratorService;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.utils.JsonBinder;
import com.s4you.flybeau.utils.LoadWebAPIUtil;

@Controller
@RequestMapping(value="admin")
public class AdministratorController {

	@Autowired
	AdministratorService administratorService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String showAdminHome(Model model) {
		
		return "redirect:/admin/listuser/1";
	}
	
	@RequestMapping(value="/listuser/{pageNum}", method = RequestMethod.GET)
	public String showAdminHomeWithPager(@PathVariable int pageNum, Model model) {
		
		model.addAttribute("listUserR2S", administratorService.getListUser(pageNum));
		return "admin/admin-home";
	}
	
	@RequestMapping(value="/deactiveuser/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public int deactiveUser(@PathVariable int userId, Model model) {
		
		if(administratorService.deactiveToogleUserRole(userId, ConstantUtil.DEACTIVATE_USER)) {
			return ConstantUtil.RETCODE_NORMAL;
		} 
		
		return ConstantUtil.RETCODE_ABNORMAL;
	}
	
	@RequestMapping(value="/activeuser/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public int activeUser(@PathVariable int userId, Model model) {
		
		if(administratorService.deactiveToogleUserRole(userId, ConstantUtil.ACTIVATE_USER)) {
			return ConstantUtil.RETCODE_NORMAL;
		} 
		
		return ConstantUtil.RETCODE_ABNORMAL;
	}
			
	@RequestMapping(value="group", method = RequestMethod.GET)
	public String initUserGroup(Model model) {
		model.addAttribute(ConstantUtil.KEY_LISTGROUP, administratorService.getListGroup());
		return "admin/admin-group";
	}
	
	
	@RequestMapping(value="competition", method = RequestMethod.GET)
	public String initCompetitionMngm(Model model) {
		
		model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION, administratorService.getListCompetition());
		return "admin/admin-competition";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="editcompetition/{competitionId}", method = RequestMethod.GET)
	public String initEditCompetitionPage(Model model, @PathVariable int competitionId) {
		
		model.addAttribute(ConstantUtil.KEY_COMPETITIONINFO, administratorService.getCompetitionInfo(competitionId));
		List<GroupDTO> listGroup = administratorService.getListGroup();
		Map<Boolean, List<GroupDTO>> selectedGroupTable = new HashMap<Boolean, List<GroupDTO>>();
		List<GroupDTO> listSelectedGroup = administratorService.getListGroup(competitionId);
		selectedGroupTable.put(true, listSelectedGroup);
		selectedGroupTable.put(false, ListUtils.removeAll(listGroup, listSelectedGroup));
		model.addAttribute("selectedGroupTable", selectedGroupTable);
		return "admin/admin-edit-competition";
	}
	
	@RequestMapping(value="editcompetition", method = RequestMethod.POST)
	@ResponseBody
	public int editCompetition(Model model, @RequestBody CompetitionDTO competitionDTO) {
		int returnCode = administratorService.editCompetition(competitionDTO);
		
		return returnCode;
	}
	
	@RequestMapping(value="addcompetition", method = RequestMethod.GET)
	public String initAddCompetitionPage(Model model) {
		model.addAttribute(ConstantUtil.KEY_LISTGROUP, administratorService.getListGroup());
		return "admin/admin-add-competition";
	}
	
	@RequestMapping(value="addcompetition", method = RequestMethod.POST)
	@ResponseBody
	public int addCompetition(Model model, @RequestBody CompetitionDTO competitionDTO) {
		
		int returnCode = administratorService.addCompetition(competitionDTO);
		
		return returnCode;
	}
	

	@RequestMapping(value="addgroup", method = RequestMethod.GET)
	public String addGroup(Model model) {
		
		return "admin/admin-add-group";
	}
	
	@RequestMapping(value="addgroup", method = RequestMethod.POST)
	@ResponseBody
	public int addGroup(Model model, @RequestBody GroupDTO groupDTO) {
		int result = BooleanUtils.toInteger(administratorService.addGroup(groupDTO));
		return result;
	}
	
	@RequestMapping(value="group/{groupId}", method = RequestMethod.GET)
	public String initEditGroupPage(Model model, @PathVariable int groupId) {
		
		model.addAttribute(ConstantUtil.KEY_GROUPINFO, administratorService.getGroup(groupId));
		return "admin/admin-edit-group";
	}
	@RequestMapping(value="group/edit", method = RequestMethod.POST)
	@ResponseBody
	public int editGroup(Model model, @RequestBody GroupDTO groupDTO) {
		
		int returnCode = BooleanUtils.toInteger(administratorService.editGroup(groupDTO));
		
		return returnCode;
	}
	@RequestMapping(value="group/{groupId}/delete", method = RequestMethod.POST)
	@ResponseBody
	public int deleteGroup(Model model, @PathVariable int groupId) {
		int returnCode = BooleanUtils.toInteger(administratorService.deleteGroup(groupId));
		return returnCode;
	}
	
	@RequestMapping(value="deletecompetition/{competitionId}", method = RequestMethod.GET)
	@ResponseBody
	public int deleteCompetition(Model model, @PathVariable int competitionId) {
		
		int returnCode = administratorService.deleteCompetition(competitionId);
		
		return returnCode;
	}
	
	/**
	 * View User's Profile
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userprofile/{userId}", method = RequestMethod.GET)
	public String showUserProfole(HttpServletRequest request, Model model, @PathVariable int userId) {
		
		
		model.addAttribute(ConstantUtil.KEY_USERDTO, 
				JsonBinder.fromJson(LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETUSERINFO + userId), UserDTO.class));
		
		return "admin/admin-profile-view";		
	}
}
