package com.example.demo.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest request, ChannelHandlerContext ctx) {
        request.headers().set("xjava_request", "edward");
        System.out.println(request.headers());
    }
}
