package com.s4you.flybeau.msg;

import java.util.List;

import com.s4you.flybeau.dto.Pager;
import com.s4you.flybeau.dto.UserDTO;

public class ListUserR2S {

	private List<UserDTO> listUser;
	private Pager pager;
	
	/**
	 * @return the listUser
	 */
	public List<UserDTO> getListUser() {
		return listUser;
	}
	
	/**
	 * @param listUser the listUser to set
	 */
	public void setListUser(List<UserDTO> listUser) {
		this.listUser = listUser;
	}
	
	/**
	 * @return the pager
	 */
	public Pager getPager() {
		return pager;
	}
	
	/**
	 * @param pager the pager to set
	 */
	public void setPager(Pager pager) {
		this.pager = pager;
	}
}
