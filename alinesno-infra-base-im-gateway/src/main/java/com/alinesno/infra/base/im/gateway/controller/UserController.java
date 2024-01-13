package com.alinesno.infra.base.im.gateway.controller;

import com.alinesno.infra.base.im.adapter.SmartAssistantConsumer;
import com.alinesno.infra.base.im.entity.ChannelUserEntity;
import com.alinesno.infra.base.im.entity.UserEntity;
import com.alinesno.infra.base.im.service.IChannelUserService;
import com.alinesno.infra.base.im.service.IUserService;
import com.alinesno.infra.common.core.constants.SpringInstanceScope;
import com.alinesno.infra.common.facade.pageable.DatatablesPageBean;
import com.alinesno.infra.common.facade.pageable.TableDataInfo;
import com.alinesno.infra.common.facade.response.AjaxResult;
import com.alinesno.infra.common.web.adapter.rest.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/infra/base/im/user")
public class UserController extends BaseController<UserEntity, IUserService> {

    @Autowired
    private IUserService service;

    @Autowired
    private IChannelUserService channelUserService ;

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
        return smartAssistantConsumer.getAgentList(page.getPageNum() , page.getPageSize() , "");
    }

    /**
     * 添加用户到当前的频道当中
     * @return
     */
    @GetMapping("/addChainAgent")
    public AjaxResult addChainAgent(Long channelId , Long roleId){

        Assert.notNull(channelId , "频道为空");
        Assert.notNull(roleId, "角色为空");

        LambdaQueryWrapper<ChannelUserEntity> wrapper = new LambdaQueryWrapper<>() ;
        wrapper.eq(ChannelUserEntity::getChannelId , channelId)
                .eq(ChannelUserEntity::getAccountType, roleId) ;

        long count = channelUserService.count(wrapper) ;
        Assert.isTrue(count == 0 , "角色已经在频道里面");

        ChannelUserEntity channelUser = new ChannelUserEntity() ;

        channelUser.setAccountType("agent");
        channelUser.setChannelId(channelId);
        channelUser.setAccountId(roleId);

        channelUserService.save(channelUser) ;

        return AjaxResult.success() ;
    }

    /**
     * 运行角色流程
     * @return
     */
    @GetMapping("/listAllUser")
    public AjaxResult listAllUser(String channelId){

        List<UserEntity> roleEntityList = channelUserService.getChannelAgent(channelId)  ;

        return AjaxResult.success(roleEntityList) ;
    }

    @Override
    public IUserService getFeign() {
        return this.service;
    }
}

