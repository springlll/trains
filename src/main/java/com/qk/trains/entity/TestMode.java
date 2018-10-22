package com.qk.trains.entity;

/**
 * @program: trains
 * @description: 试风性质实体
 * @author: Xiaotian
 * @create: 2018-09-15 16:31
 **/
public class TestMode {
	/**
	 * 数据库自增id
	 */
	private Integer id;

	/**
	 * 对应数据库字段 name
	 * 试验性质名称
	 */
	private String name;

	/**
	 * 对应数据库字段 break_test_id
	 * 试验ID列表
	 */
	private String breakTestIds;

	/**
	 * 对应数据库字段 priority
	 * 选项优先级
	 */
	private Integer priority;

	/**
	 * 对应数据库字段 remarks
	 * 备注
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

	public String getBreakTestIds() {
		return breakTestIds;
	}

	public void setBreakTestIds(String breakTestIds) {
		this.breakTestIds = breakTestIds;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "TestMode{" +
				"id=" + id +
				", name='" + name + '\'' +
				", breakTestIds='" + breakTestIds + '\'' +
				", priority=" + priority +
				", remarks='" + remarks + '\'' +
				'}';
	}
}
