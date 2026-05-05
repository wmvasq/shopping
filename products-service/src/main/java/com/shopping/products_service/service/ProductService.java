package com.shopping.products_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopping.products_service.client.FakeStoreApiClient;
import com.shopping.products_service.dto.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
     private final FakeStoreApiClient fakeStoreApiClient;

    public List<ProductResponse> getAllProducts() {
        return fakeStoreApiClient.getAllProducts();
    }

    public ProductResponse getProductById(Integer id) {
        return fakeStoreApiClient.getProductById(id);
    }
}
