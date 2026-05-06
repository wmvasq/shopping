package com.shopping.payment_service.client;

import com.shopping.payment_service.dto.OrderInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "orders-service", url = "${app.orders-service.url}")
public interface OrdersServiceClient {

    @GetMapping("/orders/{id}")
    OrderInfoDto getOrderById(@PathVariable("id") Long id);
}