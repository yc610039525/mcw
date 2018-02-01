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

public class IdHexGenerator {
	private static IdHexGenerator instance = new IdHexGenerator();
	public static final String PREFIX_SEPRATOR = "-";
	private static final String SEPRATOR = "";

	public static IdHexGenerator getInstance() {
		return instance;
	}

	public String generate() {
		String id = "";
		id += IdGeneratorHelper.format(IdGeneratorHelper.getIP()) + SEPRATOR;
		id += IdGeneratorHelper.format(IdGeneratorHelper.getJVM()) + SEPRATOR;
		id += IdGeneratorHelper.format(IdGeneratorHelper.getHiTime())
				+ SEPRATOR;
		id += IdGeneratorHelper.format(IdGeneratorHelper.getLoTime())
				+ SEPRATOR;
		id += IdGeneratorHelper.format(IdGeneratorHelper.getCount());
		return id;
	}

	public String generate(String prefix) {
		return prefix + "-" + generate();
	}

	public String compose(String className, String post) {
		return className + PREFIX_SEPRATOR + post;
	}
}
