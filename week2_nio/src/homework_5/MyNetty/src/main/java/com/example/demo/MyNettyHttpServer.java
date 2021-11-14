package com.example.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import static io.netty.buffer.PooledByteBufAllocator.DEFAULT;
import static io.netty.channel.ChannelOption.*;
import static io.netty.channel.unix.UnixChannelOption.SO_REUSEPORT;
import static io.netty.handler.logging.LogLevel.INFO;

public class MyNettyHttpServer implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        int port = 8801;

        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.option(SO_BACKLOG, 128)
                    .childOption(TCP_NODELAY, true)
                    .childOption(SO_KEEPALIVE, true)
                    .childOption(SO_REUSEADDR, true)
                    .childOption(SO_RCVBUF, 32 * 1024)
                    .childOption(SO_SNDBUF, 32 * 1024)
                    .childOption(SO_REUSEPORT, true)
                    .childOption(ALLOCATOR, DEFAULT);

            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(INFO))
                    .childHandler(new HttpInitializer());

            Channel channel = serverBootstrap.bind(port).sync().channel();
            System.out.println("开启netty http服务器, 监听地址和端口为 htpp://127.0.0.1:" + port + '/');
            channel.closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
