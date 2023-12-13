package com.alinesno.infra.base.im.gateway.provider;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alinesno.infra.base.im.dto.ChatMessageDto;
import com.alinesno.infra.base.im.service.IMessageService;
import com.alinesno.infra.base.im.service.ITaskService;
import com.alinesno.infra.common.facade.response.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 文档控制层
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/api/infra/base/im/knowledge/")
public class KnowledgeController {

    @Autowired
    private ITaskService taskService ;

    @Autowired
    private IMessageService messageService ;

    /**
     * 导入数据
     *
     * @param file          导入文件
     */
    @PostMapping(value = "/importData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxResult importData(@RequestPart("file") MultipartFile file, Long channelId) throws Exception {

        // 获取原始文件名
        String fileName = file.getOriginalFilename();

        // 完成之后发送消息给前端
        ChatMessageDto personDto = new ChatMessageDto() ;

        personDto.setChatText("<div style='color: #409EFF;padding: 3px;'><i class='fa-solid fa-file-word icon-word'></i> "+fileName+" </div> 已上传文件成功.");
        personDto.setName("考核题目生成Agent");
        personDto.setRoleType("person");
        personDto.setReaderType("html");
        personDto.setBusinessId(IdUtil.getSnowflakeNextIdStr());
        personDto.setDateTime(DateUtil.formatDateTime(new Date()));
        personDto.setIcon("https://foruda.gitee.com/avatar/1676897721015308137/41655_landonniao_1656075872.png");
        personDto.setDateTime(DateUtil.formatDateTime(new Date()));

        // 保存消息实体
        messageService.saveChatMessage(personDto , channelId) ;

        return AjaxResult.success(personDto) ;
    }

}
