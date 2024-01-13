package com.alinesno.infra.base.im.gateway.provider;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.im.adapter.SmartAssistantConsumer;
import com.alinesno.infra.base.im.adapter.SmartBrainConsumer;
import com.alinesno.infra.base.im.dto.*;
import com.alinesno.infra.base.im.entity.UserEntity;
import com.alinesno.infra.base.im.gateway.utils.AgentUtils;
import com.alinesno.infra.base.im.service.IChannelUserService;
import com.alinesno.infra.base.im.service.IMessageService;
import com.alinesno.infra.base.im.service.ITaskService;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.rest.SuperController;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/api/infra/base/im/chat/")
public class ImChatController extends SuperController {

    @Autowired
    private SmartAssistantConsumer smartAssistantConsumer ;

    @Autowired
    private IMessageService messageService ;

    @Autowired
    private IChannelUserService channelUserService ;

    @Autowired
    private SmartBrainConsumer smartBrainConsumer ;

    @Autowired
    private ITaskService taskService ;

    /**
     * 获取到消息信息
     * @return
     */
    @GetMapping("/chatAssistantContent")
    public AjaxResult chatAssistantContent(long businessId){

        AjaxResult result = smartAssistantConsumer.queryContent(businessId) ; //  smartBrainConsumer.chatContent(businessId) ;
        log.debug("chatContent result = {}" , result);

        return result ;
    }

    /**
     * 前端用户发送消息
     * @return
     */
    @PostMapping("/sendUserMessage")
    public AjaxResult sendUserMessage(@RequestBody List<WebMessageDto> dtoList , long channelId){

        log.debug("dtoList = {}" , JSONObject.toJSONString(dtoList));

        String text = AgentUtils.getText(dtoList)  ;
        long roleId = AgentUtils.getRoleId(dtoList) ;
        String preBusinessId = AgentUtils.getPreBusinessId(dtoList)  ;
        long businessId = IdUtil.getSnowflakeNextId(); // 生成一个唯一的业务ID

        // 提交任务给处理服务，让它后台执行处理
        taskService.addTask(channelId , businessId , roleId , text , preBusinessId);

        IndustryRoleDto roleDto = smartAssistantConsumer.getRoleById(roleId)  ;
        ChatMessageDto personDto = AgentUtils.getChatMessageDto(dtoList, roleDto , businessId , text);

        // 保存消息实体
        messageService.saveChatMessage(dtoList , personDto , channelId , businessId) ;
        return AjaxResult.success(personDto) ;
    }


    /**
     * 获取到消息信息
     * @return
     */
    @PostMapping("/updateAssistantContent")
    public AjaxResult updateAssistantContent(@RequestBody TaskContentDto dto){

        AjaxResult result = smartAssistantConsumer.modifyContent(dto) ? ok():error() ;
        log.debug("chatContent result = {}" , result);

        return result ;
    }

    /**
     * 执行下一个节点的任务
     * @return
     */
    @GetMapping("/runChainAgent")
    public AjaxResult runChainAgent(String businessId , String roleId){

        log.debug("businessId = {} , roleId = {}" , businessId , roleId);
        AjaxResult result = smartBrainConsumer.chatContent(businessId) ;

        log.debug("chatContent result = {}" , result);

        String resultData = result.get("data").toString() ;
        TaskContentDto ta = null ;
        if(resultData != null) {
            ta = JSONObject.parseObject(resultData, TaskContentDto.class);
        }

//        smartAssistantConsumer.runChainAgent(ta , roleId) ;

        return ok() ;
    }

    /**
     * 轮训任务结果，这里假设并发不高的情况下执行
     * TODO 后续再进一步优化
     * @return
     */
    @GetMapping("/getTaskNotice")
    public AjaxResult getTaskNotice(){
        return AjaxResult.success("查询成功" , taskService.getTaskMessage()) ;
    }


    /**
     * 获取到消息信息
     * @return
     */
    @GetMapping("/chatMessage")
    public AjaxResult chatMessage(String channelId){

        List<ChatMessageDto> chatMessageDtos = messageService.listByChannelId(channelId) ;

        if(chatMessageDtos == null || chatMessageDtos.isEmpty()){
            chatMessageDtos = new ArrayList<>()  ;

            // 完成之后发送消息给前端
            ChatMessageDto agentInfo = new ChatMessageDto() ;

            agentInfo.setChatText("你好，你可以查看一下使用教程<a target='_blank' href='http://portal.infra.linesno.com'>教程</a>或者@你想咨询的Agent.");
            agentInfo.setName("Agent小助理");
            agentInfo.setRoleType("agent");
            agentInfo.setReaderType("html");
            agentInfo.setBusinessId(IdUtil.getSnowflakeNextId());
            agentInfo.setDateTime(DateUtil.formatDateTime(new Date()));
            agentInfo.setIcon("http://data.linesno.com/icons/sepcialist/dataset_23.png");
            agentInfo.setDateTime(DateUtil.formatDateTime(new Date()));

            chatMessageDtos.add(agentInfo) ;
        }

        return AjaxResult.success(chatMessageDtos) ;
    }

    /**
     * 获取到频道的Agent列表
     * @return
     */
    @GetMapping("/getChannelAgent")
    public AjaxResult getChannelAgent(String channelId){

        List<UserEntity> userEntities = channelUserService.getChannelAgent(channelId) ;

        return AjaxResult.success(userEntities) ;
    }


    /**
     * 获取所有频道列表
     * @return
     */
    @GetMapping("/getAllCatalog")
    public AjaxResult getAllCatalog(){
        return smartAssistantConsumer.allCatalog() ;
    }


    /**
     * 发送消息给钉钉
     * @return
     */
    @SneakyThrows
    @GetMapping("/send")
    public AjaxResult robotSend() {

        RobotMessageDto dto = new RobotMessageDto() ;

        dto.setAtAll(false);
        dto.setMessageType("markdown");
        dto.setMessageContent("你要求生成的Java计算机考核题目已经生成，请查看连接.");

        smartAssistantConsumer.sendMessageWebhook(dto, null) ;

        return ok() ;
    }

}