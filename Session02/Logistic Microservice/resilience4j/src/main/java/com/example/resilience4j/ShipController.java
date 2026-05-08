package com.example.resilience4j;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/ships")
@RequiredArgsConstructor
public class ShipController {
    private final ShipService shipService;

    @GetMapping("/pay")
    public CompletableFuture<ResponseEntity<String>> pay() {
        return shipService.payService()
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> {
                    // Lấy ra lỗi lõi (bên trong lớp vỏ CompletionException)
                    Throwable rootCause = e.getCause() != null ? e.getCause() : e;

                    // Chỉ lấy message của lỗi lõi, sẽ mất đi chữ "java.lang..."
                    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(rootCause.getMessage());
                });
    }

    @GetMapping("/test-circuit-breaker")
    public ResponseEntity<String> testCircuitBreaker(@RequestParam("isError") Boolean isError) {
        return ResponseEntity.ok(shipService.testCircuitBreaker(isError));
    }

    @GetMapping("/test-rate-limiter")
    public ResponseEntity<String> testRateLimiter() {
        return ResponseEntity.ok(shipService.testRateLimiter());
    }
}
