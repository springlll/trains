package com.qk.trains.entity;

/**
 * @program: trains
 * @description: 作业者实体类
 * @author: Xiaotian
 * @create: 2018-09-06 10:29
 **/
public class Worker {
	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 所属站点ID
	 */
	private Integer station_id;
	/**分支使用
	 * 姓名
	 */
	private String operator;
	/**
	 * 姓名
	 */
//	private String name;
	/**
	 * 卡号
	 */
	private String card_id;

	/**
	 * 备注
	 */
	private String remarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStation_id() {
		return station_id;
	}

	public void setStation_id(Integer station_id) {
		this.station_id = station_id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Worker [id=" + id + ", station_id=" + station_id
				+ ", operator=" + operator + ", card_id=" + card_id
				+ ", remarks=" + remarks + "]";
	}
}