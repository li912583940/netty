package com.sl.ue.bean;


import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * 描述 []
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	@Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
  
        ByteBuf buf = (ByteBuf)msg;  
        byte [] req = new byte[buf.readableBytes()];  
          
        buf.readBytes(req);  
          
        String message = new String(req,"UTF-8");  
          
        System.out.println("Netty-Server:Receive Message,"+ message);  
      
    } 
}
