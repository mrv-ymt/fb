package com.s4you.flybeau.dto;

import java.util.ArrayList;
import java.util.List;

import com.s4you.flybeau.utils.ConstantUtil;

public class Pager {
    private int currentPage;
    private int totalPage;
    private int nextPage;
    private int previousPage;
    private int firstPage;
    private int lastPage;
    private List<Integer> pageNumberList;   
    
	public Pager(int currentPage, int totalPage) {

    	this.currentPage = currentPage;
    	this.totalPage = totalPage;    	
    	
    	if(currentPage == ConstantUtil.INIT_PAGING) {
    		this.previousPage = ConstantUtil.DISABLE_PAGING_FLAG;	 
    		this.firstPage = ConstantUtil.DISABLE_PAGING_FLAG;
    	} else {
    			 
    		this.firstPage = ConstantUtil.INIT_PAGING;
    		this.previousPage = currentPage - 1;	    		    		
    	}
    	
    	if(currentPage == totalPage) {
    		this.lastPage = ConstantUtil.DISABLE_PAGING_FLAG;	
    		this.nextPage = ConstantUtil.DISABLE_PAGING_FLAG;
    	} else {
    		this.lastPage = totalPage;
    		this.nextPage = currentPage + 1;    		
    	}
    	
    	this.pageNumberList = showPage(currentPage, totalPage, ConstantUtil.MAXPAGEDISPLAY);
	}	
	
	/**
	 * Get List Page Number
	 * @param currentPage
	 * @param totalPage
	 * @param maxPageDisplay
	 * @return List Page Number
	 */
	private static List<Integer> showPage(int currentPage, int totalPage, int maxPageDisplay) {
		
		List<Integer> pageNumberList = new ArrayList<Integer>();   
		
        int pageMin, pageMax;
        pageMin = currentPage - (maxPageDisplay - 1) / 2;
        pageMax = currentPage + (maxPageDisplay - 1) / 2;

        if (pageMin <= 0) {
            pageMin = 1;
            pageMax = maxPageDisplay;
        }

        if (pageMax > totalPage) {
            pageMax = totalPage;
            pageMin = totalPage - maxPageDisplay + 1;
        }        
       
        for (int i = pageMin; i <= pageMax; i++) {   
        	if(i > 0){
        		pageNumberList.add(i);
        	}
        }
        
        return pageNumberList;
    }
	
	public static void main(String[] args) {
		 List<Integer> list = showPage(1, 2, 3);
		 for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			
		}
		 
		showPage1(1, 2, 5);
	}
	
	private static void showPage1(int currentPage, int pageNumbers, int maxPageDisplay) {
        int pageMin, pageMax;
        pageMin = currentPage - (maxPageDisplay - 1) / 2;
        pageMax = currentPage + (maxPageDisplay - 1) / 2;

        if (pageMin <= 0) {
            pageMin = 1;
            pageMax = maxPageDisplay;
        }

        if (pageMax > pageNumbers) {
            pageMax = pageNumbers;
            pageMin = pageNumbers - maxPageDisplay + 1;
        }
        for (int i = pageMin; i <= pageMax; i++) {
            System.out.print(i + "\t");
        }
    }
    
    /**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}


	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}


	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return nextPage;
	}


	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}


	/**
	 * @return the previousPage
	 */
	public int getPreviousPage() {
		return previousPage;
	}


	/**
	 * @param previousPage the previousPage to set
	 */
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}


	/**
	 * @return the firstPage
	 */
	public int getFirstPage() {
		return firstPage;
	}


	/**
	 * @param firstPage the firstPage to set
	 */
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}


	/**
	 * @return the lastPage
	 */
	public int getLastPage() {
		return lastPage;
	}


	/**
	 * @param lastPage the lastPage to set
	 */
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * @return the pageNumberList
	 */
	public List<Integer> getPageNumberList() {
		return pageNumberList;
	}

	/**
	 * @param pageNumberList the pageNumberList to set
	 */
	public void setPageNumberList(List<Integer> pageNumberList) {
		this.pageNumberList = pageNumberList;
	}
}
