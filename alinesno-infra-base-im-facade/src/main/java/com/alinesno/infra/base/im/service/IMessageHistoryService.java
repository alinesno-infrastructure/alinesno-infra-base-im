package com.alinesno.infra.base.im.service;

import com.alinesno.infra.base.im.dto.ChatMessageDto;
import com.alinesno.infra.base.im.dto.WebMessageDto;
import com.alinesno.infra.base.im.entity.MessageEntity;
import com.alinesno.infra.base.im.entity.MessageHistoryEntity;
import com.alinesno.infra.common.facade.services.IBaseService;

import java.util.List;

public interface IMessageHistoryService extends IBaseService<MessageHistoryEntity> {

}
