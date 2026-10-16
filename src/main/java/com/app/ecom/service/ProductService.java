package com.app.ecom.service;

import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import com.app.ecom.model.Product;
import com.app.ecom.repository.ProductRepository;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductResponse createdProduct(ProductRequest productRequest) {
        Product product = new Product();
        updateProductFromRequest(product,productRequest);
        Product salvedProduct = productRepository.save(product);
        return mapToproductResponse(salvedProduct);
    }

    private ProductResponse mapToproductResponse(Product salvedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(salvedProduct.getId());
        response.setName(salvedProduct.getName());
        response.setActive(salvedProduct.getActive());
        response.setCategory(salvedProduct.getCategory());
        response.setDescription(salvedProduct.getDescription());
        response.setPrice(salvedProduct.getPrice());
        response.setImageUrl(salvedProduct.getImageUrl());
        response.setStockQuantity(salvedProduct.getStockQuantity());

        return response;
    }

    private void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest productRequest) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    updateProductFromRequest(existingProduct, productRequest);
                    Product salvedProduct = productRepository.save(existingProduct);
                    return mapToproductResponse(salvedProduct);
                });
    }
}
