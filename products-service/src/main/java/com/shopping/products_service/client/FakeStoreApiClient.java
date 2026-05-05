package com.shopping.products_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopping.products_service.dto.ProductResponse;

@FeignClient(name = "fakeStoreApi", url = "${fake_store_url}") 
public interface FakeStoreApiClient {

    @GetMapping("/products") 
    List<ProductResponse> getAllProducts();

    @GetMapping("/products/{id}")
    ProductResponse getProductById(@PathVariable("id") Integer id);

}
