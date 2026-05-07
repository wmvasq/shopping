package com.shopping.products_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopping.products_service.client.FakeStoreApiClient;
import com.shopping.products_service.dto.ProductResponse;
import com.shopping.products_service.exception.ProductNotFoundException;

import lombok.RequiredArgsConstructor;
/**
 * Service for managing products.
 * Acts as a proxy to external FakeStore API.
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final FakeStoreApiClient fakeStoreApiClient;

    public List<ProductResponse> getAllProducts() {
        return fakeStoreApiClient.getAllProducts();
    }

    public ProductResponse getProductById(Integer id) {
        ProductResponse product = fakeStoreApiClient.getProductById(id);
        if (product == null) {
            //call product not found exception
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return product;
    }

}
