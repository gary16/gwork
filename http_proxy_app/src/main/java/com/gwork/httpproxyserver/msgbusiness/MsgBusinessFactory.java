package com.gwork.httpproxyserver.msgbusiness;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpRequest;

public class MsgBusinessFactory {

	
	private static List<MsgBusiness> handleList = new ArrayList<MsgBusiness>();
	
	static {
		handleList.add(new CommonHttpBusiness());
		handleList.add(new HttpsBusiness());
	}
	
	public static MsgBusiness getHandleBusiness(ChannelHandlerContext ctx, Object msg) throws Exception {
		for(MsgBusiness tmp:handleList) {
			if(tmp.isFited(msg)) {
				return tmp;
			}
		}
		return null;
	}
	
}
