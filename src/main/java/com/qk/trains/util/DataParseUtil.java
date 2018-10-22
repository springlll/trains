package com.qk.trains.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @program: trains
 * @description: 字节数据转化快速处理
 * @author: Xiaotian
 * @create: 2018-08-21 11:47
 **/
public class DataParseUtil {
	/**
	 * 从io流中指定数量个字节获取int值
	 *
	 * @param is     输入流
	 * @param length 数字长度
	 * @return int值
	 * @throws IOException io异常
	 */
	public static int getInt(InputStream is, int length) throws IOException {
		byte[] bytes = new byte[length];
		is.read(bytes);
		return Integer.valueOf(new String(bytes));
	}

	public static double getDouble(InputStream is, int length) throws IOException {
		byte[] bytes = new byte[length];
		is.read(bytes);
		return Double.valueOf(new String(bytes));
	}

	public static String getString(InputStream is, int length) throws IOException {
		byte[] bytes = new byte[length];
		is.read(bytes);
		return new String(bytes);
	}

	/**
	 * 从io流中读12个字节获取Date对象
	 *
	 * @param is 输入流
	 * @return 返回Date对象
	 * @throws IOException           io异常
	 * @throws NumberFormatException 数字转换异常
	 */
	public static Date getDate(InputStream is) throws IOException, NumberFormatException {
		String raw = getString(is, 12);
		return Format.getDateIn12Char(raw);
	}
}
