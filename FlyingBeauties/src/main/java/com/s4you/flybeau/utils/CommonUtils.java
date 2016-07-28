package com.s4you.flybeau.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * 
 * CommonUtils 
 * Date: 10/05/2016 
 * ThienMV
 *
 */
public class CommonUtils {
	
	/**
	 * Parse String To Int
	 * @param string
	 * @return int
	 */
	public static int parseInt(String string) {
		if(string == null || ConstantUtil.INIT_STRING.equals(string)) {
			return 0;
		} else return Integer.parseInt(string);	
	}
	
	public static String getLifeTime(Timestamp insertedTime) {
		String lifeTimeString = "";
		Timestamp currentTime = new Timestamp(new Date().getTime());

		long lifeTime = currentTime.getTime() - insertedTime.getTime();
		
		if(lifeTime / (60 * 1000) < 60) {
			if(lifeTime / (60 * 1000)  < 1) {
				lifeTimeString = "Just";
			} else if(lifeTime / (60 * 1000)  == 1){
				lifeTimeString = lifeTime / (60 * 1000)  + " min";  
			} else {
				lifeTimeString = lifeTime / (60 * 1000)  + " mins";   
			}			                               
		} else if(lifeTime / (3600 * 1000) < 24) {
			
			if(lifeTime / (3600 * 1000) == 1){
				lifeTimeString = lifeTime / (3600 * 1000) + " hr";  
			} else {
				lifeTimeString = lifeTime / (3600 * 1000) + " hrs";   
			}				
		} else {
			lifeTimeString = timestampToDate(insertedTime);
		}
			
		return lifeTimeString;
	}
	
	/**
	 * Convert Timestamp to String Date
	 * @param timestamp
	 * @return String
	 */
	public static String timestampToDate(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new  SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date(timestamp.getTime());
		return dateFormat.format(date);
	}
	
	/**
	 * Convert Timestamp to String DateTime
	 * @param timestamp
	 * @return String
	 */
	public static String timestampToDateTime(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new  SimpleDateFormat("MM-dd-yyyy HH:mm");
		Date date = new Date(timestamp.getTime());
		return dateFormat.format(date);
	}
	
	/**
	 * Convert String DateTime to Timestamp
	 * @param timestamp
	 * @return dateTimeStr
	 */
	public static Timestamp dateTimeStrToTimeStamp(String dateTimeStr) {
		
		try {
			
			SimpleDateFormat dateFormat = new  SimpleDateFormat("MM-dd-yyyy");
			Date date = dateFormat.parse(dateTimeStr);
			
			return new Timestamp(date.getTime());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Convert Timestamp to String Date MMdd
	 * @param timestamp
	 * @return String
	 */
	public static String timestampToDateMMdd(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new  SimpleDateFormat("MM-dd");
		Date date = new Date(timestamp.getTime());
		return dateFormat.format(date);
	}
	
	/**
	 * Get Page Num
	 * @param imageNum
	 * @param pageSize
	 * @return pageNum
	 */
	public static int getPageNum(int imageNum, int pageSize) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		if (imageNum % pageSize != 0) {
			return imageNum / pageSize + 1;
		} else {
			return imageNum / pageSize;
		}
	}
	
	
	/**
	 * Read Properties
	 * @param key
	 * @return value
	 */
	public static String readProperties(String key) {
		
//		InputStream inputStream = CommonUtils.class.getClassLoader()
//				.getResourceAsStream(ConstantUtil.SYSTEM_PROPERTIES_FILE_NAME);
//		Properties prop = new Properties();
//		try {
//			prop.load(inputStream);
//			return prop.getProperty(key);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
		ResourceBundle configBundle = ResourceBundle.getBundle(ConstantUtil.SYSTEM_PROPERTIES_FILE_NAME);
			
		return configBundle.getString(key);		
	}
}