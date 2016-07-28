package com.s4you.flybeau.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.GroupDTO;
import com.s4you.flybeau.dto.ImageInfoDTO;
import com.s4you.flybeau.dto.Pager;
import com.s4you.flybeau.dto.SpeciesDTO;
import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.msg.ListImageInfoR2S;
import com.s4you.flybeau.utils.CommonUtils;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.utils.JsonBinder;
import com.s4you.flybeau.utils.LoadWebAPIUtil;

/**
 * 
 * ImageMngmServiceImpl 
 * Date: 20/05/2016 
 * ThienMV
 */
@Service
public class FlyingBeautiesServiceImpl implements FlyingBeautiesService{
	
	/**
	 * 
	 * Get All Image
	 * return ImageInfoR2S
	 */
	@Override
	public ListImageInfoR2S getListImage(int beginNum) {
		
		ListImageInfoR2S imageInfoR2S = new ListImageInfoR2S();
		Map<String , String> mapInput = new HashMap<String, String>();
		mapInput.put(ConstantUtil.KEY_TYPE, String.valueOf(ConstantUtil.TYPE_ALLIMAGE));		
		mapInput.put(ConstantUtil.KEY_PAGESIZE, String.valueOf(ConstantUtil.PAGE_SIZE_1));	
		
		/* Get Total Page */		
		String pageNumberStr = LoadWebAPIUtil.sendPost(ConstantUtil.URL_GETIMAGENUMBER, JsonBinder.toJson(mapInput));
		int pageNumber = CommonUtils.parseInt(pageNumberStr);
		
		if(beginNum == 0) {
			beginNum = ConstantUtil.INIT_PAGING;
		} else if(beginNum > pageNumber) {
			beginNum = pageNumber;
		}
		
		mapInput.put(ConstantUtil.KEY_BEGINNUM, String.valueOf(beginNum));
		
		/* Set Pager */
		Pager pageWrapper = new Pager(beginNum, pageNumber);
		imageInfoR2S.setPager(pageWrapper);	
		
		/* Set List Image */
		String jsonListImage = LoadWebAPIUtil.sendPost(ConstantUtil.URL_GETLISTIMAGE, JsonBinder.toJson(mapInput));
		
		List<ImageInfoDTO> listImage = JsonBinder.getListFromJson(jsonListImage, ImageInfoDTO.class);
		
		imageInfoR2S.setListImage(listImage);
		
		return imageInfoR2S;
	}
	
	/**
	 * 
	 * Get All Image Order by point
	 * return ImageInfoR2S
	 */
	@Override
	public ListImageInfoR2S getListHotImage(int beginNum) {

		ListImageInfoR2S imageInfoR2S = new ListImageInfoR2S();
		Map<String , String> mapInput = new HashMap<String, String>();
		mapInput.put(ConstantUtil.KEY_TYPE, String.valueOf(ConstantUtil.TYPE_HOTIMAGE));		
		mapInput.put(ConstantUtil.KEY_PAGESIZE, String.valueOf(ConstantUtil.PAGE_SIZE_1));	
		
		/* Get Total Page */
		String pageNumberStr = LoadWebAPIUtil.sendPost(ConstantUtil.URL_GETIMAGENUMBER, JsonBinder.toJson(mapInput));
		int pageNumber = CommonUtils.parseInt(pageNumberStr);
		
		if(beginNum == 0) {
			beginNum = ConstantUtil.INIT_PAGING;
		} else if(beginNum > pageNumber) {
			beginNum = pageNumber;
		}
		
		mapInput.put(ConstantUtil.KEY_BEGINNUM, String.valueOf(beginNum));
		
		/* Set Pager */
		Pager pageWrapper = new Pager(beginNum, pageNumber);
		imageInfoR2S.setPager(pageWrapper);	
		
		/* Set List Image */		
		String jsonListImage = LoadWebAPIUtil.sendPost(ConstantUtil.URL_GETLISTIMAGE, JsonBinder.toJson(mapInput));		
		List<ImageInfoDTO> listImage = JsonBinder.getListFromJson(jsonListImage, ImageInfoDTO.class);
		
		imageInfoR2S.setListImage(listImage);
		
		return imageInfoR2S;
	}
	
	/**
	 * 
	 * Get List Image of User in Competition
	 * return List<ImageInfoDTO>
	 */
	@Override
	public List<ImageInfoDTO> getOwnListImage(int userId, int compeitionId,
			int orderBy) {		
		
		Map<String , String> mapInput = new HashMap<String, String>();
		mapInput.put(ConstantUtil.KEY_USERID, String.valueOf(userId));
		mapInput.put(ConstantUtil.KEY_COMPETITIONID, String.valueOf(compeitionId));
		mapInput.put(ConstantUtil.KEY_TYPE, String.valueOf(ConstantUtil.TYPE_OWNIMAGE_COMPETITION));
		mapInput.put(ConstantUtil.KEY_ORDERBY, String.valueOf(orderBy));
		mapInput.put(ConstantUtil.KEY_BEGINNUM, String.valueOf(ConstantUtil.INIT_PAGING));
		mapInput.put(ConstantUtil.KEY_PAGESIZE, String.valueOf(ConstantUtil.PAGE_SIZE_MAX));		
		
		String jsonOutput = LoadWebAPIUtil.sendPost(ConstantUtil.URL_GETLISTIMAGE, JsonBinder.toJson(mapInput));		
 
		return JsonBinder.getListFromJson(jsonOutput, ImageInfoDTO.class);
	}

		/**
	 * Get List Image In Group
	 * @param userId
	 * @param orderbyDate
	 * @param pageNum
	 * @return ImageInfoR2S
	 */
	@Override
	public ListImageInfoR2S getListImageGroup(int userId, int orderBy, int beginNum) {
		
		ListImageInfoR2S imageInfoR2S = new ListImageInfoR2S();
		Map<String , String> mapInput = new HashMap<String, String>();
		mapInput.put(ConstantUtil.KEY_TYPE, String.valueOf(ConstantUtil.TYPE_GROUPIMAGE));		
		mapInput.put(ConstantUtil.KEY_PAGESIZE, String.valueOf(ConstantUtil.PAGE_SIZE_2));
		mapInput.put(ConstantUtil.KEY_ORDERBY, String.valueOf(orderBy));
		mapInput.put(ConstantUtil.KEY_USERID, String.valueOf(userId));		
		
		/* Get Total Page */
		String pageNumberStr = LoadWebAPIUtil.sendPost(ConstantUtil.URL_GETIMAGENUMBER, JsonBinder.toJson(mapInput));
		int pageNumber = CommonUtils.parseInt(pageNumberStr);
		
		if(beginNum == 0) {
			beginNum = ConstantUtil.INIT_PAGING;
		} else if(beginNum > pageNumber) {
			beginNum = pageNumber;
		}
		
		mapInput.put(ConstantUtil.KEY_BEGINNUM, String.valueOf(beginNum));
		
		/* Set Pager */
		Pager pageWrapper = new Pager(beginNum, pageNumber);
		imageInfoR2S.setPager(pageWrapper);	
		
		/* Set List Image */		
		String jsonListImage = LoadWebAPIUtil.sendPost(ConstantUtil.URL_GETLISTIMAGE, JsonBinder.toJson(mapInput));		
		List<ImageInfoDTO> listImage = JsonBinder.getListFromJson(jsonListImage, ImageInfoDTO.class);
		
		imageInfoR2S.setListImage(listImage);
		
		return imageInfoR2S;
	}
	
	
	/**
	 * 
	 * Get List All Group
	 * return List<GroupDTO>
	 */
	@Override
	public List<GroupDTO> getListGroup() {
		
		/* Get List competition which group of user joined*/
		String jsonOutput = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETLISTGROUP);
		return JsonBinder.getListFromJson(jsonOutput, GroupDTO.class);
	}

	/**
	 * Get List Competition For User
	 * @param userId
	 * @return List<CompetitionDTO>
	 */
	@Override
	public List<CompetitionDTO> getListCompetition(int userId) {
		String jsonOutput = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETLISTCPTT + userId);
		return JsonBinder.getListFromJson(jsonOutput, CompetitionDTO.class);
	}
	
	/**
	 * Get Group Info
	 * @param groupId
	 * @return GroupDTO
	 */
	@Override
	public GroupDTO getGroupInfo(int groupId) {
		
		GroupDTO groupInfo = new GroupDTO();
		
		/* Get Group Info */
		String jsonGroupInfo = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETGROUPINFO + groupId);
		groupInfo = JsonBinder.fromJson(jsonGroupInfo, GroupDTO.class);
		
		/* Get Top User in group */
		String jsonListTopUser = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETTOPUSERGROUP + groupId);
		groupInfo.setListUser(JsonBinder.getListFromJson(jsonListTopUser, UserDTO.class));		
		
		return groupInfo;
	}

	/**
	 * Get List Image In Competition
	 * @param competitionId
	 * @param orderBy
	 * @param pageNum
	 * @return ImageInfoR2S
	 */
	@Override
	public ListImageInfoR2S getListImageCompetition(int competitionId, int orderBy,
			int beginNum) {
		
		ListImageInfoR2S imageInfoR2S = new ListImageInfoR2S();
		Map<String , String> mapInput = new HashMap<String, String>();
		mapInput.put(ConstantUtil.KEY_TYPE, String.valueOf(ConstantUtil.TYPE_COMPETITONIMAGE));		
		mapInput.put(ConstantUtil.KEY_PAGESIZE, String.valueOf(ConstantUtil.PAGE_SIZE_2));
		mapInput.put(ConstantUtil.KEY_ORDERBY, String.valueOf(orderBy));
		mapInput.put(ConstantUtil.KEY_COMPETITIONID, String.valueOf(competitionId));
		
		/* Get Total Page */
		String pageNumberStr = LoadWebAPIUtil.sendPost(ConstantUtil.URL_GETIMAGENUMBER, JsonBinder.toJson(mapInput));
		int pageNumber = CommonUtils.parseInt(pageNumberStr);
		
		if(beginNum == 0) {
			beginNum = ConstantUtil.INIT_PAGING;
		} else if(beginNum > pageNumber) {
			beginNum = pageNumber;
		}
		
		mapInput.put(ConstantUtil.KEY_BEGINNUM, String.valueOf(beginNum));
		
		/* Set Pager */
		Pager pageWrapper = new Pager(beginNum, pageNumber);
		imageInfoR2S.setPager(pageWrapper);	
		
		/* Set List Image */		
		String jsonListImage = LoadWebAPIUtil.sendPost(ConstantUtil.URL_GETLISTIMAGE, JsonBinder.toJson(mapInput));		
		List<ImageInfoDTO> listImage = JsonBinder.getListFromJson(jsonListImage, ImageInfoDTO.class);
		
		imageInfoR2S.setListImage(listImage);
		
		return imageInfoR2S;
	}

	/**
	 * Get Competition Info
	 * @param competitionId
	 * @return CompetitionDTO
	 */
	@Override
	public CompetitionDTO getCompetitionInfo(CompetitionDTO competitionInfo) {		

		/* Get List Top User In Competition */
		String jsonListTopUser = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETTOPUSERCOMPETITION + competitionInfo.getCompetitionId());
		competitionInfo.setListUser(JsonBinder.getListFromJson(jsonListTopUser, UserDTO.class));
		
		/* Get List Top Group In Competition */
		String jsonListTopGroup = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETTOPGROUP + competitionInfo.getCompetitionId());
		competitionInfo.setListGroup(JsonBinder.getListFromJson(jsonListTopGroup, GroupDTO.class));
		
		return competitionInfo;
	}

	/**
	 * Get Image Info
	 * @param imageId
	 * @return ImageInfoDTO
	 */
	@Override
	public ImageInfoDTO getImageInfo(int imageId) {

		String jsonImageInfo = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETIMAGE + imageId);
		return JsonBinder.fromJson(jsonImageInfo, ImageInfoDTO.class);
	}

	@Override
	public List<SpeciesDTO> getListSpecies() {
		String jsonSpeciesInfo = LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETLISTSPECIES);
		return JsonBinder.getListFromJson(jsonSpeciesInfo, SpeciesDTO.class);
	}
}