package com.s4you.flybeau.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.s4you.flybeau.dao.AdministratorDAO;
import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.GroupDTO;
import com.s4you.flybeau.dto.Pager;
import com.s4you.flybeau.dto.UserDTO;
import com.s4you.flybeau.msg.ListUserR2S;
import com.s4you.flybeau.utils.CommonUtils;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.utils.ImageUtils;

/**
 * 
 * AdministratorServiceImpl
 * Date: 05/07/2016
 * ThienMV
 * 
 * */
@Service
public class AdministratorServiceImpl implements AdministratorService {
	
	@Autowired
	AdministratorDAO administratorDAO;
	
	private final String LOCAL_PATH_PARENT_FOLDER = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_PARENT_FOLDER);
	private final String LOCAL_PATH_AVATAR = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_AVATAR);
	
	/**
	 * Get List User
	 * @return List<UserDTO>
	 */
	@Override
	public ListUserR2S getListUser(int beginNum) {
		
		ListUserR2S listUserR2S = new ListUserR2S();
		
		/* Get List User */
		List<UserDTO> listUser = administratorDAO.getListUser(beginNum, ConstantUtil.PAGE_SIZE_3);
		listUserR2S.setListUser(listUser);
		
		/* Get Total Page */		
		int pageNumber = CommonUtils.getPageNum(administratorDAO.getUserNumber(), ConstantUtil.PAGE_SIZE_3);
		
		if(beginNum == 0) {
			beginNum = ConstantUtil.INIT_PAGING;
		} else if(beginNum > pageNumber) {
			beginNum = pageNumber;
		}
		
		/* Set Pager */
		Pager pager = new Pager(beginNum, pageNumber);
		listUserR2S.setPager(pager);
		
		return listUserR2S;
	}
	
	/**
	 * Active/Deactive User 
	 * @param userId
	 * @param status
	 * @return Result
	 */
	@Override
	public boolean deactiveToogleUserRole(int userId, int status) {
		
		return administratorDAO.deactiveToogleUserRole(userId, status);
	}
	
	/**
	 * Get List Competition
	 * @return List<CompetitionDTO>
	 */
	@Override
	public List<CompetitionDTO> getListCompetition() {
		
		return administratorDAO.getListCompetition();
	}
	
	/**
	 * Add a new Competition
	 * @param competition
	 * @return Result
	 */
	@Override
	public int addCompetition(CompetitionDTO competition) {
		
		/* Check Validate Begin date < End Date */
		if(CommonUtils.dateTimeStrToTimeStamp(competition.getBeginTime())
				.after(CommonUtils.dateTimeStrToTimeStamp(competition.getEndTime()))) {
			return ConstantUtil.RETCODE_ERRORDATE;
		}
		
		/* Check Validate CompetitionName is exists */
		if(administratorDAO.getCompetitionByName(competition.getCompetitionName().trim(), competition.getCompetitionId()) 
				== ConstantUtil.RETCODE_ABNORMAL) {
			return ConstantUtil.RETCODE_ABNORMAL;
		}
		
		String fileToBase64StringCode = competition.getFileToBase64();
		
		if(fileToBase64StringCode != null && !(ConstantUtil.INIT_STRING.equals(fileToBase64StringCode))) {
			
			/* Decode File From Base64 Encoding To File Image */
			Base64 decoder = new Base64(); 
			byte[] imgBytes = decoder.decode(fileToBase64StringCode);
			
			/* Set Random Name For Image */
			long currentTime = new Date().getTime();
			SecureRandom random = new SecureRandom();
			String randomString = new BigInteger(130, random).toString(32).substring(0, 10);
			String competitionLogoUrl = currentTime + randomString + ConstantUtil.SUFFIX_IMAGE;
			
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
				FileCopyUtils.copy(imgBytes, new File(LOCAL_PATH_AVATAR + competitionLogoUrl));
				
				
				/* Delete Old Avatar */
				File fileOld = new File(LOCAL_PATH_AVATAR + competition.getCompetitionLogoUrl());
				
				if(fileOld != null) {
					fileOld.delete();
				}
				
				/* Set File Name To Insert Database */
				competition.setCompetitionLogoUrl(competitionLogoUrl);
				
			} catch (IOException e) {				
				e.printStackTrace();
			} 
		
		} else {
			competition.setCompetitionLogoUrl(ConstantUtil.PATH_NONE_LOGO_IMG);
		}
		
		if(administratorDAO.addCompetition(competition)) {
			return ConstantUtil.RETCODE_NORMAL;
		}
		
		return ConstantUtil.RETCODE_ABNORMAL;
	}
	
	/**
	 * Edit Competition
	 * @param competition
	 * @return Result
	 */
	@Override
	public int editCompetition(CompetitionDTO competition) {
		
		/* Check Validate Begin date < End Date */
		if(CommonUtils.dateTimeStrToTimeStamp(competition.getBeginTime())
				.after(CommonUtils.dateTimeStrToTimeStamp(competition.getEndTime()))) {
			return ConstantUtil.RETCODE_ERRORDATE;
		}
		
		/* Check Validate CompetitionName is exists */
		if(administratorDAO.getCompetitionByName(competition.getCompetitionName().trim(), competition.getCompetitionId()) 
				== ConstantUtil.RETCODE_ABNORMAL) {
			return ConstantUtil.RETCODE_ABNORMAL;
		}
		
		
		String fileToBase64StringCode = competition.getFileToBase64();
		
		if(fileToBase64StringCode != null && !(ConstantUtil.INIT_STRING.equals(fileToBase64StringCode))) {
			
			/* Decode File From Base64 Encoding To File Image */
			Base64 decoder = new Base64(); 
			byte[] imgBytes = decoder.decode(fileToBase64StringCode);
			
			/* Set Random Name For Image */
			long currentTime = new Date().getTime();
			SecureRandom random = new SecureRandom();
			String randomString = new BigInteger(130, random).toString(32).substring(0, 10);
			String competitionLogoUrl = currentTime + randomString + ConstantUtil.SUFFIX_IMAGE;
			
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
				FileCopyUtils.copy(imgBytes, new File(LOCAL_PATH_AVATAR + competitionLogoUrl));
				
				
				/* Delete Old Avatar */
				File fileOld = new File(LOCAL_PATH_AVATAR + competition.getCompetitionLogoUrl());
				
				if(fileOld != null) {
					fileOld.delete();
				}
				
				/* Set File Name To Insert Database */
				competition.setCompetitionLogoUrl(competitionLogoUrl);
				
			} catch (IOException e) {				
				e.printStackTrace();
			} 
		
		}
		
		if(administratorDAO.editCompetition(competition)) {
			return ConstantUtil.RETCODE_NORMAL;
		}
		
		return ConstantUtil.RETCODE_ABNORMAL;
	}
	
	/**
	 * Delete Competition
	 * @param competitionId
	 * @return Result
	 */
	@Override
	public int deleteCompetition(int competitionId) {
		
		if(administratorDAO.deleteCompetition(competitionId)) {
			return ConstantUtil.RETCODE_NORMAL;
		}
		
		return ConstantUtil.RETCODE_ABNORMAL;
	}
	
	/**
	 * Get List Group
	 * @return List<GroupDTO>
	 */
	@Override
	public List<GroupDTO> getListGroup() {
		
		return administratorDAO.getListGroup();
	}
	
	/**
	 * Add a new Group
	 * @param groupDTO
	 * @return Result
	 */
	@Override
	public boolean addGroup(GroupDTO groupDTO) {
		String groupLogoUrl = ImageUtils.generateAvatarImage(groupDTO.getFileToBase64());
		groupDTO.setGroupLogoUrl(groupLogoUrl);
		boolean result = administratorDAO.addGroup(groupDTO);;
		return result;
	}
	
	/**
	 * Edit Group
	 * @param groupDTO
	 * @return Result
	 */
	@Override
	public boolean editGroup(GroupDTO groupDTO) {
		String groupLogoUrl = ImageUtils.generateAvatarImage(groupDTO.getFileToBase64());
		groupDTO.setGroupLogoUrl(groupLogoUrl);
		return administratorDAO.editGroup(groupDTO);
	}
	
	/**
	 * Delete Group
	 * @param groupId
	 * @return Result
	 */
	@Override
	public boolean deleteGroup(int groupId) {
		
		return administratorDAO.deleteGroup(groupId);
	}
	
	/**
	 * Add group into competition
	 * @param groupId
	 * @param competitionId
	 * @return Result
	 */
	@Override
	@Transactional
	public boolean addGroupIntoCompetition(int groupId, int[] competitionIdArr) {
		
		for (int i = 0; i < competitionIdArr.length; i++) {
			
			administratorDAO.addGroupIntoCompetition(groupId, competitionIdArr[i]);
		}
		
		return true;
	}

	/**
	 * Get Competition Info
	 * @param competitionId
	 * @return CompetitionDTO
	 */
	@Override
	public CompetitionDTO getCompetitionInfo(int competitionId) {
		
		return administratorDAO.getCompetitionInfo(competitionId);
	}

	@Override
	public GroupDTO getGroup(int groupId) {
		return administratorDAO.getGroup(groupId);
	}

	@Override
	public List<GroupDTO> getListGroup(int competitionId) {
		return administratorDAO.getListGroup(competitionId);
	}
}
