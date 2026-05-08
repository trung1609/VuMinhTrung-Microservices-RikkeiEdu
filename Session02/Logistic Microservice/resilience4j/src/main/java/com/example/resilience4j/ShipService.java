package com.example.resilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ShipService {

    @CircuitBreaker(name = "payService", fallbackMethod = "fallbackPayService")
    @Retry(name = "payService")
    @RateLimiter(name = "payService")
    @TimeLimiter(name = "payService", fallbackMethod = "fallbackPayService")
    public CompletableFuture<String> payService() {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate a delay or failure
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return "Payment successful";
        });
    }

    public CompletableFuture<String> fallbackPayService(Exception e) {
        return CompletableFuture.failedFuture(
                new RuntimeException("Payment service is currently unavailable. Please try again later.", e));
    }

    @CircuitBreaker(name = "payService", fallbackMethod = "testCircuitBreakerFallback")
    public String testCircuitBreaker(boolean isError) {
        if (isError) {
            throw new RuntimeException("Simulated error");
        }else {
            return "Success";
        }
    }

    public String testCircuitBreakerFallback(boolean isError, Throwable throwable) {
        return "Fallback: " + throwable.getMessage();
    }

    @RateLimiter(name = "payService", fallbackMethod = "testRateLimiterFallback")
    public String testRateLimiter(){
        return "Access successful";
    }

    public String testRateLimiterFallback(Throwable throwable){
        return "Too many requests. Please try again later.";
    }
}
