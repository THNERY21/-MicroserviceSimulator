package br.com.brasilprev.loja.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	private static String pattern = "dd/MM/yyyy HH:mm:ss";
	private static Gson gson = new GsonBuilder().setDateFormat(pattern).create();

	public static String toGson(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T fromGson(String json, Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

}
