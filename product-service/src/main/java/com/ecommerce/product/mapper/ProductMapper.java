package com.ecommerce.product.mapper;

import com.ecommerce.product.DTO.ProductRequestDTO;
import com.ecommerce.product.DTO.ProductResponseDTO;
import com.ecommerce.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
     public Product mapProductRequestToProduct(ProductRequestDTO productRequest) {
         return Product.builder()
                 .name(productRequest.name())
                 .description(productRequest.description())
                 .price(productRequest.price())
                 .category(productRequest.category())
                 .build();
     }

     public ProductResponseDTO mapProductToProductResponse(Product product) {
         return new ProductResponseDTO(product.getId(),
                 product.getName(), product.getDescription(),
                 product.getPrice(),product.getCategory());
     }
}
