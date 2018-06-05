package com.gwork.httpproxyserver.msgbusiness;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpRequest;

public interface MsgBusiness {

	void handleMsg(ChannelHandlerContext ctx,  Object msg) throws Exception;
	
	boolean isFited( Object msg) throws Exception;
}
