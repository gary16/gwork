package com.gwork.httpproxyserver;

import com.gwork.httpproxyserver.msgbusiness.MsgBusiness;
import com.gwork.httpproxyserver.msgbusiness.MsgBusinessFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientCallHandler extends ChannelInboundHandlerAdapter {


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		MsgBusiness biz = MsgBusinessFactory.getHandleBusiness(ctx, msg);
		if(biz!=null) {
			biz.handleMsg(ctx, msg);
		}
		
	}
	
	

}
