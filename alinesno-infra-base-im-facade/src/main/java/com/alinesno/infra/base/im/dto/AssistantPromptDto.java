package com.alinesno.infra.base.im.dto;

import lombok.Data;

/**
 * 发送Agent角色消息内容
 */
@Data
public class AssistantPromptDto {

    private String businessId;
    private long agentId;
    private String content;
    private long channelId ;

}
