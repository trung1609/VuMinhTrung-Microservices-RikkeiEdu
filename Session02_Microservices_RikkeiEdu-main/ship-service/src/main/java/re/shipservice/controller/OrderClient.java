package re.shipservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "order-service-eureka")
public interface OrderClient {
    @GetMapping("/api/v1/orders/{id}")
    Orders getOrderById(@PathVariable("id") String id);
}
