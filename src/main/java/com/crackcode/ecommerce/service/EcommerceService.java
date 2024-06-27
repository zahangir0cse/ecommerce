package com.crackcode.ecommerce.service;

import com.crackcode.ecommerce.entity.Item;

import java.time.LocalDate;
import java.util.List;

public interface EcommerceService {
    List<Item> getWishlist(Long customerId);
    Double getTotalSaleAmountOfCurrentDay();
    List<Object[]> getMaxSaleDayInRange(LocalDate startDate, LocalDate endDate);
    List<Object[]> getTopSellingItemsAllTime();
    List<Object[]> getTopSellingItemsLastMonth();
}
