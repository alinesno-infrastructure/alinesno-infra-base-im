package com.alinesno.infra.base.im.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alinesno.infra.base.im.dto.ChatMessageDto;
import com.alinesno.infra.base.im.dto.WebMessageDto;
import com.alinesno.infra.base.im.entity.MessageEntity;
import com.alinesno.infra.base.im.enums.MessageType;
import com.alinesno.infra.base.im.mapper.MessageMapper;
import com.alinesno.infra.base.im.service.IMessageService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        StringBuilder chatTextBuilder = new StringBuilder();

        // 假设Content是一个自定义类，包含type、text、username和businessId属性
        for (WebMessageDto content : parsedMessages) {
            if ("text".equals(content.getType())) {
                chatTextBuilder.append("<span class=\"mention-text\">").append(content.getText()).append("</span>");
            } else if ("mention".equals(content.getType())) {
                chatTextBuilder.append("<span class=\"mention\">@").append(content.getUsername()).append("</span>");
            } else if ("business".equals(content.getType())) {
                chatTextBuilder.append("<span class=\"mention-business\">#").append(content.getBusinessId()).append("</span>");
            }
        }
        String chatText = chatTextBuilder.toString();

        MessageEntity msg = new MessageEntity() ;

        msg.setMessageId(IdUtil.getSnowflakeNextId());
        msg.setChannelId(channelId);
        msg.setSenderId(IdUtil.getSnowflakeNextId());

        msg.setReaderType("html");
        msg.setRoleType("person");
        msg.setName("软件工程师罗小东");
        msg.setReceiverId(receiverId.toString());
        msg.setContent(chatText)  ; // JSONObject.toJSONString(parsedMessages));

        this.save(msg) ;
    }

    @Override
    public List<ChatMessageDto> listByChannelId(String channelId) {

        List<ChatMessageDto> list = new ArrayList<>() ;

        LambdaQueryWrapper<MessageEntity> wrapper = new LambdaQueryWrapper<>() ;
        wrapper.eq(MessageEntity::getChannelId , channelId)
                .orderByAsc(MessageEntity::getAddTime) ;

        List<MessageEntity> entityList = list(wrapper) ;

        if(!entityList.isEmpty()){
            for(MessageEntity e : entityList){
                ChatMessageDto dto = new ChatMessageDto() ;

                dto.setChatText(e.getContent());

                dto.setName(e.getName());
                dto.setRoleType(e.getRoleType());
                dto.setReaderType(e.getReaderType());
                dto.setBusinessId(e.getBusinessId());
                dto.setIcon(e.getIcon());
                dto.setDateTime(DateUtil.formatDateTime(e.getAddTime()));
                dto.setBusinessId(IdUtil.getSnowflakeNextIdStr());

                list.add(dto) ;
            }
        }

        return list ;
    }

    @Override
    public void saveChatMessage(List<WebMessageDto> parsedMessages, ChatMessageDto personDto, Long channelId) {
        // 处理解析后的消息对象
        StringBuilder receiverId = new StringBuilder();
        for (WebMessageDto message : parsedMessages) {
            if (MessageType.MENTION.getValue().equals(message.getType())) {
                receiverId.append(message.getId());
                receiverId.append("\\|");
            }
        }
        StringBuilder chatTextBuilder = new StringBuilder();

        // 假设Content是一个自定义类，包含type、text、username和businessId属性
        for (WebMessageDto content : parsedMessages) {
            if ("text".equals(content.getType())) {
                chatTextBuilder.append("<span class=\"mention-text\">").append(content.getText()).append("</span>");
            } else if ("mention".equals(content.getType())) {
                chatTextBuilder.append("<span class=\"mention\">@").append(content.getUsername()).append("</span>");
            } else if ("business".equals(content.getType())) {
                chatTextBuilder.append("<span class=\"mention-business\">#").append(content.getBusinessId()).append("</span>");
            }
        }
        MessageEntity msg = getMessageEntity(channelId, chatTextBuilder, receiverId);

        save(msg) ;


        MessageEntity entity = new MessageEntity() ;

        entity.setContent(personDto.getChatText().toString()) ;
        entity.setName(personDto.getName());
        entity.setRoleType(personDto.getRoleType());
        entity.setReaderType(personDto.getReaderType());
        entity.setBusinessId(IdUtil.getSnowflakeNextIdStr());
        entity.setAddTime(new Date()) ;
        entity.setIcon("http://data.linesno.com/icons/sepcialist/dataset_23.png");
        entity.setMessageId(IdUtil.getSnowflakeNextId());

        entity.setMessageId(IdUtil.getSnowflakeNextId());
        entity.setChannelId(channelId);
        entity.setSenderId(IdUtil.getSnowflakeNextId());
        entity.setReceiverId(IdUtil.getSnowflakeNextIdStr());

        save(entity) ;
    }

    private static MessageEntity getMessageEntity(Long channelId, StringBuilder chatTextBuilder, StringBuilder receiverId) {
        String chatText = chatTextBuilder.toString();

        MessageEntity msg = new MessageEntity() ;

        msg.setMessageId(IdUtil.getSnowflakeNextId());
        msg.setChannelId(channelId);
        msg.setSenderId(IdUtil.getSnowflakeNextId());

        msg.setIcon("https://foruda.gitee.com/avatar/1676897721015308137/41655_landonniao_1656075872.png");
        msg.setReaderType("html");
        msg.setRoleType("person");
        msg.setAddTime(new Date());
        msg.setName("软件工程师罗小东");
        msg.setReceiverId(receiverId.toString());
        msg.setContent(chatText)  ; // JSONObject.toJSONString(parsedMessages));
        return msg;
    }
}
