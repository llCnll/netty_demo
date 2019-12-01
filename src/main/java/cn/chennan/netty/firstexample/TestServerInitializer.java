package cn.chennan.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author ChenNan
 * @date 2019/12/1 21:48
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 可以添加若干个处理器 按顺序执行 到我们添加的TestHttpServerHandler处理器
     * @param ch
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());

    }
}
