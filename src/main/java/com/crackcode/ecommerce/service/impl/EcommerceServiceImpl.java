package com.crackcode.ecommerce.service.impl;

import com.crackcode.ecommerce.entity.Customer;
import com.crackcode.ecommerce.entity.Item;
import com.crackcode.ecommerce.entity.Wishlist;
import com.crackcode.ecommerce.repository.CustomerRepository;
import com.crackcode.ecommerce.repository.ItemRepository;
import com.crackcode.ecommerce.repository.SaleRepository;
import com.crackcode.ecommerce.repository.WishlistRepository;
import com.crackcode.ecommerce.service.EcommerceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service("ecommerceService")
@Slf4j
public class EcommerceServiceImpl implements EcommerceService {
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final WishlistRepository wishlistRepository;

    public EcommerceServiceImpl(CustomerRepository customerRepository, SaleRepository saleRepository, WishlistRepository wishlistRepository) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public List<Item> getWishlist(Long customerId) {
        log.info("Getting wishlist for customer {}", customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Wishlist> wishlistEntries = wishlistRepository.findByCustomer(customer);
        return wishlistEntries.stream().map(Wishlist::getItem).collect(Collectors.toList());
    }

    @Override
    public Double getTotalSaleAmountOfCurrentDay() {
        log.info("Getting total sale amount of current day");
        LocalDate today = LocalDate.now();
        return saleRepository.getTotalSaleAmountByDate(today);
    }

    @Override
    public List<Object[]> getMaxSaleDayInRange(LocalDate startDate, LocalDate endDate) {
        log.info("Getting max sale day in range {} and {}", startDate, endDate);
        return saleRepository.getMaxSaleDayInRange(startDate, endDate);
    }

    @Override
    public List<Object[]> getTopSellingItemsAllTime() {
        log.info("Getting top selling items");
        return saleRepository.getTopSellingItemsAllTime();
    }

    @Override
    public List<Object[]> getTopSellingItemsLastMonth() {
        log.info("Getting top selling items last month");
        LocalDate lastMonthStart = LocalDate.now().minusMonths(1);
        return saleRepository.getTopSellingItemsLastMonth(lastMonthStart);
    }
}
