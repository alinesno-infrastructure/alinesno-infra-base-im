package com.alinesno.infra.base.im.service.impl;

import com.alinesno.infra.base.im.entity.ChannelUserEntity;
import com.alinesno.infra.base.im.entity.MessageEntity;
import com.alinesno.infra.base.im.mapper.ChannelUserMapper;
import com.alinesno.infra.base.im.mapper.MessageMapper;
import com.alinesno.infra.base.im.service.IChannelUserService;
import com.alinesno.infra.base.im.service.IMessageService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChannelUserServiceImpl extends IBaseServiceImpl<ChannelUserEntity, ChannelUserMapper> implements IChannelUserService {
}
