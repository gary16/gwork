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

		test2();
		
	}

	
	public static void test1() {
		Pattern pattern = Pattern.compile("\"riskCode\":(\\d+)");
		String src = "[{\"riskCode\":6,\"riskCodeValue\":2},{\"riskCode\":8,\"riskCodeValue\":3}]";
		
		Matcher mth = pattern.matcher(src);
		while(mth.find()) {
			System.out.println(mth.group(1));
		}
	}
	
	public static void test2() {
		  Pattern pat1 = Pattern.compile("\\$(IN_[^\\s]+)");
			String text = "如果        1=1        那么{        如果        ($IN_逾期天数 > 0)and($IN_逾期天数 <= $$逾期预警天数)        那么{        $OUT_规则集代码 := 'LoanCheck';        $OUT_规则集名称 := '风险预警规则';        $OUT_规则编号 := 'OVER_DUE_WARNING';        $OUT_规则描述 := '逾期预警';        $OUT_是否命中 := 'true';        }否则{        $OUT_规则集代码 := 'LoanCheck';        $OUT_规则集名称 := '风险预警规则';        $OUT_规则编号 := 'OVER_DUE_WARNING';        $OUT_规则描述 := '逾期预警';        $OUT_是否命中 := 'false';        }        }\r\n"; 
					
			Matcher mth = pat1.matcher(text);
			while (mth.find()) {
				System.out.println(mth.group(1));
			}
	}
}
