package com.netty.startup;

import com.netty.contents.Params;
import com.netty.server.HttpServer;

/**
 * Created by yh on 17-2-7.
 */
public class ServerStart {
      public static void main(String[] args) {
          HttpServer httpServer = new HttpServer();
          System.out.println("开始启动整个后台服务。。。");
          httpServer.lunch(Params.NETTY_PORT);

      }

}
