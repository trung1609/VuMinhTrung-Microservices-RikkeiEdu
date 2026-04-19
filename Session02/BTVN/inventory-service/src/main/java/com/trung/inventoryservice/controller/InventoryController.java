package com.trung.inventoryservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @GetMapping("/test")
    public String test() {
        return "Hello World! Welcome to Inventory Service";
    }
}
