package com.example.client.Controller;

import com.example.library.dto.ProductDto;
import com.example.library.entities.Category;
import com.example.library.entities.Product;
import com.example.library.service.CategoryService;
import com.example.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/products")

    public String products(Model model){

        List<ProductDto> productsDto=productService.findAll();
        model.addAttribute("products",productsDto);
        return "shop";
    }

    @GetMapping("/find-products/{id}")
    public String findProductById(@PathVariable("id")Long id, Model model){
        Product product=productService.getProductByID(id);
        Long categoryId=product.getCategory().getId();
        List<Product> products =productService.getRelatedProducts(categoryId);
        model.addAttribute("product",product);
        model.addAttribute("products",products);
        return "shop-details";
    }
   /*@GetMapping("/high-price")
    public String filterHighPrice(Model model){
        List<Category> categories = categoryService.findAllByActivated();
        List<Product> products = productService.filterHighPrice();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "high-price";
   }*/

}
