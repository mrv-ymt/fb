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

import com.s4you.flybeau.dto.CommentDTO;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.webapi.service.ApiTokenSecurirty;
import com.s4you.flybeau.webapi.service.CommentMngmApiService;

/**
 * CommentMngmController
 * @author ThienMV
 * Date: 14/5/2016
 */
@Path("/comment")
@Produces(MediaType.APPLICATION_JSON)
public class CommentMngmApiController {

	CommentMngmApiService commentMngmService = new CommentMngmApiService();
	
	/**
	 * Get List Comment
	 * @param imageId
	 * @return List<CommentDTO>
	 */
	@Path("/getlist/{imageId}")
	@GET
	public List<CommentDTO> getListComment(@PathParam("imageId") int imageId) {
		return commentMngmService.getListComment(imageId);
	}
	
	/**
	 * Add Comment
	 * @param comment
	 * @return RetCode
	 */
	@Path("/add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int addComment(CommentDTO comment, @Context HttpHeaders headers) {		

		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			return ConstantUtil.RETCODE_NOAUTHENCATION;
		}
		
		return commentMngmService.addComment(comment);
	}
	
	/**
	 * Edit Comment
	 * @param comment
	 * @return RetCode
	 */
	@Path("/edit")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int edit(CommentDTO comment, @Context HttpHeaders headers) {		

		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			return ConstantUtil.RETCODE_NOAUTHENCATION;
		}
		return commentMngmService.editComment(comment);
	}
	
	/**
	 * Delete Comment
	 * @param commentId
	 * @return RetCode
	 */
	@Path("/delete/{commentId}")
	@DELETE	
	public int delete(@PathParam("commentId") int commentId, @Context HttpHeaders headers) {		

		if(ApiTokenSecurirty.checkApiToken(headers) == ConstantUtil.ACCESS_DENIED) {
			return ConstantUtil.RETCODE_NOAUTHENCATION;
		}
		return commentMngmService.delete(commentId);
	}
}