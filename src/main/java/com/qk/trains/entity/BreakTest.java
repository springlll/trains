package com.qk.trains.entity;

/**
 * @program: trains
 * @description: 试风试验实体类
 * @author: Xiaotian
 * @create: 2018-08-31 15:40
 **/
/**
 * @author adawin
 *
 */
public class BreakTest {
	/**
	 * 数据库自增id
	 */
	private Long id;

	/**
	 * 对应数据库字段 name
	 * 试验名称
	 */
	private String name;

	/**
	 * 对应数据库字段 min
	 * 试验风压范围最小值
	 */
	private Integer min;

	/**
	 * 对应数据库字段 max
	 * 试验风压范围最大值
	 */
	private Integer max;

	/**
	 * 对应数据库字段 test_time
	 * 试验所要求持续时间，单位 秒
	 */
	private Integer testTime;

	/**
	 * 对应数据库字段 status
	 * 试验的状态设置
	 * 例如：简略试验不显示图表填色
	 */
	private Integer status;

	/**
	 * 对应数据库字段 fill_color
	 * 图表填充色
	 */
	private String fillColor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public Integer getTestTime() {
		return testTime;
	}

	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFillColor() {
		return fillColor;
	}

	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}

	@Override
	public String toString() {
		return "BreakTest{" +
				"id=" + id +
				", name='" + name + '\'' +
				", min=" + min +
				", max=" + max +
				", testTime=" + testTime +
				", status=" + status +
				", fillColor='" + fillColor + '\'' +
				'}';
	}
}
