package cn.cd.caoyeung.webapp.utils.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

//import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.core.io.Resource;

public class JSONHelper {

//	public static JSONObject getJSONObjectByUrl(String url) {
//		JSONObject jo = null;
//		try {
//			HttpClient client = new HttpClient();
//			GetMethod getMethod = new GetMethod(url);
//			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//					new DefaultHttpMethodRetryHandler());
//			// 执行getMethod
//			int statusCode = client.executeMethod(getMethod);
//			if (statusCode == HttpStatus.SC_OK) {
//				byte[] responseBody = getMethod.getResponseBody();
//				jo = JSONObject.fromObject(new String(responseBody, "UTF-8"));
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return jo;
//	}
//	
//	public static JSONObject getJSONObjectByResource(Resource resource) {
//		JSONObject jo = null;
//		try {
//			InputStream in = resource.getInputStream();
//			jo = JSONHelper.getJSONObjectByIn(in);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return jo;
//	}
//	
//	public static JSONObject getJSONObjectByFile(File f) {
//		JSONObject jo = null;
//		try {
//			InputStream in = new FileInputStream(f);
//			jo =  JSONHelper.getJSONObjectByIn(in);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		return jo;
//	}
//	
//	public static JSONObject getJSONObjectByIn(InputStream in) {
//		JSONObject jo = null;
//		try {
//			BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
//			StringBuffer buffer = new StringBuffer();
//			String line = "";
//			while ((line = reader.readLine()) != null) {
//				buffer.append(line);
//			}
//			jo = JSONObject.fromObject(buffer.toString());
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return jo;
//	}
}
