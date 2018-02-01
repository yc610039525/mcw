/**
 * 
 */
package cn.cd.caoyeung.webapp.utils.id;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author mooker
 * 
 */
public class MD5Util {
	
	private static Log log = LogFactory.getLog(MD5Util.class);

	/**
	 * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	protected static MessageDigest messagedigest = null;
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			log.error(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
			nsaex.printStackTrace();
		}
	}

	/**
	 * 适用于上G大的文件
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		messagedigest.update(byteBuffer);
		in.close();
		return bufferToHex(messagedigest.digest());
	}
	
	public static String getMD5String(String str){
		if(str!=null){
			messagedigest.update(str.getBytes());
			return bufferToHex(messagedigest.digest());
		}else{
			return null;
		}
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public boolean checkFileMD5(String tempfile, String md5code) throws IOException {
		boolean ismd5 = false;
		long begin = System.currentTimeMillis();
		// Office 2003.ISO 710 MB c6b47a5196d171715a87c9fa2ae3e03a 9s 双核3.0G
		// 2G内存
		String filepath = tempfile;
		File big = new File(filepath);
		String md5 = getFileMD5String(big);
		long end = System.currentTimeMillis();
		log.info("测试某文件的MD5过程结束，该文件md5:" + md5 + " 整个测试用时大约耗时:" + ((end - begin) / 1000) + "s");
		if (md5.trim().equals(md5code.trim())) {
			ismd5 = true;
		} else {
			log.info("校验文件中记录的数据文件[" + tempfile + "]MD5与数据文件实际MD5" + md5 + "不一致！");
		}
		return ismd5;
	}
}