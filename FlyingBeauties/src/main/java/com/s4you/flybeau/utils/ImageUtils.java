package com.s4you.flybeau.utils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class ImageUtils {
	
	private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);
	
	private final static String LOCAL_PATH_PARENT_FOLDER = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_PARENT_FOLDER);
	private final static String LOCAL_PATH_RAW_FOLDER = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_RAW_FOLDER);
	private final static String LOCAL_PATH_PREVIEW_FOLDER = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_PREVIEW_FOLDER);
	private final static String LOCAL_PATH_AVATAR_FOLDER = CommonUtils.readProperties(ConstantUtil.PROPERTIES_LOCAL_PATH_AVATAR);
	
	/* Size of image */
	private final static int THUMBNAIL_IMAGE_SIZE = CommonUtils.parseInt(CommonUtils.readProperties(ConstantUtil.PROPERTIES_THUMBNAIL_IMAGE_SIZE));
	private final static int MEDIUM_IMAGE_SIZE = CommonUtils.parseInt(CommonUtils.readProperties(ConstantUtil.PROPERTIES_MEDIUM_IMAGE_SIZE));
	private final static int BIG_IMAGE_SIZE = CommonUtils.parseInt(CommonUtils.readProperties(ConstantUtil.PROPERTIES_BIG_IMAGE_SIZE));
	private final static int ICON_IMAGE_SIZE = CommonUtils.parseInt(CommonUtils.readProperties(ConstantUtil.PROPERTIES_ICON_IMAGE_SIZE));
	
	private final static String PATH_IMAGEMAGICK = CommonUtils.readProperties(ConstantUtil.PROPERTIES_PATH_IMAGEMAGICK);

	/**
	 * Generate image from raw content
	 * @param base64content
	 */
	public static String generatePreviewImage(String base64content){
		return generateImageFromRaw(base64content, LOCAL_PATH_PREVIEW_FOLDER);
	}

	private static String generateImageFromRaw(String base64content, String generatedFolder) {
		Base64 decoder = new Base64(); 
		byte[] imgBytes = decoder.decode(base64content);
		
		/* Set Random Name For Image */
		long currentTime = new Date().getTime();
		SecureRandom random = new SecureRandom();
		String randomString = new BigInteger(130, random).toString(32).substring(0, 10);
		String imageName = currentTime + randomString;
		String pathOriginalImage = LOCAL_PATH_RAW_FOLDER + imageName + ConstantUtil.SUFFIX_IMAGE;
		log.info("New image name: " + imageName);
		try {
		
			log.info("Create folder to save image");
			File parentFolder = new File(LOCAL_PATH_PARENT_FOLDER);
	        if (!parentFolder.exists()) {	        	
	            parentFolder.mkdir();
	        }
	        
	        File rawFolder = new File(LOCAL_PATH_RAW_FOLDER);
	        if (!rawFolder.exists()) {
	            rawFolder.mkdir(); 
	        } 
	        
	        File previewFolder = new File(generatedFolder);
	        if (!previewFolder.exists()) {
	        	previewFolder.mkdir(); 
	        } 
	        
	        log.info("Save file to disk");
			FileCopyUtils.copy(imgBytes, new File(pathOriginalImage));
			
			log.info("Process starter with path to lib imagemagick");
			ProcessStarter.setGlobalSearchPath(PATH_IMAGEMAGICK);
			
			/* Create command */
			ConvertCmd cmd = new ConvertCmd();

			log.info("Starting generate preview big files");
			/* Create the operation, add images and operators/options */
			IMOperation optionBigImage = new IMOperation();
			optionBigImage.addImage(pathOriginalImage);
			optionBigImage.thumbnail(BIG_IMAGE_SIZE, BIG_IMAGE_SIZE);
			optionBigImage.quality(90d);
			optionBigImage.unsharp(100d);
			optionBigImage.addImage(generatedFolder + imageName + ConstantUtil.SUFFIX_BIG_IMAGE);

			// Execute the operation
			cmd.run(optionBigImage);
			
			/* Create the operation, add images and operators/options */
			log.info("Starting generate preview medium files");
			IMOperation optionMediumImage = new IMOperation();
			optionMediumImage.addImage(pathOriginalImage);
			optionMediumImage.thumbnail(MEDIUM_IMAGE_SIZE, MEDIUM_IMAGE_SIZE);
			optionMediumImage.quality(85d);
			optionMediumImage.unsharp(100d);
			optionMediumImage.addImage(generatedFolder + imageName + ConstantUtil.SUFFIX_MEDIUM_IMAGE);

			// Execute the operation
			cmd.run(optionMediumImage);
			
			/* Create the operation, add images and operators/options */
			log.info("Starting generate small files");
			IMOperation optionThumbnail = new IMOperation();
			optionThumbnail.addImage(pathOriginalImage);
			optionThumbnail.thumbnail(THUMBNAIL_IMAGE_SIZE, THUMBNAIL_IMAGE_SIZE);
			optionThumbnail.quality(100d);
			optionThumbnail.unsharp(100d);
			optionThumbnail.addImage(generatedFolder + imageName + ConstantUtil.SUFFIX_THUMBNAIL_IMAGE);

			// Execute the operation
			cmd.run(optionThumbnail);
			
			/* Create the operation, add images and operators/options */
			IMOperation optionIcon = new IMOperation();
			optionIcon.addImage(pathOriginalImage);
			optionIcon.resize(ICON_IMAGE_SIZE, ICON_IMAGE_SIZE);
			optionIcon.quality(100d);
			optionIcon.unsharp(100d);
			optionIcon.addImage(generatedFolder + imageName + ConstantUtil.SUFFIX_ICON_IMAGE);

			// Execute the operation
			cmd.run(optionIcon);
			
			return imageName;
		} catch (IOException e) {	
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (IM4JavaException e) {
			
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
	}
	
	public static String generateAvatarImage(String base64Content){
		Base64 decoder = new Base64(); 
		byte[] imgBytes = decoder.decode(base64Content);
		
		/* Set Random Name For Image */
		long currentTime = new Date().getTime();
		SecureRandom random = new SecureRandom();
		String randomString = new BigInteger(130, random).toString(32).substring(0, 10);
		String imageName = currentTime + randomString + ConstantUtil.SUFFIX_IMAGE;
		String pathOriginalImage = LOCAL_PATH_AVATAR_FOLDER + imageName;
		log.info("New image name: " + imageName);
		try {
		
			log.info("Create folder to save image");
			File parentFolder = new File(LOCAL_PATH_PARENT_FOLDER);
	        if (!parentFolder.exists()) {	        	
	            parentFolder.mkdir();
	        }
	        
	        log.info("Save file to disk");
			FileCopyUtils.copy(imgBytes, new File(pathOriginalImage));
			 log.info("End---");
			return imageName;
		}catch(Exception e){
			log.error("Fail", e);
			e.printStackTrace();
		}
		return StringUtils.EMPTY;
		
	}
	
}
