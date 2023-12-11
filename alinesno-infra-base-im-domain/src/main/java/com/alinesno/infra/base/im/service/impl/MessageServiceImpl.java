package com.alinesno.infra.base.im.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.im.dto.WebMessageDto;
import com.alinesno.infra.base.im.entity.MessageEntity;
import com.alinesno.infra.base.im.enums.MessageType;
import com.alinesno.infra.base.im.mapper.MessageMapper;
import com.alinesno.infra.base.im.service.IMessageService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl extends IBaseServiceImpl<MessageEntity , MessageMapper> implements IMessageService {

    @Override
    public void saveUserMessage(List<WebMessageDto> parsedMessages, Long channelId) {

        // 处理解析后的消息对象
        StringBuilder receiverId = new StringBuilder();
        for (WebMessageDto message : parsedMessages) {
            if (MessageType.MENTION.getValue().equals(message.getType())) {
                receiverId.append(message.getId());
                receiverId.append("\\|");
            }
        }

        MessageEntity msg = new MessageEntity() ;

        msg.setMessageId(IdUtil.getSnowflakeNextId());
        msg.setChannelId(IdUtil.getSnowflakeNextId());
        msg.setSenderId(IdUtil.getSnowflakeNextId());

        msg.setReceiverId(receiverId.toString());
        msg.setContent(JSONObject.toJSONString(parsedMessages));

        this.save(msg) ;
    }
}
