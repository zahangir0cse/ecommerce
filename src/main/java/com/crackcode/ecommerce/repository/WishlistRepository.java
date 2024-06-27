package com.crackcode.ecommerce.repository;

import com.crackcode.ecommerce.entity.Customer;
import com.crackcode.ecommerce.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByCustomer(Customer customer);
}
