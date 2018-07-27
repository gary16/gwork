package com.gwork.app.others.stringhandle;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JsonCompare {

	@SuppressWarnings("all")
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {

		Pattern pattern = Pattern.compile("\"riskCode\":(\\d+)");
		String src = "[{\"riskCode\":6,\"riskCodeValue\":2},{\"riskCode\":8,\"riskCodeValue\":3}]";
		
		Matcher mth = pattern.matcher(src);
		while(mth.find()) {
			System.out.println(mth.group(1));
		}
		
	}

}
