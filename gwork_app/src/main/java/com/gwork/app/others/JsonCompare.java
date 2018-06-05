package com.gwork.app.others;

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
	
		String json="{\"a\":\"Atxt\",\"b\":[{\"c\":3} , ],}";
		JSONObject j = JSON.parseObject(json);		
		System.out.println(j);
		
		   ObjectMapper objectMapper = new ObjectMapper();
		   json = preHandle(json);
           Map<String, Object> params = objectMapper.readValue(json,Map.class);  
       	System.out.println(params);
       	
       	BigDecimal v1 = new 	BigDecimal(12000);
      	BigDecimal v2 = new 	BigDecimal(10000);
     	System.out.println(v1.compareTo(v2));
		
	}
	
	
	private static String preHandle(String json) {
		//Pattern pattern = Pattern.compile("(\\}\\s*,\\s*(?=\\}))|(\\}\\s*,\\s*(?=\\]))|(\\]\\s*,\\s*(?=\\}))|(\\]\\s*,\\s*(?=\\]))");
		Pattern pattern = Pattern.compile("[\\}\\]]\\s*,\\s*(?=[\\}\\]])");
		Matcher matcher = pattern.matcher(json);
		   StringBuffer sb = new StringBuffer();  
		while(matcher.find()) {
			
			matcher.appendReplacement(sb,matcher.group(0).replace(",", ""));
		}
		matcher.appendTail(sb);  
		return sb.toString();
	}
	
}
