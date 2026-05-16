package re.shipservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient // cach cũ ko chỉ áp dụng cho eureka
@EnableFeignClients  // spring quét tất cả các class được đánh dấu là OpenFein và tạo bean
public class ShipServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipServiceApplication.class, args);
    }
    @Bean
    @LoadBalanced // cân bằng tải
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
