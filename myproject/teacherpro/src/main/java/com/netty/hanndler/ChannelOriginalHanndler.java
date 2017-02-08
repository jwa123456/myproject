package com.netty.hanndler;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Created by yh on 17-2-7.
 */
public class ChannelOriginalHanndler extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {

        // server端发送的是httpResponse，所以要使用HttpResponseEncoder进行编码
        socketChannel.pipeline().addLast("encoder", new HttpResponseEncoder());
        // server端接收到的是httpRequest，所以要使用HttpRequestDecoder进行解码
        socketChannel.pipeline().addLast("decoder", new HttpRequestDecoder());

        socketChannel.pipeline().addLast(new HttpObjectAggregator(65535));

        socketChannel.pipeline().addLast(new HttpServerHanndler());

    }
}
