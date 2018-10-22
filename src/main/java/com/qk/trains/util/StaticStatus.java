package com.qk.trains.util;

/**
 * @program: trains
 * @description: 静态状态数据，int枚举
 * @author: Xiaotian
 * @create: 2018-09-12 10:21
 **/
public class StaticStatus {
	/**
	 * 普通数据
	 */
	public final static int NORMAL_DATA = 0;

	/**
	 * 到位标志数字
	 */
	public final static int IN_PLACE_SYMBOL = 10;

	/**
	 * 到位数据协议电压
	 */
	public final static double IN_PLACE_VOLTAGE = 0.02;

	/**
	 * 上信号标志数字
	 */
	public final static int UP_SIGNAL_SYMBOL = 20;

	/**
	 * 上信号数据协议电压
	 */
	public final static double UP_SIGNAL_VOLTAGE = 0;

	/**
	 * 下信号标志数字
	 */
	public final static int DOWN_SIGNAL_SYMBOL = 21;

	/**
	 * 下信号数据协议电压
	 */
	public final static double DOWN_SIGNAL_VOLTAGE = 0.04;

	/**
	 * 结束标志数字
	 */
	public final static int END_SYMBOL = 30;

	/**
	 * 结束数据协议电压
	 */
	public final static double END_VOLTAGE = 0.01;

	/**
	 * 校时标志数字
	 */
	public final static int CHECK_TIME_SYMBOL = 5;

	/**
	 * 校时数据协议电压
	 */
	public final static double CHECK_TIME_VOLTAGE = 0.03;

	/**
	 * 试风结束数据标志
	 */
	public final static int PRESSURE_END_SYMBOL = 30;

	/**
	 * 试风结束数据协议电压
	 */
	public final static double PRESSURE_END_VOLTAGE = 0;

	/**
	 * 试风“简略"试验标志数字
	 */
	public final static int TEST_TYPE_SIMPLE = 0;

	/**
	 * 试风“全部"试验标志数字
	 */
	public final static int TEST_TYPE_ALL = 0;

	/**
	 * 信号灯性质“直通"试验标志数字
	 */
	public final static int SIGNAL_TYPE_THROUGH = 0;

	/**
	 * 信号灯性质“换挂"试验标志数字
	 */
	public final static int SIGNAL_TYPE_CHANGE = 1;
}
