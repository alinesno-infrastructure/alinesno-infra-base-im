package com.alinesno.infra.base.im.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.alinesno.infra.base.im.adapter.SmartAssistantConsumer;
import com.alinesno.infra.base.im.dto.AssistantPromptDto;
import com.alinesno.infra.base.im.dto.ChatMessageDto;
import com.alinesno.infra.base.im.dto.IndustryRoleDto;
import com.alinesno.infra.base.im.dto.MessageQueueDto;
import com.alinesno.infra.base.im.entity.MessageEntity;
import com.alinesno.infra.base.im.service.IMessageService;
import com.alinesno.infra.base.im.service.ITaskService;
import com.alinesno.infra.common.facade.response.AjaxResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class TaskServiceImpl implements ITaskService {

    private static final Map<Long , TaskInfo> taskBox = new HashMap<>() ;

    @Autowired
    private IMessageService messageService ;

    @Autowired
    private SmartAssistantConsumer assistantConsumer ;

    @Override
    public void addTask(long channelId, long businessId , long roleId, String text, String preBusinessId , IndustryRoleDto roleDto ) {
        log.debug("添加任务实例:messageId = {} , businessId = {}" , channelId , businessId);

        TaskInfo taskInfo = new TaskInfo() ;

        taskInfo.setText(text);
        taskInfo.setBusinessId(businessId) ;
        taskInfo.setChannelId(channelId);
        taskInfo.setPreBusinessId(preBusinessId);
        taskInfo.setRoleDto(roleDto);

        taskBox.put(businessId , taskInfo) ;

        // 获取到上一条消息的业务ID
        String assistantContent = null ;

        if(StringUtils.isNotBlank(preBusinessId)){
            AjaxResult result = assistantConsumer.queryContent(Long.parseLong(preBusinessId)) ;
            MessageQueueDto messageQueueDto = JSONObject.parseObject(result.get("data")+"" , MessageQueueDto.class) ;

            assistantContent = messageQueueDto.getAssistantContent() ;
        }

        Map<String , Object> params = new HashMap<>() ;
        params.put("label1" , assistantContent == null ? text : assistantContent) ;
        params.put("require" , text) ;  // 个人所提要求

        AssistantPromptDto dto = new AssistantPromptDto() ;

        dto.setAgentId(roleId);
        dto.setChannelId(channelId);
        dto.setBusinessId(businessId);
        dto.setContent(JSONObject.toJSONString(params));
        dto.setAssistantContent(assistantContent);

        assistantConsumer.runChainAgent(dto) ;
    }

    @Override
    public List<ChatMessageDto> getTaskMessage() {

        log.debug("查询任务实例信息.");

        List<ChatMessageDto> messageList = new ArrayList<>() ;
        List<MessageEntity> messageEntities = new ArrayList<>() ;

        if(!taskBox.isEmpty()){

            Iterator<Map.Entry<Long, TaskInfo>> iterator = taskBox.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Long, TaskInfo> entry = iterator.next();

                long businessId = entry.getKey() ;
                TaskInfo taskInfo = entry.getValue() ;

                long channelId = taskInfo.getChannelId() ;
                long agentId = taskInfo.getRoleDto().getId() ;
                String agentName = taskInfo.getRoleDto().getRoleName() ;
                String agentIcon = taskInfo.getRoleDto().getRoleAvatar() ;

                AjaxResult result = assistantConsumer.queryContent(businessId) ;
                if(result.get("data") != null){
                    MessageQueueDto messageQueueDto = JSONObject.parseObject(result.get("data")+"" , MessageQueueDto.class) ;

                    log.debug("messageQueueDto = {}" , messageQueueDto);

                    if(messageQueueDto.getStatus().equals("success")){  // 构建成功.

                        ChatMessageDto dto3 = new ChatMessageDto() ;
                        MessageEntity entity = new MessageEntity() ;

                        String md = "### 任务已经处理\n" +
                                "- 业务标识: "+businessId+"\n" +
                                "- 持续时间: 46秒503\n" +
                                "- 状态: 完成\n" +
                                "- 完成时间: 2023-12-10 01:31:34\n" ;

                        dto3.setChatText(md);

                        dto3.setName(agentName);
                        dto3.setIcon(agentIcon) ;

                        dto3.setChannelId(channelId);
                        dto3.setRoleType("agent");
                        dto3.setBusinessId(businessId);
                        dto3.setDateTime(DateUtil.formatDateTime(new Date()));

                        messageList.add(dto3) ;

                        entity.setContent(dto3.getChatText().toString()) ;

                        entity.setName(agentName);
                        entity.setIcon(agentIcon) ;

                        entity.setRoleType(dto3.getRoleType());
                        entity.setReaderType(dto3.getReaderType());
                        entity.setBusinessId(businessId);
                        entity.setAddTime(new Date()) ;
                        entity.setMessageId(IdUtil.getSnowflakeNextId());

                        entity.setChannelId(channelId);
                        entity.setSenderId(agentId) ;
                        entity.setReceiverId(IdUtil.getSnowflakeNextIdStr());

                        messageEntities.add(entity) ;

                        log.debug("完成任务实例:businessId = {}" , businessId);

                        iterator.remove(); // 使用Iterator的remove方法来删除元素
                    }
                }

            }

            if(!messageEntities.isEmpty()){
                messageService.saveBatch(messageEntities) ;
            }
        }

        return messageList ;

    }

    @Data
    private static class TaskInfo {
        private long channelId ;
        private long businessId ;
        private long roleId ;
        private String text ;
        private String preBusinessId ;
        private IndustryRoleDto roleDto  ;
    }
}
