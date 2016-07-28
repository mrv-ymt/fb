package com.s4you.flybeau.webapi.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.s4you.flybeau.dto.ImageInfoDTO;
import com.s4you.flybeau.utils.CommonUtils;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.utils.JsonBinder;
import com.s4you.flybeau.webapi.dao.ImageMngmApiDAO;

/**
 * ImageMngmService
 * @author ThienMV
 * Date: 18/5/2016
 */
public class ImageMngmApiService {
	
	private Logger log = LoggerFactory.getLogger(ImageMngmApiService.class);
	ImageMngmApiDAO imageManageDAO = new ImageMngmApiDAO();
	
	/* Path folder save image */
	private final String LOCAL_PATH_PARENT_FOLDER = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_PARENT_FOLDER);
	private final String LOCAL_PATH_RAW_FOLDER = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_RAW_FOLDER);
	private final String LOCAL_PATH_PREVIEW_FOLDER = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_PREVIEW_FOLDER);
	
	/* Size of image */
	private final int THUMBNAIL_IMAGE_SIZE = CommonUtils.parseInt(CommonUtils.readProperties(ConstantUtil.PROPERTIES_THUMBNAIL_IMAGE_SIZE));
	private final int MEDIUM_IMAGE_SIZE = CommonUtils.parseInt(CommonUtils.readProperties(ConstantUtil.PROPERTIES_MEDIUM_IMAGE_SIZE));
	private final int BIG_IMAGE_SIZE = CommonUtils.parseInt(CommonUtils.readProperties(ConstantUtil.PROPERTIES_BIG_IMAGE_SIZE));
	private final int ICON_IMAGE_SIZE = CommonUtils.parseInt(CommonUtils.readProperties(ConstantUtil.PROPERTIES_ICON_IMAGE_SIZE));
	
	private final String PATH_IMAGEMAGICK = CommonUtils.readProperties(ConstantUtil.PROPERTIES_PATH_IMAGEMAGICK);
	
	/**
	 * Get List Image Dependent Type
	 * @param inputJson
	 * @return List<ImageInfoDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<ImageInfoDTO> getListImage(String inputJson) {
		
		/* Convert input Json to Map */
		Map<String, String> inputMap = new HashMap<String, String>();		
		inputMap = JsonBinder.fromJson(inputJson, Map.class);
		
		/* Get Parameter */
		int type =  CommonUtils.parseInt((String) inputMap.get(ConstantUtil.KEY_TYPE));
		int beginNum =  CommonUtils.parseInt((String) inputMap.get(ConstantUtil.KEY_BEGINNUM));
		int pageSize = CommonUtils.parseInt((String) inputMap.get(ConstantUtil.KEY_PAGESIZE));
		int userId = CommonUtils.parseInt((String)  inputMap.get(ConstantUtil.KEY_USERID));
		int competitionId = CommonUtils.parseInt((String)  inputMap.get(ConstantUtil.KEY_COMPETITIONID));
		int orderBy = CommonUtils.parseInt((String)  inputMap.get(ConstantUtil.KEY_ORDERBY));
		
		/* Select List Image By Type */
		switch (type) {
			case ConstantUtil.TYPE_ALLIMAGE:
				return imageManageDAO.getAllImage(false, beginNum, pageSize);
	
			case ConstantUtil.TYPE_HOTIMAGE:
				return imageManageDAO.getAllImage(true, beginNum, pageSize);
			
			case ConstantUtil.TYPE_OWNIMAGE_COMPETITION:				
				return imageManageDAO.getOwnListImageCptt(userId, competitionId, orderBy, beginNum, pageSize);
				
			case ConstantUtil.TYPE_COMPETITONIMAGE:
				return imageManageDAO.getCompetitionListImage(competitionId, orderBy, beginNum, pageSize);
				
			case ConstantUtil.TYPE_GROUPIMAGE:
				return imageManageDAO.getListImageGroup(userId, orderBy, beginNum, pageSize);
				
			case ConstantUtil.TYPE_ALLOWNIMAGE:
				return imageManageDAO.getAllOwnImage(userId, orderBy, beginNum, pageSize);
			default:
				return null;
		}
	}
	

	/**
	 * Upload Image
	 * @param inputJson
	 * @return RetCode
	 */
	public int uploadImage(String inputJson) {
		
		/* Get Image Info From Json*/
		List<ImageInfoDTO> images = JsonBinder.getListFromJson(inputJson, ImageInfoDTO.class);
		for (ImageInfoDTO image : images) {
			handleImage(image);
		}
		return ConstantUtil.RETCODE_NORMAL;
	}
	
	
	private void handleImage(ImageInfoDTO imageInfoDTO){
		/* Decode File From Base64 Encoding To File Image */
		Base64 decoder = new Base64(); 
		log.debug(imageInfoDTO.getFileToBase64());
		byte[] imgBytes = decoder.decode(imageInfoDTO.getFileToBase64());
		
		/* Set Random Name For Image */
		long currentTime = new Date().getTime();
		SecureRandom random = new SecureRandom();
		String randomString = new BigInteger(130, random).toString(32).substring(0, 10);
		String imageName = currentTime + randomString;
		String pathOriginalImage = LOCAL_PATH_RAW_FOLDER + imageName + ConstantUtil.SUFFIX_IMAGE;
		
		imageInfoDTO.setImageUrl(imageName);
		imageManageDAO.uploadImage(imageInfoDTO);
		try {
		
			/* Create Folder To Save Image */
			File parentFolder = new File(LOCAL_PATH_PARENT_FOLDER);
	        if (!parentFolder.exists()) {	        	
	            parentFolder.mkdir();
	        }
	        
	        File rawFolder = new File(LOCAL_PATH_RAW_FOLDER);
	        if (!rawFolder.exists()) {
	            rawFolder.mkdir(); 
	        } 
	        
	        File previewFolder = new File(LOCAL_PATH_PREVIEW_FOLDER);
	        if (!previewFolder.exists()) {
	        	previewFolder.mkdir(); 
	        } 
	        
	        /* Save File To Disk */
			FileCopyUtils.copy(imgBytes, new File(pathOriginalImage));
			
			/* Process Starter with Path To lib IMAGEMAGICK */
			ProcessStarter.setGlobalSearchPath(PATH_IMAGEMAGICK);
			
			/* Create command */
			ConvertCmd cmd = new ConvertCmd();

			/* Create the operation, add images and operators/options */
			IMOperation optionBigImage = new IMOperation();
			optionBigImage.addImage(pathOriginalImage);
			optionBigImage.thumbnail(BIG_IMAGE_SIZE, BIG_IMAGE_SIZE);
			optionBigImage.quality(90d);
			optionBigImage.unsharp(100d);
			optionBigImage.addImage(LOCAL_PATH_PREVIEW_FOLDER + imageName + ConstantUtil.SUFFIX_BIG_IMAGE);

			// Execute the operation
			cmd.run(optionBigImage);
			
			/* Create the operation, add images and operators/options */
			IMOperation optionMediumImage = new IMOperation();
			optionMediumImage.addImage(pathOriginalImage);
			optionMediumImage.thumbnail(MEDIUM_IMAGE_SIZE, MEDIUM_IMAGE_SIZE);
			optionMediumImage.quality(85d);
			optionMediumImage.unsharp(100d);
			optionMediumImage.addImage(LOCAL_PATH_PREVIEW_FOLDER + imageName + ConstantUtil.SUFFIX_MEDIUM_IMAGE);

			// Execute the operation
			cmd.run(optionMediumImage);
			
			/* Create the operation, add images and operators/options */
			IMOperation optionThumbnail = new IMOperation();
			optionThumbnail.addImage(pathOriginalImage);
			optionThumbnail.thumbnail(THUMBNAIL_IMAGE_SIZE, THUMBNAIL_IMAGE_SIZE);
			optionThumbnail.quality(100d);
			optionThumbnail.unsharp(100d);
			optionThumbnail.addImage(LOCAL_PATH_PREVIEW_FOLDER + imageName + ConstantUtil.SUFFIX_THUMBNAIL_IMAGE);

			// Execute the operation
			cmd.run(optionThumbnail);
			
			/* Create the operation, add images and operators/options */
			IMOperation optionIcon = new IMOperation();
			optionIcon.addImage(pathOriginalImage);
			optionIcon.resize(ICON_IMAGE_SIZE, ICON_IMAGE_SIZE);
			optionIcon.quality(100d);
			optionIcon.unsharp(100d);
			optionIcon.addImage(LOCAL_PATH_PREVIEW_FOLDER + imageName + ConstantUtil.SUFFIX_ICON_IMAGE);

			// Execute the operation
			cmd.run(optionIcon);
			
		} catch (IOException e) {	
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (IM4JavaException e) {
			
			e.printStackTrace();
		}
	
	}
		
	
	/**
	 * Edit Image
	 * @param inputJson
	 * @return RetCode
	 */
	public int editImage(String inputJson) {
		/* Get Image Info From Json*/
		ImageInfoDTO imageInfo = JsonBinder.fromJson(inputJson, ImageInfoDTO.class);		
	
		if(imageManageDAO.editImage(imageInfo)) {
			return ConstantUtil.RETCODE_NORMAL;
		} 		
		
		return ConstantUtil.RETCODE_ABNORMAL;
	}

	/**
	 * Delete Image
	 * @param imageId
	 * @return RetCode
	 */
	public int deleteImage(int imageId, String imageUrl) {
		
		/* TODO */
		/* Delete Image */
		if(imageManageDAO.deleteImage(imageId)) {
			
//			File file = new File(LOCAL_PATH_UPLOADIMAGE + imageUrl);
//			
//			if(file != null) {
//				file.delete();
//			}
			
			return ConstantUtil.RETCODE_NORMAL;
		}
		
		return ConstantUtil.RETCODE_ABNORMAL;
	}

	/**
	 * Get Image Info
	 * @param imageId
	 * @return ImageInfoDTO
	 */
	public ImageInfoDTO getImage(int imageId) {

		/* Get Image */
		return imageManageDAO.getImage(imageId);
	}

	/**
	 * Get Image Page Number Total 
	 * @param inputJson
	 * @return Image Page Number
	 */
	@SuppressWarnings("unchecked")
	public int getImagePageNumber(String inputJson) {
		
		/* Convert input Json to Map */
		Map<String, Object> inputMap = new HashMap<String, Object>();		
		inputMap = JsonBinder.fromJson(inputJson, Map.class);
		
		/* Get Parameter */
		int type = CommonUtils.parseInt((String) inputMap.get(ConstantUtil.KEY_TYPE));
		int userId = CommonUtils.parseInt((String)  inputMap.get(ConstantUtil.KEY_USERID));
		int competitionId = CommonUtils.parseInt((String)  inputMap.get(ConstantUtil.KEY_COMPETITIONID));
		int pageSize = CommonUtils.parseInt((String) inputMap.get(ConstantUtil.KEY_PAGESIZE));
		
		/* Select List Image By Type */
		switch (type) {
			case ConstantUtil.TYPE_ALLIMAGE:
			case ConstantUtil.TYPE_HOTIMAGE:
				return imageManageDAO.getImagePageNumberAll(pageSize);
			
			case ConstantUtil.TYPE_OWNIMAGE_COMPETITION:				
				return imageManageDAO.getImagePageNumberOwnList(userId, competitionId, pageSize);
				
			case ConstantUtil.TYPE_COMPETITONIMAGE:
				return imageManageDAO.getImagePageNumberInCompetition(competitionId, pageSize);
				
			case ConstantUtil.TYPE_GROUPIMAGE:
				return imageManageDAO.getImagePageNumberInGroup(userId, pageSize);
			default:
				return 0;
		}
	}
}