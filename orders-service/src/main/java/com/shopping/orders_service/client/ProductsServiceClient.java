package com.shopping.orders_service.client;

import com.shopping.orders_service.dto.ProductInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-service", url = "${app.products-service.url}")
public interface ProductsServiceClient {

    @GetMapping("/products/{id}")
    ProductInfoDto getProductById(@PathVariable("id") Long id);
}