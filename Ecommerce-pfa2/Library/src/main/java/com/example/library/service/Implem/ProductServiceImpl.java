package com.example.library.service.Implem;

import com.example.library.dto.ProductDto;
import com.example.library.entities.Category;
import com.example.library.entities.Product;
import com.example.library.repositories.ProductRepository;
import com.example.library.service.ProductService;
import com.example.library.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageUpload imageUpload;

    @Override
    public ProductDto getById(Long id) {
        Product product=productRepository.getById(id);
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCurrentQuantity(product.getCurrentQuantity());
        productDto.setCategory(product.getCategory());
        productDto.setSalePrice(product.getSalePrice());
        productDto.setCostPrice(product.getCostPrice());
        productDto.setImage(product.getImage());
        productDto.setDeleted(product.is_deleted());
        productDto.setActivated(product.is_activated());
        return productDto;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product getProductByID(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getRelatedProducts(Long categoryId) {
        return productRepository.getRelatedProducts(categoryId);
    }

    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productDtoList=new ArrayList<>();
        List<Product> products=productRepository.findAll();
        for (Product product :products){
            ProductDto productDto=new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setCostPrice(product.getCostPrice());
            productDto.setSalePrice(product.getSalePrice());
            productDto.setCurrentQuantity(product.getCurrentQuantity());
            productDto.setCategory(product.getCategory());
            productDto.setImage(product.getImage());
            productDto.setActivated(product.is_activated());
            productDto.setDeleted(product.is_deleted());
            productDtoList.add(productDto);

        }
        return productDtoList ;
    }

    @Override
    public Product save(MultipartFile imageProduct,ProductDto productDto) {
        Product product=new Product();
       try {
           if(imageProduct==null){
               product.setImage(null);
           }else {
               if(imageUpload.uploadImage(imageProduct)){
                   System.out.println("Upload successfully");
               }
               product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));

           }
           product.setName(productDto.getName());
           product.setDescription(productDto.getDescription());
           product.setSalePrice(productDto.getSalePrice());
           product.setCostPrice(productDto.getCostPrice());
           product.setCurrentQuantity(productDto.getCurrentQuantity());
           product.setCategory(productDto.getCategory());
           product.set_activated(true);
           product.set_deleted(false);
           return productRepository.save(product);
       }catch (Exception e)
       {
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public Product update(MultipartFile imageProduct,ProductDto productDto) {

        try{

            Product product=productRepository.getById(productDto.getId());
            if(imageProduct==null){
                product.setImage(productDto.getImage());
            }else {
                if(imageUpload.uploadImage(imageProduct)){
                    System.out.println("Update successfully");
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
                }
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setSalePrice(productDto.getSalePrice());
            product.setCostPrice(productDto.getCostPrice());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setCategory(productDto.getCategory());
            return productRepository.save(product);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void deleteById(Long id) {
        Product prd=productRepository.getById(id);
        prd.set_activated(false);
        prd.set_deleted(true);
        productRepository.save(prd);
    }

    @Override
    public void enabledById(Long id) {
        Product prd=productRepository.getById(id);
        prd.set_activated(true);
        prd.set_deleted(false);
        productRepository.save(prd);

    }
    @Override
    public List<Product> filterHighPrice(){
        return productRepository.filterHighPrice();
    }
    @Override
    public List<Product> filterLowPrice(){
        return productRepository.filterLowPrice();
    }
}
