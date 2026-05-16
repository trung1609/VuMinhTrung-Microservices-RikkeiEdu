package ra.boot3resilient4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ShipService {
    // Thực hiện gọi thanh toán và giao hàng
    // Hàm chính : logic thực hiện bình thường
//    @CircuitBreaker(name = "payService", fallbackMethod = "fallBackCheckPayment")
//    @Retry(name = "payService", fallbackMethod ="fallBackCheckPayment")
    @TimeLimiter(name = "payService", fallbackMethod = "fallBackCheckPayment")
    public CompletableFuture<String> callRemote() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000); // ⏱ simulate slow call (5s > timeout 2s)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "real result";
        });
    }

    public CompletableFuture<String> fallBackCheckPayment(Throwable ex) {
        return CompletableFuture.completedFuture("fallback: " + ex.getMessage());
    }
    @Retry(name = "payService",fallbackMethod = "testCircuitBreakerFallback")
    @CircuitBreaker(name = "payService", fallbackMethod = "testCircuitBreakerFallback")
    public String testCircuitBreaker(boolean isError) {
        if (isError) {
            throw new RuntimeException("testCircuitBreaker");
        }else {
            return "get Data success";
        }
    }

    public String testCircuitBreakerFallback(boolean isError,Throwable ex) {
        return "Breaker is OPEN !!!!";
    }
    @RateLimiter(name = "payService", fallbackMethod = "testRateLimiterFallback")
    public String testRateLimiter(){
        return "Truy cập thành công";
    }

    public String testRateLimiterFallback(Throwable ex){
        return "Đang xử lý, vui lòng thử lại sau 10s!!";
    }
}
