package com.s4you.flybeau.utils;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * JsonBinder
 * @version 20/5/2016
 * @author ThienMV
 * 
 */
public class JsonBinder
{
	private static Logger logger = LoggerFactory.getLogger(JsonBinder.class);
	private static Object lock = new Object();
	private static JsonBinder jsonBinder;
	private static JsonBinder jsonBinderNonNull;
	private static ObjectMapper mapper = new ObjectMapper();
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 
	 * @return
	 */
	public static JsonBinder getInstance()
	{
		if (jsonBinder == null)
		{
			synchronized (lock)
			{
				if (jsonBinder == null)
				{
					jsonBinder = new JsonBinder(Inclusion.NON_DEFAULT);
				}
			}
		}
		return jsonBinder;
	}
	
	/**
	 * 
	 * @param inclusion
	 * @return
	 */
	public static JsonBinder getInstanceNonNull()
	{
		if (jsonBinderNonNull == null)
		{
			synchronized (lock)
			{
				if (jsonBinderNonNull == null)
				{
					jsonBinderNonNull = new JsonBinder(Inclusion.NON_NULL);
				}
			}
		}
		return jsonBinderNonNull;
	}
	
	protected JsonBinder(Inclusion inclusion)
	{
		mapper = new ObjectMapper();
		mapper.getSerializationConfig().withSerializationInclusion(inclusion);
		// 设置输出包含的属性
		mapper.setSerializationInclusion(inclusion);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.setDateFormat(df);
	}
	
	/**
	 * 如果JSON字符串为Null或"null"字符串,返回Null. 如果JSON字符串为"[]",返回空集合.
	 * 
	 * 如需读取集合如List/Map,且不是List<String>这种简单类型时使用如下语句: List<MyBean> beanList =
	 * binder.getMapper().readValue(listString, new
	 * TypeReference<List<MyBean>>() {});
	 */
	public static <T> T fromJson(String jsonString, Class<T> clazz)
	{
		if (StringUtils.isEmpty(jsonString))
		{
			return null;
		}
		try
		{
			return mapper.readValue(jsonString, clazz);
		}
		catch (IOException e)
		{
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}
	
	/**
	 *
	 * @description: 将json字符串转化为List<bean>对象
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, TypeReference<T> type){
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return (T)mapper.readValue(jsonString, type);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}
	
	/**
	 * 
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(byte[] json, Class<T> clazz)
	{
		if (json == null || json.length == 0)
		{
			return null;
		}
		try
		{
			return mapper.readValue(json, clazz);
		}
		catch (IOException e)
		{
			logger.warn("parse json string error:", e);
			return null;
		}
	}
	
	/**
	 * 
	 */
	public static String toJson(Object object)
	{
		try
		{			
			return mapper.writeValueAsString(object);
		}
		catch (IOException e)
		{
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}
	
	/**
	 * Convert Json To List<Object>
	 * @param json
	 * @param clazz
	 * @return List<T>
	 */
	public static <T> List<T> getListFromJson(String json,  Class<T> clazz) {
		
		if (json == null || json.length() == 0)
		{
			return null;
		}
		try
		{
			return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
		}
		catch (IOException e)
		{
			logger.warn("parse json string error:", e);
			return null;
		}
	}
}
