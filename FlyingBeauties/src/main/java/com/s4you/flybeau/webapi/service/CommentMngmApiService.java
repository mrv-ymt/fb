package com.s4you.flybeau.webapi.service;

import java.util.Date;
import java.util.List;

import com.s4you.flybeau.dto.CommentDTO;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.webapi.dao.CommentMngmApiDAO;

/**
 * CommentMngmService
 * @author ThienMV
 * Date: 14/5/2016
 */
public class CommentMngmApiService {

	CommentMngmApiDAO commentMngmDAO = new CommentMngmApiDAO();
	
	/**
	 * Add Comment
	 * @param comment
	 * @return RetCode
	 */
	public int addComment(CommentDTO comment) {
		
		/* Set Comment Time By Current Time */
		comment.setCommentTime(new Date());
		
		if(commentMngmDAO.addComment(comment)) {
			return ConstantUtil.RETCODE_NORMAL;
		}
		return ConstantUtil.RETCODE_ABNORMAL;
	}

	/**
	 * Get List Comment From Image
	 * @param imageId
	 * @return List<CommentDTO>
	 */
	public List<CommentDTO> getListComment(int imageId) {
		
		return commentMngmDAO.getListComment(imageId);
	}

	/**
	 * Edit Comment
	 * @param comment
	 * @return RetCode
	 */
	public int editComment(CommentDTO comment) {
		
		if(commentMngmDAO.editComment(comment)) {
			return ConstantUtil.RETCODE_NORMAL;
		}
		return ConstantUtil.RETCODE_ABNORMAL;
	}

	/**
	 * Delete Comment
	 * @param commentId
	 * @return RetCode
	 */
	public int delete(int commentId) {
		if(commentMngmDAO.deleteComment(commentId)) {
			return ConstantUtil.RETCODE_NORMAL;
		}
		return ConstantUtil.RETCODE_ABNORMAL;
	}
}