package com.netty.hanndler;


import com.netty.util.memery.JsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;
import java.util.Map;
import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by yh on 17-2-7.
 */
public class HttpServerHanndler extends ChannelHandlerAdapter {

    private static Logger log = Logger.getLogger(HttpServerHanndler.class);

    private HttpRequest request;
    private String uri;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if(msg instanceof HttpRequest){
            request = (HttpRequest)msg;
            uri = request.uri();
            String quenue = uri.substring(1,uri.length());
            System.out.println("the uri is "+quenue);
            log.debug("Quenue is"+quenue);
        }

        if(msg instanceof HttpContent){
            HttpContent content = (HttpContent)msg;
            ByteBuf buf = content.content();
            String message = buf.toString(CharsetUtil.UTF_8);
            System.out.println(message);
            log.debug("message is"+message);
            buf.release();

            //解析json串
            System.out.println(JsonUtil.isJson(message));
            log.debug("boolean is"+JsonUtil.isJson(message));
            Map<String,Object> param = JsonUtil.strToMap(message);
            String data = JsonUtil.pojoToJson(param.get("name"));
            System.out.println(data);

            String flag = JsonUtil.pojoToJson(param.get("flag"));
            System.out.println(flag);

            String method = JsonUtil.pojoToJson(param.get("method"));
            System.out.println(method);

            //反射调用处理类，得到业务处理后的结果


            log.debug("data is "+data);
            //最终的结果
            String result = "aaaa";
            //读取配置文件得到相应的处理类

            FullHttpResponse response =
                    new DefaultFullHttpResponse(HTTP_1_1,OK,
                            Unpooled.wrappedBuffer(result.getBytes("UTF-8")));
            response.headers().set(CONTENT_TYPE,"text/plain");
            response.headers().setInt(CONTENT_LENGTH,response.content().readableBytes());
            if(HttpHeaderUtil.isKeepAlive(request)){
                response.headers().set(CONNECTION,KEEP_ALIVE);
            }
            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        ctx.close();

        log.debug("=============================================");
    }
}
