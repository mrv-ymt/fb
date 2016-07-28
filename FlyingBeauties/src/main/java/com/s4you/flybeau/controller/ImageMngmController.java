package com.s4you.flybeau.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.GroupDTO;
import com.s4you.flybeau.dto.SpeciesDTO;
import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.msg.ListImageInfoR2S;
import com.s4you.flybeau.service.FlyingBeautiesService;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.utils.JsonBinder;
import com.s4you.flybeau.utils.LoadWebAPIUtil;
import com.s4you.flybeau.utils.SessionDataUtils;

@Controller
public class ImageMngmController {
	
	@Autowired
	FlyingBeautiesService flyingBeautiesService;	
	
	/**
	 * Init Welcome Page
	 * @param error
	 * @param request
	 * @param model
	 * @return welcomePage
	 */
	@RequestMapping(value = {"/", "welcome"}, method = RequestMethod.GET)
	public String welcome(@RequestParam(value = "error", required = false) String error, 
			HttpServletRequest request, Model model) {
			
		if(error != null) {
			model.addAttribute(ConstantUtil.KEY_ERROR, true);
		}
		
		return "redirect:/welcome/" + ConstantUtil.INIT_PAGING;
		
	}
	
	/**
	 * Init Welcome Page has Paging
	 * @param pageNum
	 * @param request
	 * @param model
	 * @return welcomePage
	 */
	@RequestMapping(value = {"/{pageNum}", "welcome/{pageNum}"}, method = RequestMethod.GET)
	public String welcomePage(@RequestParam(value="error", required = false) String error, 
			@PathVariable int pageNum, HttpServletRequest request,HttpServletResponse response, Model model) {
		
		UserDTO userInfo = (UserDTO) SessionDataUtils.getSessionValue(ConstantUtil.SESSION_KEY);
		if(userInfo != null) {
			
			if(userInfo.getRoleId() == ConstantUtil.ROLE_ADMIN) {
				
				return "redirect:/admin";
			} else if(userInfo.getRoleId() == ConstantUtil.ROLE_MOD) {
				
				return "redirect:/mod";				
			} else {
				
				return "redirect:/home";
			}
			
		} else {
		
			ListImageInfoR2S imageInfoR2S = flyingBeautiesService.getListImage(pageNum);
			List<GroupDTO> listGroup = flyingBeautiesService.getListGroup();
			
			/* Get List Country */
			String listCountryJson = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETLISTCOUNTRY);
			model.addAttribute(ConstantUtil.KEY_LISTCOUNTRY, JsonBinder.getListFromJson(listCountryJson, String.class));
			
			/* Get List Country Phone Code */
			String listCountryPhoneCodeJson = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETLISTCOUNTRYPHONECODE);
			model.addAttribute(ConstantUtil.KEY_LISTCOUNTRYPHONECODE, JsonBinder.getListFromJson(listCountryPhoneCodeJson, String.class));
			
			model.addAttribute(ConstantUtil.KEY_IMAGEINFOR2S, imageInfoR2S);
			model.addAttribute(ConstantUtil.KEY_LISTGROUP, listGroup);		
			
			if(error != null) {
				model.addAttribute(ConstantUtil.KEY_ERROR, true);
			}
		
			return "welcome";
		}
	}
	
	/**
	 * Init Hot Image Page
	 * @param pageNum
	 * @param request
	 * @param model
	 * @return hotimage Page
	 */
	@RequestMapping(value = "/hotimage/{pageNum}", method = RequestMethod.GET)
	public String getHotImage(@PathVariable int pageNum,
			HttpServletRequest request, Model model) {

		ListImageInfoR2S imageInfoR2S = flyingBeautiesService.getListHotImage(pageNum);
		List<GroupDTO> listGroup = flyingBeautiesService.getListGroup();
		
		model.addAttribute(ConstantUtil.KEY_IMAGEINFOR2S, imageInfoR2S);
		model.addAttribute(ConstantUtil.KEY_LISTGROUP, listGroup);

		return "hot-image";
	}	
	
	/**
	 * Redirect to Init HomePage
	 * @param request
	 * @param model
	 * @return home page
	 */
	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public String initHomePage(HttpServletRequest request, Model model) {		

		UserDTO userInfo = (UserDTO) SessionDataUtils.getSessionValue(ConstantUtil.SESSION_KEY);	
		List<CompetitionDTO> listCompetition =  flyingBeautiesService.getListCompetition(userInfo.getUserId());
		boolean joinedCptt = false;
		
		if(listCompetition != null) {
			
			for (int i = 0; i < listCompetition.size(); i++) {
				if(listCompetition.get(i).getJoined() == ConstantUtil.JOINED) {
					joinedCptt = true;
					break;
				}
			}
			
			if(joinedCptt) {
				return "redirect:/home/" + ConstantUtil.INIT_PAGING + "/" + ConstantUtil.ORDERBY_DATE;	
			}
		}
		
		model.addAttribute(ConstantUtil.KEY_NOTJOINTCPTT, true);
		return "redirect:/competition";
	}
	
	/**
	 * Init Home Page
	 * @param pageNum
	 * @param orderBy
	 * @param request
	 * @param model
	 * @return home page
	 */
	@RequestMapping(value = {"/home/{pageNum}/{orderBy}"}, method = RequestMethod.GET)
	public String showHomePageOrder(@PathVariable int pageNum, @PathVariable int orderBy,
			HttpServletRequest request, Model model) {
		
		UserDTO userInfo = (UserDTO) SessionDataUtils.getSessionValue(ConstantUtil.SESSION_KEY);	
		
		if(orderBy == ConstantUtil.ZERO) {
			orderBy = ConstantUtil.ORDERBY_DATE;
		}
		
		model.addAttribute(ConstantUtil.KEY_IMAGEINFOR2S, flyingBeautiesService.getListImageGroup(userInfo.getUserId(), orderBy, pageNum));
		model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION, flyingBeautiesService.getListCompetition(userInfo.getUserId()));
		model.addAttribute(ConstantUtil.KEY_GROUPINFO, flyingBeautiesService.getGroupInfo(userInfo.getGroupId()));
		model.addAttribute(ConstantUtil.KEY_ORDERBY, orderBy);
		
		return "home";
	}
	
	/**
	 * Redirect To Init Own List Home Page
	 * @param competitionId
	 * @param request
	 * @param model
	 */
	@RequestMapping(value = {"/ownlistimage/{competitionId}"}, method = RequestMethod.GET)
	public String initOwnListImagePage(@PathVariable int competitionId, HttpServletRequest request, Model model) {		

		return "redirect:/ownlistimage/" + competitionId + "/" + ConstantUtil.ORDERBY_DATE;		
	}
	
	/**
	 * Init Own List Home Page
	 * @param competitionId
	 * @param orderBy
	 * @param request
	 * @param model
	 */
	@RequestMapping(value = {"/ownlistimage/{competitionId}/{orderBy}"}, method = RequestMethod.GET)
	public String initOwnListImagePageOrder(@PathVariable int competitionId, @PathVariable int orderBy, 
			HttpServletRequest request, Model model) {		

		UserDTO userInfo = (UserDTO) SessionDataUtils.getSessionValue(ConstantUtil.SESSION_KEY);	
		List<CompetitionDTO> competitionList = flyingBeautiesService.getListCompetition(userInfo.getUserId());

		CompetitionDTO competitionInfo = null;
		
		if(orderBy == ConstantUtil.ZERO) {
			orderBy = ConstantUtil.ORDERBY_DATE;
		}
		
		/* Get Competition Info */
		if(competitionList != null) {
			
			for (int i = 0; i < competitionList.size(); i++) {
				
				if(competitionList.get(i).getCompetitionId() == competitionId) {
					competitionInfo = flyingBeautiesService.getCompetitionInfo(competitionList.get(i));
					break;
				}
			}
		}
		
		model.addAttribute(ConstantUtil.KEY_COMPETITIONINFO, competitionInfo);
		
		model.addAttribute(ConstantUtil.KEY_LISTIMAGE, flyingBeautiesService.getOwnListImage(userInfo.getUserId(), competitionId, orderBy));
		model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION, competitionList);
		model.addAttribute(ConstantUtil.KEY_ORDERBY, orderBy);
		model.addAttribute(ConstantUtil.KEY_COMPETITIONINFO, competitionInfo);
		
		return "ownlistimage";
	}

	/**
	 * Redirect Init List Image in Competition Page
	 * @param competitionId
	 */
	@RequestMapping(value="/competition", method = RequestMethod.GET)
	public String initCpttListImage(@RequestParam(value="id", required = false) Integer competitionId, Model model) {
		
		if(competitionId == null || competitionId == 0) {
			return "redirect:/competition/list";
		}
		
		UserDTO userInfo = (UserDTO) SessionDataUtils.getSessionValue(ConstantUtil.SESSION_KEY);	
		CompetitionDTO competitionInfo = null;
		int userId = userInfo == null? -1 : userInfo.getUserId();
		List<CompetitionDTO> competitionList = flyingBeautiesService.getListCompetition(userId);
		
		/* Get Competition Info */
		if(competitionList != null) {
			
			for (int i = 0; i < competitionList.size(); i++) {
				
				if(competitionList.get(i).getCompetitionId() == competitionId) {
					competitionInfo = flyingBeautiesService.getCompetitionInfo(competitionList.get(i));
					break;
				}
			}
		}
			
		model.addAttribute(ConstantUtil.KEY_IMAGEINFOR2S, flyingBeautiesService.getListImageCompetition(competitionId, ConstantUtil.ORDERBY_DATE, ConstantUtil.INIT_PAGING));
		
		model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION, competitionList);
		model.addAttribute(ConstantUtil.KEY_COMPETITIONINFO, competitionInfo);
		model.addAttribute(ConstantUtil.KEY_ORDERBY, ConstantUtil.ORDERBY_DATE);
		
		if(competitionInfo.getJoined() == ConstantUtil.JOINED) {
			return "cpttlistimage";
		} else {
			return "cpttlistimageview";
		}
	}
	
	/**
	 * Init List Image in Competition Page
	 * @param competitionId
	 * @param orderBy
	 * @param pageNum
	 * @param request
	 * @param model
	 */
	@RequestMapping(value="/competition/{pageNum}/{orderBy}", method = RequestMethod.GET)
	public String showCpttListImageFull(@RequestParam(value="id", required = false) int competitionId, @PathVariable int orderBy, @PathVariable int pageNum,
			HttpServletRequest request, Model model) {
		
		if(competitionId == 0) {
			return "redirect:/competition/list";
		}
		
		UserDTO userInfo = (UserDTO) SessionDataUtils.getSessionValue(ConstantUtil.SESSION_KEY);	
		CompetitionDTO competitionInfo = null;
		int userId = userInfo== null ? -1 : userInfo.getUserId();
		List<CompetitionDTO> competitionList = flyingBeautiesService.getListCompetition(userId);
		
		if(orderBy == ConstantUtil.ZERO) {
			orderBy = ConstantUtil.ORDERBY_DATE;
		}
		
		/* Get Competition Info */
		if(competitionList != null) {
			
			for (int i = 0; i < competitionList.size(); i++) {
				
				if(competitionList.get(i).getCompetitionId() == competitionId) {
					competitionInfo = flyingBeautiesService.getCompetitionInfo(competitionList.get(i));
					break;
				}
			}
		}
			
		model.addAttribute(ConstantUtil.KEY_IMAGEINFOR2S, flyingBeautiesService.getListImageCompetition(competitionId, orderBy, pageNum));
		
		model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION, competitionList);
		model.addAttribute(ConstantUtil.KEY_COMPETITIONINFO, competitionInfo);
		model.addAttribute(ConstantUtil.KEY_ORDERBY, orderBy);
		
		if(competitionInfo.getJoined() == ConstantUtil.JOINED) {
			return "cpttlistimage";
		} else {
			return "cpttlistimageview";
		}
	}
	
	
	/**
	 * Upload Image Init
	 * @param request
	 * @param model
	 * @return Upload Image Page
	 */
	@RequestMapping(value="/image/upload", method = RequestMethod.GET)
	public String showUploadImage(HttpServletRequest request, Model model) {
		
		UserDTO userInfo = (UserDTO) SessionDataUtils.getSessionValue(ConstantUtil.SESSION_KEY);	
		boolean joinedCptt = false;
		
		List<CompetitionDTO> listCompetition = flyingBeautiesService.getListCompetition(userInfo.getUserId());
		List<SpeciesDTO> listSpecies = flyingBeautiesService.getListSpecies();
		
		if(listCompetition != null) {
			
			for (int i = 0; i < listCompetition.size(); i++) {
				
				if(listCompetition.get(i).getJoined() == ConstantUtil.JOINED) {
					joinedCptt = true;
					break;
				}
			}
			
			if(joinedCptt) {
				model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION, listCompetition);		
				model.addAttribute(ConstantUtil.KEY_LISTSPECIES, listSpecies);
				return "upload-image";
			}
		}
		
		model.addAttribute(ConstantUtil.KEY_NOTJOINTCPTT, true);
		return "redirect:/competition";
	}
	
	/**
	 * Save Image Uploaded
	 * @param imageInfo
	 * @param request
	 * @return RetCode
	 */
	@RequestMapping(value="/image/upload", method=RequestMethod.POST)
	@ResponseBody
	public String uploadImage(@RequestBody String imageInfo, HttpServletRequest request) {
		
		return LoadWebAPIUtil.authenticatedPost(SessionDataUtils.getApiToken(), ConstantUtil.URL_UPLOADIMAGE, imageInfo);		
	}
	
	/**
	 * Edit Image Init
	 * @param request
	 * @param imageId
	 * @param model
	 * @return Edit Image Page
	 */
	@RequestMapping(value="/image/edit/{imageId}", method = RequestMethod.GET)
	public String editImage(HttpServletRequest request, @PathVariable int imageId, Model model) {
		
		UserDTO userInfo = (UserDTO) SessionDataUtils.getSessionValue(ConstantUtil.SESSION_KEY);	
		
		model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION, flyingBeautiesService.getListCompetition(userInfo.getUserId()));	
		model.addAttribute(ConstantUtil.KEY_LISTSPECIES, flyingBeautiesService.getListSpecies());
		model.addAttribute(ConstantUtil.KEY_IMAGEINFO, flyingBeautiesService.getImageInfo(imageId));
		
		return "edit-image";
	}
	
	/**
	 * Delete Image 
	 * @param imageId
	 * @param imageUrl
	 * @param request
	 * @return Retcode
	 */
	@RequestMapping(value="/image/delete/{imageId}/{imageUrl:.+}", method=RequestMethod.POST)
	@ResponseBody
	public String deleteImage(@PathVariable int imageId, @PathVariable String imageUrl, HttpServletRequest request) {
		
		return LoadWebAPIUtil.authenticatedDelete(SessionDataUtils.getApiToken(),ConstantUtil.URL_DELETEIMAGE + imageId + "/" + imageUrl);		
	}
	
	
	/**
	 * Save Image Edited
	 * @param imageInfo
	 * @param request
	 * @return Retcode
	 */
	@RequestMapping(value="/image/edit", method=RequestMethod.POST)
	@ResponseBody
	public String editImage(@RequestBody String imageInfo, HttpServletRequest request) {
		
		return LoadWebAPIUtil.authenticatedPost(SessionDataUtils.getApiToken(), ConstantUtil.URL_EDITIMAGE, imageInfo);		
	}
	
	/**
	 * Save Image Edited
	 * @param imageInfo
	 * @param request
	 * @return Retcode
	 */
	@RequestMapping(value="/specieslist", method=RequestMethod.GET)
	public String initSpeciesList(@RequestBody String imageInfo, HttpServletRequest request, Model model) {
		
		UserDTO userInfo = (UserDTO) SessionDataUtils.getSessionValue(ConstantUtil.SESSION_KEY);	
		model.addAttribute(ConstantUtil.KEY_LISTCOMPETITION, flyingBeautiesService.getListCompetition(userInfo.getUserId()));
		
		return "species-list";
	}
	
	
}
