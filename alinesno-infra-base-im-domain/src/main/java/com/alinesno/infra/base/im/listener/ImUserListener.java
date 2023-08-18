package com.alinesno.infra.base.im.listener;

import com.alibaba.fastjson.JSONObject;
import org.jim.core.ImChannelContext;
import org.jim.core.exception.ImException;
import org.jim.core.packets.User;
import org.jim.server.listener.AbstractImUserListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImUserListener extends AbstractImUserListener {

    private static Logger log = LoggerFactory.getLogger(ImUserListener.class);

    @Override
    public void doAfterBind(ImChannelContext imChannelContext, User user) throws ImException {
        log.info("绑定用户:{}", JSONObject.toJSONString(user));
    }

    @Override
    public void doAfterUnbind(ImChannelContext imChannelContext, User user) throws ImException {
        log.info("解绑用户:{}",JSONObject.toJSONString(user));
    }
}
