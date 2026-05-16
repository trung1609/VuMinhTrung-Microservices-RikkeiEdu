package com.trung.redis.config;

import com.trung.redis.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Product> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Product> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        JacksonJsonRedisSerializer<Product> serializer =
                new JacksonJsonRedisSerializer<>(Product.class);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();

        //otp
        //token: accessToken, refreshToken, blackListToken



        return template;
    }
}
