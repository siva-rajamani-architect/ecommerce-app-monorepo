package com.ecommerce.product.mapper;

import com.ecommerce.product.DTO.ProductRequestDTO;
import com.ecommerce.product.DTO.ProductResponseDTO;
import com.ecommerce.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
     public Product mapProductRequestToProduct(ProductRequestDTO productRequest) {
         return Product.builder()
                 .productCode(productRequest.productCode())
                 .name(productRequest.name())
                 .description(productRequest.description())
                 .price(productRequest.price())
                 .category(productRequest.category())
                 .status(productRequest.status())
                 .createdAt(productRequest.createdAt())
                 .updatedAt(productRequest.updatedAt())
                 .brand(productRequest.brand())
                 .build();
     }

     public ProductResponseDTO mapProductToProductResponse(Product product) {
         return new ProductResponseDTO(product.getId(),
                 product.getProductCode(),
                 product.getName(),
                 product.getDescription(),
                 product.getBrand(),
                 product.getCategory(),
                 product.getPrice(),
                 product.getStatus(),
                 product.getCreatedAt(),
                 product.getUpdatedAt()
                 );
     }
}
