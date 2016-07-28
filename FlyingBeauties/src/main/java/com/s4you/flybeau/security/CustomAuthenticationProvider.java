package com.s4you.flybeau.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.s4you.flybeau.msg.UserR2S;
import com.s4you.flybeau.utils.ConstantUtil;
import com.s4you.flybeau.utils.JsonBinder;
import com.s4you.flybeau.utils.LoadWebAPIUtil;
import com.s4you.flybeau.utils.SessionDataUtils;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	/**
	 * Set Authentication
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		/* Get Parameter */
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		/* Call API Login */
		UserR2S userR2S = login(username, password);

		/* Login successful */
		if (ConstantUtil.RETCODE_NORMAL == userR2S.getRetcode()) {

			SessionDataUtils.saveSessionApiToken(userR2S.getUserInfo());
			List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();

            grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + userR2S.getUserInfo().getRoleId()));

            return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
		}
		
		return null;
	}

	/**
	 * Class<?> authentication
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	/**
	 * Login Handler
	 * @param username
	 * @param password
	 * @return UserR2S
	 */
	public UserR2S login(String username,String password) {

		UserR2S userR2S = new UserR2S();
		
		Map<String, String> inputMap = new HashMap<String, String>();
		inputMap.put(ConstantUtil.KEY_USERNAME, username);
		inputMap.put(ConstantUtil.KEY_PASSWORD, password);	
		
		String convertMapJson =  JsonBinder.toJson(inputMap);
		
		/* Call API Login Handler */
		String outputJson = LoadWebAPIUtil.sendPost(ConstantUtil.URL_LOGIN, convertMapJson);
		
		if(outputJson != null) {		
			userR2S = JsonBinder.fromJson(outputJson, UserR2S.class);		
		} else {
			userR2S.setRetcode(ConstantUtil.RETCODE_ABNORMAL);
		}
		
		return userR2S;
	}
}
