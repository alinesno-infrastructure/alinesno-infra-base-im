package com.alinesno.infra.base.im.dto;

import lombok.Data;

/**
 * 消息实体信息
 */
@Data
public class ChatMessageDto {

    private String roleType;
    private String icon ;
    private String name;
    private String dateTime ;
    private String chatText;

    private String businessId ; // 生成的唯一业务ID标识

}
