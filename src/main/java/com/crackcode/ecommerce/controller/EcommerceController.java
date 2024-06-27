package com.crackcode.ecommerce.controller;

import com.crackcode.ecommerce.entity.Item;
import com.crackcode.ecommerce.service.EcommerceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ecommerce")
public class EcommerceController {
    private final EcommerceService ecommerceService;

    public EcommerceController(EcommerceService ecommerceService) {
        this.ecommerceService = ecommerceService;
    }

    @GetMapping("/wishlist/{customerId}")
    public ResponseEntity<List<Item>> getWishlist(@PathVariable Long customerId) {
        List<Item> wishlist = ecommerceService.getWishlist(customerId);
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping("/sales/total")
    public ResponseEntity<Double> getTotalSaleAmountOfCurrentDay() {
        Double totalSales = ecommerceService.getTotalSaleAmountOfCurrentDay();
        return ResponseEntity.ok(totalSales);
    }

    @GetMapping("/sales/max-day")
    public ResponseEntity<List<Object[]>> getMaxSaleDayInRange(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Object[]> maxSaleDay = ecommerceService.getMaxSaleDayInRange(startDate, endDate);
        return ResponseEntity.ok(maxSaleDay);
    }

    @GetMapping("/sales/top-items/all-time")
    public ResponseEntity<List<Object[]>> getTopSellingItemsAllTime() {
        List<Object[]> topItems = ecommerceService.getTopSellingItemsAllTime();
        return ResponseEntity.ok(topItems);
    }

    @GetMapping("/sales/top-items/last-month")
    public ResponseEntity<List<Object[]>> getTopSellingItemsLastMonth() {
        List<Object[]> topItems = ecommerceService.getTopSellingItemsLastMonth();
        return ResponseEntity.ok(topItems);
    }
}
