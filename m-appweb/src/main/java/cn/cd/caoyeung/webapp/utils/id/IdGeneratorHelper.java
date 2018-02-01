/**
 * ------------------------------------------------------------*
 *          COPYRIGHT (C) 2004 BOCO Inter-Telecom INC          *
 *   CONFIDENTIAL AND PROPRIETARY. ALL RIGHTS RESERVED.        *
 *                                                             *
 *  This work contains confidential business information       *
 *  and intellectual property of BOCO  Inc, Beijing, CN.       *
 *  All rights reserved.                                       *
 * ------------------------------------------------------------*
 *
 * $ com.boco.transnms.client.graphkit.ui.TMStyle $
 */
/**
 *Revision Information:
 *
 *@version 1.0 2004-7-28 15:48:48 Initial release(zhangxu)
 */

package cn.cd.caoyeung.webapp.utils.id;

import java.net.InetAddress;

public class IdGeneratorHelper {
	private static int IP = -1;
	private static short counter = (short) 0;
	private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

	public IdGeneratorHelper() {
	}

	public static int getJVM() {
		return JVM;
	}

	public static short getCount() {
		synchronized (IdGeneratorHelper.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	public static int getIP() {
		if (IP == -1) {
			int ipAddr = 0;
			try {
				int result = 0;
				for (int i = 0; i < 4; i++) {
					result = (result << 8) - Byte.MIN_VALUE
							+ InetAddress.getLocalHost().getAddress()[i];
				}
				ipAddr = result;
			} catch (Exception e) {
				e.printStackTrace();
			}
			IP = ipAddr;
		}
		return IP;
	}

	public static short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	public static int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	public static String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	public static String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}
}
