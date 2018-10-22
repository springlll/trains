package com.qk.trains.util;

import com.qk.trains.service.SocketDataService;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: trains
 * @description: 实体类单例实例存储
 * @author: Xiaotian
 * @create: 2018-08-21 11:16
 **/
public class ContextInstances {
	/**
	 * 单例本身
	 */
	private static ContextInstances instance = null;

	static {
		instance = new ContextInstances();
	}

	/**
	 * SpringBoot的ApplicationContext
	 */
	private ApplicationContext applicationContext;


	/**
	 * 全局属性
	 */
	private static Map<String, Object> attributes;

	/**
	 * 私有构造方法
	 */
	private ContextInstances() {
		attributes = new HashMap<>();
	}

	/**
	 * 获取单例
	 *
	 * @return 单例实体
	 */
	public static ContextInstances getInstance() {
		return instance;
	}

	/**
	 * 获取ApplicationContext
	 *
	 * @return 返回
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 设置ApplicationContext
	 *
	 * @param applicationContext 传入ApplicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * 设置属性
	 *
	 * @param key    键值
	 * @param object 对象
	 */
	public static void setAttribute(String key, Object object) {
		attributes.put(key, object);
	}

	/**
	 * 获取属性
	 *
	 * @param key 键值
	 * @return 对象
	 */
	public static Object getAttribute(String key) {
		return attributes.get(key);
	}
}
