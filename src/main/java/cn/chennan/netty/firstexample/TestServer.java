package cn.chennan.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author ChenNan
 * @date 2019/12/1 15:02
 */
public class TestServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 用于获取连接  死循环
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 真正处理这个连接的 死循环

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer()); //服务器启动会绑定TestServerInitializer处理器

            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}
