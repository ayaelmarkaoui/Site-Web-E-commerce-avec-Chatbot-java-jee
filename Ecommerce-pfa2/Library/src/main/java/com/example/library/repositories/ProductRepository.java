package com.example.library.repositories;

import com.example.library.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.description like %?1% or p.name like %?1%")
    List<Product> searchProductsList(String keyword);

    @Query("select p from Product p where p.is_activated = true and p.is_deleted = false")
    List<Product> getAllProducts();
    @Query("select p from Product p where p.category.id=?1")
    List<Product> getRelatedProducts(Long categoryId);
    @Query("select p from Product p where p.is_activated = true and p.is_deleted = false" +
    " order by p.costPrice desc ")
    List<Product> filterHighPrice();
    @Query("select p from Product p where p.is_activated = true and p.is_deleted = false order by p.costPrice")
    List<Product> filterLowPrice();


}
