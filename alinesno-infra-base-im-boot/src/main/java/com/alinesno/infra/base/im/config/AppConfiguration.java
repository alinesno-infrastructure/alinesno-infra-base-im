package com.alinesno.infra.base.im.config;

import com.alinesno.infra.base.im.commond.WsHandshakeProcessor;
import com.alinesno.infra.base.im.handle.LoginServiceProcessor;
import com.alinesno.infra.base.im.listener.ImGroupListener;
import com.alinesno.infra.base.im.listener.ImUserListener;
import com.alinesno.infra.common.facade.enable.EnableActable;
import com.alinesno.infra.common.web.adapter.sso.enable.EnableInfraSsoApi;
import com.dtflys.forest.springboot.annotation.ForestScan;
import org.jetbrains.annotations.NotNull;
import org.jim.core.packets.Command;
import org.jim.server.JimServer;
import org.jim.server.command.CommandManager;
import org.jim.server.command.handler.ChatReqHandler;
import org.jim.server.command.handler.HandshakeReqHandler;
import org.jim.server.command.handler.LoginReqHandler;
import org.jim.server.config.ImServerConfig;
import org.jim.server.config.PropertyImServerConfigBuilder;
import org.jim.server.processor.chat.DefaultAsyncChatMessageProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 统一配置中心
 */
@EnableActable
@EnableScheduling
@EnableInfraSsoApi
@ForestScan(basePackages = "com.alinesno.infra.base.im.adapter")
@MapperScan("com.alinesno.infra.base.im.mapper")
@Configuration
public class AppConfiguration {

    /**
     * IM Server Configuration
     * @return
     * @throws Exception
     */
    @NotNull
    public static JimServer getJimServer() throws Exception {
        ImServerConfig imServerConfig = new PropertyImServerConfigBuilder("config/jim.properties").build();

        //设置群组监听器，非必须，根据需要自己选择性实现;
        imServerConfig.setImGroupListener(new ImGroupListener());

        //设置绑定用户监听器，非必须，根据需要自己选择性实现;
        imServerConfig.setImUserListener(new ImUserListener());

        JimServer jimServer = new JimServer(imServerConfig);

        /*****************start 以下处理器根据业务需要自行添加与扩展，每个Command都可以添加扩展,此处为demo中处理**********************************/
        HandshakeReqHandler handshakeReqHandler = CommandManager.getCommand(Command.COMMAND_HANDSHAKE_REQ, HandshakeReqHandler.class);
        //添加自定义握手处理器;
        handshakeReqHandler.addMultiProtocolProcessor(new WsHandshakeProcessor());
        LoginReqHandler loginReqHandler = CommandManager.getCommand(Command.COMMAND_LOGIN_REQ,LoginReqHandler.class);
        //添加登录业务处理器;
        loginReqHandler.setSingleProcessor(new LoginServiceProcessor());
        //添加用户业务聊天记录处理器，用户自己继承抽象类BaseAsyncChatMessageProcessor即可，以下为内置默认的处理器！
        ChatReqHandler chatReqHandler = CommandManager.getCommand(Command.COMMAND_CHAT_REQ, ChatReqHandler.class);
        chatReqHandler.setSingleProcessor(new DefaultAsyncChatMessageProcessor());
        /*****************end *******************************************************************************************/
        return jimServer;
    }

}
