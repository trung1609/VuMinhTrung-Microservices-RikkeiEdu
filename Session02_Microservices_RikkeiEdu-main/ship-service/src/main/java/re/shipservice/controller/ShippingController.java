package re.shipservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.json.GsonDecoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/ships")
@RequiredArgsConstructor
@RefreshScope
public class ShippingController {
    @Value("${test.username}")
    private String username;
    private final OrderClient orderClient;
    private final RestTemplate restTemplate;
    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody OrderCreateDto request)  {
        // chuyển tiếp các thông tin từ request sang API của ordervice và nhận kết quả trả về\
        String url = "http://order-service-eureka/api/v1/orders";
        Orders res =  restTemplate.postForObject(url,request, Orders.class);
        // GET DELETE gửi tham số trên đường dẫn
        // POST PUT PATCH gửi tham số vào body
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    // Giao hàng thì cần thông tin chi tiết của hóa đơn theo orderid
    @GetMapping("/show-order/{orderId}")
    public Orders showOrder(@PathVariable("orderId") String orderId){
        return orderClient.getOrderById(orderId);
    }
    @GetMapping("/test")
    public Map<String, String> test(){
        return Map.of("username",username);
    }
}
