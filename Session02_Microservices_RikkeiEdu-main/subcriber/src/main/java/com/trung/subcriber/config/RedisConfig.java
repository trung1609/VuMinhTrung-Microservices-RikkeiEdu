package com.trung.subcriber.config;

import com.trung.subcriber.service.ReceiverService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {
    // dang ky nhan tin nhan
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter adapter){
         RedisMessageListenerContainer container = new RedisMessageListenerContainer();
         container.setConnectionFactory(connectionFactory);
         // dang ky 1 kenh
        container.addMessageListener(adapter, new PatternTopic("demo_channel"));
        return container;

    }

    @Bean
    public MessageListenerAdapter adapter(ReceiverService receiverService){
        return new MessageListenerAdapter(receiverService, "receiveMessage");
    }
}
