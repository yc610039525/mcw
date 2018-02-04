package cn.cd.caoyeung.webapp.common.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.core.io.Resource;

import com.alibaba.fastjson.JSONObject;

public class JSONHelper {

	public static JSONObject getJSONObjectByResource(Resource resource) {
		JSONObject jo = null;
		try {
			InputStream in = resource.getInputStream();
			jo = JSONHelper.getJSONObjectByIn(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jo;
	}

	public static JSONObject getJSONObjectByFile(File f) {
		JSONObject jo = null;
		try {
			InputStream in = new FileInputStream(f);
			jo = JSONHelper.getJSONObjectByIn(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return jo;
	}

	public static JSONObject getJSONObjectByIn(InputStream in) {
		JSONObject jo = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			jo = JSONObject.parseObject(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		return jo;
	}
}
