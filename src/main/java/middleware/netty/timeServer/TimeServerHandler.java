package middleware.netty.timeServer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by wb-zj373670 on 2018/5/9.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception { // 1
        System.out.println(msg.getClass());
        String request = (String) msg; //2
        String response = null;
//        if ("QUERY TIME ORDER".equals(request)) { // 3
//            response = new Date(System.currentTimeMillis()).toString();
//        } else {
//            response = "BAD REQUEST";
//        }
        response = new Date(System.currentTimeMillis()).toString();
        response = response + System.getProperty("line.separator"); // 4
                ByteBuf resp = Unpooled.copiedBuffer(response.getBytes()); // 5
        ctx.writeAndFlush(resp); // 6
    }
}
