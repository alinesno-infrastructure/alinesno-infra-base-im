//package com.alinesno.infra.base.im.gateway.provider;
//
//import cn.hutool.core.util.IdUtil;
//import com.alinesno.infra.base.im.gateway.utils.SSEUtils;
//import com.alinesno.infra.common.facade.response.AjaxResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import java.util.Map;
//
///**
// * 频道SSE消息推送，主要用于任务及任务状态的推送
// */
//@RestController
//@Slf4j
//@CrossOrigin
//@RequestMapping(value = "/v1/api/infra/base/im/sseChannelMessage")
//public class SSEChannelMessageController {
//
//    private static final String CHANNEL_MESSAGE_PRE = "message_" ;
//
//    @Autowired
//    private SSEUtils sseUtils;
//
//
//    @GetMapping(value = "/createSseConnect", produces="text/event-stream;charset=UTF-8")
//    public SseEmitter createSseConnect(@RequestParam(name = "channel", required = false) Long channel) {
//        log.debug("连接频道:{}" , channel);
//        return sseUtils.connect(CHANNEL_MESSAGE_PRE + channel);
//    }
//
//    @PostMapping("/sendSseMessage")
//    public void sendSseMessage(@RequestParam("channel") Long channel, @RequestParam("message")  String message){
//
//        String messageId = IdUtil.getSnowflakeNextIdStr() ;
//        boolean isSend =  sseUtils.sendSseMessage(CHANNEL_MESSAGE_PRE + channel, messageId , message);
//
//        log.debug("is Send message = {}" , isSend);
//    }
//
//    @GetMapping(value = "/listSseConnect")
//    public AjaxResult listSseConnect(){
//        Map<String, SseEmitter> sseEmitterMap = sseUtils.listSseConnect();
//        return AjaxResult.success(sseEmitterMap);
//    }
//
//    /**
//     * 关闭SSE连接
//     *
//     * @param channel 客户端ID
//     **/
//    @GetMapping("/closeSseConnect")
//    public AjaxResult closeSseConnect(Long channel) {
//
//        log.debug("closeSseConnect = {}" , channel);
//
//        sseUtils.deleteChannel( CHANNEL_MESSAGE_PRE + channel);
//        return AjaxResult.success();
//    }
//
//}