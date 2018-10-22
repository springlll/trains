package com.qk.trains.entity;

/**
 * @program: trains
 * @description: 股道方位类
 * @author: Xiaotian
 * @create: 2018-09-05 17:27
 **/
public class Direction {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 方位代表的int值
	 */
	private Integer directionInt;
	/**
	 * 方位
	 */
	private String name;

	/** 分支修改使用
	 * 方位名称
	 */
	private String position;

	/**
	 * 激活状态
	 */
	private Integer active;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getDirectionInt() {
		return directionInt;
	}

	public void setDirectionInt(int directionInt) {
		this.directionInt = directionInt;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Direction{" +
				"id=" + id +
				", directionInt=" + directionInt +
				", name='" + name + '\'' +
				", position='" + position + '\'' +
				", active=" + active +
				'}';
	}
}
