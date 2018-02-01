package cn.cd.caoyeung.webapp.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;


/**
 * @author Administrator
 *
 */
public class HttpClient {
	
	  
	public static String downFile(String url, Map<String, String> parameters) {
		String result = "";
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();
		String params = "";
		try {
			if (parameters.size() == 1) {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(parameters.get(name));
				}
				params = sb.toString();
			} else {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(parameters.get(name))
							.append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
			URL connURL = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) connURL
					.openConnection();
			httpConn.setRequestProperty("Accept-Charset", "UTF-8");
			httpConn.setRequestProperty("contentType", "UTF-8");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			out = new PrintWriter(httpConn.getOutputStream());
			out = new PrintWriter(new OutputStreamWriter(httpConn
					.getOutputStream(), "utf-8"));
			out.write(params);
			out.flush();
			InputStream in = httpConn.getInputStream();
			receiveFile(parameters.get("fileName"), in);
			in.close();
			httpConn.getResponseCode();
			httpConn.getResponseMessage();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		return result;
	}
	
	private static void receiveFile(String fileName, InputStream in)
			throws IOException {
		File file = new File("D:/"+ URLDecoder.decode(URLDecoder.decode(fileName, "UTF-8"),
						"UTF-8"));
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedInputStream bis = new BufferedInputStream(in);
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int d = -1;
		while ((d = bis.read()) != -1) {
			bos.write(d);
		}
		bis.close();
		bos.close();
	}
	
	public static void sendPost(String url, Map<String, String> parameters) {
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();
		String params = "";
		try {
			if (parameters.size() == 1) {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(parameters.get(name));
				}
				params = sb.toString();
			} else {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(parameters.get(name))
							.append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
			URL connURL = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) connURL
					.openConnection();
			httpConn.setRequestProperty("Accept-Charset", "UTF-8");
			httpConn.setRequestProperty("contentType", "UTF-8");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			out = new PrintWriter(httpConn.getOutputStream());
			out = new PrintWriter(new OutputStreamWriter(httpConn
					.getOutputStream(), "utf-8"));
			out.write(params);
			out.flush();
			InputStream in = httpConn.getInputStream();
			StringBuffer responseStr = new StringBuffer();
			byte[] buffer = new byte[100];
			int length = -1;
			while ((length = in.read(buffer)) != -1) {
				responseStr.append(new String(buffer, 0, length));
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}	
}
