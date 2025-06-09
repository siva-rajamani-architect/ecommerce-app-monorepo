package com.ecommerce.product.service;

import com.ecommerce.product.DTO.ProductRequestDTO;
import com.ecommerce.product.DTO.ProductResponseDTO;
import com.ecommerce.product.exception.InternalServerException;
import com.ecommerce.product.exception.InvalidProductDataException;
import com.ecommerce.product.exception.ProductNotFoundException;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponseDTO save(ProductRequestDTO productRequest) {
        validate(productRequest);
        Product product = productMapper.mapProductRequestToProduct(productRequest);
        product.setCreatedAt(LocalDateTime.now());
        productRepository.save(product);
        return productMapper.mapProductToProductResponse(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::mapProductToProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO update(ProductRequestDTO productRequest) {
        Product product = productRepository.findById(productRequest.id())
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + productRequest.id() + " not found."));
        try {
            if (product != null) {
                product.setProductCode(productRequest.productCode());
                product.setName(productRequest.name());
                product.setDescription(productRequest.description());
                product.setBrand(productRequest.brand());
                product.setPrice(productRequest.price());
                product.setCategory(productRequest.category());
                product.setStatus(productRequest.status());
                product.setUpdatedAt(LocalDateTime.now());
                productRepository.save(product);
                return productMapper.mapProductToProductResponse(product);
            }
        } catch (Exception e) {
            throw new InternalServerException("An error occurred while updating the product.", e);
        }

        return null;
    }

    public ProductResponseDTO getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::mapProductToProductResponse).orElseThrow(()->new ProductNotFoundException("Product with id " + id + " not found."));
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found."));
        productRepository.deleteById(id);
    }

    private void validate(ProductRequestDTO productRequest) {
        if (productRequest.name().isEmpty() || productRequest.name().isBlank()) {
            throw new InvalidProductDataException("Product name cannot be empty or blank.");
        }
        if (productRequest.description().isEmpty() || productRequest.description().isBlank()) {
            throw new InvalidProductDataException("Product description cannot be empty or blank.");
        }
        if (productRequest.price().compareTo(BigDecimal.ZERO)<= 0) {
            throw new InvalidProductDataException("Product price cannot be negative or zero.");
        }
        if (productRequest.category().isEmpty() || productRequest.category().isBlank()) {
            throw new InvalidProductDataException("Product category cannot be empty or blank.");
        }
    }
}
