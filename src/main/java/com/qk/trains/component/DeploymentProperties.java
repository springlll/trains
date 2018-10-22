package com.qk.trains.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: trains
 * @description: 部署属性组件
 * @author: Xiaotian
 * @create: 2018-09-19 15:53
 **/
@Component
@ConfigurationProperties(prefix = "deployment-properties")
public class DeploymentProperties {

	private int lightListeningPort;

	private int pressureListeningPort;

	private String staticPath;

	private String imageSavePath;

	public int getLightListeningPort() {
		return lightListeningPort;
	}

	public void setLightListeningPort(int lightListeningPort) {
		this.lightListeningPort = lightListeningPort;
	}

	public int getPressureListeningPort() {
		return pressureListeningPort;
	}

	public void setPressureListeningPort(int pressureListeningPort) {
		this.pressureListeningPort = pressureListeningPort;
	}

	public String getStaticPath() {
		return staticPath;
	}

	public void setStaticPath(String staticPath) {
		this.staticPath = staticPath;
	}

	public String getImageSavePath() {
		return imageSavePath;
	}

	public void setImageSavePath(String imageSavePath) {
		this.imageSavePath = imageSavePath;
	}
}
