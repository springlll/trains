package com.qk.trains.entity;

/**
 * @program: trains
 * @description: 站点实体类
 * @author: Xiaotian
 * @create: 2018-09-20 11:04
 **/
public class Station {
	/**
	 *
	 */
	private Integer id;

	/**
	 *
	 */
	private String name;

	/**
	 *
	 */
	private String remarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
