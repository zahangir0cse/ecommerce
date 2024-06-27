package com.crackcode.ecommerce;

import com.crackcode.ecommerce.controller.EcommerceController;
import com.crackcode.ecommerce.entity.Item;
import com.crackcode.ecommerce.service.EcommerceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class EcommerceApplicationTests {
    @Mock
    private EcommerceService ecommerceService;

    @InjectMocks
    private EcommerceController ecommerceController;

    @Test
    public void testGetWishlist() {
        List<Item> mockWishlist = List.of(new Item("Laptop", 1000.0));
        when(ecommerceService.getWishlist(any(Long.class))).thenReturn(mockWishlist);

        ResponseEntity<List<Item>> response = ecommerceController.getWishlist(1L);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockWishlist, response.getBody());
        verify(ecommerceService, times(1)).getWishlist(1L);
    }

    @Test
    public void testGetTotalSaleAmountOfCurrentDay() {
        when(ecommerceService.getTotalSaleAmountOfCurrentDay()).thenReturn(1000.0);

        ResponseEntity<Double> response = ecommerceController.getTotalSaleAmountOfCurrentDay();

        assertEquals(OK, response.getStatusCode());
        assertEquals(1000.0, response.getBody());
        verify(ecommerceService, times(1)).getTotalSaleAmountOfCurrentDay();
    }

    @Test
    public void testGetMaxSaleDayInRange() {
        LocalDate startDate = LocalDate.now().minusDays(30);
        LocalDate endDate = LocalDate.now();
        List<Object[]> mockResults = Arrays.asList(new Object[][]{new Object[]{LocalDate.now(), 5000.0}});
        when(ecommerceService.getMaxSaleDayInRange(any(LocalDate.class), any(LocalDate.class))).thenReturn(mockResults);

        ResponseEntity<List<Object[]>> response = ecommerceController.getMaxSaleDayInRange(startDate, endDate);

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockResults, response.getBody());
        verify(ecommerceService, times(1)).getMaxSaleDayInRange(startDate, endDate);
    }

    @Test
    public void testGetTopSellingItemsAllTime() {
        List<Object[]> mockResults = Arrays.asList(new Object[][]{new Object[]{"Laptop", 5000.0}});
        when(ecommerceService.getTopSellingItemsAllTime()).thenReturn(mockResults);

        ResponseEntity<List<Object[]>> response = ecommerceController.getTopSellingItemsAllTime();

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockResults, response.getBody());
        verify(ecommerceService, times(1)).getTopSellingItemsAllTime();
    }

    @Test
    public void testGetTopSellingItemsLastMonth() {
        List<Object[]> mockResults = Arrays.asList(new Object[][]{new Object[]{"Laptop", 10L}});
        when(ecommerceService.getTopSellingItemsLastMonth()).thenReturn(mockResults);

        ResponseEntity<List<Object[]>> response = ecommerceController.getTopSellingItemsLastMonth();

        assertEquals(OK, response.getStatusCode());
        assertEquals(mockResults, response.getBody());
        verify(ecommerceService, times(1)).getTopSellingItemsLastMonth();
    }

}
