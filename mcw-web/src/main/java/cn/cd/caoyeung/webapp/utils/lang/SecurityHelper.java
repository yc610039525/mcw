package cn.cd.caoyeung.webapp.utils.lang;

import java.security.Key;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class SecurityHelper {
	private static SecurityHelper instance = new SecurityHelper();
	private String KEY = "BOCO_TNMS_2008";
	private String CIPHER_TYPE = "DES";
	private String BYTE_TYPE = "GBK";
	private Key key = null;
	private Cipher EnCipher = null;
	private Cipher DeCipher = null;
	private int DEFAULT_AVAILABLE_TIME = 3;
	private int DEFAULT_REPEAT_COUNT = 8;
	private boolean IS_CONSTRAINT_PASSWORD = true;

	public static SecurityHelper getInstance() {
		return instance;
	}

	public boolean isIS_CONSTRAINT_PASSWORD() {
		return this.IS_CONSTRAINT_PASSWORD;
	}

	public int getDEFAULT_REPEAT_COUNT() {
		return this.DEFAULT_REPEAT_COUNT;
	}

	public int getDEFAULT_AVAILABLE_TIME() {
		return this.DEFAULT_AVAILABLE_TIME;
	}

	public String getKEY() {
		return this.KEY;
	}

	public void setDEFAULT_AVAILABLE_TIME(int DEFAULT_AVAILABLE_TIME) {
		this.DEFAULT_AVAILABLE_TIME = DEFAULT_AVAILABLE_TIME;
	}

	public void setDEFAULT_REPEAT_COUNT(int DEFAULT_REPEAT_COUNT) {
		this.DEFAULT_REPEAT_COUNT = DEFAULT_REPEAT_COUNT;
	}

	public void setIS_CONSTRAINT_PASSWORD(boolean IS_CONSTRAINT_PASSWORD) {
		this.IS_CONSTRAINT_PASSWORD = IS_CONSTRAINT_PASSWORD;
	}

	public void setKEY(String KEY) {
		this.KEY = KEY;
		init();
	}

	public SecurityHelper() {
		init();
	}

	public void init() {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(this.CIPHER_TYPE);
			byte[] keyData = this.KEY.getBytes();
			DESKeySpec dks = new DESKeySpec(keyData);
			SecureRandom sr = new SecureRandom();

			this.key = keyFactory.generateSecret(dks);

			this.EnCipher = Cipher.getInstance(this.CIPHER_TYPE);
			this.DeCipher = Cipher.getInstance(this.CIPHER_TYPE);
			this.EnCipher.init(1, this.key, sr);
			this.DeCipher.init(2, this.key, sr);
		} catch (Exception ex) {
		}
	}

	public String getEncrypt(String str) {
		String encrypted = null;
		if (str != null)
			try {
				encrypted = byte2hex(getEncrypt(str.getBytes()));
			} catch (Exception e) {
			}

		return encrypted;
	}

	private byte[] getEncrypt(byte[] str) {
		byte[] encrypted = null;
		try {
			encrypted = this.EnCipher.doFinal(str);
		} catch (Exception e) {
		}
		return encrypted;
	}

	public String getDecrypt(String str) {
		String dncrypted = null;
		if (str != null)
			try {
				dncrypted = new String(getDecrypt(hex2byte(str.getBytes())));
			} catch (Exception e) {
			}

		return dncrypted;
	}

	private byte[] getDecrypt(byte[] b) {
		byte[] dncrypted = null;
		try {
			dncrypted = this.DeCipher.doFinal(b);
		} catch (Exception e) {
		}
		return dncrypted;
	}

	private String byte2hex(byte[] b) throws Exception {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; ++n) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	private byte[] hex2byte(byte[] b) throws Exception {
		if (b.length % 2 != 0)
			throw new IllegalArgumentException("长度不是偶数");

		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[(n / 2)] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static boolean isBefor(Timestamp statrTime, Timestamp endTime,
			int tpye, int count) {
		Calendar start = Calendar.getInstance();
		start.setTime(statrTime);
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		start.add(tpye, count);
		return start.before(end);
	}

	public static boolean isBefor(Timestamp statrTime, Timestamp endTime,
			int monthCount) {
		return isBefor(statrTime, endTime, 2, monthCount);
	}

	public static boolean isBefor(Timestamp statrTime, int monthCount) {
		return isBefor(statrTime, new Timestamp(System.currentTimeMillis()), 2,
				monthCount);
	}

	public static boolean isNum(String str) {
		boolean isNum = false;
		if ((str != null) && (str.length() > 0))
			isNum = str.matches("^([0-9])+$");

		return isNum;
	}

	public static boolean isContainNum(String str) {
		boolean isContainNum = false;
		if ((str != null) && (str.length() > 0))
			isContainNum = str.matches(".*?[0-9].*?");

		return isContainNum;
	}

	public static boolean isLetter(String str) {
		boolean isLetter = false;
		if ((str != null) && (str.length() > 0))
			isLetter = str.matches("^([A-Za-z])+$");

		return isLetter;
	}

	public static boolean isContainLetter_A(String str) {
		boolean isContainLetter = false;
		if ((str != null) && (str.length() > 0))
			isContainLetter = str.matches(".*?[A-Z].*?");

		return isContainLetter;
	}

	public static boolean isContainLetter_a(String str) {
		boolean isContainLetter = false;
		if ((str != null) && (str.length() > 0))
			isContainLetter = str.matches(".*?[a-z].*?");

		return isContainLetter;
	}

	public static boolean isContainCharacter(String str) {
		boolean isContainCharacter = false;
		if ((str != null) && (str.length() > 0))
			isContainCharacter = str.matches(".*?(?:[^A-Za-z0-9]).*?");

		return isContainCharacter;
	}
	
	public static void main(String[] args){
		System.out.println(SecurityHelper.getInstance().getDecrypt("14A11FA4053C049E"));
	}
}
