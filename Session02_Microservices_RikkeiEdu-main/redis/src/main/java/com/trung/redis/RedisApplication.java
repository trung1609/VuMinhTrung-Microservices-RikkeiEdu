package com.trung.redis;

import com.trung.redis.entity.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Cacheable
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(RedisTemplate<String, Product> redisTemplate) {
        return args -> {
//            String key = "user_session_01";
//
//            // 1. Kiểm tra Key có tồn tại không
//            Boolean exists = redisTemplate.hasKey(key);
//            System.out.println("Tồn tại: " + exists);
//
//            if (Boolean.TRUE.equals(exists)) {
//
//                // 2. Kiểm tra kiểu dữ liệu của Key (String, List, Hash, Set...)
//                System.out.println("Kiểu dữ liệu: " + redisTemplate.type(key));
//
//                // 3. Kiểm tra thời gian còn lại (TTL - Time To Live)
//                // Trả về số giây còn lại. -1 là vĩnh viễn, -2 là key không tồn tại.
//                Long expire = redisTemplate.getExpire(key);
//                System.out.println("Thời gian còn lại (giây): " + expire);
//
//                // Có thể lấy theo đơn vị cụ thể (phút, giờ...)
//                Long expireInMinutes = redisTemplate.getExpire(key, TimeUnit.MINUTES);
//                System.out.println("Thời gian còn lại (phút): " + expireInMinutes);
//
//                // 4. Lấy giá trị (nếu là kiểu String/Value)
//                Object val = redisTemplate.opsForValue().get(key);
//                System.out.println("Giá trị: " + val);
//
//            } else {
//                // 5. Nếu không tồn tại, thử Set mới kèm TTL
//                redisTemplate.opsForValue().set(key, "Active", Duration.ofMinutes(10));
//                System.out.println("Đã tạo mới key với thời gian sống 10 phút.");
//            }
//
//            // 6. Đổi tên Key (Rename)
//            // redisTemplate.rename("old_key", "new_key");
//
//            // 7. Xóa Key
//            // redisTemplate.delete(key);
//
//            redisTemplate.opsForHash().put("product1", "id", "P001");
//            redisTemplate.opsForHash().put("product1", "name", "Product 1");
//            redisTemplate.opsForHash().put("product1", "price", "100.00");
//
//            redisTemplate.opsForHash().get("product1", "id");
//
//            Set<String> keys = redisTemplate.keys("product1");
//            List<Object> entry = redisTemplate.opsForHash().values("product1");

            redisTemplate.convertAndSend("demo_channel", "Test Channel");
        };
    }
}
