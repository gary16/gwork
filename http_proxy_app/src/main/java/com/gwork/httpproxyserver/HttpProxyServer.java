package com.gwork.httpproxyserver;

import com.gwork.nettydemo.ReceivedServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpProxyServer {

	private int port;

	public HttpProxyServer(int port) {
		this.port = port;
	}

	public void doHandle() throws Exception {
		// 服务端eventloop
		EventLoopGroup serverGroup = new NioEventLoopGroup();
		// 客户端eventloop
		EventLoopGroup clientGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap server = new ServerBootstrap();
			server.group(serverGroup, clientGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 100).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new HttpServerCodec());
							//ch.pipeline().addLast(new HttpObjectAggregator(65536));
							ch.pipeline().addLast(new ClientCallHandler());
						}
					});

			ChannelFuture f = server.bind(port).sync(); // (7)
			f.channel().closeFuture().sync();
		} finally {
			clientGroup.shutdownGracefully();
			serverGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 9999;
		}
		new HttpProxyServer(port).doHandle();
	}

}
