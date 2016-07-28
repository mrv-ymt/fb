package com.s4you.flybeau.service;

import java.util.List;

import com.s4you.flybeau.dto.CompetitionDTO;
import com.s4you.flybeau.dto.GroupDTO;
import com.s4you.flybeau.dto.ImageInfoDTO;
import com.s4you.flybeau.dto.SpeciesDTO;
import com.s4you.flybeau.msg.ListImageInfoR2S;

/**
 * 
 * ImageMngmService 
 * Date: 20/05/2016 
 * ThienMV
 */
public interface FlyingBeautiesService {

	/**
	 * Get All Image
	 * @return ImageInfoR2S
	 */
	public ListImageInfoR2S getListImage(int beginNum);

	/**
	 * Get List Hot Image
	 * @return ImageInfoR2S
	 */
	public ListImageInfoR2S getListHotImage(int beginNum);

	/**
	 * Get List Group
	 * @return List<GroupDTO>
	 */
	public List<GroupDTO> getListGroup();

	/**
	 * Get List Own Image
	 * @param userId
	 * @param compeitionId
	 * @param orderBy
	 * @param pageNum
	 * @return List<ImageInfoDTO>
	 */
	public List<ImageInfoDTO> getOwnListImage(int userId, int compeitionId,
			int orderBy);

	/**
	 * Get List Image In Group
	 * @param userId
	 * @param pageNum 
	 * @param orderbyDate
	 * @return ImageInfoR2S
	 */
	public ListImageInfoR2S getListImageGroup(int userId, int orderBy, int pageNum);
	
	/**
	 * Get List Competition For User
	 * @param userId
	 * @return List<CompetitionDTO>
	 */
	public List<CompetitionDTO> getListCompetition(int userId);	
	
	/**
	 * Get Group Info
	 * @param groupId
	 * @return GroupDTO
	 */
	public GroupDTO getGroupInfo(int groupId);

	/**
	 * Get List Image In Competition
	 * @param competitionId
	 * @param orderBy
	 * @param pageNum
	 * @return ImageInfoR2S
	 */
	public ListImageInfoR2S getListImageCompetition(int competitionId, int orderBy,
			int pageNum);

	/**
	 * Get Competition Info
	 * @param competitionId
	 * @return CompetitionDTO
	 */
	public CompetitionDTO getCompetitionInfo(CompetitionDTO competitionInfo);

	/**
	 * Get Image Info
	 * @param imageId
	 * @return ImageInfoDTO
	 */
	public ImageInfoDTO getImageInfo(int imageId);

	public List<SpeciesDTO> getListSpecies();		
}
