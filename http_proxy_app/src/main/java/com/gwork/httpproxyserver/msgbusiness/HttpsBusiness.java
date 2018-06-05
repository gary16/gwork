package com.gwork.httpproxyserver.msgbusiness;

import com.gwork.httpproxyserver.TargetCallHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpMethod;

public class HttpsBusiness implements MsgBusiness{

	@Override
	public void handleMsg(final ChannelHandlerContext ctx, final Object msg) throws Exception {

			DefaultHttpRequest request = (DefaultHttpRequest) msg;
			String uri = request.uri();
			System.out.println("Uri:" + uri);
			String host = request.headers().get("Host");
			String[] temp = host.split(":");
			int port =Integer.parseInt(temp[1]);
			String hostName = temp[0];
			Bootstrap client = new Bootstrap();

			client.group(new NioEventLoopGroup()).channel(NioSocketChannel.class)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new HttpClientCodec());
							ch.pipeline().addLast(new TargetCallHandler(ctx.channel()));
						}
					});

			ChannelFuture cf = client.connect(hostName, port).sync();
			cf.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {

					if (future.isSuccess()) {
						System.out.println("11111" + msg);
						future.channel().writeAndFlush(msg);
					} else {
						ctx.channel().close();
					}
				}
			});

	
	}

	@Override
	public boolean isFited( Object msg) throws Exception{
		if (msg instanceof DefaultHttpRequest) {
			DefaultHttpRequest request = (DefaultHttpRequest) msg;
			if(request.method()==HttpMethod.CONNECT) {
				return true;
			}
		}
		return false;
	}

}
