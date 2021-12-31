package io.kimmking.rpcfx.demo.provider;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaders.Values.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {
    private HttpRequest request;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("收到客户端连接...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
        if(true){
            ctx.write("ok, hello");
            ctx.flush();
            return;
        }

        if(msg instanceof HttpRequest){
            request = (HttpRequest) msg;
        }

        if(msg instanceof HttpContent){
            HttpContent httpContent = (HttpContent) msg;
            ByteBuf buf = httpContent.content();
            buf.release();

            DefaultFullHttpResponse response =
                    new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer("".getBytes(UTF_8)));

            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
            if(HttpHeaders.isKeepAlive(request)){
                response.headers().set(CONNECTION, KEEP_ALIVE);
            }

            ctx.write(response);
            ctx.flush();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
