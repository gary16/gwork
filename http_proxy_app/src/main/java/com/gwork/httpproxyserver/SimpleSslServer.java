package com.gwork.httpproxyserver;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;

import com.gwork.nettydemo.ReceivedServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.ssl.SslHandler;

public class SimpleSslServer {

	private int port;

	public SimpleSslServer(int port) {
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
					.option(ChannelOption.SO_BACKLOG, 100)
					.childHandler(new ReceivedServerHandler()).childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							try {
								SSLContext sslcontext = SSLContext.getInstance("TLS");
								KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
								KeyStore ks = KeyStore.getInstance("JKS");
								String keyStorePath = "/apps/gwork.keystore";
								String keyStorePassword = "123456";
								ks.load(new FileInputStream(keyStorePath), keyStorePassword.toCharArray());
								String keyPassword = "123456";
								kmf.init(ks, keyPassword.toCharArray());
								sslcontext.init(kmf.getKeyManagers(), null, null);
								SSLEngine sslEngine = sslcontext.createSSLEngine();
								sslEngine.setUseClientMode(false);
								sslEngine.setNeedClientAuth(false);  
								ch.pipeline().addLast(new SslHandler(sslEngine));
								} catch (Exception e) {
								e.printStackTrace();
								}
							
							ch.pipeline().addLast(new HttpServerCodec());
							
							//ch.pipeline().addLast(new HttpChunkAggregator(1048576));
							ch.pipeline().addLast(new HttpObjectAggregator(1048576));
							ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
								@Override
								public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
									
									DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,Unpooled.wrappedBuffer("this is a test".getBytes("UTF-8")));
									  response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html");
								      response.headers().set(HttpHeaders.Names.CONTENT_LANGUAGE, response.content().readableBytes());
								     // response.headers().set(HttpHeaders.Names.CONNECTION, Values.KEEP_ALIVE);
									ctx.writeAndFlush(response);
									ctx.close();
								}
								
							      @Override
							        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
							            System.out.println("server channelReadComplete..");
							            ctx.channel().flush();//刷新后才将数据发出到SocketChannel
							         //   ctx.close();
							        }
							      
							        @Override
							        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
							                throws Exception {
							            System.out.println("server exceptionCaught..");
							            ctx.close();
							        }
								
							});
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
		new SimpleSslServer(port).doHandle();
	}

}
