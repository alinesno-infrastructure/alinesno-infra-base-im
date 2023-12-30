package com.alinesno.infra.base.im.adapter;

import com.alinesno.infra.base.im.dto.AssistantPromptDto;
import com.alinesno.infra.base.im.dto.IndustryRoleDto;
import com.alinesno.infra.base.im.dto.RobotMessageDto;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.dtflys.forest.annotation.*;

@BaseRequest(baseURL = "#{alinesno.infra.gateway.host}/smart-assistant" , connectTimeout = 30*1000)
public interface SmartAssistantConsumer {

    /**
     * 运行执行任务
     * @return
     */
    @Post("/api/infra/smart/assistant/message/addMessage")
    AjaxResult runChainAgent(@JSONBody AssistantPromptDto dto) ;

    /**
     * 获取到Agent列表
     * @return
     */
    @Get("/api/infra/smart/assistant/agent/list")
    AjaxResult getAgentList()  ;

    /**
     * 获取到详细信息
     * @param roleId
     * @return
     */
    @Get("/api/infra/smart/assistant/agent/getById")
    IndustryRoleDto getRoleById(@Query("roleId") long roleId);

    /**
     * 获取到生成的内容结果
     * @param businessId
     */
    @Post("/api/infra/smart/assistant/message/queryMessage")
    AjaxResult queryContent(@Query("businessId") long businessId);

    /**
     * 发送第三方IM消息
     * @param dto
     * @param o
     * @return
     */
    AjaxResult sendMessageWebhook(@JSONBody RobotMessageDto dto, @JSONBody Object o) ;
}
