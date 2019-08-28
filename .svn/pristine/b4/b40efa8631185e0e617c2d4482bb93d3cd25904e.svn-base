package com.rminfo.jinmaocs.util;

/**
 * Created by cl on 2018/10/10.
 * 返回数据封装
 */
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class DataUtil {
	private static final String STATUS = "status";
	private static final String MESSAGE = "message";

	private DataUtil() {
	}

	public static JSONObject getError(String message) {
		JSONObject data = new JSONObject();
		data.put(STATUS, 1);
		data.put(MESSAGE, message);
		return data;
	}

	public static JSONObject getSuccess() {
		JSONObject data = new JSONObject();
		data.put(STATUS, 0);
		return data;
	}

	public static JSONObject getInterfaceSuccess(String message) {
		JSONObject data = new JSONObject();
		data.put(STATUS, "+");
		data.put(MESSAGE, message);
		return data;
	}

	public static JSONObject getInterfaceError(String message) {
		JSONObject data = new JSONObject();
		data.put(STATUS, "-");
		data.put(MESSAGE, message);
		return data;
	}

	public static JSONObject getFsscSuccess(String message) {
		JSONObject data = new JSONObject();
		data.put(STATUS, "S");
		data.put(MESSAGE, message);
		return data;
	}

	public static JSONObject getFsscError(String message) {
		JSONObject data = new JSONObject();
		data.put(STATUS, "N");
		data.put(MESSAGE, message);
		return data;
	}

	public static JSONObject getData(Object object) {
		JSONObject data = new JSONObject();
		data.put(STATUS, 0);
		data.put("data", object);
		return data;
	}

	public static ModelAndView getMav(String msg, String url) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName(url);
		return mav;
	}

	public static JSONArray getArray(List objects) {
		JSONArray data = new JSONArray();
		data.addAll(objects);
		return data;
	}
}
