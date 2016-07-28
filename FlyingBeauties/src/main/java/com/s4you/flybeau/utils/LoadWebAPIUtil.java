package com.s4you.flybeau.utils;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * LoadWebAPIUtil 
 * Date: 11/05/2016 
 * ThienMV
 * 
 * */
public class LoadWebAPIUtil {
	
	private static RestTemplate rt = new RestTemplate();
	private static String hostUrl =  CommonUtils.readProperties(ConstantUtil.PROPERTIES_HOST);
	protected static Logger logger = Logger.getLogger(LoadWebAPIUtil.class);
	
	/**
	 * Call API by POST
	 * @param path
	 * @param inputJson
	 * @return outputJson
	 */
	public static String sendPost(String path, String inputJson) {
		
		String apiUrl = hostUrl.concat(path);
		String result = ConstantUtil.INIT_STRING;
		
		logger.info("Call API URL: " + apiUrl);
		
		Client client = Client.create();
		WebResource webResource = client.resource(apiUrl);
		
		/* Call web service */
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, inputJson);
		
		/* Throw error if status response difference 200 */
		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}
		
		result = response.getEntity(String.class);
		
		return result;
	}
	
	/**
	 * Call API by GET
	 * @param path
	 * @return outputJson
	 */
	public static String sendGet(String path) {
		
		String result = ConstantUtil.INIT_STRING;
		
		/* Load File Properties to get host URL */
		String apiUrl = hostUrl.concat(path);
		
		logger.info("Call API URL: " + apiUrl);
		
		Client client = Client.create();
		WebResource webResource = client.resource(apiUrl);
		
		/* Call web service */
		ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		/* Throw error if status response difference 200 */
		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}
		
		result = response.getEntity(String.class);
		
		return result;
	}
	
	/**
	 * Call API with Authencation method Delete
	 * @param path
	 * @return outputJson
	 */
	public static String authenticatedDelete(String jsessionId, String path) {
		
		/* Load File Properties to get host url */
		String apiUrl = hostUrl.concat(path);
		logger.info("Call API URL: " + apiUrl);
		
		HttpHeaders header = setAuthenHeader(jsessionId);
		HttpEntity<String> entity = new HttpEntity<String>(null, header);
		
		ResponseEntity<String> res12 = rt.exchange(apiUrl, HttpMethod.DELETE,
				entity, String.class);
		
		return res12.getBody();
	}	
	
	/**
	 * Call API with Authencation method Get
	 * @param path
	 * @return outputJson
	 */
	public static String authenticatedGet(String jsessionId, String path) {
		
		/* Load File Properties to get host url */
		String apiUrl = hostUrl.concat(path);
		logger.info("Call API URL: " + apiUrl);
		
		HttpHeaders header = setAuthenHeader(jsessionId);
		HttpEntity<String> entity = new HttpEntity<String>(null, header);
		
		ResponseEntity<String> res12 = rt.exchange(apiUrl, HttpMethod.GET,
				entity, String.class);
		
		return res12.getBody();
	}	
	
	/**
	 * Call API with Authencation method Post
	 * @param jsessionId
	 * @param path
	 * @param inputJson
	 * @return outputJson
	 * 
	 */
	public static String authenticatedPost(String jsessionId, String path,
			String inputJson) {
		
		String apiUrl = hostUrl.concat(path);
		logger.info("Call API URL: " + apiUrl);
		
		HttpHeaders header = setAuthenHeader(jsessionId);
		HttpEntity<String> entity = new HttpEntity<String>(inputJson, header);
		
		ResponseEntity<String> res12 = rt.exchange(apiUrl, HttpMethod.POST,
				entity, String.class);
		
		return res12.getBody();
	}
	
	private static HttpHeaders setAuthenHeader(String jsessionId) {
		HttpHeaders header = new HttpHeaders();
		modProxy(header);
		header.add("Authorization", "Basic " + jsessionId);
		return header;
	}
	
	private static void modProxy(HttpHeaders header) {
		header.add("Accept", "*/*");
		header.add("Proxy-Connection", "Keep-Alive");
		header.add("Content-Type", "application/json; charset=UTF-8");
	}
	
}