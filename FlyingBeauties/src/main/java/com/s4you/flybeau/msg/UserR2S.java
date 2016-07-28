package com.s4you.flybeau.msg;

import com.s4you.flybeau.dto.UserDTO;

/**
 * 
 * UserR2S 
 * Date: 11/05/2016 
 * ThienMV
 * 
 * */
public class UserR2S {
	private int Retcode;
	private UserDTO UserInfo;
	/**
	 * @return the retcode
	 */
	public int getRetcode() {
		return Retcode;
	}
	/**
	 * @param retcode the retcode to set
	 */
	public void setRetcode(int retcode) {
		Retcode = retcode;
	}
	/**
	 * @return the userInfo
	 */
	public UserDTO getUserInfo() {
		return UserInfo;
	}
	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(UserDTO userInfo) {
		UserInfo = userInfo;
	}
}
