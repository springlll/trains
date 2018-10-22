package com.qk.trains.util;

/**
 * @program: trains
 * @description: 效验码操作类
 * @author: Xiaotian
 * @create: 2018-08-10
 **/
public class CheckUtil {
	/**
	 * 对字符串进行单字节异或运算产生效验码
	 * @param src 源字符串
	 * @return 两位十六进制效验码
	 */
	public static String CRC_4(String src) {
		char[] pchMsg = src.toCharArray();
		int chChar = 0;
		int wDataLen = pchMsg.length;
		if (wDataLen == 1) {
			chChar = pchMsg[0];
		} else if (wDataLen >= 2) {
			chChar = pchMsg[0] ^ pchMsg[1];
			for (int i = 0; i < wDataLen - 2; i++) {
				chChar = chChar ^ pchMsg[i + 2];
			}
		}
		return Integer.toHexString(chChar);
	}

}
