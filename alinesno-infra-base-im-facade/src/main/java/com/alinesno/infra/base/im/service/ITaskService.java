package com.alinesno.infra.base.im.service;

import com.alinesno.infra.base.im.dto.ChatMessageDto;

import java.util.List;

/**
 * 提交任务的服务
 */
public interface ITaskService {

    /**
     * 保存任务
     */
    void addTask(long channelId , String businessId , long roleId , String text, String preBusinessId) ;

    /**
     * 获取已经完成的任务通知
     * @return
     */
    List<ChatMessageDto> getTaskMessage() ;

}
