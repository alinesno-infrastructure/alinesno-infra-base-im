//package com.alinesno.infra.base.im.gateway.stream;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.stream.Consumer;
//import org.springframework.data.redis.connection.stream.ReadOffset;
//import org.springframework.data.redis.connection.stream.StreamOffset;
//import org.springframework.data.redis.stream.StreamMessageListenerContainer;
//import org.springframework.data.redis.stream.Subscription;
//
//import java.time.Duration;
//
///**
// * 消息监听类
// */
//@Slf4j
//@Configuration
//public class RedisStreamConfig {
//
//    @Autowired
//    private ListenerStreamMessage streamListener;
//
//    @Bean
//    public Subscription subscription(RedisConnectionFactory factory){
//
//        var options = StreamMessageListenerContainer
//                .StreamMessageListenerContainerOptions
//                .builder()
//                .pollTimeout(Duration.ofSeconds(1))
//                .build();
//
//        var listenerContainer = StreamMessageListenerContainer.create(factory,options);
//
//        var subscription = listenerContainer.receiveAutoAck(
//                Consumer.from("infra_im" , "im_message_stream"),
//                StreamOffset.create(StreamConstants.STREAM_NAME , ReadOffset.lastConsumed()),
//                streamListener);
//
//        listenerContainer.start();
//        return subscription;
//    }
//
//}
