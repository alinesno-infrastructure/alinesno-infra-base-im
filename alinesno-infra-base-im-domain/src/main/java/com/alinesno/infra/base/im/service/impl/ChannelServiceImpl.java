package com.alinesno.infra.base.im.service.impl;

import com.alinesno.infra.base.im.entity.ChannelEntity;
import com.alinesno.infra.base.im.mapper.ChannelMapper;
import com.alinesno.infra.base.im.service.IChannelService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChannelServiceImpl extends IBaseServiceImpl<ChannelEntity, ChannelMapper> implements IChannelService {
}
