package com.s4you.flybeau.webapi.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.s4you.flybeau.dto.ImageInfoDTO;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.webapi.service.ApiTokenSecurirty;
import com.s4you.flybeau.webapi.service.ImageMngmApiService;

/**
 * ImageMngmController
 * @author ThienMV
 * Date: 18/5/2016
 */
@Path("/image")
@Produces(MediaType.APPLICATION_JSON)
public class ImageMngmApiController {
	
	ImageMngmApiService imageManageService = new ImageMngmApiService();
	
	/**
	 * Get All List Image
	 * @param inputJson
	 * @return List<ImageInfoDTO>
	 */
	@POST
	@Path("/getlistimage")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ImageInfoDTO> getListImage(String inputJson) {
		
		return imageManageService.getListImage(inputJson);
	}
	
	/**
	 *  Upload Image
	 * @param inputJson
	 * @return Retcode
	 */
	@POST
	@Path("/uploadimage")
	@Consumes(MediaType.APPLICATION_JSON)
	public int uploadImage(String inputJson, @Context HttpHeaders headers) {
		
		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			return ConstantUtil.RETCODE_NOAUTHENCATION;
		}
		
		return imageManageService.uploadImage(inputJson);
	}
	
	
	/**
	 *  Edit Image
	 * @param inputJson
	 * @return Retcode
	 */
	@POST
	@Path("/editimage")
	@Consumes(MediaType.APPLICATION_JSON)
	public int editImage(String inputJson, @Context HttpHeaders headers) {		
		
		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			return ConstantUtil.RETCODE_NOAUTHENCATION;
		}
		return imageManageService.editImage(inputJson);		
	}
	
	/**
	 * Delete Image
	 * @param imageId
	 * @return RetCode
	 */
	@DELETE
	@Path("/delete/{imageId}/{imageUrl}")
	public int deleteImage(@PathParam("imageId") int imageId, @PathParam("imageUrl") String imageUrl, @Context HttpHeaders headers) {		
		
		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			return ConstantUtil.RETCODE_NOAUTHENCATION;
		}
		return imageManageService.deleteImage(imageId, imageUrl);
		
	}	
	
	/**
	 * Get Image From ImageId
	 * @param imageId
	 * @return ImageInfoDTO
	 */
	@GET
	@Path("/{imageId}")
	public ImageInfoDTO getImage(@PathParam("imageId") int imageId) {

		return imageManageService.getImage(imageId);
	}
	
	/**
	 * Get All List Image
	 * @param inputJson
	 * @return List<ImageInfoDTO>
	 */
	@POST
	@Path("/getimagepagenumber")
	@Consumes(MediaType.APPLICATION_JSON)
	public int getImagePageNumber(String inputJson) {

		return imageManageService.getImagePageNumber(inputJson);
	}
	
	
}