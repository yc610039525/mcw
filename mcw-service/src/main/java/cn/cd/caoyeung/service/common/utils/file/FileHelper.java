package cn.cd.caoyeung.service.common.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.logging.LogFactory;

public class FileHelper {
	public static void main(String[] args) throws IOException {
		LogFactory.getLog(FileHelper.class).info("文件复制开始");
		long startTime = System.currentTimeMillis();
		ClassLoader classLoader = ClassUtils.class.getClassLoader();
		URL url = classLoader.getResource("./RandomAccessFile.txt");
		File f = new File(url.getPath());
		FileInputStream fileIns = new FileInputStream(f);
		FileOutputStream fileOut = new FileOutputStream(new File("D:\\RandomAccessFile2.txt"));
		FileChannel fromChannel = fileIns.getChannel();
		FileChannel toChannel = fileOut.getChannel();
		fromChannel.transferTo(0, fromChannel.size(), toChannel);
		fromChannel.close();
		fileIns.close();	
		toChannel.close();
		fileOut.close();
		LogFactory.getLog(FileHelper.class).info("文件复制完毕，耗时（微妙）：" + (System.currentTimeMillis() - startTime));
	}
}
