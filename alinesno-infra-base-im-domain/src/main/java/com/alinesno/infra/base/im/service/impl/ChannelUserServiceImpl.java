package com.alinesno.infra.base.im.service.impl;

import com.alinesno.infra.base.im.adapter.SmartAssistantConsumer;
import com.alinesno.infra.base.im.dto.IndustryRoleDto;
import com.alinesno.infra.base.im.entity.ChannelUserEntity;
import com.alinesno.infra.base.im.mapper.ChannelUserMapper;
import com.alinesno.infra.base.im.service.IChannelUserService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChannelUserServiceImpl extends IBaseServiceImpl<ChannelUserEntity, ChannelUserMapper> implements IChannelUserService {

    @Autowired
    private SmartAssistantConsumer smartAssistantConsumer ;

    @Override
    public List<IndustryRoleDto> getChannelAgent(String channelId) {

        List<IndustryRoleDto> list = smartAssistantConsumer.getAgentList() ;

        return list ;
    }
}
