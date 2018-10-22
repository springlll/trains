package com.qk.trains;

import com.qk.trains.util.ContextInstances;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * SpringBoot启动类
 */
@SpringBootApplication
@MapperScan("com.qk.trains.mapper") //mapper包位置
@EnableScheduling
@EnableCaching
public class TrainsApplication {

	/**
	 * main方法
	 *
	 * @param args 启动参数
	 */
	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(TrainsApplication.class, args);
		//获取数据保存Service
		
	}
}

