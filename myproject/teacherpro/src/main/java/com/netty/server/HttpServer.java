package com.netty.server;

import com.netty.hanndler.ChannelOriginalHanndler;
import com.netty.hanndler.HttpServerHanndler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.Logger;

/**
 * Created by yh on 17-2-7.
 */
public class HttpServer {
    private static Logger log = Logger.getLogger(HttpServerHanndler.class);

    public void lunch(int port){
        System.out.println("服务正在启动！！！");
        EventLoopGroup bossGroup  = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ChannelFuture future = null;
        ServerBootstrap bootstrap = new ServerBootstrap();
        try{
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.SO_REUSEADDR,true)
                    .childHandler(new ChannelOriginalHanndler());
            future = bootstrap.bind(port).sync();

            System.out.println("服务启动成功！！！");

            future.channel().closeFuture().sync();

        }catch (Exception e){

            log.error("system error"+e);

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
