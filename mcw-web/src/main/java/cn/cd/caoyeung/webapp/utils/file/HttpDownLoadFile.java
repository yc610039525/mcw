package cn.cd.caoyeung.webapp.utils.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class HttpDownLoadFile {
	private static Log logger = LogFactory.getLog(HttpDownLoadFile.class);
	public static boolean downLoadFile(String attachName,String attachUrl,String localfilepath,String localFileName){
		try {
			URL url = new URL(attachUrl);
			HttpURLConnection urlconnection = (HttpURLConnection) url
					.openConnection();
			urlconnection.setUseCaches(false);
			urlconnection.setConnectTimeout(20000);
			urlconnection.connect();
			byte[] buffers = new byte[4096];
			FileOutputStream fos = null;
			int size = 0;
			
			InputStream is = urlconnection.getInputStream();
			BufferedInputStream bufferedinputstream = new BufferedInputStream(is);
			long filelength = bufferedinputstream.available();
			if (bufferedinputstream != null) {

				fos = new FileOutputStream(localfilepath + File.separator
						+ localFileName);
				logger.debug("正在获取链接[" + url
							+ "]的内容...\n将其保存为文件[" + localFileName + "]");
				//保存文件 
				while ((size = bufferedinputstream.read(buffers)) != -1) {
					fos.write(buffers, 0, size);
				}
				fos.close();
				bufferedinputstream.close();
				urlconnection.disconnect();
				return true;
			}
		} catch (MalformedURLException expt) {
			logger.error("下载附件错误", expt);
		} catch (IOException eio) {
			logger.error("下载附件错误", eio);
		}
		return false;
	}

}
