package com.s4you.flybeau.webapi.service;

import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.core.HttpHeaders;

import org.apache.log4j.Logger;

import com.s4you.flybeau.utils.ConstantUtil;
import com.sun.jersey.core.util.Base64;


public class ApiTokenSecurirty {
	
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";
	
	private static Logger logger = Logger.getLogger(ApiTokenSecurirty.class);
	
	
	public static int checkApiToken(HttpHeaders headers) {
				
		//Fetch authorization header
	    final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);
	    
	    logger.info("BASIC AUTHENCATION: " + authorization.get(0));
	    
	    //If no authorization information present; block access
	    if(authorization == null || authorization.isEmpty())
	    {
	    	logger.info("RESULT: " + ConstantUtil.ACCESS_DENIED);
	    	return  ConstantUtil.ACCESS_DENIED;
	    }
	    
	    //Get encoded username and password
	    final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
	    
	    //Decode username and password
	    String usernameAndPassword;
		usernameAndPassword = new String(Base64.decode(encodedUserPassword));

		//Split username and password tokens
	    final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
	    
	    final String username = tokenizer.nextToken();
	    final String password = tokenizer.nextToken();
	    
	    UserMngmApiService userManageService = new UserMngmApiService();
	    
	    if(userManageService.checkApiAuthencation(username, password) == ConstantUtil.RETCODE_NORMAL) {
	    	
	    	logger.info("RESULT: " + ConstantUtil.ACCESS_ACCEPTED);
	    	return  ConstantUtil.ACCESS_ACCEPTED;
	    }
	    
	    logger.info("RESULT: " + ConstantUtil.ACCESS_DENIED);
	    return  ConstantUtil.ACCESS_DENIED;
	}
}