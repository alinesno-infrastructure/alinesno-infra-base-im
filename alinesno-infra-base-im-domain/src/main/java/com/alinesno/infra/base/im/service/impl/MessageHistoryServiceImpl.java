package com.alinesno.infra.base.im.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alinesno.infra.base.im.dto.ChatMessageDto;
import com.alinesno.infra.base.im.dto.WebMessageDto;
import com.alinesno.infra.base.im.entity.MessageEntity;
import com.alinesno.infra.base.im.entity.MessageHistoryEntity;
import com.alinesno.infra.base.im.enums.MessageType;
import com.alinesno.infra.base.im.mapper.MessageHistoryMapper;
import com.alinesno.infra.base.im.mapper.MessageMapper;
import com.alinesno.infra.base.im.service.IMessageHistoryService;
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
public class MessageHistoryServiceImpl extends IBaseServiceImpl<MessageHistoryEntity, MessageHistoryMapper> implements IMessageHistoryService {

}
