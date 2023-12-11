package com.alinesno.infra.base.im.service;

import com.alinesno.infra.base.im.dto.WebMessageDto;
import com.alinesno.infra.base.im.entity.MessageEntity;
import com.alinesno.infra.common.facade.services.IBaseService;

import java.util.List;

public interface IMessageService extends IBaseService<MessageEntity> {

    /**
     * 保存用户所属频道消息
     * @param dtoList
     * @param channelId
     */
    void saveUserMessage(List<WebMessageDto> parsedMessages, Long channelId);
}
