package com.sl.ue.bean;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 描述 []
 */
public class DiscardClient {

	/**
	 * 功能描述 []
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)  {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
		
		Bootstrap boot = new Bootstrap();
		boot.group(workerGroup)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true) 
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ClientHandler());
				}
			});
		ChannelFuture future = boot.connect("localhost", 8080).sync();
        future.channel().closeFuture().sync();  
		}catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
            //关闭，释放线程资源  
        	workerGroup.shutdownGracefully();  
        }	
	}

}
