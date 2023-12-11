//package com.alinesno.infra.base.im.adapter;
//
//import com.alinesno.infra.base.im.dto.RobotMessageDto;
//import com.alinesno.infra.base.im.dto.TaskContentDto;
//import com.alinesno.infra.common.facade.response.AjaxResult;
//import com.dtflys.forest.annotation.BaseRequest;
//import com.dtflys.forest.annotation.JSONBody;
//import com.dtflys.forest.annotation.Query;
//
//@BaseRequest(
//        baseURL = "#{alinesno.infra.gateway.host}" ,
//        connectTimeout = 30*1000
//)
//public interface SmartAssistantConsumerBak {
//
//    AjaxResult runChainAgent(@JSONBody TaskContentDto ta, @Query("roleId") String roleId);
//
//    AjaxResult sendMessageWebhook(@JSONBody RobotMessageDto dto, @JSONBody Object o);
//
//}
