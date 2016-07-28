package com.s4you.flybeau.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.utils.LoadWebAPIUtil;
import com.s4you.flybeau.utils.SessionDataUtils;

/**
 * 
 * CommentMngmController 
 * Date: 14/06/2016 
 * ThienMV
 * 
 * */
@Controller
@RequestMapping(value="comment")
public class CommentMngmController {
	
	/**
	 * getListComment
	 * @param imageId
	 * @param request
	 * @return List Comment
	 */
	@RequestMapping(value="/{imageId}", method = RequestMethod.GET)
	@ResponseBody
	public String getListComment(@PathVariable int imageId, HttpServletRequest request) {
		return LoadWebAPIUtil.sendGet(ConstantUtil.URL_GETLISTCOMMENT + imageId);
	}
	
	/**
	 * Add Comment
	 * @param data
	 * @param request
	 * @return Retcode
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(@RequestBody String data, HttpServletRequest request) {
		return LoadWebAPIUtil.authenticatedPost(SessionDataUtils.getApiToken(), ConstantUtil.URL_ADDCOMMENT, data);
	}
	
	/**
	 * Edit Comment
	 * @param data
	 * @param request
	 * @return Retcode
	 */
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	@ResponseBody
	public String editComment(@RequestBody String data, HttpServletRequest request) {
		return LoadWebAPIUtil.authenticatedPost(SessionDataUtils.getApiToken(), ConstantUtil.URL_EDITCOMMENT, data);
	}
	
	/**
	 * Delete Comment
	 * @param commentId
	 * @param request
	 * @return Retcode
	 */
	@RequestMapping(value="/delete/{commentId}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteComment(@PathVariable int commentId, HttpServletRequest request) {
		
		return LoadWebAPIUtil.authenticatedDelete(SessionDataUtils.getApiToken(), ConstantUtil.URL_DELETECOMMENT + commentId);
	}
}
