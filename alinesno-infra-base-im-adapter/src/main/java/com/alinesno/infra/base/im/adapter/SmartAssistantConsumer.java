package com.alinesno.infra.base.im.adapter;

import com.alinesno.infra.base.im.dto.RobotMessageDto;
import com.alinesno.infra.base.im.dto.TaskContentDto;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Query;

@BaseRequest(baseURL = "#{alinesno.infra.gateway.host}/smart-assistant" , connectTimeout = 30*1000)
public interface SmartAssistantConsumer {

    AjaxResult runChainAgent(@JSONBody TaskContentDto ta, @Query("roleId") String roleId) ;

    AjaxResult sendMessageWebhook(@JSONBody RobotMessageDto dto, @JSONBody Object o) ;

    /**
     * 获取到Agent列表
     * @return
     */
    @Get("/api/infra/smart/assistant/agent/list")
    AjaxResult getAgentList()  ;
}
