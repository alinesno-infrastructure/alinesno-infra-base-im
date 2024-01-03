package com.alinesno.infra.base.im.gateway.controller;

import com.alibaba.fastjson.JSONArray;
import com.alinesno.infra.base.im.adapter.SmartAssistantConsumer;
import com.alinesno.infra.base.im.dto.IndustryRoleDto;
import com.alinesno.infra.base.im.entity.ChannelUserEntity;
import com.alinesno.infra.base.im.service.IChannelUserService;
import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.rest.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 处理与BusinessLogEntity相关的请求的Controller。
 * 继承自BaseController类并实现IBusinessLogService接口。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@Slf4j
@RestController
@Scope(SpringInstanceScope.PROTOTYPE)
@RequestMapping("/api/infra/base/im/channelUser")
public class ChannelUserController extends BaseController<ChannelUserEntity, IChannelUserService> {

    @Autowired
    private IChannelUserService service;

    @Autowired
    private SmartAssistantConsumer smartAssistantConsumer ;

    /**
     * 获取BusinessLogEntity的DataTables数据。
     *
     * @param request HttpServletRequest对象。
     * @param model Model对象。
     * @param page DatatablesPageBean对象。
     * @return 包含DataTables数据的TableDataInfo对象。
     */
    @ResponseBody
    @PostMapping("/datatables")
    public TableDataInfo datatables(HttpServletRequest request, Model model, DatatablesPageBean page) {
        log.debug("page = {}", ToStringBuilder.reflectionToString(page));

        TableDataInfo tableDataInfo = this.toPage(model, this.getFeign(), page);

        AjaxResult result = smartAssistantConsumer.getAgentList() ;
        List<IndustryRoleDto> list = JSONArray.parseArray(result.get("data")+"" , IndustryRoleDto.class);
        tableDataInfo.setRows(list);

        return tableDataInfo ;
    }

    @Override
    public IChannelUserService getFeign() {
        return this.service;
    }
}

