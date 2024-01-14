package com.alinesno.infra.base.im.service;

import com.alinesno.infra.base.im.dto.ChatMessageDto;
import com.alinesno.infra.base.im.dto.IndustryRoleDto;
import com.alinesno.infra.base.im.dto.WebMessageDto;
import com.alinesno.infra.base.im.entity.MessageEntity;
import com.alinesno.infra.common.facade.services.IBaseService;

import java.util.List;

public interface IMessageService extends IBaseService<MessageEntity> {

    /**
     * 保存用户所属频道消息
     * @param parsedMessages
     * @param channelId
     */
    void saveUserMessage(List<WebMessageDto> parsedMessages, Long channelId);

    /**
     * 查询出频道当前所有的消息并转换返回
     * @param channelId
     * @return
     */
    List<ChatMessageDto> listByChannelId(String channelId);

    /**
     * 保存用户的返回信息
     *
     * @param dtoList
     * @param personDto
     * @param channelId
     */
    void saveChatMessage(List<WebMessageDto> dtoList, IndustryRoleDto roleDto , ChatMessageDto personDto, long channelId , long businessId);

    /**
     * 保存消息实体
     * @param personDto
     * @param channelId
     */
    void saveChatMessage(ChatMessageDto personDto, Long channelId);
}
