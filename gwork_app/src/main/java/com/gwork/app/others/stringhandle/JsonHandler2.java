package com.gwork.app.others.stringhandle;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class JsonHandler2 {

	@SuppressWarnings("all")
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {
	
		
		handle();
	}
	
	
	private static void  handle() {
		String srcText = "[{\"IN_AGE\":\"34\"},{\"IN_BASESCORE\":\"3\"},{\"IN_GENDER\":\"\"},{\"IN_IDNO\":\"440602198809251810\"},{\"IN_M12QUERYTIMES\":\"\"},{\"IN_M1QUERYTIMES\":\"\"},{\"IN_MOBILE\":\"13828468710\"},{\"IN_MOBILESTATUS\":\"\"},{\"IN_NAME\":\"dfd\"},{\"IN_ONLINETIME\":\"0\"},{\"IN_TDTHREEMONTHHIT\":\"\"},{\"IN_TIANYUSCORE\":\"\"},{\"OUT_INNERMSG\":\"鹏元手机时长不足\"},{\"OUT_OUTTERMSG\":\"综合授信不足\"},{\"OUT_RESULT\":\"Y\"},{\"OUT_RISKRESFLAG\":\"02\"},{\"OUT_RULERESTYPE\":\"05\"},{\"T_TDLOGINSCORE\":\"0\"},{\"T_TDREGSCORE\":\"-9999\"}]";
		Pattern pat = Pattern.compile("(\\{[^\\}]+\\},?)");
		StringBuffer sb = new StringBuffer("[");
		Matcher mth = pat.matcher(srcText);
		int j = 0 ;
		while(mth.find()) {
			sb.append(mth.group(1));
			if(j>0&&j%4==0) {
				sb.append("\n");
			}
			j++;
		}
		sb.append("]");
		System.out.println(sb.toString());
	}
}
