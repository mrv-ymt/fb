package com.s4you.flybeau.msg;

import java.util.List;

import com.s4you.flybeau.dto.ImageInfoDTO;
import com.s4you.flybeau.dto.Pager;

/**
 * 
 * ImageInfoR2S 
 * Date: 11/05/2016 
 * ThienMV
 * 
 * */
public class ListImageInfoR2S {

	private List<ImageInfoDTO> listImage;
	private Pager pager;
	
	/**
	 * @return the listImage
	 */
	public List<ImageInfoDTO> getListImage() {
		return listImage;
	}
	/**
	 * @param listImage the listImage to set
	 */
	public void setListImage(List<ImageInfoDTO> listImage) {
		this.listImage = listImage;
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
