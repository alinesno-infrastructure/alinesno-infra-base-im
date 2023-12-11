package com.alinesno.infra.base.im.adapter;

import com.alinesno.infra.base.im.dto.RobotMessageDto;
import com.alinesno.infra.base.im.dto.TaskContentDto;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Query;
import org.springframework.stereotype.Service;

@Service
public class SmartAssistantConsumer {

    public AjaxResult runChainAgent(@JSONBody TaskContentDto ta, @Query("roleId") String roleId){
        return AjaxResult.success() ;
    }

    public AjaxResult sendMessageWebhook(@JSONBody RobotMessageDto dto, @JSONBody Object o){
        return AjaxResult.success() ;
    }

}
