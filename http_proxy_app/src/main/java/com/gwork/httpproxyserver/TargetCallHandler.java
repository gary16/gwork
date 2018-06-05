package com.gwork.httpproxyserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TargetCallHandler extends ChannelInboundHandlerAdapter {

	private Channel channel;

	public TargetCallHandler(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		channel.writeAndFlush(msg);
		System.out.println("TargetCallHandler     "+msg.toString());
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		ctx.channel().close();
		channel.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.channel().close();
		channel.close();
		super.exceptionCaught(ctx, cause);
	}

}
