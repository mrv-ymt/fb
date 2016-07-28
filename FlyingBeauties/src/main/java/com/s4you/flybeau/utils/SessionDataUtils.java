package com.s4you.flybeau.utils;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.s4you.flybeau.dto.UserDTO;

public class SessionDataUtils {

	/**
	 * Get User Login Session
	 * @param request
	 * @return UserDTO
	 */
	public static UserDTO getSessionAttribute(HttpServletRequest request){
		
		return (UserDTO) request.getSession().getAttribute(ConstantUtil.SESSION_KEY);
	}
	
	/**
	 * Save Token, set User into Session
	 * @param user
	 */
	public static void saveSessionApiToken(UserDTO user) {
		
	       ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder
	               .currentRequestAttributes();
	       HttpSession session = request.getRequest().getSession();
	       session.setAttribute(ConstantUtil.SESSION_APITOKEN_KEY, user.getJsessionId());
	       session.setAttribute(ConstantUtil.SESSION_KEY, user);
    }
	
	public static Object getSessionValue(String param) {
        ServletRequestAttributes sv = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        if (sv != null) {
            HttpSession session = sv.getRequest().getSession();
            return session.getAttribute(param);
        }
        return null;
    }
	
	/**
     * Get apiToken from session to query API resources
     * @param Skip if this is for an AJAX call, else must pass a referred URL or messages
     * @return jsessionId (API server)
     * @throws Redirect to Home page
     */
    public static String getApiToken(String... refer) throws RuntimeException{
        try {
            String id = getSessionValue(ConstantUtil.SESSION_APITOKEN_KEY).toString();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
