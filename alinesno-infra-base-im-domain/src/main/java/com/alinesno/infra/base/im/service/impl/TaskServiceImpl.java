package com.alinesno.infra.base.im.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alinesno.infra.base.im.dto.ChatMessageDto;
import com.alinesno.infra.base.im.entity.MessageEntity;
import com.alinesno.infra.base.im.service.IMessageService;
import com.alinesno.infra.base.im.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class TaskServiceImpl implements ITaskService {

    private static final Map<String , Long> taskBox = new HashMap<>() ;

    @Autowired
    private IMessageService messageService ;

    @Override
    public void addTask(long channelId, String businessId) {
        log.debug("添加任务实例:messageId = {} , businessId = {}" , channelId , businessId);
        taskBox.put(businessId , channelId) ;
    }

    @Override
    public List<ChatMessageDto> getTaskMessage() {

        log.debug("查询任务实例信息.");

        List<ChatMessageDto> messageList = new ArrayList<>() ;
        List<MessageEntity> messageEntities = new ArrayList<>() ;

        if(!taskBox.isEmpty()){

            Iterator<Map.Entry<String, Long>> iterator = taskBox.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Long> entry = iterator.next();

                String businessId = entry.getKey() ;
                Long channelId = entry.getValue() ;

                ChatMessageDto dto3 = new ChatMessageDto() ;
                MessageEntity entity = new MessageEntity() ;

                String md = "### 罗小东的任务已经处理\n" +
                        "- 任务: \n" + "请编写关于Ansible的考核题目." + "\n" +
                        "- 业务标识: 1733539703232249856\n" +
                        "- 持续时间: 46秒503\n" +
                        "- 环境: [测试环境](#)\n" +
                        "- 内容: [查看生成结果](http://localhost/smart/specialist/index?businessId=1733539703232249856)\n" +
                        "- 状态: 完成\n" +
                        "- 完成时间: 2023-12-10 01:31:34\n" +
                        "- 执行人：培训题设计Agent" ;

                dto3.setChatText(md);
                dto3.setName("高级数据库工程师");
                dto3.setChannelId(channelId);
                dto3.setRoleType("agent");
                dto3.setBusinessId(IdUtil.getSnowflakeNextIdStr());
                dto3.setIcon("http://data.linesno.com/icons/sepcialist/dataset_23.png");
                dto3.setDateTime(DateUtil.formatDateTime(new Date()));

                messageList.add(dto3) ;

                entity.setContent(dto3.getChatText().toString()) ;
                entity.setName(dto3.getName());
                entity.setRoleType(dto3.getRoleType());
                entity.setReaderType(dto3.getReaderType());
                entity.setBusinessId(IdUtil.getSnowflakeNextIdStr());
                entity.setAddTime(new Date()) ;
                entity.setIcon("http://data.linesno.com/icons/sepcialist/dataset_23.png");
                entity.setMessageId(IdUtil.getSnowflakeNextId());

                entity.setMessageId(IdUtil.getSnowflakeNextId());
                entity.setChannelId(channelId);
                entity.setSenderId(IdUtil.getSnowflakeNextId());
                entity.setReceiverId(IdUtil.getSnowflakeNextIdStr());

                messageEntities.add(entity) ;

                log.debug("完成任务实例:businessId = {}" , businessId);

                iterator.remove(); // 使用Iterator的remove方法来删除元素
            }

            messageService.saveBatch(messageEntities) ;
        }

        return messageList ;

    }
}
