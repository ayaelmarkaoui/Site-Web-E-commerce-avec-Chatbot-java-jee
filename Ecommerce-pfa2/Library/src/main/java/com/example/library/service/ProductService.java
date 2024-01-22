package com.example.library.service;

import com.example.library.dto.ProductDto;
import com.example.library.entities.Category;
import com.example.library.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    Product save(MultipartFile imageProduct, ProductDto productDto);

    Product update(MultipartFile imageProduct,ProductDto productDto);
    void deleteById(Long id);
    void enabledById(Long id);

    ProductDto getById(Long id);
    /*Customer*/
    List<Product> getAllProducts();
    Product getProductByID(Long id);
    List<Product> getRelatedProducts(Long categoryId);
    List<Product> filterHighPrice();
    List<Product> filterLowPrice();

}
