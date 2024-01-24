package com.alinesno.infra.base.im.gateway.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 监听远行的消息和任务
 */
@Slf4j
@Component
public class ListenerStreamMessage implements StreamListener<String, MapRecord<String, String, String>> {

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Autowired
    private MessageEvent eventMessage ;

    @Override
    public void onMessage(MapRecord<String, String, String> entries) {

        log.info(">>>>>>>>>>>>>>>>>>>>>> 接受到来自redis stream 的消息");

        log.debug("message id = {}" , entries.getId());
        log.debug("stream =  {}" , entries.getStream());
        log.debug("body = {}" ,entries.getValue().get("payload"));

        // 处理任务消息
        if(entries.getStream().equals(StreamConstants.IM_TASK_LOG)){
            eventMessage.handleTaskMessage(entries) ;
        }else if(entries.getStream().equals(StreamConstants.IM_MESSAGE_LOG)){  // 处理业务返回消息
            eventMessage.handleResultMessage(entries) ;
        }


        redisTemplate.opsForStream().delete(StreamConstants.STREAM_NAME ,  entries.getId().getValue());
    }

}
