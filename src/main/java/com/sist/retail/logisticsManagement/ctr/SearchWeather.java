package com.sist.retail.logisticsManagement.ctr;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sist.retail.vo.WeatherVO;

public class SearchWeather {
	public static WeatherVO weather(String city) throws Exception {
		WeatherVO vo = new WeatherVO();
		URL url;
		HttpURLConnection conn;
		InputStream is;
		InputStreamReader isr;
		String json;
		BufferedReader br;
		String address = "http://api.openweathermap.org/data/2.5/weather?q=" + city
				+ "&appid=64fa120e55b7a0bd4f4d972720570c4c";
		String protocol = "GET";
		url = new URL(address);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(protocol);
		br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		json = br.readLine();
		JSONParser jsonPaser = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonPaser.parse(json);
		JSONArray weatherArray = (JSONArray) jsonObj.get("weather");
		JSONObject obj = (JSONObject) weatherArray.get(0);
		JSONObject mainArray = (JSONObject) jsonObj.get("main");
		double temp_min = Double.parseDouble(mainArray.get("temp_min").toString());
		temp_min -= 273.15;
		double temp_max = Double.parseDouble(mainArray.get("temp_max").toString());
		temp_max -= 273.15;
		double temp = Double.parseDouble(mainArray.get("temp").toString());
		temp -= 273.15;
		vo.setWeather((String) obj.get("main"));
		vo.setCurTemp((int) temp);
		vo.setMinTemp((int) temp_min);
		vo.setMaxTemp((int) temp_max);
		return vo;

	}

}
