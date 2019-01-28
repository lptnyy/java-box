package com.wzy.server.netty;

import com.wzy.func.fc.HttpServer;
import com.wzy.server.config.Config;
import com.wzy.server.netty.handle.HttpFileHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.nio.charset.Charset;

/**
 * 启动Http服务
 */
public class HttpServerImpl implements HttpServer {

    /**
     * 初始化服务
     */
    public void init() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.TCP_NODELAY, true);
            b.option(ChannelOption.SO_SNDBUF, 1048576*200);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder()); // 请求消息解码器
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(999999999));// 目的是将多个消息转换为单一的request或者response对象
                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());//响应解码器
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());//目的是支持异步大文件传输（）
                            ch.pipeline().addLast("fileServerHandler", new HttpFileHandler());// 业务逻辑
                            ch.pipeline().addLast("deflater", new HttpContentCompressor());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128);
            ChannelFuture f = b.bind(Config.config.getServerPort()).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    // 关闭服务
    public void close() {

    }
}
