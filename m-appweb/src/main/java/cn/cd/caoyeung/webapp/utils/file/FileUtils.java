package cn.cd.caoyeung.webapp.utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	public static void generateFile(String content, String path,
			String fileName, String fileType) {
		File tagFilePath = new File(path);
		if (!tagFilePath.exists()) {
			tagFilePath.mkdirs();
		}
		FileWriter io = null;
		BufferedWriter writer = null;
		try {
			if (!StringUtils.isEmpty(content)) {
				io = new FileWriter(path + File.separator + fileName + "."
						+ fileType);
				writer = new BufferedWriter(io);
				writer.write(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
					io.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isParentDirectoryCreationRequired(File file) {
		File parent = file.getParentFile();
		return (parent != null) && (!parent.exists());
	}

	public static boolean createMissingParentDirectories(File file) {
		File parent = file.getParentFile();
		if (parent == null) {
			throw new IllegalStateException(file
					+ " should not have a null parent");
		}
		if (parent.exists()) {
			throw new IllegalStateException(file
					+ " should not have existing parent directory");
		}
		return parent.mkdirs();
	}
	
	public static void writeFile(File file,String msg) throws Exception {
		FileOutputStream out = null;
		FileChannel fcout =  null;
		FileLock flout = null;
		try {
			if (isParentDirectoryCreationRequired(file)) {
				boolean result = createMissingParentDirectories(file);
				if (!result) {
					logger.error("创建文件失败[" + file.getAbsolutePath() + "]");
					throw new Exception("创建文件失败[" + file.getAbsolutePath()
							+ "]");
				}
			}
			out = new FileOutputStream(file,true);
			fcout = out.getChannel();
			while(true){
				flout = fcout.tryLock();
				if(flout!=null){
					break;
				}else{
					logger.warn("其它线程正在操作该文件，休眠等待1000ms");
					Thread.sleep(100);
				}
			}
			out.write(msg.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("写文件失败",e);
			throw new Exception("写文件失败：",e);
		}finally{
			if(flout!=null)
				flout.release();
			if(fcout!=null)
				fcout.close();
			if(out!=null){
				out.close();
				out=null;
			}
		}
	}
	

	public static void main(String[] args) {
		FileUtils xmlger = new FileUtils();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
		StringBuffer sbf = new StringBuffer();
		sbf.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
		sbf.append("<A>").append("我是谁").append("</A>");
		xmlger.generateFile(sbf.toString(),
				"E:/gdtnms_files/logs/irmsinterface/wo", "xxx"
						+ format.format(new Date()), "xml");
	}

}
