package com.sl.ue;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AsciiString;

/**
 * 描述 []
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest>{

	private AsciiString contentType = HttpHeaderValues.TEXT_PLAIN;
	
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, FullHttpRequest arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("class: "+arg1.getClass().getName());
		DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.OK, Unpooled.wrappedBuffer("test".getBytes()));
		HttpHeaders heads = response.headers();
		heads.add(HttpHeaderNames.CONTENT_TYPE, contentType + "; charset=UTF-8");
		heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes()); // 3
		heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
		
		arg0.write(response);
	}

	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        super.channelReadComplete(ctx);
        ctx.flush(); // 4
    }


	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        if(null != cause) cause.printStackTrace();
        if(null != ctx) ctx.close();
    }

}
