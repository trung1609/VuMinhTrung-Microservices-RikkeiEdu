package ra.boot3resilient4j;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/ships")
@RequiredArgsConstructor
public class ShipController {
    private final ShipService shipService;

    @GetMapping("/pay")
    public CompletableFuture<String> pay() {
        // gọi sang service để thực hiện check thanh toán
        return shipService.callRemote();
    }
    @GetMapping("/breaker")
    public String testBreaker(@RequestParam("isError") Boolean isError) {
        // gọi sang service để thực hiện check thanh toán
        return shipService.testCircuitBreaker(isError);
    }
    @GetMapping("/rate")
    public String testRateLimit() {
        // gọi sang service để thực hiện check thanh toán
        return shipService.testRateLimiter();
    }

}
