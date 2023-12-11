package com.alinesno.infra.base.im.service;

import com.alinesno.infra.base.im.entity.ChannelEntity;
import com.alinesno.infra.common.facade.services.IBaseService;

public interface IChannelService extends IBaseService<ChannelEntity> {

    /**
     * 保存和创建Channel
     *
     * @param entity
     * @return 最新保存的ID
     */
    String createChannel(ChannelEntity entity);

}
