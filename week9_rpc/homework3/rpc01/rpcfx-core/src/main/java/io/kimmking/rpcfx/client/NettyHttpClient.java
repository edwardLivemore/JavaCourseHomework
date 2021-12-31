package io.kimmking.rpcfx.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpHeaders.Values.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpMethod.POST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class NettyHttpClient {

    public void connect(String url, String host, Integer port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 对response解码
                    ch.pipeline().addLast(new HttpRequestDecoder());

                    // 对request编码
                    ch.pipeline().addLast(new HttpRequestEncoder());

                    ch.pipeline().addLast(new HttpClientInBoundHandler());
                }
            });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

            URI uri = new URI("/");
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HTTP_1_1, POST, uri.toASCIIString());

            // 构建http请求
            request.headers().set(HOST, host);
            request.headers().set(CONNECTION, KEEP_ALIVE);
            request.headers().set(CONTENT_LENGTH, request.content().readableBytes());

            // 发送http请求
            channelFuture.channel().writeAndFlush(request);
//            channelFuture.channel().writeAndFlush("hello");
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
