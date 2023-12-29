package com.alinesno.infra.base.im.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alinesno.infra.base.im.adapter.SmartAssistantConsumer;
import com.alinesno.infra.base.im.dto.IndustryRoleDto;
import com.alinesno.infra.base.im.entity.ChannelUserEntity;
import com.alinesno.infra.base.im.entity.UserEntity;
import com.alinesno.infra.base.im.mapper.ChannelUserMapper;
import com.alinesno.infra.base.im.service.IChannelUserService;
import com.alinesno.infra.common.core.service.impl.IBaseServiceImpl;
import com.alinesno.infra.common.facade.response.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ChannelUserServiceImpl extends IBaseServiceImpl<ChannelUserEntity, ChannelUserMapper> implements IChannelUserService {

    @Autowired
    private SmartAssistantConsumer smartAssistantConsumer ;

    @Override
    public List<UserEntity> getChannelAgent(String channelId) {

        AjaxResult result = smartAssistantConsumer.getAgentList() ;
        List<IndustryRoleDto> list = JSONArray.parseArray(result.get("data")+"" , IndustryRoleDto.class);

        List<UserEntity> userList = new ArrayList<>() ;

        for(IndustryRoleDto dto : list){

            UserEntity e =  new UserEntity() ;
            BeanUtils.copyProperties(dto, e);

            userList.add(e) ;
        }

        return userList ;
    }
}
